package com.rpt.admin.user.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpt.admin.common.main.CommonUtil;
import com.rpt.admin.domain.User;
import com.rpt.admin.stock.service.StockService;
import com.rpt.admin.user.service.UserService;

import net.sf.json.JSONObject;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="stockService")
	private StockService stockService;
	
	@Resource
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/user/userSearch")
	public String userSearch(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		return "user/userSearch";
	}
	
	@RequestMapping("/user/userSearch/list")
	public @ResponseBody View selectUserSearchList(@RequestParam Map<String,String> params,ModelMap model)throws Exception{
		int skip = Integer.parseInt(params.get("skip"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		
		User user = new User();
		user.setFirstIndex(skip);
		user.setRecordCountPerPage(pageSize);
		
		if(params.containsKey("userId") && !StringUtils.isEmpty(params.get("userId"))) {
			user.setUserId(params.get("userId"));
		}
		
		if(params.containsKey("userNm")&& !StringUtils.isEmpty(params.get("userNm"))) {
			user.setUserNm(params.get("userNm"));
		}
		
		if(params.containsKey("loginType")&& !StringUtils.isEmpty(params.get("loginType"))) {
			user.setLoginType(params.get("loginType"));
		}
		
		if(params.containsKey("userType")&& !StringUtils.isEmpty(params.get("userType"))) {
			user.setUserType(params.get("userType"));
		}
		
		if(params.containsKey("sort")) {
			String sortStr = (String)params.get("sort");
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String,String>> variables = new ArrayList<Map<String,String>>();
			variables = mapper.readValue(sortStr, new TypeReference<List<Map<String,String>>>(){});
			if(variables.size() > 0) {
				user.setSort(variables);
			}
		}
		
		int total = Integer.parseInt((String)userService.selectUserSearchListCount(user));
		List<?> userList = userService.selectUserSearchList(user);
		
		logger.info("[{}][selectUserSearchList RESULT CNT =>] " + userList.size(), this.getClass().getSimpleName());
		
		model.put("list", userList);
		model.put("totalRecords", total);
		
		return jsonView;
	}
	
	@RequestMapping("/user/userSearch/detail")
	public String userDetail(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		logger.debug("[{}] Get User Detail ID =>{}",this.getClass().getSimpleName(),params.get("userId"));
		
		User user = new User();
		user.setUserId(params.get("userId"));
		
		User userDetail = userService.selectUserDetail(user);
		
		model.addAttribute("userDetail",userDetail);
		return "user/userDetail";
	}
	
	@RequestMapping("/user/userStock/list")
	public @ResponseBody View selectUserStockList(@RequestParam Map<String,String> params,ModelMap model)throws Exception{
		int skip = Integer.parseInt(params.get("skip"));
		int pageSize = Integer.parseInt(params.get("pageSize"));
		
		User user = new User();
		user.setFirstIndex(skip);
		user.setRecordCountPerPage(pageSize);
		user.setUserId(params.get("userId"));
		
		if(params.containsKey("sort")) {
			String sortStr = (String)params.get("sort");
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String,String>> variables = new ArrayList<Map<String,String>>();
			variables = mapper.readValue(sortStr, new TypeReference<List<Map<String,String>>>(){});
			if(variables.size() > 0) {
				user.setSort(variables);
			}
		}
		
		int total = Integer.parseInt((String)userService.selectUserStockListCount(user));
		List<?> userStockList = userService.selectUserStockList(user);
		
		logger.info("[{}][selectUserStockList RESULT CNT =>] " + userStockList.size(), this.getClass().getSimpleName());
		
		model.put("list", userStockList);
		model.put("totalRecords", total);
		
		return jsonView;
	}
	
	@RequestMapping("/user/userRegist")
	public String userRegist(@RequestParam Map<String, String> params, ModelMap model) throws Exception{
		return "user/userRegist";
	}
	
	@RequestMapping(value="/user/userCheck" , produces ="text/html; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String userCheck(User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		
		int userCnt = Integer.parseInt((String) userService.selectUserCheck(userParam) );
		if(userCnt >0 ) {
			rtv.put("isError", true);
			rtv.put("message", "이미 생성된 아이디 입니다.");
		} else {
			rtv.put("isError", false);
			rtv.put("message", "ID 생성이 가능합니다.");
		}
		return rtv.toString();
	}
	
	@RequestMapping(value="/user/userRegistAct" , produces ="text/html; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String userRegistAct(User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		userParam.setPassword(CommonUtil.encryptPassword(userParam.getPassword()));
		
		try {
			userService.userRegist(userParam);
			rtv.put("isError", false);
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			rtv.put("isError", true);
			rtv.put("message", e.getMessage());
		}
		
		return rtv.toString();
	}
	
	@RequestMapping("/user/userLoginCheck")
	public @ResponseBody String userLoginCheck(User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		
		try {
			int userLoginCheckCnt = Integer.parseInt((String) userService.selectUserLoginCheck(userParam));
			if(userLoginCheckCnt == 0) {
				rtv.put("result", false);
			}else {
				rtv.put("result", true);
			}
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			rtv.put("result", "error");
		}
		
		return rtv.toString();
	}
	
	
}
