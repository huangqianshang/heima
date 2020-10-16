package com.hqs.dao;

import java.util.List;

import com.hqs.domain.Category;

public interface CategoryDao {
	List<Category> findAll();
}
