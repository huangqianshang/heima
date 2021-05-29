package com.hqs.daoImp;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hqs.dao.UserDao;
import com.hqs.domain.User;
import com.hqs.util.JDBCUtil;

public class UserDaoImp implements UserDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDs());
	String sql;
	@Override
	public User findByUsername(String username) {
		User user = null;
		// TODO Auto-generated method stub
		try {
		sql = "select * from tab_user where username = ?";
		user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
		
		}catch(Exception e) {}
		
		return user;
	}
	
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		sql = "insert into tab_user (username,password,name,birthday,sex,telephone,email,Status,code) values (?,?,?,?,?,?,?,?,?)";
		template.update(sql,user.getUsername(),
							user.getPassword(),
							user.getName(),
							user.getBirthday(),
							user.getSex(),
							user.getTelephone(),
							user.getEmail(),
							user.getStatus(),
							user.getCode());
	}

	@Override
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		User user = null;
		try {
		sql = "select * from tab_user where code =?";
		user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateStatus(User user) {
		// TODO Auto-generated method stub
		sql = "update tab_user set Status = 'Y' where uid = ? ";
		template.update(sql,user.getUid());
	}

	@Override
	public User findByUsernameAndPasssword(User user) {
		// TODO Auto-generated method stub
		User u = null;
		// TODO Auto-generated method stub
		try {
		sql = "select * from tab_user where username = ? and password = ?";
		u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
		
		}catch(Exception e) {}
		
		return u;
	}

    @Override
    public int updateUserInfo(User user){
        sql = "update tab_user set username = ?,password = ?" +
                " ,name = ?,birthday = ?,sex = ?" +
                " ,telephone = ?,email = ?" +
                " where uid = ?";
        return template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday()
            ,user.getSex(), user.getTelephone(),user.getEmail(),user.getUid());
    }

}
 