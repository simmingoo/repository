package com.ur.tb.listed.stock.service.Impl;

import com.ur.tb.listed.model.StockPrList;
import com.ur.tb.listed.model.Stocks;
import com.ur.tb.listed.model.findVO.MarketIndexFindVO;
import com.ur.tb.listed.model.findVO.StockIntraDayFindVO;
import com.ur.tb.listed.model.findVO.StockPrSectorFindVO;
import com.ur.tb.listed.model.findVO.StockPrThemeFindVO;
import com.ur.tb.listed.stock.service.StockService;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.stock.pr.sector.url}")
    private String stockPrSectorUrl;

    @Value("${tenbagger.api.stock.pr.theme.url}")
    private String stockPrThemeUrl;

    @Value("${tenbagger.api.stock.price.url}")
    private String stockPriceUrl;

    @Value("${tenbagger.api.stock.compare.sector.url}")
    private String compareSectorUrl;

    @Value("${tenbagger.api.stock.compare.theme.url}")
    private String compareThemeUrl;

    @Override
    public Object prSector(StockPrSectorFindVO stockPrSectorFindVO) throws Exception {
        ResponseEntity<StockPrList> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + stockPrSectorUrl + "/"+ stockPrSectorFindVO.getSectorNo(), stockPrSectorFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<StockPrList>() {}

        );
        return response.getBody();
    }

    @Override
    public Object prTheme(StockPrThemeFindVO stockPrThemeFindVO) throws Exception {
        ResponseEntity<StockPrList> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + stockPrThemeUrl + "/" + stockPrThemeFindVO.getThemeNo(), stockPrThemeFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<StockPrList>() {}

        );
        return response.getBody();
    }

    @Override
    public Object price(MarketIndexFindVO marketIndexFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + stockPriceUrl, marketIndexFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}

        );
        return response.getBody();
    }

    @Override
    public Object compareSector(String bizNo) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl + compareSectorUrl
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
                ,bizNo
        );
        return response.getBody();
    }

    @Override
    public Object compareTheme(String bizNo) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl + compareThemeUrl
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
                ,bizNo
        );
        return response.getBody();
    }
}
