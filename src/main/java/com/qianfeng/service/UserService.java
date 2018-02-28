package com.qianfeng.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.qianfeng.mapper.UserMapper;
import com.qianfeng.model.User;
import com.qianfeng.model.UserEmail;
import com.qianfeng.model.UserVacation;
@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserMapper mapper;
	@Override
	public User findByName(User user) {
		return mapper.findByName(user);
	}
	@Override
	public void updateByName(User user) {
		mapper.updateByName(user);
	}
	@Override
	public List<User> findAll() {
		return mapper.findAll();
	}
	@Override
	public void insert(UserEmail email,HttpSession session) {
		User user1 = (User) session.getAttribute("SessionUser");
		String username = user1.getUsername();
		email.setFname(username);
		email.setIsread("未读");
		Date date = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String nowtime = sdf.format(date);
		email.setTime(nowtime);
		mapper.insert(email);	
	}
	@Override
	public User findById(User user) {
		
		return mapper.findById(user);
	}
	@Override
	public List<UserEmail> findEmailById(Integer id) {
		return mapper.findEmailById(id);
	}
	
	@Override
	public UserEmail getEmailById(Integer id) {
		return mapper.getEmailById(id);	
	}
	@Override
	public void UpdateById(UserEmail email) {
		mapper.updateById(email);	
	}
	@Override
	public void insertEmail(UserEmail email) {
		mapper.insertEmail(email);
	}
	@Override
	public void deleteById(Integer id) {
	mapper.deleteById(id);
	}
	@Override
	public List<UserEmail> findAllRemail(Integer id) {
		return mapper.findAllRemail(id);
	}
	@Override
	public void deleteRemailById(Integer id) {
		mapper.deleteRemailById(id);
	}
	@Override
	public void insertRemail(UserEmail remail) {
		mapper.insertRemail(remail);	
	}
	@Override
	public UserEmail findRemailById(Integer id) {
		return mapper.findRemailById(id);	
	}
	@Override
	public List<UserVacation>  findVacation(User user) {
		return mapper.findVacation(user);
	}
	@Override
	public List<UserVacation> findAllVacation(String username) {
		return mapper.findAllVacation(username);
	}
	@Override
	public List<User> findApplicant(Integer admin) {
	return	mapper.findApplicant(admin);	
	}
	@Override
	public void saveApply(UserVacation usva) {
		mapper.saveApply(usva);
	}
	@Override
	public UserVacation findApply(Integer id) {
	
		return mapper.findApply(id);
	}
	@Override
	public void updatestate(UserVacation usva) {
		mapper.updatestate(usva);	
	}
	@Override
	public void updateUser(User user) {
		mapper.updateUser(user);
	}
	@Override
	public void saveUser(User user) {
		mapper.saveUser(user);
	}
	
}
