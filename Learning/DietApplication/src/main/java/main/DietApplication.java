package main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.entity.MealDetails;
import main.service.MealDetailService;

@SpringBootApplication
public class DietApplication {
    @Autowired
    private MealDetailService mealDetailService;



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApplicationContext context = SpringApplication.run(DietApplication.class, args);
        
        DietApplication application = context.getBean(DietApplication.class);
        application.fetchByUserId();        
  
    }

  

	private void fetchByUserId() {
		List<MealDetails> mealDetail=mealDetailService.findByQuantityRange(10.5,500.4);
		System.out.println(mealDetail);
	}

    // fetchbyid
   


}
