package vin.hotelAutomation.HotelAutomationMVC.authentication;
 
import java.util.ArrayList;
import java.util.List;

import vin.hotelAutomation.HotelAutomationMVC.dao.MemberDao;
import vin.hotelAutomation.HotelAutomationMVC.dao.UserRoleDao;
import vin.hotelAutomation.HotelAutomationMVC.entity.Member;
import vin.hotelAutomation.HotelAutomationMVC.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Autowired
    private MemberDao memberDao;
 
    
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserRole userRole = userRoleDao.findUserRole(userName);
        Member member = memberDao.findMember(userName);
        
 
        if (userRole == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        System.out.println("UserRole= " + userRole.getUserRole());
        // [USER,ADMIN,..]
        List<String> roles= userRoleDao.getUserRoles(userName);
         
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(roles!= null)  {
            for(String role: roles)  {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }        
         
        UserDetails userDetails = (UserDetails) new User(member.getUserName(), //
                member.getPassword(),grantList);
 
        return userDetails;
    }
     
}