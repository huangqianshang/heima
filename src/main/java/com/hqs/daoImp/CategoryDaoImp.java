package com.hqs.daoImp;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.CategoryDao;
import com.hqs.domain.Category;
import com.hqs.util.JDBCUtil;

public class CategoryDaoImp implements CategoryDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		sql = "select * from tab_category";
		return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
	}

}
