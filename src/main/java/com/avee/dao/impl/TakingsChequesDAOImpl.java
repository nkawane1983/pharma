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

import com.avee.dao.TakingsChequesDAO;
import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.form.TakingsCheques;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;
@Transactional
@Component
public class TakingsChequesDAOImpl implements TakingsChequesDAO{
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
	public String insertTakingsCheques(TakingsCheques takingsCheques, Cashing cashing) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingsCheques.setBranchId(cashing.getBranchId());
			takingsCheques.setCreatedBy(cashing.getCreatedBy());
			takingsCheques.setCreatedDt(cashing.getCreatedDt());
			takingsCheques.setUpdatedDt(cashing.getUpdatedDt());
			
			com.avee.domain.TakingsCheques takingsChequesDomain = new com.avee.domain.TakingsCheques();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			
			bub.copy(takingsChequesDomain, takingsCheques, exclusionsList);
			session.save(takingsChequesDomain);
			
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
	public List<TakingsCheques> searchTakingsCheques(int cashid) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCheques> results=new ArrayList();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCheques.class);
			criteria.add(Restrictions.eq("cashId", cashid));
				
			
			List<com.avee.domain.TakingsCheques> takingsChequesList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			
			for (com.avee.domain.TakingsCheques takingsChequesDomain : takingsChequesList) {
				TakingsCheques takingsChequesForm = new TakingsCheques();
												
				bub.copy(takingsChequesForm, takingsChequesDomain, exclusionsList);
				results.add(takingsChequesForm);
				
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
	public void deleteTakingsCheques(int id) {
		logger.info("in TakingsChequesDAO deleteTakingsCheques. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.TakingsCheques domain = (com.avee.domain.TakingsCheques) session.get(com.avee.domain.TakingsCheques.class, id);

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
		logger.info("in TakingsChequesDAO deleteTakingsCheques.. end");
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateTakingsCheques(TakingsCheques takingsCheques,Cashing cashing) {
		logger.info("in TakingsChequesDAO updateTakingsCheques. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			takingsCheques.setCashId(cashing.getId());
			takingsCheques.setBranchId(cashing.getBranchId());
			takingsCheques.setUpdatedBy(cashing.getUpdatedBy());
			takingsCheques.setUpdatedDt(cashing.getUpdatedDt());
			com.avee.domain.TakingsCheques domain = (com.avee.domain.TakingsCheques) session.get(com.avee.domain.TakingsCheques.class,
					takingsCheques.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, takingsCheques, exclusionsList);
				session.update(domain);
				tx.commit();
			}else
			{
				//System.out.println();
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
		logger.info("in TakingsChequesDAO updateTakingsCheques.. end");
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public TakingsCheques getTakingsCheques(int id) {
		Session session = null;
		Transaction tx = null;
		TakingsCheques takingsCheques = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.TakingsCheques domain = (com.avee.domain.TakingsCheques) session.get(com.avee.domain.TakingsCheques.class, id);
			if (domain != null) {
				takingsCheques = new TakingsCheques();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(takingsCheques, domain, exclusionsList);
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
		return takingsCheques;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TakingsCheques> unBankingChequesAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCheques> results=new ArrayList<>();
		List<Object[]> result = null;
		logger.info("in TakingsChequesDAO unBankingChequesAsList.. strat");
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cashId=map.get("cashid");
			int branchId=Integer.parseInt(map.get("branchId"));
			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			Query query;
			if(cashId.equals("deall"))
			{
				cashId="0";
			}
			if(cashId.equals("all"))
			{
				query= session.getNamedQuery("unBankingCheques_All");
				query.setInteger("branchID", branchId);
				query.setDate("fdate", fDate);
				query.setDate("tdate", tDate);
			}
			else
			{
				query= session.getNamedQuery("unBankingChequesBY_CashID");
				query.setString("cashID", cashId); 
			}
			result = query.list();
			for(int i=0;i<result.size();i++)
			{
				TakingsCheques takingsCheques=new TakingsCheques();
				takingsCheques.setAccountNo(result.get(i)[0].toString());
				takingsCheques.setCname(result.get(i)[1].toString());
				takingsCheques.setAmount(Double.parseDouble(result.get(i)[2].toString()));
				results.add(takingsCheques);
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
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Override
	public List<TakingsCheques> bankingChequesAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<TakingsCheques> results=new ArrayList<>();
		logger.info("in TakingsChequesDAO bankingChequesAsList.. strat");
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String cashId=map.get("cashid");
			String branchId=map.get("branchid");
			String bankId=map.get("bankid");
			Criteria criteria = session.createCriteria(com.avee.domain.TakingsCheques.class);
			if (strUtility.checkNullOrEmptyString(cashId) && strUtility.checkNullOrEmptyString(branchId)&&strUtility.checkNullOrEmptyString(bankId)) {
			criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId))).add(Restrictions.eq("cashId", Integer.parseInt(cashId))).add(Restrictions.eq("bankingId", Integer.parseInt(bankId)));
			}
				
			
			List<com.avee.domain.TakingsCheques> takingsChequesList = criteria.list();
			////System.out.println(TakingsChequesList.size());
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			if(takingsChequesList!=null && takingsChequesList.size()>0){
			for (com.avee.domain.TakingsCheques takingsChequesDomain : takingsChequesList) {
				TakingsCheques takingsChequesForm = new TakingsCheques();
												
				bub.copy(takingsChequesForm, takingsChequesDomain, exclusionsList);
				results.add(takingsChequesForm);
				
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
	public void updateBankingIdInTakingsCheques(int cashid, Banking banking) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//String hql = "UPDATE TakingsCheques t SET t.updatedBy= :updatedBy1, t.updatedDt= :updatedDt1,t.bankingId= :bankid WHERE t.cashId=:cashid";
			Query query = session.getNamedQuery("updateBankingIdInTakingsCheques");
			query.setString("updatedBy1",banking.getCreatedBy());
			query.setTimestamp("updatedDt1",banking.getCreatedDt());
			query.setInteger("bankid",banking.getId());
			query.setInteger("cashid",cashid);
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
