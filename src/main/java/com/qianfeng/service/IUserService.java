package com.qianfeng.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Update;

import com.qianfeng.model.User;
import com.qianfeng.model.UserEmail;
import com.qianfeng.model.UserVacation;

public interface IUserService {
	User findByName(User user);

	void updateByName(User user);
	List<User> findAll();
	void insert(UserEmail email,HttpSession session);
	User findById(User user);
	UserEmail getEmailById(Integer id);
	void UpdateById(UserEmail eamil);
	List<UserEmail> findEmailById(Integer id);
	void insertEmail(UserEmail email);
	void deleteById(Integer id);
	List<UserEmail> findAllRemail(Integer id);
	void deleteRemailById(Integer id);
	void insertRemail(UserEmail remail);
	UserEmail findRemailById(Integer id);
	List<UserVacation>  findVacation(User user);
	List<UserVacation> findAllVacation(String username);
	List<User> findApplicant(Integer admin);
	void saveApply(UserVacation usva);
	UserVacation findApply(Integer id);
	void updatestate(UserVacation usva);
	void updateUser(User user);
	void saveUser(User user);
}
