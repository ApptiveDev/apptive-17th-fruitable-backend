package apptive.fruitable.domain.post;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


//추후 @Table로 users 테이블과 매핑 필요
@Entity
@NoArgsConstructor
@Getter @ToString
@Table(name = "post")
public class Post {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //회원 정보 (Userid - 외래키(@Column), contact - 직접 받아옴)
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, length = 20)
    private String contact;

    //게시글 필요 정보
    @Column(nullable = false)
    private Integer vege; //0 - 과일, 1 - 채소
    @Column(length = 20, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer price;
    private LocalDateTime endDate;

    @Builder
    public Post(String userId, String contact, Integer vege, String title, String content, Integer price, LocalDateTime endDate) {
        this.userId = userId;
        this.contact = contact;
        this.vege = vege;
        this.title = title;
        this.content = content;
        this.price = price;
        this.endDate = endDate;
    }

    public void updatePost(PostDto postDto) {
        this.userId = postDto.getUserId();
        this.contact = postDto.getContact();
        this.vege = postDto.getVege();
        this.title = postDto.getTitle();
        this.content =  postDto.getContent();
        this.price = postDto.getPrice();
        this.endDate = postDto.getEndDate();
    }
}
