package A_prototype_B_Singleton;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class ConfigurationClass {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public A a(B b) {
		return new A(b);
	}
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public B b() {
		return new B();
	}
}
