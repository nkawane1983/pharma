package com.avee.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.hibernate.internal.SessionImpl;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.EScheduleDAO;
import com.avee.form.MonthYears;
import com.avee.form.Ppa500;
import com.avee.form.Ppa600;
import com.avee.form.Ppa800;
import com.avee.form.PpaData;
import com.avee.form.PpaMasterReport;
import com.avee.service.SysParamService;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

import net.sf.jasperreports.engine.JasperRunManager;

@Transactional
@Component
public class EScheduleDAOImpl implements EScheduleDAO {
	static final Logger logger = LogManager.getLogger(EScheduleDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	SysParamService sysParamService;
	@Autowired
	private StringUtility strUtility;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertPPA500(Ppa500 ppa500) {
		String message = "";
		logger.info("in insertPPA500 . start ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Ppa500 domain = new com.avee.domain.Ppa500();
			bub.copy(domain, ppa500, exclusionsList);

			session.save(domain);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertPPA600(Ppa600 ppa600) {
		String message = "";
		logger.info("in insertPPA600 . start ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Ppa600 domain = new com.avee.domain.Ppa600();
			bub.copy(domain, ppa600, exclusionsList);

			session.save(domain);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertPPA800(Ppa800 ppa800) {
		String message = "";
		logger.info("in insertPPA800 . start ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.Ppa800 domain = new com.avee.domain.Ppa800();
			bub.copy(domain, ppa800, exclusionsList);

			session.save(domain);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String insertPPADATA(PpaData ppadata) {
		String message = "";
		logger.info("in insertPPADATA . start ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.PpaData domain = new com.avee.domain.PpaData();
			bub.copy(domain, ppadata, exclusionsList);
			// System.out.println(ppadata.toString());
			session.save(domain);
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

	@Override
	public String copyAllDataIntoMasterPPA(String userId) {
		String msg = "";
		Session session = null;
		Transaction tx = null;

		logger.info("in copyAllDataIntoMasterPPA . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query qry = session.createSQLQuery(" { CALL  pharma.copyAllPPAData( ? ) } ");
			qry.setString(0, userId);
			qry.executeUpdate();

			tx.commit();
			msg = "SUCCESS";

		} catch (Exception e) {
			msg = "ERROR";
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean checkBranchCodeExistsOrNotPPA(Map<String, String> map) {
		boolean verfiy = false;
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info(" checkBranchCodeExistsOrNotPPA :start");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			int groupId = Integer.parseInt(map.get("groupId"));
			String userId = map.get("userId");
			String brnchcode = map.get("brnchcode");
		//	String branchname = map.get("branchname");
			System.out.println(userId + "userId");
			System.out.println(brnchcode + "brnchcode");
			System.out.println(groupId + "groupId");
			if (strUtility.checkNullOrEmptyString(brnchcode)) {
				Query query = session.getNamedQuery("checkBranchCodeExistsOrNotPPA");
				query.setString("branchCode", brnchcode);
				query.setInteger("groupId", groupId);
				query.setString("userId", userId);
				results = query.list();
				// System.out.println(results.get(0));
				if (results != null && results.size() > 0) {
					//System.out.println(results.get(0));
					if (!String.valueOf(results.get(0)).equals("0"))
						verfiy = true;
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
		logger.info(" checkBranchCodeExistsOrNotPPA :end");
		return verfiy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getBranchNameByCodePPA(Map<String, String> map) {
		String msg = "";
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info("in getBranchNameByCodePPA . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//int groupId = Integer.parseInt(map.get("groupId"));
			String brnchcode = map.get("brnchcode");
			Query query = session.getNamedQuery("getBranchNameByCodePPA");
			query.setString("branchCode", brnchcode);
			// query.setInteger("groupId", groupId);
			results = query.list();
			if (results != null && results.size() > 0) {
				msg = String.valueOf(results.get(0));
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

	@SuppressWarnings("unchecked")
	@Override
	public String countTotalRecodeIntoMaster() {
		String msg = "";
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info("in countTotalRecodeIntoMaster . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery("countTotalRecodeIntoMaster");

			results = query.list();
			if (results != null && results.size() > 0) {
				// System.out.println(results.get(0));
				msg = String.valueOf(results.get(0));
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
	public String UpdatePpaDataImport() {
		String msg = "";
		Session session = null;
		Transaction tx = null;
		logger.info("in UpdatePpaDataImport . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery("Update_PPA_DATA_Import");
			query.executeUpdate();
			tx.commit();
			msg = "SUCCESS";

		} catch (Exception e) {
			msg = "ERROR";
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
	public String deleteOldImportData(String userId) {
		String msg = "";
		Session session = null;
		Transaction tx = null;
		logger.info("in deleteOldImportData . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = null;

			query = session.getNamedQuery("Delete_Old_Import_Data_PPA_500");
			query.setString("userId", userId);
			query.executeUpdate();
			query = session.getNamedQuery("Delete_Old_Import_Data_PPA_600");
			query.setString("userId", userId);
			query.executeUpdate();
			query = session.getNamedQuery("Delete_Old_Import_Data_PPA_800");
			query.setString("userId", userId);
			query.executeUpdate();
			query = session.getNamedQuery("Delete_Old_Import_Data_PPA_DATA");
			query.setString("userId", userId);
			query.executeUpdate();
			tx.commit();
			msg = "SUCCESS";

		} catch (Exception e) {
			msg = "ERROR";
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
	public List<PpaData> getPpaMasterData(String userId) {
		List<PpaData> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		logger.info("in getPpaMasterData . start ");
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = null;
			if (strUtility.checkNullOrEmptyString(userId)) {
				criteria = session.createCriteria(com.avee.domain.PpaData.class);
				criteria.add(Restrictions.eq("createdBy", userId));
				criteria.addOrder(Order.asc("id"));
			}
			if (criteria != null) {
				List<com.avee.domain.PpaData> ppadatList = criteria.list();
				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();

				for (com.avee.domain.PpaData ppadatadomain : ppadatList) {
					PpaData ppadataForm = new PpaData();
					// System.out.println(ppadataForm.getMbName());
					bub.copy(ppadataForm, ppadatadomain, exclusionsList);
					list.add(ppadataForm);
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
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkPPADataExistsOrNot(Map<String, String> map) {
		boolean msg = false;
		Session session = null;
		Transaction tx = null;

		logger.info("in checkPPADataExistsOrNot . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List oRecs = null;
			int groupId = Integer.parseInt(map.get("groupId"));
			Date eventdate = new SimpleDateFormat("dd/MMM/yyyy").parse(map.get("eventdate"));

			SQLQuery query = (SQLQuery) session.getNamedQuery("checkPPADataExistsOrNot");
			query.setInteger("groupid", groupId);
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

	@SuppressWarnings("rawtypes")
	@Override
	public Map<Integer, List<String>> getMonthYearAsList(int groupId) {
		
		Map<Integer, List<String>> searchMap = new HashMap<>();
		Session session = null;
		Transaction tx = null;
		Object obj = null;
		logger.info("in getMonthYearAsListaaaaa . start ");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			List oRecs = null;
			List<String> monthList = new ArrayList<String>();
			List<String> yearList = new ArrayList<String>();

			SQLQuery query = (SQLQuery) session.getNamedQuery("getMonthAsList");
			query.setInteger("groupid", groupId);
			SQLQuery quy = (SQLQuery) session.getNamedQuery("getYearAsList");
			quy.setInteger("groupid", groupId);

			oRecs = query.list();
			//tx.commit();

			if (oRecs.size() > 0) {
				for (int i = 0; i < oRecs.size(); i++) {
					obj = oRecs.get(i);
					monthList.add(String.valueOf(obj.toString()));
				}
				
			}
			oRecs=null;
			oRecs = quy.list();
			tx.commit();
			if (oRecs.size() > 0) {
				for (int i = 0; i < oRecs.size(); i++) {
					obj = oRecs.get(i);
					yearList.add(String.valueOf(obj.toString()));
				}
				
			}
			searchMap.put(1, monthList);
			searchMap.put(2, yearList);
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
		return searchMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PpaMasterReport> getPpaMasterDataAsList(Map<String, String> map) {
		List<PpaMasterReport> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		List<Object[]> results = null;
		logger.info("in EScheduleDAO getPpaMasterDataAsList.. strat");
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String month = map.get("month");
			String year = map.get("year");
			String branchId = map.get("branchid");
			String groupId = map.get("groupid");

			String userId = map.get("userid");

			ProcedureCall call = session.createStoredProcedureCall("pharma.show_PPAMasterData");
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(branchId));
			call.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(groupId));
			call.registerParameter(3, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(year));
			call.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(Integer.parseInt(month));
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userId);

			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();

			results = output.getResultList();

			if (results != null && results.size() > 0) {
				for (int i = 0; i < results.size(); i++) {
					PpaMasterReport ppaMasterReport = new PpaMasterReport();
					ppaMasterReport.setBrachId(Integer.parseInt(results.get(i)[0].toString()));
					ppaMasterReport.setInternalId(results.get(i)[1].toString());
					ppaMasterReport.setBranchName(results.get(i)[2].toString());
					ppaMasterReport
							.setPaymentDate(new SimpleDateFormat("yyyy-MM-dd").parse(results.get(i)[4].toString()));
					ppaMasterReport.setMbOcsCode(results.get(i)[11].toString());
					ppaMasterReport.setMbPayment(Double.parseDouble(results.get(i)[34].toString()));
					list.add(ppaMasterReport);
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
		logger.info("in EScheduleDAO getPpaMasterDataAsList.. End");

		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String insertMonthYears(MonthYears monthYears) {
		String message = "";
		logger.info("in insertMonthYears . start ");
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			List<String> exclusionsList = new ArrayList<String>();
			BeansUtility bub = new BeansUtility();
			com.avee.domain.MonthYears domain = new com.avee.domain.MonthYears();
			bub.copy(domain, monthYears, exclusionsList);

			session.save(domain);
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

	@Override
	public byte[] generateReportPDF(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		//ByteArrayOutputStream outputStream = null;
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".pdf");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			int year = Integer.parseInt(map.get("year"));
			int month = Integer.parseInt(map.get("month"));

			String userid = map.get("userid");
			String fileName="";

			if (groupid == 1 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", userid);
				parametersH.put("year", year);
				parametersH.put("month", month);
				fileName="escheduleReport.jasper";

			} else if (groupid != 1 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", userid);
				parametersH.put("year", year);
				parametersH.put("month", month);
				fileName="escheduleReport_s_p_n.jasper";

			} else {
				if (groupid == 1 && branchid != 0) {
					parametersH.put("group_Id", groupid);
					parametersH.put("branch_Id", branchid);
					parametersH.put("uname", userid);
					parametersH.put("year", year);
					parametersH.put("month", month);
						fileName="escheduleReport.jasper";
				}
				if (groupid != 1 && branchid != 0) {
					parametersH.put("group_Id", groupid);
					parametersH.put("branch_Id", branchid);
					parametersH.put("uname", userid);
					parametersH.put("year", year);
					parametersH.put("month", month);
					fileName="escheduleReport_s_p_n.jasper";
				}

			}
			parametersH.put("SUBREPORT_DIR", path);

			File reportFile = new File(path + fileName);// your
																		// report_name.jasper
																		// file

			byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametersH, conn);

			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response) {
		
		Session session = null;
		Transaction tx = null;

		try {
			// String path =
			// sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("reportCSV", ".csv");

			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			int year = Integer.parseInt(map.get("year"));
			int month = Integer.parseInt(map.get("month"));

			String userid = map.get("userid");
			StringBuffer fw = new StringBuffer();
			// FileWriter fw = new FileWriter(tempFile);
			CallableStatement stproc_stmt = conn
					.prepareCall("{call [Pharma].[pharma].generateCSV_PPAMasterData(?,?,?,?,?)}");
			stproc_stmt.setInt(1, branchid);
			stproc_stmt.setInt(2, groupid);
			stproc_stmt.setInt(3, year);
			stproc_stmt.setInt(4, month);
			stproc_stmt.setString(5, userid);
			boolean hadResults = stproc_stmt.execute();
			try {
				while (hadResults) {
					ResultSet res = stproc_stmt.getResultSet();
					
					int colunmCount = getColumnCount(res);
					// process result set
					
					// this loop is used to add column names at the top of
					// file , if you do not need it just comment this loop
					for (int i = 1; i <= colunmCount; i++) {
						fw.append(res.getMetaData().getColumnName(i));
						fw.append(",");

					}

					
					fw.append(System.getProperty("line.separator"));
					//System.out.println(colunmCount);
					while (res.next()) {
						for (int i = 1; i <= colunmCount; i++) {

							if (res.getObject(i) != null) {
								String data = res.getObject(i).toString().trim();
								if(i<155)
								fw.append(data.replace(",", " "));
								else
								fw.append(data);
								
								fw.append(",");
							} else {
								String data = "";
								fw.append(data);
								fw.append(",");
							}

						}
						// new line entered after each row
						fw.append(System.getProperty("line.separator"));
					}
					//System.out.println(colunmCount);
					hadResults = stproc_stmt.getMoreResults();
				}

				InputStream in = new ByteArrayInputStream(fw.toString().getBytes("UTF-8"));
				byte[] bytes = IOUtils.toByteArray(in);

				// byte[] bytes = is.
				//System.out.println(bytes.length);
				conn.close();
				return bytes;

			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null)
					tx.rollback();
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

		return null;
	}

	public static int getRowCount(ResultSet res) throws SQLException {
		res.last();
		int numberOfRows = res.getRow();
		res.beforeFirst();
		return numberOfRows;
	}

	// to get no of columns in result set

	public static int getColumnCount(ResultSet res) throws SQLException {
		return res.getMetaData().getColumnCount();
	}

}
