package main.Bo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import main.DAO.MealDetailsProjection;
import main.DAO.MealDetailsRepository;
import main.DAO.MealSummary;
import main.DAO.UserRepo;
import main.entity.MealDetails;
import main.entity.Users;

import main.Exceptions.*;


@Component
public class MealDetailsBo {

	@Autowired
	private MealDetailsRepository mealDetailRepo;
	
	@Autowired
	private UserRepo userRepo;

	// Insert	
	
	
	public MealDetails insertMealDetails(MealDetails mealDetail) throws UserNotFound,DataIntegrityViolationException, DateException, QuantityException, FoodNameException, MealTypeException{
		
		validUser(mealDetail.getUser());
		validDate(mealDetail.getMealDate());
		validQuantity(mealDetail.getCalories()," Calories ");
		validQuantity(mealDetail.getCarboHydrate()," CarboHydrates");
		validQuantity(mealDetail.getProtein()," Protein");
		validQuantity(mealDetail.getQuantity()," Quantity");
		validQuantity(mealDetail.getVitamins()," Vitamins");
		validFoodName(mealDetail.getFoodName());
		validMealType(mealDetail.getMealType());
		
	    return mealDetailRepo.save(mealDetail);
	    
	}


	// Find BY Id

	public MealDetails findByMealId(MealDetails mealDetail) {
		long id = mealDetail.getId();
		MealDetails fetchedDetail = mealDetailRepo.findById(id).get();
		return fetchedDetail;
	}

	// Update

	
	public MealDetails updateMealDetail(MealDetails mealDetail) {
		long id=mealDetail.getId();
		MealDetails updateDetail=mealDetailRepo.findById(id).get();
		updateDetail.setFoodName(mealDetail.getFoodName());
		updateDetail=mealDetailRepo.save(updateDetail);
		return updateDetail;
	}

	//Delete
	
	public boolean deleteId(MealDetails mealDetail) {
		
		System.out.println("Bo "+mealDetail.getId());
		long id=mealDetail.getId();
		mealDetailRepo.deleteById(id);
		return true;
		
	}

	//Custom Querry Find By UserID :
	
	public List<MealDetails> findMealDetailsByUserId(long id) {
		List<MealDetails> list=mealDetailRepo.findMealDetailsByUserId(id);
		return list;
	}

	//User Association
	
	public Users associationUserWithMealDetails(Users user) {
	Users insertedUser=userRepo.save(user);	
	return insertedUser;
	
	}


   //Fetch ALl
	
	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetails=mealDetailRepo.findAll();
		return mealDetails;
	}


	//find Custom MealDetails

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetail=mealDetailRepo.findCustomMealDetails();
		return mealDetail;
	}

    //Named Querry with Aggregate

	public double avgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) {
		Double avgCalorie=mealDetailRepo.findAvgCaloriesByDateRange(startDate,endDate);
		System.out.println("Double "+avgCalorie);
		return avgCalorie;
	}

    //Named Querry with Clauses

	public List<MealSummary> findAvgCaloriesAndTotalQuantity(double calorie) {
		List<MealSummary> mealSummary=mealDetailRepo.findAvgCaloriesAndTotalQuantity(calorie);
		return mealSummary;
		
	}

	
	// Validation

	private void validUser(Users users) throws UserNotFound,DataIntegrityViolationException  {
		  Optional<Users> userOptional = userRepo.findById(users.getId());
		    if (!userOptional.isPresent()) {
		        throw new UserNotFound("ERROR : User does not exist");
		    }
		    Users user = userOptional.get();
	}
	
	private void validDate(LocalDate mealDate) throws DateException {
		 LocalDate currentDate = LocalDate.now();
		    if (mealDate.isAfter(currentDate)) {
		        throw new DateException("ERROR : In valid Date");
		    }
	}
	
	private void validQuantity(double quantity, String string) throws QuantityException {
		if(quantity<=0) {
			throw new QuantityException("ERRROR :In valid"+string);
		}
	}
	

	private void validFoodName(String foodName) throws FoodNameException {
		if(foodName==null) {
			throw new FoodNameException("ERROR : Invalid Food name");
		}
	}

	private void validMealType(int mealType) throws MealTypeException {
		if(mealType<=0 || mealType>4) {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
	}


}
