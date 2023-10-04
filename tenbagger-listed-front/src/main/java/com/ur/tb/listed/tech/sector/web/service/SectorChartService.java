package com.ur.tb.listed.tech.sector.web.service;

import com.ur.tb.listed.model.findVO.*;

public interface SectorChartService {

    public Object itemdata();

    public Object stockHistory(StockHistoryFindVO stockHistoryFindVO) throws  Exception;

    public Object stockIntraDay(StockIntraDayFindVO stockIntraDayFindVO) throws  Exception;

    public Object marketIndex(MarketIndexFindVO marketIndexFindVO) throws  Exception;

    public Object stockRcmn(StockRcmnFindVO stockRcmnFindVO) throws  Exception;

    public Object priceRate(String sectorNo) throws Exception;

}
