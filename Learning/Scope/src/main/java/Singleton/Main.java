package Singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
public static void main(String[] args) {
	ApplicationContext context=new AnnotationConfigApplicationContext(SingletonConfiguration.class);
	A a1=context.getBean(A.class);
	System.out.println("A class"+a1);
	a1.m1();
}
}
