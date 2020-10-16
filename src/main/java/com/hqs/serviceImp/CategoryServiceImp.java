package com.hqs.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hqs.dao.CategoryDao;
import com.hqs.daoImp.CategoryDaoImp;
import com.hqs.domain.Category;
import com.hqs.service.CategoryService;
import com.hqs.util.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;


public class CategoryServiceImp implements CategoryService {
	private CategoryDao dao = new CategoryDaoImp();
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		//查找jedis中的categories    sortedset
		Jedis jedis = JedisUtil.getJedis();
			//		Set<String> categories = jedis.zrange("categories", 0, -1);
		Set<Tuple> categories = jedis.zrangeWithScores("categories", 0, -1);
		List<Category> list = null;
		
		if(categories == null||categories.size()==0) {
			//如果没有
			list = dao.findAll();
			for (int i = 0; i < list.size(); i++) {
				jedis.zadd("categories", list.get(i).getCid(), list.get(i).getCname());
			}
		}else {
			//如果有
			list = new ArrayList<Category>();
			for (Tuple tuple:categories) {
				Category category = new Category();
				category.setCname(tuple.getElement());
				category.setCid((int)tuple.getScore());
				list.add(category);
			}
		}
		return list;
	}
	
}
