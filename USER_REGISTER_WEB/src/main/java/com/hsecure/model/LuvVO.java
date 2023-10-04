package com.hsecure.model;

/**
 * Im Lookup Value
 */
public class LuvVO extends BaseSearchObject {
	/** The Constant serialVersionUID. */
	// FIXME serialVersionUID 재할당
	private static final long serialVersionUID = -2051177725315774895L;
	
	private String lucKey;
	private String luvKey;
	private String luvNm;
	private String luvEncoded;
	private String luvDecoded;
	private int orderNo;
	private String sttus;
	private String creator;
	private String createDm;
	private String modifier;
	private String modifyDm;
	private String note;
	private String defaultYn;
	
	private String code;
	private String name;

	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getLucKey() {
		return lucKey;
	}
	public void setLucKey(String lucKey) {
		this.lucKey = lucKey;
	}
	public String getLuvKey() {
		return luvKey;
	}
	public void setLuvKey(String luvKey) {
		this.luvKey = luvKey;
	}
	public String getLuvNm() {
		return luvNm;
	}
	public void setLuvNm(String luvNm) {
		this.luvNm = luvNm;
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
	public String getLuvEncoded() {
		return luvEncoded;
	}
	public void setLuvEncoded(String luvEncoded) {
		this.luvEncoded = luvEncoded;
	}
	public String getLuvDecoded() {
		return luvDecoded;
	}
	public void setLuvDecoded(String luvDecoded) {
		this.luvDecoded = luvDecoded;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}