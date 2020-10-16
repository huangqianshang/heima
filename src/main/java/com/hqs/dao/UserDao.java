package com.hqs.dao;

import com.hqs.domain.User;

public interface UserDao {
	User findByUsername(String username);
	
	void save(User user);
	
	User findByCode(String code);

	void updateStatus(User user);

	User findByUsernameAndPasssword(User user);
}
