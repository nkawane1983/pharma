package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.CareHome;
import com.avee.form.CareHomesReport;
import com.avee.form.ScriptItems;

public interface CareHomeDAO {

	public CareHome getCareHome(int id);

	public String insertCareHomes(CareHome careHomes);

	public List<CareHome> searchCareHome(Map<String, String> map);

	public List<CareHome> getByBranchIdCareHome(Map<String, String> map);

	public String insertScriptItems(ScriptItems scriptItems);

	public String updateScriptItems(ScriptItems scriptItems,String mode);

	public List<ScriptItems> getByScriptItems(Map<String, String> map);

	public ScriptItems getScriptItemsById(int id);

	public void deleteScriptItems(ScriptItems scriptItems);

	public List<CareHomesReport> getCareHomesScriptItemsAsList(Map<String, String> map);

	public boolean checkScriptItemsExistOrNot(ScriptItems scriptItems);

	public CareHome getLastCareHomesScriptItems(Map<String, String> map);

	public List<CareHomesReport> getByCareHomeScriptItems(Map<String, String> map);
}
