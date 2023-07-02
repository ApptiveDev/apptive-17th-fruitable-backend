package apptive.fruitable.board.domain.post;

import apptive.fruitable.base.domain.BaseEntity;
import apptive.fruitable.board.dto.post.PostRequestDto;
import apptive.fruitable.login.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
@EntityListeners(AutoCloseable.class)
@Table(name = "post")
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    //@JoinColumn(name = "member")
    private Member userId;

    @Column(nullable = false, length = 5000)
    private String contact;

    //게시글 필요 정보
    @Column(nullable = false)
    private Integer vege; //0 - 과일, 1 - 채소
    @Column(length = 1000, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer price;
    private LocalDate endDate;

    public void updatePost(PostRequestDto postDto) {
        this.userId = postDto.getUserId();
        this.contact = postDto.getContact();
        this.vege = postDto.getVege();
        this.title = postDto.getTitle();
        this.content =  postDto.getContent();
        this.price = postDto.getPrice();
        this.endDate = postDto.getEndDate();
    }
}
