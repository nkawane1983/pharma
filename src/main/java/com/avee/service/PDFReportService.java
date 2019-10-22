package com.avee.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface PDFReportService {
	public byte[] generateMonthlyCashReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateCashReport(Map<String, String> map, HttpServletResponse response);
	public byte[] generateNhSReport(Map<String, String> map, HttpServletResponse response);
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
}
