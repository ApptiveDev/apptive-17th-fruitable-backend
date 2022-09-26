package apptive.fruitable.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import apptive.fruitable.domain.post.Post;

public interface PostService {

    Page<Post> findBoardList(Pageable pageable);
    Post findBoardByIdx(Long idx);
}
