saveOutline = function () {

    let fullUrl,regDt, outline, issue, sourceLink;
    fullUrl = baseUrl+"/v1/report/sector";
    regDt = $("#regDt").val();
    outline = $("#outline").val();
    issue = $("#issue").val();
    sourceLink = $("#sourceLink").val();

    let data = {
        'outline' : outline,
        'issue' : issue,
        'sourceLink' : sourceLink,
        'sectorNo' : sectorNo
    }

    $.ajax({
        type:"put",
        url:fullUrl,
        data:data,
        success: function(data) {
            sectorOutline(sectorNo);
        },
        error : function(xhr) {

        },
        complete : function(data) {

        }
    });
}

saveSectorTrend = function () {
    let fullUrl, year, count;
    fullUrl = baseUrl + "/v1/report/sector/trend?sectorNo="+sectorNo
    let data = [];
    let viewCnt = $("#trend_select").val();
    for(let i = 0; i < viewCnt; i++) {
        year = $("#trend_year_div").children(".xa-10i").find("input[type=text]").eq(i).val()
        count = $("#trend_count_div").children(".xa-10i").find("input[type=text]").eq(i).val()
        data.push(getSectorTrendData(year,count));
    }
    $.ajax({
        type:"put",
        url:fullUrl,
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            sectorTrend(sectorNo);
        },
        error : function(xhr) {

        },
        complete : function(data) {

        }
    });
}

getSectorTrendData = function (year, count){
    let rtv = {
        'year' : year,
        'count' : count
    }
    return rtv;
}

saveSectorCycle = function() {
    let fullUrl, field, cycle, selYn;
    fullUrl = baseUrl + "/v1/report/sector/cycle?sectorNo="+sectorNo
    let data = [];
    let viewCnt = $("#cycle_select").val();
    for(let i = 0; i < viewCnt; i++) {
        selYn = $("#cycle_selyn_div").children(".xa-10i").find("input[type=radio]").eq(i).is(":checked");
        if(selYn) {
            selYn = "Y";
        } else {
            selYn = "N";
        }
        field = $("#cycle_field_div").children(".xa-10i").find("input[type=text]").eq(i).val()
        cycle = $("#cycle_div").children(".xa-10i").find("input[type=text]").eq(i).val()
        data.push(getSectorCycleData(field, cycle, selYn));
    }
    $.ajax({
        type:"put",
        url:fullUrl,
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            sectorCycle(sectorNo);
        },
        error : function(xhr) {

        },
        complete : function(data) {

        }
    });
}

getSectorCycleData = function (field, cycle, selYn){
    let rtv = {
        'field' : field,
        'cycle' : cycle,
        'selYn' : selYn
    }
    return rtv;
}

saveSectorMajorCompany = function () {
    let fullUrl, isuSrtCd, isuKorNm, count, viewCnt;
    fullUrl = baseUrl + "/v1/report/sector/major/company?sectorNo="+sectorNo
    let data = [];
    viewCnt  = $("#company_major_select").val();
    for(let i = 0; i < viewCnt; i++) {
        isuKorNm = $("#major_company_div").children(".xa-5i").find("input[type=text]").eq(i).val();
        isuSrtCd = $("#major_company_div").children(".xa-5i").find("input[type=text]").eq(i).attr('data-isuSrtCd');
        count = $("#major_company_count_div").children(".xa-5i").find("input[type=text]").eq(i).val();
        data.push(getSectorMajorCompanyData(isuSrtCd, isuKorNm, count, i+1));
    }

    $.ajax({
        type:"put",
        url:fullUrl,
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            sectorMajorCompany(sectorNo);
        },
        error : function(xhr) {

        },
        complete : function(data) {

        }
    });
}

getSectorMajorCompanyData = function(isuSrtCd, isuKorNm, count, orderNo) {
    let rtv = {
        'isuSrtCd' : isuSrtCd,
        'isuKorNm' : isuKorNm,
        'count' : count,
        'orderNo' : orderNo,
    }
    return rtv;
}

saveSectorMajorCompanyYear = function () {
    let fullUrl, isuSrtCd, isuKorNm, majorCompanyYearDivPrefix, viewCnt, year, count;
    let data = [];
    majorCompanyYearDivPrefix = 'm_c_y_';
    fullUrl = baseUrl+"/v1/report/sector/major/company/year?sectorNo="+sectorNo;
    for(let i = 1; i <= 5; i++) {
        let patentData = [];
        isuSrtCd = $("#"+majorCompanyYearDivPrefix+i).attr('data-isuSrtCd');
        isuKorNm = $("#"+majorCompanyYearDivPrefix+i).attr('data-isuKorNm');
        if(isuSrtCd != '' && isuKorNm != '') {
            viewCnt = $("#m_c_select_"+(i)).val();
            for(let k = 0; k < viewCnt; k++) {
                if(k < 10) {
                    year = $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_y_div_1").find("input[type=text]").eq(k).val();
                    count = $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_c_div_1").find("input[type=text]").eq(k).val();
                } else {
                    year = $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_y_div_2").find("input[type=text]").eq(k-10).val();
                    count = $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_c_div_2").find("input[type=text]").eq(k-10).val();
                }
                if(year != '' && count != '') {
                    patentData.push(getSectorMajorCompanyYearPatentData(year,count));
                }
            }
            data.push(getSectorMajorCompanyYearData(isuSrtCd,  isuKorNm,i,patentData));
        }
    }
    $.ajax({
        type:"put",
        url:fullUrl,
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: function(data) {
            sectorMajorCompanyYear(sectorNo);
        },
        error : function(xhr) {

        },
        complete : function(data) {

        }
    });
}

getSectorMajorCompanyYearData = function (isuSrtCd,  isuKorNm, orderNo, patentData) {
    let rtv = {
        "isuSrtCd": isuSrtCd,
        "isuKorNm": isuKorNm,
        "orderNo": orderNo,
        "patentRegist" : patentData
    }
    return rtv;
}
getSectorMajorCompanyYearPatentData = function (year, count){
    let rtv = {
        "year": year,
        "count": count
    }
    return rtv;
}
