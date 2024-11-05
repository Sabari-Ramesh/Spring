package main.Bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Exception.DateException;
import main.Exception.MealTypeException;
import main.Exception.NameException;
import main.Exception.QuantityException;
import main.entity.MealDetails;
import main.repository.MealRepository;

@Component
public class MealBo {
	
	@Autowired
    private MealRepository mealRepository;
	
    public MealDetails addMeal(MealDetails mealDetails) throws DateException, NameException, QuantityException, MealTypeException {
    	
    	validDate(mealDetails.getMealDate());
    	vaildFoodName(mealDetails.getFoodName());
    	validQuantity(mealDetails.getCalories());
    	validQuantity(mealDetails.getProtein());
    	validQuantity(mealDetails.getQuantity());
    	validQuantity(mealDetails.getVitamins());
    	validMealType(mealDetails);
    	
        return mealRepository.save(mealDetails);
    }

	public List<MealDetails> getAllMeals() {
        return mealRepository.findAll();
    }
    
    //Validation
    
    private void validDate(LocalDate mealDate) throws DateException {
		LocalDate currentDate = LocalDate.now();
		if (mealDate.isAfter(currentDate)) {
			throw new DateException("ERROR : In valid Date");
		}
	}
    
    //Valid MealType
    
	private void validMealType(MealDetails mealDetails) throws MealTypeException {
		
		String meal=mealDetails.getMealType();
		if (meal.equalsIgnoreCase("Lunch")) {
			mealDetails.setFoodName("Lunch");
		}else if(meal.equalsIgnoreCase("Breakfast")) {
			mealDetails.setFoodName("Breakfast");
		}else if(meal.equalsIgnoreCase("Dinner")) {
			mealDetails.setFoodName("Dinner");
		}else if(meal.equalsIgnoreCase("Snacks")) {
			mealDetails.setFoodName("Snacks");
		}else {
			throw new MealTypeException("ERROR : Invalid Meal Type");
		}
	}
	
	//Valid FoodName
	
	 private void vaildFoodName(String name) throws NameException {
		 if (name == null) {
				throw new NameException("ERROR : Invalid FoodName");
			}
			
			 for (int i = 0; i < name.length(); i++) {
			        if (!Character.isAlphabetic(name.charAt(i))) {
			            throw new NameException("ERROR: Invalid FoodName. Name must contain only letters.");
			        }
			    }
		}
	 
	 //Valid Quantity
	 private void validQuantity(double quantity) throws QuantityException {
		 if (quantity <= 0) {
				throw new QuantityException("ERRROR :In valid");
			}
		}

	
}
