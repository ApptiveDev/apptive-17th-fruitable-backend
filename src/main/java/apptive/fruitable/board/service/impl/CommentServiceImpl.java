package apptive.fruitable.board.service.impl;

import apptive.fruitable.board.domain.comment.Comment;
import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentDto;
import apptive.fruitable.board.repository.CommentRepository;
import apptive.fruitable.board.repository.PostRepository;
import apptive.fruitable.board.service.inter.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentDto.CommentResponseDto> commentsList(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        List<CommentDto.CommentResponseDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {

            CommentDto.CommentResponseDto commentResponseDto = CommentDto.CommentResponseDto.of(comment);
            commentDtos.add(commentResponseDto);
        }

        return commentDtos;
    }

    @Transactional
    public Long saveComment(Long postId, CommentDto.CommentRequestDto commentDto) {

        // 게시글 가져오기
        Post post = postRepository.findById(postId).get();

        Comment comment = new Comment();
        comment.toCommentEntity(commentDto, post);

        commentRepository.save(comment);

        return comment.getCommentId();
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public String update(CommentDto.CommentUpdateRequestDto commentDto) {
        return null;
    }
}
