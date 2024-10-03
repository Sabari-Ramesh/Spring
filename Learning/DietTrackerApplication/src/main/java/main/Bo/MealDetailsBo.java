package main.Bo;

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
import main.DAO.UserRepo;
import main.Exceptions.DateException;
import main.Exceptions.FoodNameException;
import main.Exceptions.InValidCityId;
import main.Exceptions.InValidEmailException;
import main.Exceptions.MealIdNotFoundException;
import main.Exceptions.MealTypeException;
import main.Exceptions.MobileException;
import main.Exceptions.QuantityException;
import main.Exceptions.UserNotFound;
import main.entity.MealDetails;
import main.entity.Users;

@Component
public class MealDetailsBo {

	@Autowired
	private MealDetailsRepository mealDetailRepo;

	@Autowired
	private UserRepo userRepo;

	// 2. Insert

	public MealDetails insertMealDetails(MealDetails mealDetail) throws UserNotFound, DataIntegrityViolationException,
			DateException, QuantityException, FoodNameException, MealTypeException {

		validUser(mealDetail.getUser());
		validDate(mealDetail.getMealDate());
		validQuantity(mealDetail.getCalories(), " Calories ");
		validQuantity(mealDetail.getCarboHydrate(), " CarboHydrates");
		validQuantity(mealDetail.getProtein(), " Protein");
		validQuantity(mealDetail.getQuantity(), " Quantity");
		validQuantity(mealDetail.getVitamins(), " Vitamins");
		validName(mealDetail.getFoodName(), " FoodName");
		validMealType(mealDetail.getMealType());

		return mealDetailRepo.save(mealDetail);

	}

	// 3. Find BY Id

	public MealDetails findByMealId(MealDetails mealDetail) throws MealIdNotFoundException {

		long id = mealDetail.getId();
		validMealId(id);
		MealDetails fetchedDetail = mealDetailRepo.findById(id).get();
		return fetchedDetail;

	}

	// 4.Fetch ALl
	public List<MealDetails> fetchAll() {

		List<MealDetails> mealDetails = mealDetailRepo.findAll();
		return mealDetails;

	}

	// 5.Find By UserID :

	public List<MealDetails> findMealDetailsByUserId(long id) throws UserNotFound {

		validUser(id);
		List<MealDetails> list = mealDetailRepo.findMealDetailsByUserId(id);
		return list;

	}

	// 6. && 8. Named Querry with Aggregate

	public double avgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) throws DateException {

		validDates(startDate,endDate);
		Double avgCalorie = mealDetailRepo.findAvgCaloriesByDateRange(startDate, endDate);
		if (avgCalorie == null) {
			 return 0.0;
		}
		return avgCalorie;

	}


	// 7. Custom With Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		
		List<MealDetailsProjection> mealDetail = mealDetailRepo.findCustomMealDetails();
		return mealDetail;
		
	}

	//9. Named Querry with Clauses
	
	public List<MealSummary> findAvgCaloriesAndTotalQuantity(double calorie) throws QuantityException {

	    validQuantity(calorie, "Calorie");
	    List<MealSummary> mealSummary = mealDetailRepo.findAvgCaloriesAndTotalQuantity(calorie);
	    if (mealSummary == null || mealSummary.isEmpty()) {
	        return new ArrayList<>(); 
	    }	  
	    return mealSummary;
	    
	}

	
	
	// Update

	public MealDetails updateMealDetail(MealDetails mealDetail) throws MealIdNotFoundException, FoodNameException {
		long id = mealDetail.getId();

		validMealId(id);
		validName(mealDetail.getFoodName(), "foodName");

		MealDetails updateDetail = mealDetailRepo.findById(id).get();
		updateDetail.setFoodName(mealDetail.getFoodName());
		updateDetail = mealDetailRepo.save(updateDetail);
		return updateDetail;
	}

	// Delete

	public boolean deleteId(MealDetails mealDetail) throws MealIdNotFoundException {

		validMealId(mealDetail.getId());
		long id = mealDetail.getId();
		mealDetailRepo.deleteById(id);
		return true;

	}

	// User Association

	public Users associationUserWithMealDetails(Users user) throws InValidCityId, FoodNameException,
			InValidEmailException, DateException, QuantityException, MealTypeException, MobileException {

		// Perform all validations before saving
		validUserCity(user.getCity());
		validName(user.getName(), "Name");
		vailEmail(user.getEmail());
		validMobileNumber(user.getMobileNumber());
		List<MealDetails> validDetails = user.getMealDetails();

		// Validate meal details
		validateList(validDetails);

		// After all validations have passed, save the user
		Users insertedUser = userRepo.save(user);
		return insertedUser;
	}


	// Validation

	private void validUser(Users users) throws UserNotFound, DataIntegrityViolationException {
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
		if (quantity <= 0) {
			throw new QuantityException("ERRROR :In valid" + string);
		}
	}

	private void validName(String name, String msg) throws FoodNameException {
		if (name == null) {
			throw new FoodNameException("ERROR : Invalid " + msg);
		}
	}

	private void validMealType(int mealType) throws MealTypeException {
		if (mealType <= 0 || mealType > 4) {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
	}

	private void validMealId(long id) throws MealIdNotFoundException {
		Optional<MealDetails> mealDetailOptional = mealDetailRepo.findById(id);

		if (!mealDetailOptional.isPresent()) {
			throw new MealIdNotFoundException("ERROR: MealId " + id + " does not exist in the database");
		}
	}

	private void validUserCity(int city) throws InValidCityId {
		if (city > 20) {
			throw new InValidCityId("ERROR In valid City id");
		}
	}

	private void validUser(long id) throws UserNotFound {

		Optional<Users> userOptional = userRepo.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFound("ERROR : User does not exist");
		}
		Users user = userOptional.get();
	}

	private void vailEmail(String email) throws InValidEmailException {
		if (!email.endsWith("@gmail.com")) {
			throw new InValidEmailException("ERROR InValidEmail");
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
	
	private void validateList(List<MealDetails> validDetails)
			throws DateException, QuantityException, FoodNameException, MealTypeException {

		for (int i = 0; i < validDetails.size(); i++) {
			MealDetails mealDetail = validDetails.get(i);

			// Validate each field
			validDate(mealDetail.getMealDate());
			validQuantity(mealDetail.getCalories(), "Calories");
			validQuantity(mealDetail.getCarboHydrate(), "CarboHydrates");
			validQuantity(mealDetail.getProtein(), "Protein");
			validQuantity(mealDetail.getQuantity(), "Quantity");
			validQuantity(mealDetail.getVitamins(), "Vitamins");
			validName(mealDetail.getFoodName(), "FoodName");
			validMealType(mealDetail.getMealType());
		}

	}
	
	private void validMobileNumber(long mobileNumber) throws MobileException {
		String no=mobileNumber+"";
		if(no.length() != 10 || no.charAt(0) == '0') {
			throw new MobileException("Enter Valid Mobile Number");
		}
	}
}
