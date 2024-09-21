package QualifierAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainClass {
public static void main(String[] args) {
	ApplicationContext context=SpringApplication.run(MainClass.class, args);
	CarBO carBo=context.getBean(CarBO.class);
	carBo.printCar();
}
}
