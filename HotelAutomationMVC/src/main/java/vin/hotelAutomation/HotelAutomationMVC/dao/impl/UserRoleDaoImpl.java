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
import vin.hotelAutomation.HotelAutomationMVC.dao.UserRoleDao;
import vin.hotelAutomation.HotelAutomationMVC.entity.UserRole;
import vin.hotelAutomation.HotelAutomationMVC.model.UserRoleInfo;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private SessionFactory sessionFactory;


	public UserRole findUserRole(String userName) {
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		//Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(UserRole.class);
		crit.add(Restrictions.eq("id", userName));
		return (UserRole) crit.uniqueResult();
	}

	public void saveUserRole(UserRoleInfo userRoleInfo) {

		String userName = userRoleInfo.getUserName();
		String role = userRoleInfo.getUserRole();
		UserRole userRole = null;
		if (userName != null) {
			userRole = this.findUserRole(userName);
		}

		boolean isNew = false;

		if (userRole == null) {
			isNew = true;
			userRole = new UserRole();
			// member.setUserName(UUID.randomUUID().toString());
			userRole.setUserName(userName);
			userRole.setUserRole(role);
		}

		if (isNew) {
			Session session;//= this.sessionFactory.getCurrentSession();
			try {
			    session = sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
			    session = sessionFactory.openSession();
			}
			session.persist(userRole);
		}

	}

	public List<String> getUserRoles(String userName) {
			String sql = "Select "//
					+ "a.userName, a.userRole "//
					+ " from " + UserRole.class.getName() + " a ";
			Session session;// = sessionFactory.getCurrentSession();
			try {
			    session = sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
			    session = sessionFactory.openSession();
			}
			Query query = session.createQuery(sql);
			return query.list();
		
	}

}