package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DailyCalorieIntake")
public class DailyCalorieIntake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderInfo gender;

    @Column(name = "start_age", nullable = false)
    private int startAge;

    @Column(name = "end_age", nullable = false)
    private int endAge;

    @Column(name = "average_calories", nullable = false)
    private int averageCalories;
    
    // Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GenderInfo getGender() {
		return gender;
	}

	public void setGender(GenderInfo gender) {
		this.gender = gender;
	}

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}

	public int getAverageCalories() {
		return averageCalories;
	}

	public void setAverageCalories(int averageCalories) {
		this.averageCalories = averageCalories;
	}

	//ToString
	
	@Override
	public String toString() {
		return "DailyCalorieIntake [id=" + id + ", gender=" + gender + ", startAge=" + startAge + ", endAge=" + endAge
				+ ", averageCalories=" + averageCalories + "]";
	}

   
}

