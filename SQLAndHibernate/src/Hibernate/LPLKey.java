package Hibernate;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class LPLKey implements Serializable {
    private int studentId;
    private int courseId;

    public LPLKey(){}
    public LPLKey(int studentId, int courseId){
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
