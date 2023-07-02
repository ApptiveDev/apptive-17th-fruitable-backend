package apptive.fruitable.board.dto.post;

import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentDto;
import apptive.fruitable.login.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


public class PostResponseDto {

    //<------------------GET-------------------->
    @Getter @Setter
    public static class GetDto {
        private Long id;

        private Member userId;

        private String contact;
        private Integer vege;
        private String title;
        private String content;
        private Integer price;
        private LocalDate endDate;

        private static ModelMapper modelMapper = new ModelMapper();

        public static PostResponseDto.GetDto of(Post post) {
            return modelMapper.map(post, PostResponseDto.GetDto.class);
        }
    }

    @Getter @Setter
    public static class GetWithCommentDto {
        private Long id;

        private Member userId;

        private String contact;
        private Integer vege;
        private String title;
        private String content;
        private Integer price;
        private LocalDate endDate;
        private List<CommentDto.CommentResponseDto> comments;

        private static ModelMapper modelMapper = new ModelMapper();

        public static PostResponseDto.GetWithCommentDto of(Post post) {
            return modelMapper.map(post, PostResponseDto.GetWithCommentDto.class);
        }
    }
}
