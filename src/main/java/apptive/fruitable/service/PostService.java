package apptive.fruitable.service;

import apptive.fruitable.domain.post.Photo;
import apptive.fruitable.domain.post.Post;
import apptive.fruitable.repository.PhotoRepository;
import apptive.fruitable.repository.PostRepository;
import apptive.fruitable.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;
    private final PhotoService photoService;

    /**
     * 글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
     * PostService의 savePost()를 실행하게 된다.
     */
    public Long savePost(PostDto postDto, List<MultipartFile> files) throws Exception {

        //System.out.println(postDto.getTitle());
        //상품 등록
        Post post = new Post();
        post.updatePost(postDto);
        //System.out.println(post.getTitle());
        postRepository.save(post);

        //이미지 등록
        for(int i=0; i<files.size(); i++) {

            Photo photo = new Photo();
            photo.setPost(post);

            if(i==0) photo.setRepImg("Y");
            else photo.setRepImg("N");

            photoService.saveFile(photo, files.get(i));
        }

        return post.getId();
    }

    /**
     * 게시글 목록 가져오기
     */
    /*public List<PostDto> getPostList() {
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
    }*/

    /**
     * 게시글 클릭시 상세게시글의 내용 확인
     * @param id
     * @return 해당 게시글의 데이터만 가져와 화면에 뿌려줌
     */
    /*public PostDto getPost(Long id) {

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
    }*/

    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }

    /*@Transactional
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
    }*/
}
