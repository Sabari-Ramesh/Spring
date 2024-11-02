package reservationTask.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import reservationTask.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long>{
	
}
