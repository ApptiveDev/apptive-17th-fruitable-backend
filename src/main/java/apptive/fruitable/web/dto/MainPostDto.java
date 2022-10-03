package apptive.fruitable.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
public class MainPostDto {

    private Long id;
    private String userId;
    private String title;
    private String mainImgUrl;
    private Integer price;
    private LocalDateTime endDate;
}
