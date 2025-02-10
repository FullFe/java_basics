package Hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name="Subscriptions")
@ToString
@Getter
@Setter
public class Subscription {


    @EmbeddedId
    private SubscriptionId id;


    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;


    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
