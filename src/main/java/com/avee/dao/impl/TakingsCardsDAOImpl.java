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

import com.avee.dao.TakingsCardsDAO;
import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.form.TakingsCards;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class TakingsCardsDAOImpl implements TakingsCardsDAO {
	static final Logger logger = LogManager.getLogger(TakingsCardsDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertTakingsCard(TakingsCards takingsCards, Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingsCards.setBranchId(cashing.getBranchId());
			takingsCards.setCreatedBy(cashing.getCreatedBy());
			takingsCards.setCreatedDt(cashing.getCreatedDt());
			takingsCards.setUpdatedDt(cashing.getUpdatedDt());

			com.avee.domain.TakingsCards takingsCardsDomain = new com.avee.domain.TakingsCards();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			bub.copy(takingsCardsDomain, takingsCards, exclusionsList);
			session.save(takingsCardsDomain);

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
	public List<TakingsCards> searchTakingsCard(int cashid) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCards> results = new ArrayList();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCards.class);
			criteria.add(Restrictions.eq("cashId", cashid));

			List<com.avee.domain.TakingsCards> takingsCardsList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.TakingsCards takingsCardshDomain : takingsCardsList) {
				TakingsCards takingsCardsForm = new TakingsCards();

				bub.copy(takingsCardsForm, takingsCardshDomain, exclusionsList);
				results.add(takingsCardsForm);

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
	public void deleteTakingsCards(int id) {
		logger.info("in in TakingsCardsDAO deleteTakingsCards. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.TakingsCards domain = (com.avee.domain.TakingsCards) session
					.get(com.avee.domain.TakingsCards.class, id);

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
		logger.info("in TakingsCardsDAO deleteTakingsCards.. end");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateTakingsCards(TakingsCards takingsCards, Cashing cashing) {
		logger.info("in TakingsCardsDAO updateTakingsCards. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingsCards.setCashId(cashing.getId());
			takingsCards.setBranchId(cashing.getBranchId());
			takingsCards.setUpdatedBy(cashing.getUpdatedBy());
			takingsCards.setUpdatedDt(cashing.getUpdatedDt());
			com.avee.domain.TakingsCards domain = (com.avee.domain.TakingsCards) session
					.get(com.avee.domain.TakingsCards.class, takingsCards.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, takingsCards, exclusionsList);
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
		logger.info("in TakingsCardsDAO updateTakingsCards.. end");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TakingsCards getTakingsCards(int id) {
		Session session = null;
		Transaction tx = null;
		TakingsCards takingsCards = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.TakingsCards domain = (com.avee.domain.TakingsCards) session
					.get(com.avee.domain.TakingsCards.class, id);
			if (domain != null) {
				takingsCards = new TakingsCards();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(takingsCards, domain, exclusionsList);
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
		return takingsCards;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TakingsCards> unBankingCardsAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCards> results = new ArrayList<>();
		logger.info("in TakingsCardsDAO bankingCardsAsList.. strat");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cashId = map.get("cashid");
			String branchId = map.get("branchid");
			String bankId = map.get("bankid");
			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCards.class);
			if (strUtility.checkNullOrEmptyString(cashId) && strUtility.checkNullOrEmptyString(branchId)
					&& strUtility.checkNullOrEmptyString(bankId)) {
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)))
						.add(Restrictions.eq("cashId", Integer.parseInt(cashId)))
						.add(Restrictions.eq("bankingId", Integer.parseInt(bankId)));
			}

			List<com.avee.domain.TakingsCards> takingsCardsList = criteria.list();
			
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			if (takingsCardsList != null && takingsCardsList.size() > 0) {
				for (com.avee.domain.TakingsCards takingsCardsDomain : takingsCardsList) {
					TakingsCards takingsCardsForm = new TakingsCards();
					bub.copy(takingsCardsForm, takingsCardsDomain, exclusionsList);
					results.add(takingsCardsForm);

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

	@Override
	public void updateBankingIdInTakingsCards(int cashid, Banking banking) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//String hql = "UPDATE TakingsCards t SET t.updatedBy= :updatedBy1, t.updatedDt= :updatedDt1,t.bankingId= :bankid WHERE t.cashId=:cashid";
			Query query = session.getNamedQuery("updateBankingIdInTakingsCards");
			query.setString("updatedBy1", banking.getCreatedBy());
			query.setTimestamp("updatedDt1", banking.getCreatedDt());
			query.setInteger("bankid", banking.getId());
			query.setInteger("cashid", cashid);
			query.executeUpdate();
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

}
