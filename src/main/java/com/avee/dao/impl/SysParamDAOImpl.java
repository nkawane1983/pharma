package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.SysParamDAO;
import com.avee.form.SystemParameter;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class SysParamDAOImpl implements SysParamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SystemParameter> searchSysParam(Map<String, String> map) {

		Session session = null;
		List<SystemParameter> list = new ArrayList<>();

		try {

			session = sessionFactory.openSession();

			String sysParamName = map.get("sysParamName");
			String sysParamValue = map.get("sysParamValue");
			String sysId = map.get("sysId");

			BeansUtility bub = new BeansUtility();
			List<String> exclusionsList = new ArrayList<String>();

			Criteria criteria = session.createCriteria(com.avee.domain.SystemParameter.class);

			if (strUtility.checkNullOrEmptyString(sysParamName)) {
				criteria.add(Restrictions.ilike("parameterName", "%" + sysParamName + "%"));
			}

			if (strUtility.checkNullOrEmptyString(sysParamValue)) {
				criteria.add(Restrictions.ilike("parameterValue", "%" + sysParamValue + "%"));
			}

			if (strUtility.checkNullOrEmptyString(sysId)) {
				criteria.add(Restrictions.eq("id", Integer.parseInt(sysId)));
			}

			List<com.avee.domain.SystemParameter> domainList = criteria.list();
			for (com.avee.domain.SystemParameter domain : domainList) {
				SystemParameter form = new SystemParameter();
				bub.copy(form, domain, exclusionsList);
				list.add(form);
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
	public SystemParameter getSystemParameter(int id) {

		Session session = null;
		SystemParameter form = null;
		try {

			session = sessionFactory.openSession();

			com.avee.domain.SystemParameter domain = (com.avee.domain.SystemParameter) session
					.get(com.avee.domain.SystemParameter.class, id);

			if (domain != null) {

				BeansUtility bub = new BeansUtility();
				form = new SystemParameter();
				bub.copy(form, domain, new ArrayList<String>());
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return form;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String crudSysParam(SystemParameter systemParameter, String operationName) {

		String message = "";
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			if (strUtility.checkNullOrEmptyString(operationName)) {

				BeansUtility bub = new BeansUtility();
				List<String> exclusionsList = new ArrayList<String>();

				com.avee.domain.SystemParameter domain = null;

				if (operationName.equalsIgnoreCase("insert")) {
					domain = new com.avee.domain.SystemParameter();
					bub.copy(domain, systemParameter, exclusionsList);
					session.save(domain);
					tx.commit();
					message = "System parameter inserted successfully.";
				}
				if (operationName.equalsIgnoreCase("update")) {
					domain = (com.avee.domain.SystemParameter) session.get(com.avee.domain.SystemParameter.class,
							systemParameter.getId());
					bub.copy(domain, systemParameter, exclusionsList);
					session.update(domain);
					tx.commit();
					message = "System parameter updated successfully.";
				}
				if (operationName.equalsIgnoreCase("delete")) {
					domain = (com.avee.domain.SystemParameter) session.get(com.avee.domain.SystemParameter.class,
							systemParameter.getId());
					session.delete(domain);
					tx.commit();
					message = "System parameter delete successfully.";
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
		return message;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public SystemParameter getSystemParameter(String param) {

		Session session = null;
		SystemParameter form = null;
		try {

			session = sessionFactory.openSession();
			com.avee.domain.SystemParameter domain = new com.avee.domain.SystemParameter();
			Criteria criteria = session.createCriteria(com.avee.domain.SystemParameter.class);

			if (strUtility.checkNullOrEmptyString(param)) {
				criteria.add(Restrictions.ilike("parameterName", "%" + param + "%"));
				List<com.avee.domain.SystemParameter> domainlist = criteria.list();
				domain = domainlist.get(0);
			}

			if (domain != null) {

				BeansUtility bub = new BeansUtility();
				form = new SystemParameter();
				bub.copy(form, domain, new ArrayList<String>());
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return form;
	}
}
