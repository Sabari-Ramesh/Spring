package com.ex.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex.Beans.Writer;

public class MethodRunner {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("Method-arg.xml");
		Writer writer=(Writer) context.getBean("writer");
		writer.write();
	}
}
