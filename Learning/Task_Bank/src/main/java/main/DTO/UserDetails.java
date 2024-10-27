package main.DTO;


public class UserDetails {
	
	private String name;
	
	private long AccountNumber;
	
	private long Number;
	
	private String emailId;

	
	//Getter and Setter
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}

	public long getNumber() {
		return Number;
	}

	public void setNumber(long number) {
		Number = number;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
