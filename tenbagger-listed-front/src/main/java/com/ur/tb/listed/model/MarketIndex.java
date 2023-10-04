package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketIndex {

    private String isuSrtCd;

    private float trdPrc;

    private String cmpprevddTpCd;

    private float cmpprevddPrc;

    private int accTrdvol;

    private int accTrdval;

    private  String trdTm;

}
