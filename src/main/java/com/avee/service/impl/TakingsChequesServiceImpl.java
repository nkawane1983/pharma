package com.avee.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.TakingsChequesDAO;
import com.avee.form.Banking;
import com.avee.form.Cashing;

import com.avee.form.TakingsCheques;
import com.avee.service.TakingsChequesService;
@Service
public class TakingsChequesServiceImpl implements TakingsChequesService{

	@Autowired
	TakingsChequesDAO takingsChequesDAO;
	@Override
	public String insertTakingsCheques(TakingsCheques takingscheques, Cashing cashing) {
		String id = "";
		try {
			id = takingsChequesDAO.insertTakingsCheques(takingscheques,cashing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	

	@Override
	public List<TakingsCheques> searchTakingsCheques(int cashid) {
		List<TakingsCheques> results= null;
		try {
			results = takingsChequesDAO.searchTakingsCheques(cashid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


	@Override
	public void deleteTakingsCheques(int id) {
		try {
			takingsChequesDAO.deleteTakingsCheques(id);
		} catch (Exception e) {

		}
		
	}


	@Override
	public void updateTakingsCheques(TakingsCheques takingscheques,Cashing cashing) {
		try {
			takingsChequesDAO.updateTakingsCheques(takingscheques,cashing);
		} catch (Exception e) {

		}
		
	}


	@Override
	public TakingsCheques getTakingsCheques(int id) {
		TakingsCheques takingsCheques = null;
		try {
			takingsCheques= new TakingsCheques();
			takingsCheques = takingsChequesDAO.getTakingsCheques(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takingsCheques;
	}


	@Override
	public List<TakingsCheques> unBankingChequesAsList(Map<String, String> map) {
		List<TakingsCheques> results= null;
		try {
			results = takingsChequesDAO.unBankingChequesAsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


	@Override
	public List<TakingsCheques> bankingChequesAsList(Map<String, String> map) {
		List<TakingsCheques> results= null;
		try {
			results = takingsChequesDAO.bankingChequesAsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


	@Override
	public void updateBankingIdInTakingsCheques(int cashid, Banking banking) {
		try {
			takingsChequesDAO.updateBankingIdInTakingsCheques(cashid, banking);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
}
