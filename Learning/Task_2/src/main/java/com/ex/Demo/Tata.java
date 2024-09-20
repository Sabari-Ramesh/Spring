package com.ex.Demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component  
@Primary
public class Tata implements Car {

    @Override
    public void start() {
        System.out.println("Tata is starting...");
    }
}
