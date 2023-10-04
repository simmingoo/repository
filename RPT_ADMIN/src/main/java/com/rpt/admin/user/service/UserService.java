package com.rpt.admin.user.service;

import java.util.List;
import java.util.Map;

import com.rpt.admin.domain.User;

public interface UserService {
	
	public List<?> selectUserSearchList(User params) throws Exception; 
	
	public Object selectUserSearchListCount(User params)throws Exception;
	
	public User selectUserDetail(User params) throws Exception;
	
	public List<?> selectUserStockList(User params) throws Exception; 
	
	public Object selectUserStockListCount(User params) throws Exception; 
	
	public Object selectUserCheck(User params) throws Exception;
	
	public void userRegist(User params) throws Exception;
	
	public Object selectUserLoginCheck(User params) throws Exception;
	
	public void updateUser(User params) throws Exception;
	
	public List<Map<String, String>> selectUserPossessStockList (User params) throws Exception;
	
	public Map<String, String> selectUserDetailApi(User params) throws Exception;
	
	public void insertUserPossessStock(User params) throws Exception;
	
	public void deleteUserPossessStock(User params) throws Exception;
}
