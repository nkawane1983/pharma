package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.UserTypeDAO;
import com.avee.form.Role;
import com.avee.form.UserType;
import com.avee.form.UserTypeRole;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class UserTypeDAOImpl implements UserTypeDAO{
	static final Logger logger = LogManager.getLogger(UserTypeDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserType getUserType(int id,boolean getRoles){
		logger.info("in UserType getUserType.. Start");
		Session session = null;
		Transaction tx = null;
		UserType form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.UserType domain = (com.avee.domain.UserType) 
					session.get(com.avee.domain.UserType.class, id);
			if (domain != null) {
				
				form = new UserType();
				BeansUtility bub = new BeansUtility();
				bub.copy(form, domain, new ArrayList<String>());
				
				if(getRoles){
					form.setUserTypeRoles(getUserTypeRoleList(domain.getId()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in UserType getUserType.. END");
		return form;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertUserType(UserType form){
		
		String message = "";
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.UserType domain = new com.avee.domain.UserType();
			BeansUtility bub = new BeansUtility();
			bub.copy(domain,form,new ArrayList<String>());
			session.save(domain);
			tx.commit();
			message = "User type inserted successfully.";
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return message;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateUserType(UserType form){
		
		Session session = null;
		Transaction tx = null;
		String message = "";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.UserType domain = (com.avee.domain.UserType) 
						session.get(com.avee.domain.UserType.class,form.getId());
			
			if(domain != null){
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, form, new ArrayList<String>());
				session.update(domain);
				tx.commit();
				message = "User type updated successfully.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return message;
	}
	@SuppressWarnings("unchecked")
	public List<UserType> searchUserType(Map<String,String> map){
		
		List<UserType> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			String userTypeName = map.get("userTypeName");
			String userTypeId = map.get("userTypeId");
			
			Criteria criteria = session.createCriteria(com.avee.domain.UserType.class);
			
			if(strUtility.checkNullOrEmptyString(userTypeName)){
				criteria.add(Restrictions.ilike("name", "%"+userTypeName+"%"));
			}
			
			if(strUtility.checkNullOrEmptyString(userTypeId)){
				criteria.add(Restrictions.eq("id",Integer.parseInt(userTypeId)));
			}
			
			List<com.avee.domain.UserType> typeList = criteria.list();
			
			for(com.avee.domain.UserType domain : typeList){
				UserType form = new UserType();
				form.setId(domain.getId());
				form.setName(domain.getName());
				form.setDescription(domain.getDescription());
				form.setCreatedBy(domain.getCreatedBy());
				form.setCreatedDt(domain.getCreatedDt());
				form.setUpdatedBy(domain.getUpdatedBy());
				form.setUpdatedDt(domain.getUpdatedDt());
				list.add(form);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<UserTypeRole> getUserTypeRoleList(int userTypeId){
		
		List<UserTypeRole> formList = new ArrayList<>();
		
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from UserTypeRole where userType.id =:userTypeId");
			query.setInteger("userTypeId", userTypeId);
			
			BeansUtility bub = new BeansUtility();
			List<String> exclusionsList = new ArrayList<String>();
			exclusionsList.add("role");
			exclusionsList.add("userType");
			
			List<com.avee.domain.UserTypeRole> domainList = query.list();
			for(com.avee.domain.UserTypeRole domain : domainList){
				
				UserTypeRole form = new UserTypeRole();
				
				Role role = new Role();
				bub.copy(role, domain.getRole(), exclusionsList);
				form.setRole(role);
				
				com.avee.form.UserType userTypeform = new com.avee.form.UserType();
				bub.copy(userTypeform, domain.getUserType(), exclusionsList);
				form.setUserType(userTypeform);
				
				formList.add(form);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return formList;
	}
	
	@SuppressWarnings("unchecked")
	public void manageUserTypeRole(List<UserTypeRole> list,int userTypeId){
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
				
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from UserTypeRole where userType.id =:userTypeId");
			query.setInteger("userTypeId", userTypeId);
			List<com.avee.domain.UserTypeRole> domainList = query.list();
			for(com.avee.domain.UserTypeRole domain : domainList){
				session.delete(domain);
			}
			
			tx.commit();
			
			if(list != null 
					&& list.size() > 0){
				
				tx = session.beginTransaction();
				
				for(UserTypeRole form : list){
					
					com.avee.domain.UserTypeRole domain = new com.avee.domain.UserTypeRole();
					com.avee.domain.Role roleDomain = new com.avee.domain.Role();
					com.avee.domain.UserType userTypeDomain = new com.avee.domain.UserType();
					
					roleDomain.setRolId(form.getRole().getRolId());
					userTypeDomain.setId(form.getUserType().getId());;
					
					domain.setRole(roleDomain);
					domain.setUserType(userTypeDomain);
					
					session.save(domain);
					
				}
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserType> getUserTypeList(){
		List<UserType> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(com.avee.domain.UserType.class);
			List<com.avee.domain.UserType> typeList = criteria.list();
			
			for(com.avee.domain.UserType domain : typeList){
				UserType form = new UserType();
				form.setId(domain.getId());
				form.setName(domain.getName());
				form.setDescription(domain.getDescription());
				form.setCreatedBy(domain.getCreatedBy());
				form.setCreatedDt(domain.getCreatedDt());
				form.setUpdatedBy(domain.getUpdatedBy());
				form.setUpdatedDt(domain.getUpdatedDt());
				list.add(form);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@Override
	public String deleteUserType(int userTypeId) {

		String message = "";
		Session session = null;
		Transaction tx = null;
		
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.UserType domain = (com.avee.domain.UserType)session.get(com.avee.domain.UserType.class,userTypeId);
			session.delete(domain);
			tx.commit();
			message = "UserType delete successfully.";
		} catch (Exception e) {
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return message;
	}
}
