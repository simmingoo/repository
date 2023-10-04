package com.ur.tb.listed.tech.sector.web.service.Impl;

import com.ur.tb.listed.model.Sector;
import com.ur.tb.listed.model.SectorPr;
import com.ur.tb.listed.model.Theme;
import com.ur.tb.listed.model.findVO.SectorFindVO;
import com.ur.tb.listed.tech.sector.web.service.SectorDataService;
import com.ur.tb.listed.util.CommonUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("SectorDataService")
public class SectorDataServiceImpl implements SectorDataService {

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.sector.list.url}")
    private String sectorListUrl;

    @Value("${tenbagger.api.sector.theme.list.url}")
    private String sectorThemeListUrl;

    @Value("${tenbagger.api.sector.cnt.url}")
    private String sectorAllCntUrl;

    @Value("${tenbagger.api.sector.pr.url}")
    private String sectorPrUrl;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Object sectorList() {
        ResponseEntity<List<Sector>> response = restTemplate.exchange(apiUrl+sectorListUrl
                , HttpMethod.GET, null, new ParameterizedTypeReference<List<Sector>>() {
                });
        List<Sector> list = response.getBody();
        JSONArray rtv = new JSONArray();
        for(Sector sector:list) {
            if(sector.getDisplayYn().equalsIgnoreCase("Y")){
                JSONObject json = new JSONObject();
                json.put("sectorNo",sector.getSectorNo());
                json.put("sectorName",sector.getSectorName());
                rtv.add(json);
            }
        }
        return rtv;
    }

    @Override
    public Object themeList(String sectorNo) {
        ResponseEntity<List<Theme>> response = restTemplate.exchange(apiUrl+sectorThemeListUrl
                , HttpMethod.GET, null, new ParameterizedTypeReference<List<Theme>>() {
                }
                ,sectorNo);
        List<Theme> list = response.getBody();
        JSONArray rtv = new JSONArray();
        for(Theme theme:list) {
            if(theme.getDisplayYn().equalsIgnoreCase("Y")){
                JSONObject json = new JSONObject();
                json.put("themeNo",theme.getThemeNo());
                json.put("themeName",theme.getThemeName());
                rtv.add(json);
            }
        }
        return rtv;
    }

    @Override
    public String sectorAllCnt() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl + sectorAllCntUrl
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {}
        );
        return response.getBody();
    }

    @Override
    public Object sectorPr(SectorFindVO sectorFindVO) throws Exception {
        ResponseEntity<List<SectorPr>> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + sectorPrUrl, sectorFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<SectorPr>>() {}
        );
        return response.getBody();
    }
}
