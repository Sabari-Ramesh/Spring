package com.ex.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.ex.cor.beans.Writer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ex.cor.Beans", "com.ex.Interface"})

public class main {
public static void main(String[] args) {
	ApplicationContext context=SpringApplication.run(main.class, args);
	Writer writer=context.getBean(Writer.class);
	writer.write();
}
}
