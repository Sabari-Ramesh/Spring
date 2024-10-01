package main.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.DTO.MealDetailsDTO;
import main.DTO.UsersDTO;
import main.Response.ResponseHandle;
import main.entity.MealDetails;
import main.entity.Users;
import main.service.MealDetailsService;

@RestController
@RequestMapping("/mealdetails")
public class MealDetailController {
	  @Autowired
	    private MealDetailsService mealDetailsService;
	  
	  
	  // Insert Meal Detail
	  @PostMapping("/add")
	  public MealDetailsDTO insertMealDetail(@RequestBody MealDetailsDTO mealDetailDto) {

	      // Convert DTO to Entity
	      MealDetails mealDetail = new MealDetails();
	      mealDetail.setMealType(mealDetailDto.getMealType());
	      mealDetail.setMealDate(mealDetailDto.getMealDate());
	      mealDetail.setQuantity(mealDetailDto.getQuantity());
	      mealDetail.setCalories(mealDetailDto.getCalories());
	      mealDetail.setProtein(mealDetailDto.getProtein());
	      mealDetail.setCarboHydrate(mealDetailDto.getCarboHydrate());
	      mealDetail.setVitamins(mealDetailDto.getVitamins());
	      mealDetail.setFoodName(mealDetailDto.getFoodName());
	      mealDetail.setUser(mealDetailDto.getUser());

	      ResponseHandle response = mealDetailsService.insertMealDetail(mealDetail);
	      MealDetails inserted = response.getMealDetail();
	      
	      //Set update and id details
	      
	      mealDetailDto.setDateCreated(inserted.getDateCreated());
	      mealDetailDto.setLastUpdate(inserted.getLastUpdate());
	      mealDetailDto.setId(inserted.getId());
	      return mealDetailDto;
	      
	  }

	    
	    //Find By meal Id
	    
	  @GetMapping("/mealid=/{id}")
	  public MealDetailsDTO findByMealId(@PathVariable("id") long id) {
	      MealDetailsDTO mealDetailDto = new MealDetailsDTO();
	      
	      MealDetails mealDetail=new MealDetails();
	      mealDetail.setId(id);
	      
	      ResponseHandle response = mealDetailsService.findByMealId(mealDetail);
	      MealDetails fetchedDetail = response.getMealDetail();	      
	      return mapToDto(fetchedDetail);
	  }

	    
	 // Update Meal Detail
	    @PutMapping("/update")
	    public MealDetailsDTO updateMealDetail(@RequestBody MealDetailsDTO mealDetailDto) {
	    	
			MealDetails mealDetail = new MealDetails();
			mealDetail.setId(mealDetailDto.getId());
			mealDetail.setFoodName(mealDetailDto.getFoodName());
	        ResponseHandle response = mealDetailsService.updateMealDetail(mealDetail);
	        
	        MealDetails fetchedDetail = response.getMealDetail();
		     
		      
		      // Convert entity into DTO
		      
		   /*   mealDetailDto.setId(fetchedDetail.getId());
		      mealDetailDto.setCalories(fetchedDetail.getCalories());
		      mealDetailDto.setCarboHydrate(fetchedDetail.getCarboHydrate());
		      mealDetailDto.setDateCreated(fetchedDetail.getDateCreated());
		      mealDetailDto.setFoodName(fetchedDetail.getFoodName());
		      mealDetailDto.setLastUpdate(fetchedDetail.getLastUpdate());
		      mealDetailDto.setMealDate(fetchedDetail.getMealDate());
		      mealDetailDto.setMealType(fetchedDetail.getMealType());
		      mealDetailDto.setProtein(fetchedDetail.getProtein());
		      mealDetailDto.setQuantity(fetchedDetail.getQuantity());
		    //  mealDetailDto.setUser(fetchedDetail.getUser());
		      mealDetailDto.setVitamins(fetchedDetail.getVitamins()); */
		      
		      return mapToDto(fetchedDetail);
		      
	    }
	    
	    // Delete Meal Detail 
	    @DeleteMapping("/deletemealid=/{id}")
	    public ResponseHandle deleteMealDetail(@PathVariable("id") long id) {
	    	
	    	MealDetails mealDetail=new MealDetails();
			mealDetail.setId(id);	    	

	        ResponseHandle response = mealDetailsService.deleteId(mealDetail);
	        return response;
	    }

	    // Find Meal Details By User ID 
	    @GetMapping("/userid=/{userId}")  //Loop Error
	    public  List<MealDetailsDTO> findMealDetailsByUserId(@PathVariable("userId") long userId) {
	    	
	        ResponseHandle response = mealDetailsService.findMealDetailsByUserId(userId);
	        List<MealDetails> list=response.getMealDetailsList();
	        List<MealDetailsDTO> listDTO=new ArrayList<>();
	        
	        for(int i=0;i<list.size();i++) {	
	        	MealDetails mealDetail=list.get(i);
	        	MealDetailsDTO getDetail=mapToDto(mealDetail);
	        	listDTO.add(getDetail);
	        }
	        
	        return listDTO;
	    }
	    
	    
	    //Association User with MealDetails
	    @PostMapping("/associate-meal-details")
	    public ResponseHandle associateUserWithMealDetails(@RequestBody UsersDTO userDto) {
	    	
	    	Users user = new Users();
	        user.setName(userDto.getName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(userDto.getPassword());
	        user.setDateOfBirth(userDto.getDateOfBirth());
	        user.setMobileNumber(userDto.getMobileNumber());
	        user.setCity(userDto.getCity());

	        List<MealDetails> mealDetailsList = new ArrayList<>();
	        for (MealDetailsDTO mealDetailDto : userDto.getMealDetails()) {
	            MealDetails mealDetail = new MealDetails();
	            mealDetail.setMealType(mealDetailDto.getMealType());
	            mealDetail.setMealDate(mealDetailDto.getMealDate());
	            mealDetail.setFoodName(mealDetailDto.getFoodName());
	            mealDetail.setQuantity(mealDetailDto.getQuantity());
	            mealDetail.setProtein(mealDetailDto.getProtein());
	            mealDetail.setVitamins(mealDetailDto.getVitamins());
	            mealDetail.setCalories(mealDetailDto.getCalories());
	            mealDetail.setCarboHydrate(mealDetailDto.getCarboHydrate());
	            mealDetail.setUser(user); // Set the user for the meal detail
	            mealDetailsList.add(mealDetail);
	        }

	        user.setMealDetails(mealDetailsList);
	    	
	        ResponseHandle response = mealDetailsService.associationUserWithMealDetails(user);
	        
	        return response;
	    }
	    
	    
	    
	    
	    
	    public static MealDetailsDTO mapToDto(MealDetails fetchedDetail) {
	    	
	        MealDetailsDTO mealDetailDto = new MealDetailsDTO();
	        mealDetailDto.setId(fetchedDetail.getId());
	        mealDetailDto.setCalories(fetchedDetail.getCalories());
	        mealDetailDto.setCarboHydrate(fetchedDetail.getCarboHydrate());
	        mealDetailDto.setDateCreated(fetchedDetail.getDateCreated());
	        mealDetailDto.setFoodName(fetchedDetail.getFoodName());
	        mealDetailDto.setLastUpdate(fetchedDetail.getLastUpdate());
	        mealDetailDto.setMealDate(fetchedDetail.getMealDate());
	        mealDetailDto.setMealType(fetchedDetail.getMealType());
	        mealDetailDto.setProtein(fetchedDetail.getProtein());
	        mealDetailDto.setQuantity(fetchedDetail.getQuantity());
	        // mealDetailDto.setUser(fetchedDetail.getUser()); // Optional
	        mealDetailDto.setVitamins(fetchedDetail.getVitamins());

	        return mealDetailDto;
	    }

}
