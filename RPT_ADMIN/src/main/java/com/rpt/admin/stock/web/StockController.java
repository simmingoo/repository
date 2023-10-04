package com.rpt.admin.stock.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpt.admin.common.main.CommonUtil;
import com.rpt.admin.domain.Report;
import com.rpt.admin.domain.Stock;
import com.rpt.admin.domain.Trade;
import com.rpt.admin.stock.service.StockService;
import com.rpt.admin.user.service.UserService;

import net.sf.json.JSONObject;

@Controller
public class StockController {
	
	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="stockService")
	private StockService stockService;
	
	@Resource
	private MappingJackson2JsonView jsonView;
	
	@Value("${Globals.imageUploadPath}")
	private String imagePath;
	
	@RequestMapping("/stock/stockSearch")
	public String stockSearch(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		return "stock/stockSearch";
	}
	
	@RequestMapping("/stock/stockSearch/list")
	public @ResponseBody View selectStockSearchList(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		int skip = Integer.parseInt(params.get("skip"));
		int pageSize = Integer.parseInt(params.get("pageSize"));

		
		Stock stock = new Stock();
		stock.setFirstIndex(skip);
		stock.setRecordCountPerPage(pageSize);
		
		if(params.containsKey("sort")) {
			String sortStr = (String)params.get("sort");
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String,String>> variables = new ArrayList<Map<String,String>>();
			variables = mapper.readValue(sortStr, new TypeReference<List<Map<String,String>>>(){});
			if(variables.size() > 0) {
				stock.setSort(variables);
			}
		}
		
		if(params.containsKey("stockId") && !StringUtils.isEmpty(params.get("stockId"))) {
			stock.setStockId(params.get("stockId"));
		}
		
		if(params.containsKey("stockNm") && !StringUtils.isEmpty(params.get("stockNm"))) {
			stock.setStockNm(params.get("stockNm"));
		}
		
		if(params.containsKey("stockType") && !StringUtils.isEmpty(params.get("stockType"))) {
			stock.setStockType(params.get("stockType"));
		}
		
		if(params.containsKey("stockReportYn") && !StringUtils.isEmpty(params.get("stockReportYn"))) {
			stock.setStockReportYn(params.get("stockReportYn"));
		}
		
		int total = Integer.parseInt((String)stockService.selectStockListCount(stock));
		List<?> stockList = stockService.selectStockList(stock);
		
		
		model.put("list", stockList);
		model.put("totalRecords", total);
		
		return jsonView;
	}
	
	@RequestMapping("/stock/stockSearch/regist")
	public String reportRegist(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		return "/stock/stockRegist";
	}
	
	@RequestMapping("/stock/stockReport")
	public String stockReport(ModelMap model, @RequestParam Map<String,String> params) throws Exception{
		
		Trade trade = new Trade();
		trade.setStockId(params.get("popStockId"));
				
		Object stockInfo = stockService.selectStockDetailInfo(trade);
		model.addAttribute("stockInfo",stockInfo);
		return "stock/stockReport";
	}
	
	@RequestMapping(value="/stock/stockReportAct", produces ="text/html; charset=utf-8")
	public String stockRegistAct (MultipartHttpServletRequest request, ModelMap model) throws Exception{
		request.setCharacterEncoding("UTF-8");
		Report report = new Report();
		String primaryProductSalesFileName = "";
		String imcomeStatementImageFile1Name = "";
		String imcomeStatementImageFile2Name = "";
		
		String primaryProductSalesFilePath = "";
		String imcomeStatementImageFile1Path = "";
		String imcomeStatementImageFile2Path = "";
		
		String corpOverviewContent =StringEscapeUtils.escapeHtml4(new String(request.getParameter("corpOverviewContent").getBytes("8859_1"), "UTF-8"));
		String industryProperty = StringEscapeUtils.escapeHtml4(new String(request.getParameter("industryProperty").getBytes("8859_1"), "UTF-8"));
		String elementCompetition = StringEscapeUtils.escapeHtml4(new String(request.getParameter("elementCompetition").getBytes("8859_1"), "UTF-8"));
		String companyStatus = StringEscapeUtils.escapeHtml4(new String(request.getParameter("companyStatus").getBytes("8859_1"), "UTF-8"));
		String incomeStatementImageContent1 = StringEscapeUtils.escapeHtml4(new String(request.getParameter("incomeStatementImageContent1").getBytes("8859_1"), "UTF-8"));
		String incomeStatementImageContent2 = StringEscapeUtils.escapeHtml4(new String(request.getParameter("incomeStatementImageContent2").getBytes("8859_1"), "UTF-8"));
		
		logger.debug("stockId : "+request.getParameter("stockId"));
		logger.debug("corpOverviewContent : "+corpOverviewContent);
		logger.debug("industryProperty : "+industryProperty);
		logger.debug("elementCompetition : "+elementCompetition);
		logger.debug("companyStatus : "+companyStatus);
		logger.debug("incomeStatementImageContent1 : "+incomeStatementImageContent1);
		logger.debug("incomeStatementImageContent2 : "+incomeStatementImageContent2);
		
		MultipartFile primaryProductSales = request.getFile("primaryProductSales");
		if(!primaryProductSales.isEmpty()) {
			primaryProductSalesFileName = UUID.randomUUID().toString()+primaryProductSales.getOriginalFilename().substring(primaryProductSales.getOriginalFilename().lastIndexOf("."),primaryProductSales.getOriginalFilename().length());
			primaryProductSalesFilePath = imagePath+primaryProductSalesFileName;
			primaryProductSales.transferTo(new File(primaryProductSalesFilePath));
		}
		
		
		MultipartFile incomeStatementImageFile1 = request.getFile("incomeStatementImagePath1");
		if(!incomeStatementImageFile1.isEmpty()) {
			imcomeStatementImageFile1Name = UUID.randomUUID().toString()+incomeStatementImageFile1.getOriginalFilename().substring(incomeStatementImageFile1.getOriginalFilename().lastIndexOf("."),incomeStatementImageFile1.getOriginalFilename().length());
			imcomeStatementImageFile1Path = imagePath+imcomeStatementImageFile1Name;
			incomeStatementImageFile1.transferTo(new File(imcomeStatementImageFile1Path));
		}
		
		
		MultipartFile incomeStatementImageFile2 = request.getFile("incomeStatementImagePath2");
		if(!incomeStatementImageFile2.isEmpty()) {
			imcomeStatementImageFile2Name = UUID.randomUUID().toString()+incomeStatementImageFile2.getOriginalFilename().substring(incomeStatementImageFile2.getOriginalFilename().lastIndexOf("."),incomeStatementImageFile2.getOriginalFilename().length());
			imcomeStatementImageFile2Path = imagePath+imcomeStatementImageFile2Name;
			incomeStatementImageFile2.transferTo(new File(imcomeStatementImageFile2Path));
		}
		
		
		report.setStockId(request.getParameter("stockId"));
		report.setCorpOverviewContent(corpOverviewContent);
		report.setIndustryProperty(industryProperty);
		report.setElementCompetition(elementCompetition);
		report.setCompanyStatus(companyStatus);
		report.setIncomeStatementImageContent1(incomeStatementImageContent1);
		report.setIncomeStatementImageContent2(incomeStatementImageContent2);
		report.setPrimaryProductSales(primaryProductSalesFilePath);
		report.setIncomeStatementImagePath1(imcomeStatementImageFile1Path);
		report.setIncomeStatementImagePath2(imcomeStatementImageFile2Path);
		
		int reportCnt = Integer.parseInt((String) stockService.selectReportCnt(report));
		
		try {
			if(reportCnt > 0) {
				stockService.deletePrevReport(report);
				stockService.registReport(report);
			}else {
				stockService.registReport(report);
			}
			model.addAttribute("resultMsg", "등록이 완료 되었습니다.");
		} catch (Exception e) {
			logger.error(CommonUtil.getPrintStackTrace(e));
			model.addAttribute("resultMsg", "등록이 실패 하였습니다. 사유 : "+CommonUtil.getPrintStackTrace(e));
		}
		
		return "cmm/ResponseMsg";
	}
}
