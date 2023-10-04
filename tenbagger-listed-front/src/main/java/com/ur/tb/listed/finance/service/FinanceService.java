package com.ur.tb.listed.finance.service;

import com.ur.tb.listed.model.findVO.FinanceFindVO;

public interface FinanceService {

    public Object balance(FinanceFindVO financeFindVO) throws  Exception;

    public Object fundamental(FinanceFindVO financeFindVO) throws  Exception;

    public Object cashflow(FinanceFindVO financeFindVO) throws  Exception;

    public Object incomestatement(FinanceFindVO financeFindVO) throws  Exception;
}
