package com.ur.tb.listed.tech.sector.web.service.Impl;

import com.ur.tb.listed.model.*;
import com.ur.tb.listed.model.findVO.*;
import com.ur.tb.listed.tech.sector.web.service.SectorChartService;
import com.ur.tb.listed.util.CommonUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;

@Service("SectorChartService")
public class SectorChartServiceImpl implements SectorChartService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.sector.list.url}")
    private String sectorListUrl;

    @Value("${tenbagger.api.sector.theme.list.url}")
    private String sectorThemeListUrl;

    @Value("${tenbagger.api.stock.history.url}")
    private String stockHistoryUrl;

    @Value("${tenbagger.api.stock.intraday.url}")
    private String stockIntraDayUrl;

    @Value("${tenbagger.api.market.index.url}")
    private String marketIndexUrl;

    @Value("${tenbagger.api.stock.rcmn.url}")
    private String stockRcmnUrl;

    @Value("${tenbagger.api.stock.pr.url}")
    private String stockPrUrl;

    @Value("${tenbagger.api.sector.pr.url}")
    private String sectorPrUrl;

    @Value("${tenbagger.api.sector.detail.url}")
    private String sectorDetailUrl;

    @Override
    public Object itemdata() {
        ResponseEntity<List<Sector>> response = restTemplate.exchange(apiUrl+sectorListUrl
                , HttpMethod.GET, null, new ParameterizedTypeReference<List<Sector>>() {
                });
        List<Sector> list = response.getBody();
        JSONArray rtv = new JSONArray();
        for(Sector sector:list) {
            JSONObject json = new JSONObject();
            json.put("sectorName", sector.getSectorName());
            ResponseEntity<List<Theme>> responseTheme = restTemplate.exchange(apiUrl+sectorThemeListUrl
                    , HttpMethod.GET, null, new ParameterizedTypeReference<List<Theme>>() {
                    }
                    ,sector.getSectorNo());
            List<Theme> listTheme = responseTheme.getBody();
            json.put("themeCnt", listTheme.size());
            json.put("color", CommonUtil.getSectorColor(String.valueOf(sector.getSectorNo())));
            json.put("sectorNo",sector.getSectorNo());
            rtv.put(json);
        }
        return rtv;
    }

    @Override
    public Object stockIntraDay(StockIntraDayFindVO stockIntraDayFindVO) throws  Exception{
        //검색 및 데이터 가공 추가 필요

        ResponseEntity<StockIntraDay> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+stockIntraDayUrl, stockIntraDayFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<StockIntraDay>() {}
        );
        StockIntraDay rtv = response.getBody();

        return rtv;
    }

    @Override
    public Object stockHistory(StockHistoryFindVO stockHistoryFindVO) throws  Exception{
        //검색 및 데이터 가공 추가 필요

        ResponseEntity<StockHistory> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+stockHistoryUrl, stockHistoryFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<StockHistory>() {}
        );
        StockHistory rtv = response.getBody();

        return rtv;
    }

    @Override
    public Object marketIndex(MarketIndexFindVO marketIndexFindVO) throws Exception {
        ResponseEntity<MarketIndex> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + marketIndexUrl, marketIndexFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<MarketIndex>() {}
        );
        MarketIndex rtv = response.getBody();
        return rtv;
    }

    @Override
    public Object stockRcmn(StockRcmnFindVO stockRcmnFindVO) throws Exception {
        String today = CommonUtil.getDate();
        String monthAgo = CommonUtil.getMonthAgoDate();
        ResponseEntity<List<StockRcmn> > response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + stockRcmnUrl+"/"+stockRcmnFindVO.getSectorNo(), stockRcmnFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<StockRcmn>>() {}
        );
        JSONArray rtv = new JSONArray();
        List<StockRcmn> list = response.getBody();
        StockPrFindVO stockPrFindVO = new StockPrFindVO();
        stockPrFindVO.setStartDd(monthAgo);
        stockPrFindVO.setEndDd(today);
        for(StockRcmn stockRcmn : list) {
            JSONObject json = new JSONObject();
            json.put("stockName", stockRcmn.getIsuKorNm());
            json.put("stockCd", stockRcmn.getIsuCd());
            json.put("priceRate", stockRcmn.getPriceRate());
            stockPrFindVO.setIsuSrtCd(stockRcmn.getIsuSrtCd());
            ResponseEntity<List<StockPrPriceRate>> responseStockPr = restTemplate.exchange(
                    CommonUtil.getUriStr(apiUrl + stockPrUrl+"/"+stockRcmn.getIsuSrtCd(), stockPrFindVO)
                    , HttpMethod.GET
                    , null
                    , new ParameterizedTypeReference<List<StockPrPriceRate>>() {}
            );
            List<StockPrPriceRate> listStockPr = responseStockPr.getBody();
            JSONArray stockPrJsonArr = new JSONArray();
            for(StockPrPriceRate stockPr : listStockPr) {
                JSONObject stockPrJson = new JSONObject();
                stockPrJson.put("trdDd",stockPr.getTrdDd());
                stockPrJson.put("priceRate",stockPr.getPriceRate());
                stockPrJsonArr.put(stockPrJson);
            }
            json.put("stockPr", stockPrJsonArr);
            rtv.put(json);
        }

        return rtv;
    }

    @Override
    public Object priceRate(String sectorNo) throws Exception {
        JSONObject rtv = new JSONObject();
        //kospi
        ResponseEntity<List<StockPrPriceRate>> responseKospi = restTemplate.exchange(
                apiUrl + stockPrUrl + "/K1"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<StockPrPriceRate>>() {}
        );
        rtv.put("kospi",responseKospi.getBody());
        //kosdaq
        ResponseEntity<List<StockPrPriceRate>> responseKosdaq = restTemplate.exchange(
                apiUrl + stockPrUrl + "/Q1"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<StockPrPriceRate>>() {}
        );
        rtv.put("kosdaq",responseKosdaq.getBody());
        //sector
        ResponseEntity<List<StockPrPriceRate>> responseSector = restTemplate.exchange(
                apiUrl + sectorPrUrl + "/"+sectorNo
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<StockPrPriceRate>>() {}
        );

        //sector detail
        ResponseEntity<Sector> responseSectorDetail = restTemplate.exchange(
                apiUrl + sectorDetailUrl + "/UNICORNRANCH/"+sectorNo
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Sector>() {}
        );
        Sector sector = responseSectorDetail.getBody();
        rtv.put("sectorName", sector.getSectorName());
        rtv.put("themeCnt", sector.getThemeCnt());
        rtv.put("stkCtn",sector.getStkCnt());
        rtv.put("rcmnStkCnt", sector.getRcmnStkCnt());
        rtv.put("sector",responseSector.getBody());
        return rtv;
    }

}
