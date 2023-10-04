package com.ur.tb.listed.test.web;

import com.ur.tb.listed.model.Sector;
import com.ur.tb.listed.model.Stock;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test1")
    public String test1() {
        System.out.println("aws test commit");
        try {
            ResponseEntity<List<Sector>> response = restTemplate.exchange(apiUrl+"/v1/sector"
                    , HttpMethod.GET, null, new ParameterizedTypeReference<List<Sector>>() {
                    });
            List<Sector> list = response.getBody();
            for(Sector sector : list) {
                log.info( sector.getSectorNo().toString());
            }
            return "Success";

        }catch (Exception e) {
            return CommonUtil.getPrintStackTrace(e);
        }
    }

    @GetMapping("/test2")
    public String test2() {
        try{
            //
            Map<String,String> params = new HashMap<>();
            params.put("pageNo","1");
            params.put("pageSize","5");

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl+"/v1/stk");
            for(Map.Entry<String,String> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(),entry.getValue());
            }

            ResponseEntity<List<Stock>> response = restTemplate.exchange(
                    builder.toUriString()
                    , HttpMethod.GET
                    , null
                    , new ParameterizedTypeReference<List<Stock>>() {
                    }
            );
            List<Stock> list = response.getBody();
            for(Stock stock : list) {
                log.info(stock.toString());
            }
            return "Success";
        }catch (Exception e) {
            return CommonUtil.getPrintStackTrace(e);
        }
    }
}
