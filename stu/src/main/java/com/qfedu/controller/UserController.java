package com.qfedu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.User;
import com.qfedu.service.IUserService;
import com.qfedu.util.JsonUtil;
import com.qfedu.util.MD5Utils;
import com.qfedu.vo.JsonBean;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public JsonBean login(String no, String password){
		
		UsernamePasswordToken token = new UsernamePasswordToken(no, MD5Utils.getMd5(password, 1));
		
		Subject subject = SecurityUtils.getSubject();

		try {
			subject.login(token);
			
			return JsonUtil.JsonBeanS(1, "登录成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	//注册
	@RequestMapping("/register.do")
	@ResponseBody
	public JsonBean register(User user, HttpSession session){
		
		try {
			user.setPassword( MD5Utils.getMd5(user.getPassword(), 1));
			userService.register(user);
			
			return JsonUtil.JsonBeanS(1, "注册成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	//登录
	@RequestMapping("/loginOut")
	@ResponseBody
	public JsonBean loginOut( HttpSession session, HttpServletRequest request){


		Subject subject = SecurityUtils.getSubject();

		try {
			subject.logout();

			return JsonUtil.JsonBeanS(1, "退出成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}

	}
	
	//查找用户信息
	@RequestMapping("/findstuinfo.do")
	@ResponseBody
	public JsonBean findInfo(HttpSession session) {
		String no = (String) session.getAttribute("no");
		
		User user = null;
		
		try {
			user = userService.findByNo(no);
			return JsonUtil.JsonBeanS(1, user);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
		
	}
	
	/**
	 * 获得新员工工号用于注册新员
	 * @return 
	 */
	@RequestMapping("/studentno.do")
	@ResponseBody
	public JsonBean findLastStuNo() {
		try {
			User stu = userService.findLastStu();
			if(stu != null) {
				String no = stu.getNo();
				no = no.substring(3);
				no = "1" + no;
			    no = "q" + (Integer.parseInt(no) + 1);
			    no = "stu" + no.substring(2);
			    
				return JsonUtil.JsonBeanS(1, no);
			} else {
				return JsonUtil.JsonBeanS(1, "stu000001");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, "获取账号时出现问题！");
		}
		
	}
	
	//修改
	@RequestMapping("/studentupdate.do")
	@ResponseBody
	public JsonBean update(User user) {
		
		try {
			userService.update(user);
			return JsonUtil.JsonBeanS(1, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}
		
	}
	
	//找回密码
	@RequestMapping("/findpass")
	@ResponseBody
	public JsonBean findPassword( String no, String phone, String email){

		try {
			User user =  userService.findByNo(no);
			if(user == null) {
				
				return JsonUtil.JsonBeanS(0, "用户不存在");
			}
			
			if (user.getPhone().equals(phone)) {
				return JsonUtil.JsonBeanS(1, no);
			} else {
				
				return JsonUtil.JsonBeanS(0, "手机号不正确");
			}

		} catch (AuthenticationException e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}

	}
	
	//修改忘记密码
	@RequestMapping("/updatepass")
	@ResponseBody
	public JsonBean updatepass(User user, String repassword) {

		if (!user.getPassword().equals(repassword)) {
			return JsonUtil.JsonBeanS(0, "两次密码不一样");
		}

		try {
			userService.update(user);
			return JsonUtil.JsonBeanS(1, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.JsonBeanS(0, e.getMessage());
		}

	}

}
