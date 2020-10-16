package com.hqs.daoImp;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.RouteImgDao;
import com.hqs.domain.RouteImg;
import com.hqs.util.JDBCUtil;

public class RouteImgDaoImp implements RouteImgDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;
	
	@Override
	public List<RouteImg> findImg(int rid) {
		// TODO Auto-generated method stub
		sql = "select * from tab_route_img where rid = ?";
		return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
	}

}
