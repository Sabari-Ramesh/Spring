package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.client.MealClient;
import main.entity.MealDetails;

@SpringBootApplication
public class Task41Application {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(Task41Application.class, args);
		Scanner sc=new Scanner(System.in);
		Task41Application application=context.getBean(Task41Application.class);
		boolean flag=true;
		while(flag) {
			System.out.println("\t\t\tMENU :\n1.add\n2.findAll\n3.exit :");
			System.out.println("Enter your Choice :");
			int option=sc.nextInt();
			switch(option) {
			case 1:{
				 application.add();
				 break;
			}case 2:{
				application.findAll();
				break;
			}
			case 3:{
				flag=false;
				break;
			}default:{
				System.out.println("Enter the Valid Option :");
			}
			}
		}
		
	}

	private void findAll() {
		MealClient mealClient = new MealClient();
        // Retrieve all meals
        List<MealDetails> allMeals = mealClient.getAllMeals();
        System.out.println("All Meals: ");
        allMeals.forEach(System.out::println);
	}

	//ADD
	
	private void add() {
		
		MealClient mealClient = new MealClient();

        // Add a new meal
        MealDetails newMeal = new MealDetails();
        newMeal.setMealType("Lunch");
        newMeal.setMealDate(LocalDate.now());
        newMeal.setFoodName("Oatmeal");
        newMeal.setQuantity(1.5);
        newMeal.setCalories(150);
        newMeal.setProtein(5);
        newMeal.setCarbs(30);
        newMeal.setVitamins(1.5);

        MealDetails addedMeal = mealClient.addMeal(newMeal);
        System.out.println("Added Meal: " + addedMeal);

	}

}
