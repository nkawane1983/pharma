package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.Role;

public interface RoleDAO {
	public List<Role> getRoleList();
	public Role getRole(int id);
	public String insertRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(int id);
	public List<Role> searchRole(Map<String,String> map);
}
