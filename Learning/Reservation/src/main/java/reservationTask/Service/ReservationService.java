package reservationTask.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import reservationTask.Bo.ReservationBo;
import reservationTask.Exception.InvaildPaymentStatus;
import reservationTask.Exception.InvaildTicketType;
import reservationTask.Exception.InvalidMoviename;
import reservationTask.Exception.TicketNotAvailable;
import reservationTask.Exception.UserNotFound;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationBo reservationBo;

	@Transactional(rollbackOn={Exception.class,InvalidMoviename.class,InvaildTicketType.class,TicketNotAvailable.class,InvaildPaymentStatus.class,UserNotFound.class})
	public String reserveTicket(long userId, String movieName, String ticketType, int numberOfTickets,String paymentStatus)
			throws InvalidMoviename, InvaildTicketType, TicketNotAvailable, InvaildPaymentStatus, UserNotFound {
		
		String generatedSeatNumber=reservationBo.reserveTicket(userId,movieName,ticketType,numberOfTickets,paymentStatus);
		return generatedSeatNumber;
		
	}

}


