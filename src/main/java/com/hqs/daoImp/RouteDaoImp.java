package com.hqs.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.RouteDao;
import com.hqs.domain.Route;
import com.hqs.util.JDBCUtil;

public class RouteDaoImp implements RouteDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;
	
	@Override
	public int findAll(int cid, String rname) {
		// TODO Auto-generated method stub
		sql = "select count(*) from tab_route where 1 = 1 ";
		StringBuilder builder = new StringBuilder(sql);
		List list = new ArrayList();
		if(cid != 0) {
			builder.append("and cid = ? ");
			list.add(cid);
		}
		if(rname != null && rname.length() > 0) {
			builder.append("and rname like ?");
			list.add("%"+rname+"%");
		}
		sql = builder.toString();
		return template.queryForObject(sql, Integer.class,list.toArray());
	}

	@Override
	public List<Route> findPageQuery(int cid,int start, int numOfPage, String rname) {
		// TODO Auto-generated method stub
//		sql = "select * from tab_route where cid = ? limit ?,?";
		sql = "select * from tab_route where 1 = 1 ";
		StringBuilder builder = new StringBuilder(sql);
		List list = new ArrayList();
		if(cid != 0) {
			builder.append("and cid = ? ");
			list.add(cid);
		}
		if(rname != null && rname.length() > 0) {
			builder.append("and rname like ? ");
			list.add("%"+rname+"%");
		}
		builder.append("limit ?, ? ");
		list.add(start);
		list.add(numOfPage);
		sql = builder.toString();
		return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),list.toArray());
	}

	@Override
	public Route findOne(int rid) {
		// TODO Auto-generated method stub
		sql = "select * from tab_route where rid = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class),rid);
	}

}
