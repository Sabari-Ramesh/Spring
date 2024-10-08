package main.DTO;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import main.entity.MealInfo;
import main.entity.User;

@Component
public class MealDetailDTO {
	
	    private long mealId;


	    private String meal;


	    private LocalDate mealDate;

	    private long userid;


	    private String foodName;


	    private double quantity;


	    private double calories;


	    private double protein;

	    
	    private double carbs;

	    private double vitamins;
	    
	    private LocalDate dateCreated;

	    private LocalDate lastUpdate;
	    
	    //GETTER AND SETTER

		public long getMealId() {
			return mealId;
		}

		public void setMealId(long mealId) {
			this.mealId = mealId;
		}

		public String getMeal() {
			return meal;
		}

		public void setMeal(String meal) {
			this.meal = meal;
		}

		public LocalDate getMealDate() {
			return mealDate;
		}

		public void setMealDate(LocalDate mealDate) {
			this.mealDate = mealDate;
		}

		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
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

		//ToString
		
		@Override
		public String toString() {
			return "MealDetailDTO [mealId=" + mealId + ", meal" + meal + ", mealDate=" + mealDate
					+ ", userid=" + userid + ", foodName=" + foodName + ", quantity=" + quantity + ", calories="
					+ calories + ", protein=" + protein + ", carbs=" + carbs + ", vitamins=" + vitamins
					+ ", dateCreated=" + dateCreated + ", lastUpdate=" + lastUpdate + "]";
		}
	    
		

}
