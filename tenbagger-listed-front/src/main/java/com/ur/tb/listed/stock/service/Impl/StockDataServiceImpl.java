package com.ur.tb.listed.stock.service.Impl;

import com.ur.tb.listed.model.Corp;
import com.ur.tb.listed.model.Stock;
import com.ur.tb.listed.model.findVO.StockFindVO;
import com.ur.tb.listed.stock.service.StockDataService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("StockDataService")
@Slf4j
public class StockDataServiceImpl implements StockDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.stock.ranking.list.url}")
    private String stockListUrl;

    @Override
    public Object sectorStockList(StockFindVO stockSearch) throws  Exception{
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+stockListUrl, stockSearch)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
                );
        return response.getBody();
    }

}
