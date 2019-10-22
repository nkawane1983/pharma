package com.avee.service;

import java.util.List;
import java.util.Map;

import com.avee.form.CollDelvSingup;
import com.avee.form.ScriptItems;

public interface CollDelvSingupService {

	public String insertScriptItems(CollDelvSingup collDelvSingup);

	public List<CollDelvSingup> getCollDelvSingupasList(Map<String, String> map);
	public boolean checkCollDelvSingupExistOrNot(Map<String, String> map);
	public List<CollDelvSingup> searchCollDelvSingupasList(Map<String, String> map);
	public void deleteCollDelvSingup(CollDelvSingup collDelvSingup);
}
