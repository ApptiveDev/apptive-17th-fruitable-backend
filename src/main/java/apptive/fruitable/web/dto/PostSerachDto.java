package apptive.fruitable.web.dto;

import lombok.Data;

@Data
public class PostSerachDto {

    private String searchDataType;
    private String searchBy;
    private String searchQuery = "";
}
