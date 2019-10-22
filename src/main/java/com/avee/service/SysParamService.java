package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.SystemParameter;

public interface SysParamService {
	public List<SystemParameter> searchSysParam(Map<String,String> map);
	public SystemParameter getSystemParameter(int id);
	public SystemParameter getSystemParameter(String param);
	public String crudSysParam(SystemParameter systemParameter,String operationName);
}
