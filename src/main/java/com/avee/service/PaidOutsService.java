package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.Cashing;
import com.avee.form.PaidOuts;

public interface PaidOutsService {

	public String insertPaidOuts(PaidOuts paidouts, Cashing cashing);

	public List<PaidOuts> searchPaidOuts(int cashid);

	public void deletePaidOuts(int id);

	public void updatePaidOuts(PaidOuts paidouts, Cashing cashing);

	public PaidOuts getPaidOuts(int id);
	
	public List<PaidOuts> unBankingPaidOutsAsList(Map<String, String> map);
}
