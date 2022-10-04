package apptive.fruitable.dto;

import apptive.fruitable.domain.post.Post;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String userId;

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
    private LocalDateTime endDate;

    @Builder
    public PostDto(String userId, String contact, Integer vege, String title, String content, Integer price, LocalDateTime endDate) {
        this.userId = userId;
        this.contact = contact;
        this.vege = vege;
        this.title = title;
        this.content = content;
        this.price = price;
        this.endDate = endDate;
    }

    public Post toEntity() {
        Post entity = Post.builder()
                .userId(userId)
                .contact(contact)
                .vege(vege)
                .title(title)
                .content(content)
                .price(price)
                .endDate(endDate)
                .build();

        return entity;
    }
}
