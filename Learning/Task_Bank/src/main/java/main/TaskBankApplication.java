package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import main.Exception.InVailDateException;
import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.PasswordException;
import main.Response.ResponseHandler;
import main.Response.UserwithBalance;
import main.entity.Account;
import main.entity.User;
import main.service.AccountService;
import main.service.UserService;

@SpringBootApplication
public class TaskBankApplication {

	@Autowired
	private ResponseHandler responseHandler;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TaskBankApplication.class, args);

		TaskBankApplication application = context.getBean(TaskBankApplication.class);

		boolean flag = true;
		Scanner scanner = new Scanner(System.in);

		while (flag) {

			System.out.println("\t\t\t MENU");
			System.out.println(
					"1.insert User\n2.insert Account\n3.Amount Transfer\n4.Bulk Insert\n5.Filter\n6.Filter in Sql\n7.Exit");
			System.out.println("Enter Your Option :");

			int option = scanner.nextInt();

			switch (option) {

			case 1: {
				application.insertUser();
				break;
			}
			case 2: {
				application.insertAccount();
				break;
			}
			case 3: {
				application.amountTransfer();
				break;
			}
			case 4: {
				application.bulkinsert();
				break;
			}
			case 5: {
				application.getMinAccountBalanceUser();
				break;
			}
			case 6: {
				application.getmaxAccountBalanceUser();
				break;
			}
			case 7: {
				flag = false;
				break;
			}
			default: {
				System.out.println("Enter the Valid Option");
			}
			}

		}

	}

	// Filter

	private void getMinAccountBalanceUser() {

		List<Account> minBalanceAccounts = accountService.getMinAccountBalanceUsers();

		if (!minBalanceAccounts.isEmpty()) {
			List<UserwithBalance> usersWithBalance = minBalanceAccounts.stream().map(account -> {
				UserwithBalance user = new UserwithBalance();
				user.setAccountNumber(account.getAccountNumber());
				user.setBalance(account.getBalance());
				user.setName(account.getUser().getName());
				user.setPhoneNumber(account.getUser().getPhonenumber());
				return user;
			}).collect(Collectors.toList());

			System.out.println(usersWithBalance);
		} else {
			System.out.println("No valid accounts found with positive balance.");
		}

	}

	private void getmaxAccountBalanceUser() {
		List<Account> maxBalanceAccounts = accountService.getMaxAccountBalanceUsers();

		if(!maxBalanceAccounts.isEmpty()) {
			System.out.println(maxBalanceAccounts);
		}else {
			System.out.println("No details are available");
		}

	}

	// bulk Insert

	private void bulkinsert() {

		List<User> users = new ArrayList<>();

		// Create multiple users with sample data
		for (int i = 1; i <= 100; i++) {
			User user = new User();
			user.setName("User" + i);
			user.setEmail("user" + i + "@examplegmail.com");
			user.setPassword("Passw@ord" + i);
			user.setDob(LocalDate.of(1990, 1, 1));
			users.add(user);
		}

		// Bulk save users along with their accounts
		try {
			userService.bulkInsert(users);
		} catch (NameException e) {
			e.getMessage();
		} catch (InvalidEmailException e) {
			e.getMessage();
		} catch (PasswordException e) {
			e.getMessage();
		} catch (InVailDateException e) {
			e.getMessage();
		}

	}

	// Amount Transfer

	private void amountTransfer() {
		System.out.println("Enter Sender Account Number :");
		long senderAccountNo = sc.nextLong();

		System.out.println("Enter Receiver Account Number :");
		long receiverAccountNo = sc.nextLong();

		System.out.println("Enter the Account to Transfer :");
		double amount = sc.nextDouble();

		try {

			responseHandler = accountService.getAccountByAccountNumber(receiverAccountNo);
			Account receiverAccount = responseHandler.getAccount();
			System.out.println("\t\t\tReceiverAccount Number :" + receiverAccount.getAccountNumber() + "\n\t\t\t"
					+ "Account HolderName :" + receiverAccount.getUser().getName() + "\n\t\t\t"
					+ "Account Holder Mobile Number :" + receiverAccount.getUser().getPhonenumber() + "\n\t\t\t"
					+ "Account Holder Email Id: " + receiverAccount.getUser().getEmail());
			System.out
					.println("Check Above Receiver Details are Correct or Not If Correct Enter Yes If Not Enter No : ");
			String flag = sc.next();

			if (flag.equalsIgnoreCase("YES")) {
				accountService.transferAmount(senderAccountNo, receiverAccountNo, amount);
			}

		} catch (InsufficientBalance e) {
			System.out.println(e.getMessage());
		} catch (InValidAccountNumber e) {
			System.out.println(e.getMessage());
		}
	}

	// Insert Account

	private void insertAccount() {
		Account accountObj = new Account();

		System.out.println("Enter the Account Number :");
		accountObj.setAccountNumber(sc.nextLong());

		System.out.println("Enter the Branch Code :");
		accountObj.setBranchCode(sc.next());

		System.out.println("Enter the Balance :");
		accountObj.setBalance(sc.nextDouble());

		System.out.println("Enter the user Id");
		User userObj = new User();
		userObj.setId(sc.nextLong());
		accountObj.setUser(userObj);

		try {
			accountService.insertAccount(accountObj);
		} catch (InsufficientBalance e) {
			e.getMessage();
		} catch (InValidAccountNumber e) {
			e.getMessage();
		}

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

		try {
			userService.insertUser(userobj);
		} catch (NameException e) {
			e.getMessage();
		} catch (InvalidEmailException e) {
			e.getMessage();
		} catch (InVailDateException e) {
			e.getMessage();
		} catch (PasswordException e) {
			e.getMessage();
		}

	}
}
