package QualifierAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CarBO {

	private Car car;

    //Setter Injection
	@Autowired
	//@Qualifier("Honda")
	@Qualifier("Tata")
	public void setCar(Car car) {
		this.car = car;
	}
	
	public void printCar() {
		car.Start();
	}
	
}
