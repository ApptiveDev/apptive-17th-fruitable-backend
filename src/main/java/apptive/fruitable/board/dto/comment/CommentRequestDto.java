package apptive.fruitable.board.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentRequestDto {

    private String commentContent; // 댓글 내용
    private Long postId; // 게시글 id
}
