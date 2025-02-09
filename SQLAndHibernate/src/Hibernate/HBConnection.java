package Hibernate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;

public class HBConnection {

    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("resources/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        List<PurchaseList> newPurchases = getListEntity(sessionFactory, new PurchaseList());

        Map<Integer, List<String>> idPlusCourses = getIdplusCourses(sessionFactory, newPurchases);
        Map<Integer, List<Integer>> idNIds = getIdNIds(sessionFactory,idPlusCourses);

        addDataLPL(sessionFactory, idNIds);

        sessionFactory.close();
    }

    public static <T> List<T> getListEntity(SessionFactory sessionFactory, T obj){
        String sqlQuerry = "from ";
        sqlQuerry += obj.getClass().getSimpleName();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        List entityList = session.createQuery(sqlQuerry, obj.getClass()).list();
        session.getTransaction().commit();
        session.close();
        return entityList;
    }

    public static Map<Integer, List<String>> getIdplusCourses(SessionFactory sessionFactory,  List<PurchaseList> newPurchases){
    /*
    Мапа с соотношением айди, название курса.
     */
        Map<Integer, List<String>> idNCourses = new HashMap<>();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        for (PurchaseList purchase : newPurchases) {
            try{
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
                Root<Student> root = criteria.from(Student.class);

                criteria.select(root).where(builder.equal(root.get("name"), purchase.getStudentName())); // Сюда пишется название поля в классе а не в таблице
                session.createQuery(criteria).getResultList().forEach(core -> {
                    if(!idNCourses.containsKey(core.getId())) {
                        List<String> temp = new ArrayList<>();
                        temp.add(purchase.getCourseName());
                        idNCourses.put(core.getId(), temp);
                    }else{
                        List<String> temp = idNCourses.get(core.getId());
                        temp.add(purchase.getCourseName());
                        idNCourses.put(core.getId(), temp);
                    }
                });
            }catch(HibernateException e){
                System.out.println("Нет такого студента");
                e.printStackTrace();
            }
        }
        session.getTransaction().commit();
        session.close();
        return idNCourses;
    }

    public static Map<Integer, List<Integer>> getIdNIds(SessionFactory sessionFactory,Map<Integer, List<String>> idPlusCourses){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Map<Integer, List<Integer>> idNIds = new HashMap<>();
        for (Map.Entry<Integer, List<String>> entry : idPlusCourses.entrySet()){
            List<String> listNames = entry.getValue();
            List<Integer> ids = new ArrayList<>();
            try{
                for (String courseName : listNames){
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<Course> criteria = builder.createQuery(Course.class);
                    Root<Course> root = criteria.from(Course.class);
                    criteria.select(root).where(builder.equal(root.get("name"), courseName)); // Сюда пишется название поля в классе а не в таблице
                    session.createQuery(criteria).getResultList().forEach(core -> {
                        ids.add(core.getId());
                    });
                }
                idNIds.put(entry.getKey(), ids);
            }
            catch(HibernateException e){
                e.printStackTrace();
            }
        }
        session.getTransaction().commit();
         return idNIds;
    }

    public static void addDataLPL(SessionFactory sessionFactory, Map<Integer, List<Integer>> purchaseList){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        for(Map.Entry<Integer, List<Integer>> entry : purchaseList.entrySet()){

            List<Integer> courseId = entry.getValue();

            for(int id : courseId){

                LinkedPurchasedList newEntry = new LinkedPurchasedList();

                Student student = session.get(Student.class, entry.getKey());
                Course course = session.get(Course.class, id);
                newEntry.setStudent(student);
                newEntry.setCourse(course);
                newEntry.setLplKey(new LPLKey(student.getId(), course.getId()));
                if(session.get(LinkedPurchasedList.class, new LPLKey(entry.getKey(), id)) == null){
                    session.save(newEntry);
                    //Нужен счетчик для curses
                    int countStudent = course.getStudentsCount();
                    course.setStudentsCount(countStudent+1);
                    session.update(course);
                }

            }

        }
        session.getTransaction().commit();
        session.close();
    }
}
