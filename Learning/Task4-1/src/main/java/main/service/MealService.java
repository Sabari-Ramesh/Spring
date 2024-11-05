package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.Bo.MealBo;
import main.Exception.DateException;
import main.Exception.MealTypeException;
import main.Exception.NameException;
import main.Exception.QuantityException;
import main.entity.MealDetails;

@Service
public class MealService {
	
	@Autowired
	private MealBo mealBo;

	@Transactional
	public MealDetails addMeal(MealDetails mealDetails) throws DateException, NameException, QuantityException, MealTypeException {
		return mealBo.addMeal(mealDetails);
	}
	@Transactional
	public List<MealDetails> getAllMeals() {
		return mealBo.getAllMeals();
	}
	
}
