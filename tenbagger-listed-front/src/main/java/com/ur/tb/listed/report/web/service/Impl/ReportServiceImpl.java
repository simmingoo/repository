package com.ur.tb.listed.report.web.service.Impl;

import com.ur.tb.listed.model.findVO.ReportFindVO;
import com.ur.tb.listed.report.web.service.ReportService;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.report.citation.url}")
    private String citationUrl;

    @Value("${tenbagger.api.report.competition.url}")
    private String competitionUrl;

    @Value("${tenbagger.api.report.family.url}")
    private String familyUrl;

    @Value("${tenbagger.api.report.keyinventor.url}")
    private String keyinventorUrl;

    @Value("${tenbagger.api.report.keypatent.url}")
    private String keypatentUrl;

    @Value("${tenbagger.api.report.keyword.url}")
    private String keywordUrl;

    @Value("${tenbagger.api.report.outline.url}")
    private String outlineUrl;

    @Value("${tenbagger.api.report.patentactivity.url}")
    private String patentactivityUrl;

    @Value("${tenbagger.api.report.technology.url}")
    private String technologyUrl;

    @Value("${tenbagger.api.report.transfer.url}")
    private String transferUrl;

    @Value("${tenbagger.api.report.stock.url}")
    private String stockUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object citation(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+citationUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object competition(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+competitionUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object family(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+familyUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object keyinventor(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+keyinventorUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object keypatent(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+keypatentUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object keyword(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+keywordUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object outline(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+outlineUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object patentactivity(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+patentactivityUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object technology(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+technologyUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object transfer(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+transferUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }

    @Override
    public Object stock(ReportFindVO reportFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl+stockUrl, reportFindVO)
                ,HttpMethod.GET
                ,null
                ,new ParameterizedTypeReference<Object>(){}
        );
        return response.getBody();
    }
}
