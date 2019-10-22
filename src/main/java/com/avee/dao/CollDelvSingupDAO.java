package com.avee.dao;

import java.util.List;
import java.util.Map;

import com.avee.form.CollDelvSingup;

public interface CollDelvSingupDAO {
	public String insertScriptItems(CollDelvSingup collDelvSingup);

	public List<CollDelvSingup> getCollDelvSingupasList(Map<String, String> map);
	public boolean checkCollDelvSingupExistOrNot(Map<String, String> map);
	public List<CollDelvSingup> searchCollDelvSingupasList(Map<String, String> map);
	//public String updateCollDelvSingup(CollDelvSingup collDelvSingup);
	public void deleteCollDelvSingup(CollDelvSingup collDelvSingup);
}
