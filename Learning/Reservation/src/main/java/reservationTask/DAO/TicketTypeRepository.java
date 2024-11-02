package reservationTask.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import reservationTask.entity.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType,Long>{

	@Query("SELECT t FROM TicketType t WHERE t.type = :ticketType AND t.movie.movieId = :movieId")
	TicketType findByfindByTypeAndMovieId(String ticketType, Long movieId);

}
