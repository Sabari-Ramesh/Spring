package main.Bo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.DAO.UserRepository;
import main.Exception.InVailDateException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.PasswordException;
import main.entity.User;

@Component
public class UserBo {
	
	@Autowired
	private UserRepository userRepo;

	public void insertUser(User userobj) throws NameException, InvalidEmailException, InVailDateException, PasswordException {
		validName(userobj.getName());
		vaildEmail(userobj.getEmail());
		vaildPassword(userobj.getPassword());
		vaildDate(userobj.getDob());
		
		userRepo.save(userobj);
	}
	
	
	public void bulkInsert(List<User> users) throws NameException, InvalidEmailException, PasswordException, InVailDateException {
	
		for(int i=0;i<users.size();i++) {
			
			User userobj=users.get(i);
			validName(userobj.getName());
			vaildEmail(userobj.getEmail());
			vaildPassword(userobj.getPassword());
			vaildDate(userobj.getDob());	
			
		}
		
		userRepo.saveAll(users);
	}
	
	//Validation 

	private void vaildDate(LocalDate dob) throws InVailDateException {
		if (dob == null) {
	        throw new InVailDateException("Date of birth cannot be null.");
	    }

	    LocalDate today = LocalDate.now();

	    if (!dob.isBefore(today)) {
	        throw new InVailDateException("Date of birth must be a past date.");
	    }
	}

	private void vaildPassword(String password) throws PasswordException {
		if (password.length() <= 8 && password.length()>=12) {
			throw new PasswordException("Password must be greater than 8 characters and less than 12 characters.");
		}

		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasSpecialChar = false;

		for (char c : password.toCharArray()) {
			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (!Character.isLetterOrDigit(c)) {
				hasSpecialChar = true;
			}
		}
		if (!hasUppercase) {
			throw new PasswordException("InValid Password : Password must contain at least one uppercase letter.");
		}
		if (!hasLowercase) {
			throw new PasswordException("InValid Password : Password must contain at least one lowercase letter.");
		}
		if (!hasSpecialChar) {
			throw new PasswordException("InValid Password : Password must contain at least one special character.");
		}
	}

	private void vaildEmail(String email) throws InvalidEmailException {
		if (!email.contains("@gmail.com")) {
			throw new InvalidEmailException("Enter valid Email address");
		}
	}

	private void validName(String name) throws NameException {
		if (name == null) {
			throw new NameException("ERROR : Invalid Name :" + name);
		}
		
		 for (int i = 0; i < name.length(); i++) {
		        char c = name.charAt(i);
		        if (!Character.isAlphabetic(c) && !Character.isWhitespace(c)) {
		            throw new NameException("ERROR: Invalid Name: " + name + ". Name must contain only letters and spaces.");
		        }
		    }
	}

	

}
