package com.ur.tb.listed.model.findVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockFindVO {

    private String pageNo;

    private String pageSize;

    private String searchSectorNo;

    private String searchThemeNo;

    private String searchWrd;

    private String sortColumn;

    private String sortOrdr;

}
