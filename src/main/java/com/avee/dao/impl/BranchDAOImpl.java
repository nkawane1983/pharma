package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.BranchDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.Branch;
import com.avee.form.BranchDetails;

import com.avee.utility.BeansUtility;

import com.avee.utility.StringUtility;

@Transactional
@Component
public class BranchDAOImpl  implements BranchDAO {

	
	static final Logger logger = LogManager.getLogger(BranchDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertBranchDetails(BranchDetails branchDetails) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.BranchDetails domain = new com.avee.domain.BranchDetails();
			bub.copy(domain, branchDetails, exclusionsList);

			session.save(domain);
			tx.commit();

			message = "BranchDetails inserted successfully.";

		} catch (Exception e) {
			message = "BranchDetails can not be inserted.";
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

	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	@Override
	public List<BranchDetails> searchBranchDetails(Map<String, String> map) {
		List<BranchDetails> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String branchName = map.get("branchName");
			String groupname = map.get("groupname");
			String gid = map.get("groupId");
			String bid = map.get("branchId");
			Criteria criteria = session.createCriteria(com.avee.domain.BranchDetails.class);

			if (strUtility.checkNullOrEmptyString(branchName)) {
				criteria.add(Restrictions.ilike("description", "%" + branchName + "%"));
			}
			if (strUtility.checkNullOrEmptyString(gid)) {
				criteria.add(Restrictions.eq("groupId", Integer.parseInt(gid)));
			}
			if (strUtility.checkNullOrEmptyString(bid)) {
				criteria.add(Restrictions.eq("id", Integer.parseInt(bid)));
			}
			/*
			 * if(strUtility.checkNullOrEmptyString(distance)){
			 * criteria.add(Restrictions.eq("rolId",Integer.parseInt(distance)))
			 * ; }
			 */
			criteria.add(Restrictions.eq("isActive", true));
			List<com.avee.domain.BranchDetails> branchDetailsList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.BranchDetails branchDetailsdomain : branchDetailsList) {
				BranchDetails branchDetailsForm = new BranchDetails();
				bub.copy(branchDetailsForm, branchDetailsdomain, exclusionsList);
				list.add(branchDetailsForm);

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
	public BranchDetails getBranchDetails(int id) {
		Session session = null;
		Transaction tx = null;
		BranchDetails form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.BranchDetails domain = (com.avee.domain.BranchDetails) session
					.get(com.avee.domain.BranchDetails.class, id);
			if (domain != null) {
				form = new BranchDetails();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(form, domain, exclusionsList);

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
		return form;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateBranch(Branch branch) {
		logger.info("in BranchDAO - updateBranch.. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Branch domain = (com.avee.domain.Branch) session.get(com.avee.domain.Branch.class,
					branch.getId());

			if (domain != null) {
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				bub.copy(domain, branch, exclusionsList);
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
		logger.info("in BranchDAO updateBranch.. end");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String updateBranchDetails(BranchDetails branchDetails, String operation) {
		logger.info("in BranchDAO - updateBranchDetails.. start");
		Session session = null;
		Transaction tx = null;
		String massege="ERROR";
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			/// System.out.println(branchDetails.getId());
			com.avee.domain.BranchDetails domain = (com.avee.domain.BranchDetails) session
					.get(com.avee.domain.BranchDetails.class, branchDetails.getId());

			if (domain != null) {
				if (operation.equals("update")) {
					List<String> exclusionsList = new ArrayList<String>();
					BeansUtility bub = new BeansUtility();
					bub.copy(domain, branchDetails, exclusionsList);
				}
				if(operation.equals("delete"))
				{
					domain.setIsActive(false);
				}
				session.update(domain);
				tx.commit();
			}
			massege="SUCCESS";

		} catch (Exception e) {
			e.printStackTrace();
			massege="ERROR";
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		logger.info("in BranchDAO updateBranchDetails.. end");
		return massege;

	}

	@Override
	public void deleteBranch(int id) {
		logger.info("in BranchDAO - deleteBranch.. start");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.Branch domain = (com.avee.domain.Branch) session.get(com.avee.domain.Branch.class, id);

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
		logger.info("in BranchDAO deleteBranch.. end");

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BranchDetails> getBranchDetailsList(int id) {
		List<BranchDetails> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.BranchDetails.class);

			if (strUtility.isEmptyInteger(id) && id != 0) {
				criteria.add(Restrictions.eq("groupId", id));
				criteria.addOrder(Order.asc("id"));
				criteria.add(Restrictions.eq("isActive", true));
			}

			List<com.avee.domain.BranchDetails> branchDetailsList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.BranchDetails branchDetailsdomain : branchDetailsList) {
				BranchDetails branchDetailsForm = new BranchDetails();
				bub.copy(branchDetailsForm, branchDetailsdomain, exclusionsList);
				list.add(branchDetailsForm);

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchDetailsByDefaultId(String id) {
		Session session = null;
		Transaction tx = null;

		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (strUtility.checkNullOrEmptyString(id)) {
				String hql = "Select bd.id as bid,bd.description as bname,gd.id as gid,gd.description as gname FROM pharma.branch_details bd,pharma.group_details gd where bd.group_id=gd.id and bd.id In(SELECT branch_id FROM pharma.branch_defaults where value_id="
						+ Integer.parseInt(id) + " and   now() between start_date and expiry_date)";
				SQLQuery query = session.createSQLQuery(hql);

				result = query.list();

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
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> branchDetailslist(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		String hql = null;
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int branchId = Integer.parseInt(map.get("branchid"));
			int groupId = Integer.parseInt(map.get("groupid"));

			if (groupId == 0 && branchId == 0) {
				hql = "Select description from pharma.branch_details order by id";
			} else if (groupId != 0 && branchId == 0) {
				hql = "Select description from pharma.branch_details where group_id=" + groupId + " order by id";
			} else {
				hql = "Select description from pharma.branch_details where id=" + branchId + " order by id";

			}
			SQLQuery query = session.createSQLQuery(hql);
			result = query.list();

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
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> branchDetailsAssolist(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		String hql = null;
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int branchId = Integer.parseInt(map.get("branchid"));
			int groupId = Integer.parseInt(map.get("groupid"));
			// System.out.println(branchId+"--"+groupId);
			if (groupId == 0 && branchId == 0) {
				// hql = "Select description from pharma.branch_details order by
				// id";
			} else if (groupId != 0 && branchId == 0) {
				// hql = "Select description from pharma.branch_details where
				// group_id=" + groupId + " order by id";
			} else {
				hql = "select user_id from pharma.branch_user_details where branch_id=" + branchId
						+ " order by user_id";

			}
			SQLQuery query = session.createSQLQuery(hql);
			result = query.list();

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
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AppUserGroupBranchMapping> getBranchDetailsaAsList(Map<String, String> map) {
		logger.info("in BranchDAO getBranchDetailsaAsList.. Start");

		List<AppUserGroupBranchMapping> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String uid = map.get("uid");
			String branchName = map.get("branchName");
			String groupname = map.get("groupname");
			String groupId = map.get("groupId");
			String branchId = map.get("branchID");
			Query qry = null;
			if (strUtility.checkNullOrEmptyString(uid)
					&& (strUtility.checkNullOrEmptyString(branchName) || strUtility.checkNullOrEmptyString(groupname)
							|| (strUtility.checkNullOrEmptyString(groupId) && !groupId.equals("0"))
							|| (strUtility.checkNullOrEmptyString(branchId) && !branchId.equals("0")))) {
				qry = session.getNamedQuery("getBranchDetailsByBranchNameOrGroupNameAsList");

				if (!strUtility.checkNullOrEmptyString(branchName))
					branchName = "";
				else
					branchName += "%";
				if (!strUtility.checkNullOrEmptyString(groupname))
					groupname = "";
				else
					groupname += "%";

				qry.setString("branchName", branchName);
				qry.setString("groupname", groupname);

				qry.setInteger("groupId", Integer.parseInt(groupId));
				qry.setInteger("branchId", Integer.parseInt(branchId));
			} else {

				qry = session.getNamedQuery("getBranchDetailsByUIdAsList");
			}
			qry.setString("uId", uid);

			List<com.avee.domain.AppUserGroupBranchMapping> branchDetailsList = qry.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.AppUserGroupBranchMapping branchDetailsdomain : branchDetailsList) {
				AppUserGroupBranchMapping branchDetailsForm = new AppUserGroupBranchMapping();
				bub.copy(branchDetailsForm, branchDetailsdomain, exclusionsList);
				list.add(branchDetailsForm);

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
		logger.info("in BranchDAO getBranchDetailsaAsList.. END");
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkBranchWorkingDayOrNo(Map<String, String> map) {
		boolean msg = false;
		Session session = null;
		Transaction tx = null;

		logger.info("in checkBranchWorkingDayOrNo . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List oRecs = null;
			int branchId = Integer.parseInt(map.get("branchId"));
			Date eventdate = strUtility.covertStringToDate(map.get("eventdate"), "yyyy-MM-dd");

			SQLQuery query = (SQLQuery) session.getNamedQuery("checkBranchWorkingDayOrNo");
			query.setInteger("branchId", branchId);
			query.setDate("eventdate", eventdate);
			oRecs = query.list();
			tx.commit();

			if (oRecs != null && oRecs.size() > 0) {

				if (String.valueOf(oRecs.get(0)).equals("0"))
					msg = true;
				else
					msg = false;
			}

		} catch (Exception e) {
			msg = false;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BranchDetails> getBranchDetailsASList(String id,String mode,String userId,String type) {
		logger.info("in BranchDAO getBranchDetailsASList.. Start");

		List<BranchDetails> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

		
			Query qry = null;
			if(mode.equalsIgnoreCase("all")){
				qry=session.getNamedQuery("getbranchDetailsAll");
			}
			if(type.equalsIgnoreCase("dropdown")){
				
				if(mode.equalsIgnoreCase("groupId")){
					qry=session.getNamedQuery("getbranchDetailsGroupIdDropdown");
					qry.setString("id", id);
				}
			}else
			{
				if(mode.equalsIgnoreCase("groupId")){
					qry=session.getNamedQuery("getbranchDetailsGroupId");
					qry.setString("id", id);
				}
			}
			if(mode.equalsIgnoreCase("branchId")){
				qry=session.getNamedQuery("getbranchDetailsBranchId");
				qry.setString("id", id);
			}
			
			qry.setString("useid",userId);
			List<com.avee.domain.BranchDetails> branchDetailsList = qry.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.BranchDetails branchDetailsdomain : branchDetailsList) {
				BranchDetails branchDetailsForm = new BranchDetails();
				bub.copy(branchDetailsForm, branchDetailsdomain, exclusionsList);
				list.add(branchDetailsForm);

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
		logger.info("in BranchDAO getBranchDetailsASList.. END");
		return list;
	}

}
