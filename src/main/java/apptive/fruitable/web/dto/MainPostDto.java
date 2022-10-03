package apptive.fruitable.web.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MainPostDto {

    private Long id;
    private String userId;
    private String title;
    private String mainImgUrl;
    private Integer price;
    private LocalDateTime endDate;

    @QueryProjection
    public MainPostDto(Long id, String userId, String title, String mainImgUrl, Integer price, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.mainImgUrl = mainImgUrl;
        this.price = price;
        this.endDate = endDate;
    }
}
