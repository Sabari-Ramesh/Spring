package main.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import main.BO.MealDetailsBO;
import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.Exception.DateException;
import main.Exception.InvalidNumberException;
import main.Exception.MealIdNotFoundException;
import main.Exception.MealTypeException;
import main.Exception.NameException;
import main.Exception.QuantityException;
import main.Exception.UserNotFound;
import main.entity.MealDetails;
import main.response.Response;

@Service
public class MealDetailService {

	@Autowired
	MealDetailsBO mealDetailBo;

	@Autowired
	private Response response;

	// 2.Insert

	@Transactional
	public Response insertDetails(MealDetails mealDetail) throws DataIntegrityViolationException, UserNotFound,
			DateException, QuantityException, NameException, MealTypeException {
		MealDetails detail = mealDetailBo.insertDetails(mealDetail);
		if (detail != null) {
			response.setSucessMsg("Details are Added Sucessfully !!!");
			response.setId(detail.getMealId());
		} else {
			response.setFailureMsg("Details are not Added Sucessfully !!!");
		}
		return response;
	}

	// 3.Find By Id

	@Transactional
	public Response fetchById(long id) throws MealIdNotFoundException {

		MealDetails fetchedDetail = mealDetailBo.fetchById(id);

		if (fetchedDetail != null) {
			response.setSucessMsg("Details are Sucessfully fetched..");
			response.setMealDetail(fetchedDetail);
		} else {
			response.setFailureMsg("Details are not fetched Sucessfully");
		}

		return response;
	}

	// 4.Find All

	@Transactional
	public Response fetchAll() {
		List<MealDetails> mealDetail = mealDetailBo.fetchAll();
		response.setMealDetailsList(mealDetail);
		if (mealDetail.size() > 0) {
			response.setSucessMsg("Sucessfully Details are Fetched !!!");
		} else {
			response.setFailureMsg("Failure to Fetch the Details !!!");
		}
		return response;
	}

	// 5.Custom Querry

	@Transactional
	public Response fetchByUserId(long id) throws UserNotFound {
		List<MealDetails> list = mealDetailBo.fetchByUserId(id);
		response.setMealDetailsList(list);
		if (list.size() > 0) {
			response.setSucessMsg("Details are Sucessfully fetched !!");
		} else {
			response.setFailureMsg("No Details Available ");
		}
		return response;
	}

	// 6.Named Querry

	@Transactional
	public Response findByQuantityRange(double min, double max) throws InvalidNumberException {
		List<MealDetails> mealDetailList = mealDetailBo.findByQuantityRange(min, max);
		if (mealDetailList.isEmpty()) {
			response.setMealDetails(mealDetailList);
			response.setSucessMsg("No Details Available");
		} else if (!mealDetailList.isEmpty()) {
			response.setSucessMsg("Details Fetched Sucessfully !!!");
			response.setMealDetails(mealDetailList);
		} else {
			response.setFailureMsg("Failure to fetch details !!!");
		}
		return response;
	}

	// 7.Custom with Projection

	@Transactional
	public Response findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection = mealDetailBo.findCustomMealDetails();
		if (mealDetailProjection.size() > 0) {
			response.setMealDetailProjection(mealDetailProjection);
			response.setSucessMsg("Details are Sucessfully Fetched !!!");
		} else {
			response.setFailureMsg("Details are not Fetched Sucessfully !!!");
		}
		return response;
	}

	// 8.Custom Query with Aggregate
	@Transactional
	public Response findAvgCaloriesByDateRange(LocalDate startDate, LocalDate endDate) throws DateException {
		double avgCalorie = mealDetailBo.findAvgCaloriesByDateRange(startDate, endDate);
		if (avgCalorie >= 0) {
			response.setSucessMsg("Average Calories is Sucessfully Fetched !!!");
			response.setAvg(avgCalorie);
		} else {
			response.setFailureMsg("Average Calories is not fetched Sucessfully !!!");
		}
		return response;
	}

	// 9.Named with Clauses

	@Transactional
	public Response avgCaloriesAndTotalQuantity(double calorie) throws QuantityException {
		
		List<MealSummary> mealSummary=new ArrayList<>();
		 mealSummary = mealDetailBo.avgCaloriesAndTotalQuantity(calorie);
		if (mealSummary.isEmpty()) {
			response.setMealSummary(mealSummary);
			response.setSucessMsg("No Details Available");
		}else if(!mealSummary.isEmpty()) {
			response.setMealSummary(mealSummary);
			response.setSucessMsg("Sucessfully mealSummary Fetched !!!");
		}else {
			response.setFailureMsg("Failed to fetch mealSummary !!!");
		}
		return response;
		
	}
}
