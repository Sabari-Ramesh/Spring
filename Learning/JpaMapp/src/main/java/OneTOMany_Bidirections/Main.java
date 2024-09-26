package OneTOMany_Bidirections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Main.class, args);
		OrderService ordservice = ctx.getBean(OrderService.class);
		ordservice.insertOrderItems();

	}
}
