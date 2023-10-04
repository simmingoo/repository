package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockIntraDay {

    private String isuSrtCd;

    private String prevTrdDd;

    private float prevTrdPrc;

    private List<StockIntraHisLists> hisLists;

}
