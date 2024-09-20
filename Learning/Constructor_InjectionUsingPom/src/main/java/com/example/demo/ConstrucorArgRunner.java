package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.core.beans.Writer;

public class ConstrucorArgRunner {
public static void main(String[] args) {
	ApplicationContext context=new ClassPathXmlApplicationContext("constructor-arg.xml");
	
	Writer writer=(Writer) context.getBean("writer");
	writer.write();
}
}
