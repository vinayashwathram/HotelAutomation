package vin.hotelAutomation.HotelAutomationMVC.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vin.hotelAutomation.HotelAutomationMVC.service.LoginService;
import vin.hotelAutomation.HotelAutomationMVC.dao.*;


@Service("loginService")
public class LoginServiceImpl implements LoginService {
	 
	@Autowired
	 private MemberDao loginDAO;

	   public void setLoginDAO(MemberDao loginDAO) {
             this.loginDAO = loginDAO;
      }
     
      public boolean checkLogin(String userName, String userPassword){
             System.out.println("In Service class...Check Login");
             return loginDAO.checkLogin(userName, userPassword);
      }

}
