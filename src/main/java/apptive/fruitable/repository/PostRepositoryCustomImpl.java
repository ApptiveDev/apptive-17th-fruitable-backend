package apptive.fruitable.repository;

import apptive.fruitable.domain.post.QPost;
import apptive.fruitable.domain.post.QPostImg;
import apptive.fruitable.web.dto.MainPostDto;
import apptive.fruitable.web.dto.PostSearchDto;
import apptive.fruitable.web.dto.QMainPostDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public PostRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MainPostDto> getMainPostPage(PostSearchDto postSearchDto, Pageable pageable) {
        return null;
    }
}
