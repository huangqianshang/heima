package com.hqs.daoImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.FavoriteDao;
import com.hqs.domain.Favorite_three;
import com.hqs.domain.Route;
import com.hqs.util.JDBCUtil;

public class FavoriteDaoImp implements FavoriteDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;
	
	@Override
	public int findFavoriteByUidAndRid(int uid, int rid) {
		// TODO Auto-generated method stub
		sql = "select count(*) from tab_favorite where uid = ? and rid = ?";
	
		return template.queryForObject(sql, Integer.class,uid,rid);
	}

	@Override
	public void addFavoriteByUidAndRid(int uid, int rid) {
		// TODO Auto-generated method stub
		sql = "insert into tab_favorite values (?,?,?)";
		template.update(sql,rid,new Date(),uid);
	}

	@Override
	public int findAllByRid(int rid) {
		// TODO Auto-generated method stub
		sql = "select count(*) from tab_favorite where rid = ?";
		return template.queryForObject(sql, Integer.class,rid);
	}

	@Override
	public List<Favorite_three> findNeedByUid(int uid, int start, int pageSize) {
		// TODO Auto-generated method stub
		sql = "select * from tab_favorite where uid = ? limit ?,?";
		return template.query(sql,new BeanPropertyRowMapper<Favorite_three>(Favorite_three.class),uid,start,pageSize);
	}

	@Override
	public int findAllByUid(int uid) {
		// TODO Auto-generated method stub
		sql = "select count(*) from tab_favorite where uid = ?";
		return template.queryForObject(sql, Integer.class,uid);
	}

	@Override
	public List<Route> findOrderedFavorite(int start, int pageSize) {
		// TODO Auto-generated method stub
		sql = "SELECT rid,COUNT(*) FROM tab_favorite GROUP BY rid ORDER BY COUNT(*) DESC ";
		StringBuilder builder = new StringBuilder(sql);
		List<Integer> list = new ArrayList<Integer>();
		if(start >= 0 && pageSize > 0) {
			builder.append("LIMIT ?,?");
			list.add(start);
			list.add(pageSize);
		}

		sql = builder.toString();
		return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
	}

	@Override
	public List<Route> findQueryPrice(String name, int lowPrice, int heighPrice, int start, int pageSize) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM tab_route WHERE price >= ? AND price <= ? AND rname LIKE ? ORDER BY price ";
		StringBuilder builder = new StringBuilder(sql);
		List list = new ArrayList();
		list.add(lowPrice);
		list.add(heighPrice);		
		list.add("%"+name+"%");		
		if(start >= 0 && pageSize > 0) {
			builder.append("LIMIT ?,?");
			list.add(start);
			list.add(pageSize);
		}

		sql = builder.toString();
		return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
	}

}
