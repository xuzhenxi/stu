package com.qfedu.dao;

import com.qfedu.entity.User;

public interface IUserDao {
   
	//通过账号查找用户
	public User findByNo(String no);
	
	//添加用户
	public void add(User user);
	
	/**
     * 查找最后一个学生
     * @return
     */
    public User findLastStu();
    
    //修改
    public void update(User user);
    
    
}