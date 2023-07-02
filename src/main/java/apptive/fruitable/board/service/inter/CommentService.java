package apptive.fruitable.board.service.inter;

import apptive.fruitable.board.dto.comment.CommentDto;

import java.util.List;

public interface CommentService {

    public List<CommentDto.CommentResponseDto> commentsList(Long postId);
    public Long saveComment(Long postId, CommentDto.CommentRequestDto requestDto);
    public void deleteComment(Long commentId);
    public String update(CommentDto.CommentUpdateRequestDto commentDto);
}
