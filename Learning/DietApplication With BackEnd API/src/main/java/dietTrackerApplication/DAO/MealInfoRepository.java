package dietTrackerApplication.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dietTrackerApplication.entity.MealInfo;

@Repository
public interface MealInfoRepository extends JpaRepository<MealInfo,Integer>{
	
	  @Query("SELECT m.mealType FROM MealInfo m WHERE m.meal = :meal")
	    Integer findMealTypeByMeal(String meal);
	  
}
