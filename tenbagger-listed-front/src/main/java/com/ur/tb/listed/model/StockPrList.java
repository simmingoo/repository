package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockPrList {

    private Long totalCnt;

    private List<Stocks> stks;
}
