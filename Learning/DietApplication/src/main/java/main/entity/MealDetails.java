package main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
@NamedQuery(	
	    name = "MealDetails.findByQuantityRange",
	    query = "SELECT m FROM MealDetails m WHERE m.quantity BETWEEN :minQuantity AND :maxQuantity"
	),
@NamedQuery(
        name = "MealDetails.avgCaloriesAndTotalQuantity",
        query = "SELECT SUM(m.quantity) AS totalQuantity, AVG(m.calories) AS avgCalories " +
                "FROM MealDetails m GROUP BY m.user HAVING AVG(m.calories) > :calorieThreshold " +
                "ORDER BY AVG(m.calories) DESC"
    )
})


@Table(name = "meal_details")
public class MealDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private long mealId;

    @ManyToOne
    @JoinColumn(name = "meal_type", nullable = false)
    private MealInfo mealType;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @Column(name = "calories")
    private double calories;

    @Column(name = "protein")
    private double protein;

    @Column(name = "carbs")
    private double carbs;

    @Column(name = "vitamins")
    private double vitamins;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate lastUpdate;

    // Getters and Setters
  

	public long getMealId() {
		return mealId;
	}

	public void setMealId(long mealId) {
		this.mealId = mealId;
	}

	public MealInfo getMealType() {
		return mealType;
	}

	public void setMealType(MealInfo mealType) {
		this.mealType = mealType;
	}

	public LocalDate getMealDate() {
		return mealDate;
	}

	public void setMealDate(LocalDate mealDate) {
		this.mealDate = mealDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
	
	
    @Override
    public String toString() {
        return "MealDetails [mealId=" + mealId + ", mealType=" + mealType + ", mealDate=" + mealDate + ", user=" + user
                + ", foodName=" + foodName + ", quantity=" + quantity + ", calories=" + calories + ", protein="
                + protein + ", carbs=" + carbs + ", vitamins=" + vitamins + "]";
    }
}
