package reservationTask.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import reservationTask.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
