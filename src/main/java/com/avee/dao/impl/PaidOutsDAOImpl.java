package com.avee.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.avee.dao.PaidOutsDAO;
import com.avee.form.Cashing;
import com.avee.form.PaidOuts;
import com.avee.utility.BeansUtility;

@Transactional
@Component
public class PaidOutsDAOImpl implements PaidOutsDAO {
	static final Logger logger = LogManager.getLogger(PaidOutsDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertPaidOuts(PaidOuts paidouts, Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			paidouts.setBranchId(cashing.getBranchId());
			paidouts.setCreatedBy(cashing.getCreatedBy());
			paidouts.setCreatedDt(cashing.getCreatedDt());
			paidouts.setUpdatedDt(cashing.getUpdatedDt());

			com.avee.domain.PaidOuts paidoutsDomain = new com.avee.domain.PaidOuts();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			bub.copy(paidoutsDomain, paidouts, exclusionsList);
			session.save(paidoutsDomain);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<PaidOuts> searchPaidOuts(int cashid) {
		Session session = null;
		Transaction tx = null;
		List<PaidOuts> results = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.PaidOuts.class);
			criteria.add(Restrictions.eq("cashId", cashid));

			List<com.avee.domain.PaidOuts> paidOutsList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.PaidOuts paidOutsDomain : paidOutsList) {
				PaidOuts paidOutsForm = new PaidOuts();

				bub.copy(paidOutsForm, paidOutsDomain, exclusionsList);
				results.add(paidOutsForm);

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
	public void deletePaidOuts(int id) {
		logger.info("in PaidOutsDAO deletePaidOuts. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.PaidOuts domain = (com.avee.domain.PaidOuts) session.get(com.avee.domain.PaidOuts.class,
					id);

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
		logger.info("in PaidOutsDAO deletePaidOuts.. end");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updatePaidOuts(PaidOuts paidouts, Cashing cashing) {
		logger.info("in PaidOutsDAO updatePaidOuts. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			paidouts.setCashId(cashing.getId());
			paidouts.setBranchId(cashing.getBranchId());
			paidouts.setUpdatedBy(cashing.getUpdatedBy());
			paidouts.setUpdatedDt(cashing.getUpdatedDt());
			com.avee.domain.PaidOuts domain = (com.avee.domain.PaidOuts) session.get(com.avee.domain.PaidOuts.class,
					paidouts.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, paidouts, exclusionsList);
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
		logger.info("in PaidOutsDAO updatePaidOuts.. end");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PaidOuts getPaidOuts(int id) {
		Session session = null;
		Transaction tx = null;
		PaidOuts paidouts = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.PaidOuts domain = (com.avee.domain.PaidOuts) session.get(com.avee.domain.PaidOuts.class,
					id);
			if (domain != null) {
				paidouts = new PaidOuts();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(paidouts, domain, exclusionsList);
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
		return paidouts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaidOuts> unBankingPaidOutsAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<PaidOuts> results = new ArrayList<>();
		List<Object[]> result = null;
		logger.info("in TakingsCardsDAO unBankingPaidOutsAsList.. strat");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cashId = map.get("cashid");
			int branchId = Integer.parseInt(map.get("branchId"));
			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			Query query;
			if (cashId.equals("deall")) {
				cashId = "0";
			}
			if (cashId.equals("all")) {
				query = session.getNamedQuery("unBankingPaidOuts_ALL");
				query.setInteger("branchId", branchId);
				query.setDate("fdate", fDate);
				query.setDate("tdate", tDate);
			} else {
				query = session.getNamedQuery("unBankingPaidOuts_BYCashID");
				query.setString("cashId", cashId);
			}
			result = query.list();
			for (int i = 0; i < result.size(); i++) {
				PaidOuts paidOuts = new PaidOuts();
				paidOuts.setCtype(Integer.parseInt(result.get(i)[0].toString()));
				paidOuts.setAmount(Double.parseDouble(result.get(i)[1].toString()));
				results.add(paidOuts);
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

}
