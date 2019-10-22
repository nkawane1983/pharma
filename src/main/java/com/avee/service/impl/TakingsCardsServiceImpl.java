package com.avee.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.TakingsCardsDAO;
import com.avee.form.Banking;
import com.avee.form.Cashing;
import com.avee.form.TakingsCards;
import com.avee.service.TakingsCardsService;
@Service
public class TakingsCardsServiceImpl implements TakingsCardsService{
@Autowired
TakingsCardsDAO takingsCardsDAO;
	@Override
	public String insertTakingsCard(TakingsCards takingscards, Cashing cashing) {
		String id = "";
		try {
			id = takingsCardsDAO.insertTakingsCard(takingscards,cashing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<TakingsCards> searchTakingsCard(int cashid) {
		List<TakingsCards> results= null;
		try {
			results = takingsCardsDAO.searchTakingsCard(cashid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void deleteTakingsCards(int id) {
		try {
			takingsCardsDAO.deleteTakingsCards(id);
		} catch (Exception e) {

		}
		
	}

	@Override
	public void updateTakingsCards(TakingsCards takingscards, Cashing cashing) {
		try {
			takingsCardsDAO.updateTakingsCards(takingscards,cashing);
		} catch (Exception e) {

		}
		
	}

	@Override
	public TakingsCards getTakingsCards(int id) {
		TakingsCards takingsCards = null;
		try {
			takingsCards = new TakingsCards();
			takingsCards = takingsCardsDAO.getTakingsCards(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takingsCards;
	}

	@Override
	public List<TakingsCards> unBankingCardsAsList(Map<String, String> map) {
		List<TakingsCards> results= null;
		try {
			results = takingsCardsDAO.unBankingCardsAsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void updateBankingIdInTakingsCards(int cashid, Banking banking) {
		try {
			takingsCardsDAO.updateBankingIdInTakingsCards(cashid, banking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	

}
