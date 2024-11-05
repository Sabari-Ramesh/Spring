package dietTrackerApplication.BO;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import dietTrackerApplication.DAO.CityInfoRepository;
import dietTrackerApplication.DAO.GenderInfoRepository;
import dietTrackerApplication.DAO.UserCountByCity;
import dietTrackerApplication.DAO.UserRepository;
import dietTrackerApplication.DAO.UsersWithMealDetails;
import dietTrackerApplication.Exception.CityException;
import dietTrackerApplication.Exception.DateException;
import dietTrackerApplication.Exception.GenderException;
import dietTrackerApplication.Exception.InvalidEmailException;
import dietTrackerApplication.Exception.NameException;
import dietTrackerApplication.Exception.NumberException;
import dietTrackerApplication.Exception.PasswordException;
import dietTrackerApplication.Exception.UserIdException;
import dietTrackerApplication.entity.CityInfo;
import dietTrackerApplication.entity.GenderInfo;
import dietTrackerApplication.entity.User;

@Component
public class UserBo {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CityInfoRepository cityRepo;

	@Autowired
	private GenderInfoRepository genderRepo;

	// Insert

	public User insertDetails(User user) throws DateException, DataIntegrityViolationException, InvalidEmailException,
			NumberException, PasswordException, NameException, CityException, GenderException {
		validDob(user.getDob());
		validEmail(user.getEmail());
		validMobileNumber(user.getMobileNumber());
		vaildPassword(user.getPassword());
		validName(user.getUserName());
		validCity(user.getCity());
		validGender(user.getGender());
		User insertedDetail = userRepo.save(user);
		return insertedDetail;
	}

	// Fetch By Id

	public User fetchById(long id) throws UserIdException {
		validId(id);
		User user = userRepo.findById(id).get();
		return user;
	}

	// Find All

	public List<User> fetchAll() {
		List<User> userList = userRepo.findAll();
		return userList;
	} 
	
	

	// Custom Querry With Aggregate
	
	public List<User> getUsersByRegistrationDateRange(LocalDate startDate, LocalDate endDate) throws DateException {
		validDates(startDate, endDate);
		List<User> userList=userRepo.findUsersByRegistrationDateRange(startDate, endDate);
		if (userList == null || userList.isEmpty()) {
			return new ArrayList<>();
		}
		return userList;
	}
	
	//Named With Clauses
	
	public List<UserCountByCity> countUsersByCity(){
		
		 List<UserCountByCity> userCountList = userRepo.countUsersByCity();
		 if (userCountList == null || userCountList.isEmpty()) {
				return new ArrayList<>();
			}
	        return userCountList;
	}

	//Custom With Projection
	
	public List<UsersWithMealDetails> usersWithMealDetails() {
		List<UsersWithMealDetails> listUser=userRepo.findUsersWithMealDetails();
		 if (listUser == null || listUser.isEmpty()) {
				return new ArrayList<>();
			}
		return listUser;
	}
	
	// Validation

	private void validDob(LocalDate dob) throws DateException {

		LocalDate currentDate = LocalDate.now();
		if (dob.isAfter(currentDate)) {
			throw new DateException("Date of birth cannot be in the future.");
		}
		int age = Period.between(dob, currentDate).getYears();
		if (age < 18) {
			throw new DateException("User must be at least 18 years old.");
		}

	}

	private void validEmail(String email) throws InvalidEmailException {
		if (!email.contains("@gmail.com")) {
			throw new InvalidEmailException("Enter valid Email address");
		}
	}

	private void validMobileNumber(String number) throws NumberException {
		
		
		boolean flag=true;
		for(int i=0;i<number.length();i++) {
			if(!Character.isDigit(number.charAt(i))) {
				flag=false;
				break;
			}
		}
		
		if (number.length() != 10 || number.charAt(0) == '0'|| !flag) {
			throw new NumberException("Enter Valid Mobile Number");
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

	private void validName(String userName) throws NameException {
		if (userName == null || userName.trim().isEmpty()) {
			throw new NameException("User name cannot be null or empty.");
		}
		for (char c : userName.toCharArray()) {
			if (Character.isDigit(c)) {
				throw new NameException("User name cannot contain digits.");
			}
		}
	}

	private void validCity(CityInfo city) throws CityException {

		String cityName = city.getCityName();
		int cityId;
		if (cityName.equalsIgnoreCase("New York")) {
			cityId = cityRepo.findCityIdByCityName("New York");
		} else if (cityName.equalsIgnoreCase("Madurai")) {
			cityId = cityRepo.findCityIdByCityName("Madurai");
		} else if (cityName.equalsIgnoreCase("Chennai")) {
			cityId = cityRepo.findCityIdByCityName("Chennai");
		} else if (cityName.equalsIgnoreCase("Coimbatore")) {
			cityId = cityRepo.findCityIdByCityName("Coimbatore");
		} else if (cityName.equalsIgnoreCase("Kochi")) {
			cityId = cityRepo.findCityIdByCityName("Kochi");
		} else {
			throw new CityException("In valid city Name");
		}
		city.setCity(cityId);

	}

	private void validGender(GenderInfo gender) throws GenderException {
		String genderName = gender.getGender();
		int genderId;

		if (genderName.equalsIgnoreCase("Male")) {
			genderId = genderRepo.findGenderIdByGender("Male");
		} else if (genderName.equalsIgnoreCase("Female")) {
			genderId = genderRepo.findGenderIdByGender("Female");
		} else if (genderName.equalsIgnoreCase("Other")) {
			genderId = genderRepo.findGenderIdByGender("Other");
		} else {
			throw new GenderException("Invalid Gender Name");
		}
		gender.setGenderId(genderId);
	}

	private void validId(long id) throws UserIdException {
		Optional<User> userOptional = userRepo.findById(id);

		if (userOptional.isEmpty()) { 
			throw new UserIdException("Id does not exist in the database: " + id);
		}

	}

	private void validDates(LocalDate startDate, LocalDate endDate) throws DateException {

		LocalDate today = LocalDate.now();

		if (startDate.isAfter(endDate)) {
			throw new DateException("ERROR : Start date cannot be after end date.");
		}

		if (startDate.isAfter(today)) {
			throw new DateException("ERROR : Start date cannot be in the future.");
		}

		if (endDate.isAfter(today)) {
			throw new DateException("ERROR : End date cannot be in the future.");
		}

	}



}
