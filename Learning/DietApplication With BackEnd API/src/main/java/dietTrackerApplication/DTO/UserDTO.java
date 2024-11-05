package dietTrackerApplication.DTO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import dietTrackerApplication.entity.CityInfo;
import dietTrackerApplication.entity.GenderInfo;
import dietTrackerApplication.entity.MealDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Component
public class UserDTO {

	    private long userId;
	    private String userName;
	    private String email;
	    private String password;
	    private String gender;
	    private LocalDate dob;
	    private String mobileNumber;
	    private String city;
	    private LocalDate dateCreated;
	    private LocalDate lastUpdate;
	    
	    //Getter and Setter
	    
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
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
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
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
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
		
		//To String
		
		@Override
		public String toString() {
			return "UserDTO [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password="
					+ password + ", gender=" + gender + ", dob=" + dob + ", mobileNumber=" + mobileNumber + ", city="
					+ city + ", dateCreated=" + dateCreated + ", lastUpdate=" + lastUpdate + "]";
		}
		
		
	    
}
