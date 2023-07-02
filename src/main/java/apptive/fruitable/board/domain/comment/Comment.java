package apptive.fruitable.board.domain.comment;

import apptive.fruitable.base.domain.BaseEntity;
import apptive.fruitable.board.domain.post.Post;
import apptive.fruitable.board.dto.comment.CommentDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String commentContent; // 댓글 내용
    private String memberName; // 작성자

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post")
    private Post post;

    public void toCommentEntity(CommentDto.CommentRequestDto commentDto, Post post) {

        this.memberName = commentDto.getMemberName();
        this.commentContent = commentDto.getCommentContent();
        this.post = post;
    }
}
