package apptive.fruitable.domain.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@ToString
public class UploadVO {
    private String name;
    private MultipartFile file;
}
