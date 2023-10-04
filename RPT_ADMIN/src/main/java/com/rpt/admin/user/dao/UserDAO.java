package com.rpt.admin.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rpt.admin.common.main.BaseAbstractDao;
import com.rpt.admin.domain.User;

@Repository("userDAO")
public class UserDAO extends BaseAbstractDao{
	
	public List<?> selectUserSearchList(User params) throws Exception{
		return list("user.selectUserSearchList", params);
	}
	
	public Object selectUserSearchListCount(User params) throws Exception{
		return selectByPk("user.selectUserSearchListCount", params);
	}
	
	public User selectUserDetail(User params) throws Exception{
		return  (User) selectByPk("user.selectUserDetail", params);
	}
	
	public List<?> selectUserStockList(User params) throws Exception{
		return list("user.selectUserStockList", params);
	}
	
	public Object selectUserStockListCount(User params) throws Exception{
		return selectByPk("user.selectUserStockListCount", params);
	}
	
	public Object selectUserCheck(User params) throws Exception {
		return selectByPk("user.selectUserCheck", params);
	}
	
	public void userRegist(User params) throws Exception {
		insert("user.userRegist", params);
	}
	
	public Object selectUserLoginCheck(User params) throws Exception {
		return selectByPk("user.selectUserLoginCheck", params);
	}
	
	public void updateUser(User params) throws Exception {
		update("user.updateUser", params);
	}
	
	public List<Map<String, String>> selectUserPossessStockList(User params) throws Exception {
		return list("user.selectUserPossessStockList", params);
	}
	
	public Map<String, String> selectUserDetailApi(User params) throws Exception {
		return (Map<String, String>) selectByPk("user.selectUserDetailApi", params);
	}
	
	public void insertUserPossessStock(User params) throws Exception {
		insert("user.insertUserPossessStock", params);
	}
	
	public void deleteUserPossessStock(User params) throws Exception {
		delete("user.deleteUserPossessStock", params);
	}
	
	
}
