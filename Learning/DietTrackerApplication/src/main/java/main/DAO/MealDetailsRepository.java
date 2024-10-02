package main.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.MealDetails;

@Repository
public interface MealDetailsRepository extends JpaRepository<MealDetails,Long>{

	 @Query("SELECT md FROM MealDetails md JOIN FETCH md.user u WHERE u.id = :userId")
	    List<MealDetails> findMealDetailsByUserId(@Param("userId") Long userId);
	
	 
	 @Query("SELECT m.foodName AS foodName, m.quantity AS quantity, m.id AS id, m.mealDate AS date FROM MealDetails m")
	    List<MealDetailsProjection> findCustomMealDetails();

	 @Query(name = "MealDetails.avgCaloriesByDateRange")
	 Double findAvgCaloriesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	 
	  @Query(name = "MealDetails.avgCaloriesAndTotalQuantity")
	    List<MealSummary> findAvgCaloriesAndTotalQuantity(@Param("calorieThreshold") double calorieThreshold);
	
}
