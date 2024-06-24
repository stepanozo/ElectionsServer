package elections.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "elections")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
