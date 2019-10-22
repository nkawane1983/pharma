package com.avee.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.avee.form.Report;

public interface ExcelReportDAO {
	public byte[] generateMonthlyCashReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCashReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generatecommanFileReport(Map<String, String> map, HttpServletResponse response,List<Report> list);
	public byte[] generateMonthlyNhSReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateBankingReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyBankingReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCareHomesReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyCareHomesReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCollDelvReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyCollDelvReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyExpensesReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateExpensesReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateVATReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyVATReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateMonthlyCouponsReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCouponsReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCustomerStatsReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response);
}
