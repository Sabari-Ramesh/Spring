package main.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "User_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private GenderInfo gender;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dob;

    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "city")
    private CityInfo city;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MealDetails> mealDetails;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate lastUpdate;

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public GenderInfo getGender() {
        return gender;
    }

    public void setGender(GenderInfo gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public CityInfo getCity() {
        return city;
    }

    public void setCity(CityInfo city) {
        this.city = city;
    }

    public List<MealDetails> getMealDetails() {
        return mealDetails;
    }

    public void setMealDetails(List<MealDetails> mealDetails) {
        this.mealDetails = mealDetails;
    }
	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

//    @Override
//    public String toString() {
//        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
//                + ", gender=" + gender + ", dob=" + dob + ", mobileNumber=" + mobileNumber + ", city=" + city
//                + ", mealDetails=" + mealDetails + "]";
//    }
}
