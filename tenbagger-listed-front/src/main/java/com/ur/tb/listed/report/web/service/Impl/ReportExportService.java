package com.ur.tb.listed.report.web.service.Impl;

import java.util.List;

public interface ReportExportService {
    public void makepdfbulk(String email, List<String> isuSrtCds) throws Exception;
}
