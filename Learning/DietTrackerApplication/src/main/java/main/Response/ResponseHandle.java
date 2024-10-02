package main.Response;

import java.util.List;

import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.entity.MealDetails;

@Component
public class ResponseHandle {
	
	private String Sucessmessage;
	private String failuremessage;
	private long id;
	private List<MealSummary> mealSummary;
	public List<MealSummary> getMealSummary() {
		return mealSummary;
	}
	public void setMealSummary(List<MealSummary> mealSummary) {
		this.mealSummary = mealSummary;
	}
	private MealDetails mealDetail;
	private List<MealDetails> mealDetailsList;
	private List<MealDetailsProjection> mealDetailProjection;
	private double calories;
	
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public List<MealDetailsProjection> getMealDetailProjection() {
		return mealDetailProjection;
	}
	public void setMealDetailProjection(List<MealDetailsProjection> mealDetailProjection) {
		this.mealDetailProjection = mealDetailProjection;
	}
	public List<MealDetails> getMealDetailsList() {
		return mealDetailsList;
	}
	public void setMealDetailsList(List<MealDetails> mealDetailsList) {
		this.mealDetailsList = mealDetailsList;
	}
	public String getSucessmessage() {
		return Sucessmessage;
	}
	public void setSucessmessage(String sucessmessage) {
		Sucessmessage = sucessmessage;
	}
	public String getFailuremessage() {
		return failuremessage;
	}
	public void setFailuremessage(String failuremessage) {
		this.failuremessage = failuremessage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MealDetails getMealDetail() {
		return mealDetail;
	}
	public void setMealDetail(MealDetails mealDetail) {
		this.mealDetail = mealDetail;
	}
	
}
