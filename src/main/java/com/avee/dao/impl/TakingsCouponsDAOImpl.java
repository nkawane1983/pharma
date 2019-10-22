package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.TakingsCouponsDAO;
import com.avee.form.Cashing;
import com.avee.form.TakingCoupons;
import com.avee.utility.BeansUtility;

@Transactional
@Component
public class TakingsCouponsDAOImpl implements TakingsCouponsDAO {
	static final Logger logger = LogManager.getLogger(TakingsCouponsDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertTakingsCoupon(TakingCoupons takingCoupons, Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingCoupons.setBranchId(cashing.getBranchId());
			takingCoupons.setCreatedBy(cashing.getCreatedBy());
			takingCoupons.setCreatedDt(cashing.getCreatedDt());
			takingCoupons.setUpdatedDt(cashing.getUpdatedDt());

			com.avee.domain.TakingCoupons takingCouponsDomain = new com.avee.domain.TakingCoupons();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			bub.copy(takingCouponsDomain, takingCoupons, exclusionsList);
			session.save(takingCouponsDomain);

			tx.commit();
			message = "Cashing inserted successfully.";
		} catch (Exception e) {
			message = "Cashing can not be inserted.";
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
	public List<TakingCoupons> searchTakingsCoupon(int cashid) {
		Session session = null;
		Transaction tx = null;
		List<TakingCoupons> results=new ArrayList();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.TakingCoupons.class);
			criteria.add(Restrictions.eq("cashId", cashid));
				
			
			List<com.avee.domain.TakingCoupons> takingCouponsList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			
			for (com.avee.domain.TakingCoupons takingCouponsDomain : takingCouponsList) {
				TakingCoupons takingCouponsForm = new TakingCoupons();
												
				bub.copy(takingCouponsForm, takingCouponsDomain, exclusionsList);
				results.add(takingCouponsForm);
				
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
		return results;
	}

	@Override
	public void deleteTakingCoupons(int id) {
		logger.info("in in TakingCouponsDAO deleteTakingCoupons. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.TakingCoupons domain = (com.avee.domain.TakingCoupons) session.get(com.avee.domain.TakingCoupons.class, id);

			if (domain != null) {
				session.delete(domain);
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
		logger.info("in TakingCouponsDAO deleteTakingCoupons.. end");
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateTakingCoupons(TakingCoupons takingCoupons, Cashing cashing) {
		logger.info("in TakingCouponsDAO updateTakingCoupons. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingCoupons.setCashId(cashing.getId());
			takingCoupons.setBranchId(cashing.getBranchId());
			takingCoupons.setUpdatedBy(cashing.getUpdatedBy());
			takingCoupons.setUpdatedDt(cashing.getUpdatedDt());
			com.avee.domain.TakingCoupons domain = (com.avee.domain.TakingCoupons) session.get(com.avee.domain.TakingCoupons.class,
					takingCoupons.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, takingCoupons, exclusionsList);
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
		logger.info("in TakingCouponsDAO updateTakingCoupons.. end");
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TakingCoupons getTakingCoupons(int id) {
		Session session = null;
		Transaction tx = null;
		TakingCoupons takingCoupons = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.TakingCoupons domain = (com.avee.domain.TakingCoupons) session.get(com.avee.domain.TakingCoupons.class, id);
			if (domain != null) {
				takingCoupons = new TakingCoupons();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(takingCoupons, domain, exclusionsList);
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
		return takingCoupons;
	}



}
