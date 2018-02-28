package com.qianfeng.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qianfeng.model.User;

import com.qianfeng.model.UserVacation;
import com.qianfeng.service.IUserService;

@Controller
@RequestMapping(value="/Vacation")
public class VacationAction {
	@Autowired
	private IUserService uService;
	@RequestMapping(value="/checkvacation",produces="plain/text;charset=UTF-8")
	public String checkvacation(HttpSession session){
		User user= (User) session.getAttribute("SessionUser");
		Integer admin = user.getIsadmin();
		String username = user.getUsername();
		if(admin==1){
			List<UserVacation> vacation1 = uService.findAllVacation(username);
			session.setAttribute("vacation1",vacation1);
			return "admin";
		}else{
			List<UserVacation> vacation2 = uService.findVacation(user);
			session.setAttribute("vacation2",vacation2);
			}
		
		return "general";
	}
	@RequestMapping(value="/applyvacation.do",produces="plain/text;charset=UTF-8")
	public String applyvacation(HttpSession session){
		Integer admin =1;
		List<User> applicant = uService.findApplicant(admin);
		session.setAttribute("applicant",applicant);
		return "applyvacation";
	}
	@RequestMapping(value="/saveApply",produces="plain/text;charset=UTF-8")
	public String saveApply(UserVacation usva){
		usva.setState("未审核");
		uService.saveApply(usva);
		return "redirect:/Vacation/checkvacation";
	}
	@RequestMapping(value="/audit/{id}",produces="plain/text;charset=UTF-8")
	public String audit(@PathVariable("id") Integer id,HttpSession session){
		UserVacation findApply = uService.findApply(id);
		session.setAttribute("findApply",findApply);
		return "audit";
	}
	@RequestMapping(value="/passAudit",produces="plain/text;charset=UTF-8")
	public String passAudit(Integer id,UserVacation usva){
		usva.setId(id);
		usva.setState("已审核");
		uService.updatestate(usva);
		return "redirect:/Vacation/checkvacation";
	}
	@RequestMapping(value="/unAudit",produces="plain/text;charset=UTF-8")
	public String unAudit( Integer id,UserVacation usva){
		usva.setId(id);
		usva.setState("审核拒绝");
		uService.updatestate(usva);
		return "redirect:/Vacation/checkvacation";
	}
}
