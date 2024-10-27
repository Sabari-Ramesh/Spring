package main.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import main.Bo.AccountBo;
import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.Response.ResponseHandler;
import main.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	private ResponseHandler responseHandle;
	
	@Autowired
	private AccountBo accountBo;

	public void insertAccount(Account accountObj) throws InsufficientBalance, InValidAccountNumber {
		accountBo.inserAccount(accountObj);
	}

	 @Transactional(propagation = Propagation.REQUIRED)
	public void transferAmount(long senderAccountNo, long receiverAccountNo, double amount) throws InsufficientBalance, InValidAccountNumber {
		 accountBo.transferAmount(senderAccountNo,receiverAccountNo,amount);
		
	}

	 @Transactional
	public ResponseHandler getAccountByAccountNumber(long receiverAccountNo) throws InValidAccountNumber {
		 
		 Account receiverAccount=accountBo.findByAccountNumber(receiverAccountNo);
		 responseHandle.setAccount(receiverAccount);
		 return responseHandle;
		 
	}

	 
	//Filter
	 
	 @Transactional
	 public List<Account> getMinAccountBalanceUsers() {
	     List<Account> accountDetails = accountBo.getAccountDetails();

	     // Find the minimum balance
	     Optional<Double> minBalance = accountDetails.stream().filter(account -> account.getBalance() > 0) .map(Account::getBalance).min(Double::compare);

	     // Filter accounts with the minimum balance (if found)
	     if (minBalance.isPresent()) {
	         return accountDetails.stream().filter(account -> account.getBalance() == minBalance.get()).collect(Collectors.toList());
	     }

	     // If no valid accounts are found, return an empty list
	     return Collections.emptyList();
	 }

	
	 public List<Account> getMaxAccountBalanceUsers() {
			List<Account> maxBalance=accountBo.getmaxAccountDetails();
			return maxBalance;
		}

	

}
