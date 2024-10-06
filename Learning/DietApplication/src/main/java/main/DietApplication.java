package main;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.service.MealDetailService;

@SpringBootApplication
public class DietApplication {
    @Autowired
    private MealDetailService mealDetailService;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationContext context = SpringApplication.run(DietApplication.class, args);
        
        DietApplication application = context.getBean(DietApplication.class);
        application.findAvgCaloriesByDateRange();        
  
    }



	private void findAvgCaloriesByDateRange() {
		 LocalDate startDate = LocalDate.parse("2020-01-10");
	     LocalDate endDate = LocalDate.parse("2024-10-05");
		double avgCalorie=mealDetailService.findAvgCaloriesByDateRange(startDate,endDate);
		System.out.println(avgCalorie);
	}

  

    // fetchbyid
   


}
