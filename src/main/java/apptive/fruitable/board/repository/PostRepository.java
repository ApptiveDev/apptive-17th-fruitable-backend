package apptive.fruitable.board.repository;

import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.login.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByUserId(Member member);
    boolean existsByUserId(Member userId);
}
