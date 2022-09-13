package apptive.fruitable.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


//추후 @Table로 users 테이블과 매핑 필요
@Entity
@Getter @Setter
public class Post {

    @Id @GeneratedValue
    private Long id;

    //회원 정보 (Userid - 외래키(@Column), contact - 직접 받아옴)
    private String Userid;
    private String contact;

    //게시글 필요 정보
    private String title;
    private String content;
    private int price;
    //사진
    private LocalDateTime uploadDate; //업로드 날짜
    private LocalDateTime endDate; //마감기한
}
