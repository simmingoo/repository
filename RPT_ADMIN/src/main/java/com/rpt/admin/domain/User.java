package com.rpt.admin.domain;

public class User extends BaseMultiSortSearchObject{
	
	private static final long serialVersionUID = -2849322479587683171L;
	
	private String userId;
	
	private String userNm;
	
	private String password;
	
	private String lastLoginTime;
	
	private String loginType;
	
	private String userType;
	
	private String stockId;
	
	private String phoneNum;
	
	private String nickNm;
	
	private String encYn;
	
	private String stockNm;
	
	private String possessType;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getNickNm() {
		return nickNm;
	}

	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}

	public String getEncYn() {
		return encYn;
	}

	public void setEncYn(String encYn) {
		this.encYn = encYn;
	}

	public String getStockNm() {
		return stockNm;
	}

	public void setStockNm(String stockNm) {
		this.stockNm = stockNm;
	}

	public String getPossessType() {
		return possessType;
	}

	public void setPossessType(String possessType) {
		this.possessType = possessType;
	}
	
}
