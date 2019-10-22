package com.avee.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.UserDAO;
import com.avee.domain.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchUserAssocs;
import com.avee.form.GroupUserAssocs;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {
	static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "rawtypes" })
	public AppUser findByName(String szName) {
		AppUser usr = new AppUser();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Criteria qry = session.createCriteria(AppUser.class);
			qry.add(Restrictions.eq("usrName", szName).ignoreCase());
			qry.add(Restrictions.eq("usrIsactiveYn", "Y"));

			List oRecs = qry.list();
			if (oRecs.size() > 0) {
				usr = (AppUser) qry.list().get(0);
				/*
				 * if(usr.getUsrIsactiveYn().equals("Y")) {
				 * com.avee.form.AppUser usrForm=new com.avee.form.AppUser();
				 * BeansUtility beansUtility = new BeansUtility<>();
				 * beansUtility.copy(usrForm,usr, new ArrayList<>());
				 * usrForm.setBranchId(getBranchUserAssocsList(usr.getUsrId()));
				 * beansUtility.copy(usr,usrForm, new ArrayList<>()); }
				 */

			} else {
				usr = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			usr = null;
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return (usr);
	}

	@SuppressWarnings("rawtypes")
	public AppUser findByEmail(String szEmail) {
		AppUser usr = new AppUser();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query qry = (Query) session.createQuery("from AppUser where usrEmail = :usrEmail and usrIsactiveYn = 'Y'");
			qry.setString("usrEmail", szEmail);
			List oRecs = qry.list();
			if (oRecs.size() > 0) {
				usr = (AppUser) qry.list().get(0);
			} else
				usr = null;
		} catch (Exception e) {
			e.printStackTrace();
			usr = null;
			if(tx!=null)
				tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return (usr);
	}

	@SuppressWarnings("unchecked")
	public List<com.avee.form.AppUser> searchUser(Map<String, String> map) {

		List<com.avee.form.AppUser> formList = new ArrayList<com.avee.form.AppUser>();

		String usrName = map.get("usrName");
		String usrEmail = map.get("usrEmail");

		Session session = null;
		try {

			session = sessionFactory.openSession();
			List<AppUser> userList = null;

			Criteria criteria = session.createCriteria(AppUser.class, "user");
			criteria.add(Restrictions.eq("user.usrIsactiveYn", "Y"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (strUtility.checkNullOrEmptyString(usrEmail)) {
				criteria.add(Restrictions.ilike("user.usrEmail", usrEmail.trim()));
			}
			if (usrName != null && strUtility.checkNullOrEmptyString(usrName)) {
				criteria.add(Restrictions.or(Restrictions.ilike("user.usrFirstName", "%" + usrName.trim() + "%"),
						Restrictions.ilike("user.usrLastName", "%" + usrName.trim() + "%")));
			}
			userList = criteria.list();

			if (userList != null) {
				Iterator<AppUser> itr = userList.iterator();
				while (itr.hasNext()) {
					AppUser domain = (AppUser) itr.next();
					com.avee.form.AppUser frmUser = new com.avee.form.AppUser();
					BeanUtils.copyProperties(domain, frmUser);
					formList.add(frmUser);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return formList;
	}

	@SuppressWarnings("unchecked")
	public com.avee.form.AppUser getUser(String usrid) {
		com.avee.form.AppUser usr = null;
		Session session = this.sessionFactory.openSession();
		try {
			Query qry = (Query) session.createQuery("from AppUser where usrId = :usrId");
			qry.setString("usrId", usrid);
			List<AppUser> list = qry.list();

			if (list.size() > 0) {
				AppUser usrDom = (AppUser) qry.list().get(0);
				if (usrDom != null) {
					usr = new com.avee.form.AppUser();
					BeanUtils.copyProperties(usrDom, usr);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			usr = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
		return usr;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertUpdateUserData(com.avee.form.AppUser appUser, String operation) {
		String status = "";
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<com.avee.domain.AppUser> list = new ArrayList<>();

			if (operation.equalsIgnoreCase("insert")) {
				Query query = session.createQuery("from AppUser where usrId =:usrId or usrName =:uname");
				query.setString("usrId", appUser.getUsrId());
				query.setString("uname", appUser.getUsrName());
				list = query.list();
			}

			if (list.size() > 0) {
				status = "User id and name is already created. Please try another user id and name.";
			} else {

				BeansUtility beansUtility = new BeansUtility<>();
				com.avee.domain.AppUser domain = null;

				if (operation.equalsIgnoreCase("insert")) {
					domain = new com.avee.domain.AppUser();
					beansUtility.copy(domain, appUser, new ArrayList<>());

					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					domain.setUsrPasswd(passwordEncoder.encode((CharSequence) appUser.getUsrPasswd()));
				} else {
					domain = (com.avee.domain.AppUser) session.load(com.avee.domain.AppUser.class, appUser.getUsrId());// getUser();

					if (domain != null) {
						beansUtility.copy(domain, appUser, new ArrayList<>());
						
						logger.debug("appUser.getUsrIsactiveYn()   -  " + appUser.getUsrIsactiveYn());
						if(appUser.getUsrIsactiveYn() == null) {
							domain.setUsrIsactiveYn(null);
						}
						logger.debug("domain.getUsrIsactiveYn()   -  " + domain.getUsrIsactiveYn());
						
					} else {

						domain = new com.avee.domain.AppUser();
						beansUtility.copy(domain, appUser, new ArrayList<>(Arrays.asList("usrSignature")));
						
						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						domain.setUsrPasswd(passwordEncoder.encode((CharSequence) appUser.getUsrPasswd()));
					}
				}

				String dispName = appUser.getUsrFirstName() + " " + appUser.getUsrLastName();
				domain.setUsrDisplayName(dispName);

				if (operation.equalsIgnoreCase("insert")) {
					status = "User saved successfully.";
				} else {
					status = "User updated successfully.";
				}

//				domain.setUsrIsactiveYn("Y");

				if (!strUtility.checkNullOrEmptyString(appUser.getUsrLockYn())) {
					domain.setUsrLockYn("N");
				}

				if (!strUtility.checkNullOrEmptyString(appUser.getUsrEmployeeYn())) {
					domain.setUsrEmployeeYn("N");
				}

				session.saveOrUpdate(domain);
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
		return status;
	}

	public void deactiveUser(String userId, String loginUser) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			AppUser user = (AppUser) session.get(AppUser.class, userId);
			if (user != null) {
				user.setUpdatedBy(loginUser);
				user.setUpdatedDt(new Date());
				user.setUsrIsactiveYn("N");
			}

			session.update(user);
			tx.commit();

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			if (session != null && session.isOpen()) {
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<com.avee.form.BranchUserAssocs> getBranchUserAssocsList(Map<String, String> map) {
		List<com.avee.form.BranchUserAssocs> formList = new ArrayList<>();

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String userId = map.get("userid");
			String bid = map.get("bid");
			Query query = null;
			if (strUtility.checkNullOrEmptyString(userId) && (!strUtility.checkNullOrEmptyString(bid))) {
				query = session.createQuery("from BranchUserAssocs where appUser.usrId =:userId");
				query.setString("userId", userId);
			}
			if (strUtility.checkNullOrEmptyString(bid) && (!strUtility.checkNullOrEmptyString(userId))) {
				query = session.createQuery("from BranchUserAssocs where branchdetails.id =:branchId");
				query.setInteger("branchId", Integer.parseInt(bid));
			}
			BeansUtility bub = new BeansUtility();
			List<String> exclusionsList = new ArrayList<String>();
			exclusionsList.add("branchdetails");
			exclusionsList.add("appUser");

			List<com.avee.domain.BranchUserAssocs> domainList = query.list();

			for (com.avee.domain.BranchUserAssocs domain : domainList) {

				com.avee.form.BranchUserAssocs form = new com.avee.form.BranchUserAssocs();

				com.avee.form.BranchDetails branchdetails = new com.avee.form.BranchDetails();
				bub.copy(branchdetails, domain.getBranchdetails(), exclusionsList);
				form.setBranchdetails(branchdetails);

				com.avee.form.AppUser appUserform = new com.avee.form.AppUser();
				bub.copy(appUserform, domain.getAppUser(), exclusionsList);
				form.setAppUser(appUserform);
				formList.add(form);
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
		return formList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GroupUserAssocs> getGroupUserAssocsList(String userId) {
		List<com.avee.form.GroupUserAssocs> formList = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query query = session.createQuery("from GroupUserAssocs where appUser.usrId =:userId");
			query.setString("userId", userId);

			BeansUtility bub = new BeansUtility();
			List<String> exclusionsList = new ArrayList<String>();
			exclusionsList.add("groupDetails");
			exclusionsList.add("appUser");

			List<com.avee.domain.GroupUserAssocs> domainList = query.list();

			for (com.avee.domain.GroupUserAssocs domain : domainList) {

				com.avee.form.GroupUserAssocs form = new com.avee.form.GroupUserAssocs();

				com.avee.form.GroupDetails groupdetails = new com.avee.form.GroupDetails();
				bub.copy(groupdetails, domain.getGroupDetails(), exclusionsList);
				form.setGroupDetails(groupdetails);

				com.avee.form.AppUser appUserform = new com.avee.form.AppUser();
				bub.copy(appUserform, domain.getAppUser(), exclusionsList);
				form.setAppUser(appUserform);
				formList.add(form);
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
		return formList;
	}

	@Override
	public void manageGroupUserAssocs(List<GroupUserAssocs> list) {
		logger.info("insertGroupDetailsAssoscData	--	start");
		Session session = null;
		Transaction tx = null;

		try {

			for (GroupUserAssocs groupUserAssocs : list) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				com.avee.domain.GroupUserAssocs domain = new com.avee.domain.GroupUserAssocs();
				com.avee.domain.AppUser appUserdomain = new com.avee.domain.AppUser();
				com.avee.domain.GroupDetails groupDetailsdomain = new com.avee.domain.GroupDetails();

				appUserdomain.setUsrId(groupUserAssocs.getAppUser().getUsrId());
				groupDetailsdomain.setId(groupUserAssocs.getGroupDetails().getId());
				// //System.out.println(groupDetailsdomain.getId());
				domain.setStartDate(groupUserAssocs.getAppUser().getCreatedDt());
				domain.setCreatedBy(groupUserAssocs.getAppUser().getCreatedBy());
				domain.setCreatedDt(groupUserAssocs.getAppUser().getCreatedDt());
				domain.setUpdatedDt(groupUserAssocs.getAppUser().getCreatedDt());
				domain.setWorkingDay(111111);
				domain.setAppUser(appUserdomain);
				domain.setGroupDetails(groupDetailsdomain);

				session.save(domain);
				tx.commit();
				session.clear();
				session.close();
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
	}

	@Override
	public void manageBranchUserAssocs(List<BranchUserAssocs> list) {
		logger.info("manageBranchUserAssocs	--	start");
		Session session = null;
		Transaction tx = null;

		try {

			for (BranchUserAssocs branchUserAssocs : list) {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				com.avee.domain.BranchUserAssocs domain = new com.avee.domain.BranchUserAssocs();
				com.avee.domain.AppUser appUserdomain = new com.avee.domain.AppUser();
				com.avee.domain.BranchDetails branchDetailsdomain = new com.avee.domain.BranchDetails();

				appUserdomain.setUsrId(branchUserAssocs.getAppUser().getUsrId());
				branchDetailsdomain.setId(branchUserAssocs.getBranchdetails().getId());

				domain.setStartDate(branchUserAssocs.getAppUser().getCreatedDt());
				domain.setCreatedBy(branchUserAssocs.getAppUser().getCreatedBy());
				domain.setCreatedDt(branchUserAssocs.getAppUser().getCreatedDt());
				domain.setUpdatedDt(branchUserAssocs.getAppUser().getCreatedDt());
				domain.setWorkingDay(111111);
				domain.setAppUser(appUserdomain);
				domain.setBranchdetails(branchDetailsdomain);

				session.save(domain);
				tx.commit();
				session.close();

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

	}

	@SuppressWarnings("unchecked")
	@Override
	public String changeUserPassword(Map<String, String> map) {
		String message = "";

		String userId = map.get("usrId");
		// String usrName = map.get("usrName");
		String newPw = map.get("newPw");

		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query pwdQuery = null;

			if (strUtility.checkNullOrEmptyString(userId)) {
				pwdQuery = session.createQuery("from AppUser where usrId = :usrId");
				pwdQuery.setString("usrId", userId);

			}

			if (pwdQuery != null) {
				List<AppUser> list = pwdQuery.list();
				if (list.size() > 0) {

					AppUser urs = list.get(0);
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					urs.setUsrPasswd(passwordEncoder.encode((CharSequence) newPw));
					session.update(urs);
				}
				tx.commit();
				message = "Password changed successfully.";
			}
		} catch (Exception e) {
			message = "";
			if (tx != null)
				tx.rollback();
			throw e;
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
	public List<AppUserGroupBranchMapping> getUserDetailsaAsList(Map<String, String> map) {
		logger.info("in UserDAO getUserDetailsAsList.. Start");

		List<AppUserGroupBranchMapping> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String uid = map.get("uid");
			String userId = map.get("userId");
			String emailId = map.get("emailId");
			Query qry = null;
			if (strUtility.checkNullOrEmptyString(uid)
					&& (strUtility.checkNullOrEmptyString(userId) || strUtility.checkNullOrEmptyString(emailId))) {
				qry = session.getNamedQuery("getUserDetailsByuserIdOrEmailIdAsList");
				
				if (!strUtility.checkNullOrEmptyString(userId))
					userId = "";
				else
					userId += "%";
				if (!strUtility.checkNullOrEmptyString(emailId))
					emailId = "";
				else
					emailId += "%";
				
				
				qry.setString("userId", userId);
				qry.setString("emailId", emailId);
			} else {
				qry = session.getNamedQuery("getuserDetailsByUIdAsList");
			}
			qry.setString("uId", uid);

			List<com.avee.domain.AppUserGroupBranchMapping> userDetailsList = qry.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.AppUserGroupBranchMapping userDetailsdomain : userDetailsList) {
				AppUserGroupBranchMapping userDetailsForm = new AppUserGroupBranchMapping();
				bub.copy(userDetailsForm, userDetailsdomain, exclusionsList);
				list.add(userDetailsForm);

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
	public void updateGroupUserAssocs(com.avee.form.AppUser appUser) {
		// TODO Auto-generated method stub
			Session session = null;
			Transaction tx = null;
			logger.info("in updateGroupUserAssocs . start ");
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				String userId = appUser.getUsrId();
				
				System.out.println(userId);
				if (strUtility.checkNullOrEmptyString(userId)) {
					Query query = null;
					query = session.getNamedQuery("Delete_User_Group_Asso");
					query.setString("userId", userId);
					query.executeUpdate();
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
			
		}
		
	

	@SuppressWarnings("unchecked")
	@Override
	public void updateBranchUserAssocs(com.avee.form.AppUser appUser) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = null;
		try {
			// TODO Auto-generated method stub
			
			String userId = appUser.getUsrId();
			String branchId = appUser.getBid();
			if (strUtility.checkNullOrEmptyString(userId) || strUtility.checkNullOrEmptyString(branchId)) {
				Query qry = (Query) session.createQuery("from BranchUserAssocs where appUser.usrId =:userId");
				qry.setString("userId", userId);
				List<com.avee.domain.BranchUserAssocs> domainList = qry.list();
				System.out.println(domainList.size());
				if (domainList.size() > 0) {
					for(com.avee.domain.BranchUserAssocs domain:domainList)
					{
						tx = session.beginTransaction();
						com.avee.domain.AppUser appUserdomain = new com.avee.domain.AppUser();
						com.avee.domain.BranchDetails branchDetailsdomain = new com.avee.domain.BranchDetails();

						appUserdomain.setUsrId(appUser.getUsrId());
						branchDetailsdomain.setId(Integer.parseInt(branchId));
						domain.setUpdatedBy(appUser.getUpdatedBy());
						domain.setUpdatedDt(appUser.getUpdatedDt());
						domain.setAppUser(appUserdomain);
						domain.setBranchdetails(branchDetailsdomain);
						session.update(domain);
						tx.commit();
						session.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			appUser = null;
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