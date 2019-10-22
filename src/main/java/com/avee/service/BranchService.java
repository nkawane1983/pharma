package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.Branch;
import com.avee.form.BranchDetails;

public interface BranchService {
	public String insertBranchDetails(BranchDetails branchDetails);

	public List<BranchDetails> searchBranchDetails(Map<String, String> map);

	public BranchDetails getBranchDetails(int id);

	public void updateBranch(Branch branch);

	public String updateBranchDetails(BranchDetails branchDetails,String operation);

	public List<BranchDetails> getBranchDetailsList(int id);
	public List<BranchDetails> getBranchDetailsASList(String id,String mode,String userId,String type);

	public void deleteBranch(int id);

	public List<Object[]> getBranchDetailsByDefaultId(String id);

	public List<Object[]> branchDetailslist(Map<String, String> map);
	
	public List<Object[]> branchDetailsAssolist(Map<String, String> map);
	
	public List<AppUserGroupBranchMapping> getBranchDetailsaAsList(Map<String, String> map);
	
	public boolean checkBranchWorkingDayOrNo(Map<String, String> map);
}
