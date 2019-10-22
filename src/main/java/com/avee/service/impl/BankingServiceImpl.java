package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.BankingDAO;
import com.avee.form.Banking;
import com.avee.form.BankingOutstanging;
import com.avee.form.Cashing;
import com.avee.service.BankingService;

@Service
public class BankingServiceImpl implements BankingService {

	@Autowired
	BankingDAO bankingDAO;

	@Override
	public String insertBanking(Banking banking,String outsatanding,String cfamount,String cashid,List<Cashing> unBanking,String outstandingId) {
		String strObj = "";
		try {
			strObj = bankingDAO.insertBanking(banking, outsatanding, cfamount, cashid, unBanking,outstandingId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strObj;
	}

	@Override
	public List<Banking> searchBanking(Map<String, String> map) {
		List<Banking> list = null;
		try {
			list = bankingDAO.searchBanking(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Banking getBanking(int id) {
		Banking banking = null;
		try {
			banking = bankingDAO.getBanking(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return banking;
	}

	@Override
	public void updateBanking(Banking banking) {

	}

	@Override
	public void deleteBanking(int id) {

	}

	@Override
	public int countTotalBanking(String refname, int type, int branchid) {
		int id = 0;
		try {
			id = bankingDAO.countTotalBanking(refname, type, branchid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public BankingOutstanging getOutstangingBanking(int branchid, int bankingid) {
		return bankingDAO.getOutstangingBanking(branchid, bankingid);
	}

	@Override
	public BankingOutstanging getCarryForwordBanking(int branchid, int bankingid) {
		return bankingDAO.getCarryForwordBanking(branchid, bankingid);
	}

	@Override
	public String insertCarryForwordBanking(BankingOutstanging bankingoutstanging, Banking banking) {
		return bankingDAO.insertCarryForwordBanking(bankingoutstanging, banking);
	}

	@Override
	public void updateCarryForwordBanking(int id) {
		bankingDAO.updateCarryForwordBanking(id);
	}

	@Override
	public int checkBankingByStringComma(String cashId, int branchid) {
		return bankingDAO.checkBankingByStringComma(cashId, branchid);
	}

}
