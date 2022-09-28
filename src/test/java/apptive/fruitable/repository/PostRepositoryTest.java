package apptive.fruitable.repository;

import apptive.fruitable.domain.post.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired PostRepository postRepository;

    @Test
    public void 게시글저장() {
        //given
        //Post post = new Post(1L, "memberA",1, "contact", "title", "content", 123, LocalDateTime.now());
        //when
        //postRepository.save(post);
        //then
        //Assertions.assertThat(post.getTitle()).isEqualTo("title");
    }
}
