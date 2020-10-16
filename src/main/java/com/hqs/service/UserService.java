package com.hqs.service;

import com.hqs.domain.User;

public interface UserService {

	boolean regist(User user);

	boolean active(String code);

	User findUser(User user);

}
