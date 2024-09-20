package com.ex.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex.configure.SpringConfig;
import com.ex.core.Beans.Writer;

public class JavaConfigRunner {
	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
		Writer writer=(Writer) context.getBean("writer");
		writer.write();
		System.out.println("Done!!!");
	}
}
