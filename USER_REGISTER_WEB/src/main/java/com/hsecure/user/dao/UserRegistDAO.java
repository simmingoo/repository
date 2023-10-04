package com.hsecure.user.dao;

import com.hsecure.model.LucVO;
import com.hsecure.model.OrgVO;
import com.hsecure.model.UserAddBulkVO;
import com.hsecure.model.UserAddVO;
import com.hsecure.model.UsrVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository("userRegistDAO")
public class UserRegistDAO extends BaseAbstractDao
{
  public List<?> selectLuvList(String strLucKey)
  {
    return list("userRegist.selectLuvList", strLucKey);
  }

  public List<?> selectLuvListBySqlstring(String strSqlstring) {
    return list("userRegist.selectLuvListBySqlstring", strSqlstring);
  }

  public LucVO selectLucDetailInfo(String strLucKey) {
    return (LucVO)selectByPk("userRegist.selectLucDetailInfo", strLucKey);
  }

  public Object selectOrgSearchCount(OrgVO orgVo) {
    return selectByPk("userRegist.selectOrgSearchCount", orgVo);
  }

  public List<?> selectOrgSearch(OrgVO orgVo) {
    return list("userRegist.selectOrgSearch", orgVo);
  }

  public Object selectUsrSearchCount(UsrVO orgVo) {
    return selectByPk("userRegist.selectUsrSearchCount", orgVo);
  }

  public List<?> selectUsrSearch(UsrVO orgVo) {
    return list("userRegist.selectUsrSearch", orgVo);
  }

  public int checkUserId(UserAddVO userAddVO) {
    return ((Integer)selectByPk("userRegist.checkUserId", userAddVO)).intValue();
  }

  public void registUserAdd(UserAddVO userAddVO) {
    insert("userRegist.registUserAdd", userAddVO);
  }

  public UserAddVO findUserAddInfo(String transactionId) {
    return (UserAddVO)selectByPk("userRegist.findUserAddInfo", transactionId);
  }

  public String selectProcessDefinitionId(String oprkey) {
    return (String)selectByPk("userRegist.selectProcessDefinitionId", oprkey);
  }

  public String selectUsrKey(String usrloginId) {
    return (String)selectByPk("userRegist.selectUsrKey", usrloginId);
  }

  public Object selectUserAddBulkListCount(UserAddBulkVO userAddBulkVO) {
    return selectByPk("userRegist.selectUserAddBulkListCount", userAddBulkVO);
  }

  public List<?> selectUserAddBulkList(UserAddBulkVO userAddBulkVO) {
    return list("userRegist.selectUserAddBulkList", userAddBulkVO);
  }

  public void registUserAddBulkInfo(UserAddBulkVO userAddBulkVO) {
    update("userRegist.registUserAddBulkInfo", userAddBulkVO);
  }

  public void registUserAddBulk(UserAddBulkVO userAddBulkVO) {
    update("userRegist.registUserAddBulk", userAddBulkVO);
  }

  public String getNextUserId() {
    return (String)selectByPk("userRegist.getNextUserId", "");
  }

  public void updateNextUserId() {
    update("userRegist.updateNextUserId", "");
  }

  public void deleteUserAddBulk(UserAddBulkVO userAddBulkVO) {
    delete("userRegist.deleteUserAddBulk", userAddBulkVO);
  }

  public HashMap<String, String> getImUserInfo(UserAddBulkVO userAddBulkVO) {
    return (HashMap)selectByPk("userRegist.getImUserInfo", userAddBulkVO);
  }

  public void updateUserAddBulkReqTbl(UserAddBulkVO userAddBulkVO) {
    update("userRegist.updateUserAddBulkReqTbl", userAddBulkVO);
  }

  public int getApprovalCnt(UserAddBulkVO userAddBulkVO) {
    return ((Integer)selectByPk("userRegist.getApprovalCnt", userAddBulkVO)).intValue();
  }

  public int updateApprovalResult(UserAddBulkVO userAddBulkVO) {
    return update("userRegist.updateApprovalResult", userAddBulkVO);
  }

  public List<?> getApprovalReqList(UserAddBulkVO userAddBulkVO) {
    return list("userRegist.getApprovalReqList", userAddBulkVO);
  }

  public Object selectUserEditBulkListCount(UserAddBulkVO userAddBulkVO) {
    return selectByPk("userRegist.selectUserEditBulkListCount", userAddBulkVO);
  }

  public List<?> selectUserEditBulkList(UserAddBulkVO userAddBulkVO) {
    return list("userRegist.selectUserEditBulkList", userAddBulkVO);
  }

  public Object selectUserRetireBulkListCount(UserAddBulkVO userAddBulkVO) {
    return selectByPk("userRegist.selectUserRetireBulkListCount", userAddBulkVO);
  }

  public List<?> selectUserRetireBulkList(UserAddBulkVO userAddBulkVO) {
    return list("userRegist.selectUserRetireBulkList", userAddBulkVO);
  }

  public void registUserEditBulk(UserAddBulkVO userAddBulkVO) {
    update("userRegist.registUserEditBulk", userAddBulkVO);
  }

  public void registUserRetireBulk(UserAddBulkVO userAddBulkVO) {
    update("userRegist.registUserRetireBulk", userAddBulkVO);
  }

  public void deleteUserEditBulk(UserAddBulkVO userAddBulkVO) {
    update("userRegist.deleteUserEditBulk", userAddBulkVO);
  }

  public void deleteUserRetireBulk(UserAddBulkVO userAddBulkVO) {
    update("userRegist.deleteUserRetireBulk", userAddBulkVO);
  }

  public Object selectApproveHistoryListCount(UserAddBulkVO userAddBulkVO) {
    return selectByPk("userRegist.selectApproveHistoryListCount", userAddBulkVO);
  }

  public List<?> selectApproveHistoryList(UserAddBulkVO userAddBulkVO) {
    return list("userRegist.selectApproveHistoryList", userAddBulkVO);
  }

  public Map<String, String> selectUserInfo(String usrKey) {
    return (Map)selectByPk("userRegist.selectUserInfo", usrKey);
  }

  public Map<String, String> getApprovalReqInfo(UserAddBulkVO userAddBulkVO) {
    return (Map)selectByPk("userRegist.getApprovalReqInfo", userAddBulkVO);
  }

  public Object getAttrUserCnt(String usrKey) {
    return selectByPk("userRegist.getAttrUserCnt", usrKey);
  }

  public Map<String, String> selectUserNotAttrInfo(String usrKey) {
    return (Map)selectByPk("userRegist.selectUserNotAttrInfo", usrKey);
  }

  public void updateCreateUsrLoginId(UserAddBulkVO userAddBulkVO) {
    update("userRegist.updateCreateUsrLoginId", userAddBulkVO);
  }

  public int checkUserIdBulk(UserAddBulkVO userAddBulkVO) {
    return ((Integer)selectByPk("userRegist.checkUserIdBulk", userAddBulkVO)).intValue();
  }

  public Map<String, String> getMaxPartnerUserId() {
    return (Map)selectByPk("userRegist.getMaxPartnerUserId", "");
  }

  public Map<String, String> getMaxOutsideUserId() {
    return (Map)selectByPk("userRegist.getMaxOutsideUserId", "");
  }

  public Object selectApprUsrSearchCount(UsrVO orgVo)
  {
    return selectByPk("userRegist.selectApprUsrSearchCount", orgVo);
  }

  public List<?> selectApprUsrSearch(UsrVO orgVo) {
    return list("userRegist.selectApprUsrSearch", orgVo);
  }

  public Map<String, String> getApprUserInfo(String reqId) {
    return (Map)selectByPk("userRegist.getApprUserInfo", reqId);
  }

  public int checkAdminRole(String usrLoginId) {
    return ((Integer)selectByPk("userRegist.checkAdminRole", usrLoginId)).intValue();
  }
}