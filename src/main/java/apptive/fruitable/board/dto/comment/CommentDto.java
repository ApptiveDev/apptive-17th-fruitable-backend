package apptive.fruitable.board.dto.comment;

import apptive.fruitable.board.domain.comment.Comment;
import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.post.PostResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

public class CommentDto {

    //<------------------Request-------------------->
    @Getter @Setter
    public static class CommentRequestDto {
        private String commentContent; // 댓글 내용
        private String memberName; // 작성자
    }

    @Getter @Setter
    public static class CommentUpdateRequestDto {
        private String commentContent; // 댓글 내용
    }

    //<------------------Response-------------------->
    @Getter @Setter
    public static class CommentResponseDto {
        private Long commentId; // 댓글 id
        private String commentContent; // 댓글 내용
        private String memberName; // 작성자

        private static ModelMapper modelMapper = new ModelMapper();

        public static CommentDto.CommentResponseDto of(Comment comment) {
            return modelMapper.map(comment, CommentDto.CommentResponseDto.class);
        }
    }
}
