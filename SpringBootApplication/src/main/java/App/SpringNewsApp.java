package App;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringNewsApp {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringNewsApp.class, args);
    }
}
