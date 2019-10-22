package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.form.TakingsCards;

public interface TakingsCardsDAO {
	public String insertTakingsCard(TakingsCards takingscards, Cashing cashing);

	public List<TakingsCards> searchTakingsCard(int cashid);

	public void deleteTakingsCards(int id);

	public void updateTakingsCards(TakingsCards takingscards, Cashing cashing);

	public TakingsCards getTakingsCards(int id);
	
	public List<TakingsCards> unBankingCardsAsList(Map<String, String> map);

	public void updateBankingIdInTakingsCards(int cashid, Banking banking);

}
