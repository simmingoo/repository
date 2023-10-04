package com.hsecure.user.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hsecure.model.LucVO;
import com.hsecure.model.OrgVO;
import com.hsecure.model.UserAddBulkVO;
import com.hsecure.model.UserAddVO;
import com.hsecure.model.UsrVO;
import com.hsecure.user.dao.UserRegistDAO;
import com.hsecure.user.service.UserRegistService;
import com.hsecure.util.CommonUtil;

@Service("userRegistService")
public class UserRegistServiceImpl
  implements UserRegistService
{
  private static final Logger logger = LoggerFactory.getLogger(UserRegistServiceImpl.class);

  @Resource(name="userRegistDAO")
  private UserRegistDAO userRegistDAO;

  @Value("${Globals.restful.rolekey}")
  private String roleKey;

  @Value("${Globals.restful.url}")
  private String restfulUrl;

  @Value("${Globals.restful.usr.create}")
  private String userCreateUrl;

  @Value("${Globals.restful.usr.update}")
  private String userUpdateUrl;

  @Value("${Globals.restful.call.workFlow}")
  private String workFlowUrl;

  public List<?> selectLuvList(String strLucKey) { LucVO tmpLucVO = selectLucDetailInfo(strLucKey);
    List result = null;

    if (tmpLucVO.getLucType().equalsIgnoreCase("LT01"))
      result = this.userRegistDAO.selectLuvList(strLucKey);
    else if (tmpLucVO.getLucType().equalsIgnoreCase("LT02")) {
      result = this.userRegistDAO.selectLuvListBySqlstring(tmpLucVO.getSqlString());
    }

    return result;
  }

  public Object selectOrgSearchCount(OrgVO orgVo)
  {
    return this.userRegistDAO.selectOrgSearchCount(orgVo);
  }

  public List<?> selectOrgSearch(OrgVO orgVo)
  {
    return this.userRegistDAO.selectOrgSearch(orgVo);
  }

  public LucVO selectLucDetailInfo(String strLucKey) {
    return this.userRegistDAO.selectLucDetailInfo(strLucKey);
  }

  public int checkUserId(UserAddVO userAddVO)
  {
    return this.userRegistDAO.checkUserId(userAddVO);
  }

  public void registUserAdd(UserAddVO userAddVO)
  {
    this.userRegistDAO.registUserAdd(userAddVO);
  }

  public UserAddVO findUserAddInfo(String transactionId)
  {
    return this.userRegistDAO.findUserAddInfo(transactionId);
  }
  @Async("threadPoolTaskExecutor")
  public void createUserAction(UserAddVO userAddVO) {
    String userAddPostUrl = this.restfulUrl + this.userCreateUrl;
    String workFlowPostUrl = this.restfulUrl + this.workFlowUrl;
    try {
      String usrKey = this.userRegistDAO.selectUsrKey(userAddVO.getUsrloginId());
      CommonUtil commonUtil = new CommonUtil();
      Map jsonMap = new HashMap();
      if (!StringUtils.isEmpty(usrKey)) {
        String procDefId = this.userRegistDAO.selectProcessDefinitionId("EU-UA-023");
        HashMap workFlowMap = new HashMap();
        jsonMap.put("usrKey", usrKey);
        jsonMap.put("roleKey", this.roleKey);
        jsonMap.put("procDefId", procDefId);
        jsonMap.put("method", "insert");

        String restJsonData = commonUtil.getJsonString(jsonMap);
        workFlowMap.put("postUrl", workFlowPostUrl);
        workFlowMap.put("jsonStr", restJsonData);
        commonUtil.callWorkFlow(workFlowMap);
      } else {
        HashMap userAddMap = new HashMap();
        userAddMap.put("userAddVO", userAddVO);
        userAddMap.put("postUrl", userAddPostUrl);
        commonUtil.userAddIam(userAddMap);
        usrKey = this.userRegistDAO.selectUsrKey(userAddVO.getUsrloginId());
        if (!StringUtils.isEmpty(usrKey)) {
          String procDefId = this.userRegistDAO.selectProcessDefinitionId("EU-UA-023");
          HashMap workFlowMap = new HashMap();
          jsonMap.put("usrKey", usrKey);
          jsonMap.put("roleKey", this.roleKey);
          jsonMap.put("procDefId", procDefId);
          jsonMap.put("method", "INSERT");

          String restJsonData = commonUtil.getJsonString(jsonMap);
          workFlowMap.put("postUrl", workFlowPostUrl);
          workFlowMap.put("jsonStr", restJsonData);
          commonUtil.callWorkFlow(workFlowMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object selectUserAddBulkListCount(UserAddBulkVO userAddBulkVO)
  {
    return this.userRegistDAO.selectUserAddBulkListCount(userAddBulkVO);
  }

  public List<?> selectUserAddBulkList(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectUserAddBulkList(userAddBulkVO);
  }

  public void registUserAddBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.registUserAddBulkInfo(userAddBulkVO);
    this.userRegistDAO.registUserAddBulk(userAddBulkVO);
  }

  public String getNextUserId(String userTp) throws Exception {
    String maxId = "";
    Map maxUserMap = null;
    if (userTp.equals("6"))
      maxUserMap = this.userRegistDAO.getMaxPartnerUserId();
    else {
      maxUserMap = this.userRegistDAO.getMaxOutsideUserId();
    }

    logger.debug("maxUserMap : " + maxUserMap);
    if (maxUserMap != null) {
      logger.debug("maxUserMap : " + maxUserMap.toString());
      maxId = (String)maxUserMap.get("USRLOGINID");
      logger.debug("maxId : " + maxId);
    }
    else if (userTp.equals("6")) {
      maxId = "a0000";
    } else {
      maxId = "k0000";
    }

    String nextId = getNextId(maxId, userTp);
    return nextId;
  }

  public void deleteUserAddBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.deleteUserAddBulk(userAddBulkVO);
  }

  public HashMap<String, String> getImUserInfo(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.getImUserInfo(userAddBulkVO);
  }

  public void updateUserAddBulkReqTbl(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.updateUserAddBulkReqTbl(userAddBulkVO);
  }

  public int getApprovalCnt(UserAddBulkVO userAddBulkVO)
  {
    return this.userRegistDAO.getApprovalCnt(userAddBulkVO);
  }

  public int updateApprovalResult(UserAddBulkVO userAddBulkVO)
  {
    return this.userRegistDAO.updateApprovalResult(userAddBulkVO);
  }

  public List<?> getApprovalReqList(UserAddBulkVO userAddBulkVO)
  {
    return this.userRegistDAO.getApprovalReqList(userAddBulkVO);
  }

  @Async("threadPoolTaskExecutor")
  public void createUserBulkAction(List<?> userReqList) {
    String userAddPostUrl = this.restfulUrl + this.userCreateUrl;
    String workFlowPostUrl = this.restfulUrl + this.workFlowUrl;
    try {
      for (int i = 0; i < userReqList.size(); i++) {
        UserAddBulkVO userAddBulkVO = (UserAddBulkVO)userReqList.get(i);
        String usrLoginId = "";
        if (StringUtils.isEmpty(userAddBulkVO.getUsrLoginId()))
          usrLoginId = getNextUserId(userAddBulkVO.getUserTp());
        else {
          usrLoginId = userAddBulkVO.getUsrLoginId();
        }
        logger.debug("createUserBulkAction get usrLoginId : " + usrLoginId);
        logger.debug("createUserBulkAction get reqSubId : " + userAddBulkVO.getReqSubId());
        logger.debug("createUserBulkAction get reqId : " + userAddBulkVO.getReqId());
        userAddBulkVO.setUsrLoginId(usrLoginId);

        this.userRegistDAO.updateCreateUsrLoginId(userAddBulkVO);

        Map iamUserInfo = this.userRegistDAO.getImUserInfo(userAddBulkVO);

        String usrKey = this.userRegistDAO.selectUsrKey(usrLoginId);
        CommonUtil commonUtil = new CommonUtil();
        Map jsonMap = new HashMap();
        if (StringUtils.isEmpty(usrKey)) {
          HashMap userAddMap = new HashMap();
          userAddMap.put("userAddBulkVO", userAddBulkVO);
          userAddMap.put("postUrl", userAddPostUrl);
          userAddMap.put("brofCd", iamUserInfo.get("BROF_CD"));
          userAddMap.put("brofNm", iamUserInfo.get("BROF_NM"));
          userAddMap.put("hqbrCd", iamUserInfo.get("HQBR_CD"));
          userAddMap.put("hqbrNm", iamUserInfo.get("HQBR_NM"));
          commonUtil.userAddBulkIam(userAddMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async("threadPoolTaskExecutor")
  public void editUserBulkAction(List<?> userReqList) {
    String userEditPostUrl = this.restfulUrl + this.userUpdateUrl;
    try {
      for (int i = 0; i < userReqList.size(); i++) {
        UserAddBulkVO userAddBulkVO = (UserAddBulkVO)userReqList.get(i);
        String usrLoginId = userAddBulkVO.getUsrLoginId();
        String usrKey = this.userRegistDAO.selectUsrKey(usrLoginId);
        logger.debug("editUserBulkAction usrKey : " + usrKey);
        CommonUtil commonUtil = new CommonUtil();
        Map jsonMap = new HashMap();
        if (!StringUtils.isEmpty(usrKey)) {
          HashMap userAddMap = new HashMap();
          userAddMap.put("userAddBulkVO", userAddBulkVO);
          userAddMap.put("postUrl", userEditPostUrl);
          logger.debug("editUserBulkAction userAddMap : " + userAddMap);
          commonUtil.userEditBulkIam(userAddMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async("threadPoolTaskExecutor")
  public void retireUserBulkAction(List<?> userReqList) {
    String userEditPostUrl = this.restfulUrl + this.userUpdateUrl;
    try {
      for (int i = 0; i < userReqList.size(); i++) {
        UserAddBulkVO userAddBulkVO = (UserAddBulkVO)userReqList.get(i);
        String usrLoginId = userAddBulkVO.getUsrLoginId();
        String usrKey = this.userRegistDAO.selectUsrKey(usrLoginId);
        CommonUtil commonUtil = new CommonUtil();
        Map jsonMap = new HashMap();
        if (!StringUtils.isEmpty(usrKey)) {
          HashMap userAddMap = new HashMap();
          userAddMap.put("userAddBulkVO", userAddBulkVO);
          userAddMap.put("postUrl", userEditPostUrl);
          commonUtil.userRetireBulkIam(userAddMap);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object selectUserEditBulkListCount(UserAddBulkVO userAddBulkVO)
  {
    return this.userRegistDAO.selectUserEditBulkListCount(userAddBulkVO);
  }

  public List<?> selectUserEditBulkList(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectUserEditBulkList(userAddBulkVO);
  }

  public Object selectUserRetireBulkListCount(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectUserRetireBulkListCount(userAddBulkVO);
  }

  public List<?> selectUserRetireBulkList(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectUserRetireBulkList(userAddBulkVO);
  }

  public Object selectUsrSearchCount(UsrVO usrVo) {
    return this.userRegistDAO.selectUsrSearchCount(usrVo);
  }

  public List<?> selectUsrSearch(UsrVO usrVo) {
    return this.userRegistDAO.selectUsrSearch(usrVo);
  }

  public void registUserEditBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.registUserAddBulkInfo(userAddBulkVO);
    this.userRegistDAO.registUserEditBulk(userAddBulkVO);
  }

  public void registUserRetireBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.registUserAddBulkInfo(userAddBulkVO);
    this.userRegistDAO.registUserRetireBulk(userAddBulkVO);
  }

  public void deleteUserEditBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.deleteUserEditBulk(userAddBulkVO);
  }

  public void deleteUserRetireBulk(UserAddBulkVO userAddBulkVO) {
    this.userRegistDAO.deleteUserRetireBulk(userAddBulkVO);
  }

  public Object selectApproveHistoryListCount(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectApproveHistoryListCount(userAddBulkVO);
  }

  public List<?> selectApproveHistoryList(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.selectApproveHistoryList(userAddBulkVO);
  }

  public Map<String, String> selectUserInfo(String usrKey)
  {
    return this.userRegistDAO.selectUserInfo(usrKey);
  }

  public Map<String, String> getApprovalReqInfo(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.getApprovalReqInfo(userAddBulkVO);
  }
  @Async("threadPoolTaskExecutor")
  public void editUserAction(UserAddBulkVO userAddBulkVO) {
    String userEditPostUrl = this.restfulUrl + this.userUpdateUrl;
    try {
      String usrLoginId = userAddBulkVO.getUsrLoginId();
      String usrKey = this.userRegistDAO.selectUsrKey(usrLoginId);
      CommonUtil commonUtil = new CommonUtil();
      Map jsonMap = new HashMap();
      if (StringUtils.isEmpty(usrKey)) {
        HashMap userAddMap = new HashMap();
        userAddMap.put("userAddBulkVO", userAddBulkVO);
        userAddMap.put("postUrl", userEditPostUrl);
        commonUtil.userEditBulkIam(userAddMap);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async("threadPoolTaskExecutor")
  public void retireUserAction(UserAddBulkVO userAddBulkVO) {
    String userEditPostUrl = this.restfulUrl + this.userUpdateUrl;
    try {
      String usrLoginId = userAddBulkVO.getUsrLoginId();
      String usrKey = this.userRegistDAO.selectUsrKey(usrLoginId);
      CommonUtil commonUtil = new CommonUtil();
      Map jsonMap = new HashMap();
      if (StringUtils.isEmpty(usrKey)) {
        HashMap userAddMap = new HashMap();
        userAddMap.put("userAddBulkVO", userAddBulkVO);
        userAddMap.put("postUrl", userEditPostUrl);
        commonUtil.userRetireBulkIam(userAddMap);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Object getAttrUserCnt(String usrKey) {
    return this.userRegistDAO.getAttrUserCnt(usrKey);
  }

  public Map<String, String> selectUserNotAttrInfo(String usrKey) {
    return this.userRegistDAO.selectUserNotAttrInfo(usrKey);
  }

  public String selectUsrKey(String usrLoginId) {
    return this.userRegistDAO.selectUsrKey(usrLoginId);
  }

  public int checkUserIdBulk(UserAddBulkVO userAddBulkVO) {
    return this.userRegistDAO.checkUserIdBulk(userAddBulkVO);
  }

  public String getNextId(String userId, String userTp) {
    String nextId = "";
    logger.debug("userId.length() : " + userId.length());
    logger.debug("userId : " + userId);
    System.out.println("userId.length() : " + userId.length());
    System.out.println("userId : " + userId);
    boolean isCreateUser = false;
    boolean isGetUserId = false;
    try {

        if (userId.length() == 5) {
          String userIdPrefix = userId.substring(0, 1);
          char ch = userIdPrefix.charAt(0);
          int aStringNum = ch;
          String userIdCountStr = "";
          int userIdCount = 0;
          if ("6".equals(userTp)) {
            logger.debug("파트너스");
            System.out.println("파트너스");
            userIdCountStr = userId.substring(1, userId.length());
            try {
              userIdCount = Integer.parseInt(userIdCountStr);
              isCreateUser = true;
            } catch (NumberFormatException e) {
              logger.debug("사용자 뒤 값이 숫자가 아님");
              System.out.println("사용자 뒤 값이 숫자가 아님");
            }
            logger.debug("isCreateUser : " + isCreateUser);
            logger.debug("aStringNum : " + aStringNum);
            logger.debug("userIdCount : " + userIdCount);
            System.out.println("isCreateUser : " + isCreateUser);
            System.out.println("aStringNum : " + aStringNum);
            System.out.println("userIdCount : " + userIdCount);
            if ((aStringNum >= 97) && (aStringNum <= 106) && 
              (isCreateUser)) {
              do {
                  String tempUserId = String.valueOf(ch) + String.format("%04d", new Object[] { Integer.valueOf(userIdCount) });
                  System.out.println("tempUserId : " + tempUserId);
                  logger.debug("tempUserId : "+ tempUserId);
                  UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
                  userAddBulkVO.setUsrLoginId(tempUserId);
                  int userCheckCnt = this.userRegistDAO.checkUserIdBulk(userAddBulkVO);
                  if (userCheckCnt == 0) {
                    isGetUserId = true;
                    nextId = tempUserId;
                  }
                  else {
                    userIdCount++;
                    if (userIdCount == 10000) {
                      userIdCount = 0;
                      ch++;
                    }
                  }
              }
              while ((!isGetUserId) && (ch <= 'j'));
            }
          } else if ("5".equals(userTp)) {
            logger.debug("용역직원");
            System.out.println("용역직원");
            userIdCountStr = userId.substring(1, userId.length());
            System.out.println("userIdCountStr : " + userIdCountStr);
            
            try {
              userIdCount = Integer.parseInt(userIdCountStr);
              isCreateUser = true;
            } catch (NumberFormatException e) {
              logger.debug("사용자 뒤 값이 숫자가 아님");
            }
            System.out.println("userIdCount : " + userIdCount);
            logger.debug("isCreateUser : " + isCreateUser);
            logger.debug("aStringNum : " + aStringNum);
            logger.debug("userIdCount : " + userIdCount);
            System.out.println("isCreateUser : " + isCreateUser);
            System.out.println("aStringNum : " + aStringNum);
            System.out.println("userIdCount : " + userIdCount);
            if ((aStringNum >= 107) && (aStringNum <= 122)
            		&& (isCreateUser)) {
              do {
                  String tempUserId = String.valueOf(ch) + String.format("%04d", new Object[] { Integer.valueOf(userIdCount) });
                  logger.debug("tempUserId : " + tempUserId);
                  UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
                  userAddBulkVO.setUsrLoginId(tempUserId);
                  int userCheckCnt = this.userRegistDAO.checkUserIdBulk(userAddBulkVO);
                  if (userCheckCnt == 0) {
                    isGetUserId = true;
                    nextId = tempUserId;
                  }
                  else {
                    userIdCount++;
                    if (userIdCount == 10000) {
                      userIdCount = 0;
                      ch++;
                    }
                  }
              }while ((!isGetUserId) &&  (ch <= 'z'));
            }
          }
        }
    } catch(Exception e) {
    	logger.error("getNextId Error : "+getPrintStackTrace(e));
    }
    return nextId;
  }

  public Object selectApprUsrSearchCount(UsrVO usrVo)
  {
    return this.userRegistDAO.selectApprUsrSearchCount(usrVo);
  }

  public List<?> selectApprUsrSearch(UsrVO usrVo) {
    return this.userRegistDAO.selectApprUsrSearch(usrVo);
  }

  public Map<String, String> getApprUserInfo(String reqId) {
    return this.userRegistDAO.getApprUserInfo(reqId);
  }

  public int checkAdminRole(String usrLoginId) {
    return this.userRegistDAO.checkAdminRole(usrLoginId);
  }
  
  public String getPrintStackTrace(Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    pw.append("+++Start printing trace:\n");
	    e.printStackTrace(pw);
	    pw.append("---Finish printing trace");
	    return sw.toString();
  }
}