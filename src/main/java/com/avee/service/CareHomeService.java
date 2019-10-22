package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.CareHome;
import com.avee.form.CareHomesReport;
import com.avee.form.NHS;
import com.avee.form.ScriptItems;

public interface CareHomeService {

	public CareHome getCareHome(int id);

	public String insertCareHomes(CareHome careHomes);
	
	public String updateCareHomes(CareHome careHomes);

	public List<CareHome> searchCareHome(Map<String, String> map);

	public List<CareHome> getByBranchIdCareHome(Map<String, String> map);

	public String insertScriptItems(ScriptItems scriptItems);

	public String updateScriptItems(ScriptItems scriptItems,String mode);

	public List<ScriptItems> getByScriptItems(Map<String, String> map);

	public ScriptItems getScriptItemsById(int id);

	public boolean checkScriptItemsExistOrNot(ScriptItems scriptItems);

	public List<CareHomesReport> getCareHomesScriptItemsAsList(Map<String, String> map);

	public CareHome getLastCareHomesScriptItems(Map<String, String> map);
	
	public List<CareHomesReport> getByCareHomeScriptItems(Map<String, String> map);

}
