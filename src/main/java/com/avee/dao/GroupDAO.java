package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.GroupDetails;

public interface GroupDAO {
	public GroupDetails getGroupDetails(int id);

	public String insertGroupDetails(GroupDetails groupDetails);

	public List<GroupDetails> searchGroupDetails(Map<String, String> map);
	public List<AppUserGroupBranchMapping> getGroupDetailsAsList(Map<String, String> map);
}
