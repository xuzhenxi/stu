package com.qfedu.service;

import java.util.Map;

import com.qfedu.entity.Sign;
import com.qfedu.vo.PageBean;

public interface ISignService {

	
	/**
	 * 分页查询
	 * @param page 页码
	 * @param limit 每页数据数
	 * @return 返回 分页数据
	 */
	public PageBean<Sign> findSignByPage(int page, int limit);
	
	//添加签到记录
    public void add(Sign sign);
    
    //通过账号和上午或下午查询签到
    public Sign findByNoAndAmflag(Map<String, Object> map);
}
