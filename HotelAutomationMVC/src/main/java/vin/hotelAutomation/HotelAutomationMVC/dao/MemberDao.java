package vin.hotelAutomation.HotelAutomationMVC.dao;

import java.util.List;

import vin.hotelAutomation.HotelAutomationMVC.entity.Member;
import vin.hotelAutomation.HotelAutomationMVC.model.MemberInfo;
 
public interface MemberDao {
 
    public Member findMember(String userName);
 
    public List<MemberInfo> listMemberInfos();
 
    public void saveMember(MemberInfo memberInfo);
 
    public MemberInfo findMemberInfo(String userName);
 
    public void deleteMember(String userName);
    
    public boolean checkLogin(String userName, String userPassword);
    
}
