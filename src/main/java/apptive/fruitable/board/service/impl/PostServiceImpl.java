package apptive.fruitable.board.service.impl;

import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentDto;
import apptive.fruitable.board.dto.post.PostResponseDto;
import apptive.fruitable.board.dto.post.PostRequestDto;
import apptive.fruitable.board.repository.PostRepository;
import apptive.fruitable.board.service.inter.CommentService;
import apptive.fruitable.board.service.inter.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private CommentService commentService;
    private final PostRepository postRepository;

    /**
     * 글쓰기 Form에서 내용을 입력한 뒤, '글쓰기' 버튼을 누르면 Post 형식으로 요청이 오고,
     * PostService의 savePost()를 실행하게 된다.
     */
    @Transactional
    public Long savePost(PostRequestDto requestDto) throws Exception {

        Post post = new Post();
        post.updatePost(requestDto);

        postRepository.save(post);

        return post.getId();
    }

    /**
     * 게시글 목록 가져오기
     */
    public List<PostResponseDto.GetDto> getPostList() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto.GetDto> postResponseDtoList = new ArrayList<>();

        for (Post post : postList) {

            PostResponseDto.GetDto postResponseDto = PostResponseDto.GetDto.of(post);

            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }

    /**
     * 게시글 클릭시 상세게시글의 내용 확인
     * @param postId
     * @return 해당 게시글의 데이터만 가져와 화면에 뿌려줌
     */
    @Transactional(readOnly = true)
    public PostResponseDto.GetWithCommentDto getPost(Long postId) {

        // 게시글 가져오기
        Post post = postRepository.findById(postId)
                .orElseThrow(EntityNotFoundException::new);
        PostResponseDto.GetWithCommentDto postResponseDto = PostResponseDto.GetWithCommentDto.of(post);

        // 댓글 가져오기
        List<CommentDto.CommentResponseDto> comments = commentService.commentsList(postId);
        postResponseDto.setComments(comments);

        return postResponseDto;
    }

    @Transactional
    public void deletePost(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        postRepository.deleteById(id);
    }

    @Transactional
    public Long update(
            Long id,
            PostRequestDto requestDto
    ) throws IOException {

        //상품 수정
        Post post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        post.updatePost(requestDto);

        postRepository.save(post);

        return post.getId();
    }
}
