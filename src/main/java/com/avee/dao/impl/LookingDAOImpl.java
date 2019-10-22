package com.avee.dao.impl;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.olap4j.impl.ArrayMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.LookingDAO;
import com.avee.form.Cashing;
import com.avee.form.CountryCode;
import com.avee.form.Denomination;
import com.avee.form.GroupDetails;
import com.avee.form.Menu;
import com.avee.form.Version;
import com.avee.utility.BeansUtility;

import com.avee.utility.StringUtility;

@Transactional
@Component
public class LookingDAOImpl implements LookingDAO {
	static final Logger logger = LogManager.getLogger(LookingDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Denomination> getDenominationsAsList() {
		Session session = null;
		Transaction tx = null;
		List<Denomination> results = new ArrayList();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.Denomination.class);

			criteria.addOrder(Order.asc("displayOrder"));

			List<com.avee.domain.Denomination> denominationList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			for (com.avee.domain.Denomination denominationDomain : denominationList) {
				Denomination denominationForm = new Denomination();
				bub.copy(denominationForm, denominationDomain, exclusionsList);
				results.add(denominationForm);
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
		return results;
	}

	@Override
	public List<Denomination> getDenominationsAsList(int cashid) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean verifyGroupAndBranch(Map<String, String> map) {
		boolean verfiy = false;
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int groupId = Integer.parseInt(map.get("groupId"));
			String userId = map.get("userId");
			int branchId = Integer.parseInt(map.get("branchId"));

			/*
			 * String hql =
			 * "select DISTINCT b.id from pharma.group_user_details  a,pharma.branch_details b,pharma.branch_user_details c where (a.user_id='"
			 * + userId + "' and a.group_id=" + groupId +
			 * " and a.group_id=b.group_id and b.id=" + branchId +
			 * ") or (c.user_id='" + userId + "' and c.branch_id=" + branchId +
			 * " and c.branch_id=b.id and b.group_id=" + groupId + ")";
			 */
			// SQLQuery query = session.createSQLQuery(hql);
			Query query = session.getNamedQuery("verifyGroupAndBranch");
			query.setString("userId", userId);
			query.setInteger("groupId", groupId);
			query.setInteger("branchId", branchId);
			results = query.list();
			if (results != null && results.size() > 0) {
				verfiy = true;
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
		return verfiy;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CountryCode> getAllCountryAsList() {
		Session session = null;
		Transaction tx = null;
		List<CountryCode> countryCode = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.CountryCode.class);
			criteria.addOrder(Order.asc("countryName"));
			List<com.avee.domain.CountryCode> countryCodeList = criteria.list();
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			for (com.avee.domain.CountryCode countryCodeDomain : countryCodeList) {
				CountryCode countryCodeForm = new CountryCode();
				bub.copy(countryCodeForm, countryCodeDomain, exclusionsList);
				countryCode.add(countryCodeForm);
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
		return countryCode;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> userGuides(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String usertype = map.get("usertype");
			Query query = session.getNamedQuery("userGuides");
			query.setString("usertype", usertype);
			results = query.list();
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
	public List<Object[]> getGroupDefault(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// String usertype=map.get("usertype");
			String hql = "SELECT dv.default_value,dvv.default_name FROM pharma.group_defaults gd , pharma.default_values dv, pharma.default dvv where gd.group_id=4 and gd.value_id=dv.id and dv.default_id=dvv.id";
			SQLQuery query = session.createSQLQuery(hql);
			results = query.list();
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Menu> getMenuListByUserRole(String menutype) {
		Session session = null;
		Transaction tx = null;
		List<Menu> results = new ArrayList<Menu>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (strUtility.checkNullOrEmptyString(menutype)) {
				Criteria criteria = session.createCriteria(com.avee.domain.Menu.class);
				criteria.add(Restrictions.eq("menuType", menutype)).addOrder(Order.asc("menuOrderBy"));
				List<com.avee.domain.Menu> menuList = criteria.list();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				for (com.avee.domain.Menu menuDomain : menuList) {
					Menu menuForm = new Menu();
					bub.copy(menuForm, menuDomain, exclusionsList);
					results.add(menuForm);
				}

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
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getdashboard(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info("in LookingDAOImpl getdashboard.. strat");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String branchIdObj = map.get("branchIdObj");

			String groupIdObj = map.get("groupIdObj");

			String useridObj = map.get("useridObj");

			ProcedureCall call = session.createStoredProcedureCall("pharma.show_dashboardreport");

			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(branchIdObj));
			call.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(groupIdObj));
			call.registerParameter(3, String.class, ParameterMode.IN).bindValue(useridObj);
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
		logger.info("in LookingDAOImpl getdashboard.. End");
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getNhsChartDailyData(String gid, String bid) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> chartDataMap = new ArrayMap<>();

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query;
			if (strUtility.checkNullOrEmptyString(gid) && Integer.parseInt(gid) != 0 && Integer.parseInt(bid) == 0) {
				query = session.getNamedQuery("getNHSChart_By_GroupID");
				query.setInteger("groupId", Integer.parseInt(gid));
			} else if (strUtility.checkNullOrEmptyString(bid)
					&& (Integer.parseInt(gid) != 0 || Integer.parseInt(gid) == 0) && Integer.parseInt(bid) != 0) {
				query = session.getNamedQuery("getNHSChart_By_BranchId");
				query.setInteger("branchId", Integer.parseInt(bid));
			} else {
				query = session.getNamedQuery("getNHSChart_All");
			}

			List<Object[]> list = query.list();

			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);

				dataMap.put(arr[0].toString(), arr[1].toString());
			}
			Format formatter = new SimpleDateFormat("dd-MMM-yy");
			for (int i = 6; i >= 0; i--) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -i - 1);
				String date = formatter.format(calendar.getTime());
				// System.out.println(date);
				if (dataMap.containsKey(date)) {
					chartDataMap.put(date, dataMap.get(date));
				} else {
					chartDataMap.put(date, BigDecimal.ZERO);
				}
			}
			map.put("dateAndpaid", chartDataMap);
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
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTakingChartDailyData(String gid, String bid) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> chartDataMap = new ArrayMap<>();

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query;
			if (strUtility.checkNullOrEmptyString(gid) && Integer.parseInt(gid) != 0 && Integer.parseInt(bid) == 0) {
				query = session.getNamedQuery("getTakingChart_By_GroupID");
				query.setInteger("groupId", Integer.parseInt(gid));
			} else if (strUtility.checkNullOrEmptyString(bid)
					&& (Integer.parseInt(gid) != 0 || Integer.parseInt(gid) == 0) && Integer.parseInt(bid) != 0) {
				query = session.getNamedQuery("getTakingChart_By_BranchId");
				query.setInteger("branchId", Integer.parseInt(bid));
			} else {
				query = session.getNamedQuery("getTakingChart_All");
			}

			List<Object[]> list = query.list();

			for (int i = 0; i < list.size(); i++) {
				Object[] arr = (Object[]) list.get(i);

				dataMap.put(arr[0].toString(), arr[1].toString());
			}
			Format formatter = new SimpleDateFormat("dd-MMM-yy");
			for (int i = 6; i >= 0; i--) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -i - 1);
				String date = formatter.format(calendar.getTime());
				// System.out.println(date);
				if (dataMap.containsKey(date)) {
					chartDataMap.put(date, dataMap.get(date));
				} else {
					chartDataMap.put(date, BigDecimal.ZERO);
				}
			}
			map.put("dateAndTaking", chartDataMap);
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
		return map;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Version getLatestVersion() {
		Version from = new Version();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria criteria = session.createCriteria(com.avee.domain.Version.class);
			criteria.add(Restrictions.eq("status", 0));

			com.avee.domain.Version domain = (com.avee.domain.Version) criteria.uniqueResult();
			if (domain == null) {
				domain = new com.avee.domain.Version();
			}
			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			bub.copy(from, domain, exclusionsList);

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
		return from;
	}
}
