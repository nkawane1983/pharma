package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.Cashing;
import com.avee.form.TakingCoupons;

public interface TakingsCouponsService {
	public String insertTakingsCoupon(TakingCoupons takingcoupons, Cashing cashing);

	public List<TakingCoupons> searchTakingsCoupon(int cashid);

	public void deleteTakingCoupons(int id);

	public void updateTakingCoupons(TakingCoupons takingcoupons, Cashing cashing);

	public TakingCoupons getTakingCoupons(int id);
	
	public List<TakingCoupons> unBankingCouponsAsList(Map<String, String> map);
}
