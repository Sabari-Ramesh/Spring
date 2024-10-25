package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import main.Bo.AccountBo;
import main.Exception.InValidAccountNumber;
import main.Exception.InsufficientBalance;
import main.entity.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountBo accountBo;

	public void insertAccount(Account accountObj) {
		accountBo.inserAccount(accountObj);
	}

	 @Transactional(propagation = Propagation.REQUIRED)
	public void transferAmount(long senderAccountNo, long receiverAccountNo, double amount) throws InsufficientBalance, InValidAccountNumber {
		 accountBo.transferAmount(senderAccountNo,receiverAccountNo,amount);
		
	}

}
