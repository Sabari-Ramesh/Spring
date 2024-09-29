package main;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.entity.MealDetails;
import main.entity.Users;
import main.service.MealDetailsService;

@SpringBootApplication
public class DietTrackerApplication {

	@Autowired
	private MealDetailsService mealDetailService;

	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DietTrackerApplication.class, args);
		DietTrackerApplication application = context.getBean(DietTrackerApplication.class);

		Scanner sc = new Scanner(System.in);

		boolean flag = true;
		System.out.println("/t/t------DietTracker Application------");

		while (flag) {
			System.out.println("\t\t\t\tMenu");
			System.out.println(
					"\t\t\t1.insert\n\t\t\t2.Find By MealDetail Id\n\t\t\t3.Update\n\t\t\t4.DeleteByID\n\t\t\t5.Association MealDetails with User\n\t\t\t6.exit");
			System.out.println("Enter your Option");
			int option = sc.nextInt();

			switch (option) {

			case 1: {
				application.insert();
				break;
			}
			case 2: {
				application.findByMealId();
				break;
			}
			case 3: {
				application.update();
				break;
			}
			case 4: {
				application.delete();
				System.out.println("kk");
				break;
			}
			case 5:{
				application.MealDetailsWithUsers();
				break;
			}
			case 6: {
				flag = false;
				break;
			}
			default: {
				System.out.println("Enter the Valid Option..");
			}

			}// case

		}

	}


//INSERT 

	private void insert() {

		MealDetails mealDetail = new MealDetails();
		Users user = new Users();

		System.out.println("Enter the Meal Type");
		System.out.println("1. Breakfast\n2. Lunch\n3. Dinner\n4. Snacks");
		int option = sc.nextInt();
		if (option >= 1 && option <= 4) {
			mealDetail.setMealType(option);
		} else {
			System.out.println("Invalid meal type, please try again.");
			return;
		}

		System.out.print("Enter Meal Date (YYYY-MM-DD): ");
		mealDetail.setMealDate(sc.next());

		System.out.print("Enter User Id: ");
		user.setId(sc.nextLong());
		mealDetail.setUser(user);

		System.out.print("Enter Food Name: ");
		sc.nextLine();
		mealDetail.setFoodName(sc.nextLine());

		System.out.print("Enter Quantity: ");
		mealDetail.setQuantity(sc.nextDouble());

		System.out.print("Enter Calories: ");
		mealDetail.setCalories(sc.nextDouble());

		System.out.print("Enter Protein: ");
		mealDetail.setProtein(sc.nextDouble());

		System.out.print("Enter Carbohydrate: ");
		mealDetail.setCarboHydrate(sc.nextDouble());

		System.out.print("Enter the Vitamins: ");
		mealDetail.setVitamins(sc.nextDouble());

		mealDetailService.insertMealDetail(mealDetail);
		System.out.println("Meal details inserted successfully!");

		System.out.println("---------------------------------------------------------------------");

	}

//Find By Meal Id

	private void findByMealId() {
		System.out.println("Enter the Meal Id :");
		long id = sc.nextLong();
		mealDetailService.findByMealId(id);
		System.out.println("Sucessfully Fetched");

	}

//Update

	private void update() {
		System.out.println("Enter the Meal Id to Update :");
		long id = sc.nextLong();
		mealDetailService.update(id);
		System.out.println("Sucessfully Updated..");
	}

//Delete	

	private void delete() {
		System.out.println("Enter the Id to Delete :");
		long id=sc.nextLong();
		mealDetailService.delete(id);
		System.out.println("Sucessfully Deleted...");
	}

//MealDetails With User	
	
	private void MealDetailsWithUsers() {
	    Users user = new Users();
	    
	    System.out.println("Enter Name:");
	    user.setName(sc.nextLine());
	    System.out.println("Enter email:");
	    user.setEmail(sc.nextLine());
	    System.out.println("Enter Password:");
	    user.setPassword(sc.nextLine());
	    
	    System.out.println("1. Male\n2. Female\n3. Others");
	    System.out.println("Enter Gender:");
	    int option = sc.nextInt();
	    if (option >= 1 && option <= 3) {
	        user.setGender(option);
	    }
	    
	    System.out.println("Enter The Date Of Birth:");
	    user.setDateOfBirth(sc.next());
	    
	    System.out.println("Enter Mobile Number:");
	    user.setMobileNumber(sc.nextLong());
	    
	    System.out.println("Enter City:");
	    user.setCity(sc.nextInt());
	    
	    
	    user.setMealDetails(new ArrayList<>()); 

	    System.out.println("Enter the Number of Meal Details Association with User:");
	    int n = sc.nextInt();
	    sc.nextLine(); 

	    for (int i = 0; i < n; i++) {
	        MealDetails mealDetail = new MealDetails();

	        System.out.println("Enter the Meal Type");
	        System.out.println("1. Breakfast\n2. Lunch\n3. Dinner\n4. Snacks");
	        int opt = sc.nextInt();
	        if (opt >= 1 && opt <= 4) {
	            mealDetail.setMealType(opt);
	        } else {
	            System.out.println("Invalid meal type, please try again.");
	            return;
	        }

	        System.out.print("Enter Meal Date (YYYY-MM-DD): ");
	        mealDetail.setMealDate(sc.next());
	        
	        System.out.print("Enter Food Name: ");
	        sc.nextLine();
	        mealDetail.setFoodName(sc.nextLine());
	        
	        System.out.print("Enter Quantity: ");
	        mealDetail.setQuantity(sc.nextDouble());

	        System.out.print("Enter Calories: ");
	        mealDetail.setCalories(sc.nextDouble());

	        System.out.print("Enter Protein: ");
	        mealDetail.setProtein(sc.nextDouble());

	        System.out.print("Enter Carbohydrate: ");
	        mealDetail.setCarboHydrate(sc.nextDouble());

	        System.out.print("Enter the Vitamins: ");
	        mealDetail.setVitamins(sc.nextDouble());

	       
	        mealDetail.setUser(user); 
	        user.getMealDetails().add(mealDetail); 
	    } // for Loop

	   
	    mealDetailService.MealDetailsWithUsers(user);
	}
	

}
