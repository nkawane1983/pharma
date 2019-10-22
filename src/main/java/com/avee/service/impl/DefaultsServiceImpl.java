package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.DefaultsDAO;
import com.avee.form.BranchDefaults;
import com.avee.form.DefaultValues;
import com.avee.service.DefaultsService;

@Service
public class DefaultsServiceImpl implements DefaultsService {
	@Autowired
	DefaultsDAO defaultsDao;

	@Override
	public List<Object[]> getdefaultsValue(String dafaultName,String userID) {
		return defaultsDao.getdefaultsValue(dafaultName,userID);
	}

	@Override
	public List<Object[]> getdefaultsGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		return defaultsDao.getdefaultsGroup(map);
	}

	@Override
	public void UpdateUserSetting(DefaultValues defaultValues) {
		// TODO Auto-generated method stub
		 defaultsDao.UpdateUserSetting(defaultValues);
	}

	@Override
	public int InsertBranchDefault(BranchDefaults branchDefaults) {
		// TODO Auto-generated method stub
		return defaultsDao.InsertBranchDefault(branchDefaults);
	}

	@Override
	public int InsertDefaultValue(DefaultValues defaultValues) {
		// TODO Auto-generated method stub
		return defaultsDao.InsertDefaultValue(defaultValues);
	}

}
