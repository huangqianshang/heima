package com.hqs.serviceImp;

import com.hqs.dao.UserDao;
import com.hqs.daoImp.UserDaoImp;
import com.hqs.domain.User;
import com.hqs.service.UserService;
import com.hqs.util.MailUtils;
import com.hqs.util.UuidUtil;

public class UserServiceImp implements UserService {
	private UserDao dao = new UserDaoImp(); 
	
	/**
	 * 检查注册
	 * @param User user
	 * @return boolean
	 */
	@Override
	public boolean regist(User user) {
		// TODO Auto-generated method stub
		User u = dao.findByUsername(user.getUsername());
		if(u != null) {//用户存在
			return false;
		}
		user.setCode(UuidUtil.getUuid());
		user.setStatus("N");
		dao.save(user);
		
		//发送激活码
		String info = "<a href = 'bs/user/active&code="+user.getCode()+"'>点击激活【旅游网账号】</a>";
		MailUtils.sendMail(user.getEmail(), info,"激活");			
		return true;
	}
	
	/**
	 * 激活用户
	 */
	@Override
	public boolean active(String code) {
		// TODO Auto-generated method stub
		User user = dao.findByCode(code);
		if(user != null) {//用户存在
			//激活
			dao.updateStatus(user);
			return true;
		}else {			
			return false;
		}
	}

	/**
	 * 查找详细用户
	 */
	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPasssword(user);
	}

    @Override
    public int updateUserInfo(User user){
        return dao.updateUserInfo(user);
    }

}
