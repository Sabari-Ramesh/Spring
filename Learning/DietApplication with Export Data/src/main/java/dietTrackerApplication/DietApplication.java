package dietTrackerApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import dietTrackerApplication.DTO.MealDetailDTO;
import dietTrackerApplication.client.MealDetailsClient;
import dietTrackerApplication.service.UserService;

@SpringBootApplication
@EnableJpaAuditing
public class DietApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private MealDetailsClient client;

	Scanner sc = new Scanner(System.in);

	static Logger log = Logger.getLogger(DietApplication.class);

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DietApplication.class, args);
		PropertyConfigurator
				.configure("D:\\Spring-workspace-2\\DietApplication\\src\\main\\java\\log4j\\log4j.properties");
		log.info("Application Started");

		DietApplication application = context.getBean(DietApplication.class);

		// Additional Task 4
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {

			System.out.println("\t\t\tMenu :\n\t\t\t1.addDetails\n\t\t\t2.FindAll\n\t\t\t3.Exit");
			System.out.println("Enter you option :");
			int option = sc.nextInt();

			switch (option) {
			case 1: {
				application.addDetails();
				break;
			} // case 1
			case 2: {
				application.findAll();
				break;
			}
			case 3: {
				flag = false;
				break;
			}
			default: {
				System.out.println("Enter the Valid Option");
			}
			}// switch

		}
	}

	// Additional Task 4 -->add
	
	private void addDetails() {
		MealDetailDTO mealDetail = new MealDetailDTO();

		System.out.println("Enter the MealType:");
		mealDetail.setMeal(sc.next());

		System.out.println("Enter the Date (yyyy-MM-dd):");
		String date = sc.next();
		LocalDate mealDate = LocalDate.parse(date);
		mealDetail.setMealDate(mealDate);

		System.out.println("Enter the userId:");
		mealDetail.setUserid(sc.nextLong());
		sc.nextLine(); // Clear the newline character left by nextLong()

		System.out.println("Enter the Food Name:");
		mealDetail.setFoodName(sc.nextLine());

		System.out.println("Enter the Quantity:");
		mealDetail.setQuantity(sc.nextDouble());

		System.out.println("Enter the Calories:");
		mealDetail.setCalories(sc.nextDouble());

		System.out.println("Enter the Protein:");
		mealDetail.setProtein(sc.nextDouble());

		System.out.println("Enter the Carbohydrate:");
		mealDetail.setCarbs(sc.nextDouble());

		System.out.println("Enter the Vitamins:");
		mealDetail.setVitamins(sc.nextDouble());

		// Invoke the addMeal method
		String msg = client.addMeal(mealDetail);
		System.out.println(msg);
	}

	private void findAll() {
		
		 System.out.println("Fetching all meal details...");
	        List<MealDetailDTO> mealDetailsList = client.getAllMeals();

	        if (mealDetailsList != null && !mealDetailsList.isEmpty()) {
	            mealDetailsList.forEach(mealDetail -> System.out.println(mealDetail));
	        } else {
	            System.out.println("No meal details found.");
	        }
		
	}

}
