package main.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entity.MealDetails;

@Repository
public interface MealDetailsRepository extends JpaRepository<MealDetails,Long>{

	//5.Find By User Id( Custom Querry )
	
	@Query("SELECT md FROM MealDetails md JOIN FETCH md.user u WHERE u.id = :userId")
    List<MealDetails> findMealDetailsByUserId(@Param("userId") Long userId);
	
	//6.Find Mealdetails for Give Quantity ( Named Querry)
	
	 @Query(name = "MealDetails.findByQuantityRange")
	 List<MealDetails> findByQuantityRange(@Param("minQuantity") double minQuantity, @Param("maxQuantity") double maxQuantity);

}
