package vin.hotelAutomation.HotelAutomationMVC.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import vin.hotelAutomation.HotelAutomationMVC.dao.MemberDao;
import vin.hotelAutomation.HotelAutomationMVC.entity.Member;
import vin.hotelAutomation.HotelAutomationMVC.model.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
 
public class MemberDaoImpl implements MemberDao {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public Member findMember(String userName) {
        Session session;// = sessionFactory.getCurrentSession();
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Criteria crit = session.createCriteria(Member.class);
        crit.add(Restrictions.eq("id", userName));
        return (Member) crit.uniqueResult();
    }
 
    public MemberInfo findMemberInfo(String userName) {
        Member member = this.findMember(userName);
        if (member == null) {
            return null;
        }
        return new MemberInfo(member.getUserName(),member.getPassword(), member.getFirstName(), //
                member.getLastName(),member.getAge(),member.getEmail(), member.getGender());
    }
 
    public List<MemberInfo> listMemberInfos() {
        String sql = "Select new " + MemberInfo.class.getName()//
                + "(a.userName, a.firstName, a.lastName,a.age, a.email, a.gender) "//
                + " from " + Member.class.getName() + " a ";
        Session session; // = sessionFactory.getCurrentSession();
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        Query query = session.createQuery(sql);
        return query.list();
    }
 
    public void saveMember(MemberInfo memberInfo) {
        String userName = memberInfo.getUserName();
        Member member = null;
        if (userName != null) {
            member = this.findMember(userName);
        }
        
        boolean isNew = false;
        
        if (member == null) {
            isNew = true;
            member = new Member();
            //member.setUserName(UUID.randomUUID().toString());
            member.setUserName(userName);
            member.setPassword(memberInfo.getPassword());
        }
        
        
        member.setEmail(memberInfo.getEmail());
        member.setGender(memberInfo.getGender());
        member.setFirstName(memberInfo.getFirstName());
        member.setLastName(memberInfo.getLastName());
        member.setAge(memberInfo.getAge());
        //
 
        if (isNew) {
            Session session; // = this.sessionFactory.getCurrentSession();
            try {
                session = this.sessionFactory.getCurrentSession();
            } catch (HibernateException e) {
                session = this.sessionFactory.openSession();
            }
            session.persist(member);
        }
    }
 
    public void deleteMember(String userName) {
        Member member = this.findMember(userName);
        if (member != null) {
            this.sessionFactory.getCurrentSession().delete(member);
        }
    }

	public boolean checkLogin(String userName, String userPassword) {
		System.out.println("In Check login");
		System.out.println("User name is:"+userName);
		boolean userFound = false;
		
		Member member = this.findMember(userName);
		System.out.println(member == null);
		System.out.println(userPassword);

		if(member != null){
			
			if(member.getPassword() !=null){
				return member.getPassword().equals(userPassword);
			} else return userFound;
			
		}else return userFound;
		
	}
 
}