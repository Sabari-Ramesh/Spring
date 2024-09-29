package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Bo.MealDetailsBo;
import main.entity.MealDetails;
import main.entity.Users;

@Service
public class MealDetailsService {

	@Autowired
	private MealDetailsBo mealDetailBo;

	// Insert

	public void insertMealDetail(MealDetails mealDetail) {
		mealDetailBo.insertDetail(mealDetail);
	}

	// Find By Id

	public void findByMealId(long id) {
		mealDetailBo.findByMealId(id);
	}

	// Update

	public void update(long id) {
		mealDetailBo.update(id);
	}

	// Delete

	public void delete(long id) {
		mealDetailBo.delete(id);
	}

	// Association
	public void MealDetailsWithUsers(Users user) {
		mealDetailBo. MealDetailsWithUsers(user);
	}

}
