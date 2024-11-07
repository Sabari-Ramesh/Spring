package main.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DailyCalorieIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalVitamins;

    @ManyToOne
    private User user;

    //Getter And Setter
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}

	public double getTotalProtein() {
		return totalProtein;
	}

	public void setTotalProtein(double totalProtein) {
		this.totalProtein = totalProtein;
	}

	public double getTotalCarbs() {
		return totalCarbs;
	}

	public void setTotalCarbs(double totalCarbs) {
		this.totalCarbs = totalCarbs;
	}

	public double getTotalVitamins() {
		return totalVitamins;
	}

	public void setTotalVitamins(double totalVitamins) {
		this.totalVitamins = totalVitamins;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}
