package com.qfedu.service;

import com.qfedu.entity.User;

public interface IUserService {
	
	/**
	 * 注册新用户
	 * @param user
	 */
	public void register(User user);
	
	//通过账号查找用户
	public User findByNo(String no);
	
	/**
     * 查找最后一个学生
     * @return
     */
    public User findLastStu();
    
    //修改
    public void update(User user);

}
