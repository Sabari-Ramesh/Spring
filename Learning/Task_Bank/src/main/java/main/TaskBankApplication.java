package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.entity.Account;
import main.entity.User;
import main.service.AccountService;
import main.service.UserService;

@SpringBootApplication
public class TaskBankApplication {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TaskBankApplication.class, args);

		TaskBankApplication application = context.getBean(TaskBankApplication.class);
		
		boolean flag=true;
		Scanner scanner=new Scanner(System.in);
		
		while(flag) {
			
			System.out.println("\t\t\t MENU");
			System.out.println("1.insert User\n2.insert Account\n3.Amount Transfer\n4.exit");
			System.out.println("Enter Your Option :");
		    
			int option=scanner.nextInt();
			
			switch(option) {
			
			case 1:{
				application.insertUser();
				break;
			}
			case 2:{
				application.insertAccount();
				break;
			}
			case 3:{
				application.amountTransfer();
				break;
			}
			case 4:{
				flag=false;
				break;
			}
			default:{
				System.out.println("Enter the Valid Option");
			}
			}
			
			
		}

		
	}

	//Amount Transfer
	
	private void amountTransfer() {
		System.out.println("Enter Sender Account Number :");
		long senderAccountNo=sc.nextLong();
		
		System.out.println("Enter Receiver Account Number :");
		long receiverAccountNo=sc.nextLong();
		
		System.out.println("Enter the Account to Transfer :");
		double amount=sc.nextDouble();
		
		try {
			accountService.transferAmount(senderAccountNo,receiverAccountNo,amount);
		} catch (InsufficientBalance e) {
			System.out.println(e.getMessage());
		}
		catch(InValidAccountNumber e) {
			System.out.println(e.getMessage());	
		}
	}


	//Insert Account
	
	private void insertAccount() {
		Account accountObj=new Account();
		
		System.out.println("Enter the Account Number :");
		accountObj.setAccountNumber(sc.nextLong());
		
		System.out.println("Enter the Branch Code :");
		accountObj.setBranchCode(sc.next());
		
		System.out.println("Enter the Balance :");
		accountObj.setBalance(sc.nextDouble());
		
		System.out.println("Enter the user Id");
		User userObj=new User();
		userObj.setId(sc.nextLong());
		accountObj.setUser(userObj);
		
		accountService.insertAccount(accountObj);
		
	}

	// Insert User

	private void insertUser() {

		User userobj = new User();

		System.out.println("Enter User Name :");
		userobj.setName(sc.nextLine());

		System.out.print("Enter your Date of Birth (yyyy-MM-dd): ");
		String input = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(input, formatter);
		userobj.setDob(date);
		
		
		System.out.println("Enter the email:");
		userobj.setEmail(sc.next());
		
		
		System.out.println("Enter the Password :");
		userobj.setPassword(sc.next());
		
		userService.insertUser(userobj);

	}

}
