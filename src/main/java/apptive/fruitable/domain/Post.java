package apptive.fruitable.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


//추후 @Table로 users 테이블과 매핑 필요
@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //회원 정보 (Userid - 외래키(@Column), contact - 직접 받아옴)
    private String Userid;
    @Column(length = 100, nullable = false)
    private String contact;

    //게시글 필요 정보
    @Column(length = 20, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private int price;
    //사진
    private LocalDateTime endDate; //마감기한

    @Builder
    public Post(String UserId, String title, String content, int price, LocalDateTime endDate) {
        this.Userid = UserId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.endDate = endDate;
    }
}
