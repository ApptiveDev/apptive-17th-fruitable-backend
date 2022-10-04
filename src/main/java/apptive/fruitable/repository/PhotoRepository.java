package apptive.fruitable.repository;

import apptive.fruitable.domain.post.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
