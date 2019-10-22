package com.avee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.LookingDAO;
import com.avee.form.CountryCode;
import com.avee.form.Denomination;
import com.avee.form.Menu;
import com.avee.form.Version;
import com.avee.service.LookingService;
import com.avee.service.UserTypeService;

@Service
public class LookingServiceImpl implements LookingService {

	@Autowired
	LookingDAO lookingDAO;
	@Autowired
	private UserTypeService userTypeservice;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Denomination> getDenominationsAsList() {
		List<Denomination> results = new ArrayList();
		try {
			results = lookingDAO.getDenominationsAsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Denomination> getDenominationsAsList(int cashid) {
		List<Denomination> results = new ArrayList();
		try {
			results = lookingDAO.getDenominationsAsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public boolean verifyGroupAndBranch(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return lookingDAO.verifyGroupAndBranch(searchMap);
	}

	@Override
	public List<CountryCode> getAllCountryAsList() {
		// TODO Auto-generated method stub
		return lookingDAO.getAllCountryAsList();
	}

	@Override
	public List<Object[]> userGuides(Map<String, String> map) {
		// TODO Auto-generated method stub
		return lookingDAO.userGuides(map);
	}

	@Override
	public List<Object[]> getGroupDefault(Map<String, String> map) {
		// TODO Auto-generated method stub
		return lookingDAO.getGroupDefault(map);
	}

	@Override
	public List<Menu> getMenuListByUserRole(String menutype) {
		// TODO Auto-generated method stub
		return lookingDAO.getMenuListByUserRole(userTypeservice.getUserType(Integer.parseInt(menutype), true).getName());
	}

	@Override
	public List<Object[]> getdashboard(Map<String, String> map) {
		// TODO Auto-generated method stub
		return lookingDAO.getdashboard(map);
	}

	@Override
	public Map<String, Object> getNhsChartDailyData(String gid,String bid) {
		// TODO Auto-generated method stub
		return lookingDAO.getNhsChartDailyData( gid, bid);
	}

	@Override
	public Map<String, Object> getTakingChartDailyData(String gid, String bid) {
		// TODO Auto-generated method stub
		return lookingDAO.getTakingChartDailyData(gid, bid);
	}

	@Override
	public Version getLatestVersion() {
		// TODO Auto-generated method stub
		return lookingDAO.getLatestVersion();
	}

}
