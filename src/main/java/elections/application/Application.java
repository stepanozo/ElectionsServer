package elections.application;

import elections.frames.LogInFrame;
import javax.swing.SwingUtilities;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

//@SpringBootApplication(scanBasePackages = "elections")
//public class Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//
//}

@SpringBootApplication(scanBasePackages = "elections")
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        ApplicationContext contexto = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.SERVLET)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            LogInFrame frame = new LogInFrame();
            frame.setVisible(true);
        });
    }
}
