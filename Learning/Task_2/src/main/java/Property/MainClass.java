package Property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationClass.class);
        Writer writer = context.getBean(Writer.class);
        writer.write();
        System.out.println("Done!!!");
    }
}
