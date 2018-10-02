package com.qfedu.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Sign;
import com.qfedu.entity.User;
import com.qfedu.service.ISignService;
import com.qfedu.util.JsonUtil;
import com.qfedu.vo.JsonBean;
import com.qfedu.vo.PageBean;

@Controller
public class SignController {
	
	@Autowired
	private ISignService signService;

	@RequestMapping("/signpage.do")
	@ResponseBody
	public Map<String, Object> findByPage(int page, int limit) {
		PageBean<Sign> pageInfo = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			//查找分页数据
			pageInfo = signService.findSignByPage(page, limit);
			List<Sign> list = pageInfo.getPageInfos();
			
			//设置状态（查找成功）
			map.put("code", 0);
			//设置提示信息
			map.put("msg", "");
			//设置用户总数
			map.put("count", pageInfo.getCount());
			//设置用户信息
			map.put("data", list);
			//返回分页信息
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			//返回非成功状态
			map.put("code", 1);
			return map;
		}
	}
	
	@RequestMapping("/addsign")
	@ResponseBody
	public JsonBean add() {
		String no = (String) SecurityUtils.getSubject().getPrincipal();
		
		
		
		Sign sign = new Sign();
		
		try {
			JsonBean bean = new JsonBean();
			
			Calendar cal = Calendar.getInstance();
			//获得12点时间戳
			cal.set(Calendar.HOUR_OF_DAY, 12);
			long t12 = cal.getTimeInMillis();
			
			
			 //获得8点时间戳
			 cal.set(Calendar.HOUR_OF_DAY, 8);
			 long t8 = cal.getTimeInMillis();
			 
			//获得9点时间戳
			 cal.set(Calendar.HOUR_OF_DAY, 9);
			 long t9 = cal.getTimeInMillis();
			 
			 
			//获得21点时间戳
			 cal.set(Calendar.HOUR_OF_DAY, 21);
			 long t21 = cal.getTimeInMillis();
			 
			//获得22点时间戳
			 cal.set(Calendar.HOUR_OF_DAY, 22);
			 long t22 = cal.getTimeInMillis();
			
			//格式化当天时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String today = sdf.format(new Date());
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("uno", no);
			map.put("todaydate", today);
			Sign s = signService.findByNoAndAmflag(map);
			
			
			
			if (s != null) {
				switch (s.getAmflag()) {
				case 1:
					//设置签到时间
					s.setEnddate(new Date());
					//修改签到状态
					s.setAmflag(2);
					
					long d = s.getEnddate().getTime();
					
					 //大于12点小于21点早退
					 if (d < t21) {
						 s.setLateflag(3);
						 bean.setCode(1);
						 bean.setMsg("早退了");
					 }
					 
					 //大于22点正常下班
					 if (d > t21 && d <t22) {
						 s.setLateflag(4);
						 bean.setCode(1);
						 bean.setMsg("正常下班");
					 }
					 
					//大于22点注意休息
					 if (d > t21 && d <t22) {
						 s.setLateflag(4);
						 bean.setCode(1);
						 bean.setMsg("工作狂人");
					 }
					 s.setAmflag(2);
					 //修改签到信息
					 signService.update(s);
					 
					
					break;
					
				case 2:
					bean.setCode(0);
					bean.setMsg("今天已经签到两次了");
					
					break;
				
				default:
					
					break;
				}
			} else {
				
				
				//设置当前日期
				sign.setTodaydate(sdf.parse(today));
				//设置签到时间
				sign.setSigndate(new Date());
				//设置签到人
				sign.setUno(no);
				
				long d1 = sign.getSigndate().getTime();
				//小于八点提前打卡
				if (d1 < t8) {
					sign.setLateflag(5);
					bean.setCode(1);
					bean.setMsg("提前签到");
				}
				
				//大于9点迟到
				if (t9 < d1 ) {
					sign.setLateflag(2);
					bean.setCode(1);
					bean.setMsg("上班迟到了");
					
				}
				
				//小于9点正常
				if (d1 > t8 && d1< t9) {
					sign.setLateflag(1);
					bean.setCode(1);
					bean.setMsg("正常签到");
				}
				
				sign.setAmflag(1);
				//添加第一次签到
				signService.add(sign);
			}
			
			
			 return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	
}
