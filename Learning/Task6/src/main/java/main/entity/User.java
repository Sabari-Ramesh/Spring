package main.entity;

import java.util.Set;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role; // e.g., "USER", "ADMIN"

    @OneToMany(mappedBy = "user")
    private Set<MealDetails> mealDetails;

    @OneToMany(mappedBy = "user")
    private Set<DailyCalorieIntake> dailyCalorieIntakes;
    
    // Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<MealDetails> getMealDetails() {
		return mealDetails;
	}

	public void setMealDetails(Set<MealDetails> mealDetails) {
		this.mealDetails = mealDetails;
	}

	public Set<DailyCalorieIntake> getDailyCalorieIntakes() {
		return dailyCalorieIntakes;
	}

	public void setDailyCalorieIntakes(Set<DailyCalorieIntake> dailyCalorieIntakes) {
		this.dailyCalorieIntakes = dailyCalorieIntakes;
	}    
    
}