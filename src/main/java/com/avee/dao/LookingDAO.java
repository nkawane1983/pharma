package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.CountryCode;
import com.avee.form.Denomination;
import com.avee.form.Menu;
import com.avee.form.Version;

public interface LookingDAO {
	public List<Denomination> getDenominationsAsList();

	public List<Denomination> getDenominationsAsList(int cashid);

	public boolean verifyGroupAndBranch(Map<String, String> searchMap);

	public List<CountryCode> getAllCountryAsList();

	public List<Object[]> userGuides(Map<String, String> map);

	public List<Object[]> getGroupDefault(Map<String, String> map);

	public List<Menu> getMenuListByUserRole(String menutype);
	
	public List<Object[]> getdashboard(Map<String, String> map);
	public Map<String,Object> getNhsChartDailyData(String gid,String bid);
	public Map<String, Object> getTakingChartDailyData(String gid, String bid);
	public Version getLatestVersion();
}
