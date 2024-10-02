package main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.Bo.MealDetailsBo;
import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.DTO.UsersDTO;
import main.Response.ResponseHandle;
import main.entity.MealDetails;
import main.entity.Users;

@Service
public class MealDetailsService {

	@Autowired
	private UsersDTO userDto;

	@Autowired
	private MealDetailsBo mealDetailBo;

	@Autowired
	private ResponseHandle response;
	

	// Insert
	public ResponseHandle insertMealDetail(MealDetails mealDetail) {
		
		//System.out.println("Serive "+mealDetail);
		MealDetails insertedDetail = mealDetailBo.insertMealDetails(mealDetail);
        
		
		//System.out.println(mealDetail+"Service ");
		long id = insertedDetail.getId();

		if (id > 0) {
			response.setSucessmessage("Details are Added Sucessfully");
			response.setMealDetail(insertedDetail);
		} else {
			response.setFailuremessage("Failure to Add Details " + id);
		}

		response.setId(id);

		return response;
	}

	// FindById

	@Transactional
	public ResponseHandle findByMealId(MealDetails mealDetail) {

		MealDetails fetchedDetail = mealDetailBo.findByMealId(mealDetail);

		if (fetchedDetail != null) {
			response.setSucessmessage("Details are Sucessfully fetched..");
			response.setMealDetail(fetchedDetail);
		} else {
			response.setFailuremessage("Details are not fetched Sucessfully");
		}

		return response;
	}

	// Update Detail

	@Transactional
	public ResponseHandle updateMealDetail(MealDetails mealDetail) {
		
		MealDetails updateDetail = mealDetailBo.updateMealDetail(mealDetail);
		long id = updateDetail.getId();

		if (id > 0) {
			response.setSucessmessage("Details are Updated Sucessfully");
			response.setMealDetail(updateDetail);
		} else {
			response.setFailuremessage("Failure to Update Details " + id);
		}
		
		response.setId(id);

		return response;
	}
	
	//Delete Id
	@Transactional
	public ResponseHandle deleteId(MealDetails mealDetail) {
		
		
		boolean flag=mealDetailBo.deleteId(mealDetail);
		if(flag!=false) {
			response.setSucessmessage(" Meal Detail is Successfully Deleted");
			response.setId(mealDetail.getId());
		}else {
			response.setFailuremessage("Meal detail are Failure to Delete ");
		}
		return response;
	}

	//Find MealDetail By UserId
	@Transactional
	public ResponseHandle findMealDetailsByUserId(long id) {
		
		List<MealDetails> list=mealDetailBo.findMealDetailsByUserId(id);
		response.setMealDetailsList(list);
		if(list.size()>0) {
			response.setSucessmessage("Details are Sucessfully fetched !!");
			System.out.println(response.getMealDetailsList());
		}else {
			response.setFailuremessage("Details are not Sucessfully fetched !!!");
		}
		return response;
	}

	   // Associate meal details with user
	
	public ResponseHandle associationUserWithMealDetails(Users user) {
		
            Users insertedUser= mealDetailBo.associationUserWithMealDetails(user);
	        long id=insertedUser.getId();	        
	        if(id>0) {
	        response.setSucessmessage("User Details are Addded Sucessfully !!!");
	        response.setId(id);
	        }else {
	        	response.setFailuremessage("User Details are not Added Sucessfully !!!");
	        }
		
		
		return response;
	}

	//Fetch All
	
	public ResponseHandle fetchAll() {
		
		List<MealDetails> mealDetail=mealDetailBo.fetchAll();
		response.setMealDetailsList(mealDetail);
		if(mealDetail.size()>0) {
			response.setSucessmessage("Sucessfully Details are Fetched !!!");
		}else {
			response.setFailuremessage("Failure to Fetch the Details !!!");
		}
		return response;
	}

	//find Custom MealDetails
	
	public ResponseHandle findCustomMealDetails() {
	List<MealDetailsProjection> mealDetail=mealDetailBo.findCustomMealDetails();
    if(mealDetail.size()>0) {
    	response.setMealDetailProjection(mealDetail);
    	response.setSucessmessage("Details are Sucessfully Fetched !!!");
    }else {
    	response.setFailuremessage("Details are not Fetched Sucessfully !!!");
    }
		return response;
	}

	//Named Queery With Aggregate
	
	public ResponseHandle avgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) {
		double avgCalorie=mealDetailBo.avgCaloriesByDateRange(startDate,endDate);
		if(avgCalorie>0) {
			response.setSucessmessage("Average Calories is Sucessfully Fetched !!!");
			response.setCalories(avgCalorie);
		}else {
			response.setFailuremessage("Average Calories is not fetched Sucessfully !!!");
		}
		return response;
	}

	//Named Querry with Clauses
	
	public ResponseHandle findAvgCaloriesAndTotalQuantity(double calorie) {
       List<MealSummary> mealSummary=mealDetailBo.findAvgCaloriesAndTotalQuantity(calorie);
       if(mealSummary.size()>0) {
    	   response.setMealSummary(mealSummary);
    	   response.setSucessmessage("Sucessfully mealSummary Fetched !!!");
       }else {
    	   response.setFailuremessage("Failed to fetch mealSummary !!!");
       }
       return response;
	}




}


