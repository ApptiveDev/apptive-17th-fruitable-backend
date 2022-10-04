package apptive.fruitable.controller;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.dto.PostDto;
import apptive.fruitable.repository.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostControllerTest {

    @Autowired
    PostServiceImpl postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostImgRepository postImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5; i++) {
            String path = "/Users/hjhwang/Projects/Spring/fruitable/";
            String imgName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imgName, "img/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("이미지 등록 테스트")
    public void savePost() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setUserId("userA");
        postDto.setContact("123-456");
        postDto.setVege(0);
        postDto.setTitle("title");
        postDto.setContent("content");
        postDto.setPrice(123);
        postDto.setEndDate(LocalDateTime.now());

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long postId = postService.savedPost(postDto, multipartFileList);
        //List<PostImg> postImgList = postImgRepository.findByItemIdOrderByAsc(postId);

        Post post = postRepository.findById(postId)
                .orElseThrow(EntityNotFoundException::new);

        assertEquals(postDto.getUserId(), post.getUserId());
        assertEquals(postDto.getContact(), post.getContact());
        assertEquals(postDto.getPrice(), post.getPrice());
        //assertEquals(multipartFileList.get(0).getOriginalFilename(), postImgList.get(0).getOriImgName());
    }
}
