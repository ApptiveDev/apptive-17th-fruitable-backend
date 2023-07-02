package apptive.fruitable.board.service.impl;

import apptive.fruitable.board.domain.comment.Comment;
import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentRequestDto;
import apptive.fruitable.board.repository.CommentRepository;
import apptive.fruitable.board.repository.PostRepository;
import apptive.fruitable.board.service.inter.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long saveComment(CommentRequestDto commentRequestDto) {

        // 게시글 가져오기
        Post post = postRepository.findById(commentRequestDto.getPostId()).get();

        Comment comment = new Comment();
        comment.toCommentEntity(commentRequestDto, post);

        return comment.getCommentId();
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public Long update(Long commentId, CommentRequestDto commentRequestDto) {
        return null;
    }
}
