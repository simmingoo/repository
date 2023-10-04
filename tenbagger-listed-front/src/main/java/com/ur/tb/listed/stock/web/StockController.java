package com.ur.tb.listed.stock.web;

import com.ur.tb.listed.model.findVO.MarketIndexFindVO;
import com.ur.tb.listed.model.findVO.StockIntraDayFindVO;
import com.ur.tb.listed.model.findVO.StockPrSectorFindVO;
import com.ur.tb.listed.model.findVO.StockPrThemeFindVO;
import com.ur.tb.listed.stock.service.StockService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value="/stock")
public class StockController {
    //추후 종목 / 섹터 / 테마 등 각각의 컨트롤러로 변경 또는 api 를 프론트에서 직접 호출 하도록 변경
    //https->http 직접 호출 이슈 있어 임시로 컨트롤러 생성(전체) & 리팩토리 필요
    //2021-11-24

    @Autowired
    private StockService stockService;

    @GetMapping(value="/pr/sector")
    public String prSector(StockPrSectorFindVO stockPrSectorFindVO) throws Exception {
        return CommonUtil.getRtvStr(stockService.prSector(stockPrSectorFindVO));
    }

    @GetMapping(value="/pr/theme")
    public String prSector(StockPrThemeFindVO stockPrThemeFindVO) throws Exception {
        return CommonUtil.getRtvStr(stockService.prTheme(stockPrThemeFindVO));
    }

    @GetMapping(value = "/price")
    public String price(MarketIndexFindVO marketIndexFindVO) throws Exception {
        return CommonUtil.getRtvStr(stockService.price(marketIndexFindVO));
    }

    @GetMapping(value= "/compare/sector/{bizNo}")
    public String compareSector(@PathVariable("bizNo") String bizNo) throws  Exception{
        return CommonUtil.getRtvStr(stockService.compareSector(bizNo));
    }

    @GetMapping(value= "/compare/theme/{bizNo}")
    public String compareTheme(@PathVariable("bizNo") String bizNo) throws  Exception{
        return CommonUtil.getRtvStr(stockService.compareTheme(bizNo));
    }


}
