package com.ur.tb.listed.model.findVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockHistoryFindVO {
    /*조회종료일자 YYYYMMDD*/
    private String inqEndDd;
    /*조회시작일자 YYYYMMDD*/
    private String inqStrtDd;
    /*종목단축코드 (종목단축코드 또는 KOSPI:K1, KOSDAQ:Q1)*/
    private String issuecode;
    /*시장구분 (kospi | kosdaq | sector | etc)*/
    private String marketcode;
    /*요청건수 (최대 100건)*/
    private String reqCnt;
    /*전송주기구분코드 (D:일별, W:주별, M:월별)*/
    private String trnsmCycleTpCd;
}
