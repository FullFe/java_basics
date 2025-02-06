package Hibernate;

import jakarta.persistence.*;
@Entity
@Table(name="LinkedPurchasedList")
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
