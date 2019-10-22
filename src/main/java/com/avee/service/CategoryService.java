package com.avee.service;

import java.util.List;

import com.avee.form.Category;

public interface CategoryService {
	public List<Category> searchCategory(String CategoryGroup);
	public List<Object[]> getCategoryGroup();
}
