package apptive.fruitable.service;

import apptive.fruitable.domain.post.Photo;
import apptive.fruitable.domain.post.Post;
import apptive.fruitable.repository.PhotoRepository;
import apptive.fruitable.repository.PostRepository;
import apptive.fruitable.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private PhotoRepository photoRepository;
    private FileHandler fileHandler;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
     * PostService의 savePost()를 실행하게 된다.
     * @param postDto
     * @return
     */
    @Transactional
    public Long savePost(PostDto postDto, List<MultipartFile> files) throws Exception {

        Post post = postDto.toEntity();
        List<Photo> photoList = fileHandler.parseFileInfo(post, files);

        //파일이 존재할 때만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                //파일을 DB에 저장
                post.addPhoto(photoRepository.save(photo));
            }
        }

        return postRepository.save(post).getId();
    }

    /*
     * 게시글 목록 가져오기
     */
    @Transactional
    public List<PostDto> getPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : postList) {

            PostDto postDto = PostDto.builder()
                    .userId(post.getUserId())
                    .contact(post.getContact())
                    .vege(post.getVege())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .price(post.getPrice())
                    .endDate(post.getEndDate())
                    .build();

            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    /**
     * 게시글 클릭시 상세게시글의 내용 확인
     * @param id
     * @return 해당 게시글의 데이터만 가져와 화면에 뿌려줌
     */
    @Transactional
    public PostDto getPost(Long id) {

        Post post = postRepository.findById(id).get();

        PostDto postDto = PostDto.builder()
                .userId(post.getUserId())
                .contact(post.getContact())
                .vege(post.getVege())
                .title(post.getTitle())
                .content(post.getContent())
                .price(post.getPrice())
                .endDate(post.getEndDate())
                .build();

        return postDto;
    }

    @Transactional
    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }

    @Transactional
    public Long update(
            Long id,
            PostDto postDto,
            List<MultipartFile> files
    ) throws Exception {

        Post post = postRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        List<Photo> photoList = fileHandler.parseFileInfo(post, files);

        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                photoRepository.save(photo);
            }
        }

        post.updatePost(postDto);
        return id;
    }
}
