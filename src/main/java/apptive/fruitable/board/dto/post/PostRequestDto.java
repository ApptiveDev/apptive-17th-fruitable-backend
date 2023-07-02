package apptive.fruitable.board.dto.post;

import apptive.fruitable.login.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Component
public class PostRequestDto {

    private Member userId;

    @NotBlank(message = "연락처를 입력해 주세요")
    private String contact;
    @NotNull(message = "과일/채소 여부를 알려주세요")
    private Integer vege;
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
    @NotNull(message = "가격을 입력해주세요")
    private Integer price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

}

