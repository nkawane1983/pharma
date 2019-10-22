package com.avee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avee.dao.CategoryDAO;
import com.avee.form.Category;
import com.avee.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO categoryDAO;
	@Override
	public List<Category> searchCategory(String CategoryGroup) {
		List<Category> list = null;
		try{
			list = categoryDAO.searchCategory(CategoryGroup);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Object[]> getCategoryGroup() {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryGroup();
	}

}
