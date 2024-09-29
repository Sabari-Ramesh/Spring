package main.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.entity.MealDetails;

@Repository
public interface MealDetailsRepository extends JpaRepository<MealDetails,Long>{

}
