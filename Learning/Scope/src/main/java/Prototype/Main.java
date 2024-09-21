package Prototype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(ConfigurationClass.class);
		A a1=context.getBean(A.class);
		System.out.println("A class"+a1);
		A a2=context.getBean(A.class);
		System.out.println("A class"+a2);
		A a3=context.getBean(A.class);
		System.out.println("A class"+a3);
		a1.m1();
		a2.m1();
		a3.m1();
	}
}
