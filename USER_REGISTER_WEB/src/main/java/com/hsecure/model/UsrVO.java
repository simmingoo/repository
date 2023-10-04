package com.hsecure.model;

public class UsrVO extends BaseMultiSortSearchObject
{
  private static final long serialVersionUID = -2051177725315774897L;
  private String usrKey;
  private String orgKey;
  private String orgNm;
  private String usrNm;
  private String usrPwd;
  private String usrType;
  private String mngrAt;
  private String mngrKey;
  private String mngrNm;
  private String deptNo;
  private String empNo;
  private int orderNo;
  private String sttus;
  private String creator;
  private String createDm;
  private String modifier;
  private String modifyDm;
  private String note;
  private String usrLoginId;
  private String pwdChangeDt;
  private String firstNm;
  private String middleNm;
  private String lastNm;
  private String email;
  private String fax;
  private String phone;
  private String mobile;
  private String postalCd;
  private String address;
  private String ofcPs;
  private String rspOfc;
  private String disabled;
  private String locked;
  private String lastAccessDt;
  private String reqUserId;

  public String getLastAccessDt()
  {
    return this.lastAccessDt;
  }
  public void setLastAccessDt(String lastAccessDt) {
    this.lastAccessDt = lastAccessDt;
  }
  public String getFirstNm() {
    return this.firstNm;
  }
  public String getDisabled() {
    return this.disabled;
  }
  public void setDisabled(String disabled) {
    this.disabled = disabled;
  }
  public String getLocked() {
    return this.locked;
  }
  public void setLocked(String locked) {
    this.locked = locked;
  }
  public void setFirstNm(String firstNm) {
    this.firstNm = firstNm;
  }
  public String getMiddleNm() {
    return this.middleNm;
  }
  public void setMiddleNm(String middleNm) {
    this.middleNm = middleNm;
  }
  public String getLastNm() {
    return this.lastNm;
  }
  public void setLastNm(String lastNm) {
    this.lastNm = lastNm;
  }
  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getFax() {
    return this.fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
  public String getPhone() {
    return this.phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getMobile() {
    return this.mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public String getPostalCd() {
    return this.postalCd;
  }
  public void setPostalCd(String postalCd) {
    this.postalCd = postalCd;
  }
  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getOfcPs() {
    return this.ofcPs;
  }
  public void setOfcPs(String ofcPs) {
    this.ofcPs = ofcPs;
  }
  public String getRspOfc() {
    return this.rspOfc;
  }
  public void setRspOfc(String rspOfc) {
    this.rspOfc = rspOfc;
  }
  public String getPwdChangeDt() {
    return this.pwdChangeDt;
  }
  public void setPwdChangeDt(String pwdChangeDt) {
    this.pwdChangeDt = pwdChangeDt;
  }
  public String getUsrPwd() {
    return this.usrPwd;
  }
  public void setUsrPwd(String usrPwd) {
    this.usrPwd = (usrPwd == null ? null : usrPwd.trim());
  }
  public String getOrgNm() {
    return this.orgNm;
  }
  public void setOrgNm(String orgNm) {
    this.orgNm = orgNm;
  }
  public String getMngrNm() {
    return this.mngrNm;
  }
  public void setMngrNm(String mngrNm) {
    this.mngrNm = mngrNm;
  }
  public String getUsrKey() {
    return this.usrKey;
  }
  public void setUsrKey(String usrKey) {
    this.usrKey = usrKey;
  }
  public String getOrgKey() {
    return this.orgKey;
  }
  public void setOrgKey(String orgKey) {
    this.orgKey = orgKey;
  }
  public String getUsrNm() {
    return this.usrNm;
  }
  public void setUsrNm(String usrNm) {
    this.usrNm = usrNm;
  }
  public String getUsrType() {
    return this.usrType;
  }
  public void setUsrType(String usrType) {
    this.usrType = usrType;
  }
  public String getMngrAt() {
    return this.mngrAt;
  }
  public void setMngrAt(String mngrAt) {
    this.mngrAt = mngrAt;
  }
  public String getMngrKey() {
    return this.mngrKey;
  }
  public void setMngrKey(String mngrKey) {
    this.mngrKey = mngrKey;
  }
  public String getDeptNo() {
    return this.deptNo;
  }
  public void setDeptNo(String deptNo) {
    this.deptNo = deptNo;
  }
  public String getEmpNo() {
    return this.empNo;
  }
  public void setEmpNo(String empNo) {
    this.empNo = empNo;
  }
  public int getOrderNo() {
    return this.orderNo;
  }
  public void setOrderNo(int orderNo) {
    this.orderNo = orderNo;
  }
  public String getSttus() {
    return this.sttus;
  }
  public void setSttus(String sttus) {
    this.sttus = sttus;
  }
  public String getCreator() {
    return this.creator;
  }
  public void setCreator(String creator) {
    this.creator = creator;
  }
  public String getCreateDm() {
    return this.createDm;
  }
  public void setCreateDm(String createDm) {
    this.createDm = createDm;
  }
  public String getModifier() {
    return this.modifier;
  }
  public void setModifier(String modifier) {
    this.modifier = modifier;
  }
  public String getModifyDm() {
    return this.modifyDm;
  }
  public void setModifyDm(String modifyDm) {
    this.modifyDm = modifyDm;
  }
  public String getNote() {
    return this.note;
  }
  public void setNote(String note) {
    this.note = note;
  }
  public String getUsrLoginId() {
    return this.usrLoginId;
  }
  public void setUsrLoginId(String usrLoginId) {
    this.usrLoginId = (usrLoginId == null ? null : usrLoginId.trim());
  }
  public String getReqUserId() {
    return this.reqUserId;
  }
  public void setReqUserId(String reqUserId) {
    this.reqUserId = reqUserId;
  }
}