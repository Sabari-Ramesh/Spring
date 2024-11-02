package reservationTask.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import reservationTask.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
