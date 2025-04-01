package App.Dto;

import lombok.Data;

import java.time.Instant;


@Data
public class NewsDto {

    private long id;

    private String title;

    private String text;

    private Instant date;

    private String category;

}
