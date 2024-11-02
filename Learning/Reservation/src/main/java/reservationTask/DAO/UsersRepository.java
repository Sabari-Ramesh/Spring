package reservationTask.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reservationTask.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

}
