package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.PaidOutsDAO;
import com.avee.form.Cashing;
import com.avee.form.PaidOuts;

import com.avee.service.PaidOutsService;

@Service
public class PaidOutsServiceImpl implements PaidOutsService {

	@Autowired
	PaidOutsDAO paidOutsDAO;

	@Override
	public String insertPaidOuts(PaidOuts paidouts, Cashing cashing) {
		String id = "";
		try {
			id = paidOutsDAO.insertPaidOuts(paidouts, cashing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<PaidOuts> searchPaidOuts(int cashid) {
		List<PaidOuts> results = null;
		try {
			results = paidOutsDAO.searchPaidOuts(cashid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void deletePaidOuts(int id) {
		try {
			paidOutsDAO.deletePaidOuts(id);
		} catch (Exception e) {
		}

	}

	@Override
	public void updatePaidOuts(PaidOuts paidouts, Cashing cashing) {
		try {
			paidOutsDAO.updatePaidOuts(paidouts, cashing);
		} catch (Exception e) {
		}

	}

	@Override
	public PaidOuts getPaidOuts(int id) {
		PaidOuts paidouts = null;
		try {
			paidouts = new PaidOuts();
			paidouts = paidOutsDAO.getPaidOuts(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paidouts;
	}

	@Override
	public List<PaidOuts> unBankingPaidOutsAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return paidOutsDAO.unBankingPaidOutsAsList(map);
	}

}
