package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.Banking;
import com.avee.form.Cashing;

import com.avee.form.TakingsCheques;

public interface TakingsChequesService {
	public String insertTakingsCheques(TakingsCheques takingscheques, Cashing cashing);

	public List<TakingsCheques> searchTakingsCheques(int cashid);

	public void deleteTakingsCheques(int id);

	public void updateTakingsCheques(TakingsCheques takingscheques, Cashing cashing);

	public TakingsCheques getTakingsCheques(int id);

	public List<TakingsCheques> unBankingChequesAsList(Map<String, String> map);

	public List<TakingsCheques> bankingChequesAsList(Map<String, String> map);

	public void updateBankingIdInTakingsCheques(int cashid, Banking banking);

}
