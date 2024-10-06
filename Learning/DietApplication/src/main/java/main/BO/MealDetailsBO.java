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
	
	public void insertDetails(MealDetails mealDetail) {
		mealDetailRepo.save(mealDetail);
		System.out.println("hi");
	}

	public MealDetails fetchById(long id) {
		MealDetails detail=mealDetailRepo.findById(id).get();
		return detail;
	}

	public List<MealDetails> fetchAll() {
		List<MealDetails> mealDetail=mealDetailRepo.findAll();
		return mealDetail;
	}

}
