package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.Cashing;
import com.avee.form.TakingsCash;

public interface TakingCashDAO {
	public String insertTakingCash(TakingsCash takingcash, Cashing cashing);

	public List<TakingsCash> searchTakingCash(int cashid);

	public void deleteTakingsCash(int id);

	public void updateTakingsCash(TakingsCash takingcash, Cashing cashing);

	public TakingsCash getTakingsCash(int id);

	public List<TakingsCash> unBankingCashAsList(Map<String, String> map);

	public List<TakingsCash> bankingCashAsList(Map<String, String> map);

}
