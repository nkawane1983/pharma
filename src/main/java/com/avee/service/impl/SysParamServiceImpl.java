package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.SysParamDAO;
import com.avee.form.SystemParameter;
import com.avee.service.SysParamService;

@Service
public class SysParamServiceImpl implements SysParamService{
	
	@Autowired
	private SysParamDAO sysParamDAO;
	
	public List<SystemParameter> searchSysParam(Map<String,String> map){
		return sysParamDAO.searchSysParam(map);
	}
	
	public SystemParameter getSystemParameter(int id){
		return sysParamDAO.getSystemParameter(id);
	}
	
	public String crudSysParam(SystemParameter systemParameter,String operationName){
		return sysParamDAO.crudSysParam(systemParameter, operationName);
	}

	@Override
	public SystemParameter getSystemParameter(String param) {
		// TODO Auto-generated method stub
		return sysParamDAO.getSystemParameter(param);
	}
}
