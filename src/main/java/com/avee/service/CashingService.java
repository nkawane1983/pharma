package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.AmendmentTill;
import com.avee.form.AmendmentTillHO;
import com.avee.form.Banking;
import com.avee.form.Cashing;

public interface CashingService {

	public String insertCashing(Cashing cashing);

	public List<Cashing> searchCashing(Map<String, String> map);

	public Cashing getCashing(int id);

	public String updateCashing(Cashing cashing);

	public String editCashing(Cashing cashing);

	public void deleteCashing(int id);

	public List<Object[]> searchRemainingCashing(Map<String, String> map);

	public boolean cashingExistOrNot(Map<String, String> map);

	public List<Object[]> getUnBankingCashAndCheques(Map<String, String> map);

	public List<Cashing> unBankingCashingAsList(String cashid, Map<String, String> map);

	public List<Cashing> bankingCashingAsList(int branchid, int bankId);

	public void updateBankingIdInCashing(int cashid, Banking banking);

	public List<Object[]> getNotification(Map<String, String> map);

	public int checkRemainingCashingUp(Map<String, String> map);

	public Cashing getCashSummaryById(int id);

	public String[] insertAmendmentTilRequest(AmendmentTill amendmentTill);
	
	public AmendmentTill getAmendmentTilRequest(int id);
	
	public String updateAmendmentTilRequest(AmendmentTill amendmentTill);

	public String updateCashingByRequset(Cashing cashing, String note, String amdId);

	public String saveOrUpdateAmendmentTilByHO(AmendmentTillHO amendmentTillHO, Cashing cashing,String mode);

	public AmendmentTillHO getAmendmentTillByHO(int cashId, int branchId);
	
	public boolean checkAmendmentTilRequestExistOrNot(String cashId,int branchid);
	
	public boolean checkChashingCompleteOrNot(int cashId,int branchid);
}
