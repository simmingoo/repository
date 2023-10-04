package com.ur.tb.listed.finance.service.Impl;

import com.ur.tb.listed.finance.service.FinanceService;
import com.ur.tb.listed.model.findVO.FinanceFindVO;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

@Service("FinanceService")
public class FinanceServiceImpl implements FinanceService {

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.finance.balance.url}")
    private String balanceUrl;

    @Value("${tenbagger.api.finance.fundamental.url}")
    private String fundamentalUrl;

    @Value("${tenbagger.api.finance.cashflow.url}")
    private String cashflowUrl;

    @Value("${tenbagger.api.finance.incomestatement.url}")
    private String incomestatementUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object balance(FinanceFindVO financeFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + balanceUrl, financeFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    @Override
    public Object fundamental(FinanceFindVO financeFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + fundamentalUrl, financeFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    @Override
    public Object cashflow(FinanceFindVO financeFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + cashflowUrl, financeFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    @Override
    public Object incomestatement(FinanceFindVO financeFindVO) throws Exception {
        ResponseEntity<Object> response = restTemplate.exchange(
                CommonUtil.getUriStr(apiUrl + incomestatementUrl, financeFindVO)
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }
}
