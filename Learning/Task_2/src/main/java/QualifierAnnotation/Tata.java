package QualifierAnnotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Tata")
public class Tata implements Car{

	@Override
	public void Start() {
		System.out.println("Tata Started....");
	}

}
