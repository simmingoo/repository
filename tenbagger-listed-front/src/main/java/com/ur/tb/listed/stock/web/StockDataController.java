package com.ur.tb.listed.stock.web;

import com.ur.tb.listed.model.findVO.StockFindVO;
import com.ur.tb.listed.stock.service.StockDataService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/stockdata")
@Slf4j
public class StockDataController {

    @Autowired
    private StockDataService stockDataService;

    @GetMapping(value = "/list")
    public String list(StockFindVO stockSearch) throws Exception{
        Object rtv = new ArrayList<>();
        try {
            rtv = stockDataService.sectorStockList(stockSearch);
        } catch (Exception e) {
            log.error(CommonUtil.getPrintStackTrace(e));
        }
        return CommonUtil.getRtvStr(rtv);
    }



}
