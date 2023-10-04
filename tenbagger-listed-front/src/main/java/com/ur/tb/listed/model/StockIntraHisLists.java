package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockIntraHisLists {

    private String inddTm;

    private float inddOpnprc;

    private float inddHgprc;

    private float inddLwprc;

    private float inddClsprc;

    private int inddTrdvol;

    private int inddTrdval;

    private int inddAccTrdvol;

    private int inddAccTrdval;

    private float cmpprevddInddPrice;

    private float cmpprevddInddPrct;

}
