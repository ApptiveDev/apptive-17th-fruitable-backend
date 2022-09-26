package apptive.fruitable.controller;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostControllerTest {

    @Autowired
    EntityManager em;
    @Autowired
    PostController postController;
    @Autowired PostRepository postRepository;

    @Test
    public void 게시글수정() {
        //given
        Post post = createPost();
        System.out.println(post.getContent()+" + "+post.getPrice());
        //when
        postController.rewritePost(post.getIdx(), new Post(1L, "memberA", "contact", "title", "changeContent", 1111, LocalDateTime.now()));
        //then
        System.out.println(post.getContent()+" + "+post.getPrice());
    }

    private Post createPost() {
        Post post = new Post();
        post.setIdx(1L);
        post.setUserId("memberA");
        post.setContact("contact");
        post.setTitle("title");
        post.setContent("content");
        post.setPrice(123);
        post.setEndDate(LocalDateTime.now());

        postRepository.save(post);
        return post;
    }
}
