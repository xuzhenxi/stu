package com.qfedu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.IUserDao;
import com.qfedu.entity.User;
import com.qfedu.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	public void register(User user) {
		
		if (user == null) {
			throw new RuntimeException("信息为空，注册失败");
		}
		
		try {
			userDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public User findByNo(String no) {
		User user = null;
		
		try {
			user = userDao.findByNo(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User findLastStu() {
		User u = null;
		
		try {
			u = userDao.findLastStu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public void update(User user) {
		
		try {
			userDao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
