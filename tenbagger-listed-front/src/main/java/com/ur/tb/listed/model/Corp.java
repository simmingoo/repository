package com.ur.tb.listed.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Corp {

    private Long corpNo;//기업번호

    private String bizno;//사업자번호

    private String crpno;//법인등록번호

    private String stkcd;//주식코드

    private String korentrnm;//업체명

    private String optEntrnm;//정제업체명(약식업체명)

    private String korreprnm;//대표자명

    private String idscdid;//산업분류코드

    private String idscd;//산업코드

    private String korIdscdnm;//산업코드명

    private String scl;//기업규모명

    private String ltgmktdivcd;//상장시장구분코드(11 : KOSPI, 12 : KOSDAQ, 14 : KONEX, 15 : ETC)

   private String obzDate;//개업일자

    private String ltgDate;//상장일자

    private String empnum;//종업원수

    private String stacmm;//결산월

    private String grpnm;//그룹코드명

    private String mainupche;//그룹주력업체명

    private String koraddr;//도로명주소

    private String zarcd;//신우편번호

    private String noltKoraddr;//지번주소

    private String zcd;//우편번호

    private String tel;//전화번호

    private String fax;//팩스번호

    private String mainpdtpcl;//주요상품내역

    private String homepurl;//홈페이지

    private String bnknm;//주거래은행명

    private String logossl;//로그ssl url

    private String korItemnm;//종목명

    private String bzdnm;//업태명

    private String rbtpnm;//업태

    private String engBnknm;//영문 주거래은행명

    private String prtGrd;//PTR등급

    private float tii;//기술투자지수

    private Long tir;//기술투자랭킹

    private float tss;//TSS

    private String tcg;//TCG

    private float apes;//APES

    private Long prCnt;//특허등록건수

    private Long mktCap;//시가총액

    private String assdValue;//기업가치 추정

    private  Theme theme;//소속테마

}
