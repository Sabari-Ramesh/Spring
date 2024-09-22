package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJpaApplication {
	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringJpaApplication.class, args);
		CrudOperation crudOperation = context.getBean(CrudOperation.class);
        crudOperation.exits(); 
	}
	  
}
