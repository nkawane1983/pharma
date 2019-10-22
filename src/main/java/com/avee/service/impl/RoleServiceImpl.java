package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.RoleDAO;
import com.avee.form.Role;
import com.avee.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO RoleDAO;

	public void setRoleDAO(RoleDAO RoleDAO) {
		this.RoleDAO = RoleDAO;
	}

	public List<Role> getRoleList(){
		List<Role> list = null;
		try{
			list = RoleDAO.getRoleList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Role getRole(int id){
		Role phyRole =null;
		try{
			phyRole = RoleDAO.getRole(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return phyRole;
		
	}
	
	public String insertRole(Role role){
		String id = "";
		try{
			id = RoleDAO.insertRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
		return id;
	}
	
	public void updateRole(Role role){
		try{
			RoleDAO.updateRole(role);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteRole(int id){
		try{
			RoleDAO.deleteRole(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Role> searchRole(Map<String,String> map){
		List<Role> list = null;
		try{
			list = RoleDAO.searchRole(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}