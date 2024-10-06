package main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import main.BO.MealDetailsBO;
import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.Exception.DateException;
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

	//2.Insert
	
	public Response insertDetails(MealDetails mealDetail) throws DataIntegrityViolationException, UserNotFound, DateException, QuantityException, NameException, MealTypeException {
		MealDetails detail=mealDetailBo.insertDetails(mealDetail);
		if(detail!=null) {
			response.setSucessMsg("Details are Added Sucessfully !!!");
			response.setId(detail.getMealId());
		}else {
			response.setFailureMsg("Details are not Added Sucessfully !!!");
		}
		return response;
	}

	//3.Find By Id
	
	public Response fetchById(long id) throws MealIdNotFoundException{
		
		MealDetails fetchedDetail = mealDetailBo.fetchById(id);

		if (fetchedDetail != null) {
			response.setSucessMsg("Details are Sucessfully fetched..");
			response.setMealDetail(fetchedDetail);
		} else {
			response.setFailureMsg("Details are not fetched Sucessfully");
		}

		return response;
	}

	//4.Find All
	
	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetailList=mealDetailBo.fetchAll();
		return mealDetailList;
	}
	
	//5.Custom Querry 
	
	public List<MealDetails> fetchByUserId(long id) {
		List<MealDetails> mealDetailList=mealDetailBo.fetchByUserId(id);
		return mealDetailList;
	}
	
	//6.Named Querry

	public List<MealDetails> findByQuantityRange(double min,double max) {
		List<MealDetails> mealDetailList=mealDetailBo.findByQuantityRange(min,max);
		return mealDetailList;
	}
	
	//7.Custom with Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection=mealDetailBo.findCustomMealDetails();
		return mealDetailProjection;
	}
	
	//8.Custom Query with Aggregate
	public double findAvgCaloriesByDateRange(LocalDate startDate,LocalDate endDate) {
		double avgCalorie=mealDetailBo.findAvgCaloriesByDateRange(startDate, endDate);
		return avgCalorie;
	}

	//9.Named with Clauses
	
	public List<MealSummary> avgCaloriesAndTotalQuantity(double calorieThreshold) {
		List<MealSummary> list=mealDetailBo.avgCaloriesAndTotalQuantity(calorieThreshold);
		return list;
	}
}
