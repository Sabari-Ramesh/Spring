package main.client;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import main.entity.MealDetails;

public class MealClient {

	
    private  RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080/api/meal-details";

    public MealClient() {
        this.restTemplate = new RestTemplate();
    }

    // Method to add a meal
    public MealDetails addMeal(MealDetails mealDetails) {
        ResponseEntity<MealDetails> response = restTemplate.postForEntity(baseUrl + "/add", mealDetails, MealDetails.class);
        return response.getBody();
    }

    public List<MealDetails> getAllMeals() {
        ResponseEntity<List<MealDetails>> response = restTemplate.exchange(baseUrl + "/all",org.springframework.http.HttpMethod.GET,
                null,new ParameterizedTypeReference<List<MealDetails>>() {}
        );
        return response.getBody();
    }
}
