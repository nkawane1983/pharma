package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.GroupDAO;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.GroupDetails;
import com.avee.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupDAO groupDao;

	@Override
	public GroupDetails getGroupDetails(int id) {
		// TODO Auto-generated method stub
		return groupDao.getGroupDetails(id);
	}

	@Override
	public String insertGroupDetails(GroupDetails groupDetails) {
		// TODO Auto-generated method stub
		return groupDao.insertGroupDetails(groupDetails);
	}

	@Override
	public List<GroupDetails> searchGroupDetails(Map<String, String> map) {
		// TODO Auto-generated method stub
		return groupDao.searchGroupDetails(map);
	}

	@Override
	public List<AppUserGroupBranchMapping> getGroupDetailsAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return groupDao.getGroupDetailsAsList(map);
	}

}
