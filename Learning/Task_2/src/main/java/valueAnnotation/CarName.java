package valueAnnotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CarName implements Car{
	
	@Value("Jaguar ")
	private String carname;
	@Override
	public void start() {
		System.out.println(carname +"Car is Started....");
	}

}
