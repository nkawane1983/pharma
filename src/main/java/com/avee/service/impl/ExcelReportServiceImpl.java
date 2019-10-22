package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.avee.dao.ExcelReportDAO;
import com.avee.form.Report;
import com.avee.service.ExcelReportService;

@Service
public class ExcelReportServiceImpl implements ExcelReportService {
	@Autowired
	ExcelReportDAO excelReportdao;

	@Override
	public byte[] generateMonthlyCashReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyCashReport(map, response);
	}

	@Override
	public byte[] generateCashReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateCashReport(map, response);
	}

	@Override
	public byte[] generatecommanFileReport(Map<String, String> map, HttpServletResponse response,List<Report> list) {
		// TODO Auto-generated method stub
		return excelReportdao.generatecommanFileReport(map, response,list);
	}

	@Override
	public byte[] generateMonthlyNhSReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyNhSReport(map, response);
	}

	@Override
	public byte[] generateBankingReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateBankingReport(map, response);
	}

	@Override
	public byte[] generateMonthlyBankingReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyBankingReport(map, response);
	}

	@Override
	public byte[] generateCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateCareHomesReport(map, response);
	}

	@Override
	public byte[] generateMonthlyCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyCareHomesReport(map, response);
	}

	@Override
	public byte[] generateCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateCollDelvReport(map, response);
	}

	@Override
	public byte[] generateMonthlyCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyCollDelvReport(map, response);
	}

	@Override
	public byte[] generateMonthlyExpensesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyExpensesReport(map, response);
	}

	@Override
	public byte[] generateExpensesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateExpensesReport(map, response);
	}

	@Override
	public byte[] generateVATReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateVATReport(map, response);
	}

	@Override
	public byte[] generateMonthlyVATReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyVATReport(map, response);
	}

	@Override
	public byte[] generateMonthlyCouponsReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateMonthlyCouponsReport(map, response);
	}

	@Override
	public byte[] generateCouponsReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateCouponsReport(map, response);
	}

	@Override
	public byte[] generateCustomerStatsReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateCustomerStatsReport(map, response);
	}

	@Override
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return excelReportdao.generateReportCSV(map, response);
	}

}
