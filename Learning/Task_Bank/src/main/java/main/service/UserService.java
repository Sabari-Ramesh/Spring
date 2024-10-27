package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.Bo.UserBo;
import main.Exception.InVailDateException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.PasswordException;
import main.entity.User;

@Service
public class UserService {

	@Autowired
	private UserBo userBo;

	public void insertUser(User userobj) throws NameException, InvalidEmailException, InVailDateException, PasswordException {
		userBo.insertUser(userobj);
	}

	// Bulk Insert

	public void bulkInsert(List<User> users) throws NameException, InvalidEmailException, PasswordException, InVailDateException {
		userBo.bulkInsert(users);
	}
	
		

}
