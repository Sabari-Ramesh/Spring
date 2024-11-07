package main.Repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.entity.MealDetails;

public interface MealDetailsRepository extends JpaRepository<MealDetails, Long> {
	@Query("SELECT m FROM MealDetails m WHERE m.user.id = :userId AND m.mealDate = :mealDate")
    List<MealDetails> findByUserIdAndMealDate(Long userId, LocalDate mealDate);
}

