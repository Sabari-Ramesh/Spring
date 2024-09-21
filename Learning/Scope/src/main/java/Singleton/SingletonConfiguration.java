package Singleton;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SingletonConfiguration {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public A a(B b) {
		return new A(b);
	}
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public B b() {
		return new B();
	}
}
