package apptive.fruitable.dto;

import apptive.fruitable.domain.post.Photo;
import lombok.*;

@Getter @Setter
@ToString @NoArgsConstructor
public class PhotoDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;
    private Long fileSize;

    public Photo toEntity() {

        Photo entity = Photo.builder()
                .origFilename(origFilename)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();

        return entity;
    }

    @Builder
    public PhotoDto(Long id, String origFilename,String filePath, Long fileSize) {
        this.id = id;
        this.origFilename = origFilename;
        this.filePath =filePath;
        this.fileSize = fileSize;
    }
}
