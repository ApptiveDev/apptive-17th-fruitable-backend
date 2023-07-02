package apptive.fruitable.board.service.inter;

import apptive.fruitable.board.dto.comment.CommentRequestDto;

public interface CommentService {

    public Long saveComment(CommentRequestDto requestDto);
    public void deleteComment(Long commentId);
    public Long update(Long commentId, CommentRequestDto commentRequestDto);
}
