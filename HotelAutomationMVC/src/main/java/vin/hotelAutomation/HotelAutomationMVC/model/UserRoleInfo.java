package vin.hotelAutomation.HotelAutomationMVC.model;

public class UserRoleInfo {

	private String userName;
	private String userRole;
	

	public UserRoleInfo() {

	}

	// Do not change this constructor,
	// it is used in the Hibernate Query
	public UserRoleInfo(String userName, String userRole) {
		this.userName = userName;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		
		this.userName = userName;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
