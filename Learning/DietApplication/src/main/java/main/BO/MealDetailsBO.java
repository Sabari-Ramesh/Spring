package main.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	//Find By User Id
	
	public List<MealDetails> fetchByUserId(long id) {
		List<MealDetails> mealDetail=mealDetailRepo.findMealDetailsByUserId(id);
		return mealDetail;
	}

}
