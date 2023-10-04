const baseUrl = 'https://c-api.10bagger.co.kr';

sectorOutline = function (sectorNo) {
    let fullUrl = baseUrl + "/v1/report/sector/"+sectorNo;
    $.ajax({
        type:"get",
        url:fullUrl,
        async : false,
        contentType: "application/json",
        dataType: "json",
        success:function(data) {
            sectorOutlineCallBack(data);
        },
        error:function (xhr) {

        },
        complete:function (xhr) {

        }
    });
}

sectorOutlineCallBack = function (data) {
    sectorName = data[0].sectorName;
    $("#sectorName").text(data[0].sectorName);
    $("#outline_div").html(data[0].outline);
    $("#outline").val(data[0].outline);
    $("#issue_div").html(data[0].issue);
    $("#issue").val(data[0].issue);
    $("#sourceLink_a").attr("href",data[0].sourceLink);
    $("#sourceLink_a").text(data[0].sourceLink);
    $("#sourceLink").val(data[0].sourceLink);
}

sectorTrend = function (sectorNo) {
    let fullUrl = baseUrl + "/v1/report/sector/trend/"+sectorNo;
    $.ajax({
        type:"get",
        url:fullUrl,
        async : false,
        contentType: "application/json",
        dataType: "json",
        success:function(data) {
            sectorTrendCallBack(data);
            sectorTrendChartCallBack(data);
        },
        error:function (xhr) {

        },
        complete:function (xhr) {

        }
    });
}

sectorTrendCallBack = function (data) {
    $("#trend_year_div").html("");
    $("#trend_count_div").html("");
    let innerHtml, trend_desc_1, trend_desc_2;
    if(data.length != 0) {
        $("#trend_select").val(data.length).prop("selected",true);
        trend_desc_1 = "지난 "+data.length+"년간 "+sectorName+" 분야의 특허 등록 동향을 표시합니다."
        trend_desc_2 = "최근 "+data.length+"년간 특허 등록 동향"
        $("#trend_desc_div_1").text(trend_desc_1);
        $("#trend_desc_div_2").text(trend_desc_2);
    }

    $.each(data, function (index, item) {
        if(index == 0) {
            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>연도</span>';
            innerHtml += '</div>';
            $("#trend_year_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>특허갯수</span>';
            innerHtml += '</div>';
            $("#trend_count_div").append(innerHtml);
        }

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" value="'+item.year+'">';
        innerHtml += '</div>';
        $("#trend_year_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" value="'+item.count+'">';
        innerHtml += '</div>';
        $("#trend_count_div").append(innerHtml);
    });


    if(data.length < 10) {
        for(let i = 0; i < 10-data.length; i++) {
            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
            innerHtml += '</div>';
            $("#trend_year_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
            innerHtml += '</div>';
            $("#trend_count_div").append(innerHtml);
        }
    }
}

sectorTrendChartCallBack = function (data) {
    let categories = [];
    let seriesData = [];
    $.each(data,function (index,item) {
        categories.push(item.year);
        seriesData.push(item.count);
    })
    Highcharts.chart('container1', {
        chart: {
            backgroundColor:'rgba(255, 255, 255, 0)',
            plotBorderWidth: 1,
            events: {
                load: function() {
                    var chart = this;
                    var bottom = chart.plotHeight+15;

                    $.each(chart.series[0].data,function(i,data){

                    });

                }
            }
        },

        title: {
            text: ''
        },

        exporting: {
            enabled: false
        },
        credits: {enabled: false},

        subtitle: {
            text: ''
        },

        yAxis: {
            min:2000,
            max:8000,
            tickInterval:1000,
            title: {
                text: ''
            },
            labels:{
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        xAxis: {
            categories:categories,
            labels: {
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        plotOptions: {
            series: {
                states: {
                    inactive: {
                        opacity: 1
                    },
                    hover:{
                        enabled:true
                    },
                },
                events: {
                    legendItemClick: function() {
                        return false;
                    }
                }
            }
        },

        series: [{
            name: sectorName+'섹터',
            label: {
                enabled: false
            },
            marker: {
                enabled: true,
                symbol: 'circle',
                radius: 3,
            },
            color: '#AE2318',
            data: seriesData,
            dataLabels: {
                enabled: true,
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold',
                    textOutline:'0px'
                }
            }
        }],
    });
}

sectorCycle = function(sectorNo) {
    let fullUrl = baseUrl + "/v1/report/sector/cycle/"+sectorNo;
    $.ajax({
        type:"get",
        url:fullUrl,
        async : false,
        contentType: "application/json",
        dataType: "json",
        success:function(data) {
            sectorCycleCallBack(data);
            sectorCycleChartCallBack(data);
        },
        error:function (xhr) {

        },
        complete:function (xhr) {

        }
    });
}

sectorCycleCallBack = function(data) {
    $("#cycle_selyn_div").html("");
    $("#cycle_field_div").html("");
    $("#cycle_div").html("");
    let innerHtml,cycle_desc_1, cycle_desc_2;
    if(data.length != 0) {
        $("#cycle_select").val(data.length).prop("selected",true);
        cycle_desc_1 = sectorName+" - 분야 기술 주기";
        cycle_desc_2 = sectorName+" 시장에서 개별 기술의 평균적인 유효기간입니다.<br>기술 주기가 짧을수록 기술변화 속도가 빠르다고 볼 수 있습니다.";
        $("#cycle_desc_1").text(cycle_desc_1);
        $("#cycle_desc_2").text(cycle_desc_2);
    }

    $.each(data, function (index, item) {
        if(index == 0) {
            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>해당분야</span>';
            innerHtml += '</div>';
            $("#cycle_selyn_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>분야</span>';
            innerHtml += '</div>';
            $("#cycle_field_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>기술주기</span>';
            innerHtml += '</div>';
            $("#cycle_div").append(innerHtml);
        }
        innerHtml = "";
        innerHtml += '<div class="xa-10i xl-flex-row xl-center">';
        if(item.selYn == 'Y') {
            innerHtml += '   <input type="radio" name="cycle_sel_group" checked>';
            $("#sectorCycleVal").val(item.cycle);
        } else {
            innerHtml += '   <input type="radio" name="cycle_sel_group">';
        }
        innerHtml += '</div>';
        $("#cycle_selyn_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" value="'+item.field+'">';
        innerHtml += '</div>';
        $("#cycle_field_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" value="'+item.cycle+'">';
        innerHtml += '</div>';
        $("#cycle_div").append(innerHtml);
    });

    if(data.length < 10) {
        for(let i = 0; i < 10-data.length; i++) {
            innerHtml = "";
            innerHtml += '<div class="xa-10i xl-flex-row xl-center">';
            innerHtml += '   <input type="radio" name="cycle_sel_group" disabled>';
            innerHtml += '</div>';
            $("#cycle_selyn_div").append(innerHtml);


            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
            innerHtml += '</div>';
            $("#cycle_field_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
            innerHtml += '</div>';
            $("#cycle_div").append(innerHtml);
        }
    }

}

sectorCycleChartCallBack = function(data) {
    let categories = [];
    let seriesData = [];
    $.each(data,function (index,item) {
        categories.push(item.field);
        seriesData.push(getSectorCycleChartData(item.cycle,item.selYn));
    });

    Highcharts.chart('container2', {
        chart: {
            type: 'column',
            spacing:[5.5, 6.0, 6.5, 7.1, 7.5, 8.0, 8.5, 9.0],
            backgroundColor:'rgba(255, 255, 255, 0)',
            plotBorderWidth: 1
        },

        title: {
            text: ''
        },

        exporting: {
            enabled: false
        },
        credits: {enabled: false},

        subtitle: {
            text: ''
        },

        yAxis: {
            min: 4.5,
            tickInterval: 0.5,
            title: {
                text: ''
            },
            labels:{
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        xAxis: {
            categories:categories,
            labels: {
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        plotOptions: {
            series: {
                states: {
                    inactive: {
                        opacity: 1
                    },
                    hover:{
                        enabled:false
                    },
                },
                events: {
                    legendItemClick: function() {
                        return false;
                    }
                }
            }
        },

        series: [{
            name: '업종별 기술 주기(년)',
            color: '#253E60',
            data:seriesData,
            dataLabels: {
                enabled: false,
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold',
                    textOutline:'0px'
                }
            }
        }
        ],

    });
}

getSectorCycleChartData = function(cycle, selYn) {
    let rtv = {
        y: cycle,
        dataLabels: {
            enabled: true,
            format: '{y}',
            align: 'center',
            verticalAlign: 'top',
            color: '#ffffff',
            x: 0,
            y: 0
        }
    }
    if(selYn == 'Y') rtv.color = '#AE2318';
    return rtv;
}

changeTrendSelect = function(len) {

    let innerHtml = "";

    $("#trend_year_div").html("");
    $("#trend_count_div").html("");

    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>연도</span>';
    innerHtml += '</div>';
    $("#trend_year_div").append(innerHtml);

    innerHtml = "";
    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>특허갯수</span>';
    innerHtml += '</div>';
    $("#trend_count_div").append(innerHtml);

    for(let i = 0; i < 10; i++) {
        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        }else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#trend_year_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        } else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#trend_count_div").append(innerHtml);
    }
}

changeCycleSelect = function(len) {
    let innerHtml = "";
    $("#cycle_selyn_div").html("");
    $("#cycle_field_div").html("");
    $("#cycle_div").html("");

    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>해당분야</span>';
    innerHtml += '</div>';
    $("#cycle_selyn_div").append(innerHtml);

    innerHtml = "";
    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>분야</span>';
    innerHtml += '</div>';
    $("#cycle_field_div").append(innerHtml);

    innerHtml = "";
    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>기술주기</span>';
    innerHtml += '</div>';
    $("#cycle_div").append(innerHtml);

    for(let i = 0; i < 10; i++) {
        innerHtml = "";
        innerHtml += '<div class="xa-10i xl-flex-row xl-center">';
        innerHtml += '   <input type="radio" name="cycle_sel_group">';
        innerHtml += '</div>';
        $("#cycle_selyn_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        } else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#cycle_field_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-10i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        }else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#cycle_div").append(innerHtml);
    }
}

sectorMajorCompany = function(sectorNo) {
    let fullUrl = baseUrl + "/v1/report/sector/major/company/"+sectorNo;
    $.ajax({
        type:"get",
        url:fullUrl,
        async : false,
        contentType: "application/json",
        dataType: "json",
        success:function(data) {
            sectorMajorCompanyCallBack(data);
            sectorMajorCompanyChartCallBack(data);
        },
        error:function (xhr) {

        },
        complete:function (xhr) {

        }
    });
}

sectorMajorCompanyCallBack = function(data) {
    $("#company_major_select").val(data.length).prop("selected",true);
    $("#major_company_div").html("");
    $("#major_company_count_div").html("");
    let innerHtml;
    $.each(data,function (index,item) {
        if (index == 0) {
            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>주요기업</span>';
            innerHtml += '</div>';
            $("#major_company_div").append(innerHtml);

            innerHtml = "";
            innerHtml += '<div class="xa-10i">';
            innerHtml += '  <span>특허갯수</span>';
            innerHtml += '</div>';
            $("#major_company_count_div").append(innerHtml);
        }

        innerHtml = "";
        innerHtml += '<div class="xa-5i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" data-isuSrtCd="'+item.isuSrtCd+'" value="'+item.isuKorNm+'">';
        innerHtml += '</div>';
        $("#major_company_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-5i">';
        innerHtml += '   <input type="text" class="xl-note-1-input" value="'+item.count+'">';
        innerHtml += '</div>';
        $("#major_company_count_div").append(innerHtml);
    });

}

sectorMajorCompanyChartCallBack = function(data) {
    let categories = [];
    let seriesData = [];
    $.each(data,function (index,item) {
        categories.push(item.isuKorNm);
        seriesData.push(getMajorCompanyChartData(item.count));
    });
    Highcharts.chart('container3', {
        chart: {
            type: 'bar',
            backgroundColor:'rgba(255, 255, 255, 0)',
            plotBorderWidth: 1
        },

        title: {
            text: ''
        },

        exporting: {
            enabled: false
        },
        credits: {enabled: false},

        subtitle: {
            text: ''
        },

        legend: {
            enabled:false
        },

        yAxis: {
            min: 0,
            tickInterval: 1000,
            title: {
                text: ''
            },
            labels:{
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        xAxis: {
            categories:categories,
            labels: {
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        plotOptions: {
            series: {
                states: {
                    inactive: {
                        opacity: 1
                    },
                    hover:{
                        enabled:false
                    },
                },
                events: {
                    legendItemClick: function() {
                        return false;
                    }
                }
            }
        },

        series: [{
            name: '특허보유건수',
            color: '#416595',
            data: seriesData,
            dataLabels: {
                enabled: false,
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold',
                    textOutline:'0px'
                }
            }
        }],
    });
}

getMajorCompanyChartData = function (count) {
    let rtv = {
        y: count,
        dataLabels: {
            enabled: true,
            format: '{y}',
            align: 'right',
            verticalAlign: 'top',
            x: 30,
            y: -3,
        }
    }
    return rtv;
}

changeCompanyMajorSelect = function(len) {
    let innerHtml;
    $("#major_company_div").html("");
    $("#major_company_count_div").html("");

    innerHtml = "";
    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>주요기업</span>';
    innerHtml += '</div>';
    $("#major_company_div").append(innerHtml);

    innerHtml = "";
    innerHtml += '<div class="xa-10i">';
    innerHtml += '  <span>특허갯수</span>';
    innerHtml += '</div>';
    $("#major_company_count_div").append(innerHtml);

    for(let i = 0; i < 5; i++) {
        innerHtml = "";
        innerHtml += '<div class="xa-5i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        } else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#major_company_div").append(innerHtml);

        innerHtml = "";
        innerHtml += '<div class="xa-5i">';
        if(i < len) {
            innerHtml += '   <input type="text" class="xl-note-1-input">';
        } else {
            innerHtml += '   <input type="text" class="xl-note-1-input" disabled>';
        }
        innerHtml += '</div>';
        $("#major_company_count_div").append(innerHtml);
    }
}

sectorMajorCompanyYear = function(sectorNo) {
    let fullUrl = baseUrl + "/v1/report/sector/major/company/year/"+sectorNo;
    $.ajax({
        type:"get",
        url:fullUrl,
        async : false,
        contentType: "application/json",
        dataType: "json",
        success:function(data) {
            sectorMajorCompanyYearCallBack(data);
            sectorMajorCompanyYearChartCallBack(data);
        },
        error:function (xhr) {

        },
        complete:function (xhr) {

        }
    });
}

sectorMajorCompanyYearCallBack = function(data) {
    let innerHtml, majorCompanyYearDivPrefix;
    majorCompanyYearDivPrefix = 'm_c_y_'
    for(let i = 1; i <= 5; i++) {
        $("#"+majorCompanyYearDivPrefix+i).html("");
        innerHtml = "";
        innerHtml += '<div id="m_c_y_p_y_div_1" class="xa-col xl-flex-row xl-center" style="margin-bottom:2px"></div>';
        $("#"+majorCompanyYearDivPrefix+i).append(innerHtml);
        $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_y_div_1").append(getMajorCompanyYearPatentForm('연도'))

        innerHtml = "";
        innerHtml += '<div id="m_c_y_p_c_div_1" class="xa-col xl-flex-row xl-center" style="margin-top:2px;margin-bottom:2px">';
        $("#"+majorCompanyYearDivPrefix+i).append(innerHtml);
        $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_c_div_1").append(getMajorCompanyYearPatentForm('특허갯수'))

        innerHtml = "";
        innerHtml += '<div id="m_c_y_p_y_div_2" class="xa-col xl-flex-row xl-center" style="margin-top:2px;margin-bottom:2px">';
        $("#"+majorCompanyYearDivPrefix+i).append(innerHtml);
        $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_y_div_2").append(getMajorCompanyYearPatentForm('연도'))

        innerHtml = "";
        innerHtml += '<div id="m_c_y_p_c_div_2" class="xa-col xl-flex-row xl-center" style="margin-top:2px">';
        $("#"+majorCompanyYearDivPrefix+i).append(innerHtml);
        $("#"+majorCompanyYearDivPrefix+i).children("#m_c_y_p_c_div_2").append(getMajorCompanyYearPatentForm('특허갯수'))
    }

    $.each(data,function (indexData, item) {
        item.patentRegist.sort(function (a,b) {
            return parseFloat(a.year) - parseFloat(b.year);
        });

        $("#m_c_select_"+(indexData+1)).val(item.patentRegist.length).prop('selected',true);

        $("#"+majorCompanyYearDivPrefix+(indexData+1)).attr('data-isuSrtCd',item.isuSrtCd);
        $("#"+majorCompanyYearDivPrefix+(indexData+1)).attr('data-isuKorNm',item.isuKorNm);

        $.each(item.patentRegist,function (index,item) {
            if(index < 10) {
                $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_y_div_1").find("input[type=text]").eq(index).val(item.year);
                $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_c_div_1").find("input[type=text]").eq(index).val(item.count);
            } else {
                $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_y_div_2").find("input[type=text]").eq(index-10).val(item.year);
                $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_c_div_2").find("input[type=text]").eq(index-10).val(item.count);
            }
        });

        if(item.patentRegist.length < 20) {
            $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_y_div_1").find("input[type=text]").each(function () {
                if($(this).val() == '') {
                    $(this).attr('disabled',true);
                }
            });
            $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_c_div_1").find("input[type=text]").each(function () {
                if($(this).val() == '') {
                    $(this).attr('disabled',true);
                }
            });
            $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_y_div_2").find("input[type=text]").each(function () {
                if($(this).val() == '') {
                    $(this).attr('disabled',true);
                }
            });
            $("#"+majorCompanyYearDivPrefix+(indexData+1)).children("#m_c_y_p_c_div_2").find("input[type=text]").each(function () {
                if($(this).val() == '') {
                    $(this).attr('disabled',true);
                }
            });
        }
    });
}

sectorMajorCompanyYearChartCallBack = function(data) {
    let categories;
    let seriesData;
    $.each(data,function (index, item) {
        categories = [];
        seriesData = [];
        item.patentRegist.sort(function (a,b) {
            return parseFloat(a.year) - parseFloat(b.year);
        });

        $.each(item.patentRegist,function (index,item) {
            categories.push(item.year);
            seriesData.push(getMajorCompanyYearChartSeriesData(item));
        });
        Highcharts.chart('m_c_y_c_div_'+(index+1),
            getMajorCompanyYearChartData(item.isuKorNm,categories,seriesData)
        )
    });
}


getMajorCompanyYearChartData = function(companyName, categories, seriesData){
    let rtv = {
        chart: {
            type: 'column',
            backgroundColor:'rgba(255, 255, 255, 0)',
            plotBorderWidth: 1
        },

        title: {
            text: companyName,
            style: {
                fontFamily: 'Noto Sans KR',
                fontSize: '12px',
                color: '#191919',
                fontWeight:'normal'
            }
        },

        exporting: {
            enabled: false
        },
        credits: {enabled: false},

        subtitle: {
            text: ''
        },

        legend: {
            enabled:false
        },

        yAxis: {
            min: 0,
            max: 5000,
            tickInterval: 1000,
            title: {
                text: ''
            },
            labels:{
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        xAxis: {
            categories:categories,
            labels: {
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold'
                }
            }
        },

        plotOptions: {
            series: {
                states: {
                    inactive: {
                        opacity: 1
                    },
                    hover:{
                        enabled:false
                    },
                },
                events: {
                    legendItemClick: function() {
                        return false;
                    }
                }
            }
        },

        series: [{
            name: '특허보유건수',
            color: '#5379BE',
            data: seriesData,
            dataLabels: {
                enabled: false,
                style: {
                    fontFamily: 'Noto Sans KR',
                    fontSize: '8px',
                    color: '#191919',
                    fontWeight:'bold',
                    textOutline:'0px'
                }
            }
        }],
    };

    return rtv;
}


getMajorCompanyYearChartSeriesData = function (item) {
    let rtv = {
        name: item.year,
        y: item.count,
        dataLabels: {
            enabled: true,
            format: '{y}',
            align: 'center',
            verticalAlign: 'top',
            x: 0,
            y: -20,
        }
    }
    return rtv;
}

getMajorCompanyYearPatentForm = function(spanText) {
    let rtvHtml;
    rtvHtml = "";
    rtvHtml += '<div class="xa-10i">';
    rtvHtml += '    <span>'+spanText+'</span>';
    rtvHtml += '</div>';
    for(let i = 0; i < 10; i++) {
        rtvHtml += '<div class="xa-10i">';
        rtvHtml += '    <input type="text" class="xl-note-1-input">';
        rtvHtml += '</div>';
    }
    return rtvHtml;
}
changeMajorCompanyYearPatentSelect = function (id,len) {
    let innerHtml, index, majorCompanyYearDivPrefix;
    majorCompanyYearDivPrefix = 'm_c_y_'
    index = id.charAt(id.length-1);
    $("#"+majorCompanyYearDivPrefix+index).html("");

    innerHtml = "";
    innerHtml += '<div id="m_c_y_p_y_div_1" class="xa-col xl-flex-row xl-center" style="margin-bottom:2px"></div>';
    $("#"+majorCompanyYearDivPrefix+index).append(innerHtml);
    $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_y_div_1").append(getMajorCompanyYearPatentForm('연도'))

    innerHtml = "";
    innerHtml += '<div id="m_c_y_p_c_div_1" class="xa-col xl-flex-row xl-center" style="margin-top:2px;margin-bottom:2px">';
    $("#"+majorCompanyYearDivPrefix+index).append(innerHtml);
    $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_c_div_1").append(getMajorCompanyYearPatentForm('특허갯수'))

    innerHtml = "";
    innerHtml += '<div id="m_c_y_p_y_div_2" class="xa-col xl-flex-row xl-center" style="margin-top:2px;margin-bottom:2px">';
    $("#"+majorCompanyYearDivPrefix+index).append(innerHtml);
    $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_y_div_2").append(getMajorCompanyYearPatentForm('연도'))

    innerHtml = "";
    innerHtml += '<div id="m_c_y_p_c_div_2" class="xa-col xl-flex-row xl-center" style="margin-top:2px">';
    $("#"+majorCompanyYearDivPrefix+index).append(innerHtml);
    $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_c_div_2").append(getMajorCompanyYearPatentForm('특허갯수'))

    if(0 < len && len <= 10) {
        for(let i = len; i < 10; i++) {
            $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_y_div_1").find("input[type=text]").eq(i).attr('disabled',true);
            $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_c_div_1").find("input[type=text]").eq(i).attr('disabled',true);
        }

        $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_y_div_2").find("input[type=text]").each(function () {
            $(this).attr('disabled',true);
        });

        $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_c_div_2").find("input[type=text]").each(function () {
            $(this).attr('disabled',true);
        });
    } else {
        for(let i = len-10; i < 10; i++) {
            $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_y_div_2").find("input[type=text]").eq(i).attr('disabled',true);
            $("#"+majorCompanyYearDivPrefix+index).children("#m_c_y_p_c_div_2").find("input[type=text]").eq(i).attr('disabled',true);
        }
    }
}

autoComplete = function (request, response) {
    let fullUrl, val;
    let stockList = [];
    fullUrl = baseUrl +"/v1/stk?pageNo=1&pageSize=20&searchWrd="
    val = request.term;
    if(val.length > 1) {
        fullUrl = fullUrl+val;
        $.ajax({
            type:"get",
            url:fullUrl,
            async : false,
            contentType: "application/json",
            dataType: "json",
            success:function(data) {
                response(
                    $.map(data.stks,function (item) {
                        return {
                            label:item.isuKorNm,
                            value:item.isuSrtCd
                        }
                    })
                )
            },
            error:function (xhr) {

            },
            complete:function (xhr) {

            }
        });
    }
}

getAutoCompleteData = function (isuSrtCd, isuKorNm) {
    let rtv = {
        'isuSrtCd' : isuSrtCd,
        'isuKorNm' : isuKorNm
    }
    return rtv;
}

sectorRecommendStock = function() {
    let fullUrl, searchStkCds;
    fullUrl = baseUrl +"/v1/stk";
    searchStkCds = '079370,053610,195870,005930,227950';
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 1, pageSize: 5, searchStkCds: searchStkCds, sortColumn: 'TII', sortOrdr: 'ASC'},
        dataType: 'json',
        success: function(data) {
            sectorRecommendStockCallBack(data);
        }
    });
}

sectorRecommendStockCallBack = function (data) {
    for( var i=0; i<5; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 5 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            }
        } else {
            if( (i+1) == 5 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            }
        }
    }
}













sectorRecommendStock1 = function() {
    let fullUrl, searchStkCds;
    fullUrl = baseUrl +"/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 1, pageSize: 5, searchSectorNo: sectorNo, sortColumn: 'TII', sortOrdr: 'ASC'},
        dataType: 'json',
        success: function(data) {
            sectorRecommendStockCallBack1(data);
        }
    });
}

sectorRecommendStockCallBack1 = function (data) {
    for( var i=0; i<5; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 5 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            }
        } else {
            if( (i+1) == 5 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list1-1").append(htmlStr);
            }
        }
    }
}




sectorTssRankStockList = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 1, pageSize: 1000, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack(data);
        }
    });
}

sectorTssRankStockListCallBack = function (data) {
    for( var i=0; i<data.totalCnt; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == data.totalCnt ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list2-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list2-1").append(htmlStr);
            }
        } else {
            if( (i+1) == data.totalCnt ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list2-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list2-1").append(htmlStr);
            }
        }
    }
}








sectorTssRankStockList1 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 1, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack1(data);
        }
    });
}

sectorTssRankStockListCallBack1 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_1").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-1").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-1").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-1").append(htmlStr);
            }
        }
    }
}



sectorTssRankStockList2 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 2, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack2(data);
        }
    });
}

sectorTssRankStockListCallBack2 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_2").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-2").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-2").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-2").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-2").append(htmlStr);
            }
        }
    }
}

sectorTssRankStockList3 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 3, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack3(data);
        }
    });
}

sectorTssRankStockListCallBack3 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_3").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-3").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-3").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-3").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-3").append(htmlStr);
            }
        }
    }
}

sectorTssRankStockList4 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 4, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack4(data);
        }
    });
}

sectorTssRankStockListCallBack4 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_4").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-4").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-4").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-4").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-4").append(htmlStr);
            }
        }
    }
}

sectorTssRankStockList5 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 5, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack5(data);
        }
    });
}

sectorTssRankStockListCallBack5 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_5").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-5").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-5").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-5").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-5").append(htmlStr);
            }
        }
    }
}

sectorTssRankStockList6 = function () {
    let fullUrl;
    fullUrl = baseUrl + "/v1/stk";
    $.ajax({
        type: 'GET',
        url: fullUrl,
        data: {pageNo: 6, pageSize: 24, searchSectorNo: sectorNo, sortColumn: 'TSS', sortOrdr: 'DESC'},
        dataType: 'json',
        success: function(data) {
            sectorTssRankStockListCallBack6(data);
        }
    });
}

sectorTssRankStockListCallBack6 = function (data) {
	if (data.stks.length == 0) {
		$(".xl-contents#page5_6").css("display", "none");
	}
    for( var i=0; i<24; i++ ) {
        if( (i%2) == 0 ) {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-6").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-6").append(htmlStr);
            }
        } else {
            if( (i+1) == 24 ) {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-bottom:red solid 1px;border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-6").append(htmlStr);
            } else {
                var htmlStr = "<div class='xl-st-row'>"
                    + " <div class='xl-str-1 xl-flex-col xl-center xl-str-g'>" + data.stks[i].isuKorNm + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-right:none'>" + ((data.stks[i].corp.tii).toFixed(2)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='border-left:red solid 1px;border-right:red solid 1px;text-align:center'>" + ((data.stks[i].corp.tss).toFixed(1)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.tcg + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.apes).toFixed(1) + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.patentCount + "</div>"
                    + " <div class='xl-str-2 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + (data.stks[i].corp.mktCap).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "</div>"
                    + " <div class='xl-str-3 xl-flex-col xl-center xl-str-g' style='text-align:center'>" + data.stks[i].corp.assdValue + "</div>"
                    + "</div>";
                $("#_sector_list3-6").append(htmlStr);
            }
        }
    }
}