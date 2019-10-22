package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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

import com.avee.dao.RoleDAO;
import com.avee.form.Role;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class RoleDAOImpl implements RoleDAO {

	static final Logger logger = LogManager.getLogger(RoleDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Role> getRoleList() {
		Session session = null;

		List<Role> list = new ArrayList<Role>();

		List<Role> pList = new ArrayList<Role>();
		List<Role> cList = new CopyOnWriteArrayList<Role>();

		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Role phyRole where phyRole.rolIsactive = 'Y'");
			List<com.avee.domain.Role> domainList = query.list();

			BeansUtility bub = new BeansUtility();

			List<String> exclusionsList = new ArrayList<String>();

			for (com.avee.domain.Role domain : domainList) {

				Role form = new Role();
				bub.copy(form, domain, exclusionsList);

				if (domain.getRolParentId() != 0) {
					cList.add(form);
				} else {
					pList.add(form);
				}
			}

			// Here we arrange list of object according to their parent and child records.
			for (Role pForm : pList) {
				list.add(pForm);
				for (Role cForm : cList) {
					if (pForm.getRolId() == cForm.getRolParentId()) {
						list.add(cForm);
						cList.remove(cForm);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Role getRole(int id) {

		Session session = null;
		Transaction tx = null;
		Role form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.Role domain = (com.avee.domain.Role) session.get(com.avee.domain.Role.class, id);
			if (domain != null) {
				form = new Role();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(form, domain, exclusionsList);
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
		return form;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String insertRole(Role role) {

		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
					
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Role domain = new com.avee.domain.Role();
			bub.copy(domain, role, exclusionsList);
			
			if(!strUtility.checkNullOrEmptyString(domain.getRolIsactive())){
				domain.setRolIsactive("N");
			}
			
			session.save(domain);
			tx.commit();
				
			message = "Role inserted successfully.";
			
		} catch (Exception e) {
			message = "Role can not be inserted.";
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
	public void updateRole(Role form) {
		logger.info("in RoleDAO - updateRole.. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Role domain = (com.avee.domain.Role) 
						session.get(com.avee.domain.Role.class,form.getRolId());
			
			if(domain != null){
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, form, exclusionsList);
				session.update(domain);
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
		logger.info("in RoleDAO updateRole.. end");
	}

	public void deleteRole(int id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.Role domain = (com.avee.domain.Role) session.get(com.avee.domain.Role.class,
					id);
			session.delete(domain);
			tx.commit();
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
	public List<Role> searchRole(Map<String,String> map){
		List<Role> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			String roleName = map.get("role_Name");
			String roleId = map.get("role_Id");
			
			Criteria criteria = session.createCriteria(com.avee.domain.Role.class);
			
			if(strUtility.checkNullOrEmptyString(roleName)){
				criteria.add(Restrictions.ilike("rolName", "%"+roleName+"%"));
			}
			
			if(strUtility.checkNullOrEmptyString(roleId)){
				criteria.add(Restrictions.eq("rolId",Integer.parseInt(roleId)));
			}
			
			criteria.add(Restrictions.eq("rolIsactive", "Y"));
			
			List<com.avee.domain.Role> roleList = criteria.list();
			
			for(com.avee.domain.Role roledomain : roleList){
				Role roleForm = new Role();
				roleForm.setRolName(roledomain.getRolName());
				roleForm.setRolId(roledomain.getRolId());
				roleForm.setRolDescription(roledomain.getRolDescription());
				list.add(roleForm);
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
}