package com.ur.tb.listed.tech.theme.web;

import com.ur.tb.listed.model.findVO.ThemePrFindVO;
import com.ur.tb.listed.tech.theme.service.ThemeRankingService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/theme")
public class ThemeRankingController {

    @Autowired
    private ThemeRankingService themeRankingService;

    @GetMapping("/pr")
    public String themePr(ThemePrFindVO themePrFindVO) throws  Exception{
        return CommonUtil.getRtvStr(themeRankingService.themePr(themePrFindVO));
    }


}
