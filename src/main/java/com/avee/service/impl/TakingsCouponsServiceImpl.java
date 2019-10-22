package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.TakingsCouponsDAO;
import com.avee.form.Cashing;
import com.avee.form.TakingCoupons;
import com.avee.service.TakingsCouponsService;

@Service
public class TakingsCouponsServiceImpl implements TakingsCouponsService {
	@Autowired
	TakingsCouponsDAO takingsCouponsDAO;
	@Override
	public String insertTakingsCoupon(TakingCoupons takingcoupons, Cashing cashing) {
		String id = "";
		try {
			id = takingsCouponsDAO.insertTakingsCoupon(takingcoupons, cashing);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<TakingCoupons> searchTakingsCoupon(int cashid) {
		List<TakingCoupons> results= null;
		try {
			results = takingsCouponsDAO.searchTakingsCoupon(cashid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void deleteTakingCoupons(int id) {
		try {
			takingsCouponsDAO.deleteTakingCoupons(id);
		} catch (Exception e) {}
		
	}

	@Override
	public void updateTakingCoupons(TakingCoupons takingcoupons, Cashing cashing) {
		try {
			takingsCouponsDAO.updateTakingCoupons(takingcoupons,cashing);
		} catch (Exception e) {}
		
	}

	@Override
	public TakingCoupons getTakingCoupons(int id) {
		TakingCoupons takingCoupons = null;
		try {
			takingCoupons = takingsCouponsDAO.getTakingCoupons(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return takingCoupons;
	}

	@Override
	public List<TakingCoupons> unBankingCouponsAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
