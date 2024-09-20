package Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex.BeanCore.Writer;
import com.ex.Configure.SpringConfig;

public class JavaConfigRunner {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		Writer writer = (Writer) context.getBean("writer");
		writer.write();
	}
}
