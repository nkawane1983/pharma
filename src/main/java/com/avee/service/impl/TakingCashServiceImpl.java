package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.TakingCashDAO;
import com.avee.form.Cashing;
import com.avee.form.TakingsCash;
import com.avee.service.TakingCashService;

@Service
public class TakingCashServiceImpl implements TakingCashService {
	@Autowired
	TakingCashDAO takingcashDAO;

	@Override
	public String insertTakingCash(TakingsCash takingcash, Cashing cashing) {
		String id = "";
		try {
			id = takingcashDAO.insertTakingCash(takingcash, cashing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<TakingsCash> searchTakingCash(int cashid) {
		List<TakingsCash> results = null;
		try {
			results = takingcashDAO.searchTakingCash(cashid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void deleteTakingsCash(int id) {
		try {
			takingcashDAO.deleteTakingsCash(id);
		} catch (Exception e) {
		}
	}

	@Override
	public TakingsCash getTakingsCash(int id) {
		TakingsCash takingCash = null;
		try {
			takingCash = new TakingsCash();
			takingCash = takingcashDAO.getTakingsCash(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takingCash;
	}

	@Override
	public void updateTakingsCash(TakingsCash takingcash, Cashing cashing) {
		try {
			takingcashDAO.updateTakingsCash(takingcash, cashing);
		} catch (Exception e) {
		}

	}

	@Override
	public List<TakingsCash> unBankingCashAsList(Map<String, String> map) {
		List<TakingsCash> results = null;
		try {
			results = takingcashDAO.unBankingCashAsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<TakingsCash> bankingCashAsList(Map<String, String> map) {
		List<TakingsCash> results = null;
		try {
			results = takingcashDAO.bankingCashAsList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
