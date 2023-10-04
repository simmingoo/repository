package com.ur.tb.listed.report.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sectorreport")
public class SectorReportController {

    @RequestMapping("/p/{sectorNo}")
    public String sectorReportPrintView(@PathVariable("sectorNo") String sectorNo) {
        return "report/SECTOR-p"+sectorNo;
    }

    @RequestMapping("/m/{sectorNo}")
    public String sectorReportMobileView(@PathVariable("sectorNo") String sectorNo) {
        return "report/SECTOR-m"+sectorNo;
    }

    @RequestMapping("/n/{sectorNo}")
    public String sectorReportNormalView(@PathVariable("sectorNo") String sectorNo) {
        return "report/SECTOR-"+sectorNo;
    }

    @RequestMapping("/admin/modify/main/{sectorNo}")
    public String sectorReportModifyMain(@PathVariable("sectorNo") String sectorNo, ModelMap model) {
        model.addAttribute("sectorNo", sectorNo);
        return "report/admin/SECTOR-Report-Modify-Main";
    }

    @RequestMapping("/admin/modify/{sectorNo}/{pageNo}")
    public String sectorReportAdminModifyPage(@PathVariable("sectorNo") String sectorNo,@PathVariable("pageNo") String pageNo, ModelMap model) {
        model.addAttribute("sectorNo", sectorNo);
        return "report/admin/SECTOR-Report-Modify-"+pageNo;
    }
    
    @RequestMapping("/page/cover")
    public String coverView() {
        return "report/sector/SECTOR-cover";
    }
    
    @RequestMapping("/page/end")
    public String endView() {
        return "report/sector/SECTOR-end";
    }    
    
    @RequestMapping("/page/{sectorNo}/{pageNo}")
    public String sectorreportView(@PathVariable("sectorNo") String sectorNo,@PathVariable("pageNo") String pageNo, ModelMap model) {
    	model.addAttribute("sectorNo", sectorNo);
        return "report/sector/SECTOR-"+pageNo;
    }
    
    @RequestMapping("/{sectorNo}")
    public String sectorreportView2(@PathVariable("sectorNo") String sectorNo, ModelMap model) {
    	model.addAttribute("sectorNo", sectorNo);
        return "report/sector/sectorReport";
    }

    @RequestMapping("/admin")
    public String tempAdmin() {
        return "report/admin/SECTOR-Admin";
    }

}
