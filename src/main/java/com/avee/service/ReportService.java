package com.avee.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

public interface ReportService {
	public List<CashingReport> getCashReport(Map<String, String> map);

	public List<CashingReport> getMonthlyCashReport(Map<String, String> map);

	public List<NhsReport> getMonthlyNHSReport(Map<String, String> map);

	public List<NhsReport> getNHSReport(Map<String, String> map);

	public byte[] generateReport(Map<String, String> map, HttpServletResponse response);

	public List<BankingReport> getMonthlyBankingReport(Map<String, String> map,List<BranchDetails> branchList);

	public List<CollDelvReport> getCollDelivReport(Map<String, String> map);

	public List<Object[]> getCareHomeReport(Map<String, String> map);

	public List<Object[]> getMonthlyCollDevReport(Map<String, String> map);

	public List<ExpensesReport> getMonthlyExpensesReport(Map<String, String> map,List<Category> categorylist);

	public List<CareHomesReport> getMonthlyCareHomesReport(Map<String, String> map);

	public List<BankingReport> getBankingReport(Map<String, String> map);
	
	public List<VATTAX> getVatTaxReport(Map<String, String> map);
	
	public List<CustomerStatsReport> getCustomerStatsReport(Map<String, String> map);
	
	public List<CouponReport> getMonthlyCouponReport(Map<String, String> map,List<Category> categorylist);
	
	public List<ExpensesReport> getMonthlyExpensesSummaryReportAsList(Map<String, String> map,List<Category> categorylist);
	
	public List<Report> getReportNameASList(String reportType);
}
