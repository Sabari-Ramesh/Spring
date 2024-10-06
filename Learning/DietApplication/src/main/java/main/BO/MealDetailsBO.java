package main.BO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealDetailsRepository;
import main.DAO.MealSummary;
import main.DAO.UserRepository;
import main.entity.MealDetails;
import main.entity.MealInfo;
import main.entity.User;

import main.Exception.*;

@Component
public class MealDetailsBO {
	
	@Autowired
	private MealDetailsRepository mealDetailRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//2.Insert
	
	public MealDetails insertDetails(MealDetails mealDetail) throws DataIntegrityViolationException, UserNotFound, DateException, QuantityException, NameException, MealTypeException {
		
		validUser(mealDetail.getUser());
		validDate(mealDetail.getMealDate());
		validQuantity(mealDetail.getCalories(), " Calories ");
		validQuantity(mealDetail.getCarbs(), " CarboHydrates");
		validQuantity(mealDetail.getProtein(), " Protein");
		validQuantity(mealDetail.getQuantity(), " Quantity");
		validQuantity(mealDetail.getVitamins(), " Vitamins");
		validName(mealDetail.getFoodName(), " FoodName");
		validMealType(mealDetail.getMealType());
		
		MealDetails detail=mealDetailRepo.save(mealDetail);
		return detail;
		
		
	}
	
	//3. Find By Id

	public MealDetails fetchById(long id) {
		MealDetails detail=mealDetailRepo.findById(id).get();
		return detail;
	}
	
	//4.Find All

	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetail=mealDetailRepo.findAll();
		return mealDetail;
	}

	//5.Find By User Id( Custom Querry )
	
	public List<MealDetails> fetchByUserId(long id) {
		List<MealDetails> mealDetail=mealDetailRepo.findMealDetailsByUserId(id);
		return mealDetail;
	}
	
	//6.Find By min and max Quantity ( Named Querry )

	public List<MealDetails> findByQuantityRange(double min, double max) {
		List<MealDetails> mealDetail=mealDetailRepo.findByQuantityRange(min,max);
		return mealDetail;
	}
	
	//7.Custom querry with Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection=mealDetailRepo.findCustomMealDetails();
		return mealDetailProjection;
	}
	
	//8.Custom Query with Aggregate function
	
	public double findAvgCaloriesByDateRange(LocalDate startDate,LocalDate endDate) {
	Double avgCalorie = mealDetailRepo.findAvgCaloriesByDateRange(startDate, endDate);
	if (avgCalorie == null) {
		 return 0.0;
	}
	return avgCalorie;
	}
	
	//9.Named with Clauses

	public List<MealSummary> avgCaloriesAndTotalQuantity(double calorieThreshold) {
		 List<MealSummary> list = mealDetailRepo.avgCaloriesAndTotalQuantity(calorieThreshold); 
		    return list;
	}
	
	//Validation
	
	private void validUser(User user) throws UserNotFound,DataIntegrityViolationException {
		Optional<User> userOptional = userRepo.findById(user.getUserId());
		if (!userOptional.isPresent()) {
			throw new UserNotFound("ERROR : User does not exist");
		}
	}
	
	private void validDate(LocalDate mealDate) throws DateException {
		LocalDate currentDate = LocalDate.now();
		if (mealDate.isAfter(currentDate)) {
			throw new DateException("ERROR : In valid Date");
		}
	}
	
	private void validQuantity(double quantity, String string) throws QuantityException {
		if (quantity <= 0) {
			throw new QuantityException("ERRROR :In valid" + string);
		}
	}
	
	private void validName(String name, String msg) throws NameException {
		if (name == null) {
			throw new NameException("ERROR : Invalid " + msg);
		}
	}
	private void validMealType(MealInfo mealInfo) throws MealTypeException {
		int mealId=mealInfo.getMealType();
		if (mealId <= 0 || mealId >=5) {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
	}

	
}
