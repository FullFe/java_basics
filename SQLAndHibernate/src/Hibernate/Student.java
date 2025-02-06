package Hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Students")
@ToString
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    @Column(name = "registration_date")
    private Date registrationDate;

    @OneToMany(mappedBy = "student")
    private Set<Subscription> subscriptions;

}
