package com.avee.dao;

import java.util.List;

import com.avee.form.Cashing;
import com.avee.form.TakingCoupons;

public interface TakingsCouponsDAO {
	public String insertTakingsCoupon(TakingCoupons takingcoupons, Cashing cashing);

	public List<TakingCoupons> searchTakingsCoupon(int cashid);

	public void deleteTakingCoupons(int id);

	public void updateTakingCoupons(TakingCoupons takingcoupons, Cashing cashing);

	public TakingCoupons getTakingCoupons(int id);

}
