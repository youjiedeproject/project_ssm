package com.qianfeng.action;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.model.User;
import com.qianfeng.service.IUserService;
@Controller
@RequestMapping(value="/user")
public class UserAction {
	@Autowired
	private IUserService uService;
	@RequestMapping(value="/tologin")
	public String toLogin(){
		return "login";
	}
	@RequestMapping(value="/login.do",produces="plain/text;charset=UTF-8")
	@ResponseBody
	public String doLogin(User user,HttpSession session){

		User findUser = uService.findByName(user);
		if(findUser!=null){
			if(findUser.getPassword().equals(user.getPassword())){
				session.setAttribute("SessionUser",findUser);
				return "1";
			}else{
				return "0";
			}
		}
		return "0";	
	}
	@RequestMapping(value="/toindex.do")
	public String toIndex(){
		return "index2";
	}
	@RequestMapping(value="/compile")
	public String compileMessage(){
		return "indexCompile";
	}
	@RequestMapping(value="/update.do")
	public String update(User user,HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		Integer id = user1.getId();
		String username = user1.getUsername();
		user.setId(id);
		uService.updateByName(user);
		user.setUsername(username);
		User findUser = uService.findByName(user);
		session.setAttribute("SessionUser",findUser);
		return "index2";
	}
	@RequestMapping(value="/select.do")
	public String select(User user,HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		Integer id = user1.getId();
		user.setId(id);
		User findUser = uService.findById(user);
		session.setAttribute("SessionUser",findUser);
		return "index2";
	}
}
