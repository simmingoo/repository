package com.hsecure.model;

/**
 * Im Lookup Category
 */
public class LucVO extends BaseSearchObject {
	/** The Constant serialVersionUID. */
	// FIXME serialVersionUID 재할당
	private static final long serialVersionUID = -2051177725315774894L;
	
	private String lucKey;
	private String pLucKey;
	private String lucNm;
	private String lucType;
	private String sqlString;
	private int orderNo;
	private String sttus;
	private String creator;
	private String createDm;
	private String modifier;
	private String modifyDm;
	private String note;
	private String itRscKey;
	private String defaultYn;

	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getItRscKey() {
		return itRscKey;
	}
	public void setItRscKey(String itRscKey) {
		this.itRscKey = itRscKey;
	}
	public String getLucKey() {
		return lucKey;
	}
	public void setLucKey(String lucKey) {
		this.lucKey = lucKey;
	}
	public String getpLucKey() {
		return pLucKey;
	}
	public void setpLucKey(String pLucKey) {
		this.pLucKey = pLucKey;
	}
	public String getLucNm() {
		return lucNm;
	}
	public void setLucNm(String lucNm) {
		this.lucNm = lucNm;
	}
	public String getLucType() {
		return lucType;
	}
	public void setLucType(String lucType) {
		this.lucType = lucType;
	}
	public String getSqlString() {
		return sqlString;
	}
	public void setSqlString(String sqlString) {
		this.sqlString = sqlString;
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