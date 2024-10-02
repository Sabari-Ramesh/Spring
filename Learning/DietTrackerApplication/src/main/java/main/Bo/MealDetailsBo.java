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
		validName(mealDetail.getFoodName()," FoodName");
		validMealType(mealDetail.getMealType());
		
	    return mealDetailRepo.save(mealDetail);
	    
	}


	// Find BY Id

	public MealDetails findByMealId(MealDetails mealDetail) throws MealIdNotFoundException {
		long id = mealDetail.getId();
		validMealId(id);
		MealDetails fetchedDetail = mealDetailRepo.findById(id).get();
		return fetchedDetail;
	}


	// Update

	
	public MealDetails updateMealDetail(MealDetails mealDetail) throws MealIdNotFoundException, FoodNameException {
		long id=mealDetail.getId();
		
		validMealId(id);
		validName(mealDetail.getFoodName(),"foodName");
		
		MealDetails updateDetail=mealDetailRepo.findById(id).get();
		updateDetail.setFoodName(mealDetail.getFoodName());
		updateDetail=mealDetailRepo.save(updateDetail);
		return updateDetail;
	}

	//Delete

	public boolean deleteId(MealDetails mealDetail) throws MealIdNotFoundException {
		
		validMealId(mealDetail.getId());
		long id=mealDetail.getId();
		mealDetailRepo.deleteById(id);
		return true;
		
	}
	
	
	

	//Custom Querry Find By UserID :
	
	public List<MealDetails> findMealDetailsByUserId(long id) throws UserNotFound {
		validUser(id);
		List<MealDetails> list=mealDetailRepo.findMealDetailsByUserId(id);
		return list;
	}
	//User Association
   
	
	public Users associationUserWithMealDetails(Users user) throws InValidCityId, FoodNameException, InValidEmailException, DateException, QuantityException, MealTypeException  {

	    // Perform all validations before saving
	    validUserCity(user.getCity());
	    validName(user.getName(), "Name");
	    vailEmail(user.getEmail());
	    List<MealDetails> validDetails = user.getMealDetails();
	    
	    // Validate meal details
	    validateList(validDetails);
	    
	    // After all validations have passed, save the user
	    Users insertedUser = userRepo.save(user);
	    return insertedUser;
	}


	/*
	  
		public Users associationUserWithMealDetails(Users user) throws InValidCityId, FoodNameException, InValidEmailException, DateException, QuantityException, MealTypeException  {
		
	validUserCity(user.getCity());		
	validName(user.getName()," Name");
	vailEmail(user.getEmail());
	List<MealDetails> validDetails=user.getMealDetails();
	validateList(validDetails);
	Users insertedUser=userRepo.save(user);	
	List<MealDetails> mealDetail=user.getMealDetails();
	return insertedUser;
	
	}
	
	 */



//
//	private void vaildName(String name, String string) {
//		// TODO Auto-generated method stub
//		
//	}




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
	

	private void validName(String name,String msg) throws FoodNameException {
		if(name==null) {
			throw new FoodNameException("ERROR : Invalid "+msg);
		}
	}

	private void validMealType(int mealType) throws MealTypeException {
		if(mealType<=0 || mealType>4) {
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
			if(city>20) {
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
			if(!email.endsWith("@gmail.com")) {
				throw new InValidEmailException("ERROR InValidEmail");
			}
		}

/*		private void validateList(List<MealDetails> validDetails) throws DateException, QuantityException, FoodNameException, MealTypeException {
			
			for(int i=0;i<validDetails.size();i++) {
				MealDetails mealDetail=validDetails.get(i);
				
				validDate(mealDetail.getMealDate());
				validQuantity(mealDetail.getCalories()," Calories ");
				validQuantity(mealDetail.getCarboHydrate()," CarboHydrates");
				validQuantity(mealDetail.getProtein()," Protein");
				validQuantity(mealDetail.getQuantity()," Quantity");
				validQuantity(mealDetail.getVitamins()," Vitamins");
				validName(mealDetail.getFoodName()," FoodName");
				validMealType(mealDetail.getMealType());
				
			}
			
		}

*/
		
		private void validateList(List<MealDetails> validDetails) throws DateException, QuantityException, FoodNameException, MealTypeException {

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
}
