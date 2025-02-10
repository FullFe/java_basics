package Hibernate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Courses")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;



    @Enumerated(EnumType.STRING)
    private CourseType type;

    private String description;

    @ManyToOne (cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count")
    private int studentsCount;
    private int price;

    @Column(name = "price_per_hour")
    private int pricePerHour;

    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscriptions;

}
