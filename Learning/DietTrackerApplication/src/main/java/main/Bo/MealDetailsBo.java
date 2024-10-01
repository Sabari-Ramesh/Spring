package main.Bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
	
	@Transactional
	public MealDetails insertMealDetails(MealDetails mealDetail) {
//	    Users user=userRepo.findById(mealDetail.getUser().getId()).get();
//	    mealDetail.setUser(user);
		
	    return mealDetailRepo.save(mealDetail);
	}

	

	// Find BY Id

	public MealDetails findByMealId(MealDetails mealDetail) {
		long id = mealDetail.getId();
		MealDetails fetchedDetail = mealDetailRepo.findById(id).get();
		return fetchedDetail;
	}

	// Update

	
	public MealDetails updateMealDetail(MealDetails mealDetail) {
		long id=mealDetail.getId();
		MealDetails updateDetail=mealDetailRepo.findById(id).get();
		updateDetail.setFoodName(mealDetail.getFoodName());
		updateDetail=mealDetailRepo.save(updateDetail);
		return updateDetail;
	}

	//Delete
	
	public boolean deleteId(MealDetails mealDetail) {
		
		System.out.println("Bo "+mealDetail.getId());
		long id=mealDetail.getId();
		mealDetailRepo.deleteById(id);
		return true;
		
	}

	//Custom Querry Find By UserID :
	
	public List<MealDetails> findMealDetailsByUserId(long id) {
		List<MealDetails> list=mealDetailRepo.findMealDetailsByUserId(id);
		return list;
	}

	//User Association
	
	public Users associationUserWithMealDetails(Users user) {
	Users insertedUser=userRepo.save(user);	
	return insertedUser;
	}

}
