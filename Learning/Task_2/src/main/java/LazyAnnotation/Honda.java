package LazyAnnotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Honda implements Car{

	@Override
	public void Start() {
		System.out.println("Honda Car Started....");
	}
	

}
