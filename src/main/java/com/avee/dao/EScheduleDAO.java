package com.avee.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.avee.form.MonthYears;
import com.avee.form.Ppa500;
import com.avee.form.Ppa600;
import com.avee.form.Ppa800;
import com.avee.form.PpaData;
import com.avee.form.PpaMaster;
import com.avee.form.PpaMasterReport;

public interface EScheduleDAO {
	public String insertPPA500(Ppa500 ppa500);
	public String insertPPA600(Ppa600 ppa600);
	public String insertPPA800(Ppa800 ppa800);
	public String insertPPADATA(PpaData ppadata);
	public String insertMonthYears(MonthYears monthYears);
	public String copyAllDataIntoMasterPPA(String userId);
	public boolean checkBranchCodeExistsOrNotPPA(Map<String, String> map);
	public String getBranchNameByCodePPA(Map<String, String> map);
	public String countTotalRecodeIntoMaster();
	public String UpdatePpaDataImport();
	public String deleteOldImportData(String userId);
	public List<PpaData> getPpaMasterData(String userId);
	public boolean checkPPADataExistsOrNot(Map<String, String> map);

	public Map<Integer,List<String>> getMonthYearAsList(int groupId);
	public List<PpaMasterReport> getPpaMasterDataAsList(Map<String, String> map);
	public byte[] generateReportPDF(Map<String, String> map, HttpServletResponse response);
	public byte[] generateReportCSV(Map<String, String> map, HttpServletResponse response);
}
