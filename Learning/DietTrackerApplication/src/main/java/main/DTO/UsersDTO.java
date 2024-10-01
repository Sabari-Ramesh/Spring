package main.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import main.entity.MealDetails;

@Component
public class UsersDTO {

	private long id;
	private String name;
	private String email;
	private String password;
	private int gender;
	private LocalDate dateOfBirth;
	private long mobileNumber;
	private int city;
	private List<MealDetailsDTO> mealDetails = new ArrayList<>();
	private LocalDate dateCreated;
	private LocalDate lastUpdate;

	// Getter And Setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public List<MealDetailsDTO> getMealDetails() {
		return mealDetails;
	}

	public void setMealDetails(List<MealDetailsDTO> mealDetails) {
		this.mealDetails = mealDetails;
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

	// To String

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", dateOfBirth=" + dateOfBirth + ", mobileNumber=" + mobileNumber + ", city=" + city
				+ ", mealDetails=" + mealDetails + ", dateCreated=" + dateCreated + ", lastUpdate=" + lastUpdate + "]";
	}

}

