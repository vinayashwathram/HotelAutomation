package vin.hotelAutomation.HotelAutomationMVC.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLES")
public class UserRole implements Serializable {

	// private static final long serialVersionUID = -7893237204476214050L;
	private String userName;
	private String userRole;

	@Id
	@Column(name = "USERNAME", length = 50, nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ROLE", length = 10, nullable = false)
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
