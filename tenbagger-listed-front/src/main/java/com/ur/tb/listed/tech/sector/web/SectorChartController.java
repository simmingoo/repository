package com.ur.tb.listed.tech.sector.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ur.tb.listed.model.findVO.MarketIndexFindVO;
import com.ur.tb.listed.model.findVO.StockHistoryFindVO;
import com.ur.tb.listed.model.findVO.StockIntraDayFindVO;
import com.ur.tb.listed.model.findVO.StockRcmnFindVO;
import com.ur.tb.listed.tech.sector.web.service.SectorChartService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Slf4j
@RequestMapping(value="/sectorchart")
public class SectorChartController {

    @Autowired
    private SectorChartService sectorChartService;

    @GetMapping("/itemdata")
    public String itemdata() {
        return CommonUtil.objectToJSONArray(sectorChartService.itemdata()).toString().replaceAll("분\\\\","");
    }

    @GetMapping("/stockIntraDay")
    public String stockIntraDay(StockIntraDayFindVO stockIntraDayFindVO) throws  Exception{
        return CommonUtil.getRtvStr(sectorChartService.stockIntraDay(stockIntraDayFindVO));
    }

    @GetMapping("/stockHistory")
    public String stockHistory(StockHistoryFindVO stockHistoryFindVO) throws  Exception{
        return CommonUtil.getRtvStr(sectorChartService.stockHistory(stockHistoryFindVO));
    }

    @GetMapping("/marketIndex")
    public String markeyIndex(MarketIndexFindVO marketIndexFindVO) throws  Exception {
        return CommonUtil.getRtvStr(sectorChartService.marketIndex(marketIndexFindVO));
    }

    @GetMapping("/stockRcmn")
    public String stockRcmn(StockRcmnFindVO stockRcmnFindVO) throws  Exception{
        return sectorChartService.stockRcmn(stockRcmnFindVO).toString().replaceAll("분\\\\","");
    }

    @GetMapping("/priceRate/{sectorNo}")
    public String priceRate(@PathVariable("sectorNo") String sectorNo) throws Exception {
        return sectorChartService.priceRate(sectorNo).toString();
    }
}
