package com.ex.Demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary  
public class Honda implements Car {

    @Override
    public void start() {
        System.out.println("Honda is starting...");
    }
    
}
