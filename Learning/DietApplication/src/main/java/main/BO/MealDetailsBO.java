package main.BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealDetailsRepository;
import main.DAO.MealSummary;
import main.DAO.UserRepository;
import main.Exception.DateException;
import main.Exception.InvalidNumberException;
import main.Exception.MealIdNotFoundException;
import main.Exception.MealTypeException;
import main.Exception.NameException;
import main.Exception.QuantityException;
import main.Exception.UserNotFound;
import main.entity.MealDetails;
import main.entity.MealInfo;
import main.entity.User;

@Component
public class MealDetailsBO {

	@Autowired
	private MealDetailsRepository mealDetailRepo;

	@Autowired
	private UserRepository userRepo;

	// 2.Insert

	public MealDetails insertDetails(MealDetails mealDetail) throws DataIntegrityViolationException, UserNotFound,
			DateException, QuantityException, NameException, MealTypeException {

		validUser(mealDetail.getUser());
		validDate(mealDetail.getMealDate());
		validQuantity(mealDetail.getCalories(), " Calories ");
		validQuantity(mealDetail.getCarbs(), " CarboHydrates");
		validQuantity(mealDetail.getProtein(), " Protein");
		validQuantity(mealDetail.getQuantity(), " Quantity");
		validQuantity(mealDetail.getVitamins(), " Vitamins");
		validName(mealDetail.getFoodName(), " FoodName");
		validMealType(mealDetail.getMealType());

		MealDetails detail = mealDetailRepo.save(mealDetail);
		return detail;

	}

	// 3. Find By Id

	public MealDetails fetchById(long id) throws MealIdNotFoundException {
		validMealId(id);
		MealDetails detail = mealDetailRepo.findById(id).get();
		return detail;
	}

	// 4.Find All

	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetail = mealDetailRepo.findAll();
		return mealDetail;
	}

	// 5.Find By User Id( Custom Querry )

	public List<MealDetails> fetchByUserId(long id) throws UserNotFound {
		validUser(id);
		List<MealDetails> mealDetail = mealDetailRepo.findMealDetailsByUserId(id);
		return mealDetail;
	}

	// 6.Find By min and max Quantity ( Named Querry )

	public List<MealDetails> findByQuantityRange(double min, double max) throws InvalidNumberException {
		validNumber(min, max);
		List<MealDetails> mealDetail = mealDetailRepo.findByQuantityRange(min, max);
		if (mealDetail.isEmpty()) {
			return new ArrayList<>();
		}
		return mealDetail;
	}

	// 7.Custom querry with Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection = mealDetailRepo.findCustomMealDetails();
		return mealDetailProjection;
	}

	// 8.Custom Query with Aggregate function

	public double findAvgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) throws DateException {

		validDates(startDate, endDate);
		Double avgCalorie = mealDetailRepo.findAvgCaloriesByDateRange(startDate, endDate);
		if (avgCalorie == null) {
			return 0.0;
		}
		return avgCalorie;
	}

	// 9.Named with Clauses

	public List<MealSummary> avgCaloriesAndTotalQuantity(double calorie) throws QuantityException {

		validQuantity(calorie, "Calorie");
		List<MealSummary> mealSummary = mealDetailRepo.avgCaloriesAndTotalQuantity(calorie);
		if (mealSummary == null || mealSummary.isEmpty()) {
			return new ArrayList<>();
		}
		return mealSummary;

	}

	// Update
	
	
	public MealDetails updateMealDetail(MealDetails mealDetail) throws MealIdNotFoundException, NameException {
		long id = mealDetail.getMealId();

		validMealId(id);
		validName(mealDetail.getFoodName(), "foodName");

		MealDetails updateDetail = mealDetailRepo.findById(id).get();
		updateDetail.setFoodName(mealDetail.getFoodName());
		updateDetail = mealDetailRepo.save(updateDetail);
		return updateDetail;
	}

	

	// Validation

	private void validUser(User user) throws UserNotFound, DataIntegrityViolationException {
		Optional<User> userOptional = userRepo.findById(user.getUserId());
		if (!userOptional.isPresent()) {
			throw new UserNotFound("ERROR : User does not exist");
		}
	}

	private void validUser(long id) throws UserNotFound {
		Optional<User> userOptional = userRepo.findById(id);
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
		String meal =mealInfo.getMeal();
		if (meal.equalsIgnoreCase("Lunch")) {
			mealInfo.setMealType(2);
		}else if(meal.equalsIgnoreCase("Breakfast")) {
			mealInfo.setMealType(1);
		}else if(meal.equalsIgnoreCase("Dinner")) {
			mealInfo.setMealType(3);
		}else if(meal.equalsIgnoreCase("Snacks")) {
			mealInfo.setMealType(4);
		}else {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
	}

	private void validMealId(long id) throws MealIdNotFoundException {
		Optional<MealDetails> mealDetailOptional = mealDetailRepo.findById(id);

		if (!mealDetailOptional.isPresent()) {
			throw new MealIdNotFoundException("ERROR: MealId " + id + " does not exist in the database");
		}
	}

	private void validNumber(double min, double max) throws InvalidNumberException {
		if (min >= max) {
			throw new InvalidNumberException("maximum should be greater than minimum");
		} else if (min < 0) {
			throw new InvalidNumberException("minimum should be Non negative Number");
		} else if (max < 0) {
			throw new InvalidNumberException("maximum should be Non negative Number");
		}
	}

	private void validDates(LocalDate startDate, LocalDate endDate) throws DateException {

		LocalDate today = LocalDate.now();

		if (startDate.isAfter(endDate)) {
			throw new DateException("ERROR : Start date cannot be after end date.");
		}

		if (startDate.isAfter(today)) {
			throw new DateException("ERROR : Start date cannot be in the future.");
		}

		if (endDate.isAfter(today)) {
			throw new DateException("ERROR : End date cannot be in the future.");
		}

	}

}
