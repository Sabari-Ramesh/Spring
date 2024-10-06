package main.BO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.DAO.MealDetailsProjection;
import main.DAO.MealDetailsRepository;
import main.entity.MealDetails;

@Component
public class MealDetailsBO {
	
	@Autowired
	private MealDetailsRepository mealDetailRepo;
	
	//2.Insert
	
	public void insertDetails(MealDetails mealDetail) {
		mealDetailRepo.save(mealDetail);
		System.out.println("hi");
	}
	
	//3. Find By Id

	public MealDetails fetchById(long id) {
		MealDetails detail=mealDetailRepo.findById(id).get();
		return detail;
	}
	
	//4.Find All

	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetail=mealDetailRepo.findAll();
		return mealDetail;
	}

	//5.Find By User Id( Custom Querry )
	
	public List<MealDetails> fetchByUserId(long id) {
		List<MealDetails> mealDetail=mealDetailRepo.findMealDetailsByUserId(id);
		return mealDetail;
	}
	
	//6.Find By min and max Quantity ( Named Querry )

	public List<MealDetails> findByQuantityRange(double min, double max) {
		List<MealDetails> mealDetail=mealDetailRepo.findByQuantityRange(min,max);
		return mealDetail;
	}
	
	//7.Custom querry with Projection

	public List<MealDetailsProjection> findCustomMealDetails() {
		List<MealDetailsProjection> mealDetailProjection=mealDetailRepo.findCustomMealDetails();
		return mealDetailProjection;
	}
	
	//8.Custom Query with Aggregate function
	
	public double findAvgCaloriesByDateRange(LocalDate startDate,LocalDate endDate) {
	Double avgCalorie = mealDetailRepo.findAvgCaloriesByDateRange(startDate, endDate);
	if (avgCalorie == null) {
		 return 0.0;
	}
	return avgCalorie;
	}

}
