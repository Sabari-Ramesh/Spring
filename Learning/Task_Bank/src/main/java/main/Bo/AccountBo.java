package main.Bo;

import java.util.ArrayList;
import java.util.List;

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

	//Insert
	
	public void inserAccount(Account accountObj) throws InsufficientBalance, InValidAccountNumber {
		validAccount(accountObj.getAccountNumber());
		validAccountBalance(accountObj.getBalance());
		accountRepo.save(accountObj);
	}
	
	//find AccountNumber
	
	public Account findByAccountNumber(long accountNo) throws InValidAccountNumber {
		
		validAccount(accountNo);
		Account receiverAccount=accountRepo.findByAccountNumber(accountNo);
		return receiverAccount;
		
	}
	
	//Transfer Amount

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
	
	//Filter
	
	public List<Account> getAccountDetails() {
		List<Account> account=accountRepo.findAll();
		if(account.isEmpty()) {
			return new ArrayList();
		}
		return account;
	}
	
	public List<Account> getmaxAccountDetails() {
		List<Account> list=accountRepo.findAccountWithMaxBalance();
		
		if(list.isEmpty()) {
			return new ArrayList();
		}
		return list;
		
	}

	
	//valid Account Numbber
	
	private Account validAccount(long accountNo) throws InValidAccountNumber {
		Account account=accountRepo.findByAccountNumber(accountNo);
		if(account==null) {
			throw new InValidAccountNumber("Account not exit in the DataBase");
		}
		return account;
	}

	//Validation
	

	private void validbalance(double balance, double amount) throws InsufficientBalance {
		
		if(amount<0) {
			throw new InsufficientBalance("amount should be non-negative number");
		}
		if(balance<amount) {
			throw new InsufficientBalance("Insufficient Balance");
		}
	}

	private void validAccountBalance(double amount) throws InsufficientBalance {
		if(amount<0) {
			throw new InsufficientBalance("amount should be non-negative number");
		}
	}

	
	

}
