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

import com.avee.dao.GroupDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.GroupDetails;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class GroupDAOImpl implements GroupDAO {
	static final Logger logger = LogManager.getLogger(GroupDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public GroupDetails getGroupDetails(int id) {
		Session session = null;
		Transaction tx = null;
		GroupDetails form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.GroupDetails domain = (com.avee.domain.GroupDetails) session
					.get(com.avee.domain.GroupDetails.class, id);
			if (domain != null) {
				form = new GroupDetails();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String insertGroupDetails(GroupDetails groupDetails) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.GroupDetails domain = new com.avee.domain.GroupDetails();
			bub.copy(domain, groupDetails, exclusionsList);

			session.save(domain);
			tx.commit();

			message = "GroupDetails inserted successfully.";

		} catch (Exception e) {
			message = "GroupDetails can not be inserted.";
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GroupDetails> searchGroupDetails(Map<String, String> map) {
		List<GroupDetails> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String groupName = map.get("groupName");
		//	String gid = map.get("gid");

			Criteria criteria = session.createCriteria(com.avee.domain.GroupDetails.class);

			if (strUtility.checkNullOrEmptyString(groupName)) {
				criteria.add(Restrictions.ilike("description", "%" + groupName + "%"));
			}
			criteria.add(Restrictions.eq("isActive", true));
			List<com.avee.domain.GroupDetails> groupList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.GroupDetails groupdomain : groupList) {
				GroupDetails groupForm = new GroupDetails();
				bub.copy(groupForm, groupdomain, exclusionsList);
				list.add(groupForm);

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
	@Override
	public List<AppUserGroupBranchMapping> getGroupDetailsAsList(Map<String, String> map) {
		logger.info("in GroupDAO getGroupDetailsAsList.. Start");

		List<AppUserGroupBranchMapping> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String uid = map.get("uid");
			String groupId = map.get("groupId");
			Query qry = null;
			if (strUtility.checkNullOrEmptyString(uid) && strUtility.checkNullOrEmptyString(groupId)) {
				qry = session.getNamedQuery("getGroupDetailsByGroupIdAsList");
				qry.setInteger("groupId", Integer.parseInt(groupId));
			} else {
				qry = session.getNamedQuery("getGroupDetailsByUIdAsList");
			}
			qry.setString("uId", uid);

			List<com.avee.domain.AppUserGroupBranchMapping> groupDetailsList = qry.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.AppUserGroupBranchMapping groupDetailsdomain : groupDetailsList) {
				AppUserGroupBranchMapping groupDetailsForm = new AppUserGroupBranchMapping();
				bub.copy(groupDetailsForm, groupDetailsdomain, exclusionsList);
				list.add(groupDetailsForm);

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
