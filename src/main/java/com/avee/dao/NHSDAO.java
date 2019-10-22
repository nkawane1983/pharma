package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.NHS;

public interface NHSDAO {
	public String insertNHSScript(NHS nhs);

	public List<NHS> searchNHSScript(Map<String, String> map);

	public NHS getNHSScript(int id);

	public void updateNHSScript(NHS nhs,String mode);
	public String amendUpdateNHSScript(NHS nhs);

	public void deleteNHSScript(int id);

	public List<Object[]> searchRemainingNHSScript(Map<String, String> map);

	public List<Object[]> getNhsStats(Map<String, String> map);

	public boolean nhsExistOrNot(Map<String, String> map);

	public String getPrivatePrescriptionValueForNHS(Map<String, String> map);

	public NHS getLastNHSScript(Map<String, String> map);
	public int checkRemainingNHS(Map<String, String> map);
}
