package main.Response;

import org.springframework.stereotype.Component;

import main.entity.Account;

@Component
public class ResponseHandler {
	
	private Account account;

	//Getter and Setter
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
	//ToString
	
	@Override
	public String toString() {
		return "ResponseHandler [account=" + account + "]";
	}	
	

}
