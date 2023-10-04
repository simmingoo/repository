package com.ur.tb.listed.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Stock {

    private  String isuCd; // 종목코드

    private String isuSrtCd; //종목 단축코드

    private String isuKorNm; //종목한글명

    private String kiscode; //업체코드

    private String mktCd; // 시장구분코드

    private Corp corp; //회사정보

}
