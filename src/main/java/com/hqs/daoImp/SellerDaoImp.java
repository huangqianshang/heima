package com.hqs.daoImp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.SellerDao;
import com.hqs.domain.Seller;
import com.hqs.util.JDBCUtil;

public class SellerDaoImp implements SellerDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;

	@Override
	public Seller findSellerBySid(int sid) {
		// TODO Auto-generated method stub
		sql = "select * from tab_seller where sid = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid);
	}

}