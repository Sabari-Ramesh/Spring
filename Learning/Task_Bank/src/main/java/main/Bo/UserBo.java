package main.Bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.DAO.UserRepository;
import main.entity.User;

@Component
public class UserBo {
	
	@Autowired
	private UserRepository userRepo;

	public void insertUser(User userobj) {
		userRepo.save(userobj);
		System.out.println("Done");
	}

}
