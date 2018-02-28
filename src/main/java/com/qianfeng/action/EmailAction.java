package com.qianfeng.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qianfeng.model.User;
import com.qianfeng.model.UserEmail;
import com.qianfeng.service.IUserService;


@Controller
@RequestMapping(value="/Email")
public class EmailAction {
	@Autowired
	private IUserService uService;
	@RequestMapping(value="writeEmail.do")
	public String findAll(HttpSession session){
		List<User> userList = uService.findAll();
		session.setAttribute("UserList",userList);
		return "writeEmail";
	}
	@RequestMapping(value="/sendEmail",produces="plain/text;charset=UTF-8")
	public String insert(@RequestParam("file")MultipartFile file,Integer sid,String title,String content,HttpSession session) throws Exception {
			UserEmail email = new UserEmail();
			email.setSid(sid);
			email.setTitle(title);
			email.setContent(content);
		if (!file.isEmpty()) {
			file.transferTo(new File("d:/file/" + file.getOriginalFilename()));
			email.setFile("d:/file/" + file.getOriginalFilename());
			String filename=file.getOriginalFilename();
			email.setFilename(filename);
		}
		 uService.insert(email,session);
		 return "writeEmail";
	}
	@RequestMapping(value="/readEmail.do",produces="plain/text;charset=UTF-8")
	public String findById(HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		Integer id = user1.getId();
		List<UserEmail> email = uService.findEmailById(id);
		session.setAttribute("UserEmail",email);
		return "readEmail";
	}
	@RequestMapping(value="/getEmail.do/{id}",produces="plain/text;charset=UTF-8")
	public String getEmailById(@PathVariable("id") Integer id,UserEmail email,HttpSession session){
		email.setIsread("已读");
		email.setId(id);
		uService.UpdateById(email);
		UserEmail email2 = uService.getEmailById(id);
		session.setAttribute("getEmail",email2);
		return "checkEmail";
	}
	@RequestMapping(value="/deleteEmail.do",produces="plain/text;charset=UTF-8")
	public String deleteEmailById(Integer id){
		UserEmail email = uService.getEmailById(id);
		uService.insertEmail(email);
		uService.deleteById(id);
		return "redirect:/Email/readEmail.do";
	}
	@RequestMapping(value="/checkRemail",produces="plain/text;charset=UTF-8")
	public String findAllRemail(HttpSession session){
		User user1 = (User) session.getAttribute("SessionUser");
		Integer id = user1.getId();
		List<UserEmail> remail = uService.findAllRemail(id);
		session.setAttribute("remail",remail);
		return "junkEmail";
	}
	@RequestMapping(value="/deleteRemail",produces="plain/text;charset=UTF-8")
	public String deleteRemailById(Integer id){
		uService.deleteRemailById(id);
		return "redirect:/Email/checkRemail";
	}
	@RequestMapping(value="/restory.do/{id}",produces="plain/text;charset=UTF-8")
	public String restoryById(@PathVariable("id") Integer id){
		UserEmail remail = uService.findRemailById(id);
		uService.insertRemail(remail);
		uService.deleteRemailById(id);
		return "redirect:/Email/checkRemail";
	}
	@RequestMapping(value="/download",produces="plain/text;charset=UTF-8")
	public String  download(HttpServletRequest request,HttpServletResponse response,String filename,String filePath) throws Exception{
		//获得请求过来的请求参数中键是filename的值
		
		
		
				String realPath =filePath;
				//告诉浏览器要下载的文件的类型
				response.setContentType(request.getServletContext().getMimeType(filename));
				//告诉浏览器别解析而是下载文件。
				response.setHeader("Content-Disposition","attachment;filename="+filename);
				FileInputStream fis = new FileInputStream(realPath);
				ServletOutputStream os = response.getOutputStream();
				int b=0;
				while((b = fis.read())!=-1){
					os.write(b);
				}
				//os.close();
				fis.close();
				
		return "forward:getEmail.do";
		
	}
}
