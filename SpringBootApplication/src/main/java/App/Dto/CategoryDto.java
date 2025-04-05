package App.Dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDto {
    private long id;
    private String categoryName;
    private List<NewsDto> news = new ArrayList<>();

}
