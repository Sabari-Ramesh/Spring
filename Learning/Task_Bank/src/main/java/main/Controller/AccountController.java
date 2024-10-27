package main.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.DTO.UserDetails;
import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.Response.ResponseHandler;
import main.Response.UserwithBalance;
import main.entity.Account;
import main.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ResponseHandler responseHandler;
	
	//Reciver Details
	
    @GetMapping("/receiver")
    public UserDetails getReceiverAccountDetails(@RequestParam long receiverAccountNo) throws InValidAccountNumber {
    	responseHandler=accountService.getAccountByAccountNumber(receiverAccountNo);
    	UserDetails reciverDetails=maptoDetails(responseHandler);
    	return reciverDetails;
    	
    }
    
    //Amount Transfer
    
    
    @PostMapping("/transfer/confrim")
    public ResponseEntity<?> transferAmount(@RequestParam long senderAccountNo,@RequestParam long receiverAccountNo,@RequestParam double amount,@RequestParam String confirmation) {

    	//Transfer amount if confirmed
        if (confirmation.equalsIgnoreCase("yes")) {
            try {
				accountService.transferAmount(senderAccountNo, receiverAccountNo, amount);
				return ResponseEntity.ok("Amount transfer Sucesfully!!!");
			} catch (InsufficientBalance e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} catch (InValidAccountNumber e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
            
           
        } else {
        	return ResponseEntity.ok("Amount Transfer Request Cancel By User !!!");
        }
    }

    @GetMapping("/min-balance")
    public ResponseEntity<?> getMinAccountBalanceUsers() {
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

            return ResponseEntity.ok(usersWithBalance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid accounts found with positive balance.");
        }
    }

    
    @GetMapping("/max-balance")
    private ResponseEntity<?> getmaxAccountBalance() {
    	List<Account> maxBalanceAccounts = accountService.getMaxAccountBalanceUsers();
    	if(!maxBalanceAccounts.isEmpty()) {
    		 List<UserwithBalance> usersWithBalance = maxBalanceAccounts.stream().map(account -> {
                 UserwithBalance user = new UserwithBalance();
                 user.setAccountNumber(account.getAccountNumber());
                 user.setBalance(account.getBalance());
                 user.setName(account.getUser().getName());
                 user.setPhoneNumber(account.getUser().getPhonenumber());
                 return user;
             }).collect(Collectors.toList());

             return ResponseEntity.ok(usersWithBalance);
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No valid accounts found with positive balance.");
    	}
    }
    
    
    //Mapping
    
	private UserDetails maptoDetails(ResponseHandler responseHandler) {
		
		UserDetails details=new UserDetails();
		
		details.setAccountNumber(responseHandler.getAccount().getAccountNumber());
		details.setName(responseHandler.getAccount().getUser().getName());
		details.setEmailId(responseHandler.getAccount().getUser().getEmail());
		details.setNumber(responseHandler.getAccount().getUser().getPhonenumber());
		
		return details;
	}
	
}
