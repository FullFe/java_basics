package App;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Setter
@Getter
public class NewsEntity {

    private long id;
    private String title;
    private String text;
    private Instant date;

    public NewsEntity(long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = Instant.now();
    }
}
