package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.Banking;
import com.avee.form.BankingOutstanging;
import com.avee.form.Cashing;

public interface BankingDAO {

	public String insertBanking(Banking banking, String outsatanding, String cfamount, String cashid,
			List<Cashing> unBanking, String outstandingId);

	public List<Banking> searchBanking(Map<String, String> map);

	public Banking getBanking(int id);

	public void updateBanking(Banking banking);

	public void deleteBanking(int id);

	public int countTotalBanking(String refname, int type, int branchid);

	public BankingOutstanging getOutstangingBanking(int branchid, int bankingid);

	public BankingOutstanging getCarryForwordBanking(int branchid, int bankingid);

	public String insertCarryForwordBanking(BankingOutstanging bankingoutstanging, Banking banking);

	public void updateCarryForwordBanking(int id);
	
	public int checkBankingByStringComma(String cashId, int branchid);

}
