package main.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.Exception.DateException;
import main.Exception.MealTypeException;
import main.Exception.NameException;
import main.Exception.QuantityException;
import main.entity.MealDetails;
import main.service.MealService;

@RestController
@RequestMapping("/api/meal-details")
public class MealController {

	@Autowired
    private MealService mealService;

    @PostMapping("/add")
    public ResponseEntity<MealDetails> addMeal(@RequestBody MealDetails mealDetails){
        MealDetails savedMeal=null;
		try {
			savedMeal = mealService.addMeal(mealDetails);
		} catch (DateException e) {
			System.out.println(e.getMessage());
		} catch (NameException e) {
			System.out.println(e.getMessage());
		} catch (QuantityException e) {
			System.out.println(e.getMessage());
		} catch (MealTypeException e) {
			System.out.println(e.getMessage());
		}
        return ResponseEntity.ok(savedMeal);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MealDetails>> getAllMeals() {
        List<MealDetails> meals = mealService.getAllMeals();
        return ResponseEntity.ok(meals);
    }
}

