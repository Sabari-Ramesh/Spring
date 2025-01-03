package dietTrackerApplication.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dietTrackerApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	//Custom With Aggregate
	@Query("SELECT u FROM User u WHERE u.dateCreated BETWEEN :startDate AND :endDate")
    List<User> findUsersByRegistrationDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	//Named With Clauses
	@Query(name = "User.countUsersByCity")
    List<UserCountByCity> countUsersByCity();
	
	
	 @Query("SELECT DISTINCT u.userId AS userId, u.userName AS userName FROM User u JOIN u.mealDetails md")
    List<UsersWithMealDetails> findUsersWithMealDetails();

}
