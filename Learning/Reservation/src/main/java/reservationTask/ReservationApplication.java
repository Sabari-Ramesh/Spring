package reservationTask;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import reservationTask.Exception.InvaildPaymentStatus;
import reservationTask.Exception.InvaildTicketType;
import reservationTask.Exception.InvalidMoviename;
import reservationTask.Exception.TicketNotAvailable;
import reservationTask.Exception.UserNotFound;
import reservationTask.Service.ReservationService;

@SpringBootApplication
public class ReservationApplication {
	
	@Autowired
	private ReservationService reservationService;
	
	Scanner sc=new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(ReservationApplication.class, args);
		
		ReservationApplication ReservationObj=context.getBean(ReservationApplication.class);
		boolean flag=true;
		while(flag) {
			ReservationObj.reserveTicket();
		}
	}

	private void reserveTicket() {
		
		System.out.println("Enter User Id :");
		int userId=sc.nextInt();
		
		sc.nextLine();
		System.out.println("Enter MovieName :");
		String movieName=sc.nextLine();
		
		
		System.out.println("Enter the Ticket Type :");
		String ticketType=sc.nextLine();
		
		System.out.println("Enter the Number of Tickets :");
		int quantity=sc.nextInt();
		
		System.out.println("Enter the Payment Status :");
		String paymentStatus=sc.next();
		
		try {
			String generatedSeatNumber=reservationService.reserveTicket(userId,movieName,ticketType,quantity,paymentStatus);
			System.out.println(generatedSeatNumber);
		} catch (InvalidMoviename e) {
			System.err.println(e.getMessage());
		} catch (InvaildTicketType e) {
			System.err.println(e.getMessage());
		} catch (TicketNotAvailable e) {
			System.err.println(e.getMessage());
		} catch (InvaildPaymentStatus e) {
			System.err.println(e.getMessage());
		} catch (UserNotFound e) {
			System.err.println(e.getMessage());
		}
	}

}
