package com.ur.tb.listed.report.web.service;

import com.ur.tb.listed.model.findVO.ReportFindVO;

public interface ReportService {

    public Object citation(ReportFindVO reportFindVO) throws  Exception;

    public Object competition(ReportFindVO reportFindVO) throws  Exception;

    public Object family(ReportFindVO reportFindVO) throws  Exception;

    public Object keyinventor(ReportFindVO reportFindVO) throws  Exception;

    public Object keypatent(ReportFindVO reportFindVO) throws  Exception;

    public Object keyword(ReportFindVO reportFindVO) throws  Exception;

    public Object outline(ReportFindVO reportFindVO) throws  Exception;

    public Object patentactivity(ReportFindVO reportFindVO) throws  Exception;

    public Object technology(ReportFindVO reportFindVO) throws  Exception;

    public Object transfer(ReportFindVO reportFindVO) throws  Exception;

    public Object stock(ReportFindVO reportFindVO) throws  Exception;
}
