package com.hsecure.util;

import com.google.gson.Gson;
import com.hsecure.model.ActFrmVO;
import com.hsecure.model.UserAddBulkVO;
import com.hsecure.model.UserAddVO;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class CommonUtil
{
  private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

  private String responseText = "";

  HttpClient client = new DefaultHttpClient();
  HttpPost post;
  HttpResponse response;
  org.apache.http.HttpEntity entity;

  public String userAddIam(HashMap<String, Object> map)
    throws Exception
  {
    UserAddVO userAddVO = (UserAddVO)map.get("userAddVO");
    String postUrl = (String)map.get("postUrl");

    String resultCode = "";

    String cMac = "";

    SyncHMAC hmac = new SyncHMAC(logger);
    SyncDate syncDate = new SyncDate(logger);

    long timestamp = SyncDate.getTimeStamp();

    JSONObject mainJson = new JSONObject();
    JSONObject usrJson = new JSONObject();
    JSONObject usrAttrJson = new JSONObject();
    JSONObject orgAttrJson = new JSONObject();

    cMac = hmac.getHMAC(userAddVO.getUsrloginId(),String.valueOf(timestamp) );
    usrJson.put("USRNM", userAddVO.getUsrNm());
    usrJson.put("USRTYPE", "UT01");
    usrJson.put("USRLOGINID", userAddVO.getUsrloginId());
    usrJson.put("USRPWD", userAddVO.getUsrNm());
    usrJson.put("EMAIL", userAddVO.getEmail());
    usrJson.put("MOBILE", userAddVO.getPhone());
    usrJson.put("EMPNO", userAddVO.getUsrloginId());
    usrJson.put("STTUS", "ST01");

    usrAttrJson.put("USERID[PK]", userAddVO.getUsrloginId());
    orgAttrJson.put("DEPTNO", userAddVO.getOrgKey());

    mainJson.put("usr", usrJson);
    mainJson.put("usrAttr", usrAttrJson);
    mainJson.put("orgAttr", orgAttrJson);
    mainJson.put("hmac", cMac);
    mainJson.put("timestamp", Long.valueOf(timestamp));

    logger.debug("postUrl ==>" + postUrl);
    this.post = new HttpPost(postUrl);

    StringEntity postingString = new StringEntity(mainJson.toString(), "UTF-8");

    this.post.setEntity(postingString);
    this.post.setHeader("Content-type", "application/json");

    this.response = this.client.execute(this.post);

    this.entity = this.response.getEntity();

    if (this.entity != null) {
      this.responseText = EntityUtils.toString(this.entity, "UTF-8");
    }
    logger.debug("responseText ==>" + this.responseText);
    JSONObject responseJSON = (JSONObject)JSONSerializer.toJSON(this.responseText);

    resultCode = (String)responseJSON.get("status");

    return resultCode;
  }

  public String userAddBulkIam(HashMap<String, Object> map)
    throws Exception
  {
    UserAddBulkVO userAddBulkVO = (UserAddBulkVO)map.get("userAddBulkVO");
    String postUrl = (String)map.get("postUrl");

    String resultCode = "";

    String cMac = "";

    SyncHMAC hmac = new SyncHMAC(logger);
    SyncDate syncDate = new SyncDate(logger);

    long timestamp = SyncDate.getTimeStamp();

    JSONObject mainJson = new JSONObject();
    JSONObject usrJson = new JSONObject();
    JSONObject usrAttrJson = new JSONObject();
    JSONObject orgAttrJson = new JSONObject();

    cMac = hmac.getHMAC(userAddBulkVO.getUsrLoginId(), String.valueOf(timestamp));
    usrJson.put("USRNM", userAddBulkVO.getUsrNm());
    usrJson.put("USRTYPE", "UT01");
    usrJson.put("USRLOGINID", userAddBulkVO.getUsrLoginId());
    usrJson.put("USRPWD", userAddBulkVO.getUsrLoginId());
    usrJson.put("EMAIL", userAddBulkVO.getUsrLoginId() + "@biz.hf.go.kr");
    usrJson.put("MOBILE", userAddBulkVO.getCellPhone());
    usrJson.put("PHONE", userAddBulkVO.getPhone());
    usrJson.put("EMPNO", userAddBulkVO.getUsrLoginId());
    usrJson.put("STTUS", "ST01");

    usrAttrJson.put("EMP_NO[PK]", userAddBulkVO.getUsrLoginId());
    usrAttrJson.put("INFC_DVCD", "1");
    usrAttrJson.put("INFC_DVCD_NM", "재직");
    usrAttrJson.put("STFF_DVCD", "80");
    usrAttrJson.put("ENCO_DY", userAddBulkVO.getStartDt());
    usrAttrJson.put("RETIR_DY", userAddBulkVO.getEndDt());
    usrAttrJson.put("BROF_CD", map.get("brofCd"));
    usrAttrJson.put("BROF_NM", map.get("brofNm"));
    usrAttrJson.put("HQBR_CD", map.get("hqbrCd"));
    usrAttrJson.put("HQBR_NM", map.get("hqbrNm"));
    usrAttrJson.put("STATUS", "1");
    usrAttrJson.put("USER_TP", userAddBulkVO.getUserTp());
    if ((!StringUtils.isEmpty(userAddBulkVO.getUserTp())) && 
      (userAddBulkVO.getUserTp().equalsIgnoreCase("5"))) {
      usrAttrJson.put("POS_REGU_CD", "950");
      usrAttrJson.put("POS_REGU_CD_NM", "용역직원");
      usrAttrJson.put("JGRD_CD", "950");
      usrAttrJson.put("JGRD_NM", "용역직원");
    } else if ((!StringUtils.isEmpty(userAddBulkVO.getUserTp())) && 
      (userAddBulkVO.getUserTp().equalsIgnoreCase("6"))) {
      usrAttrJson.put("POS_REGU_CD", "960");
      usrAttrJson.put("POS_REGU_CD_NM", "HF파트너스");
      usrAttrJson.put("JGRD_CD", "960");
      usrAttrJson.put("JGRD_NM", "HF파트너스");
    }

    usrAttrJson.put("CO_EMAIL_ADDR", userAddBulkVO.getUsrLoginId());
    usrAttrJson.put("WORK_STCD", "1");
    usrAttrJson.put("WORK_STCD_NM", "재직");

    logger.debug("map : " + map.toString());
    logger.debug("usrAttrJson : " + usrAttrJson.toString());

    orgAttrJson.put("DEPTNO", userAddBulkVO.getDeptNo());

    mainJson.put("usr", usrJson);
    mainJson.put("usrAttr", usrAttrJson);
    mainJson.put("orgAttr", orgAttrJson);
    mainJson.put("hmac", cMac);
    mainJson.put("timestamp", Long.valueOf(timestamp));

    logger.debug("postUrl ==>" + postUrl);
    this.post = new HttpPost(postUrl);

    StringEntity postingString = new StringEntity(mainJson.toString(), "UTF-8");

    this.post.setEntity(postingString);
    this.post.setHeader("Content-type", "application/json");

    this.response = this.client.execute(this.post);

    this.entity = this.response.getEntity();

    if (this.entity != null) {
      this.responseText = EntityUtils.toString(this.entity, "UTF-8");
    }
    logger.debug("responseText ==>" + this.responseText);
    JSONObject responseJSON = (JSONObject)JSONSerializer.toJSON(this.responseText);

    resultCode = (String)responseJSON.get("status");

    return resultCode;
  }

  public String userEditBulkIam(HashMap<String, Object> map)
    throws Exception
  {
    UserAddBulkVO userAddBulkVO = (UserAddBulkVO)map.get("userAddBulkVO");
    String postUrl = (String)map.get("postUrl");

    String resultCode = "";

    String cMac = "";

    SyncHMAC hmac = new SyncHMAC(logger);
    SyncDate syncDate = new SyncDate(logger);

    long timestamp = SyncDate.getTimeStamp();

    JSONObject mainJson = new JSONObject();
    JSONObject usrJson = new JSONObject();
    JSONObject usrAttrJson = new JSONObject();

    cMac = hmac.getHMAC(userAddBulkVO.getUsrLoginId().trim(),""+timestamp);
    usrJson.put("USRLOGINID", userAddBulkVO.getUsrLoginId().trim());
    usrJson.put("STTUS", "ST01");
    usrJson.put("PHONE", userAddBulkVO.getPhone());
    usrJson.put("MOBILE", userAddBulkVO.getCellPhone());

    usrAttrJson.put("EMP_NO[PK]", userAddBulkVO.getUsrLoginId().trim());
    usrAttrJson.put("CO_EMAIL_ADDR", userAddBulkVO.getUsrLoginId().trim());
    usrAttrJson.put("RETIR_DY", userAddBulkVO.getEndDt());

    mainJson.put("usr", usrJson);
    mainJson.put("usrAttr", usrAttrJson);
    mainJson.put("hmac", cMac);
    mainJson.put("timestamp", Long.valueOf(timestamp));

    logger.debug("post Form ==>" + mainJson.toString());
    logger.debug("postUrl ==>" + postUrl);
    this.post = new HttpPost(postUrl);

    StringEntity postingString = new StringEntity(mainJson.toString(), "UTF-8");

    this.post.setEntity(postingString);
    this.post.setHeader("Content-type", "application/json");

    this.response = this.client.execute(this.post);

    this.entity = this.response.getEntity();

    if (this.entity != null) {
      this.responseText = EntityUtils.toString(this.entity, "UTF-8");
    }
    logger.debug("responseText ==>" + this.responseText);
    JSONObject responseJSON = (JSONObject)JSONSerializer.toJSON(this.responseText);

    resultCode = (String)responseJSON.get("status");

    return resultCode;
  }

  public String userRetireBulkIam(HashMap<String, Object> map)
    throws Exception
  {
    UserAddBulkVO userAddBulkVO = (UserAddBulkVO)map.get("userAddBulkVO");
    String postUrl = (String)map.get("postUrl");

    String resultCode = "";

    String cMac = "";

    SyncHMAC hmac = new SyncHMAC(logger);
    SyncDate syncDate = new SyncDate(logger);

    long timestamp = SyncDate.getTimeStamp();

    JSONObject mainJson = new JSONObject();
    JSONObject usrJson = new JSONObject();
    JSONObject usrAttrJson = new JSONObject();

    cMac = hmac.getHMAC(userAddBulkVO.getUsrLoginId().trim(), ""+timestamp);
    usrJson.put("USRNM", "퇴사자");
    usrJson.put("USRTYPE", "UT01");
    usrJson.put("USRLOGINID", userAddBulkVO.getUsrLoginId().trim());
    usrJson.put("EMAIL", "");
    usrJson.put("MOBILE", "");
    usrJson.put("PHONE", "");
    usrJson.put("EMPNO", userAddBulkVO.getUsrLoginId().trim());
    usrJson.put("STTUS", "ST02");

    usrAttrJson.put("EMP_NO[PK]", userAddBulkVO.getUsrLoginId().trim());
    usrAttrJson.put("RETIR_DY", userAddBulkVO.getEndDt().trim());
    usrAttrJson.put("CO_EMAIL_ADDR", "");

    mainJson.put("usr", usrJson);
    mainJson.put("usrAttr", usrAttrJson);
    mainJson.put("hmac", cMac);
    mainJson.put("timestamp", Long.valueOf(timestamp));

    logger.debug("post Form ==>" + mainJson.toString());
    logger.debug("postUrl ==>" + postUrl);
    this.post = new HttpPost(postUrl);

    StringEntity postingString = new StringEntity(mainJson.toString(), "UTF-8");

    this.post.setEntity(postingString);
    this.post.setHeader("Content-type", "application/json");

    this.response = this.client.execute(this.post);

    this.entity = this.response.getEntity();

    if (this.entity != null) {
      this.responseText = EntityUtils.toString(this.entity, "UTF-8");
    }
    logger.debug("responseText ==>" + this.responseText);
    JSONObject responseJSON = (JSONObject)JSONSerializer.toJSON(this.responseText);

    resultCode = (String)responseJSON.get("status");

    return resultCode;
  }

  public void callWorkFlow(HashMap<String, Object> map) throws Exception
  {
    String resultCode = "400";
    String jsonStr = (String)map.get("jsonStr");
    String postUrl = (String)map.get("postUrl");

    String USER_ID = "siimadmin";
    String USER_PW = "!admin!";
    try
    {
      RestTemplate restTemplate = new RestTemplate();

      restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

      String credentialsKeyPw = USER_ID + ":" + USER_PW;
      byte[] credentialsEncoded = Base64.encodeBase64(credentialsKeyPw.getBytes());
      String credentials = new String(credentialsEncoded, "UTF-8");

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Authorization", "Basic" + credentials);
      org.springframework.http.HttpEntity entityIn = new org.springframework.http.HttpEntity(jsonStr, headers);

      ResponseEntity response = restTemplate.exchange(postUrl, HttpMethod.POST, entityIn, String.class, new Object[0]);

      logger.debug("response.getBody ==> " + (String)response.getBody());
      logger.debug("response.getStatusCode().value() ==> " + response.getStatusCode().value());
      logger.debug("response.getStatusCode().toString() ==> " + response.getStatusCode().toString());
    }
    catch (Exception e)
    {
      logger.debug("call work flow error ==>" + e);
    }
  }

  public String getInsertJsonString(Map<String, Object> map)
    throws Exception
  {
    String returnJsonStr = "";

    String usrKey = "";
    String roleKey = "";
    String procDefId = "";

    usrKey = (String)map.get("usrkey");
    roleKey = (String)map.get("roleKey");
    procDefId = (String)map.get("procDefId");

    JSONObject mainJson = new JSONObject();
    JSONObject processJson = new JSONObject();
    JSONObject subJson = new JSONObject();
    JSONObject variableJson = new JSONObject();

    JSONArray subJsonArr = new JSONArray();
    JSONArray processJsonArr = new JSONArray();

    subJson.put("queryType", "INSERT");
    subJson.put("SIIM_PF_KEY", usrKey);
    subJson.put("FDLKEY", "FDL0000003");
    subJson.put("FDLTYPE", "FT01");

    subJsonArr.add(subJson);

    processJson.put("SUBFORM", subJsonArr);
    processJson.put("FDLKEY", "FDL0000005");
    processJson.put("FDLTYPE", "FT01");
    processJson.put("SIIM_PF_KEY", roleKey);

    processJsonArr.add(processJson);

    mainJson.put("PROCESSFORM", processJsonArr);
    mainJson.put("assigneeList", null);
    mainJson.put("processName", "업무역할구성원할당(UA)");
    mainJson.put("isApproval", Boolean.valueOf(false));
    mainJson.put("CREATOR", "USR000000000000");
    mainJson.put("justification", "관리자 업무역할 구성원 할당");
    mainJson.put("requester", "슈퍼관리자");
    mainJson.put("pAssignee", "");
    mainJson.put("opCode", "EU-UA-023");
    mainJson.put("approved", null);
    mainJson.put("isNotification", null);
    mainJson.put("initiator", "USR000000000000");
    mainJson.put("MODIFIER", "USR000000000000");
    mainJson.put("approvalProcess", null);
    mainJson.put("provisionResult", Boolean.valueOf(false));

    returnJsonStr = mainJson.toString();

    return returnJsonStr;
  }

  public String getJsonString(Map<String, Object> map) throws Exception
  {
    String returnJsonStr = "";
    String procDefId = "";

    ActFrmVO actFrmVO = new ActFrmVO();

    List variables = requestAccess(map);
    procDefId = (String)map.get("procDefId");
    actFrmVO.setProcessDefinitionId(procDefId);
    actFrmVO.setVariables(variables);

    Gson gsRequest = new Gson();
    returnJsonStr = gsRequest.toJson(actFrmVO);

    return returnJsonStr;
  }

  private static List<Map> requestAccess(Map<String, Object> inputMap) {
    MultiValueMap map = new LinkedMultiValueMap();
    List processform = new ArrayList();
    List subform = new ArrayList();
    List entit = new ArrayList();
    List variables = new ArrayList();
    Map variable = null;
    Map processformMap = new HashMap();
    Map subformMap = new HashMap();

    String usrKey = "";
    String roleKey = "";

    usrKey = (String)inputMap.get("usrKey");
    roleKey = (String)inputMap.get("roleKey");

    subformMap.put("SIIM_PF_KEY", usrKey);

    subformMap.put("queryType", inputMap.get("method").toString().toUpperCase());
    subformMap.put("FDLKEY", "FDL1000000000001");
    subformMap.put("FDLTYPE", "FT02");
    subform.add(subformMap);

    processformMap.put("SUBFORM", subform);
    processformMap.put("SIIM_PF_KEY", roleKey);
    processformMap.put("FDLKEY", "FDL0000005");
    processformMap.put("FDLTYPE", "FT01");
    processform.add(processformMap);

    variable = new HashMap();
    variable.put("name", "PROCESSFORM");
    variable.put("value", processform);
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "justification");
    variable.put("value", "관리자 업무역할 구성원 할당");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "pAssignee");
    variable.put("value", "");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "isApproval");
    variable.put("value", "false");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "CREATOR");
    variable.put("value", "USR000000000000");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "MODIFIER");
    variable.put("value", "USR000000000000");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "requester");
    variable.put("value", "슈퍼관리자");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "opCode");
    variable.put("value", "EU-UA-023");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "processName");
    variable.put("value", "업무역할구성원할당(UA)");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "approved");
    variable.put("value", null);
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "isNotification");
    variable.put("value", null);
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "initiator");
    variable.put("value", "USR000000000000");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "approvalProcess");
    variable.put("value", "null");
    variables.add(variable);
    variable = new HashMap();
    variable.put("name", "provisionResult");
    variable.put("value", "false");
    variables.add(variable);

    return variables;
  }

  public static String CreatSendData(Map<String, String> map, Map<String, String> apprUserMap) throws Exception {
    String SendData = "{\"header\":{\"uiCommon\":{\"epno\":\"" + 
      (String)map.get("EMPNO") + "\"" + "," + 
      "\"empNm\":" + "\"" + (String)map.get("USRNM") + "\"" + "," + 
      "\"brofCd\":" + "\"" + (String)map.get("BROF_CD") + "\"" + "," + 
      "\"brofTeamCd\":" + "\"" + (String)map.get("BROF_TEAM_CD") + "\"" + 
      "}," + 
      "\"reqRespDvcd\":" + "\"" + "S" + "\"" + "," + 
      "\"sendTaskClcd\":" + "\"" + "ONLE" + "\"" + "," + 
      "\"trdCommon\":{" + 
      "\"intfId\":" + "\"" + "IAMONLE_CORCOAP_EOS_00001" + "\"" + "," + 
      "\"taskTycd\":" + "\"" + "0" + "\"" + 
      "}," + 
      "\"reqChnlId\":" + "\"" + "OLT" + "\"" + "," + 
      "\"sendSysDvcd\":" + "\"" + "IAM" + "\"" + "," + 
      "\"sysEnvDvcd\":" + "\"" + "D" + "\"" + "," + 
      "\"service\":" + "\"" + "hf2_cor.hf2_cor_service.S14100P01S01" + "\"" + "," + 
      "\"tlgmGid\":" + "\"" + (String)map.get("cDate") + "D" + "ESB" + "IAMONLE" + "01" + "\"" + "," + 
      "\"langDvcd\":" + "\"" + "KR" + "\"" + "," + 
      "\"recvSysDvcd\":" + "\"" + "COR" + "\"" + "," + 
      "\"recvTaskClcd\":" + "\"" + "COAP" + "\"" + "," + 
      "\"trdPrcsSeq\":" + "\"" + "01" + "\"" + "," + 
      "\"asyncDvcd\":" + "\"" + "S" + "\"" + "," + 
      "\"serviceMethod\":" + "\"" + "esbInsReqAprl" + "\"" + 
      "}," + 
      "\"dto\":{" + 
      "\"sysDivEngAbbrCd\":" + "\"" + "IM" + "\"" + "," + 
      "\"pttnKncd\":" + "\"" + "IMAC1" + "\"" + "," + 
      "\"pttnKncdNm\":" + "\"" + "통합계정신청" + "\"" + "," + 
      "\"relPttnNo\":" + "\"" + "-" + "\"" + "," + 
      "\"execPttnSeq\":" + "\"" + "0" + "\"" + "," + 
      "\"drerEpno\":" + "\"" + (String)map.get("EMPNO") + "\"" + "," + 
      "\"drerNm\":" + "\"" + (String)map.get("USRNM") + "\"" + "," + 
      "\"drftBrofCd\":" + "\"" + (String)map.get("BROF_CD") + "\"" + "," + 
      "\"drftBrofTeamCd\":" + "\"" + (String)map.get("BROF_TEAM_CD") + "\"" + "," + 
      "\"cnlPttnYn\":" + "\"" + "N" + "\"" + "," + 
      "\"ardcDvcd\":" + "\"" + "01" + "\"" + "," + 
      "\"brHqAprlDvcd\":" + "\"" + "-2" + "\"" + "," + 
      "\"pttnOpnCont\":" + "\"" + (String)map.get("CONTENTS") + "\"" + "," + 
      "\"pttnReqTrdNo\":" + "\"" + "\"" + "," + 
      "\"aprlOpnCont\":" + "\"" + "\"" + "," + 
      "\"reptCnt\":" + "\"" + "0000000002" + "\"" + "," + 
      "\"listCod411p001\":[{" + 
      "\"rowStatus\":" + "\"" + "C" + "\"" + "," + 
      "\"aprlMthdCd\":" + "\"" + "03" + "\"" + "," + 
      "\"aprrDvcd\":" + "\"" + "02" + "\"" + "," + 
      "\"aprrEpno\":" + "\"" + (String)apprUserMap.get("EMP_NO") + "\"" + "," + 
      "\"aprrNm\":" + "\"" + (String)apprUserMap.get("USRNM") + "\"" + "," + 
      "\"posReguCd\":" + "\"" + (String)apprUserMap.get("POS_REGU_CD") + "\"" + "," + 
      "\"jgrdCd\":" + "\"" + (String)apprUserMap.get("JGRD_CD") + "\"" + "," + 
      "\"aprrBrofCd\":" + "\"" + (String)apprUserMap.get("BROF_CD") + "\"" + "," + 
      "\"aprrBrofTeamCd\":" + "\"" + (String)apprUserMap.get("BROF_TEAM_CD") + "\"" + 
      "},{" + 
      "\"rowStatus\":" + "\"" + "C" + "\"" + "," + 
      "\"aprlMthdCd\":" + "\"" + "01" + "\"" + "," + 
      "\"aprrDvcd\":" + "\"" + "01" + "\"" + "," + 
      "\"aprrEpno\":" + "\"" + (String)map.get("EMPNO") + "\"" + "," + 
      "\"aprrNm\":" + "\"" + (String)map.get("USRNM") + "\"" + "," + 
      "\"posReguCd\":" + "\"" + (String)map.get("POS_REGU_CD") + "\"" + "," + 
      "\"jgrdCd\":" + "\"" + (String)map.get("JGRD_CD") + "\"" + "," + 
      "\"aprrBrofCd\":" + "\"" + (String)map.get("BROF_CD") + "\"" + "," + 
      "\"aprrBrofTeamCd\":" + "\"" + (String)map.get("BROF_TEAM_CD") + "\"" + 
      "}]" + 
      "}" + 
      "}";

    System.out.println("=========33333 CreatSendData===========" + SendData);

    return SendData;
  }

  public static String CreatRecvData(Map<String, String> map) throws Exception {
    String rtv = "{\"header\":{\"timestamp\":\"" + 
      (String)map.get("cDate") + "\"" + "," + 
      "}" + 
      "\"dto\":{" + 
      "}";

    System.out.println("=========44444 CreatRecvData===========" + rtv);

    return rtv;
  }
}