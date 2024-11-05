package dietTrackerApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "city_info")
public class CityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city")
    private int city;

    @Column(name = "city_name", length = 50)
    private String cityName;
    
 // Getters and Setters

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	 //ToString

	@Override
	public String toString() {
		return "CityInfo [city=" + city + ", cityName=" + cityName + "]";
	}	
    
    
}
