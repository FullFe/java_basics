package Hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="LinkedPurchasedList")
@Setter
@Getter
public class LinkedPurchasedList {

    @EmbeddedId
    private LPLKey lplKey;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

}
