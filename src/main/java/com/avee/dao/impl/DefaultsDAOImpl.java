package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.form.BranchDefaults;
import com.avee.form.DefaultValues;
import com.avee.dao.DefaultsDAO;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class DefaultsDAOImpl implements DefaultsDAO {
	static final Logger logger = LogManager.getLogger(DefaultsDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getdefaultsValue(String dafaultName, String userID) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			if (strUtility.checkNullOrEmptyString(dafaultName) && strUtility.checkNullOrEmptyString(userID)) {
				Query qry = session.getNamedQuery("defaultSetting_BydefaultName");
				qry.setString("userId", userID);
				qry.setString("defaultName", dafaultName);
				result = qry.list();
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
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getdefaultsGroup(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String groupid = map.get("groupid");
			String defaultName = map.get("defaultName");
			Query qry = null;
			if (strUtility.checkNullOrEmptyString(groupid)&&strUtility.checkNullOrEmptyString(defaultName)) {
				qry = session.getNamedQuery("defaultSetting_Bygroupid");
				qry.setString("defaultName", defaultName);
				qry.setInteger("groupid", Integer.parseInt(groupid));
			}
			result = qry.list();
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
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void UpdateUserSetting(DefaultValues defaultValues) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.DefaultValues domain = (com.avee.domain.DefaultValues) session.get(com.avee.domain.DefaultValues.class,
					defaultValues.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, defaultValues, exclusionsList);
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

		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int InsertBranchDefault(BranchDefaults branchDefaults) {
		int id = 0;

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.BranchDefaults domain = new com.avee.domain.BranchDefaults();
			bub.copy(domain, branchDefaults, exclusionsList);

			session.save(domain);

			tx.commit();
			id = domain.getId();

		} catch (Exception e) {
			id = 0;
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return id;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int InsertDefaultValue(DefaultValues defaultValues) {
		int id = 0;

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.DefaultValues domain = new com.avee.domain.DefaultValues();
			bub.copy(domain, defaultValues, exclusionsList);

			session.save(domain);

			tx.commit();
			id = domain.getId();

		} catch (Exception e) {
			id = 0;
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return id;
	}
}
