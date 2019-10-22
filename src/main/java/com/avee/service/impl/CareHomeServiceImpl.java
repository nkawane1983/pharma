package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.CareHomeDAO;
import com.avee.form.CareHome;
import com.avee.form.CareHomesReport;
import com.avee.form.ScriptItems;
import com.avee.service.CareHomeService;

@Service
public class CareHomeServiceImpl implements CareHomeService {
	@Autowired
	CareHomeDAO careHomedao;

	@Override
	public CareHome getCareHome(int id) {
		return careHomedao.getCareHome(id);
	}

	@Override
	public List<CareHome> searchCareHome(Map<String, String> map) {
		return careHomedao.searchCareHome(map);
	}

	@Override
	public List<CareHome> getByBranchIdCareHome(Map<String, String> map) {
		return careHomedao.getByBranchIdCareHome(map);
	}

	@Override
	public String insertScriptItems(ScriptItems scriptItems) {
		return careHomedao.insertScriptItems(scriptItems);
	}

	@Override
	public List<ScriptItems> getByScriptItems(Map<String, String> map) {
		return careHomedao.getByScriptItems(map);
	}

	@Override
	public String insertCareHomes(CareHome careHomes) {
		// TODO Auto-generated method stub
		return careHomedao.insertCareHomes(careHomes);
	}

	@Override
	public List<CareHomesReport> getCareHomesScriptItemsAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return careHomedao.getCareHomesScriptItemsAsList(map);
	}

	@Override
	public ScriptItems getScriptItemsById(int id) {
		// TODO Auto-generated method stub
		return careHomedao.getScriptItemsById(id);
	}

	@Override
	public String updateScriptItems(ScriptItems scriptItems, String mode) {
		// TODO Auto-generated method stub
		String msg = "";
		if (mode.equalsIgnoreCase("scriptItemsUpdate")) {
			careHomedao.deleteScriptItems(scriptItems);
		}
		msg = careHomedao.updateScriptItems(scriptItems,mode);

		return msg;
	}

	@Override
	public boolean checkScriptItemsExistOrNot(ScriptItems scriptItems) {
		// TODO Auto-generated method stub
		return careHomedao.checkScriptItemsExistOrNot(scriptItems);
	}

	@Override
	public CareHome getLastCareHomesScriptItems(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CareHomesReport> getByCareHomeScriptItems(Map<String, String> map) {
		// TODO Auto-generated method stub
		return careHomedao.getByCareHomeScriptItems(map);
	}

	@Override
	public String updateCareHomes(CareHome careHomes) {
		// TODO Auto-generated method stub
		return null;
	}

}
