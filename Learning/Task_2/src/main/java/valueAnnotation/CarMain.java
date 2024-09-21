package valueAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarMain {
public static void main(String[] args) {
	 ApplicationContext context = SpringApplication.run(CarMain.class, args);
    CarName carName = context.getBean(CarName.class);
    carName.start();
}
}
