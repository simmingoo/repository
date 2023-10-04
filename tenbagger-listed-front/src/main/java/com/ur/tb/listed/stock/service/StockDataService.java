package com.ur.tb.listed.stock.service;

import com.ur.tb.listed.model.findVO.StockFindVO;

import java.util.ArrayList;
import java.util.Map;

public interface StockDataService {

    public Object sectorStockList(StockFindVO stockSearch) throws  Exception;
}
