package main.Bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.DAO.AccountRepository;
import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.entity.Account;

@Component
public class AccountBo {
	
	@Autowired
	private AccountRepository accountRepo;

	public void inserAccount(Account accountObj) {
		accountRepo.save(accountObj);
	}

	public void transferAmount(long senderAccountNo, long receiverAccountNo, double amount) throws InsufficientBalance, InValidAccountNumber {
		
		Account senderAccount=validAccount(senderAccountNo);
		double senderbalance=senderAccount.getBalance();
		validbalance(senderbalance,amount);
		Account receiverAccount=validAccount(receiverAccountNo);
		
		//transfer Amount
		
		//sender balance reduced :
		double senderBalance=senderAccount.getBalance()-amount;
		senderAccount.setBalance(senderBalance);
		accountRepo.save(senderAccount);
		
		System.out.println(senderbalance);
		
		double recevierbalance=receiverAccount.getBalance()+amount;
		receiverAccount.setBalance(recevierbalance);
		accountRepo.save(receiverAccount);
		
		System.out.println(recevierbalance);
		
		
		
		
		
		System.out.println(senderAccount.getBalance()); //Debug Purpose
		
	}
	
	//valid Account Numbber
	
	private Account validAccount(long senderAccountNo) throws InValidAccountNumber {
		Account account=accountRepo.findByAccountNumber(senderAccountNo);
		if(account==null) {
			throw new InValidAccountNumber("Account not exit in the DataBase");
		}
		return account;
	}

	//Validation
	

	private void validbalance(double balance, double amount) throws InsufficientBalance {
		
		if(balance<amount) {
			throw new InsufficientBalance("Insufficient Balance");
		}
	}


}
