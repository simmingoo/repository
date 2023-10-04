package com.ur.tb.listed.tech.sector.web;

import com.ur.tb.listed.model.findVO.SectorFindVO;
import com.ur.tb.listed.tech.sector.web.service.SectorDataService;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sector")
public class SectorDataController {

    @Autowired
    private SectorDataService sectorDataService;

    @GetMapping(value = "/list")
    public String sectorList() {
        return CommonUtil.objectToJSONArray(sectorDataService.sectorList()).toString().replaceAll("분\\\\","");
    }

    @GetMapping(value ="/theme/list/{sectorNo}")
    public String themeList(@PathVariable("sectorNo") String sectorNo ) {
        return CommonUtil.objectToJSONArray(sectorDataService.themeList(sectorNo)).toString().replaceAll("\\\\","");
    }

    @GetMapping(value="/cnt")
    public String sectorAllCnt() throws Exception{
        return sectorDataService.sectorAllCnt();
    }

    @GetMapping(value = "/pr")
    public String sectorPr(SectorFindVO sectorFindVO) throws Exception{
        return CommonUtil.getRtvStr(sectorDataService.sectorPr(sectorFindVO));
    }

}
