package com.ur.tb.listed.tech.theme.service.Impl;

import com.ur.tb.listed.model.ThemePr;
import com.ur.tb.listed.model.findVO.ThemePrFindVO;
import com.ur.tb.listed.tech.theme.service.ThemeRankingService;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("ThemeRankingService")
public class ThemeRankingServiceImpl implements ThemeRankingService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.theme.pr.url}")
    private String themePrUrl;

    @Override
    public Object themePr(ThemePrFindVO themePrFindVO) throws Exception {
        ResponseEntity<List<ThemePr>> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + themePrUrl + "/" + themePrFindVO.getSectorNo(), themePrFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<ThemePr>>() {}
        );
        return response.getBody();
    }
}
