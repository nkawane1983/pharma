package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.UserTypeDAO;
import com.avee.form.UserType;
import com.avee.form.UserTypeRole;
import com.avee.service.UserTypeService;

@Service("userService")
public class UserTypeServiceImpl implements UserTypeService{
	
	@Autowired
	private UserTypeDAO dao;
	
	public UserType getUserType(int id,boolean getRoles){
		return dao.getUserType(id,getRoles);
	}
	
	public String insertUserType(UserType userType){
		return dao.insertUserType(userType);
	}
	
	public String updateUserType(UserType userType){
		return dao.updateUserType(userType);
	}
	
	public List<UserType> searchUserType(Map<String,String> map){
		return dao.searchUserType(map);
	}
	
	public List<UserTypeRole> getUserTypeRoleList(int userTypeId){
		List<UserTypeRole> userRoleList =  null;
		try {
			userRoleList =  dao.getUserTypeRoleList(userTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRoleList;
	}
	
	public void manageUserTypeRole(List<UserTypeRole> list,int userTypeId){
	try {
			 dao.manageUserTypeRole(list,userTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UserType> getUserTypeList(){
		return dao.getUserTypeList();
	}

	@Override
	public String deleteUserType(int userTypeId) {
		String msg =  null;
		try {
			msg =  dao.deleteUserType(userTypeId);;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
		
	}
	
}
