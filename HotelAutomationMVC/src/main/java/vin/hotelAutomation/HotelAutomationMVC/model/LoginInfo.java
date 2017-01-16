package vin.hotelAutomation.HotelAutomationMVC.model;

public class LoginInfo {


	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String gender;

	public LoginInfo() {

	}

	// Do not change this constructor,
	// it is used in the Hibernate Query
	public LoginInfo(String userName, String password, String firstName, String lastName, int age, String gender, String email) {
		this.userName = userName;
		this.password = password;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
