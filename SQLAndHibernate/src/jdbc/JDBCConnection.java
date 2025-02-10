package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnection {
    public static void main(String[] args) {
        testJDBC();
    }

    public static void testJDBC() {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String password = "root10@2#";
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_id, courses.name, subscriptions.subscription_date, subscriptions.student_id FROM courses JOIN subscriptions ON courses.id = subscriptions.course_id;");
            while(resultSet.next()){
                subscriptions.add(new Subscription(resultSet.getInt("course_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("subscription_date"),
                        resultSet.getInt("student_id")));

            }

            System.out.println(subscriptions.size());
            subscriptions.forEach(System.out::println);

            List<Subscription> tempSubscriptions = new ArrayList<>();
            StringBuilder result = new StringBuilder();
            int count = 1;
            for (int i= 0; i < subscriptions.size(); i++) {
                tempSubscriptions.add(subscriptions.get(i));
                 if (count != subscriptions.get(i).getId() || i == subscriptions.size()-1){

                    tempSubscriptions = tempSubscriptions.stream().sorted().toList();
                    result.append(tempSubscriptions.get(0).getName());
                    result.append(" - Продано: ");
                    result.append(tempSubscriptions.size());
                    result.append(" - Среднее значение продаж: ");
                    int diff = tempSubscriptions.get(tempSubscriptions.size()-1).getDate().getMonth() - tempSubscriptions.get(0).getDate().getMonth();

                    result.append(String.format("%.2f",(double) tempSubscriptions.size()/diff));
                    result.append(" За количество месяцев = ");

                    result.append(diff);
                    result.append("\n\n");
                    count++;


                    System.out.println(tempSubscriptions.get(0));
                    System.out.println(tempSubscriptions.get(tempSubscriptions.size()-1));
                    tempSubscriptions = new ArrayList<>();
                    tempSubscriptions.add(subscriptions.get(i));
                }

            }



            System.out.println(result);
            resultSet.close();
            statement.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

}
