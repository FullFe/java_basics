package jdbc;

import java.util.Date;

public class Subscription implements Comparable{
    private int id;
    private String name;
    private Date date;
    private int student_id;



    Subscription(int id, String name, Date date, int student_id) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.student_id = student_id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", student_id=" + student_id +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.getDate().compareTo(((Subscription) o).getDate());
    }
}
