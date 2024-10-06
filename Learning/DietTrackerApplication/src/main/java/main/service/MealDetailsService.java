package main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.Bo.MealDetailsBo;
import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.DTO.UsersDTO;
import main.Exceptions.DateException;
import main.Exceptions.FoodNameException;
import main.Exceptions.InValidCityId;
import main.Exceptions.InValidEmailException;
import main.Exceptions.MealIdNotFoundException;
import main.Exceptions.MealTypeException;
import main.Exceptions.MobileException;
import main.Exceptions.QuantityException;
import main.Exceptions.UserNotFound;
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
	
	
	Logger log=Logger.getLogger(MealDetailsService.class);

	// 2. Insert

	@Transactional
	public ResponseHandle insertMealDetail(MealDetails mealDetail) throws UserNotFound, DataIntegrityViolationException,
			DateException, QuantityException, FoodNameException, MealTypeException {
		log.info("insert Method is triggred...");
		MealDetails insertedDetail = mealDetailBo.insertMealDetails(mealDetail);
		long id = insertedDetail.getId();

		if (id > 0) {
			response.setSucessMessage("Details are Added Successfully");
			response.setMealDetail(insertedDetail);
		} else {
			response.setFailuremessage("Failure to Add Details " + id);
		}

		response.setId(id);
		return response;

	}

	// 3. FindById

	@Transactional
	public ResponseHandle findByMealId(MealDetails mealDetail) throws MealIdNotFoundException {
		
		log.info("Find By method is Triggred....");

		MealDetails fetchedDetail = mealDetailBo.findByMealId(mealDetail);

		if (fetchedDetail != null) {
			response.setSucessMessage("Details are Sucessfully fetched..");
			response.setMealDetail(fetchedDetail);
		} else {
			response.setFailuremessage("Details are not fetched Sucessfully");
		}

		return response;
	}

	// 4. Fetch All

	public ResponseHandle fetchAll() {
		
		log.info("Fetch All method is triggred...");
		
		List<MealDetails> mealDetail = mealDetailBo.fetchAll();
		response.setMealDetailsList(mealDetail);
		if (mealDetail.size() > 0) {
			response.setSucessMessage("Sucessfully Details are Fetched !!!");
		} else {
			response.setFailuremessage("Failure to Fetch the Details !!!");
		}
		return response;
	}

	// 5. Find MealDetail By UserId
	@Transactional
	public ResponseHandle findMealDetailsByUserId(long id) throws UserNotFound {
		
		log.info("Find MealDetail By User Id is triggred...");

		List<MealDetails> list = mealDetailBo.findMealDetailsByUserId(id);
		response.setMealDetailsList(list);
		if (list.size() > 0) {
			response.setSucessMessage("Details are Sucessfully fetched !!");
		} else {
			response.setFailuremessage("No Details Available ");
		}
		return response;
	}

	// 6.and 8. Named Querry With Aggregate

	public ResponseHandle avgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) throws DateException {
		
		log.info("Avg Calories By Date Range is triggred...");
		
		double avgCalorie = mealDetailBo.avgCaloriesByDateRange(startDate, endDate);
		if (avgCalorie >= 0) {
			response.setSucessMessage("Average Calories is Sucessfully Fetched !!!");
			response.setCalories(avgCalorie);
		} else {
			response.setFailuremessage("Average Calories is not fetched Sucessfully !!!");
		}
		return response;
		
	}

	// 7 Custom Querry with Projection

	public ResponseHandle findCustomMealDetails() {
		
		log.info("Custom Meal Detail Method is triggred...");
		
		List<MealDetailsProjection> mealDetail = mealDetailBo.findCustomMealDetails();
		if (mealDetail.size() > 0) {
			response.setMealDetailProjection(mealDetail);
			response.setSucessMessage("Details are Sucessfully Fetched !!!");
		} else {
			response.setFailuremessage("Details are not Fetched Sucessfully !!!");
		}
		return response;
		
	}

	// 9. Named Querry with Clauses

	public ResponseHandle findAvgCaloriesAndTotalQuantity(double calorie) throws QuantityException {
		
		log.info("Find Avg Calories And Total Quantity Method is triggred...");
		List<MealSummary> mealSummary=new ArrayList<>();
		 mealSummary = mealDetailBo.findAvgCaloriesAndTotalQuantity(calorie);
		if (mealSummary.isEmpty()) {
			response.setMealSummary(mealSummary);
			response.setSucessMessage("No Details Available");
		}else if(!mealSummary.isEmpty()) {
			response.setMealSummary(mealSummary);
			response.setSucessMessage("Sucessfully mealSummary Fetched !!!");
		}else {
			response.setFailuremessage("Failed to fetch mealSummary !!!");
		}
		return response;
		
	}

	// Update Detail

	@Transactional
	public ResponseHandle updateMealDetail(MealDetails mealDetail) throws MealIdNotFoundException, FoodNameException {
		
		log.info("Update Method is triggred...");

		MealDetails updateDetail = mealDetailBo.updateMealDetail(mealDetail);
		long id = updateDetail.getId();

		if (id > 0) {
			response.setSucessMessage("Details are Updated Sucessfully");
			response.setMealDetail(updateDetail);
		} else {
			response.setFailuremessage("Failure to Update Details " + id);
		}

		response.setId(id);

		return response;
	}

	// Delete Id

	public ResponseHandle deleteId(MealDetails mealDetail) throws MealIdNotFoundException {
		
		log.info("Delete Method is triggred...");

		boolean flag = mealDetailBo.deleteId(mealDetail);
		if (flag != false) {
			response.setSucessMessage(" Meal Detail is Successfully Deleted");
			response.setId(mealDetail.getId());
		} else {
			response.setFailuremessage("Meal detail are Failure to Delete ");
		}
		return response;
	}

	// Associate meal details with user

	public ResponseHandle associationUserWithMealDetails(Users user) throws InValidCityId, FoodNameException,
			InValidEmailException, DateException, QuantityException, MealTypeException, MobileException {
		
		log.info("Association Meal Detail With User Method is triggred...");

		Users insertedUser = mealDetailBo.associationUserWithMealDetails(user);
		long id = insertedUser.getId();
		if (id > 0) {
			response.setSucessMessage("User Details are Addded Sucessfully !!!");
			response.setId(id);
		} else {
			response.setFailuremessage("User Details are not Added Sucessfully !!!");
		}

		return response;
	}

}
