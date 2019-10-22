package com.avee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.CollDelvSingupDAO;
import com.avee.form.CollDelvSingup;
import com.avee.service.CollDelvSingupService;

@Service
public class CollDelvSingupServiceImpl implements CollDelvSingupService {
	@Autowired
	CollDelvSingupDAO collDelvSingupdao;

	@Override
	public String insertScriptItems(CollDelvSingup collDelvSingup) {
		// TODO Auto-generated method stub
		return collDelvSingupdao.insertScriptItems(collDelvSingup);
	}

	@Override
	public List<CollDelvSingup> getCollDelvSingupasList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return collDelvSingupdao.getCollDelvSingupasList(map);
	}

	@Override
	public boolean checkCollDelvSingupExistOrNot(Map<String, String> map) {
		// TODO Auto-generated method stub
		return collDelvSingupdao.checkCollDelvSingupExistOrNot(map);
	}

	@Override
	public List<CollDelvSingup> searchCollDelvSingupasList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return collDelvSingupdao.searchCollDelvSingupasList(map);
	}

	@Override
	public void deleteCollDelvSingup(CollDelvSingup collDelvSingup) {
			collDelvSingupdao.deleteCollDelvSingup(collDelvSingup);
	}

}
