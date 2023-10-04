package com.hsecure.user.service;

import com.hsecure.model.OrgVO;
import com.hsecure.model.UserAddBulkVO;
import com.hsecure.model.UserAddVO;
import com.hsecure.model.UsrVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract interface UserRegistService
{
  public abstract List<?> selectLuvList(String paramString);

  public abstract Object selectOrgSearchCount(OrgVO paramOrgVO);

  public abstract List<?> selectOrgSearch(OrgVO paramOrgVO);

  public abstract Object selectUsrSearchCount(UsrVO paramUsrVO);

  public abstract List<?> selectUsrSearch(UsrVO paramUsrVO);

  public abstract int checkUserId(UserAddVO paramUserAddVO);

  public abstract void registUserAdd(UserAddVO paramUserAddVO);

  public abstract UserAddVO findUserAddInfo(String paramString);

  public abstract void createUserAction(UserAddVO paramUserAddVO);

  public abstract Object selectUserAddBulkListCount(UserAddBulkVO paramUserAddBulkVO);

  public abstract List<?> selectUserAddBulkList(UserAddBulkVO paramUserAddBulkVO);

  public abstract void registUserAddBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract void deleteUserAddBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract HashMap<String, String> getImUserInfo(UserAddBulkVO paramUserAddBulkVO);

  public abstract void updateUserAddBulkReqTbl(UserAddBulkVO paramUserAddBulkVO);

  public abstract int getApprovalCnt(UserAddBulkVO paramUserAddBulkVO);

  public abstract int updateApprovalResult(UserAddBulkVO paramUserAddBulkVO);

  public abstract List<?> getApprovalReqList(UserAddBulkVO paramUserAddBulkVO);

  public abstract void createUserBulkAction(List<?> paramList);

  public abstract void editUserBulkAction(List<?> paramList);

  public abstract void retireUserBulkAction(List<?> paramList);

  public abstract Object selectUserEditBulkListCount(UserAddBulkVO paramUserAddBulkVO);

  public abstract List<?> selectUserEditBulkList(UserAddBulkVO paramUserAddBulkVO);

  public abstract Object selectUserRetireBulkListCount(UserAddBulkVO paramUserAddBulkVO);

  public abstract List<?> selectUserRetireBulkList(UserAddBulkVO paramUserAddBulkVO);

  public abstract void registUserEditBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract void registUserRetireBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract void deleteUserEditBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract void deleteUserRetireBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract Object selectApproveHistoryListCount(UserAddBulkVO paramUserAddBulkVO);

  public abstract List<?> selectApproveHistoryList(UserAddBulkVO paramUserAddBulkVO);

  public abstract Map<String, String> selectUserInfo(String paramString);

  public abstract Map<String, String> getApprovalReqInfo(UserAddBulkVO paramUserAddBulkVO);

  public abstract void editUserAction(UserAddBulkVO paramUserAddBulkVO);

  public abstract void retireUserAction(UserAddBulkVO paramUserAddBulkVO);

  public abstract Object getAttrUserCnt(String paramString);

  public abstract Map<String, String> selectUserNotAttrInfo(String paramString);

  public abstract String selectUsrKey(String paramString);

  public abstract int checkUserIdBulk(UserAddBulkVO paramUserAddBulkVO);

  public abstract Object selectApprUsrSearchCount(UsrVO paramUsrVO);

  public abstract List<?> selectApprUsrSearch(UsrVO paramUsrVO);

  public abstract Map<String, String> getApprUserInfo(String paramString);

  public abstract int checkAdminRole(String paramString);
}