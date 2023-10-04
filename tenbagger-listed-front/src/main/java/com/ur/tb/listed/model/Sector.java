package com.ur.tb.listed.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Sector {

    private Long sectorNo;

    private String sectorName;

    private String displayYn;

    private String issueYn;

    private Long orderNo;

    private Long themeCnt;

    private Long stkCnt;

    private Long rcmnStkCnt;
}
