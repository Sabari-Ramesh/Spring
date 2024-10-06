package main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.DAO.MealSummary;
import main.service.MealDetailService;

@SpringBootApplication
public class DietApplication {
    @Autowired
    private MealDetailService mealDetailService;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationContext context = SpringApplication.run(DietApplication.class, args);
        
        DietApplication application = context.getBean(DietApplication.class);
        application.avgCaloriesAndTotalQuantity();        
  
    }



	private void avgCaloriesAndTotalQuantity() {
		
		List<MealSummary> list=mealDetailService.avgCaloriesAndTotalQuantity(100.5);
		System.out.println(list);
	}

  

    // fetchbyid
   


}
