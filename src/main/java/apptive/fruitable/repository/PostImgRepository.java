package apptive.fruitable.repository;

import apptive.fruitable.domain.post.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    List<PostImg> findByPostIdOrderByIdAsc(Long postId);

    PostImg findByPostIdAndRepImgYn(Long postId, String repImgYn);
}
