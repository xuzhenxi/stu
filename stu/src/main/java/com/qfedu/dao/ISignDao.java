package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.entity.Sign;

public interface ISignDao {
	  /**
     * 查询学生总数
     * @return 返回学生总数
     */
    public int count();
    
    /**
     * 分页查询学生
     * @param map 用来传递分页数据
     * @return 返回所有学生
     */
    public List<Sign> findByIndexAndSize(Map<String, Object> map);
    
    //添加签到记录
    public void add(Sign sign);
    
    //通过账号和上午或下午查询签到
    public Sign findByNoAndTodaydate(Map<String, Object> map);
    
    //修改签到信息
    public void update(Sign sign);
}