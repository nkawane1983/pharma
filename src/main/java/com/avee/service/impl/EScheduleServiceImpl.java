package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.EScheduleDAO;
import com.avee.form.MonthYears;
import com.avee.form.Ppa500;
import com.avee.form.Ppa600;
import com.avee.form.Ppa800;
import com.avee.form.PpaData;
import com.avee.form.PpaMasterReport;
import com.avee.service.EScheduleService;

@Service
public class EScheduleServiceImpl implements EScheduleService {
	@Autowired
	EScheduleDAO eScheduleDao;

	@Override
	public String insertPPA500(Ppa500 ppa500) {
		return eScheduleDao.insertPPA500(ppa500);
	}

	@Override
	public String insertPPA600(Ppa600 ppa600) {
		return eScheduleDao.insertPPA600(ppa600);
	}

	@Override
	public String insertPPA800(Ppa800 ppa800) {
		return eScheduleDao.insertPPA800(ppa800);
	}

	@Override
	public String insertPPADATA(PpaData ppadata) {
		return eScheduleDao.insertPPADATA(ppadata);
	}

	@Override
	public String copyAllDataIntoMasterPPA(String userId) {
		return eScheduleDao.copyAllDataIntoMasterPPA(userId);
	}

	@Override
	public boolean checkBranchCodeExistsOrNotPPA(Map<String, String> map) {
		return eScheduleDao.checkBranchCodeExistsOrNotPPA(map);
	}

	@Override
	public String getBranchNameByCodePPA(Map<String, String> map) {
		return eScheduleDao.getBranchNameByCodePPA(map);
	}

	@Override
	public String countTotalRecodeIntoMaster() {
		return eScheduleDao.countTotalRecodeIntoMaster();
	}

	@Override
	public String UpdatePpaDataImport() {
		return eScheduleDao.UpdatePpaDataImport();
	}

	@Override
	public String deleteOldImportData(String userId) {
		return eScheduleDao.deleteOldImportData(userId);
	}

	@Override
	public List<PpaData> getPpaMasterData(String userId) {
		return eScheduleDao.getPpaMasterData(userId);
	}

	@Override
	public boolean checkPPADataExistsOrNot(Map<String, String> map) {
		return eScheduleDao.checkPPADataExistsOrNot(map);
	}

	@Override
	public Map<Integer, List<String>> getMonthYearAsList(int groupId) {
		return eScheduleDao.getMonthYearAsList(groupId);
	}

	@Override
	public List<PpaMasterReport> getPpaMasterDataAsList(Map<String, String> map) {
		return eScheduleDao.getPpaMasterDataAsList(map);
	}

	@Override
	public String insertMonthYears(MonthYears monthYears) {
		
		return eScheduleDao.insertMonthYears(monthYears);
	}

	@Override
	public byte[] generateReportPDF(Map<String, String> map, HttpServletResponse response) {
		
		return eScheduleDao.generateReportPDF(map, response);
	}

	@Override
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response) {
		
		return eScheduleDao.generateReportCSV(map, response);
	}


}
