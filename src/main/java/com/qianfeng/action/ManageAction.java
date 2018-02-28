package com.qianfeng.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qianfeng.model.User;
import com.qianfeng.service.IUserService;

@Controller
@RequestMapping(value="/Manage")
public class ManageAction {
	@Autowired
	private IUserService uService;
	@RequestMapping(value="/checkUser",produces="plain/text;charset=UTF-8")
	public String  checkUser(HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		User findById = uService.findById(user1);
		session.setAttribute("SessionUser",findById);
		return "checkUser";
	}
	@RequestMapping(value="/compileUser",produces="plain/text;charset=UTF-8")
	public String  compileUser(){
		return "compileUser";
	}
	@RequestMapping(value="/updateUser",produces="plain/text;charset=UTF-8")
	public String  updateUser(User user,HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		Integer id = user1.getId();
		user.setId(id);
		uService.updateUser(user);
		return "redirect:/Manage/checkUser";
	}
	@RequestMapping(value="/manageUser",produces="plain/text;charset=UTF-8")
	public String  manageUser(HttpSession session){
		List<User> allUser = uService.findAll();
		session.setAttribute("allUser",allUser);
		return "showUser";
	}
	@RequestMapping(value="/insertUser",produces="plain/text;charset=UTF-8")
	public String  insertUser(HttpSession session){
		return "insertUser";
	}
	@RequestMapping(value="/saveUser",produces="plain/text;charset=UTF-8")
	public String  saveUser(User user){
		uService.saveUser(user);
		return "redirect:/Manage/manageUser";
	}
}
