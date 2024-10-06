package main.DAO;

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

}
