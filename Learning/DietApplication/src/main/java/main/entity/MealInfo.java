package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meal_info")
public class MealInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_type")
    private int mealType;

    @Column(name = "meal")
    private String meal;
    
 // Getters and Setters

	public int getMealType() {
		return mealType;
	}

	public void setMealType(int mealType) {
		this.mealType = mealType;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
	
	//TOString

	@Override
	public String toString() {
		return "MealInfo [mealType=" + mealType + ", meal=" + meal + "]";
	}

	
    
}
