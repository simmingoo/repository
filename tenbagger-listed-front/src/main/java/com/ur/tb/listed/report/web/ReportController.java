package com.ur.tb.listed.report.web;

import com.ur.tb.listed.model.findVO.ReportFindVO;
import com.ur.tb.listed.report.web.service.ReportService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Value("${tenbagger.front.url}")
    private String frontUrl;

    @RequestMapping("/index")
    public String index() {
        return "report/index";
    }
    @RequestMapping("/")
    public String root() {
        return "report/index";
    }

    @RequestMapping("/page/{pageNum}")
    public String reportView(@PathVariable("pageNum") String pageNum) {
        return "report/page"+pageNum;
    }

    @RequestMapping("/page/m/{pageNum}")
    public String reportMobileView(@PathVariable("pageNum") String pageNum) {
        return "report/m/page"+pageNum;
    }

    @RequestMapping("/page/p/{pageNum}")
    public String reportPcView(@PathVariable("pageNum") String pageNum) {
        return "report/p/page"+pageNum;
    }

    @RequestMapping("/page/cover")
    public String coverView() {
        return "report/cover";
    }
    @RequestMapping("/page/table")
    public String tableView() {
        return "report/table";
    }
    @RequestMapping("/page/end")
    public String endView() {
        return "report/end";
    }

    @RequestMapping("/page/m/cover")
    public String coverMobileView() {
        return "report/m/cover";
    }
    @RequestMapping("/page/m/table")
    public String tableMobileView() {
        return "report/m/table";
    }
    @RequestMapping("/page/m/end")
    public String endMobileView() {
        return "report/m/end";
    }

    @RequestMapping("/page/p/cover")
    public String coverPcView() {
        return "report/p/cover";
    }
    @RequestMapping("/page/p/table")
    public String tablePcView() {
        return "report/p/table";
    }
    @RequestMapping("/page/p/end")
    public String endPcView() {
        return "report/p/end";
    }

    @RequestMapping("/stockReport")
    public String stockReport(ModelMap model){
        model.addAttribute("frontUrl",frontUrl);
        return "report/stockReport";
    }
    
    @RequestMapping("/m-sector")
    public String sectorReport(ModelMap model){
        return "report/m-sector";
    }
    
    @RequestMapping("/m-theme")
    public String themeReport(ModelMap model){
        return "report/m-theme";
    }

    @ResponseBody
    @RequestMapping("/citation")
    public String citation(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.citation(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/competition")
    public String competition(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.competition(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/family")
    public String family(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.family(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/keyinventor")
    public String keyinventor(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.keyinventor(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/keypatent")
    public String keypatent(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.keypatent(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/keyword")
    public String keyword(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.keyword(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/outline")
    public String outline(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.outline(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/patentactivity")
    public String patentactivity(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.patentactivity(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/technology")
    public String technology(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.technology(reportFindVO));
    }

    @ResponseBody
    @RequestMapping("/transfer")
    public String transfer(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.transfer(reportFindVO));
    }
    @ResponseBody
    @RequestMapping("/stock")
    public String stock(ReportFindVO reportFindVO) throws Exception{
        return CommonUtil.getRtvStr(reportService.stock(reportFindVO));
    }
}

