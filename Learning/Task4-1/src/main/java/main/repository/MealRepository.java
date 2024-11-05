package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.MealDetails;

public interface MealRepository extends JpaRepository<MealDetails, Long> {

}
