package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Bo.UserBo;
import main.entity.User;

@Service
public class UserService {

	@Autowired	
	private UserBo userBo;
	
	public void insertUser(User userobj) {
		userBo.insertUser(userobj);
	}

}
