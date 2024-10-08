package main.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.DAO.UserCountByCity;
import main.DTO.UserDTO;
import main.Exception.CityException;
import main.Exception.DateException;
import main.Exception.GenderException;
import main.Exception.InvalidEmailException;
import main.Exception.NameException;
import main.Exception.NumberException;
import main.Exception.PasswordException;
import main.Exception.UserIdException;
import main.entity.CityInfo;
import main.entity.GenderInfo;
import main.entity.User;
import main.response.UserResponse;
import main.service.UserService;

@RestController
@RequestMapping("/userdetails")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	UserResponse response;

	Logger log = Logger.getLogger(UserController.class);

	// insert

	@PostMapping("/add")

	public ResponseEntity<?> insertDetail(@RequestBody UserDTO userdto) {

		log.info("insert Method is triggred...");

		User user = maptoEntity(userdto);
		try {
			response = service.insertDetails(user);
			if (response.getSucessMessage() != null) {
				String str = "User Details are Sucessfully Created and Your Generated Meal Id : " + response.getId();
				log.info(str);
				return ResponseEntity
						.ok("User Details are Sucessfully Created and Your Generated Meal Id : " + response.getId());
			} else {
				log.info("User Details are not Sucessfully Created");
				return ResponseEntity.ok("Meal Details are not Sucessfully Created");
			}

		} catch (DataIntegrityViolationException e) {
			log.error("Duplicate Entry ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate Entry");
		} catch (DateException e) {
			log.error("Invalid Date ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (InvalidEmailException e) {
			log.error("Invalid Email ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (NumberException e) {
			log.error("Invalid Number ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (PasswordException e) {
			log.error("Invalid Password ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (NameException e) {
			log.error("Invalid Name ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (CityException e) {
			log.error("Invalid City ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (GenderException e) {
			log.error("Invalid Gender ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Fetch By Id

	@GetMapping("/fetchByid/{id}")
	public ResponseEntity<?> findByMealId(@PathVariable("id") long id) {

		log.info("Find By Id Method is triggred...");
		try {
			response = service.fetchById(id);
			User detail = response.getUser();
			UserDTO userDto = maptoDto(detail);
			return ResponseEntity.ok(userDto);
		} catch (UserIdException e) {
			log.error("User Id Not Found ERROR :", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Find ALL

	@GetMapping("/findall")
	public List<UserDTO> findAll() {

		log.info("Find All method is triggred....");
		response = service.fetchAll();
		List<User> userDetailList = response.getUserDetailsList();
		List<UserDTO> userDetailsDto = new ArrayList<>();
		if (userDetailList == null || userDetailList.isEmpty()) {
	        log.info("No user details found.");
	        return userDetailsDto; // Return an empty list if no users are found
	    }
	    for (User user : userDetailList) {
	        UserDTO userDTO = maptoDto(user);
	        userDetailsDto.add(userDTO);
	    }

		String sucess = response.getSucessMessage();
		log.info(sucess);
		log.info(userDetailsDto);
		return userDetailsDto;
	}


	// Custom with Aggregate

	@GetMapping("/registration-dates")
	public ResponseEntity<?> getUsersByRegistrationDateRange(@RequestBody Map<String, String> dateRange) {

		log.info("Get User by Date Range method is triggred...");
		LocalDate startDate = LocalDate.parse(dateRange.get("startDate"));
		LocalDate endDate = LocalDate.parse(dateRange.get("endDate"));
		try {
			response = service.getUsersByRegistrationDateRange(startDate, endDate);
			List<User> userDetailList = response.getUserDetailsList();
			if (userDetailList.isEmpty()) {
				String sucess = response.getSucessMessage();
				log.info(sucess);
				log.info(userDetailList);
				return ResponseEntity.ok(response.getSucessMessage());
			}

			List<UserDTO> userDetailsDto = new ArrayList<>();
			for (int i = 0; i < userDetailList.size(); i++) {
				User user = userDetailList.get(i);
				UserDTO getDetail = maptoDto(user);
				userDetailsDto.add(getDetail);
			}
			String sucess = response.getSucessMessage();
			log.info(sucess);
			log.info(userDetailsDto);
			return ResponseEntity.ok(userDetailsDto);
		} catch (DateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Named With Clauses and Projection

	@GetMapping("/groupBycity")
	public ResponseEntity<?> countUsersByCity() {

		log.info("Group By City Mehthod is triggred..");
		response = service.countUsersByCity();
		List<UserCountByCity> groupByCity = response.getGroupByCity();
		if (groupByCity.isEmpty()) {
			log.info("No Details Available");
			log.info(groupByCity);
			return ResponseEntity.ok("No Details Available");
		}
		String sucess = response.getSucessMessage();
		log.info(sucess);
		log.info(groupByCity);
		return ResponseEntity.ok(groupByCity);

	}

	// maptoEntity

	private User maptoEntity(UserDTO userdto) {

		User user = new User();

		user.setUserName(userdto.getUserName());
		user.setDob(userdto.getDob());
		user.setEmail(userdto.getEmail());
		user.setMobileNumber(userdto.getMobileNumber());
		user.setPassword(userdto.getPassword());

		// Setting obj

		GenderInfo gender = new GenderInfo();
		gender.setGender(userdto.getGender());

		CityInfo city = new CityInfo();
		city.setCityName(userdto.getCity());

		user.setCity(city);
		user.setGender(gender);

		return user;
	}

	// maptoDTo

	private UserDTO maptoDto(User detail) {
		UserDTO userDto = new UserDTO();

		userDto.setUserId(detail.getUserId());
		userDto.setDob(detail.getDob());
		userDto.setEmail(detail.getEmail());
		userDto.setGender(detail.getGender().getGender());
		userDto.setCity(detail.getCity().getCityName());
		userDto.setMobileNumber(detail.getMobileNumber());
		userDto.setUserName(detail.getUserName());
		userDto.setPassword(detail.getPassword());
		userDto.setDateCreated(detail.getDateCreated());
		userDto.setLastUpdate(detail.getLastUpdate());

		return userDto;
	}
}
