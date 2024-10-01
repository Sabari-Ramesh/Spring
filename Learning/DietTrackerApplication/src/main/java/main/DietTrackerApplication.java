package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.DTO.MealDetailsDTO;
import main.DTO.UsersDTO;
import main.Response.ResponseHandle;
import main.entity.MealDetails;
import main.entity.Users;
import main.service.MealDetailsService;

@SpringBootApplication
public class DietTrackerApplication {

	@Autowired
	private MealDetailsService mealDetailService;

	@Autowired
	private MealDetailsDTO mealDetailDto;

	@Autowired
	private UsersDTO userDto;

	@Autowired
	private ResponseHandle response;

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
					"\t\t\t1.insert\n\t\t\t2.Find By MealDetail Id\n\t\t\t3.Update\n\t\t\t4.DeleteByID\n\t\t\t5.Find MealDetails with User id\n\t\t\t6.Association User With MealDetails\n\t\t\t7.exit");
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
				break;
			}
			case 5: {
				application.findMealDetailsByUserId();
				break;
			}
			case 6: {
				 application.associationUserWithMealDetails();
				break;
			}
			case 7: {
				flag = false;
				break;
			}
			default: {
				System.out.println("Enter the Valid Option..");
			}

			}// case

		}

	}


	// INSERT

	private void insert() {
       
		MealDetails mealDetail=new MealDetails();
		Users user=new Users();
		
		System.out.println("Enter User id :");
		user.setId(sc.nextLong());
		mealDetail.setUser(user);

		System.out.println("Enter the Meal  Type :");
		System.out.println("1.BreakFast\n2.Lunch\n3.Dinner\n4.Snacks");
		mealDetail.setMealType(sc.nextInt());

		System.out.println("Enter The Date :");

		String dateString = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(dateString, formatter);

		mealDetail.setMealDate(localDate);

		System.out.println("Enter the Food Name :");
		sc.nextLine();
		mealDetail.setFoodName(sc.nextLine());

		System.out.println("Enter the Quantity :");
		mealDetail.setQuantity(sc.nextDouble());

		System.out.println("Enter the Protein :");
		mealDetail.setProtein(sc.nextDouble());

		System.out.println("Enter the Vitamin :");
		mealDetail.setVitamins(sc.nextDouble());

		System.out.println("Enter the Calories :");
		mealDetail.setCalories(sc.nextDouble());

		System.out.println("Enter CarboHydrate :");
		mealDetail.setCarboHydrate(sc.nextDouble());

		response = mealDetailService.insertMealDetail(mealDetail);

		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage() + " for User Id " + response.getId());
		}

	}

	// Find By Meal Id

	private void findByMealId() {
	
		MealDetails mealDetail=new MealDetails();
		
		System.out.println("Enter Meal Id :");
		long id = sc.nextLong();

		mealDetail.setId(id);

		response = mealDetailService.findByMealId(mealDetail);

		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage() + " for User Id " + id);
			System.out.println(response.getMealDetail().toString());
		}

	}

	// Update

	private void update() {

		MealDetails mealDetail=new MealDetails();
		
		System.out.println("Enter the Meal Id to Update :");
		long id = sc.nextLong();
		mealDetail.setId(id);
		System.out.println("Enter Food Name To Update :");
		sc.nextLine();
		String foodName = sc.nextLine();
		mealDetail.setFoodName(foodName);
		response = mealDetailService.updateMealDetail(mealDetail);
		
		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage() + " for User Id " + id);
		}

	}

	// Delete

	private void delete() {
		
		MealDetails mealDetail=new MealDetails();
		
		System.out.println("Enter the id To Delete :");
		long id = sc.nextLong();
		mealDetail.setId(id);

		response = mealDetailService.deleteId(mealDetail);
		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage() + " for User Id " + id);
		}

	}

	// Custom Querry
	
	private void findMealDetailsByUserId() {	
		System.out.println("Enter the User id :");
		long id=sc.nextLong();
		response=mealDetailService.findMealDetailsByUserId(id);
		if (response.getSucessmessage() != null) {
			System.out.println(response.getSucessmessage() + " for User Id " + id);
		}
	}
	
	//Association User with mealDetail
	private void associationUserWithMealDetails() {
		
		Users user=new Users();
		
		System.out.println("Enter User Name :");
		String name=sc.nextLine();
		user.setName(name);
		
		System.out.println("Enter the email :");
		String email=sc.next();
		user.setEmail(email);
		
		System.out.println("Enter the password :");
		String password=sc.next();
		user.setPassword(password);
		
		
		System.out.println("Enter Date of Birth :");
		String dateString = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		user.setDateOfBirth(localDate);
		
		
		System.out.println("Enter Mobile Number :");
		long mobile=sc.nextLong();
		user.setMobileNumber(mobile);
		
		System.out.println("Enter the city :");
		int city=sc.nextInt();
		user.setCity(city);
		
		System.out.println("Enter Number of Meal Details :");
		int n=sc.nextInt();
		
		List<MealDetails> list=new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			
			MealDetails mealDetail=new MealDetails();
			
			System.out.println("Enter the Meal  Type :");
			System.out.println("1.BreakFast\n2.Lunch\n3.Dinner\n4.Snacks");
			mealDetail.setMealType(sc.nextInt());

			System.out.println("Enter The Date :");

			String date = sc.next();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			localDate = LocalDate.parse(date, format);
			mealDetail.setMealDate(localDate);

			System.out.println("Enter the Food Name :");
			sc.nextLine();
			mealDetail.setFoodName(sc.nextLine());

			System.out.println("Enter the Quantity :");
			mealDetail.setQuantity(sc.nextDouble());

			System.out.println("Enter the Protein :");
			mealDetail.setProtein(sc.nextDouble());

			System.out.println("Enter the Vitamin :");
			mealDetail.setVitamins(sc.nextDouble());

			System.out.println("Enter the Calories :");
			mealDetail.setCalories(sc.nextDouble());

			System.out.println("Enter CarboHydrate :");
			mealDetail.setCarboHydrate(sc.nextDouble());
			
			mealDetail.setUser(user);
			
			list.add(mealDetail);
			
		}
		
		user.setMealDetails(list);		
		response=mealDetailService.associationUserWithMealDetails(user);
		
		long id=response.getId();
		if(id>0) {
			System.out.println(response.getSucessmessage()+" "+id);
		}else {
			System.out.println(response.getFailuremessage());
		}
		
	}

}
