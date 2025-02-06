package Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Objects;

public class HBConnection {



    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        ArrayList<Student> students = new ArrayList<Student>();
        int temp = 1;
        while(true){
            if(!Objects.isNull(session.get(Student.class, temp))){
                students.add(session.get(Student.class, temp));
                temp++;
            }else{
                break;
            }

        }
        students.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();



        sessionFactory.close();
    }
}
