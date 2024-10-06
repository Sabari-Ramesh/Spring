package main.response;

import java.util.List;

import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.entity.MealDetails;

@Component
public class Response {
	
	private String sucessMsg;
	private String failureMsg;
	private long id;
	private double avg;
	private MealDetails mealDetail;
	private List<MealDetails> mealDetailsList;
	private List<MealDetailsProjection> mealDetailProjection;
	private List<MealSummary> mealSummary;
	
	
	//getter And Setter
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public List<MealDetails> getMealDetailDto() {
		return mealDetailsList;
	}
	public void setMealDetails(List<MealDetails> mealDetailDto) {
		this.mealDetailsList = mealDetailDto;
	}
	public List<MealDetailsProjection> getMealDetailProjection() {
		return mealDetailProjection;
	}
	public void setMealDetailProjection(List<MealDetailsProjection> mealDetailProjection) {
		this.mealDetailProjection = mealDetailProjection;
	}
	public List<MealSummary> getMealSummary() {
		return mealSummary;
	}
	public void setMealSummary(List<MealSummary> mealSummary) {
		this.mealSummary = mealSummary;
	}
	public String getSucessMsg() {
		return sucessMsg;
	}
	public void setSucessMsg(String sucessMsg) {
		this.sucessMsg = sucessMsg;
	}
	public String getFailureMsg() {
		return failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	public MealDetails getMealDetail() {
		return mealDetail;
	}
	public void setMealDetail(MealDetails mealDetail) {
		this.mealDetail = mealDetail;
	}
	public List<MealDetails> getMealDetailsList() {
		return mealDetailsList;
	}
	public void setMealDetailsList(List<MealDetails> mealDetailsList) {
		this.mealDetailsList = mealDetailsList;
	}
	
	//To String
	
	@Override
	public String toString() {
		return "Response [SucessMsg=" + sucessMsg + ", failureMsg=" + failureMsg + ", id=" + id + ", avg=" + avg
				+ ", mealDetail=" + mealDetail + ", mealDetailsList=" + mealDetailsList + ", mealDetailProjection="
				+ mealDetailProjection + ", mealSummary=" + mealSummary + "]";
	}
	
}
