package com.ur.tb.listed.stock.service;

import com.ur.tb.listed.model.findVO.MarketIndexFindVO;
import com.ur.tb.listed.model.findVO.StockIntraDayFindVO;
import com.ur.tb.listed.model.findVO.StockPrSectorFindVO;
import com.ur.tb.listed.model.findVO.StockPrThemeFindVO;

public interface StockService {

    public Object prSector(StockPrSectorFindVO stockPrSectorFindVO) throws Exception;

    public Object prTheme(StockPrThemeFindVO stockPrThemeFindVO) throws Exception;

    public Object price(MarketIndexFindVO marketIndexFindVO) throws  Exception;

    public Object compareSector(String bizNo) throws  Exception;

    public Object compareTheme(String bizNo) throws  Exception;
}
