package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockHistoryHisLists {

    private String trdDd;

    private int trdPrc;

    private String cmpprevddTpCd;

    private float cmpprevddPrc;

    private Long accTrdvol;

    private Long accTrdval;

    private float opnprc;

    private float hgprc;

    private float lwprc;

    private float cmpprevddTpPrct;
}
