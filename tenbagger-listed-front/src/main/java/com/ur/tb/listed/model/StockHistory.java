package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockHistory {

    private String isuSrtCd;

    private List<StockHistoryHisLists> hisLists;

}
