package com.ex.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.ec.Core.Beans.Writer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ec.core.Beans", "com.ex.Interface" })
public class MainMethod {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MainMethod.class, args);
		Writer writer = context.getBean(Writer.class);
		writer.write();
	}
}