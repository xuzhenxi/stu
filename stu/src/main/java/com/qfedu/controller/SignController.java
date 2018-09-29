package com.qfedu.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Sign;
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
	public JsonBean add(HttpSession session) {
		String no = (String) session.getAttribute("no");
		
		Sign sign = new Sign();
		
		try {
			//设置当前日期
			sign.setTodaydate(new Date());
			
			
			//设置签到时间
			sign.setSigndate(new Date());
			
			sign.setUno(no);
			
			long d = sign.getSigndate().getTime();
			Calendar cal = Calendar.getInstance();
			//获得12点时间戳
			cal.set(Calendar.HOUR_OF_DAY, 12);
			long t12 = cal.getTimeInMillis();
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			if (d < t12) {
				map.put("uno", no);
				map.put("amflag", 1);
				Sign s = signService.findByNoAndAmflag(map);
				if (s != null) {
					return JsonUtil.JsonBeanS(0, "上午已经签到过了");
				}
				
			} else {
				map.put("uno", no);
				map.put("amflag", 2);
				Sign s = signService.findByNoAndAmflag(map);
				if (s != null) {
					return JsonUtil.JsonBeanS(0, "下午已经签到过了");
				}
			}
			
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
			 
			 
			 JsonBean bean = new JsonBean();
			 //小于八点提前打卡
			 if (d < t8) {
				 sign.setLateflag(5);
				 sign.setAmflag(1);
				 
				 bean.setCode(1);
				 bean.setMsg("提前签到");
			 }
			 
			 //大于9点小于12点迟到
			 if (t9 < d && d < t12) {
				 sign.setLateflag(2);
				 sign.setAmflag(1);
				 
				 bean.setCode(1);
				 bean.setMsg("上班迟到了");
				 
			 }
			 
			 //小于9点正常
			 if (d < t9) {
				 sign.setLateflag(1);
				 sign.setAmflag(1);
				 
				 bean.setCode(1);
				 bean.setMsg("正常签到");
			 }
			 
			 //大于12点小于21点早退
			 if (d > t12 && d < t21) {
				 sign.setLateflag(3);
				 sign.setAmflag(2);
				 
				 bean.setCode(1);
				 bean.setMsg("早退了");
			 }
			 
			 //大于22点正常下班
			 if (d > t22) {
				 sign.setLateflag(4);
				 sign.setAmflag(2);
				 
				 bean.setCode(1);
				 bean.setMsg("正常下班");
			 }
			 
			 signService.add(sign);
			 
			 return bean;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	
}
