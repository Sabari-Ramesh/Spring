package dietTrackerApplication.BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import dietTrackerApplication.DAO.MealDetailsProjection;
import dietTrackerApplication.DAO.MealDetailsRepository;
import dietTrackerApplication.DAO.MealInfoRepository;
import dietTrackerApplication.DAO.MealSummary;
import dietTrackerApplication.DAO.UserRepository;
import dietTrackerApplication.Exception.DateException;
import dietTrackerApplication.Exception.InvalidNumberException;
import dietTrackerApplication.Exception.MealIdNotFoundException;
import dietTrackerApplication.Exception.MealTypeException;
import dietTrackerApplication.Exception.NameException;
import dietTrackerApplication.Exception.QuantityException;
import dietTrackerApplication.Exception.UserNotFound;
import dietTrackerApplication.entity.MealDetails;
import dietTrackerApplication.entity.MealInfo;
import dietTrackerApplication.entity.User;

@Component
public class MealDetailsBO {

	@Autowired
	private MealDetailsRepository mealDetailRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MealInfoRepository mealInfoRepo;
	
	Logger logger = Logger.getLogger(MealDetailsBO.class);
	
	// 2.Insert

	public MealDetails insertDetails(MealDetails mealDetail) throws DataIntegrityViolationException, UserNotFound,
			DateException, QuantityException, NameException, MealTypeException {

		logger.debug("Inserting meal details: "+ mealDetail);


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
	
	// 4.Find All

		public List<MealDetails> fetchAll() {
			List<MealDetails> mealDetail = mealDetailRepo.findAll();
			logger.debug("mealDetails records...");
			return mealDetail;
		}

	// 3. Find By Id

	public MealDetails fetchById(long id) throws MealIdNotFoundException {
		validMealId(id);
		
		logger.debug("Find by user id :"+id);
		MealDetails detail = mealDetailRepo.findById(id).get();
		return detail;
	}

	

	// 5.Find By User Id( Custom Querry )

	public List<MealDetails> fetchByUserId(long id) throws UserNotFound {
		logger.debug("Find By User");
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
		logger.debug("Find By min :"+min+"max :"+max);
		return mealDetail;
	}

	// 7.Custom querry with Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection = mealDetailRepo.findCustomMealDetails();
		logger.debug("MealDetails With Projection"+mealDetailProjection);
		return mealDetailProjection;
	}

	// 8.Custom Query with Aggregate function

	public double findAvgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) throws DateException {

		validDates(startDate, endDate);
		Double avgCalorie = mealDetailRepo.findAvgCaloriesByDateRange(startDate, endDate);
		if (avgCalorie == null) {
			return 0.0;
		}
		logger.debug("Find The Avg Calories Starting Date "+startDate+"End Date :"+endDate +"Abg Calories :"+avgCalorie);
		return avgCalorie;
	}

	// 9.Named with Clauses

	public List<MealSummary> avgCaloriesAndTotalQuantity(double calorie) throws QuantityException {

		validQuantity(calorie, "Calorie");
		List<MealSummary> mealSummary = mealDetailRepo.avgCaloriesAndTotalQuantity(calorie);
		if (mealSummary == null || mealSummary.isEmpty()) {
			return new ArrayList<>();
		}
		logger.debug("Find the Avg calorie and Quantity calorie Thresshold "+calorie+mealSummary);
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
		
		 for (int i = 0; i < name.length(); i++) {
		        if (!Character.isAlphabetic(name.charAt(i))) {
		            throw new NameException("ERROR: Invalid " + msg + ". Name must contain only letters.");
		        }
		    }
		
	}

	private void validMealType(MealInfo mealInfo) throws MealTypeException {
		String meal =mealInfo.getMeal().trim();
		int mealType=0;
		if (meal.equalsIgnoreCase("Lunch")) {
			mealType=mealInfoRepo.findMealTypeByMeal("Lunch");
		}else if(meal.equalsIgnoreCase("Breakfast")) {
			mealType=mealInfoRepo.findMealTypeByMeal("Lunch");
		}else if(meal.equalsIgnoreCase("Dinner")) {
			mealType=mealInfoRepo.findMealTypeByMeal("Lunch");
		}else if(meal.equalsIgnoreCase("Snacks")) {
			mealType=mealInfoRepo.findMealTypeByMeal("Lunch");
		}else {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
		mealInfo.setMealType(mealType);
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
