package apptive.fruitable.repository;

import apptive.fruitable.domain.post.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
