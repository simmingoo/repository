package com.hsecure.model;

public class OrgVO extends BaseMultiSortSearchObject {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2849322479587683171L;
	
	private String orgKey;
	private String pOrgKey;
	private String pOrgNm;
	private String orgNm;
	private String orgType;
	private String pwdPKey;
	private int orderNo;
	private String sttus;
	private String creator;
	private String createDm;
	private String modifier;
	private String modifyDm;
	private String note;
	
	public String getpOrgNm() {
		return pOrgNm;
	}
	public void setpOrgNm(String pOrgNm) {
		this.pOrgNm = pOrgNm;
	}
	public String getOrgKey() {
		return orgKey;
	}
	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}
	public String getpOrgKey() {
		return pOrgKey;
	}
	public void setpOrgKey(String pOrgKey) {
		this.pOrgKey = pOrgKey;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	public String getPwdPKey() {
		return pwdPKey;
	}
	public void setPwdPKey(String pwdPKey) {
		this.pwdPKey = pwdPKey;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getSttus() {
		return sttus;
	}
	public void setSttus(String sttus) {
		this.sttus = sttus;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateDm() {
		return createDm;
	}
	public void setCreateDm(String createDm) {
		this.createDm = createDm;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyDm() {
		return modifyDm;
	}
	public void setModifyDm(String modifyDm) {
		this.modifyDm = modifyDm;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
