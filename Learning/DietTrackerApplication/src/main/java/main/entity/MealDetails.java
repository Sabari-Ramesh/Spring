package main.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MealDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meal_id")
    private long id;

    @Column(name="meal_type", nullable = false)
    private int mealType; 

    @Column(name="meal_date", nullable = false)
    private LocalDate mealDate;

    
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    private String foodName;
    private double quantity;
    private double calories;
    private double protein;
    private double carboHydrate;
    private double vitamins;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate lastUpdate;

    // Getters and Setters

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

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



    @Override
    public String toString() {
        return "MealDetails [id=" + id + ", mealType=" + mealType + ", mealDate=" + mealDate + ", foodName=" + foodName + ", quantity=" + quantity + ", calories=" + calories + ", protein="
                + protein + ", carboHydrate=" + carboHydrate + ", vitamins=" + vitamins + ", dateCreated=" 
                + dateCreated + ", lastUpdate=" + lastUpdate + "]";
    }
}
