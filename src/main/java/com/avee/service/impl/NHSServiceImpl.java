package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.NHSDAO;
import com.avee.form.NHS;
import com.avee.service.NHSService;

@Service
public class NHSServiceImpl implements NHSService {

	@Autowired
	NHSDAO nhsDAO;

	@Override
	public String insertNHSScript(NHS nhs) {
		String id = "";
		try {
			id = nhsDAO.insertNHSScript(nhs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<NHS> searchNHSScript(Map<String, String> map) {
		List<NHS> list = null;
		try {
			list = nhsDAO.searchNHSScript(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NHS getNHSScript(int id) {
		NHS phyNhs = null;
		try {
			phyNhs = nhsDAO.getNHSScript(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phyNhs;
	}

	@Override
	public void updateNHSScript(NHS nhs,String mode) {
		try {
			nhsDAO.updateNHSScript(nhs,mode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteNHSScript(int id) {
		try {
			nhsDAO.deleteNHSScript(id);
		} catch (Exception e) {

		}

	}

	@Override
	public List<Object[]> searchRemainingNHSScript(Map<String, String> map) {
		List<Object[]> list = null;
		try {
			list = nhsDAO.searchRemainingNHSScript(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Object[]> getNhsStats(Map<String, String> map) {

		return nhsDAO.getNhsStats(map);
	}

	@Override
	public boolean nhsExistOrNot(Map<String, String> map) {

		return nhsDAO.nhsExistOrNot(map);
	}

	@Override
	public String getPrivatePrescriptionValueForNHS(Map<String, String> map) {

		return nhsDAO.getPrivatePrescriptionValueForNHS(map);
	}

	@Override
	public NHS getLastNHSScript(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkRemainingNHS(Map<String, String> map) {
		// TODO Auto-generated method stub
		return nhsDAO.checkRemainingNHS(map);
	}

	@Override
	public String amendUpdateNHSScript(NHS nhs) {
		// TODO Auto-generated method stub
		return nhsDAO.amendUpdateNHSScript(nhs);
	}

}
