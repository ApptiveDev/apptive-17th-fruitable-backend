package apptive.fruitable.service;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.domain.post.PostDto;
import apptive.fruitable.domain.post.PostImg;
import apptive.fruitable.domain.post.PostImgDto;
import apptive.fruitable.repository.PostImgRepository;
import apptive.fruitable.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;
    private final PostImgService postImgService;

    /**
     * pagealbe로 넘어온 pageNumber 객체가 0 이하일 때, 0으로 초기화
     * @param pageable
     * @return 기본 페이지크기(10)으로 새로운 PageRequest 객체를 만들어 페이징 처리된 게시글 리스트 반환
     */
    @Override
    public Page<Post> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return postRepository.findAll(pageable);
    }

    /**
     * post의 idx 값을 이용하여 post 객체를 만듦
     * @param idx
     * @return post Entity
     */
    @Override
    public Post findBoardByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }

    public Long savedPost(PostDto postDto, List<MultipartFile> postImgFileList) throws Exception {

        //상품 등록
        Post post = postDto.toEntity(postDto);
        postRepository.save(post);

        //이미지 등록
        for(int i=0; i<postImgFileList.size(); i++) {
            PostImg postImg = PostImg.builder()
                    .post(post)
                    .repImgYn(i == 0 ? "Y" : "N") //첫번째 사진일 경우 대표 상품 이미지 여부 값 Y로 세팅
                    .build();

            postImgService.savePostImg(postImg, postImgFileList.get(i));
        }

        return post.getId();
    }

    @Transactional(readOnly = true)
    public PostDto getPostDetail(Long postId) {

        List<PostImg> postImgList = postImgRepository.findByPostIdOrderByIdAsc(postId);
        List<PostImgDto> postImgDtoList = new ArrayList<>();

        for(PostImg postImg : postImgList) {
            PostImgDto postImgDto = PostImgDto.of(postImg);
            postImgDtoList.add(postImgDto);
        }

        Post post = postRepository.findById(postId).orElseThrow(EntityExistsException::new);
        PostDto postDto = PostDto.of(post);
        postDto.setPostImgDtoList(postImgDtoList);

        return postDto;
    }

    public Long updatePost(PostDto postDto, List<MultipartFile> postImgFileList) throws Exception {

        //상품 수정
        Post post = postRepository.findById(postDto.getId()).orElseThrow(EntityNotFoundException::new);
        post.updatePost(postDto);

        List<Long> postImgIds = postDto.getPostImgIdList();

        //이미지 등록
        for(int i=0; i<postImgFileList.size(); i++) {
            postImgService.updatePostImg(postImgIds.get(i), postImgFileList.get(i));
        }

        return post.getId();
    }
}
