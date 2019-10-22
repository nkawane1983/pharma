package com.avee.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.ExcelReportDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchDetails;
import com.avee.form.Report;
import com.avee.service.BranchService;
import com.avee.service.CategoryService;
import com.avee.service.GroupService;
import com.avee.service.SysParamService;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Transactional
@Component
public class ExcelReportDAOImpl implements ExcelReportDAO {
	static final Logger logger = LogManager.getLogger(ExcelReportDAOImpl.class.getName());
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BranchService branchService;
	@Autowired
	GroupService groupService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SysParamService sysParamService;

	@Override
	public byte[] generateMonthlyCashReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<BranchDetails> brnchdetails = new ArrayList<BranchDetails>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			String branchid = map.get("branchId");
			String groupid = map.get("groupId");
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid.length()>0 && branchid.length()==0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsASList(groupid, "groupId",userid,"");
			} else if (groupid.length()==0 && branchid.length()==0) {
				parametersH.put("group_Id", "");
				parametersH.put("branch_Id", "");
				parametersH.put("uname", userid);
				brnchdetails = branchService.getBranchDetailsASList(groupid, "All",userid,"");
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsASList(branchid, "branchId",userid,""); // branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "CashSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyCashSummaryXls.jasper");
			File summaryTillReportDataFile = new File(path + "monthlyTillCashSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);
			FileInputStream jasperTillXML_data = new FileInputStream(summaryTillReportDataFile);
			JasperReport jasRepTillYE_data = (JasperReport) JRLoader.loadObject(jasperTillXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Cash Summary");
			if (brnchdetails != null) {

				for (BranchDetails branchDetails : brnchdetails) {
					parametersD.put("SUBREPORT_DIR", path);
					int tillno = branchDetails.getNoOfTills();
							if (tillno > 1) {
								parametersD.put("branch_Id", branchDetails.getId());
								parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
								parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
								parametersD.put("IntranalId", branchDetails.getInternalBranchId());
								parametersD.put("branchName", branchDetails.getDescription().substring(0,
										branchDetails.getDescription().length() - 8));
								JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepTillYE_data, parametersD,
										conn);
								list.add(jasperPrint_data);
								sheetNamess.add(branchDetails.getDescription().substring(0,
										branchDetails.getDescription().length() - 8));
							}
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
						if (j == 1) {
							if (tillno > 1) {
								sheetNamess.add(branchDetails.getDescription().substring(0,
										branchDetails.getDescription().length() - 8) + "(" + String.valueOf(j) + ")");
							} else {
								sheetNamess.add(branchDetails.getDescription().substring(0,
										branchDetails.getDescription().length() - 8));
							}
						} else {

							sheetNamess.add(branchDetails.getDescription().substring(0,
									branchDetails.getDescription().length() - 8) + "(" + String.valueOf(j) + ")");
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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateCashReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();

		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			String branchid = map.get("branchId");
			String groupid = map.get("groupId");
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid.length()>0 && branchid.length()==0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", "");
				parametersH.put("uname", null);
		
			} else if (groupid.length()==0 && branchid.length()==0) {
				parametersH.put("group_Id", "");
				parametersH.put("branch_Id", "");
				parametersH.put("uname", userid);
		
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
	
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);
		

			File summaryReportHeaderFile = new File(path + "CashSummary.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Cash Summary");

			String[] sheetName = sheetNamess.toArray(new String[sheetNamess.size()]);
			SimpleXlsxReportConfiguration dataConfiguration = new SimpleXlsxReportConfiguration();
			dataConfiguration.setSheetNames(sheetName);
			dataConfiguration.setOnePagePerSheet(false);
			dataConfiguration.setRemoveEmptySpaceBetweenColumns(true);
			dataConfiguration.setRemoveEmptySpaceBetweenRows(true);
			dataConfiguration.setWhitePageBackground(false);
			dataConfiguration.setIgnorePageMargins(false);
			dataConfiguration.setDetectCellType(true);
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(new SimpleExporterInput(jasperPrint_Header));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generatecommanFileReport(Map<String, String> map, HttpServletResponse response,List<Report> reportlist){
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<BranchDetails> brnchdetails = new ArrayList<BranchDetails>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			String branchid = map.get("branchId");
			String groupid = map.get("groupId");
			String gname = map.get("groupname");
			String userid = map.get("userid");
			if (groupid.length()>0 && branchid.length()==0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsASList(groupid, "groupId",userid,"");
			} else if (groupid.length()==0 && branchid.length()==0) {
				parametersH.put("group_Id", "");
				parametersH.put("branch_Id", "");
				parametersH.put("uname", userid);
				brnchdetails = branchService.getBranchDetailsASList(groupid, "All",userid,"");
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
		
				brnchdetails = branchService.getBranchDetailsASList(branchid, "branchId",userid,""); // branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);
			ArrayList<String> sheetNamess = new ArrayList<String>();
			List<JasperPrint> list = new ArrayList<JasperPrint>();
			File summaryReportHeaderFile=null;
			File summaryReportDataFile=null;
			if(reportlist!=null && reportlist.size()==1){
			 summaryReportHeaderFile = new File(path + reportlist.get(0).getReportFileName());
			 sheetNamess.add(reportlist.get(0).getReportSheetName());
			}
			if(reportlist!=null && reportlist.size()==2){
				summaryReportHeaderFile = new File(path + reportlist.get(0).getReportFileName());
				summaryReportDataFile = new File(path + reportlist.get(1).getReportFileName());
				sheetNamess.add(reportlist.get(0).getReportSheetName());
			}
			

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			
			list.add(jasperPrint_Header);
			
			
			if(reportlist!=null && reportlist.size()==2){
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			
			
			if (brnchdetails != null) {

				for (BranchDetails branchDetails  : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getId());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getDescription().substring(0, branchDetails.getDescription().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getDescription().substring(0, branchDetails.getDescription().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateMonthlyNhSReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");


			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "NHSSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyNHSSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("NHS Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateBankingReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyBankingReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "BankingSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyBankingSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Banking Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");

String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "careHomeSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlycareHomeSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("CareHome Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "colldelSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlycolldeleSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("CollectionDelivery Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateMonthlyExpensesReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);
			File summaryReportHeaderFile = new File(path + "expensesSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyexpensesSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Expenses Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateExpensesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateVATReport(Map<String, String> map, HttpServletResponse response) {
		return null;
	}

	@Override
	public byte[] generateMonthlyVATReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();
		Map<String, Object> parametersD = new HashMap<>();
		List<AppUserGroupBranchMapping> brnchdetails = new ArrayList<AppUserGroupBranchMapping>();
		Map<String, String> mapp = new HashMap<>();
		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(groupid));
				mapp.put("branchID", "0");
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
				mapp.put("uid", userid);
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
				mapp.put("uid", userid);
				mapp.put("groupId", String.valueOf(0));
				mapp.put("branchID", String.valueOf(branchid));
				brnchdetails = branchService.getBranchDetailsaAsList(mapp);
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);

			File summaryReportHeaderFile = new File(path + "vatAnalysisSummary.jasper");
			File summaryReportDataFile = new File(path + "monthlyVAtSummaryXls.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			List<JasperPrint> list = new ArrayList<JasperPrint>();
			list.add(jasperPrint_Header);
			FileInputStream jasperXML_data = new FileInputStream(summaryReportDataFile);
			JasperReport jasRepYE_data = (JasperReport) JRLoader.loadObject(jasperXML_data);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Analysis Summary");
			if (brnchdetails != null) {

				for (AppUserGroupBranchMapping branchDetails : brnchdetails) {

					parametersD.put("branch_Id", branchDetails.getBrnachid());
					parametersD.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
					parametersD.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
					parametersD.put("IntranalId", branchDetails.getInternalBranchId());
					parametersD.put("branchName",
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));
					JasperPrint jasperPrint_data = JasperFillManager.fillReport(jasRepYE_data, parametersD, conn);
					list.add(jasperPrint_data);

					sheetNamess.add(
							branchDetails.getBranchName().substring(0, branchDetails.getBranchName().length() - 8));

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
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(SimpleExporterInput.getInstance(list));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			//////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateMonthlyCouponsReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();

		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
		
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
		
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
	
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);
		

			File summaryReportHeaderFile = new File(path + "couponSummary.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Coupon Summary");

			String[] sheetName = sheetNamess.toArray(new String[sheetNamess.size()]);
			SimpleXlsxReportConfiguration dataConfiguration = new SimpleXlsxReportConfiguration();
			dataConfiguration.setSheetNames(sheetName);
			dataConfiguration.setOnePagePerSheet(false);
			dataConfiguration.setRemoveEmptySpaceBetweenColumns(true);
			dataConfiguration.setRemoveEmptySpaceBetweenRows(true);
			dataConfiguration.setWhitePageBackground(false);
			dataConfiguration.setIgnorePageMargins(false);
			dataConfiguration.setDetectCellType(true);
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(new SimpleExporterInput(jasperPrint_Header));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			return bytes;
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
		return null;
	}

	@Override
	public byte[] generateCouponsReport(Map<String, String> map, HttpServletResponse response) {
		return null;
	}

	@Override
	public byte[] generateCustomerStatsReport(Map<String, String> map, HttpServletResponse response) {
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();

		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
			int branchid = Integer.parseInt(map.get("branchId"));
			int groupid = Integer.parseInt(map.get("groupId"));
			String gname = map.get("groupname");
			String userid = map.get("userid");

			if (groupid != 0 && branchid == 0) {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
		
			} else if (groupid == 0 && branchid == 0) {
				parametersH.put("group_Id", 0);
				parametersH.put("branch_Id", 0);
				parametersH.put("uname", userid);
		
			} else {
				parametersH.put("group_Id", groupid);
				parametersH.put("branch_Id", branchid);
				parametersH.put("uname", null);
	
			}
			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
			parametersH.put("groupName", gname);
		

			File summaryReportHeaderFile = new File(path + "CustomerStats.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

			ArrayList<String> sheetNamess = new ArrayList<String>();
			sheetNamess.add("Customer Stats Summary");

			String[] sheetName = sheetNamess.toArray(new String[sheetNamess.size()]);
			SimpleXlsxReportConfiguration dataConfiguration = new SimpleXlsxReportConfiguration();
			dataConfiguration.setSheetNames(sheetName);
			dataConfiguration.setOnePagePerSheet(false);
			dataConfiguration.setRemoveEmptySpaceBetweenColumns(true);
			dataConfiguration.setRemoveEmptySpaceBetweenRows(true);
			dataConfiguration.setWhitePageBackground(false);
			dataConfiguration.setIgnorePageMargins(false);
			dataConfiguration.setDetectCellType(true);
			dataConfiguration.setWrapText(true);
			dataConfiguration.setCollapseRowSpan(true);

			JRXlsxExporter xlsxExporterData = new JRXlsxExporter();
			xlsxExporterData.setExporterInput(new SimpleExporterInput(jasperPrint_Header));
			xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			xlsxExporterData.setConfiguration(dataConfiguration);

			xlsxExporterData.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}
	
	@Override
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response) {
		
		Session session = null;
		Transaction tx = null;
		Map<String, Object> parametersH = new HashMap<>();

		try {
			String path = sysParamService.getSystemParameter("jsperPath").getParameterValue();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			File tempFile = File.createTempFile("report", ".xlsx");
			tempFile.deleteOnExit();
//			int branchid = Integer.parseInt(map.get("branchId"));
//			int groupid = Integer.parseInt(map.get("groupId"));
//			String gname = map.get("groupname");
//			String userid = map.get("userid");

//			if (groupid != 0 && branchid == 0) {
//				parametersH.put("group_Id", groupid);
//				parametersH.put("branch_Id", branchid);
//				parametersH.put("uname", null);
//		
//			} else if (groupid == 0 && branchid == 0) {
//				parametersH.put("group_Id", 0);
//				parametersH.put("branch_Id", 0);
//				parametersH.put("uname", userid);
//		
//			} else {
//				parametersH.put("group_Id", groupid);
//				parametersH.put("branch_Id", branchid);
//				parametersH.put("uname", null);
//	
//			}
//			parametersH.put("fdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("fdate")));
//			parametersH.put("tdate", new SimpleDateFormat("yyyy-MM-dd").parse(map.get("tdate")));
//			parametersH.put("groupName", gname);
		

			File summaryReportHeaderFile = new File(path + "sagefiletwo.jasper");

			FileInputStream jasperXML_Header = new FileInputStream(summaryReportHeaderFile);
			JasperReport jasRepYE_Header = (JasperReport) JRLoader.loadObject(jasperXML_Header);
			JasperPrint jasperPrint_Header = JasperFillManager.fillReport(jasRepYE_Header, parametersH, conn);

		

			JRCsvExporter  exporter = new JRCsvExporter();
			//xlsxExporterData.setExporterInput(new SimpleExporterInput(jasperPrint_Header));
			// exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint_Header);
			//xlsxExporterData.setExporterOutput(new SimpleOutputStreamExporterOutput(tempFile));
			 //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, tempFile);
			 
			 exporter.setExporterInput(new SimpleExporterInput(jasperPrint_Header));
				exporter.setExporterOutput(new SimpleWriterExporterOutput(tempFile));
			//xlsxExporterData.setConfiguration(dataConfiguration);

			 exporter.exportReport();

			InputStream is = new FileInputStream(tempFile);
			byte[] bytes = IOUtils.toByteArray(is);
			////// System.out.println(bytes.length);
			return bytes;
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
		return null;
	}
	
}
