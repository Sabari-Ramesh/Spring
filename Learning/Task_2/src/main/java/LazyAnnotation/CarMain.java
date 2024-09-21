package LazyAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarMain {
	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(CarMain.class, args);
		CarBo carbo=context.getBean(CarBo.class);
		carbo.printCar();
	}
}
