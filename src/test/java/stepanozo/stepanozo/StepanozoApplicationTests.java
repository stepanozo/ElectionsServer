package stepanozo.stepanozo;

import elections.application.Application;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//Тестовый комментарий
//Тестовый класс
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StepanozoApplicationTests {

    @BeforeClass
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "false");
    }

    @Test
    public void someTest() { }
}
