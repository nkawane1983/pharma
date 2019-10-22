package com.avee.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.ReportDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BankingReport;
import com.avee.form.BranchDetails;
import com.avee.form.CareHomesReport;
import com.avee.form.CashingReport;
import com.avee.form.Category;
import com.avee.form.CollDelvReport;
import com.avee.form.CouponReport;
import com.avee.form.CustomerStatsReport;
import com.avee.form.ExpensesReport;
import com.avee.form.ListStringAndDouble;
import com.avee.form.NhsReport;
import com.avee.form.Report;
import com.avee.form.VATTAX;
import com.avee.service.BranchService;
import com.avee.service.GroupService;
import com.avee.utility.BeansUtility;
import com.avee.utility.StringUtility;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Transactional
@Component
public class ReportDAOImpl implements ReportDAO {
	static final Logger logger = LogManager.getLogger(ReportDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StringUtility strUtility;
	@Autowired
	BranchService branchService;
	@Autowired
	GroupService groupService;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashingReport> getCashReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		List<CashingReport> results = new ArrayList<CashingReport>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			String userid = map.get("userid");
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			// int groupId = Integer.parseInt(map.get("groupid"));
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			// int branchId = Integer.parseInt(map.get("branchid"));
			ProcedureCall call = session.createStoredProcedureCall("pharma.show_cashreport");
			/*
			 * ----------------------------FOR PostgreSQL
			 * call.registerParameter(1, void.class, ParameterMode.REF_CURSOR);
			 * call.registerParameter(2, Integer.class,
			 * ParameterMode.IN).bindValue(branchId); call.registerParameter(3,
			 * Integer.class, ParameterMode.IN).bindValue(groupId);
			 * call.registerParameter(4, Date.class,
			 * ParameterMode.IN).bindValue(fdate); call.registerParameter(5,
			 * Date.class, ParameterMode.IN).bindValue(tdate);
			 */
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(3, String.class, ParameterMode.IN).bindValue(userid);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

			for (int i = 0; i < result.size(); i++) {
				CashingReport cashingReport = new CashingReport();
				cashingReport.setBranchid(Integer.parseInt(result.get(i)[0].toString()));
				cashingReport.setBranchInternalId(Integer.parseInt(result.get(i)[1].toString()));
				cashingReport.setBranchName(result.get(i)[2].toString());
				cashingReport.setzReading(Double.parseDouble(result.get(i)[3].toString()));
				cashingReport.setDiff(Double.parseDouble(result.get(i)[12].toString())
						- Double.parseDouble(result.get(i)[3].toString()));
				cashingReport.setVoids(Integer.parseInt(result.get(i)[4].toString()));
				cashingReport.setSales(Integer.parseInt(result.get(i)[5].toString()));
				cashingReport.setRefunds(Double.parseDouble(result.get(i)[6].toString()));
				cashingReport.setCash(Double.parseDouble(result.get(i)[7].toString()));
				cashingReport.setCheques(Double.parseDouble(result.get(i)[8].toString()));
				cashingReport.setCard(Double.parseDouble(result.get(i)[9].toString()));
				cashingReport.setCoupons(Double.parseDouble(result.get(i)[10].toString()));
				cashingReport.setExpenses(Double.parseDouble(result.get(i)[11].toString()));
				cashingReport.setActualtill(Double.parseDouble(result.get(i)[12].toString()));
				cashingReport.setTotalitem(Integer.parseInt(result.get(i)[16].toString()));
				cashingReport.setLevy(Double.parseDouble(result.get(i)[17].toString()));
				double inf = (Double.parseDouble(result.get(i)[12].toString())
						- Double.parseDouble(result.get(i)[17].toString()))
						/ Double.parseDouble(result.get(i)[5].toString());
				if (Double.isNaN(inf) || Double.isInfinite(inf))
					cashingReport.setAvg(0.0);
				else
					cashingReport.setAvg(inf);
				cashingReport.setGroupId(Integer.parseInt(result.get(i)[18].toString()));
				results.add(cashingReport);
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<CashingReport> getMonthlyCashReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CashingReport> results = new ArrayList<CashingReport>();
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			// int groupId = Integer.parseInt(map.get("groupid"));
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			String userid = map.get("userid");
			ProcedureCall call = session.createStoredProcedureCall("pharma.show_cashmonthlyreport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

			for (int i = 0; i < result.size(); i++) {
				CashingReport cashingReport = new CashingReport();
				cashingReport.setWeekday(Double.parseDouble(result.get(i)[0].toString()));
				cashingReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
				cashingReport.setzReading(Double.parseDouble(result.get(i)[2].toString()));
				cashingReport.setDiff(Double.parseDouble(result.get(i)[11].toString())
						- Double.parseDouble(result.get(i)[2].toString()));
				cashingReport.setVoids(Integer.parseInt(result.get(i)[3].toString()));
				cashingReport.setSales(Integer.parseInt(result.get(i)[4].toString()));
				cashingReport.setRefunds(Double.parseDouble(result.get(i)[5].toString()));
				double cash = Double.parseDouble(result.get(i)[6].toString())
						+ Double.parseDouble(result.get(i)[7].toString());

				if (!branchId.equals(""))
					cashingReport.setActualCash(cash);

				cashingReport.setCash(cash);
				cashingReport.setCheques(Double.parseDouble(result.get(i)[7].toString()));
				cashingReport.setCard(Double.parseDouble(result.get(i)[8].toString()));
				cashingReport.setCoupons(Double.parseDouble(result.get(i)[9].toString()));
				cashingReport.setExpenses(Double.parseDouble(result.get(i)[10].toString()));
				cashingReport.setActualtill(Double.parseDouble(result.get(i)[11].toString()));
				cashingReport.setZero(Double.parseDouble(result.get(i)[12].toString()));
				cashingReport.setLow(Double.parseDouble(result.get(i)[14].toString()));
				cashingReport.setStd(Double.parseDouble(result.get(i)[13].toString()));
				double inf = (Double.parseDouble(result.get(i)[11].toString())
						- Double.parseDouble(result.get(i)[17].toString()))
						/ Double.parseDouble(result.get(i)[4].toString());
				if (Double.isNaN(inf) || Double.isInfinite(inf))
					cashingReport.setAvg(0.0);
				else
					cashingReport.setAvg(inf);

				if ((groupId.length() == 0 && branchId.length() != 0)
						|| (groupId.length() != 0 && branchId.length() != 0)) {
					cashingReport.setTillno(Integer.parseInt(result.get(i)[19].toString()));
					cashingReport.setCashid(Integer.parseInt(result.get(i)[20].toString()));
					cashingReport.setRef(result.get(i)[18].toString());
				}

				results.add(cashingReport);
			}
		} catch (

		Exception e)

		{
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
	public List<NhsReport> getMonthlyNHSReport(Map<String, String> map) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhsReport> getNHSReport(Map<String, String> map) {

		Session session = null;
		Transaction tx = null;
		List<NhsReport> results = new ArrayList<NhsReport>();
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));

			String userid = map.get("userid");

			// int groupId = Integer.parseInt(map.get("groupid"));
			// int branchId = Integer.parseInt(map.get("branchid"));
			String groupId = map.get("groupid");
			// int groupId = Integer.parseInt(map.get("groupid"));
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			int mode = Integer.parseInt(map.get("mode"));

			ProcedureCall call = session.createStoredProcedureCall("pharma.show_nhsreport");
			/*
			 * ----------------------------FOR PostgreSQL
			 * call.registerParameter(1, void.class, ParameterMode.REF_CURSOR);
			 * call.registerParameter(2, Integer.class,
			 * ParameterMode.IN).bindValue(branchId); call.registerParameter(3,
			 * Integer.class, ParameterMode.IN).bindValue(groupId);
			 * call.registerParameter(4, Date.class,
			 * ParameterMode.IN).bindValue(fdate); call.registerParameter(5,
			 * Date.class, ParameterMode.IN).bindValue(tdate);
			 * call.registerParameter(6, Integer.class,
			 * ParameterMode.IN).bindValue(mode);
			 */
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			call.registerParameter(6, Integer.class, ParameterMode.IN).bindValue(mode);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();
			// System.out.println();
			for (int i = 0; i < result.size(); i++) {
				NhsReport nhsReport = new NhsReport();
				if (mode == 0) {
					nhsReport.setBranchInternalId(result.get(i)[0].toString());
					nhsReport.setBranchName(result.get(i)[1].toString());
					nhsReport.setId(Integer.parseInt((result.get(i)[15].toString())));
				} else {
					nhsReport.setWeekday(Double.parseDouble(result.get(i)[0].toString()));
					nhsReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
					nhsReport.setId(Integer.parseInt(branchId));
					nhsReport.setNhsid(Integer.parseInt((result.get(i)[15].toString())));
				}
				nhsReport.setTotalform(Integer.parseInt((result.get(i)[2].toString())));
				nhsReport.setTotalitems(Integer.parseInt((result.get(i)[3].toString())));
				nhsReport.setPaidform(Integer.parseInt((result.get(i)[4].toString())));
				nhsReport.setPaiditems(Integer.parseInt((result.get(i)[5].toString())));
				nhsReport.setNocharge(Integer.parseInt((result.get(i)[6].toString())));
				nhsReport.setExeform(Integer.parseInt((result.get(i)[7].toString())));
				nhsReport.setExeitems(Integer.parseInt((result.get(i)[8].toString())));
				nhsReport.setOtheritems(Integer.parseInt((result.get(i)[9].toString())));
				nhsReport.setOthervalue(Double.parseDouble((result.get(i)[10].toString())));
				nhsReport.setOthervat(Double.parseDouble((result.get(i)[11].toString())));
				nhsReport.setPrivateitems(Integer.parseInt((result.get(i)[12].toString())));
				nhsReport.setPrivatevalue(Double.parseDouble((result.get(i)[13].toString())));
				nhsReport.setNhslevy(Double.parseDouble((result.get(i)[14].toString())));

				results.add(nhsReport);
			}

		} catch (

		Exception e)

		{
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

	public byte[] generateReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<BranchDetails> brnchdetails = new ArrayList<>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = "D:\\3e\\workspace\\JasperReport\\";
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			parametersH.put("group_Id", groupid);
			parametersH.put("branch_Id", branchid);
			parametersH.put("uname", null);
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			if (groupid != 0 && branchid == 0) {
				mapp.put("groupId", map.get("groupId"));
				brnchdetails = branchService.searchBranchDetails(mapp);
			} else if (groupid == 0 && branchid == 0) {
				mapp.put("groupId", null);
				brnchdetails = branchService.searchBranchDetails(mapp);
			} else {

				mapp.put("branchId", map.get("branchId"));
				brnchdetails = branchService.searchBranchDetails(mapp);

			}

			File summaryReportHeaderFile = new File(path + "CashSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyCashSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Cash Summary");
			if (brnchdetails != null) {

				for (BranchDetails branchDetails : brnchdetails) {
					for (int j = 1; j <= branchDetails.getNoOfTills(); j++) {
						parametersD.put("branch_Id", branchDetails.getId());
						parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
						parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
						parametersD.put("tillNo", j);
						parametersD.put("IntranalId", branchDetails.getInternalBranchId());
						parametersD.put("branchName", branchDetails.getDescription().substring(0,
								branchDetails.getDescription().length() - 8));
						JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
						list.add(jasperPrint_data);
						if (j == 1)
							sheetNamess.add(branchDetails.getDescription().substring(0,
									branchDetails.getDescription().length() - 8));
						else {

							sheetNamess.add(branchDetails.getDescription().substring(0,
									branchDetails.getDescription().length() - 8) + "" + String.valueOf(j));
						}

					}
				}
			}
			String[] sheetName = sheetNamess.toArray(new String[sheetNamess.size()]);
			SimpleXlsxReportConfiguration dataConfiguration = new SimpleXlsxReportConfiguration();
			dataConfiguration.setSheetNames(sheetName);
			dataConfiguration.setOnePagePerSheet(false);
			dataConfiguration.setRemoveEmptySpaceBetweenColumns(true);
			dataConfiguration.setRemoveEmptySpaceBetweenRows(true);
			dataConfiguration.setWhitePageBackground(false);
			dataConfiguration.setIgnorePageMargins(false);
			dataConfiguration.setDetectCellType(true);
			// dataConfiguration.setCellHidden(true);
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			// jasperPrint_data.setProperty("net.sf.jasperreports.print.keep.full.text",
			// "true");
			// jasperPrint_data.setProperty("net.sf.jasperreports.export.xls.wrap.text",
			// "false");
			// jasperPrint_data.setProperty("net.sf.jasperreports.export.xlsx.detect.cell.type",
			// "true");

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			// System.out.println(bytes.length);
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<BankingReport> getMonthlyBankingReport(Map<String, String> map, List<BranchDetails> branchList) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		List<BankingReport> listBank = new ArrayList<BankingReport>();

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			Query query = session.getNamedQuery("monthlyBanking");

			String qry = query.getQueryString();

			String res = qry.replace("branchNames", getBanrchName(branchList));

			SQLQuery squery = session.createSQLQuery(res);
			squery.setDate("fdate", fdate);
			squery.setDate("tdate", tdate);
			result = squery.list();
			// System.out.println(result.size());
			if (result != null && result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					BankingReport bankingReport = new BankingReport();
					bankingReport.setWeekday(Double.parseDouble(result.get(i)[0].toString()));
					bankingReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
					List<ListStringAndDouble> bankList = new ArrayList<>();
					if (branchList != null && branchList.size() > 0) {
						for (int j = 0; j < branchList.size(); j++) {
							ListStringAndDouble listStringAndDouble = new ListStringAndDouble();
							listStringAndDouble.setListName(branchList.get(j).getDescription());
							listStringAndDouble.setListValue(Double.parseDouble(result.get(i)[j + 2].toString()));
							bankList.add(listStringAndDouble);
						}

					}
					bankingReport.setBankingList(bankList);
					listBank.add(bankingReport);
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
		return listBank;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMonthlyCollDevReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			int brn = Integer.parseInt(map.get("branchid"));
			Query query = session.getNamedQuery("monthlyCollDev");
			String qry = query.getQueryString();
			String res = qry.replace("userNames", getUserName(map));
			// System.out.println(res);
			SQLQuery squery = session.createSQLQuery(res);
			squery.setDate("fdate", fdate);
			squery.setDate("tdate", tdate);
			squery.setInteger("branchID", brn);
			result = squery.list();

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
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CollDelvReport> getCollDelivReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CollDelvReport> collDelvreportlist = new ArrayList<CollDelvReport>();
		List<Object[]> result = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			// int groupId = Integer.parseInt(map.get("groupid"));
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			String userid = map.get("userid");
			ProcedureCall call = session.createStoredProcedureCall("pharma.getcolldelivreport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();
			for (int i = 0; i < result.size(); i++) {
				CollDelvReport collDelvReport = new CollDelvReport();
				collDelvReport.setBranchid(Integer.parseInt(result.get(i)[0].toString()));
				collDelvReport.setBranchInternalId(result.get(i)[1].toString());
				collDelvReport.setBranchName(result.get(i)[2].toString());
				collDelvReport.setTotalColl(Integer.parseInt(result.get(i)[3].toString()));
				collDelvReport.setTotaldelv(Integer.parseInt(result.get(i)[4].toString()));
				collDelvReport.setTotal(Integer.parseInt(result.get(i)[5].toString()));
				collDelvreportlist.add(collDelvReport);
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
		return collDelvreportlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCareHomeReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;

		List<Object[]> result = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			// int groupId = Integer.parseInt(map.get("groupid"));
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			String userid = map.get("userid");
			ProcedureCall call = session.createStoredProcedureCall("pharma.getcarehomereport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

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
		return result;
	}

	public String getBanrchName(List<BranchDetails> branchList) {

		try {
			String branchname = "(";

			for (int i = 0; i < branchList.size(); i++) {
				String arg = String.valueOf(branchList.get(i).getDescription());
				if (i == 0)
					branchname += "\"" + arg + "\"";
				else
					branchname += ",\"" + arg + "\"";

			}
			branchname += ")";
			return branchname;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	public String getUserName(Map<String, String> map) {

		try {

			String shql = null;
			List<Object[]> userlist = branchService.branchDetailsAssolist(map);

			for (int i = 0; i < userlist.size(); i++) {
				if (i == 0)
					shql = "\"" + userlist.get(i) + "\"";
				else
					shql += ",\"" + userlist.get(i) + "\"";
			}
			// System.out.println(shql);
			return shql;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CareHomesReport> getMonthlyCareHomesReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CareHomesReport> careHomesReportlist = new ArrayList<CareHomesReport>();
		List<Object[]> result = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			int groupId = Integer.parseInt(map.get("groupid"));
			int branchId = Integer.parseInt(map.get("branchid"));
			// System.out.println(tdate);
			ProcedureCall call = session.createStoredProcedureCall("pharma.getmonthlycarehomereport");
			/*
			 * ----------------------------FOR PostgreSQL
			 * call.registerParameter(1, void.class, ParameterMode.REF_CURSOR);
			 * 
			 * call.registerParameter(2, Integer.class,
			 * ParameterMode.IN).bindValue(branchId); call.registerParameter(3,
			 * Integer.class, ParameterMode.IN).bindValue(groupId);
			 * call.registerParameter(4, Date.class,
			 * ParameterMode.IN).bindValue(fdate); call.registerParameter(5,
			 * Date.class, ParameterMode.IN).bindValue(tdate);
			 */
			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

			for (int i = 0; i < result.size(); i++) {
				CareHomesReport careHomesReport = new CareHomesReport();
				careHomesReport.setWeekday(Double.parseDouble(result.get(i)[0].toString()));
				careHomesReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
				careHomesReport.setForm7day(Integer.parseInt(result.get(i)[2].toString()));
				careHomesReport.setItems7day(Integer.parseInt(result.get(i)[3].toString()));
				careHomesReport.setForm28day(Integer.parseInt(result.get(i)[4].toString()));
				careHomesReport.setItems28day(Integer.parseInt(result.get(i)[5].toString()));
				careHomesReport.setComm7form(Integer.parseInt(result.get(i)[6].toString()));
				careHomesReport.setComm7items(Integer.parseInt(result.get(i)[7].toString()));
				careHomesReport.setComm28form(Integer.parseInt(result.get(i)[8].toString()));
				careHomesReport.setComm28items(Integer.parseInt(result.get(i)[9].toString()));
				careHomesReport.setTotal7form(Integer.parseInt(result.get(i)[6].toString()));
				careHomesReport.setTotal7items(Integer.parseInt(result.get(i)[7].toString()));
				careHomesReport.setTotal28form(Integer.parseInt(result.get(i)[8].toString()));
				careHomesReport.setTotal28items(Integer.parseInt(result.get(i)[9].toString()));
				careHomesReport.setId(Integer.parseInt(result.get(i)[10].toString()));
				careHomesReport.setBranchid(branchId);
				careHomesReportlist.add(careHomesReport);
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
		return careHomesReportlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BankingReport> getBankingReport(Map<String, String> map) {

		Session session = null;
		Transaction tx = null;
		List<BankingReport> results = new ArrayList<BankingReport>();
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			int mode = Integer.parseInt(map.get("mode"));
			String userid = map.get("userid");
			ProcedureCall call = session.createStoredProcedureCall("pharma.getbankingreport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			call.registerParameter(6, Integer.class, ParameterMode.IN).bindValue(mode);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

			// System.out.println(result.size());
			for (int i = 0; i < result.size(); i++) {
				BankingReport bankingReport = new BankingReport();
				if (mode == 0) {
					bankingReport.setBranchId(Integer.parseInt(result.get(i)[0].toString()));
					bankingReport.setInternalId(result.get(i)[1].toString());
					bankingReport.setBranchName(result.get(i)[2].toString());
				} else {
					bankingReport.setWeekday(Double.parseDouble(result.get(i)[9].toString()));
					bankingReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[2].toString()));
					bankingReport.setBankingid(Integer.parseInt(result.get(i)[0].toString()));
					bankingReport.setRef(result.get(i)[1].toString());
					bankingReport.setBranchId(Integer.parseInt(branchId));
				}
				bankingReport.setCashTotal(Double.parseDouble((result.get(i)[3].toString())));
				bankingReport.setChequesTotal(Double.parseDouble((result.get(i)[4].toString())));
				bankingReport.setGiroTotal(Double.parseDouble((result.get(i)[5].toString())));
				bankingReport.setSwitchTotal(Double.parseDouble((result.get(i)[6].toString())));
				bankingReport.setOtherTotal(Double.parseDouble((result.get(i)[7].toString())));
				bankingReport.setCardTotal(Double.parseDouble((result.get(i)[8].toString())));
				bankingReport.setBankTotal(Double.parseDouble((result.get(i)[5].toString()))
						+ Double.parseDouble((result.get(i)[8].toString())));
				results.add(bankingReport);
			}

		} catch (

		Exception e)

		{
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
	public List<VATTAX> getVatTaxReport(Map<String, String> map) {

		Session session = null;
		Transaction tx = null;
		List<VATTAX> results = new ArrayList<VATTAX>();
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			int mode = Integer.parseInt(map.get("mode"));
			String userid = map.get("userid");
			ProcedureCall call = session.createStoredProcedureCall("pharma.getVatTaxAnalysisreport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			call.registerParameter(6, Integer.class, ParameterMode.IN).bindValue(mode);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();

			for (int i = 0; i < result.size(); i++) {
				VATTAX vatTaxReport = new VATTAX();
				if (mode == 0) {
					vatTaxReport.setBranchid(Integer.parseInt(result.get(i)[15].toString()));
					vatTaxReport.setBranchInternalId(result.get(i)[0].toString());
					vatTaxReport.setBranchName(result.get(i)[1].toString());
					vatTaxReport.setScript(Double.parseDouble((result.get(i)[13].toString())));
				} else {

					vatTaxReport.setWeekday(Double.parseDouble((result.get(i)[0].toString())));
					vatTaxReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
					double paiditem = Double.parseDouble(result.get(i)[13].toString());
					vatTaxReport.setPaiditems((int) paiditem);
					vatTaxReport.setTax(Double.parseDouble((result.get(i)[15].toString())));
				}
				vatTaxReport.setZeroNett(Double.parseDouble((result.get(i)[2].toString())));
				vatTaxReport.setLowNett(Double.parseDouble((result.get(i)[3].toString())));
				vatTaxReport.setStdNett(Double.parseDouble((result.get(i)[4].toString())));
				vatTaxReport.setTotalNett(Double.parseDouble((result.get(i)[5].toString())));
				vatTaxReport.setLowVat(Double.parseDouble((result.get(i)[6].toString())));
				vatTaxReport.setStdVat(Double.parseDouble((result.get(i)[7].toString())));
				vatTaxReport.setTotalVat(Double.parseDouble((result.get(i)[8].toString())));
				vatTaxReport.setZeroGross(Double.parseDouble((result.get(i)[9].toString())));
				vatTaxReport.setLowGross(Double.parseDouble((result.get(i)[10].toString())));
				vatTaxReport.setStdGross(Double.parseDouble((result.get(i)[11].toString())));
				vatTaxReport.setTotalGross(Double.parseDouble((result.get(i)[12].toString())));
				vatTaxReport.setLevy(Double.parseDouble((result.get(i)[14].toString())));

				results.add(vatTaxReport);
			}

		} catch (

		Exception e)

		{
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
	public List<ExpensesReport> getMonthlyExpensesReport(Map<String, String> map, List<Category> categorylist) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		List<ExpensesReport> listexpen = new ArrayList<ExpensesReport>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			String groupId = map.get("groupid");
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";
			String userid = map.get("userid");
			Query query = null;
			SQLQuery squery = null;
			if (groupId.length() == 0 && branchId.length() == 0) {
				query = session.getNamedQuery("expensesReportByUserId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("userId", userid);
			} else if (groupId.length() != 0 && branchId.length() == 0) {
				query = session.getNamedQuery("expensesReportByGroupId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("groupId", groupId);
			} else {
				query = session.getNamedQuery("expensesReportByBranchId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("branchID", branchId);
			}
			result = squery.list();
			if (result != null && result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					ExpensesReport expensesReport = new ExpensesReport();
					expensesReport.setBranchid(Integer.parseInt(result.get(i)[0].toString()));
					expensesReport.setBranchInternalId(result.get(i)[1].toString());
					expensesReport.setBranchName(result.get(i)[2].toString());
					List<ListStringAndDouble> listExpenses = new ArrayList<>();
					if (categorylist != null && categorylist.size() > 0) {
						for (int j = 0; j < categorylist.size(); j++) {
							ListStringAndDouble listStringAndDouble = new ListStringAndDouble();
							listStringAndDouble.setListName(categorylist.get(j).getCategoryName());
							listStringAndDouble.setListValue(Double.parseDouble(result.get(i)[j + 4].toString()));
							listExpenses.add(listStringAndDouble);
						}
					}
					if (result.get(i)[3] != null)
						expensesReport.setBranchTotal(Double.parseDouble(result.get(i)[3].toString()));
					else
						expensesReport.setBranchTotal(0);

					String reportKey = "";
					reportKey = result.get(i)[0].toString();
					reportKey = strUtility.encodeString(reportKey);
					expensesReport.setReportKey(reportKey);
					expensesReport.setListExpenses(listExpenses);
					listexpen.add(expensesReport);
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
		return listexpen;
	}

	public String getCategoryNames(List<Category> categorylist) {

		try {

			String shql = null;
			for (int i = 0; i < categorylist.size(); i++) {
				if (i == 0)
					shql = "\"" + categorylist.get(i).getCategoryName() + "\"";
				else
					shql += ",\"" + categorylist.get(i).getCategoryName() + "\"";
			}

			return shql;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerStatsReport> getCustomerStatsReport(Map<String, String> map) {
		Session session = null;
		Transaction tx = null;
		List<CustomerStatsReport> results = new ArrayList<CustomerStatsReport>();
		List<Object[]> result = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));

			String userid = map.get("userid");

			String groupId = map.get("groupid");
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";

			int mode = Integer.parseInt(map.get("mode"));

			ProcedureCall call = session.createStoredProcedureCall("pharma.show_customerstatsreport");

			/*----------------------FOR SQL SERVER-----------------------------------------------**/
			call.registerParameter(1, String.class, ParameterMode.IN).bindValue(branchId);
			call.registerParameter(2, String.class, ParameterMode.IN).bindValue(groupId);
			call.registerParameter(3, Date.class, ParameterMode.IN).bindValue(fdate);
			call.registerParameter(4, Date.class, ParameterMode.IN).bindValue(tdate);
			call.registerParameter(5, String.class, ParameterMode.IN).bindValue(userid);
			call.registerParameter(6, Integer.class, ParameterMode.IN).bindValue(mode);
			ResultSetOutput output = (ResultSetOutput) call.getOutputs().getCurrent();
			result = output.getResultList();
			for (int i = 0; i < result.size(); i++) {
				CustomerStatsReport customerStatsReport = new CustomerStatsReport();
				customerStatsReport.setBranchInternalId(result.get(i)[0].toString());
				customerStatsReport.setBranchName(result.get(i)[1].toString());
				double actualsale = 0.0;
				double totalactualsale = 0.0;
				double avg = 0.0;
				double avgExc = 0.0;
				double avgExcVat = 0.0;
				double vat = 0.0;
				double nhsleavy = 0.0;
				int nhsitems = 0;
				int sales = 0;

				nhsitems = Integer.parseInt(result.get(i)[2].toString());
				nhsleavy = Double.parseDouble(result.get(i)[3].toString());
				actualsale = Double.parseDouble(result.get(i)[4].toString());
				sales = Integer.parseInt(result.get(i)[5].toString());
				vat = Double.parseDouble(result.get(i)[6].toString());

				totalactualsale = totalactualsale + actualsale;
				
				if (sales > 0) {
					avg = totalactualsale / sales;
					avgExc = (totalactualsale - vat) / sales;
					avgExcVat = (actualsale - vat - nhsleavy) / sales;
				}

				customerStatsReport.setNhsItems(nhsitems);
				customerStatsReport.setNoOfSales(sales);
				customerStatsReport.setSalesIncActual(actualsale);
				customerStatsReport.setSalesIncAvg(avg);
				customerStatsReport.setSalesExcActual(actualsale - vat);
				customerStatsReport.setSalesExcAvg(avgExc);
				customerStatsReport.setCounterSale(actualsale - vat - nhsleavy);
				customerStatsReport.setExcVat(avgExcVat);
				customerStatsReport.setNhslevy(nhsleavy);
				results.add(customerStatsReport);
			}

		} catch (

		Exception e)

		{
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
	public List<CouponReport> getMonthlyCouponReport(Map<String, String> map, List<Category> categorylist) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		List<CouponReport> listCoupon = new ArrayList<CouponReport>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));

			String groupId = map.get("groupid");
			String branchId = map.get("branchid");
			if (groupId.equals("0"))
				groupId = "";
			if (branchId.equals("0"))
				branchId = "";

			String userid = map.get("userid");
			Query query = null;
			SQLQuery squery = null;
			if (groupId.length() == 0 && branchId.length() == 0) {
				query = session.getNamedQuery("couponReportByUserId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("userId", userid);
			} else if (groupId.length() != 0 && branchId.length() == 0) {
				query = session.getNamedQuery("couponReportByGroupId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("groupId", groupId);
			} else {
				query = session.getNamedQuery("couponReportByBranchId");
				String qry = query.getQueryString();
				String res = qry.replace("categoryNames", getCategoryNames(categorylist));
				squery = session.createSQLQuery(res);
				squery.setDate("fdate", fdate);
				squery.setDate("tdate", tdate);
				squery.setString("branchID", branchId);
			}
			result = squery.list();
			if (result != null && result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					CouponReport coupontReport = new CouponReport();
					coupontReport.setBranchid(Integer.parseInt(result.get(i)[0].toString()));
					coupontReport.setBranchInternalId(result.get(i)[1].toString());
					coupontReport.setBranchName(result.get(i)[2].toString());
					List<ListStringAndDouble> listCouponObj = new ArrayList<>();
					if (categorylist != null && categorylist.size() > 0) {
						for (int j = 0; j < categorylist.size(); j++) {
							ListStringAndDouble listStringAndDouble = new ListStringAndDouble();
							listStringAndDouble.setListName(categorylist.get(j).getCategoryName());
							listStringAndDouble.setListValue(Double.parseDouble(result.get(i)[j + 4].toString()));
							listCouponObj.add(listStringAndDouble);
						}
					}
					if (result.get(i)[3] != null)
						coupontReport.setBranchTotal(Double.parseDouble(result.get(i)[3].toString()));
					else
						coupontReport.setBranchTotal(0);
					coupontReport.setListCoupon(listCouponObj);

					listCoupon.add(coupontReport);
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
		// return result;
		return listCoupon;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExpensesReport> getMonthlyExpensesSummaryReportAsList(Map<String, String> map,
			List<Category> categorylist) {
		Session session = null;
		Transaction tx = null;
		List<Object[]> result = null;
		List<ExpensesReport> listexpen = new ArrayList<ExpensesReport>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fDate"));
			Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tDate"));
			int branchId = Integer.parseInt(map.get("branchid"));

			Query query = null;
			SQLQuery squery = null;

			query = session.getNamedQuery("monthlyExpensesReportByBranchId");
			String qry = query.getQueryString();
			String res = qry.replace("categoryNames", getCategoryNames(categorylist));
			squery = session.createSQLQuery(res);
			squery.setDate("fdate", fdate);
			squery.setDate("tdate", tdate);
			squery.setInteger("branchID", branchId);

			result = squery.list();
			if (result != null && result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					ExpensesReport expensesReport = new ExpensesReport();
					expensesReport.setWeekday(Double.parseDouble((result.get(i)[0].toString())));
					expensesReport.setEdate(new SimpleDateFormat("dd/MM/yyyy").parse(result.get(i)[1].toString()));
					List<ListStringAndDouble> listExpenses = new ArrayList<>();
					if (categorylist != null && categorylist.size() > 0) {
						for (int j = 0; j < categorylist.size(); j++) {
							ListStringAndDouble listStringAndDouble = new ListStringAndDouble();
							listStringAndDouble.setListName(categorylist.get(j).getCategoryName());
							listStringAndDouble.setListValue(Double.parseDouble(result.get(i)[j + 3].toString()));
							listExpenses.add(listStringAndDouble);

						}
					}
					if (result.get(i)[2] != null)
						expensesReport.setBranchTotal(Double.parseDouble(result.get(i)[2].toString()));
					else
						expensesReport.setBranchTotal(0);
					System.out.println(listExpenses);
					expensesReport.setListExpenses(listExpenses);

					listexpen.add(expensesReport);
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
		// return result;
		return listexpen;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Report> getReportNameASList(String reportType) {
		List<Report> list = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(com.avee.domain.Report.class);
			if (strUtility.checkNullOrEmptyString(reportType)) {
				criteria.add(Restrictions.eq("reportType", reportType)).addOrder(Order.asc("reportOrder"));

				List<com.avee.domain.Report> reportList = criteria.list();

				List<String> exclusionsList = new ArrayList<String>();
				BeansUtility bub = new BeansUtility();
				for (com.avee.domain.Report reportDomain : reportList) {
					Report form = new Report();
					bub.copy(form, reportDomain, exclusionsList);
					list.add(form);

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
}
