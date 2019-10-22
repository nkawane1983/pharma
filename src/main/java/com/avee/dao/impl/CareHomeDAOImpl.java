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

import com.avee.dao.CareHomeDAO;
import com.avee.form.BranchDetails;
import com.avee.form.CareHome;
import com.avee.form.CareHomeScriptItems;
import com.avee.form.CareHomesReport;
import com.avee.form.ScriptItems;
import com.avee.form.TakingsCash;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

@Transactional
@Component
public class CareHomeDAOImpl implements CareHomeDAO {
	static final Logger logger = LogManager.getLogger(CareHomeDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public CareHome getCareHome(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CareHome> searchCareHome(Map<String, String> map) {
		List<CareHome> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String branchId = map.get("branchId");
			String cname = map.get("cname");

			Criteria criteria = session.createCriteria(com.avee.domain.CareHome.class);
			if (strUtility.checkNullOrEmptyString(branchId) && !branchId.equals("0"))
				criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));
			if (strUtility.checkNullOrEmptyString(cname))
				criteria.add(Restrictions.ilike("description", "%" + cname + "%"));

			List<com.avee.domain.CareHome> careHomeList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.CareHome careHomedomain : careHomeList) {
				CareHome careHomeForm = new CareHome();
				bub.copy(careHomeForm, careHomedomain, exclusionsList);
				list.add(careHomeForm);

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CareHome> getByBranchIdCareHome(Map<String, String> map) {
		List<CareHome> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String branchId = map.get("branchId");
			Criteria criteria = session.createCriteria(com.avee.domain.CareHome.class);

			criteria.add(Restrictions.eq("branchId", Integer.parseInt(branchId)));

			List<com.avee.domain.CareHome> careHomeList = criteria.list();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			for (com.avee.domain.CareHome careHomedomain : careHomeList) {
				CareHome careHomeForm = new CareHome();
				bub.copy(careHomeForm, careHomedomain, exclusionsList);
				list.add(careHomeForm);

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String insertScriptItems(ScriptItems scriptItems) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();

			com.avee.domain.ScriptItems domain = new com.avee.domain.ScriptItems();

			bub.copy(domain, scriptItems, exclusionsList);
			session.save(domain);

			// System.out.println("id=" + domain.getId());
			if (domain.getId() > 0 && scriptItems.getCareHomeScriptId() != null
					&& scriptItems.getCareHomeScriptId().size() > 0) {

				for (com.avee.form.CareHomeScriptItems careHomeScriptItemsForm : scriptItems.getCareHomeScriptId()) {

					com.avee.domain.CareHomeScriptItems careHomeScriptItemsdomain = new com.avee.domain.CareHomeScriptItems();

					bub.copy(careHomeScriptItemsdomain, careHomeScriptItemsForm, exclusionsList);

					careHomeScriptItemsdomain.setScriptId(domain.getId());

					session.save(careHomeScriptItemsdomain);
				}
				tx.commit();
				message = "CareHome  inserted.";

			} else if (domain.getId() > 0) {
				tx.commit();

			} else {
				message = "CareHome  can not be inserted.";
				tx.rollback();
			}

		} catch (Exception e) {
			message = "CareHome  can not be inserted.";
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ScriptItems> getByScriptItems(Map<String, String> map) {
		List<ScriptItems> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		List<Object[]> listform = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String fdate = map.get("fDate");
			String tdate = map.get("tDate");
			String branchId = map.get("branchId");
			if (strUtility.checkNullOrEmptyString(fdate) && strUtility.checkNullOrEmptyString(tdate)) {

				Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
				Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);

				Query query = session.getNamedQuery("getScriptItems");
				query.setInteger("branchId", Integer.parseInt(branchId));
				query.setDate("fdate", fDate);
				query.setDate("tdate", tDate);
				listform = query.list();
			}

			// for (int i = 0; i < listform.size(); i++) {
			// ScriptItems scriptform = new ScriptItems();
			// scriptform.setId(Integer.parseInt(listform.get(i)[0].toString()));
			// scriptform.setCarehomeId(Integer.parseInt(listform.get(i)[1].toString()));
			// scriptform.setCodeId(Integer.parseInt(listform.get(i)[3].toString()));
			// scriptform.setEventDate(new
			// SimpleDateFormat("dd/MM/yyyy").parse(listform.get(i)[2].toString()));
			// scriptform.setForm(Integer.parseInt(listform.get(i)[4].toString()));
			// scriptform.setItems(Integer.parseInt(listform.get(i)[5].toString()));
			// list.add(scriptform);
			// }

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
	public String insertCareHomes(CareHome careHomes) {
		String message = "";

		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.CareHome domain = new com.avee.domain.CareHome();
			bub.copy(domain, careHomes, exclusionsList);

			session.save(domain);

			// System.out.println("id="+domain.getId());

			tx.commit();
			message = "CareHome inserted successfully.";

		} catch (Exception e) {
			message = "CareHome can not be inserted.";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CareHomesReport> getCareHomesScriptItemsAsList(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CareHomesReport> results = new ArrayList<>();
		List<Object[]> result = null;
		logger.info("in CareHomeDAOImpl getCareHomesScriptItemsAsList.. strat");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int branchId = Integer.parseInt(map.get("branchId"));

			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			Query query;

			query = session.getNamedQuery("getCareHomesScriptItemsAsList");
			query.setInteger("branchID", branchId);
			query.setDate("fdate", fDate);
			query.setDate("tdate", tDate);

			result = query.list();
			for (int i = 0; i < result.size(); i++) {
				CareHomesReport careHomesReport = new CareHomesReport();
				careHomesReport.setId(Integer.parseInt(result.get(i)[0].toString()));

				careHomesReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
				careHomesReport.setComm7form(Integer.parseInt(result.get(i)[2].toString()));
				careHomesReport.setComm7items(Integer.parseInt(result.get(i)[3].toString()));
				careHomesReport.setComm28form(Integer.parseInt(result.get(i)[4].toString()));
				careHomesReport.setComm28items(Integer.parseInt(result.get(i)[5].toString()));
				careHomesReport.setForm7day(Integer.parseInt(result.get(i)[6].toString()));
				careHomesReport.setItems7day(Integer.parseInt(result.get(i)[7].toString()));
				careHomesReport.setForm28day(Integer.parseInt(result.get(i)[8].toString()));
				careHomesReport.setItems28day(Integer.parseInt(result.get(i)[9].toString()));
				careHomesReport.setTotal7form(
						Integer.parseInt(result.get(i)[2].toString()) + Integer.parseInt(result.get(i)[4].toString()));
				careHomesReport.setTotal7items(
						Integer.parseInt(result.get(i)[3].toString()) + Integer.parseInt(result.get(i)[5].toString()));

				results.add(careHomesReport);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ScriptItems getScriptItemsById(int id) {
		Session session = null;
		Transaction tx = null;
		ScriptItems form = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			com.avee.domain.ScriptItems domain = (com.avee.domain.ScriptItems) session
					.get(com.avee.domain.ScriptItems.class, id);
			if (domain != null) {
				form = new ScriptItems();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();

				Criteria criteria = session.createCriteria(com.avee.domain.CareHomeScriptItems.class);
				criteria.add(Restrictions.eq("scriptId", id));
				criteria.add(Restrictions.eq("markDelete", false));
				List<com.avee.domain.CareHomeScriptItems> careHomeScriptItemsList = criteria.list();

				List<CareHomeScriptItems> careHomeScriptItemsformList = new ArrayList<CareHomeScriptItems>();

				if (careHomeScriptItemsList != null && careHomeScriptItemsList.size() > 0) {
					for (com.avee.domain.CareHomeScriptItems careHomeScriptItemsdomain : careHomeScriptItemsList) {
						CareHomeScriptItems careHomeScriptItemsfrom = new CareHomeScriptItems();
						bub.copy(careHomeScriptItemsfrom, careHomeScriptItemsdomain, exclusionsList);
						careHomeScriptItemsformList.add(careHomeScriptItemsfrom);
					}
					bub.copy(form, domain, exclusionsList);
					form.setCareHomeScriptId(careHomeScriptItemsformList);
				} else {
					bub.copy(form, domain, exclusionsList);
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
		return form;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateScriptItems(ScriptItems scriptItems, String mode) {
		// TODO Auto-generated method stub
		String message = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			com.avee.domain.ScriptItems domain = (com.avee.domain.ScriptItems) session
					.get(com.avee.domain.ScriptItems.class, scriptItems.getId());

			if (domain != null) {
				BeansUtility bub = new BeansUtility();
				List<String> exclusionsList = new ArrayList<String>();
				if (mode.equalsIgnoreCase("scriptItemsUpdate")) {
					
					bub.copy(domain, scriptItems, exclusionsList);
				} else {
					domain.setNoCareHomePatients(scriptItems.getNoCareHomePatients());
					domain.setNoPatients(scriptItems.getNoPatients());
					domain.setUpdatedBy(scriptItems.getUpdatedBy());
					domain.setUpdatedDt(scriptItems.getUpdatedDt());
				}

				session.update(domain);

				if (mode.equalsIgnoreCase("scriptItemsUpdate")) {
					if (scriptItems.getCareHomeScriptId() != null && scriptItems.getCareHomeScriptId().size() > 0) {
						for (CareHomeScriptItems careHomeScriptItemsform : scriptItems.getCareHomeScriptId()) {

							careHomeScriptItemsform.setId(0);
							com.avee.domain.CareHomeScriptItems careHomeScriptItemsdomain = new com.avee.domain.CareHomeScriptItems();

							bub.copy(careHomeScriptItemsdomain, careHomeScriptItemsform, exclusionsList);

							careHomeScriptItemsdomain.setScriptId(domain.getId());

							session.save(careHomeScriptItemsdomain);

						}
					}
				}

			}

			tx.commit();

			message = "CareHome updated successfully.";

		} catch (

		Exception e) {
			message = "CareHome not updated successfully.";
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

	public void deleteScriptItems(ScriptItems scriptItems) {
		logger.info("in   deleteScriptItems. start");
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			if (scriptItems.getCareHomeScriptId() != null && scriptItems.getCareHomeScriptId().size() > 0) {
				for (CareHomeScriptItems careHomeScriptItemsform : scriptItems.getCareHomeScriptId()) {

					if (careHomeScriptItemsform.getId() != 0) {
						com.avee.domain.CareHomeScriptItems careHomeScriptItemsdomain = (com.avee.domain.CareHomeScriptItems) session
								.get(com.avee.domain.CareHomeScriptItems.class, careHomeScriptItemsform.getId());
						if (careHomeScriptItemsdomain != null) {
							session.delete(careHomeScriptItemsdomain);

						}
					}
				}
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
		logger.info("in  deleteScriptItems.. end");

	}

	@Override
	public boolean checkScriptItemsExistOrNot(ScriptItems scriptItems) {
		Session session = null;
		Transaction tx = null;

		boolean msg = false;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			if (scriptItems != null) {
				Criteria criteria = session.createCriteria(com.avee.domain.ScriptItems.class);
				criteria.add(Restrictions.eq("eventDate", scriptItems.getEventDate()));
				criteria.add(Restrictions.eq("branchId", scriptItems.getBranchId()));
				criteria.add(Restrictions.eq("markDelete", false));
				List<com.avee.domain.ScriptItems> scriptItemsList = criteria.list();

				if (scriptItemsList != null && scriptItemsList.size() > 0)
					msg = true;

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
		return msg;
	}

	@Override
	public CareHome getLastCareHomesScriptItems(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CareHomesReport> getByCareHomeScriptItems(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CareHomesReport> results = new ArrayList<>();
		List<Object[]> result = null;
		logger.info("in CareHomeDAOImpl getByCareHomeScriptItems.. strat");

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int branchId = Integer.parseInt(map.get("branchId"));

			Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tDate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			Query query;

			query = session.getNamedQuery("getByCareHomeScriptItems");
			query.setInteger("branchID", branchId);
			query.setDate("fdate", fDate);
			query.setDate("tdate", tDate);

			result = query.list();
			for (int i = 0; i < result.size(); i++) {
				CareHomesReport careHomesReport = new CareHomesReport();
				careHomesReport.setCarehomeName(result.get(i)[0].toString());
				careHomesReport.setTotal7form(Integer.parseInt(result.get(i)[1].toString()));
				careHomesReport.setTotal7items(Integer.parseInt(result.get(i)[2].toString()));

				results.add(careHomesReport);
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
}
