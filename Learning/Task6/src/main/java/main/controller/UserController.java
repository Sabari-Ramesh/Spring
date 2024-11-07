package main.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.entity.MealDetails;
import main.service.MealService;

@RestController
@RequestMapping("/api/meals")
public class UserController {

    @Autowired
    private MealService mealService;

    @PostMapping("/add")
    public MealDetails addMeal(@RequestBody MealDetails mealDetails) {
        return mealService.addMealDetails(mealDetails);
    }

    @GetMapping("/view")
    public List<MealDetails> getMeals(@RequestParam Long userId, @RequestParam String date) {
        LocalDate mealDate = LocalDate.parse(date);
        return mealService.getMealsForDate(userId, mealDate);
    }
}
