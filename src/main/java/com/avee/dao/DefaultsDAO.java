package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.BranchDefaults;
import com.avee.form.DefaultValues;

public interface DefaultsDAO {
	public List<Object[]> getdefaultsValue(String dafaultName,String userID);
	public List<Object[]> getdefaultsGroup(Map<String, String> map);
	public void UpdateUserSetting(DefaultValues defaultValues);
	public int InsertBranchDefault(BranchDefaults branchDefaults);
	public int InsertDefaultValue(DefaultValues defaultValues);
}
