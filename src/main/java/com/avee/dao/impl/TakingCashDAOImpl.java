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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.TakingCashDAO;
import com.avee.form.Cashing;
import com.avee.form.TakingsCash;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class TakingCashDAOImpl implements TakingCashDAO {
	static final Logger logger = LogManager.getLogger(TakingCashDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertTakingCash(TakingsCash takingsCash, Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingsCash.setBranchId(cashing.getBranchId());
			takingsCash.setCreatedBy(cashing.getCreatedBy());
			takingsCash.setCreatedDt(cashing.getCreatedDt());
			takingsCash.setUpdatedDt(cashing.getUpdatedDt());

			com.avee.domain.TakingsCash takingsCashDomain = new com.avee.domain.TakingsCash();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			bub.copy(takingsCashDomain, takingsCash, exclusionsList);
			session.save(takingsCashDomain);

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
	public List<TakingsCash> searchTakingCash(int cashid) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCash> results = new ArrayList();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCash.class);
			criteria.add(Restrictions.eq("cashId", cashid)).addOrder(Order.asc("denominationId"));

			List<com.avee.domain.TakingsCash> takingsCashList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.TakingsCash takingsCashDomain : takingsCashList) {
				TakingsCash takingsCashForm = new TakingsCash();

				bub.copy(takingsCashForm, takingsCashDomain, exclusionsList);
				results.add(takingsCashForm);

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
	public void deleteTakingsCash(int id) {
		logger.info("in TakingsCashDAO deleteTakingsCash. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.TakingsCash domain = (com.avee.domain.TakingsCash) session
					.get(com.avee.domain.TakingsCash.class, id);

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
		logger.info("in TakingsCashDAO deleteTakingsCash.. end");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateTakingsCash(TakingsCash takingCash, Cashing cashing) {
		logger.info("in TakingsCashDAO updateTakingsCash. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingCash.setCashId(cashing.getId());
			takingCash.setBranchId(cashing.getBranchId());
			takingCash.setUpdatedBy(cashing.getUpdatedBy());
			takingCash.setUpdatedDt(cashing.getUpdatedDt());
			com.avee.domain.TakingsCash domain = (com.avee.domain.TakingsCash) session
					.get(com.avee.domain.TakingsCash.class, takingCash.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();

				bub.copy(domain, takingCash, exclusionsList);
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
		logger.info("in TakingsCashDAO updateTakingsCash. end");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public TakingsCash getTakingsCash(int id) {
		Session session = null;
		Transaction tx = null;
		TakingsCash takingCash = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.TakingsCash domain = (com.avee.domain.TakingsCash) session
					.get(com.avee.domain.TakingsCash.class, id);
			if (domain != null) {
				takingCash = new TakingsCash();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(takingCash, domain, exclusionsList);
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
		return takingCash;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TakingsCash> unBankingCashAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCash> results = new ArrayList<>();
		List<Object[]> result = null;
		logger.info("in TakingsCashsDAO unBankingCashAsList.. strat");

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
				query = session.getNamedQuery("unBankingCash_All");
				query.setInteger("branchID", branchId);
				query.setDate("fdate", fDate);
				query.setDate("tdate", tDate);
			} else {
				query = session.getNamedQuery("unBankingCashBY_CashID");
				query.setString("cashID", cashId);
			}
			
			result = query.list();
			for (int i = 0; i < result.size(); i++) {
				TakingsCash takingsCash = new TakingsCash();
				takingsCash.setQuantity(Integer.parseInt(result.get(i)[0].toString()));
				takingsCash.setDenominationId(Integer.parseInt(result.get(i)[1].toString()));
				results.add(takingsCash);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TakingsCash> bankingCashAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCash> results = new ArrayList<>();
		logger.info("in TakingsCashsDAO unBankingCashAsList.. strat");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cashId = map.get("cashid");
			String branchId = map.get("branchid");

			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCash.class);
			if (strUtility.checkNullOrEmptyString(cashId) && strUtility.checkNullOrEmptyString(branchId)) {
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)))
						.add(Restrictions.eq("cashId", Integer.parseInt(cashId)));
				//criteria.add((Criterion) Order.desc("denominationId"));
				criteria.addOrder(Order.asc("denominationId"));
			}

			List<com.avee.domain.TakingsCash> takingsCashList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			if (takingsCashList != null && takingsCashList.size() > 0) {
				for (com.avee.domain.TakingsCash takingsCashDomain : takingsCashList) {
					TakingsCash takingsCashForm = new TakingsCash();
					bub.copy(takingsCashForm, takingsCashDomain, exclusionsList);
					results.add(takingsCashForm);

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
		return results;
	}

}
