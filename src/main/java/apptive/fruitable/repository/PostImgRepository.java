package apptive.fruitable.repository;

import apptive.fruitable.domain.post.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
}
