package apptive.fruitable.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostImgDto {

    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

    @Builder
    public PostImgDto(String imgName, String oriImgName, String imgUrl, String repImgYn) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public PostImg toEntity(PostImgDto dto) {
        PostImg entity = PostImg.builder()
                .imgName(dto.getImgName())
                .oriImgName(dto.oriImgName)
                .imgUrl(dto.imgUrl)
                .repImgYn(dto.repImgYn)
                .build();

        return entity;
    }

    public static PostImgDto of(PostImg entity) {
        PostImgDto dto = PostImgDto.builder()
                .imgName(entity.getImgName())
                .oriImgName(entity.getOriImgName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepImgYn())
                .build();

        return dto;
    }
}
