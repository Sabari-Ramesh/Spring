package main.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import main.entity.Users;

@Component
public class MealDetailsDTO {

	private long id;
	private int mealType;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate mealDate;
	private Users user;
	private String foodName;
	private double quantity;
	private double calories;
	private double protein;
	private double carboHydrate;
	private double vitamins;
	private LocalDate dateCreated;
    private LocalDate lastUpdate;
	// Getter And Setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMealType() {
		return mealType;
	}

	public void setMealType(int mealType) {
		this.mealType = mealType;
	}

	public LocalDate getMealDate() {
		return mealDate;
	}

	public void setMealDate(LocalDate mealDate) {
		this.mealDate = mealDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public double getCarboHydrate() {
		return carboHydrate;
	}

	public void setCarboHydrate(double carboHydrate) {
		this.carboHydrate = carboHydrate;
	}

	public double getVitamins() {
		return vitamins;
	}

	public void setVitamins(double vitamins) {
		this.vitamins = vitamins;
	}

	public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

	// ToString

	@Override
	public String toString() {
		return "MealDetailsDto [id=" + id + ", mealType=" + mealType + ", mealDate=" + mealDate + ", foodName="
				+ foodName + ", quantity=" + quantity + ", calories=" + calories + ", protein=" + protein
				+ ", carboHydrate=" + carboHydrate + ", vitamins=" + vitamins + ", dateCreated=" + dateCreated
				+ ", lastUpdate=" + lastUpdate + "]";
	}

}



