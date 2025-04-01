package App.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import java.time.Instant;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name="news")
public class NewsEntity {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="text")
    private String text;

    @CreationTimestamp
    @Column(name="creation_time")
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", referencedColumnName = "category_name")
    @Nullable
    private CategoryEntity category;

    public NewsEntity(long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = Instant.now();
    }
}
