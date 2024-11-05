package main.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class MealDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealId;

    @Column(nullable = false)
    private String mealType;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private double calories;

    @Column(nullable = false)
    private double protein;

    @Column(nullable = false)
    private double carbs;

    @Column(nullable = false)
    private double vitamins;
    
 // Getters and Setters

	public long getMealId() {
		return mealId;
	}

	public void setMealId(long mealId) {
		this.mealId = mealId;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public LocalDate getMealDate() {
		return mealDate;
	}

	public void setMealDate(LocalDate mealDate) {
		this.mealDate = mealDate;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	public double getVitamins() {
		return vitamins;
	}

	public void setVitamins(double vitamins) {
		this.vitamins = vitamins;
	}

    
    
    
}