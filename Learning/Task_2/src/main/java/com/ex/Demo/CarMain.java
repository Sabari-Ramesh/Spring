package com.ex.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarMain {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(CarMain.class, args);
        CarBo carbo = ctx.getBean(CarBo.class);
        carbo.printCarStart();  
    }
}
