package com.avee.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.BankingDAO;
import com.avee.form.Banking;
import com.avee.form.BankingOutstanging;
import com.avee.form.Cashing;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class BankingDAOImpl implements BankingDAO {
	protected static final String SUCCESS = "SUCCESS";
	protected static final String ERROR = "ERROR";
	static final Logger logger = LogManager.getLogger(BankingDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertBanking(Banking banking, String outsatanding, String cfamount, String cashid,
			List<Cashing> unBanking, String outstandingId) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Banking domain = new com.avee.domain.Banking();
			bub.copy(domain, banking, exclusionsList);

			session.save(domain);

			session.buildLockRequest(new LockOptions(LockMode.PESSIMISTIC_WRITE)).lock(domain);

            
			if (!strUtility.checkNullOrEmptyString(outsatanding))
				outsatanding = "0.0";

			if (!strUtility.checkNullOrEmptyString(cfamount))
				cfamount = "0.0";

			BankingOutstanging bankingOutstanging = new BankingOutstanging();

			bankingOutstanging.setBankDate(banking.getBankDate());
			bankingOutstanging.setAmount(Double.parseDouble(cfamount));
			bankingOutstanging.setOutstanging(Double.parseDouble(outsatanding));
			bankingOutstanging.setBankingId(domain.getId());
			bankingOutstanging.setBranchId(banking.getBranchId());
			bankingOutstanging.setCreatedBy(banking.getCreatedBy());
			bankingOutstanging.setCreatedDt(banking.getCreatedDt());
			bankingOutstanging.setUpdatedDt(banking.getCreatedDt());

			if (strUtility.checkNullOrEmptyString(outstandingId) && !outstandingId.equalsIgnoreCase("0")) {
				com.avee.domain.BankingOutstanging outstandingIdDomain = (com.avee.domain.BankingOutstanging) session
						.get(com.avee.domain.BankingOutstanging.class, Integer.parseInt(outstandingId));

				if (outstandingIdDomain != null) {
					outstandingIdDomain.setStatus("1");
					session.update(outstandingIdDomain);
				}

				bankingOutstanging.setStatus("0");
				com.avee.domain.BankingOutstanging bankingOutStangingDomain = new com.avee.domain.BankingOutstanging();
				bub.copy(bankingOutStangingDomain, bankingOutstanging, exclusionsList);
				session.save(bankingOutStangingDomain);
			}
			if (cashid.equals("all")) {
				if (unBanking != null) {
					for (int i = 0; i < unBanking.size(); i++) {

						Query queryCsh = session.getNamedQuery("updateBankingIdInCashing");
						queryCsh.setString("updatedBy1", banking.getCreatedBy());
						queryCsh.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCsh.setInteger("cashid", unBanking.get(i).getId());
						queryCsh.setInteger("bankid", domain.getId());
						queryCsh.executeUpdate();

						Query queryCheques = session.getNamedQuery("updateBankingIdInTakingsCheques");
						queryCheques.setString("updatedBy1", banking.getCreatedBy());
						queryCheques.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCheques.setInteger("bankid", domain.getId());
						queryCheques.setInteger("cashid", unBanking.get(i).getId());
						queryCheques.executeUpdate();

						Query queryCards = session.getNamedQuery("updateBankingIdInTakingsCards");
						queryCards.setString("updatedBy1", banking.getCreatedBy());
						queryCards.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCards.setInteger("bankid", domain.getId());
						queryCards.setInteger("cashid", unBanking.get(i).getId());
						queryCards.executeUpdate();
					}
				}
			} else {
				String[] temp = cashid.split(",");
				if (temp.length > 0) {
					for (int i = 0; i < temp.length; i++) {
						Query queryCsh = session.getNamedQuery("updateBankingIdInCashing");
						queryCsh.setString("updatedBy1", banking.getCreatedBy());
						queryCsh.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCsh.setInteger("cashid", Integer.parseInt(temp[i]));
						queryCsh.setInteger("bankid", domain.getId());
						queryCsh.executeUpdate();

						Query queryCheques = session.getNamedQuery("updateBankingIdInTakingsCheques");
						queryCheques.setString("updatedBy1", banking.getCreatedBy());
						queryCheques.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCheques.setInteger("bankid", domain.getId());
						queryCheques.setInteger("cashid", Integer.parseInt(temp[i]));
						queryCheques.executeUpdate();

						Query queryCards = session.getNamedQuery("updateBankingIdInTakingsCards");
						queryCards.setString("updatedBy1", banking.getCreatedBy());
						queryCards.setTimestamp("updatedDt1", banking.getCreatedDt());
						queryCards.setInteger("bankid", domain.getId());
						queryCards.setInteger("cashid", Integer.parseInt(temp[i]));
						queryCards.executeUpdate();

					}
				}
			}
			tx.commit();
			message = "SUCCESS";
			

		} catch (Exception e) {
			message = "ERROR";
			e.printStackTrace();
			if (tx != null)
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
	public List<Banking> searchBanking(Map<String, String> map) {
		List<Banking> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		logger.info("in BankingDAO . start ");
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String refNo = map.get("refNo");
			String branchId = map.get("branchId");

			Criteria criteria = session.createCriteria(com.avee.domain.Banking.class);

			if (strUtility.checkNullOrEmptyString(fdate) && strUtility.checkNullOrEmptyString(tdate)) {

				Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
				Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);

				criteria.add(Restrictions.between("bankDate", fDate, tDate))
						.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			} else {

				if (strUtility.checkNullOrEmptyString(refNo)) {
					criteria.add(Restrictions.eq("bankingRef", refNo))
							.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
				} else {
					criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
				}

			}
			criteria.add(Restrictions.eq("markDelete", false));
			List<com.avee.domain.Banking> bankingList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.Banking bankingdomain : bankingList) {
				Banking bankingForm = new Banking();
				bub.copy(bankingForm, bankingdomain, exclusionsList);
				double totalbanking = 0;
				totalbanking = bankingForm.getCash() + bankingForm.getCheques() + bankingForm.getMiscCash();
				bankingForm.setTotalBanking(totalbanking);
				bankingForm.setWeekNo(strUtility.getWeekNumber(bankingForm.getBankDate()));
				list.add(bankingForm);

			}

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
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Banking getBanking(int id) {
		Session session = null;
		Transaction tx = null;
		Banking form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.Banking domain = (com.avee.domain.Banking) session.get(com.avee.domain.Banking.class, id);
			if (domain != null) {
				form = new Banking();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(form, domain, exclusionsList);
			}
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
		return form;
	}

	@Override
	public void updateBanking(Banking banking) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBanking(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countTotalBanking(String refname, int type, int branchid) {
		Session session = null;
		Transaction tx = null;

		String bg1 = null;
		int bankingCount = 0;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (type == 0) {
				SQLQuery query = session
						.createSQLQuery("select count(*)+1 as refcount from pharma.banking where banking_ref LIKE '%"
								+ refname + "%' and branch_id=" + branchid);
				bg1 = query.uniqueResult().toString();

			} else if (type == 1) {
				SQLQuery query = session
						.createSQLQuery("select count(*)+1 as refcount from pharma.banking where banking_ref LIKE '%"
								+ refname.substring(0, 3) + "%' and branch_id=" + branchid);
				bg1 = query.uniqueResult().toString();

			} else {
				SQLQuery query = session.createSQLQuery("select max(id)+1 as refcount from pharma.banking");
				bg1 = query.uniqueResult().toString();
			}
			if (bg1 == null)
				bankingCount = 0;
			else
				bankingCount = Integer.parseInt(bg1);

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
		return bankingCount;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BankingOutstanging getOutstangingBanking(int branchid, int bankingid) {
		BankingOutstanging bankingOutstanging = new BankingOutstanging();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query qry = (Query) session
					.createQuery("from BankingOutstanging where branchId = :branchId and status = '0'");
			qry.setInteger("branchId", branchid);
			List oRecs = qry.list();
			if (oRecs.size() > 0) {
				com.avee.domain.BankingOutstanging domain = new com.avee.domain.BankingOutstanging();
				domain = (com.avee.domain.BankingOutstanging) qry.list().get(0);
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(bankingOutstanging, domain, exclusionsList);
			} else
				bankingOutstanging = new BankingOutstanging();
		} catch (Exception e) {
			e.printStackTrace();
			bankingOutstanging = new BankingOutstanging();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return (bankingOutstanging);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public BankingOutstanging getCarryForwordBanking(int branchid, int bankingid) {
		BankingOutstanging bankingOutstanging = new BankingOutstanging();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query qry = (Query) session.createQuery("from BankingOutstanging where bankingId = :bankingid ");
			qry.setInteger("bankingid", bankingid);
			List oRecs = qry.list();
			if (oRecs.size() > 0) {
				com.avee.domain.BankingOutstanging domain = new com.avee.domain.BankingOutstanging();
				domain = (com.avee.domain.BankingOutstanging) qry.list().get(0);
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(bankingOutstanging, domain, exclusionsList);
			} else
				bankingOutstanging = new BankingOutstanging();
			;
		} catch (Exception e) {
			e.printStackTrace();
			bankingOutstanging = new BankingOutstanging();
			;
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return (bankingOutstanging);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertCarryForwordBanking(BankingOutstanging bankingoutstanging, Banking banking) {
		String message = "";
		logger.info("in BankingDAO . insertCarryForwordBanking start ");
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			bankingoutstanging.setStatus("0");
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.BankingOutstanging domain = new com.avee.domain.BankingOutstanging();
			bub.copy(domain, bankingoutstanging, exclusionsList);

			session.save(domain);
			tx.commit();

			message = "";

		} catch (Exception e) {
			message = "Banking can not be inserted.";
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in BankingDAO insertCarryForwordBanking end ");
		return message;
	}

	@Override
	public void updateCarryForwordBanking(int id) {
		logger.info("in BranchDAO - updateBranch.. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.BankingOutstanging domain = (com.avee.domain.BankingOutstanging) session
					.get(com.avee.domain.BankingOutstanging.class, id);

			if (domain != null) {
				domain.setStatus("1");
				session.update(domain);
				tx.commit();
			}

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
		logger.info("in BranchDAO updateBranch.. end");

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public int checkBankingByStringComma(String cashId, int branchid) {
		int msg = 0;
		Session session = null;
		Transaction tx = null;
		List<Object> obj = null;
		logger.info("in checkBankingByStringComma . start ");
		try {
			session = sessionFactory.openSession();
		
			tx = session.beginTransaction();
			Query qry = session.getNamedQuery("checkBankingByStringComma");
			//qry.setLockOptions(LockOptions.READ);
			qry.setInteger("branchID", branchid);
			qry.setString("cashId", cashId);
			obj = qry.list();
			if (obj != null && obj.size() > 0) {
				msg = Integer.parseInt(obj.get(0).toString());
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
		return msg;
	}

}
