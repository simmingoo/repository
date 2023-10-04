package com.ur.tb.listed.tech.sector.web.service;

import com.ur.tb.listed.model.findVO.SectorFindVO;

public interface SectorDataService {

    public Object sectorList();

    public Object themeList(String sectorNo);

    public String sectorAllCnt() throws Exception;

    public Object sectorPr(SectorFindVO sectorFindVO) throws Exception;
}
