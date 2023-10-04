package com.rpt.admin.api.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rpt.admin.common.main.CommonUtil;
import com.rpt.admin.domain.Stock;
import com.rpt.admin.domain.Trade;
import com.rpt.admin.domain.User;
import com.rpt.admin.stock.service.StockService;
import com.rpt.admin.user.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class ApiController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="stockService")
	private StockService stockService;
	
	@Resource
	private MappingJackson2JsonView jsonView;
	
	@RequestMapping("/api/userCheck")
	public @ResponseBody String userLoginCheck(User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		
		try {
			userParam.setPassword(CommonUtil.encryptPassword(userParam.getPassword()));
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
	
	@RequestMapping(value="/api/stockSearch", produces ="text/html; charset=utf-8" )
	public @ResponseBody String stockSearch(@RequestParam Map<String,String> params) throws Exception {
		JSONArray rtvArr = new JSONArray();
		Trade tradeParam = new Trade();
		
		if(params.containsKey("searchType")) {
			if(!StringUtils.isEmpty(params.get("searchType"))) {
				tradeParam.setSearchType(params.get("searchType"));
			}
		}
		if(params.containsKey("stockId")) {
			if(!StringUtils.isEmpty(params.get("stockId"))) {
				tradeParam.setStockId(params.get("stockId"));
			}
		}
		if(params.containsKey("stockNm")) {
			if(!StringUtils.isEmpty(params.get("stockNm"))) {
				tradeParam.setStockNm(params.get("stockNm"));
			}
		}
		
		if(params.containsKey("stockSearchType")) {
			if(!StringUtils.isEmpty(params.get("stockSearchType")) ) {
				tradeParam.setStockSearchType(params.get("stockSearchType"));
			}
		}	
		
		if(params.containsKey("fluctuationRateType")) {
			if(!StringUtils.isEmpty(params.get("fluctuationRateType")) ) {
				tradeParam.setFluctuationRateType(params.get("fluctuationRateType"));
			}
		}	
		
		if(params.containsKey("topNum")) {
			if(!StringUtils.isEmpty(params.get("topNum")) ) {
				tradeParam.setTopNum(params.get("topNum"));
			}
		}	
		List<Trade> tradeList = stockService.selectStockListApi(tradeParam);
		for(Trade trade : tradeList) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(trade);
			try {
				JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
				rtvArr.add(json);
			} catch (Exception e) {
				logger.error(CommonUtil.getPrintStackTrace(e));
			}
		}
		
		
		return rtvArr.toString();
	}
	
	@RequestMapping(value="/api/stockSearchSimple", produces ="text/html; charset=utf-8" )
	public @ResponseBody String stockSearchSimple(@RequestParam Map<String,String> params) throws Exception {
		JSONArray rtvArr = new JSONArray();
		Trade tradeParam = new Trade();
		
		if(params.containsKey("searchType")) {
			if(!StringUtils.isEmpty(params.get("searchType"))) {
				tradeParam.setSearchType(params.get("searchType"));
			}
		}
		if(params.containsKey("stockId")) {
			if(!StringUtils.isEmpty(params.get("stockId"))) {
				tradeParam.setStockId(params.get("stockId"));
			}
		}
		if(params.containsKey("stockNm")) {
			if(!StringUtils.isEmpty(params.get("stockNm"))) {
				tradeParam.setStockNm(params.get("stockNm"));
			}
		}
		
		
		List<Trade> tradeList = stockService.selectStockListSimpleApi(tradeParam);
		for(Trade trade : tradeList) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(trade);
			try {
				JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
				rtvArr.add(json);
			} catch (Exception e) {
				logger.error(CommonUtil.getPrintStackTrace(e));
			}
		}
		
		return rtvArr.toString();
	}
	
	@RequestMapping(value="/api/userRegist" , produces ="text/html; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String userRegistAct(User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		if(userParam.getEncYn().equalsIgnoreCase("Y")) {
			userParam.setPassword(CommonUtil.encryptPassword(userParam.getPassword()));
		}
		try {
			userService.userRegist(userParam);
			rtv.put("result", true);
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			rtv.put("result", false);
		}
		
		return rtv.toString();
	}

	@RequestMapping(value="/api/stockSearchTypeSimple", produces ="text/html; charset=utf-8" )
	public @ResponseBody String stockSearchTypeSimple(@RequestParam Map<String,String> params) throws Exception {
		JSONArray rtvArr = new JSONArray();
		Trade tradeParam = new Trade();
		
		if(params.containsKey("stockSearchType")) {
			if(!StringUtils.isEmpty("stockSearchType")) {
				tradeParam.setStockSearchType(params.get("stockSearchType"));
			}
		}
		
		if(params.containsKey("searchType")) {
			if(!StringUtils.isEmpty(params.get("searchType"))) {
				tradeParam.setSearchType(params.get("searchType"));
			}
		}
		if(params.containsKey("stockId")) {
			if(!StringUtils.isEmpty(params.get("stockId"))) {
				tradeParam.setStockId(params.get("stockId"));
			}
		}
		if(params.containsKey("stockNm")) {
			if(!StringUtils.isEmpty(params.get("stockNm"))) {
				tradeParam.setStockNm(params.get("stockNm"));
			}
		}
		
		List<Trade> tradeList = stockService.selectStockCategoryListSimpleApi(tradeParam);
		for(Trade trade : tradeList) {
			Gson gson = new Gson();
			String jsonString = gson.toJson(trade);
			try {
				JSONObject json = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
				rtvArr.add(json);
			} catch (Exception e) {
				logger.error(CommonUtil.getPrintStackTrace(e));
			}
		}
		
		return rtvArr.toString();
	}
	
	@RequestMapping(value="/api/userUpdate" , produces ="text/html; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String userUpdateAct (User userParam) throws Exception{
		JSONObject rtv = new JSONObject();
		if(userParam.getEncYn().equalsIgnoreCase("Y")) {
			userParam.setPassword(CommonUtil.encryptPassword(userParam.getPassword()));
		}
		try {
			userService.updateUser(userParam);
			rtv.put("result", true);
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			rtv.put("result", false);
		}
				
		return rtv.toString();		
	}
	
	@RequestMapping(value="/api/userInfo", produces ="text/html; charset=utf-8")
	public @ResponseBody String userInfo (User userParam) throws Exception {
		JSONObject rtv = new JSONObject();
		Map<String, String> getUserInfo = userService.selectUserDetailApi(userParam);
		if(getUserInfo != null) {
			rtv.put("userId",getUserInfo.get("userId"));
			rtv.put("userNm",getUserInfo.get("userNm"));
			rtv.put("password",getUserInfo.get("password"));
			rtv.put("lastLoginTime",getUserInfo.get("lastLoginTime"));
			rtv.put("loginType",getUserInfo.get("loginType"));
			rtv.put("userType",getUserInfo.get("userType"));
			rtv.put("phoneNum",getUserInfo.get("phoneNum"));
			rtv.put("nickNm",getUserInfo.get("nickNm"));
			List<Map<String, String>> userPossessStockList = userService.selectUserPossessStockList(userParam);
			JSONArray userPossessStockListJson = new JSONArray();
			if(userPossessStockList != null) {
				for(Map<String, String> map : userPossessStockList) {
					JSONObject userPossessStockJson = new JSONObject();
					userPossessStockJson.put("stockId", map.get("stockId"));
					userPossessStockJson.put("stockNm", map.get("stockNm"));
					userPossessStockListJson.add(userPossessStockJson);
				}
			}
			rtv.put("possessList", userPossessStockListJson);
		}
		
		return rtv.toString();
	}
	
	@RequestMapping(value="/api/userPossessStockManage", produces ="text/html; charset=utf-8")
	public @ResponseBody String userPossessStockManageAct(User userParam) throws Exception {
		JSONObject rtv = new JSONObject();
		try {
			if(userParam.getPossessType().equalsIgnoreCase("insert")) {
				userService.insertUserPossessStock(userParam);
			} else if(userParam.getPossessType().equalsIgnoreCase("delete")) {
				userService.deleteUserPossessStock(userParam);
			}
			rtv.put("result", true);
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			rtv.put("result", false);
		}
		
		return rtv.toString();
	}
	
	@RequestMapping(value="/api/stockDetailInfo", produces ="text/html; charset=utf-8")
	public @ResponseBody String stockDetailInfo(@RequestParam Map<String,String> params) throws Exception{
		JSONObject rtv = new JSONObject();
		if(params.containsKey("stockId")) {
			if(!StringUtils.isEmpty(params.get("stockId"))) {
				Trade tradeParam = new Trade();
				tradeParam.setStockId(params.get("stockId"));
				Object tradeDetail = stockService.selectStockDetailInfo(tradeParam);
				Gson gson = new Gson();
				String jsonString = gson.toJson(tradeDetail);
				rtv = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
			}
		}else {
			rtv.put("result", false);
			rtv.put("resultMst", "stockId is null");
		}
		
		return rtv.toString();
	}
	
	@RequestMapping(value="/api/stockDartInfo", produces ="text/html; charset=utf-8")
	public @ResponseBody String stockDartInfo(@RequestParam Map<String,String> params) throws Exception{
		JSONObject rtv = new JSONObject();
		if(params.containsKey("stockId")) {
			if(!StringUtils.isEmpty(params.get("stockId"))) {
				Trade tradeParam = new Trade();
				tradeParam.setStockId(params.get("stockId"));
				JSONArray dartDetailArr = new JSONArray();
						
				Object tradeDetail = stockService.selectDartFinancialInfo(tradeParam);
				Gson gson = new Gson();
				String jsonString = gson.toJson(tradeDetail);
				rtv = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
				
				List<Trade> dartDetail = stockService.selectDartFinancialInfoDetail(tradeParam);
				for(Trade trade : dartDetail) {
					gson = new Gson();
					String dartJsonString = gson.toJson(trade);
					JSONObject dartDetailJson = JSONObject.fromObject(JSONSerializer.toJSON(dartJsonString));
					dartDetailArr.add(dartDetailJson);
				}
				rtv.put("dartDetail", dartDetailArr);
			}
		}else {
			rtv.put("result", false);
			rtv.put("resultMst", "stockId is null");
		}
		
		return rtv.toString();
	}

}
