package com.ur.tb.listed.finance.web;

import com.ur.tb.listed.finance.service.FinanceService;
import com.ur.tb.listed.model.findVO.FinanceFindVO;
import com.ur.tb.listed.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping(value="/balance")
    public String balance(FinanceFindVO financeFindVO) throws Exception {
        return CommonUtil.getRtvStr(financeService.balance(financeFindVO));
    }

    @GetMapping(value="/fundamental")
    public String fundamental(FinanceFindVO financeFindVO) throws Exception {
        return CommonUtil.getRtvStr(financeService.fundamental(financeFindVO));
    }

    @GetMapping(value="/cashflow")
    public String cashflow(FinanceFindVO financeFindVO) throws Exception {
        return CommonUtil.getRtvStr(financeService.cashflow(financeFindVO));
    }

    @GetMapping(value="/incomestatement")
    public String incomestatement(FinanceFindVO financeFindVO) throws Exception {
        return CommonUtil.getRtvStr(financeService.incomestatement(financeFindVO));
    }

}
