package LazyAnnotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class Tata implements Car{

	@Override
	public void Start() {
		System.out.println("Tata Car Started....");
	}

}
