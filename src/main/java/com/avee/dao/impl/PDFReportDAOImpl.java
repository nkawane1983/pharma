package com.avee.dao.impl;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.avee.dao.PDFReportDAO;
@Transactional
@Component
public class PDFReportDAOImpl implements PDFReportDAO{

	@Override
	public byte[] generateMonthlyCashReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateCashReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateNhSReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyNhSReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateBankingReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyBankingReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyCareHomesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyCollDelvReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyExpensesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateExpensesReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateVATReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyVATReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateMonthlyCouponsReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] generateCouponsReport(Map<String, String> map, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
