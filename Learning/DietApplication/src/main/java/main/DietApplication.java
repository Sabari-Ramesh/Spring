package main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.entity.MealDetails;
import main.service.MealDetailService;

@SpringBootApplication
public class DietApplication {

	
	static Logger log=Logger.getLogger(DietApplication.class);
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DietApplication.class, args);        
    	PropertyConfigurator.configure("D:\\Spring-workspace-2\\DietApplication\\src\\main\\java\\log4j\\log4j.properties");
    	log.info("Application Started");
    }

}
