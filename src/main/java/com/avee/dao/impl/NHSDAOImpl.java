package com.avee.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.NHSDAO;
import com.avee.domain.AppUser;
import com.avee.form.BankingOutstanging;
import com.avee.form.NHS;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class NHSDAOImpl implements NHSDAO {

	static final Logger logger = LogManager.getLogger(NHSDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertNHSScript(NHS nhs) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.NHS domain = new com.avee.domain.NHS();
			bub.copy(domain, nhs, exclusionsList);

			session.save(domain);
			tx.commit();

			message = "NHS inserted successfully.";

		} catch (Exception e) {
			message = "NHS can not be inserted.";
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
	public List<NHS> searchNHSScript(Map<String, String> map) {

		List<NHS> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String branchId = map.get("branchId");
			// System.out.println(fdate);

			Criteria criteria = session.createCriteria(com.avee.domain.NHS.class);

			if (strUtility.checkNullOrEmptyString(fdate) && strUtility.checkNullOrEmptyString(tdate)
					&& strUtility.checkNullOrEmptyString(branchId)) {

				Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
				Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);
				logger.info("in NHSDAO . start " + fDate + tDate);
				criteria.add(Restrictions.between("date", fDate, tDate))
						.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			} else {
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			}
			criteria.addOrder(Order.asc("date"));
			List<com.avee.domain.NHS> nhsList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.NHS nhsDomain : nhsList) {
				NHS nhsForm = new NHS();
				bub.copy(nhsForm, nhsDomain, exclusionsList);
				int totalForm=0;
				int totalItems=0;
				totalForm=nhsForm.getPaidForm()+nhsForm.getPaidFormOld()+nhsForm.getExemptForm()+nhsForm.getContraceptiveForm();
				totalItems=nhsForm.getPaidItem()+nhsForm.getPaidItemOld()+nhsForm.getExemptItem()+nhsForm.getContraceptiveItem();
				nhsForm.setWeekNo(strUtility.getWeekNumber(nhsForm.getDate()));
				nhsForm.setTotalForm(totalForm);
				nhsForm.setTotalItems(totalItems);
				list.add(nhsForm);

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
		logger.info("in NHSDAO . End");
		return list;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public NHS getNHSScript(int id) {
		Session session = null;
		Transaction tx = null;
		NHS form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.NHS domain = (com.avee.domain.NHS) session.get(com.avee.domain.NHS.class, id);
			if (domain != null) {
				form = new NHS();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateNHSScript(NHS nhs, String mode) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.NHS domain = (com.avee.domain.NHS) session.get(com.avee.domain.NHS.class, nhs.getId());

			if (domain != null) {
				if (mode.equalsIgnoreCase("nhsUpdate")) {
					List<String> exclusionsList = new ArrayList<String>();
					BeansUtility bub = new BeansUtility();
					bub.copy(domain, nhs, exclusionsList);
				}else
				{
					domain.setUpdatedBy(nhs.getUpdatedBy());
					domain.setUpdatedDt(nhs.getUpdatedDt());
					domain.setSubMisPatients(nhs.getSubMisPatients());
					domain.setRepDisPatients(nhs.getRepDisPatients());
				}
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
	}

	@Override
	public void deleteNHSScript(int id) {
		logger.info("in NHSDAO deleteNHSScript. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.NHS domain = (com.avee.domain.NHS) session.get(com.avee.domain.NHS.class, id);

			if (domain != null) {
				session.delete(domain);
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
		logger.info("in NHSDAO deleteNHSScript.. end");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> searchRemainingNHSScript(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			String branchId = map.get("branchId");
			String cp = map.get("cp");
		
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));

			ProcedureCall call = session.createStoredProcedureCall("pharma.getremainingnhs");
		
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(branchId));
			call.registerParameter(2, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(cp));
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();

			results = output.getResultList();
			
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
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getNhsStats(Map<String, String> map) {
		List<Object[]> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String groupIdObj = map.get("groupIdObj");
			String branchIdObj = map.get("branchIdObj");
			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String uid = map.get("useridObj");

			Query query;
			if (strUtility.checkNullOrEmptyString(groupIdObj) && !groupIdObj.equals("0") && branchIdObj.equals("0")) {
				query = session.getNamedQuery("HQL_GETSCRIPTITEMSBY_GroupID");
				query.setInteger("groupId", Integer.parseInt(groupIdObj));
			} else if (strUtility.checkNullOrEmptyString(branchIdObj) && !branchIdObj.equals("0")) {
				query = session.getNamedQuery("HQL_GETSCRIPTITEMSBY_BranchID");
				query.setInteger("branchId", Integer.parseInt(branchIdObj));

			} else {

				query = session.getNamedQuery("HQL_GETSCRIPTITEMSBY_ALL");
				query.setString("uId", uid);

			}
			query.setDate("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(fdate));
			query.setDate("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(tdate));
			list = query.list();
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

	@SuppressWarnings("unchecked")
	@Override
	public boolean nhsExistOrNot(Map<String, String> map) {
		logger.info("in NHSDAO nhsExistOrNot.. start");
		Session session = null;
		Transaction tx = null;
		boolean check = false;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String nhsdate = map.get("nhsdate");
			String branchid = map.get("branchid");

			Criteria criteria = session.createCriteria(com.avee.domain.NHS.class);

			if (strUtility.checkNullOrEmptyString(nhsdate) && strUtility.checkNullOrEmptyString(branchid)) {

				Date cdate = new SimpleDateFormat("yyyy-MM-dd").parse(nhsdate);

				Criterion date = Restrictions.eq("date", cdate);
				Criterion branchId = Restrictions.eq("branchId", Integer.parseInt(branchid));
				LogicalExpression andexp = Restrictions.and(date, branchId);
				criteria.add(andexp);
			}
			List<com.avee.domain.NHS> nhsList = criteria.list();

			if (nhsList != null && !(nhsList.isEmpty()) && nhsList.size() > 0) {
				check = true;
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
		logger.info("in NHSDAO nhsExistOrNot.. end");
		return check;
	}

	@Override
	public String getPrivatePrescriptionValueForNHS(Map<String, String> map) {
		String privatePrescriptionValue = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int branchId = Integer.parseInt(map.get("branchId"));
			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Query query = session.getNamedQuery("getPrivatePrescriptionValueForNHS");
			query.setInteger("branchId", branchId);
			query.setDate("fdate", fDate);

			privatePrescriptionValue = (String) query.uniqueResult();

			if (!strUtility.checkNullOrEmptyString(privatePrescriptionValue))
				privatePrescriptionValue = "0.0";

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
		return privatePrescriptionValue;
	}

	@Override
	public NHS getLastNHSScript(Map<String, String> map) {

		return (null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int checkRemainingNHS(Map<String, String> map) {
		int msg = 0;
		Session session = null;
		Transaction tx = null;
		List<Object> obj=null;
		logger.info("in checkRemainingNHS . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String branchId = map.get("branchId");
			String cp = map.get("cp");

			Query qry  = session.getNamedQuery("checkRemainingNHS");
			
			qry.setInteger("branchID", Integer.parseInt(branchId));
			qry.setDate("fdate", fdate);
			qry.setDate("tdate", tdate);
			qry.setInteger("cp", Integer.parseInt(cp));
			obj=qry.list();
			
			if(obj!=null && obj.size()>0)
			{
				msg=Integer.parseInt(obj.get(0).toString());
			}
			tx.commit();
			//msg = "SUCCESS";

		} catch (Exception e) {
			//msg = "ERROR";
			e.printStackTrace();
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return msg;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String amendUpdateNHSScript(NHS nhs) {
		Session session = null;
		Transaction tx = null;
		String strObj="";
		logger.info("in amendUpdateNHSScript . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.NHS domain = (com.avee.domain.NHS) session.get(com.avee.domain.NHS.class, nhs.getId());

			if (domain != null) {
		
					List<String> exclusionsList = new ArrayList<String>();
					BeansUtility bub = new BeansUtility();
					bub.copy(domain, nhs, exclusionsList);
		
				session.update(domain);
				tx.commit();
				strObj = "SUCCESS";
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			strObj = "ERROR";
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return strObj;
	}

}
