package main.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import main.BO.UserBo;
import main.DAO.UserCountByCity;
import main.Exception.CityException;
import main.Exception.DateException;
import main.Exception.GenderException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.NumberException;
import main.Exception.PasswordException;
import main.Exception.UserIdException;
import main.entity.User;
import main.response.UserResponse;

@Service
public class UserService {

	@Autowired
	private UserBo userBo;

	@Autowired
	private UserResponse response;
	
	Logger log=Logger.getLogger(UserService.class);

	// insert
	public UserResponse insertDetails(User user) throws DateException, DataIntegrityViolationException,
			InvalidEmailException, NumberException, PasswordException, NameException, CityException, GenderException {
		
		log.info("insert Method is triggred...");
		User detail = userBo.insertDetails(user);

		if (detail != null) {
			response.setSucessMessage("Details are Added Sucessfully !!!");
			response.setId(detail.getUserId());
		} else {
			response.setFailureMessage("Details are not Added Sucessfully !!!");
		}
		return response;

	}

	// Fetch By Id

	public UserResponse fetchById(long id) throws UserIdException {

		log.info("Find By Id Method is triggred...");
		User fetchedDetail = userBo.fetchById(id);
		if (fetchedDetail != null) {
			response.setSucessMessage("Details are Sucessfully fetched..");
			response.setUser(fetchedDetail);
		} else {
			response.setFailureMessage("Details are not fetched Sucessfully");
		}
		return response;
	}

	// Fetch All

	public UserResponse fetchAll() {

		log.info("Find All method is triggred....");
		List<User> user = userBo.fetchAll();
		response.setUserDetailsList(user);
		if (user != null && !user.isEmpty()) {
			response.setSucessMessage("Sucessfully Details are Fetched !!!");
		} else {
			response.setFailureMessage("Failure to Fetch the Details !!!");
		}
		return response;

	}
	
	
	

	// Custom With Aggregate

	public UserResponse getUsersByRegistrationDateRange(LocalDate startDate, LocalDate endDate) throws DateException {

		log.info("Get User by Date Range method is triggred...");
		List<User> userDetail = userBo.getUsersByRegistrationDateRange(startDate, endDate);
		if (userDetail.size() > 0) {
			response.setUserDetailsList(userDetail);
			response.setSucessMessage("Sucessfully Details are Fetched !!!");
		} else {
			response.setFailureMessage("Failure to Fetch the Details !!!");
		}
		return response;

	}

	// Named With Clauses

	public UserResponse countUsersByCity() {

		log.info("Group By City Mehthod is triggred..");
		List<UserCountByCity> usersByCity = userBo.countUsersByCity();
		response.setGroupByCity(usersByCity);
		if (usersByCity.size() > 0) {
			response.setSucessMessage("Sucessfully Details are Fetched !!!");
		} else {
			response.setFailureMessage("Failure to Fetch the Details !!!");
		}
		return response;
	}

}