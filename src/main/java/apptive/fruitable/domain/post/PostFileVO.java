package apptive.fruitable.domain.post;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostFileVO {

    private String userId;
    private String title;
    private String contact;
    private Integer vege;
    private String content;
    private Integer price;
    private LocalDateTime endDate;
    private List<MultipartFile> files;
}
