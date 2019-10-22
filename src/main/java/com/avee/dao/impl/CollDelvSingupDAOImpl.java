package com.avee.dao.impl;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.CollDelvSingupDAO;
import com.avee.form.CareHomeScriptItems;
import com.avee.form.CollDelvSingup;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class CollDelvSingupDAOImpl implements CollDelvSingupDAO {
	static final Logger logger = LogManager.getLogger(CollDelvSingupDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private StringUtility strUtility;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String insertScriptItems(CollDelvSingup collDelvSingup) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			com.avee.domain.CollDelvSingup domain = new com.avee.domain.CollDelvSingup();

			bub.copy(domain, collDelvSingup, exclusionsList);
			session.save(domain);
			tx.commit();

			// System.out.println("id="+domain.getId());

			message = "Collection & Delivery  saved successfully.";

		} catch (Exception e) {
			message = "Collection & Delivery  can not be inserted.";
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
	public List<CollDelvSingup> getCollDelvSingupasList(Map<String, String> map) {
		List<CollDelvSingup> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String branchId = map.get("bid");
			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);
			Criteria criteria = session.createCriteria(com.avee.domain.CollDelvSingup.class);
			criteria.add(Restrictions.between("eventDate", fDate, tDate))
					.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			List<com.avee.domain.CollDelvSingup> collDelvSingupList = criteria.list();
		
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.CollDelvSingup collDelvSingupListDomain : collDelvSingupList) {
				CollDelvSingup collDelvSingupForm = new CollDelvSingup();
				bub.copy(collDelvSingupForm, collDelvSingupListDomain, exclusionsList);
				String encodeStr = strUtility.encodeString(strUtility.covertDateToString(collDelvSingupListDomain.getEventDate(),"yyyy-MM-dd"));
				collDelvSingupForm.setEncodeId(encodeStr);
				list.add(collDelvSingupForm);
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
	public List<CollDelvSingup> searchCollDelvSingupasList(Map<String, String> map) {
		List<CollDelvSingup> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String branchId = map.get("bid");
			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);
			Criteria criteria = session.createCriteria(com.avee.domain.CollDelvSingup.class);
			criteria.add(Restrictions.between("eventDate", fDate, tDate))
					.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			criteria.setProjection(
					Projections.projectionList()
					.add(Projections.groupProperty("eventDate"))
					.add(Projections.groupProperty("branchId"))
					.add(Projections.sum("delvItems"),"delvItems")
					.add(Projections.sum("collItems"),"collItems")
					);
			
			List<Object[]> results = criteria.list();
			for(Object[] object : results){
				CollDelvSingup collDelvSingupForm = new CollDelvSingup();
				collDelvSingupForm.setBranchId(Integer.parseInt(object[1].toString()));;
				collDelvSingupForm.setEventDate(strUtility.covertStringToDate(object[0].toString(), "yyyy-MM-dd"));
				collDelvSingupForm.setDelvItems(Integer.parseInt(object[2].toString()));
				collDelvSingupForm.setCollItems(Integer.parseInt(object[3].toString()));
				String encodeStr = strUtility.encodeString(strUtility.covertDateToString(collDelvSingupForm.getEventDate(),"yyyy-MM-dd"));
				collDelvSingupForm.setEncodeId(encodeStr);
				list.add(collDelvSingupForm);
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
	public boolean checkCollDelvSingupExistOrNot(Map<String, String> map) {
		// TODO Auto-generated method stub
		boolean val=true;
		List<CollDelvSingup> list = new ArrayList<>();
		list=getCollDelvSingupasList(map);
		if(list!=null && list.size()>0)
			val=false;
		return val;
	}



	@Override
	public void deleteCollDelvSingup(CollDelvSingup collDelvSingup) {
		logger.info("in   deleteCollDelvSingup. start");
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			if (collDelvSingup.getCollDelv() != null && collDelvSingup.getCollDelv().size() > 0) {
				for (CollDelvSingup collDelvSingupform : collDelvSingup.getCollDelv()) {

					if (collDelvSingupform.getId() != 0) {
						com.avee.domain.CollDelvSingup domain = (com.avee.domain.CollDelvSingup) session
								.get(com.avee.domain.CollDelvSingup.class, collDelvSingupform.getId());
						if (domain != null) {
							session.delete(domain);

						}
					}
				}
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in  deleteCollDelvSingup.. end");
	}

}
