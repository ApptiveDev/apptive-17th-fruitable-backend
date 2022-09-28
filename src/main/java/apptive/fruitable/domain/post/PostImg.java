package apptive.fruitable.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @Entity
@Table(name = "post_img")
@NoArgsConstructor
@AllArgsConstructor
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_img_id")
    private Long id;

    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public PostImg(String imgName, String oriImgName, String imgUrl, String repImgYn, Post post) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
        this.post = post;
    }

    //PostImgService에서 이미지 수정 시 사용
    public void imgUpdate(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }
}
