package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.SystemParameter;

public interface SysParamDAO {
	public List<SystemParameter> searchSysParam(Map<String,String> map);
	public SystemParameter getSystemParameter(int id);
	public SystemParameter getSystemParameter(String param);
	public String crudSysParam(SystemParameter systemParameter,String operationName);
}
