package com.hsecure.model;

public class UserAddVO
{
  private String usrloginId;
  private String orgKey;
  private String email;
  private String usrNm;
  private String inputNumber1;
  private String inputNumber2;
  private String inputNumber3;
  private String phone;
  private String transactionId;

  public String getUsrloginId()
  {
    return this.usrloginId;
  }

  public void setUsrloginId(String usrloginId) {
    this.usrloginId = usrloginId;
  }

  public String getOrgKey() {
    return this.orgKey;
  }

  public void setOrgKey(String orgKey) {
    this.orgKey = orgKey;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsrNm() {
    return this.usrNm;
  }

  public void setUsrNm(String usrNm) {
    this.usrNm = usrNm;
  }

  public String getInputNumber1() {
    return this.inputNumber1;
  }

  public void setInputNumber1(String inputNumber1) {
    this.inputNumber1 = inputNumber1;
  }

  public String getInputNumber2() {
    return this.inputNumber2;
  }

  public void setInputNumber2(String inputNumber2) {
    this.inputNumber2 = inputNumber2;
  }

  public String getInputNumber3() {
    return this.inputNumber3;
  }

  public void setInputNumber3(String inputNumber3) {
    this.inputNumber3 = inputNumber3;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
}