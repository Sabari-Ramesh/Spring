package main.Bo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.DAO.MealDetailsRepository;
import main.DAO.UserRepo;
import main.entity.MealDetails;
import main.entity.Users;

@Component
public class MealDetailsBo {

	@Autowired
	private MealDetailsRepository mealDetailRepo;
	
	@Autowired
	private UserRepo userRepo;

// Insert 	

	public void insertDetail(MealDetails mealDetails) {
		mealDetailRepo.save(mealDetails);
	}

//Find By Id	

	public void findByMealId(long id) {
		Optional<MealDetails> mealDetailsOpt = mealDetailRepo.findById(id);
		MealDetails mealDetails = mealDetailsOpt.get();
		System.out.println("Meal found: " + mealDetails);

	}

//Update 	

	public void update(long id) {
		MealDetails mealDetail = mealDetailRepo.findById(id).get();
		mealDetail.setFoodName("Jamanun");
		mealDetailRepo.save(mealDetail);
	}

// Delete	

	public void delete(long id) {
		MealDetails mealDetail = mealDetailRepo.findById(id).get();
		System.out.println(mealDetail);
		mealDetailRepo.delete(mealDetail);
	}

	public void MealDetailsWithUsers(Users user) {
		userRepo.save(user);
	}

}
