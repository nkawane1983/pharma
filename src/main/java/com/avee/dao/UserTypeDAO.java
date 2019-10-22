package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.UserType;
import com.avee.form.UserTypeRole;

public interface UserTypeDAO {
	
	public UserType getUserType(int id,boolean getRoles);
	public String insertUserType(UserType userType);
	public String updateUserType(UserType userType);
	public List<UserType> searchUserType(Map<String,String> map);
	public List<UserTypeRole> getUserTypeRoleList(int userTypeId);
	public void manageUserTypeRole(List<UserTypeRole> list,int userTypeId);
	public List<UserType> getUserTypeList();
	public String deleteUserType(int userTypeId);
}
