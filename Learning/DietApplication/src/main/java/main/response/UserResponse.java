package main.response;

import java.util.List;

import org.springframework.stereotype.Component;

import main.DAO.UserCountByCity;
import main.entity.User;

@Component
public class UserResponse {
	private long id;
	private String sucessMessage;
	private String failureMessage;
	private User user;
	private List<User> userDetailsList;
	private List<UserCountByCity> groupByCity;
	
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
	
	

}
