package OneTOOne_Bidirection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(Main.class, args);
		
		OrderService or=ctx.getBean(OrderService.class);
		or.CreatedOrderitesManually();
	}
}
