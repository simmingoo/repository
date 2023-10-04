package com.rpt.admin.stock.service;

import java.util.List;

import com.rpt.admin.domain.Report;
import com.rpt.admin.domain.Stock;
import com.rpt.admin.domain.Trade;

public interface StockService {
	
	public List<?> selectStockList(Stock params) throws Exception;
	
	public Object selectStockListCount(Stock params) throws Exception;
	
	public List<Trade> selectStockListApi(Trade params) throws Exception;
	
	public List<Trade> selectStockListSimpleApi(Trade params) throws Exception;

	public List<Trade> selectStockCategoryListSimpleApi(Trade params) throws Exception;
	
	public Object selectStockDetailInfo(Trade params) throws Exception;
	
	public Object selectDartFinancialInfo(Trade params) throws Exception;

	public List<Trade> selectDartFinancialInfoDetail(Trade params) throws Exception;
	
	public Object selectReportCnt(Report params) throws Exception;
	
	public void deletePrevReport(Report params) throws Exception;
	
	public void registReport(Report params) throws Exception;

}
