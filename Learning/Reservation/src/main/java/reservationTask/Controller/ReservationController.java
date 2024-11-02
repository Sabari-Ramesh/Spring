package reservationTask.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reservationTask.Exception.InvaildPaymentStatus;
import reservationTask.Exception.InvaildTicketType;
import reservationTask.Exception.InvalidMoviename;
import reservationTask.Exception.TicketNotAvailable;
import reservationTask.Exception.UserNotFound;
import reservationTask.Service.ReservationService;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/reserve-tickets")
	private ResponseEntity<?> reserveTicket( @RequestParam long userId,@RequestParam String movieName,@RequestParam String ticketType,
            @RequestParam int numberOfTickets,@RequestParam String paymentStatus ){
		
			String generatedSeatNumber;
			try {
				generatedSeatNumber = reservationService.reserveTicket(userId, movieName, ticketType, numberOfTickets, paymentStatus);
				return ResponseEntity.ok().body("Payment Sucessful and your Seat Number are"+generatedSeatNumber);
			} catch (InvalidMoviename e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (InvaildTicketType e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (TicketNotAvailable e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (InvaildPaymentStatus e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (UserNotFound e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
	}
	
}
