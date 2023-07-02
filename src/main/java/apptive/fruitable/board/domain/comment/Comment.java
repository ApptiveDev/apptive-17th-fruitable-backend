package apptive.fruitable.board.domain.comment;

import apptive.fruitable.base.domain.BaseEntity;
import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentRequestDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String commentContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post")
    private Post post;

    public void toCommentEntity(CommentRequestDto commentDto, Post post) {

        this.commentContent = commentDto.getCommentContent();
        this.post = post;
    }
}
