package com.qfedu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.ISignDao;
import com.qfedu.entity.Sign;
import com.qfedu.service.ISignService;
import com.qfedu.vo.PageBean;

@Service
public class SignService implements ISignService {

	@Autowired
	private ISignDao signDao;
	
	@Override
	public PageBean<Sign> findSignByPage(int page, int limit) {
		Map<String, Object> map = new HashMap<>();
		
		PageBean<Sign> pageInfo = new PageBean<>();

		pageInfo.setPageSize(limit);

		pageInfo.setCurrentPage(page);
		
		// 获取表中的记录总数
		int count = signDao.count();
		pageInfo.setCount(count);
		// 计算总页数
		if(count % pageInfo.getPageSize() == 0){
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		}else{
			pageInfo.setTotalPage(count / pageInfo.getPageSize()+ 1);
		}
		// 根据页码计算分页查询时的开始索引
		int index = (page - 1) * pageInfo.getPageSize();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Sign> list = signDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);

		return pageInfo;	
	}

	@Override
	public void add(Sign sign) {
		
		try {
			signDao.add(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Sign findByNoAndAmflag(Map<String, Object> map) {
		Sign s = null; 
		
		try {
			s = signDao.findByNoAndTodaydate(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}

	//修改签到
	@Override
	public void update(Sign sign) {
		
		try {
			signDao.update(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
