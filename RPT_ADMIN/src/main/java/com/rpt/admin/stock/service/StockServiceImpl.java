package com.rpt.admin.stock.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpt.admin.domain.Report;
import com.rpt.admin.domain.Stock;
import com.rpt.admin.domain.Trade;
import com.rpt.admin.stock.dao.StockDAO;
import com.rpt.admin.user.dao.UserDAO;

@Service(value="stockService")
public class StockServiceImpl implements StockService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="stockDAO")
	private StockDAO stockDAO;

	public List<?> selectStockList(Stock params) throws Exception {
		return stockDAO.selectStockList(params);
	}

	public Object selectStockListCount(Stock params) throws Exception {
		return stockDAO.selectStockListCount(params);
	}

	public List<Trade> selectStockListApi(Trade params) throws Exception {
		return stockDAO.selectStockListApi(params);
	}
	
	public List<Trade> selectStockListSimpleApi(Trade params) throws Exception {
		return stockDAO.selectStockListSimpleApi(params);
	}

	public List<Trade> selectStockCategoryListSimpleApi(Trade params) throws Exception {
		return stockDAO.selectStockCategoryListSimpleApi(params);
	}

	public Object selectStockDetailInfo(Trade params) throws Exception {
		return stockDAO.selectStockDetailInfo(params);
	}

	public Object selectDartFinancialInfo(Trade params) throws Exception {
		return stockDAO.selectDartFinancialInfo(params);
	}

	public List<Trade> selectDartFinancialInfoDetail(Trade params) throws Exception {
		return stockDAO.selectDartFinancialInfoDetail(params);
	}

	public Object selectReportCnt(Report params) throws Exception {
		return stockDAO.selectReportCnt(params);
	}

	public void deletePrevReport(Report params) throws Exception {
		stockDAO.deletePrevReport(params);
	}

	public void registReport(Report params) throws Exception {
		stockDAO.registReport(params);
	}

}
