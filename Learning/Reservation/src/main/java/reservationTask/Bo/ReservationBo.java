package reservationTask.Bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reservationTask.DAO.MovieRepository;
import reservationTask.DAO.PaymentRepository;
import reservationTask.DAO.ReservationRepository;
import reservationTask.DAO.TicketTypeRepository;
import reservationTask.DAO.UsersRepository;
import reservationTask.Exception.InvaildPaymentStatus;
import reservationTask.Exception.InvaildTicketType;
import reservationTask.Exception.InvalidMoviename;
import reservationTask.Exception.TicketNotAvailable;
import reservationTask.Exception.UserNotFound;
import reservationTask.entity.Movie;
import reservationTask.entity.Payment;
import reservationTask.entity.Reservation;
import reservationTask.entity.TicketType;
import reservationTask.entity.Users;

@Component
public class ReservationBo {

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private TicketTypeRepository ticketRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private UsersRepository userRepo;

	/*
	 * public void reserveTicket(long userId, String movieName, String ticketType,
	 * int numberOfTicket, String paymentStatus) throws InvalidMoviename,
	 * InvaildTicketType, TicketNotAvailable, InvaildPaymentStatus {
	 * 
	 * // Find Movie Id Movie movie = getMovie(movieName);
	 * System.out.println(movie.getMovieId());
	 * 
	 * // Find Ticket Type String ticketclass = vaildTicketType(ticketType);
	 * TicketType selectedTicket =
	 * ticketRepo.findByfindByTypeAndMovieId(ticketclass, movie.getMovieId());
	 * 
	 * double amount = selectedTicket.getPrice() * numberOfTicket;
	 * 
	 * // Payment Payment payment = new Payment(); payment.setAmount(amount); String
	 * status = vaildstatus(paymentStatus); payment.setStatus(status); // Set
	 * payment status paymentRepo.save(payment); // Save payment first
	 * 
	 * // Check ticket is available or not
	 * checkAvailability(selectedTicket.getAvailableSeats(), numberOfTicket); double
	 * price = selectedTicket.getPrice() * numberOfTicket;
	 * 
	 * // Generate the Seat number List<String> seatNumbers =
	 * generateSeatNumbers(numberOfTicket); String seatNumbersString =
	 * String.join(", ", seatNumbers);
	 * 
	 * // Create reservation
	 * 
	 * Reservation reservation = new Reservation(); reservation.setUserId(userId);
	 * reservation.setQuantity(numberOfTicket);
	 * reservation.setSeatNumbers(seatNumbersString); reservation.setMovie(movie);
	 * reservation.setPayment(payment); reservationRepo.save(reservation); // Save
	 * reservation
	 * 
	 * selectedTicket.setAvailableSeats(selectedTicket.getAvailableSeats() -
	 * numberOfTicket); ticketRepo.save(selectedTicket);
	 * 
	 * }
	 */

	public String reserveTicket(long userId, String movieName, String ticketType, int numberOfTicket,String paymentStatus) 
			throws InvalidMoviename, InvaildTicketType, TicketNotAvailable, InvaildPaymentStatus, UserNotFound {
		
		//Find Movie by Name
		Movie movie = getMovie(movieName);
		
		//Validate and Fetch TicketType Entity
		String ticketClass = vaildTicketType(ticketType);
		TicketType selectedTicket = ticketRepo.findByfindByTypeAndMovieId(ticketClass, movie.getMovieId());
		
		//Check Ticket Availability
		checkAvailability(selectedTicket.getAvailableSeats(), numberOfTicket);

        //Generate Seat Numbers
		List<String> seatNumbers = generateSeatNumbers(numberOfTicket);
		String seatNumbersString = String.join(", ", seatNumbers);

        //Create Reservation
		Reservation reservation = new Reservation();
		Users user=validuser(userId);
		reservation.setUser(user);
		reservation.setQuantity(numberOfTicket);
		reservation.setSeatNumbers(seatNumbersString);
		reservation.setMovie(movie);

        // Save Reservation First
		reservationRepo.save(reservation);

         // Deduct Tickets from Available Seats
		selectedTicket.setAvailableSeats(selectedTicket.getAvailableSeats() - numberOfTicket);
		ticketRepo.save(selectedTicket);

         // Calculate Payment Amount
		double amount = selectedTicket.getPrice() * numberOfTicket;

        // Process Payment
		Payment payment = new Payment();
		payment.setAmount(amount);

        // Validate Payment Status
		String status = vaildstatus(paymentStatus);
		payment.setStatus(status);

        // Save Payment
		paymentRepo.save(payment);
		reservation.setPayment(payment);
		reservationRepo.save(reservation); // Update reservation with payment info
		return seatNumbersString;
	}

	// Generate the Seat NUmber

	private List<String> generateSeatNumbers(int quantity) {
		List<String> seatNumbers = new ArrayList<>();
		char row = 'A'; // Starting row
		for (int i = 1; i <= quantity; i++) {
			seatNumbers.add(row + String.valueOf(i));
		}
		return seatNumbers;
	}

	// Validate

	private Movie getMovie(String movieName) throws InvalidMoviename {

		List<Movie> movies = movieRepo.findAll();
		Movie movieobj = null;

		for (Movie movie : movies) {
			if (movie.getName().equalsIgnoreCase(movieName)) {
				movieobj = movie;
				break;
			}
		} // forEach

		if (movieobj == null) {
			throw new InvalidMoviename("Movie Name Not Exit in database");
		}
		return movieobj;
	}

	// Check Ticket Type

	private String vaildTicketType(String ticketType) throws InvaildTicketType {
		if (ticketType.equalsIgnoreCase("Premium")) {
			return "Premium";
		} else if (ticketType.equalsIgnoreCase("Premium Pro")) {
			return "Premium Pro";
		} else if (ticketType.equalsIgnoreCase("Economy")) {
			return "Economy";
		} else {
			throw new InvaildTicketType("Invalid Ticket Type");
		}
	}

	// Check Availablability

	private void checkAvailability(int availableSeats, int numberOfTicket) throws TicketNotAvailable {
		if (numberOfTicket <= 0) {
			throw new TicketNotAvailable("Invaild Number of Ticket");
		}
		if (availableSeats < numberOfTicket) {
			throw new TicketNotAvailable("Sorry Tickets not Available");
		}
	}

	private String vaildstatus(String paymentStatus) throws InvaildPaymentStatus {
		if (paymentStatus.equalsIgnoreCase("SUCESS")) {
			return "SUCESS";
		} else if (paymentStatus.equalsIgnoreCase("FAILURE")) {
			throw new InvaildPaymentStatus("Sorry Payment Failure");
		} else {
			throw new InvaildPaymentStatus("Invalid Payment Status");
		}
	}
	
	//Valid User or not
	
	private Users validuser(long userId) throws UserNotFound {
	    Optional<Users> user = userRepo.findById(userId);
	    if (!user.isPresent()) {
	        throw new UserNotFound("User Not Exist in database");
	    }
	    return user.get();
	}
}
