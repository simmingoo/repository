package com.rpt.admin.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpt.admin.domain.User;
import com.rpt.admin.stock.dao.StockDAO;
import com.rpt.admin.user.dao.UserDAO;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="stockDAO")
	private StockDAO stockDAO;

	public List<?> selectUserSearchList(User params) throws Exception {
		return userDAO.selectUserSearchList(params);
	}

	public Object selectUserSearchListCount(User params) throws Exception {
		return userDAO.selectUserSearchListCount(params);
	}
	
	public User selectUserDetail(User params)throws Exception{
		return userDAO.selectUserDetail(params);
	}

	public List<?> selectUserStockList(User params) throws Exception {
		return userDAO.selectUserStockList(params);
	}

	public Object selectUserStockListCount(User params) throws Exception {
		return userDAO.selectUserStockListCount(params);
	}

	public Object selectUserCheck(User params) throws Exception {
		return userDAO.selectUserCheck(params);
	}

	public void userRegist(User params) throws Exception {
		userDAO.userRegist(params);
	}
	
	public Object selectUserLoginCheck(User params) throws Exception {
		return userDAO.selectUserLoginCheck(params);
	}

	public void updateUser(User params) throws Exception {
		userDAO.updateUser(params);
	}

	public List<Map<String, String>> selectUserPossessStockList(User params) throws Exception {
		return userDAO.selectUserPossessStockList(params);
	}

	public Map<String, String> selectUserDetailApi(User params) throws Exception {
		return userDAO.selectUserDetailApi(params);
	}

	public void insertUserPossessStock(User params) throws Exception {
		userDAO.insertUserPossessStock(params);
	}

	public void deleteUserPossessStock(User params) throws Exception {
		userDAO.deleteUserPossessStock(params);
	}

}
