package apptive.fruitable.service;

import apptive.fruitable.domain.post.Post;
import apptive.fruitable.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * pagealbe로 넘어온 pageNumber 객체가 0 이하일 때, 0으로 초기화
     * @param pageable
     * @return 기본 페이지크기(10)으로 새로운 PageRequest 객체를 만들어 페이징 처리된 게시글 리스트 반환
     */
    @Override
    public Page<Post> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return postRepository.findAll(pageable);
    }

    /**
     * post의 idx 값을 이용하여 post 객체를 만듦
     * @param idx
     * @return post Entity
     */
    @Override
    public Post findBoardByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }
}
