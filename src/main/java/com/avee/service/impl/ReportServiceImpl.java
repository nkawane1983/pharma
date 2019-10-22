package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.avee.form.NhsReport;
import com.avee.form.Report;
import com.avee.form.VATTAX;
import com.avee.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportDAO reportDAO;

	@Override
	public List<CashingReport> getCashReport(Map<String, String> map) {
		return reportDAO.getCashReport(map);
	}

	@Override
	public List<CashingReport> getMonthlyCashReport(Map<String, String> map) {
		return reportDAO.getMonthlyCashReport(map);
	}

	@Override
	public List<NhsReport> getMonthlyNHSReport(Map<String, String> map) {
		return reportDAO.getMonthlyNHSReport(map);
	}

	@Override
	public List<NhsReport> getNHSReport(Map<String, String> map) {
		return reportDAO.getNHSReport(map);
	}

	@Override
	public byte[] generateReport(Map<String, String> map, HttpServletResponse response) {
		return reportDAO.generateReport(map, response);
	}

	@Override
	public List<BankingReport> getMonthlyBankingReport(Map<String, String> map,List<BranchDetails> branchList) {
		return reportDAO.getMonthlyBankingReport(map,branchList);
	}

	@Override
	public List<CollDelvReport> getCollDelivReport(Map<String, String> map) {
		return reportDAO.getCollDelivReport(map);
	}

	@Override
	public List<Object[]> getCareHomeReport(Map<String, String> map) {
		return reportDAO.getCareHomeReport(map);
	}

	@Override
	public List<Object[]> getMonthlyCollDevReport(Map<String, String> map) {
		return reportDAO.getMonthlyCollDevReport(map);
	}

	@Override
	public List<CareHomesReport> getMonthlyCareHomesReport(Map<String, String> map) {
		return reportDAO.getMonthlyCareHomesReport(map);
	}

	@Override
	public List<BankingReport> getBankingReport(Map<String, String> map) {
		return reportDAO.getBankingReport(map);
	}

	@Override
	public List<ExpensesReport> getMonthlyExpensesReport(Map<String, String> map,List<Category> categorylist) {
		return reportDAO.getMonthlyExpensesReport(map,categorylist);
	}

	@Override
	public List<VATTAX> getVatTaxReport(Map<String, String> map) {
		return reportDAO.getVatTaxReport(map);
	}

	@Override
	public List<CustomerStatsReport> getCustomerStatsReport(Map<String, String> map) {
		return reportDAO.getCustomerStatsReport(map);
	}

	@Override
	public List<CouponReport> getMonthlyCouponReport(Map<String, String> map, List<Category> categorylist) {
		return reportDAO.getMonthlyCouponReport(map, categorylist);
	}

	@Override
	public List<ExpensesReport> getMonthlyExpensesSummaryReportAsList(Map<String, String> map,
			List<Category> categorylist) {
		return reportDAO.getMonthlyExpensesSummaryReportAsList(map, categorylist);
	}

	@Override
	public List<Report> getReportNameASList(String reportType) {
		// TODO Auto-generated method stub
		return reportDAO.getReportNameASList(reportType);
	}

}
