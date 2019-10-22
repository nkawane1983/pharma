package com.avee.dao;

import java.util.List;

import com.avee.form.Category;

public interface CategoryDAO {
	public List<Category> searchCategory(String CategoryGroup);
	public List<Object[]> getCategoryGroup();
}
