package dietTrackerApplication.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dietTrackerApplication.DTO.MealDetailDTO;
import dietTrackerApplication.entity.MealDetails;

@Component
public class MealDetailsClient {

    private  RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080/mealdetails";

    public MealDetailsClient() {
        this.restTemplate = new RestTemplate();
    }

    // Method to add a meal
    
    public String addMeal(MealDetailDTO mealDetail) {
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/insert", mealDetail, String.class);
        return response.getBody();
    }
    
    //Method for FindAll
    
 /*   public List<MealDetails> getAllMeals() {
        ResponseEntity<List<MealDetails>> response = restTemplate.exchange(baseUrl + "/findAll",org.springframework.http.HttpMethod.GET,
                null,new ParameterizedTypeReference<List<MealDetails>>() {}
        );
        return response.getBody();
    }
    
    */
    public List<MealDetailDTO> getAllMeals() {
        ResponseEntity<List<MealDetailDTO>> response = restTemplate.exchange(
            baseUrl + "/findAll",
            org.springframework.http.HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<MealDetailDTO>>() {}
        );
        return response.getBody();
    }
}
