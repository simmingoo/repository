package com.rpt.admin.stock.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rpt.admin.common.main.BaseAbstractDao;
import com.rpt.admin.domain.Report;
import com.rpt.admin.domain.Stock;
import com.rpt.admin.domain.Trade;

@Repository("stockDAO")
public class StockDAO extends BaseAbstractDao{
	
	public List<?> selectStockList(Stock params) throws Exception {
		return list("stock.selectStockList", params);
	}
	
	public Object selectStockListCount(Stock params) throws Exception {
		return selectByPk("stock.selectStockListCount", params);
	}
	
	public List<Trade> selectStockListApi(Trade params) throws Exception {
		return list("stock.selectStockListApi",params);
	}
	
	public List<Trade> selectStockListSimpleApi(Trade params) throws Exception {
		return list("stock.selectStockListSimpleApi",params);
	}
	
	public List<Trade> selectStockCategoryListSimpleApi(Trade params) throws Exception {
		return list("stock.selectStockCategoryListSimpleApi", params);
	}
	
	public Object selectStockDetailInfo(Trade params) throws Exception {
		return selectByPk("stock.selectStockDetailInfo", params);
	}
	
	public Object selectDartFinancialInfo(Trade params) throws Exception {
		return selectByPk("stock.selectDartFinancialInfo", params);
	}

	public List<Trade> selectDartFinancialInfoDetail(Trade params) throws Exception {
		return list("stock.selectDartFinancialInfoDetail", params);
	}
	
	public Object selectReportCnt(Report params) throws Exception {
		return selectByPk("stock.selectReportCnt", params);
	}
	
	public void deletePrevReport(Report params) throws Exception {
		delete("stock.deletePrevReport", params);
	}
	
	public void registReport(Report params) throws Exception {
		insert("stock.registReport", params);
	}

}
