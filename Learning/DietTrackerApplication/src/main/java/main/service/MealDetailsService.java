package main.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.Bo.MealDetailsBo;
import main.DAO.UserRepo;
import main.DTO.MealDetailsDTO;
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
	@Transactional
	public ResponseHandle insertMealDetail(MealDetails mealDetail) {
		
		
		MealDetails insertedDetail = mealDetailBo.insertMealDetails(mealDetail);

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
	        System.out.println(user);
	        System.out.println(user.getMealDetails());
	        
	        if(id>0) {
	        response.setSucessmessage("User Details are Addded Sucessfully !!!");
	        response.setId(id);
	        }else {
	        	response.setFailuremessage("User Details are not Added Sucessfully !!!");
	        }
		
		
		return response;
	}


}


