package main.response;

import java.util.List;

import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealSummary;
import main.DTO.MealDetailDTO;

@Component
public class Response {
	
	private String SucessMsg;
	private String failureMsg;
	private long id;
	private double avg;
	private List<MealDetailDTO> mealDetailDto;
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
	public List<MealDetailDTO> getMealDetailDto() {
		return mealDetailDto;
	}
	public void setMealDetailDto(List<MealDetailDTO> mealDetailDto) {
		this.mealDetailDto = mealDetailDto;
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
		return SucessMsg;
	}
	public void setSucessMsg(String sucessMsg) {
		SucessMsg = sucessMsg;
	}
	public String getFailureMsg() {
		return failureMsg;
	}
	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	
	//To String
	
	@Override
	public String toString() {
		return "Response [SucessMsg=" + SucessMsg + ", failureMsg=" + failureMsg + ", id=" + id + ", avg=" + avg
				+ ", mealDetailDto=" + mealDetailDto + ", mealDetailProjection=" + mealDetailProjection
				+ ", mealSummary=" + mealSummary + "]";
	}

	
}
