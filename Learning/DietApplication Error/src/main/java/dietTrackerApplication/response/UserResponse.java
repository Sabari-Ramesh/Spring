package dietTrackerApplication.response;

import java.util.List;

import org.springframework.stereotype.Component;

import dietTrackerApplication.DAO.UserCountByCity;
import dietTrackerApplication.DAO.UsersWithMealDetails;
import dietTrackerApplication.entity.User;

@Component
public class UserResponse {
	private long id;
	private String sucessMessage;
	private String failureMessage;
	private User user;
	private List<User> userDetailsList;
	private List<UserCountByCity> groupByCity;
	private List<UsersWithMealDetails> usersWithMeal;
	
	//Getter and Setter
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSucessMessage() {
		return sucessMessage;
	}
	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserDetailsList() {
		return userDetailsList;
	}
	public void setUserDetailsList(List<User> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}
	public List<UserCountByCity> getGroupByCity() {
		return groupByCity;
	}
	public void setGroupByCity(List<UserCountByCity> groupByCity) {
		this.groupByCity = groupByCity;
	}
	public List<UsersWithMealDetails> getUsersWithMeal() {
		return usersWithMeal;
	}
	public void setUsersWithMeal(List<UsersWithMealDetails> usersWithMeal) {
		this.usersWithMeal = usersWithMeal;
	}
	
	

}
