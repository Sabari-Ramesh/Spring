package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gender_info")
public class GenderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private int genderId;

    @Column(name = "gender", length = 20)
    private String gender;
    
    // Getters and Setters

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	//ToString
	
	@Override
	public String toString() {
		return "GenderInfo [genderId=" + genderId + ", gender=" + gender + "]";
	}
	
    
}
