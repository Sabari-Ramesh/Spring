package main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.BO.MealDetailsBO;
import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.entity.MealDetails;

@Service
public class MealDetailService {
	
	@Autowired
	MealDetailsBO mealDetailBo;

	//2.Insert
	
	public void insertDetails(MealDetails mealDetail) {
		mealDetailBo.insertDetails(mealDetail);
	}

	//3.Find By Id
	
	public MealDetails fetchById(long id) {
		MealDetails detail=mealDetailBo.fetchById(id);
		return detail;
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
