package vin.hotelAutomation.HotelAutomationMVC.dao;

import java.util.List;

import vin.hotelAutomation.HotelAutomationMVC.entity.UserRole;
import vin.hotelAutomation.HotelAutomationMVC.model.UserRoleInfo;

public interface UserRoleDao {
	
    public UserRole findUserRole(String userName);
    
    public List<String> getUserRoles(String userName);
 
    public void saveUserRole(UserRoleInfo userRoleInfo);

}
