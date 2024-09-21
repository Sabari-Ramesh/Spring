package QualifierAnnotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("Honda")
public class Honda implements Car{

	@Override
	public void Start() {
		System.out.println("Honda Started...");
	}

}
