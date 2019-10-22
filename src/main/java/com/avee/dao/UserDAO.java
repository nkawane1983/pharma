package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchUserAssocs;
import com.avee.form.GroupUserAssocs;

public interface UserDAO {
	public com.avee.domain.AppUser findByName(String szName);

	public com.avee.domain.AppUser findByEmail(String szEmail);

	public List<AppUser> searchUser(Map<String, String> map);

	public AppUser getUser(String usrid);

	public String insertUpdateUserData(AppUser appUser, String operation);

	public void deactiveUser(String userId, String loginUser);

	public List<com.avee.form.BranchUserAssocs> getBranchUserAssocsList(Map<String, String> map);

	public List<com.avee.form.GroupUserAssocs> getGroupUserAssocsList(String userId);

	public void manageGroupUserAssocs(List<GroupUserAssocs> list);

	public void manageBranchUserAssocs(List<BranchUserAssocs> list);
	
	public void updateGroupUserAssocs(AppUser appUser);
	
	public void updateBranchUserAssocs(AppUser appUser);

	public String changeUserPassword(Map<String, String> map);
	public List<AppUserGroupBranchMapping> getUserDetailsaAsList(Map<String, String> map);
}