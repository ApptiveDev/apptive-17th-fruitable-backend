package apptive.fruitable.repository;

import apptive.fruitable.web.dto.MainPostDto;
import apptive.fruitable.web.dto.PostSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<MainPostDto> getMainPostPage(PostSearchDto postSearchDto, Pageable pageable);
}
