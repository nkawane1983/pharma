package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.UserDAO;
import com.avee.form.AppUser;
import com.avee.form.AppUserGroupBranchMapping;
import com.avee.form.BranchUserAssocs;
import com.avee.form.GroupUserAssocs;
import com.avee.service.UserService;

@Service("userTypeService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

	public com.avee.domain.AppUser findByName(String szName) {
		return dao.findByName(szName);
	}

	public com.avee.domain.AppUser findByEmail(String szEmail) {
		return dao.findByEmail(szEmail);
	}
	
	
	public List<AppUser> searchUser(Map<String,String> map){
		return dao.searchUser(map);
	}
	
	public AppUser getUser(String usrid){
		return dao.getUser(usrid) ;
	}
	
	public String insertUpdateUserData(AppUser appUser,String operation){
		return dao.insertUpdateUserData(appUser, operation);
	}
	
	public void deactiveUser(String userId,String loginUser){
		dao.deactiveUser(userId,loginUser);
	}

	@Override
	public List<BranchUserAssocs> getBranchUserAssocsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.getBranchUserAssocsList(map);
	}

	@Override
	public List<GroupUserAssocs> getGroupUserAssocsList(String userId) {
		// TODO Auto-generated method stub
		return dao.getGroupUserAssocsList(userId);
	}

	@Override
	public void manageGroupUserAssocs(List<GroupUserAssocs> list) {
		// TODO Auto-generated method stub
		dao.manageGroupUserAssocs(list);
		
	}

	@Override
	public void manageBranchUserAssocs(List<BranchUserAssocs> list) {
		// TODO Auto-generated method stub
		dao.manageBranchUserAssocs(list);
	}

	@Override
	public String changeUserPassword(Map<String, String> map) {
		String message="";
		message = dao.changeUserPassword(map);
		return message;
	}

	@Override
	public List<AppUserGroupBranchMapping> getUserDetailsaAsList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return dao.getUserDetailsaAsList(map);
	}

	@Override
	public void updateGroupUserAssocs(AppUser appUser) {
		// TODO Auto-generated method stub
		dao.updateGroupUserAssocs(appUser);
	}

	@Override
	public void updateBranchUserAssocs(AppUser appUser) {
		// TODO Auto-generated method stub
		dao.updateBranchUserAssocs(appUser);
	}
}