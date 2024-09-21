package LazyAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarBo {
	private Car car;

	// Setter Injection
	@Autowired
	public void setCar(Car car) {
		this.car = car;
	}

	// PrintCar
	public void printCar() {
		car.Start();
	}
}
