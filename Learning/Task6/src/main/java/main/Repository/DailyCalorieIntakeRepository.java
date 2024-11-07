package main.Repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main.entity.DailyCalorieIntake;

public interface DailyCalorieIntakeRepository extends JpaRepository<DailyCalorieIntake, Long> {
	@Query("SELECT d FROM DailyCalorieIntake d WHERE d.user.id = :userId AND d.date = :date")
    DailyCalorieIntake findByUserIdAndDate(Long userId, LocalDate date);
	@Query("SELECT d FROM DailyCalorieIntake d WHERE d.user.id = :userId AND d.date BETWEEN :startDate AND :endDate")
    List<DailyCalorieIntake> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
