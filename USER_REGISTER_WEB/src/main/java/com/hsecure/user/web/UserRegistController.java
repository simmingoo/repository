package com.hsecure.user.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsecure.model.LuvVO;
import com.hsecure.model.OrgVO;
import com.hsecure.model.UserAddBulkVO;
import com.hsecure.model.UserAddVO;
import com.hsecure.model.UsrVO;
import com.hsecure.user.service.UserRegistService;
import com.hsecure.util.CommonUtil;

import kr.co.direa.server.cruzlink.adapter.api.client.CruzClient;
import kr.co.direa.server.cruzlink.adapter.api.exception.CruzAPIException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class UserRegistController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistController.class);
	
	@Resource(name="userRegistService")
	private UserRegistService userRegistService;
	
	@Resource
	private MappingJackson2JsonView jsonView;
	
	  @Value("${Globals.cruzSendUrl}")
	  private String cruzSendUrl;
	
	  @Value("${Globals.cruzLogDir}")
	  private String cruzLogDir;
	
	@RequestMapping("/userAddView.do")
	public String userAddView(HttpServletRequest request, ModelMap model) throws Exception{
		return "useradd/userAddView";
	}

	/**
     * orgSearch.jsp loading
     * Organization admin page
     */
	@RequestMapping(value="/login/orgSearch", method = RequestMethod.POST)
	public String orgRegisterSearch(ModelMap model, @RequestParam Map<String, String> params) throws Exception {
		String callBackFnc = params.get("callBackFnc");
		String selector = params.get("selector");
		
		List<?> luvDataTypeList = userRegistService.selectLuvList("LUC0000017");
		List<?> luvDataSttusList = userRegistService.selectLuvList("LUC0000006");
		
		JSONArray jsonObjectlist = new JSONArray();
		for(Object tmpVO : luvDataTypeList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
			jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());
			
			jsonObjectlist.add(jsonObject);
		}
		model.addAttribute("luvDataType",jsonObjectlist);
		
		jsonObjectlist = new JSONArray();
		for(Object tmpVO : luvDataSttusList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
			jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());
			
			jsonObjectlist.add(jsonObject);
		}
		model.addAttribute("luvDataSttus",jsonObjectlist);
		model.addAttribute("callBackFnc", callBackFnc);
		model.addAttribute("selector", selector);
		
		return "login/orgSearch";
	}
	
	@RequestMapping(value="/login/usrSearch", method = RequestMethod.POST)
	public String usrRegisterSearch(ModelMap model, @RequestParam Map<String, String> params) throws Exception {
		String callBackFnc = params.get("callBackFnc");
		String selector = params.get("selector");
		
		List<?> luvDataTypeList = userRegistService.selectLuvList("LUC0000002");
		List<?> luvDataSttusList = userRegistService.selectLuvList("LUC0000006");
		
		JSONArray jsonObjectlist = new JSONArray();
		for(Object tmpVO : luvDataTypeList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
			jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());
			
			jsonObjectlist.add(jsonObject);
		}
		model.addAttribute("luvDataType",jsonObjectlist);
		
		jsonObjectlist = new JSONArray();
		for(Object tmpVO : luvDataSttusList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
			jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());
			
			jsonObjectlist.add(jsonObject);
		}
		model.addAttribute("luvDataSttus",jsonObjectlist);
		model.addAttribute("callBackFnc", callBackFnc);
		model.addAttribute("selector", selector);
		
		return "login/usrSearch";
	}
	
	/**
     * select organization list
     */
	@RequestMapping(value="/login/orgSearch/list", method = RequestMethod.POST)
	public @ResponseBody View selectOrgRegisterSearch(@RequestParam Map<String, String> params, ModelMap model) throws Exception {
		int skip = Integer.parseInt(params.get("skip"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		
		OrgVO orgVoParams = new OrgVO();
		orgVoParams.setFirstIndex(skip);
		orgVoParams.setRecordCountPerPage(pageSize);
		
		if(params.containsKey("ORGNM")) {
			orgVoParams.setOrgNm(params.get("ORGNM"));
		}
		if(params.containsKey("PORGNM")) {
			orgVoParams.setpOrgNm(params.get("PORGNM"));
		}
		
		int total = Integer.parseInt((String)userRegistService.selectOrgSearchCount(orgVoParams));
		
		List<?> orgList = userRegistService.selectOrgSearch(orgVoParams);
		logger.info("[{}][selectOrgSearch RESULT CNT =>] " + orgList.size(), this.getClass().getSimpleName());
		
		model.put("list", orgList);
		model.put("totalRecords", total);
		
		return jsonView;
	}
	
	@RequestMapping(value="/login/usrSearch/list", method = RequestMethod.POST)
	public @ResponseBody View selectUsrRegisterSearch(@RequestParam Map<String, String> params, ModelMap model) throws Exception {
		int skip = Integer.parseInt(params.get("skip"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		
		UsrVO usrVoParams = new UsrVO();
		
		usrVoParams.setFirstIndex(skip);
		usrVoParams.setRecordCountPerPage(pageSize);
		
		if(params.containsKey("USRNM")) {
			usrVoParams.setUsrNm(params.get("USRNM"));
		}
		
		if(params.containsKey("USRLOGINID")) {
			usrVoParams.setUsrLoginId(params.get("USRLOGINID"));
		}
		
		if(params.containsKey("REQUSERID")) {
			usrVoParams.setReqUserId(params.get("REQUSERID"));
		}

		int total = Integer.parseInt((String)userRegistService.selectUsrSearchCount(usrVoParams));
		
		List<?> usrList = userRegistService.selectUsrSearch(usrVoParams);
		logger.info("[{}][selectUsrSearch RESULT CNT =>] " + usrList.size(), this.getClass().getSimpleName());
		
		model.put("list", usrList);
		model.put("totalRecords", total);
		
		return jsonView;
	}
	
	@RequestMapping(value={"/checkUserId"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String checkUserId(UserAddVO userAddVO) throws Exception {
	    JSONObject rtv = new JSONObject();
	    logger.info("[{}][checkUserId request userId =>] " + userAddVO.getUsrloginId(), getClass().getSimpleName());
	    int checkCnt = this.userRegistService.checkUserId(userAddVO);
	    logger.info("[{}][checkUserId checkCnt RESULT CNT =>] " + checkCnt, getClass().getSimpleName());
	    if (checkCnt == 0) {
	      rtv.put("isError", Boolean.valueOf(false));
	      rtv.put("message", "계정 생성이 가능 합니다.");
	    } else {
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", "신청 상태이거나 이미 존재하는 계정입니다.");
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/checkUserIdBulk"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String checkUserIdBulk(UserAddBulkVO userAddBulkVO) throws Exception { JSONObject rtv = new JSONObject();
	    logger.info("[{}][checkUserId request userId =>] " + userAddBulkVO.getUsrLoginId(), getClass().getSimpleName());
	    int checkCnt = this.userRegistService.checkUserIdBulk(userAddBulkVO);
	    logger.info("[{}][checkUserId checkCnt RESULT CNT =>] " + checkCnt, getClass().getSimpleName());
	    if (checkCnt == 0) {
	      rtv.put("isError", Boolean.valueOf(false));
	      rtv.put("message", "계정 생성이 가능 합니다.");
	    } else {
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", "신청 상태이거나 이미 존재하는 계정입니다.");
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/requestUserAddAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String requestUserAddAction(UserAddVO userAddVO) throws Exception { JSONObject rtv = new JSONObject();
	    try {
	      if ((!StringUtils.isEmpty(userAddVO.getInputNumber1())) && (!StringUtils.isEmpty(userAddVO.getInputNumber2())) && (!StringUtils.isEmpty(userAddVO.getInputNumber3()))) {
	        userAddVO.setPhone(userAddVO.getInputNumber1() + "-" + userAddVO.getInputNumber2() + "-" + userAddVO.getInputNumber3());
	      }
	      this.userRegistService.registUserAdd(userAddVO);
	      rtv.put("isError", Boolean.valueOf(false));
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][requestUserAddAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); } 
	  @RequestMapping({"/approveUserAddAction/{transactionId}/{approveFlag}"})
	  @ResponseBody
	  public String approveUserAddAction(@PathVariable String transactionId, @PathVariable String approveFlag) { JSONObject rtv = new JSONObject();
	    try
	    {
	      if (approveFlag.equalsIgnoreCase("A")) {
	        UserAddVO userAddVO = this.userRegistService.findUserAddInfo(transactionId);
	        this.userRegistService.createUserAction(userAddVO);
	      }
	      rtv.put("isError", Boolean.valueOf(false));
	    }
	    catch (Exception e) {
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); }

	  @RequestMapping({"/userAddBulkView"})
	  public String userAddBulkView(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> params) throws Exception {
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    String userKey = request.getParameter("usrkey");
	    logger.debug("userKey : " + userKey);
	    logger.debug(params.toString());
	    Map userInfoMap = this.userRegistService.selectUserNotAttrInfo(userKey);
	    /*
	    UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	    userAddBulkVO.setReqUserId((String)userInfoMap.get("USRLOGINID"));
	    Map imUserInfo = this.userRegistService.getImUserInfo(userAddBulkVO);
	    if ((imUserInfo != null) && 
	      (!StringUtils.isEmpty(imUserInfo.get("DCHF_EPNO"))) && 
	      (!((String)imUserInfo.get("DCHF_EPNO")).equals("-")) && 
	      (!((String)imUserInfo.get("DCHF_EPNO")).equals("null"))) {
	      model.addAttribute("apprUserId", imUserInfo.get("DCHF_EPNO"));
	      model.addAttribute("apprUserNm", (String)imUserInfo.get("DCHF_NM") + "(" + (String)imUserInfo.get("ORGNM") + ")");
	    }
	     */
	    String reqId = "REQ_" + format.format(new Date());
	    model.addAttribute("reqId", reqId);
	    model.addAttribute("userKey", userKey);
	    model.addAttribute("usrLoginId", userInfoMap.get("USRLOGINID"));
	    model.addAttribute("usrNm", userInfoMap.get("USRNM"));
	    model.addAttribute("orgNm", userInfoMap.get("ORGNM"));
	    return "useradd/userAddBulkView"; } 
	  @RequestMapping(value={"/userAddBulk/list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public View userAddBulkList(@RequestParam Map<String, String> params, ModelMap model) throws Exception { int skip = Integer.parseInt((String)params.get("skip"));
	    int pageSize = Integer.parseInt((String)params.get("pageSize"));

	    UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	    userAddBulkVO.setFirstIndex(skip);
	    userAddBulkVO.setRecordCountPerPage(pageSize);

	    if (params.containsKey("sort")) {
	      String sortStr = (String)params.get("sort");
	      ObjectMapper mapper = new ObjectMapper();
	      List variables = new ArrayList();
	      variables = (List)mapper.readValue(sortStr, new TypeReference()
	      {
	      });
	      if (variables.size() > 0) {
	        userAddBulkVO.setSort(variables);
	      }
	    }

	    if (params.containsKey("REQID")) {
	      userAddBulkVO.setReqId((String)params.get("REQID"));
	    }

	    int total = Integer.parseInt((String)this.userRegistService.selectUserAddBulkListCount(userAddBulkVO));

	    List userAddBultList = this.userRegistService.selectUserAddBulkList(userAddBulkVO);
	    logger.info("[{}][userAddBultList RESULT CNT =>] " + userAddBultList.size(), getClass().getSimpleName());

	    model.put("list", userAddBultList);
	    model.put("totalRecords", Integer.valueOf(total));

	    return this.jsonView; }

	  @RequestMapping(value={"/userEditBulk/list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public View userEditBulkList(@RequestParam Map<String, String> params, ModelMap model) throws Exception {
	    int skip = Integer.parseInt((String)params.get("skip"));
	    int pageSize = Integer.parseInt((String)params.get("pageSize"));

	    UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	    userAddBulkVO.setFirstIndex(skip);
	    userAddBulkVO.setRecordCountPerPage(pageSize);

	    if (params.containsKey("sort")) {
	      String sortStr = (String)params.get("sort");
	      ObjectMapper mapper = new ObjectMapper();
	      List variables = new ArrayList();
	      variables = (List)mapper.readValue(sortStr, new TypeReference()
	      {
	      });
	      if (variables.size() > 0) {
	        userAddBulkVO.setSort(variables);
	      }
	    }

	    if (params.containsKey("REQID")) {
	      userAddBulkVO.setReqId((String)params.get("REQID"));
	    }

	    int total = Integer.parseInt((String)this.userRegistService.selectUserEditBulkListCount(userAddBulkVO));

	    List userAddBultList = this.userRegistService.selectUserEditBulkList(userAddBulkVO);
	    logger.info("[{}][userAddBultList RESULT CNT =>] " + userAddBultList.size(), getClass().getSimpleName());

	    model.put("list", userAddBultList);
	    model.put("totalRecords", Integer.valueOf(total));

	    return this.jsonView;
	  }
	  @RequestMapping(value={"/userRetireBulk/list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public View userRetireBulkList(@RequestParam Map<String, String> params, ModelMap model) throws Exception { int skip = Integer.parseInt((String)params.get("skip"));
	    int pageSize = Integer.parseInt((String)params.get("pageSize"));

	    UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	    userAddBulkVO.setFirstIndex(skip);
	    userAddBulkVO.setRecordCountPerPage(pageSize);

	    if (params.containsKey("sort")) {
	      String sortStr = (String)params.get("sort");
	      ObjectMapper mapper = new ObjectMapper();
	      List variables = new ArrayList();
	      variables = (List)mapper.readValue(sortStr, new TypeReference()
	      {
	      });
	      if (variables.size() > 0) {
	        userAddBulkVO.setSort(variables);
	      }
	    }

	    if (params.containsKey("REQID")) {
	      userAddBulkVO.setReqId((String)params.get("REQID"));
	    }

	    int total = Integer.parseInt((String)this.userRegistService.selectUserRetireBulkListCount(userAddBulkVO));

	    List userAddBultList = this.userRegistService.selectUserRetireBulkList(userAddBulkVO);
	    logger.info("[{}][userAddBultList RESULT CNT =>] " + userAddBultList.size(), getClass().getSimpleName());

	    model.put("list", userAddBultList);
	    model.put("totalRecords", Integer.valueOf(total));

	    return this.jsonView; } 
	  @RequestMapping(value={"/userAddBulkAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String userAddBulkAction(UserAddBulkVO userAddBulkVO) throws Exception {
	    JSONObject rtv = new JSONObject();
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    String reqSubId = "REQ_SUB_" + format.format(new Date());
	    userAddBulkVO.setReqSubId(reqSubId);
	    try
	    {
	      rtv.put("isError", Boolean.valueOf(false));
	      if (userAddBulkVO.getReqType().equals("A"))
	        this.userRegistService.registUserAddBulk(userAddBulkVO);
	      else if (userAddBulkVO.getReqType().equals("E")) {
	        if (!StringUtils.isEmpty(this.userRegistService.selectUsrKey(userAddBulkVO.getEditUsrLoginId()))) {
	          this.userRegistService.registUserEditBulk(userAddBulkVO);
	        } else {
	          rtv.put("isError", Boolean.valueOf(true));
	          rtv.put("message", "존재하지 않는 계정입니다.");
	        }
	      } else if (userAddBulkVO.getReqType().equals("R"))
	        if (!StringUtils.isEmpty(this.userRegistService.selectUsrKey(userAddBulkVO.getRetireUsrLoginId()))) {
	          this.userRegistService.registUserRetireBulk(userAddBulkVO);
	        } else {
	          rtv.put("isError", Boolean.valueOf(true));
	          rtv.put("message", "존재하지 않는 계정입니다.");
	        }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      logger.error("[{}][userAddBulkAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/userAddBulkDeleteAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String userAddBulkDeleteAction(UserAddBulkVO userAddBulkVO) throws Exception { JSONObject rtv = new JSONObject();
	    try {
	      if (userAddBulkVO.getReqType().equals("A"))
	        this.userRegistService.deleteUserAddBulk(userAddBulkVO);
	      else if (userAddBulkVO.getReqType().equals("E"))
	        this.userRegistService.deleteUserEditBulk(userAddBulkVO);
	      else if (userAddBulkVO.getReqType().equals("R")) {
	        this.userRegistService.deleteUserRetireBulk(userAddBulkVO);
	      }
	      rtv.put("isError", Boolean.valueOf(false));
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][userAddBulkDeleteAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/approveUserBulkAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String approveUserBulkAction(UserAddBulkVO userAddBulkVO) throws Exception { JSONObject rtv = new JSONObject();
	    try
	    {
	      byte[] responseData = null;
	      String respData = "";
	      int iRtn = 0;
	      DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	      Calendar calendar = Calendar.getInstance();
	      String cDate = dateFormat.format(calendar.getTime());
	      HashMap userMap = this.userRegistService.getImUserInfo(userAddBulkVO);
	      Map apprUserMap = this.userRegistService.getApprUserInfo(userAddBulkVO.getReqId());
	      userMap.put("CONTENTS", userAddBulkVO.getReqContents());
	      userMap.put("cDate", cDate);
	      String sendData = CommonUtil.CreatSendData(userMap, apprUserMap);
	      CruzClient cruzClient = new CruzClient(this.cruzSendUrl);
	      cruzClient.setStLogDir(this.cruzLogDir);
	      cruzClient.setStLogLvl("DEBUG");
	      cruzClient.setTimeout(30000);
	      responseData = cruzClient.sendHttp(sendData.getBytes("UTF-8"));
	      respData = new String(responseData, "UTF-8");

	      logger.debug("respData : " + respData);
	      JSONObject jsonParser = new JSONObject();
	      if (respData != null)
	      {
	        JSONObject jsonObject = JSONObject.fromObject(respData);
	        logger.debug("=======respData==========" + jsonObject.toString());
	        JSONObject jsonDto = (JSONObject)jsonObject.get("dto");

	        String APRL_MNG_NO = jsonDto.get("aprlMngNo").toString();
	        logger.debug("=====APRL_MNG_NO====" + APRL_MNG_NO);

	        if (!StringUtils.isEmpty(APRL_MNG_NO)) {
	          userAddBulkVO.setApprNo(APRL_MNG_NO);
	          userAddBulkVO.setAppprStat("03");
	          this.userRegistService.updateUserAddBulkReqTbl(userAddBulkVO);
	        } else {
	          rtv.put("isError", Boolean.valueOf(true));
	          rtv.put("message", "결재요청이 실패하였습니다.");
	        }

	      }
	      else
	      {
	        rtv.put("isError", Boolean.valueOf(true));
	        rtv.put("message", "결재요청이 실패하였습니다.");
	      }
	      rtv.put("isError", Boolean.valueOf(false));
	    } catch (CruzAPIException e) {
	      logger.error("Client 오류 코드\t: [" + e.getErrCode() + "]");
	      logger.error("Client 오류 메세지\t: [" + e.getErrMsg() + "]");
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", "Error Code : " + e.getErrCode() + " / Error Message : " + e.getErrMsg());
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][approveUserBulkAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/approveAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String approveAction(@RequestBody JSONObject param) { JSONObject rtvJson = new JSONObject();
	    logger.debug("approveAction param : " + param.toString());
	    try
	    {
	      JSONObject headerJson = (JSONObject)param.get("header");
	      logger.debug("approveAction headerJson : " + param.toString());

	      JSONObject dtoJson = (JSONObject)param.get("dto");
	      logger.debug("approveAction dtoJson : " + dtoJson.toString());

	      JSONObject trdJson = (JSONObject)headerJson.get("trdCommon");
	      logger.debug("approveAction trdJson : " + dtoJson.toString());

	      JSONObject rtvDtoJson = new JSONObject();

	      String aprlMngNo = dtoJson.get("aprlMngNo").toString();
	      String aprlStcd = dtoJson.get("aprlStcd").toString();
	      String apRlCmplDy = dtoJson.get("aprlCmplDy").toString();
	      logger.debug("approveAction aprlMngNo : " + aprlMngNo);
	      logger.debug("approveAction aprlStcd : " + aprlStcd);
	      logger.debug("approveAction apRlCmplDy : " + apRlCmplDy);

	      UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	      userAddBulkVO.setApprDt(apRlCmplDy);
	      userAddBulkVO.setApprNo(aprlMngNo);
	      userAddBulkVO.setAppprStat(aprlStcd);
	      try
	      {
	        if (this.userRegistService.getApprovalCnt(userAddBulkVO) > 0)
	        {
	          if (this.userRegistService.updateApprovalResult(userAddBulkVO) > 0)
	          {
	            if (aprlStcd.equals("06")) {
	              Map approveReqInfo = this.userRegistService.getApprovalReqInfo(userAddBulkVO);
	              logger.debug("approveReqInfo : " + approveReqInfo.toString());
	              userAddBulkVO.setReqType((String)approveReqInfo.get("APPTYPE"));
	              List userReqList = this.userRegistService.getApprovalReqList(userAddBulkVO);

	              if (((String)approveReqInfo.get("APPTYPE")).equals("A"))
	                this.userRegistService.createUserBulkAction(userReqList);
	              else if (((String)approveReqInfo.get("APPTYPE")).equals("E"))
	                this.userRegistService.editUserBulkAction(userReqList);
	              else if (((String)approveReqInfo.get("APPTYPE")).equals("R")) {
	                this.userRegistService.retireUserBulkAction(userReqList);
	              }

	            }

	            trdJson.put("trdPrcsRsltCd", "0");
	          }
	          else {
	            logger.info("aprlMngNo : " + aprlMngNo + " is not found data");
	            trdJson.put("trdPrcsRsltCd", "1");
	            aprlMngNo = "";
	          }

	        }
	        else
	        {
	          logger.info("aprlMngNo : " + aprlMngNo + " is not found data");
	          trdJson.put("trdPrcsRsltCd", "1");
	          aprlMngNo = "";
	        }

	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	        trdJson.put("trdPrcsRsltCd", "1");
	        aprlMngNo = "";
	      }
	      headerJson.put("trdCommon", trdJson);
	      rtvDtoJson.put("aprlMngNo", aprlMngNo);
	      headerJson.put("reqRespDvcd", "R");
	      rtvJson.put("header", headerJson);
	      rtvJson.put("dto", rtvDtoJson);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][approveAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	    }

	    logger.debug("[{}][approveAction result =>] " + rtvJson.toString(), getClass().getSimpleName());

	    return rtvJson.toString(); }

	  @RequestMapping({"/approveHistory"})
	  public String approveHistoryView(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> params) throws Exception {
	    String usrKey = (String)params.get("usrkey");
	    Map userInfoMap = this.userRegistService.selectUserNotAttrInfo(usrKey);
	    String reqUserId = (String)userInfoMap.get("USRLOGINID");
	    model.addAttribute("reqUserId", reqUserId);
	    return "histoty/approveHistoryView"; } 
	  @RequestMapping(value={"/approveHistory/list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public View approveHistoryList(@RequestParam Map<String, String> params, ModelMap model) throws Exception { int skip = Integer.parseInt((String)params.get("skip"));
	    int pageSize = Integer.parseInt((String)params.get("pageSize"));
	    logger.debug(params.toString());
	    UserAddBulkVO userAddBulkVO = new UserAddBulkVO();
	    userAddBulkVO.setFirstIndex(skip);
	    userAddBulkVO.setRecordCountPerPage(pageSize);

	    if (params.containsKey("sort")) {
	      String sortStr = (String)params.get("sort");
	      logger.debug("approveHistoryList sort : " + sortStr);
	      ObjectMapper mapper = new ObjectMapper();
	      List variables = new ArrayList();
	      variables = (List)mapper.readValue(sortStr, new TypeReference()
	      {
	      });
	      if (variables.size() > 0) {
	        userAddBulkVO.setSort(variables);
	      }
	    }

	    if (params.containsKey("REQID")) {
	      userAddBulkVO.setReqUserId((String)params.get("REQID"));
	    }

	    int checkAdminRole = this.userRegistService.checkAdminRole((String)params.get("REQID"));
	    if (checkAdminRole > 0) userAddBulkVO.setReqUserId(null);

	    if (params.containsKey("REQUSERID")) {
	      userAddBulkVO.setUsrLoginId((String)params.get("REQUSERID"));
	    }

	    if (params.containsKey("REQUSERNM")) {
	      userAddBulkVO.setUsrNm((String)params.get("REQUSERNM"));
	    }

	    if (params.containsKey("REQSTATUS")) {
	      userAddBulkVO.setAppprStat((String)params.get("REQSTATUS"));
	    }

	    if (params.containsKey("REQPERIOD")) {
	      userAddBulkVO.setPeriodType((String)params.get("REQPERIOD"));
	    }

	    if (params.containsKey("REQTYPE")) {
	      userAddBulkVO.setReqType((String)params.get("REQTYPE"));
	    }

	    if (params.containsKey("REQCONTENTS")) {
	      userAddBulkVO.setReqContents((String)params.get("REQCONTENTS"));
	    }

	    userAddBulkVO.setStartDt((String)params.get("STARTDT"));
	    userAddBulkVO.setEndDt((String)params.get("ENDDT"));

	    int total = Integer.parseInt((String)this.userRegistService.selectApproveHistoryListCount(userAddBulkVO));

	    List userAddBultList = this.userRegistService.selectApproveHistoryList(userAddBulkVO);
	    logger.info("[{}][approveHistoryList RESULT CNT =>] " + userAddBultList.size(), getClass().getSimpleName());

	    model.put("list", userAddBultList);
	    model.put("totalRecords", Integer.valueOf(total));

	    return this.jsonView; }

	  @RequestMapping(value={"/approveHistory/detail"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public String approveHistory(ModelMap model, @RequestParam Map<String, String> params)
	    throws Exception
	  {
	    String rtvPage = "";
	    String reqId = (String)params.get("reqId");
	    String reqType = (String)params.get("reqType");
	    if (reqType.equals("A"))
	      rtvPage = "histoty/userAddBulkList";
	    else if (reqType.equals("E"))
	      rtvPage = "histoty/userEditBulkList";
	    else if (reqType.equals("R")) {
	      rtvPage = "histoty/userRetireBulkList";
	    }

	    model.addAttribute("reqId", reqId);
	    model.addAttribute("reqType", reqType);

	    return rtvPage;
	  }
	  @RequestMapping({"/usr"})
	  public String usr(ModelMap model, @RequestParam Map<String, String> params) throws Exception {
	    String usrKey = (String)params.get("usrkey");
	    Map userInfoMap = this.userRegistService.selectUserNotAttrInfo(usrKey);
	    String reqUserId = (String)userInfoMap.get("USRLOGINID");
	    String reqType = (String)params.get("reqType");

	    List luvDataTypeList = this.userRegistService.selectLuvList("LUC0000002");
	    List luvDataSttusList = this.userRegistService.selectLuvList("LUC0000006");

	    JSONArray jsonObjectlist = new JSONArray();
	    for ( Object tmpVO :luvDataTypeList) {
	      JSONObject jsonObject = new JSONObject();
	      jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
	      jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());

	      jsonObjectlist.add(jsonObject);
	    }
	    model.addAttribute("luvDataType", jsonObjectlist);

	    jsonObjectlist = new JSONArray();
	    for ( Object tmpVO: luvDataSttusList) {
	      JSONObject jsonObject = new JSONObject();
	      jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
	      jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());

	      jsonObjectlist.add(jsonObject);
	    }
	    model.addAttribute("luvDataSttus", jsonObjectlist);

	    model.addAttribute("reqUserId", reqUserId);
	    model.addAttribute("reqType", reqType);

	    return "/user/usr";
	  }
	  @RequestMapping({"/user/userDetail"})
	  public String userDetail(ModelMap model, @RequestParam Map<String, String> params) throws Exception {
	    String usrKey = (String)params.get("usrKey");

	    Map userInfoMap = this.userRegistService.selectUserInfo(usrKey);
	    logger.debug("userInfoMap : " + userInfoMap.toString());
	    model.addAttribute("usrKey", usrKey);
	    model.addAttribute("usrNm", userInfoMap.get("USRNM"));
	    model.addAttribute("phone", userInfoMap.get("PHONE"));
	    model.addAttribute("cellPhone", userInfoMap.get("MOBILE"));
	    model.addAttribute("usrLoginId", userInfoMap.get("USRLOGINID"));
	    model.addAttribute("orgNm", userInfoMap.get("ORGNM"));
	    model.addAttribute("startDt", userInfoMap.get("STARTDT"));
	    model.addAttribute("endDt", userInfoMap.get("ENDDT"));
	    return "/user/usrDetail"; } 
	  @RequestMapping(value={"/user/userEditAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String userEditAction(UserAddBulkVO userAddBulkVO) throws Exception { JSONObject rtv = new JSONObject();
	    try {
	      this.userRegistService.editUserAction(userAddBulkVO);
	      rtv.put("isError", Boolean.valueOf(false));
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][userEditAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString(); } 
	  @RequestMapping(value={"/user/userRetireAction"}, produces={"text/html; charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public String userRetireAction(UserAddBulkVO userAddBulkVO) throws Exception {
	    JSONObject rtv = new JSONObject();
	    try {
	      SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	      userAddBulkVO.setEndDt(format.format(new Date()));
	      this.userRegistService.retireUserAction(userAddBulkVO);
	      rtv.put("isError", Boolean.valueOf(false));
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("[{}][userRetireAction Exception =>] " + e.getMessage(), getClass().getSimpleName());
	      rtv.put("isError", Boolean.valueOf(true));
	      rtv.put("message", e.getMessage());
	    }
	    return rtv.toString();
	  }

	  @RequestMapping(value={"/login/apprUsrSearch"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public String apprUsrSearch(ModelMap model, @RequestParam Map<String, String> params) throws Exception {
	    String callBackFnc = (String)params.get("callBackFnc");
	    String selector = (String)params.get("selector");

	    List luvDataTypeList = this.userRegistService.selectLuvList("LUC0000002");
	    List luvDataSttusList = this.userRegistService.selectLuvList("LUC0000006");

	    JSONArray jsonObjectlist = new JSONArray();
	    for ( Object tmpVO :luvDataTypeList) {
	      JSONObject jsonObject = new JSONObject();
	      jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
	      jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());

	      jsonObjectlist.add(jsonObject);
	    }
	    model.addAttribute("luvDataType", jsonObjectlist);

	    jsonObjectlist = new JSONArray();
	    for ( Object tmpVO : luvDataSttusList) {
	      JSONObject jsonObject = new JSONObject();
	      jsonObject.put("luvNm", ((LuvVO)tmpVO).getName());
	      jsonObject.put("luvId", ((LuvVO)tmpVO).getCode());

	      jsonObjectlist.add(jsonObject);
	    }
	    model.addAttribute("luvDataSttus", jsonObjectlist);
	    model.addAttribute("callBackFnc", callBackFnc);
	    model.addAttribute("selector", selector);

	    return "login/apprUsrSearch";
	  }
	  @RequestMapping(value={"/login/apprUsrSearch/list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseBody
	  public View selectApprUsrSearch(@RequestParam Map<String, String> params, ModelMap model) throws Exception { int skip = Integer.parseInt((String)params.get("skip"));
	    int pageSize = Integer.parseInt((String)params.get("pageSize"));

	    UsrVO usrVoParams = new UsrVO();

	    usrVoParams.setFirstIndex(skip);
	    usrVoParams.setRecordCountPerPage(pageSize);

	    if (params.containsKey("USRNM")) {
	      usrVoParams.setUsrNm((String)params.get("USRNM"));
	    }

	    if (params.containsKey("USRLOGINID")) {
	      usrVoParams.setUsrLoginId((String)params.get("USRLOGINID"));
	    }

	    if (params.containsKey("REQUSERID")) {
	      usrVoParams.setReqUserId((String)params.get("REQUSERID"));
	    }

	    int total = Integer.parseInt((String)this.userRegistService.selectApprUsrSearchCount(usrVoParams));

	    List apprUsrList = this.userRegistService.selectApprUsrSearch(usrVoParams);
	    logger.info("[{}][selectUsrSearch RESULT CNT =>] " + apprUsrList.size(), getClass().getSimpleName());

	    model.put("list", apprUsrList);
	    model.put("totalRecords", Integer.valueOf(total));

	    return this.jsonView;
	  }
	}
