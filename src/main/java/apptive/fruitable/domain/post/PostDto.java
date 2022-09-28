package apptive.fruitable.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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

    //수정할 때 글 이미지 아이디를 저장하는 리스트
    private List<Long> postImgIdList = new ArrayList<>();

    //수정할 때 글 안의 이미지 정보를 저장하는 리스트
    private List<PostImgDto> postImgDtoList = new ArrayList<>();

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

    public Post toEntity(PostDto dto) {
        Post entity = Post.builder()
                .userId(dto.userId)
                .contact(dto.contact)
                .vege(dto.vege)
                .title(dto.title)
                .content(dto.content)
                .price(dto.price)
                .endDate(dto.endDate)
                .build();

        return entity;
    }

    public static PostDto of(Post entity) {
        PostDto dto = PostDto.builder()
                .userId(entity.getUserId())
                .contact(entity.getContact())
                .vege(entity.getVege())
                .title(entity.getTitle())
                .content(entity.getContent())
                .price(entity.getPrice())
                .endDate(entity.getEndDate())
                .build();

        return dto;
    }
}
