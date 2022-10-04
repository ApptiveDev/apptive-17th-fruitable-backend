package apptive.fruitable.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @Builder
    public Photo(String origFilename, String filePath, Long fileSize) {
        this.origFilename = origFilename;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    //Post 정보 저장
    public void setPost(Post post) {
        this.post = post;

        //게시글에 현재 파일이 존재하지 않는다면
        if(!post.getPhoto().contains(this))
            //파일 추가
            post.getPhoto().add(this);
    }
}
