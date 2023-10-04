<template>
	<div class="container">
		<div class="inner">
			<div class="top_reco">
				<div class="unit">
					<div class="bx_info">
						<strong class="txt_hlight">KOSPI</strong>
            <div class="desc" :class="kospiUpDown">
							<strong ref="kospiTrdPrc">3,031.19</strong><span></span>
							<div class="mt15"><span ref="kospiCmpprevddPrc">▼17.89</span> <span ref="kospiRatio">-0.59%</span></div><!--가로 400픽셀까지 키울수있습니다 -->
            </div>
					</div>
					<div class="bx_chart">
						<div class="btns">
							<button v-on:click="kospi1d" type="button" ref="kospi1d_b">1일</button>
							<button v-on:click="kospi3m" type="button" ref="kospi3m_b">3개월</button>
							<button v-on:click="kospi1y" type="button" ref="kospi1y_b">1년</button>
						</div>
						<div class="inner_chart"> <!-- 상위 클래스와 겹치면 안되서 클래스명 변경했습니다 -->
							<highcharts :constructorType="'stockChart'" class="hc" :options="chartOptions" ref="kospic"></highcharts>
						</div>
					</div>
				</div>
				<div class="unit">
					<div class="bx_info">
						<strong class="txt_hlight">KOSDAQ</strong>
						<div class="desc" :class="kosDaqUpDown">
							<strong ref="kosdaqTrdPrc">3,031.19</strong><span></span><br/>
							<div class="mt15"><span ref="kosdaqCmpprevddPrc">▲17.89</span> <span ref="kosdaqRatio">+0.59%</span></div>
						</div>
					</div>
					<div class="bx_chart">
						<div class="btns">
							<button v-on:click="kosdaq1d" type="button" ref="kosdaq1d_b">1일</button>
							<button v-on:click="kosdaq3m" type="button" ref="kosdaq3m_b">3개월</button>
							<button v-on:click="kosdaq1y" type="button" ref="kosdaq1y_b">1년</button>
						</div>
						<div class="inner_chart">
							<highcharts :constructorType="'stockChart'" class="hc" :options="chartOptions2" ref="kosdaqc"></highcharts>
						</div>
					</div>
				</div>
			</div>
			<div class="p_top">
				<h1>섹터/테마 별 기술테마 랭킹</h1>
				<span class="desc">전체 섹터/테마 별 기술기업들을 순위별로 확인할 수 있습니다.</span>
			</div>
			<section class="content">
				<article class="area_chart col2">
					<div class="col">
						<h2 class="mb50">섹터별 테마유형 추이</h2>
						<!-- <h3 class="tit_chart mt40 mb50">섹터별 테마유형 추이</h3> -->
						<highcharts  :options="chartOptions3" />
						<div class="bx_lavel">
              <button v-for="(sector,i) in sectorLists" v-bind:key="i" class="clavel" v-on:click="fnClickSectorLaval(sector.sectorNo)">
                <span class="em">
                  <em v-bind:style="{background:sector.color}"></em>
                  {{sector.sectorName}}
                </span>
                <span class="cnt">
                  {{sector.themeCnt}}
                </span>
              </button>
						</div>
					</div>
					<div class="col">
						<h2>섹터 주가 추이 & 추천 종목 현황</h2>
						<div class="colb mt20">
							<h3 class="tit_chart">{{ sectorName }}</h3> <button type="button" class="btn_more"><em>+</em>더보기</button>
						</div>
						<div class="bx_chart mt35">
							<highcharts  :options="chartOptions4" />
						</div>
						<div class="colb mt35">
							<h2>추천종목 수익율 상위</h2>
							<div class="chart_desc">
							<!-- <em class="ico_dash red"></em>섹터 수익율 추이 -->
							</div>
						</div>
						<div class="chart_info">
							<div class="unit" v-for="(stockRcmn,i ) in stockRcmnList" v-bind:key="i">
								<em class=""></em>
								{{stockRcmn.stockName}}
								<span :class="$fnGetPriceClassVal(stockRcmn.priceRate)">
								{{$fnGetPriceTxtVal(stockRcmn.priceRate)}} %
								</span>
							</div>
							<div class="unit" v-if="stockRcmnList.length ==0">
								추천 종목이 없습니다!
							</div>
						</div>
						<div class="btns">
							<button type="button" class="btn">더보기 +</button>
						</div>
					</div>
				</article>
				<article class="area_selc col2">
					<SectorSelect tit="섹터" type="sector" ref="sector"  @setTargetNo="settargetSectorNo"/>
					<SectorSelect tit="테마" type="theme" :targetNo="this.targetSectorNo" :key="this.targetSectorNo" @setTargetNo="settargetThemeNo"/>
				</article>
				<article class="area_list">
					<div class="colb">
						<div class="c_tap">
							<button type="button" class="searchBtn tap on" v-on:click="fn_Search('TII', $event)" data-id="TII">투자매력도순(기술투자지순)</button>
							<button type="button" class="searchBtn tap" v-on:click="fn_Search('TSS', $event)" data-id="TSS">기술력순(TSS)</button>
						</div>
						<div class="area_slct">
							<span class="txt_gray f14 mr10">항목수</span>
							<Select width="90" class="type1" @setTargetNo="setstockListCnt" :list="[{name: '10',no: '10', select:true, ref:'listCnt'},{name: '50',no: '50', ref:'listCnt'},{name: '100',no: '100', ref:'listCnt'}]"/>
						</div>
					</div>
					<table class="tbl">
						<colgroup>
							<col width="60"/>
							<col/>
							<col width="115" id="border_red"/>
							<col width="85"/>
							<col width="85"/>
							<col width="85"/>
							<col width="110"/>
							<col/>
							<col/>
							<col />
							<col width="80"/>
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>종목명</th>
								<th>기술투자지수 <Tooltip desc='기술투자지수란 기업의 시가총액을 TSS 점수(위즈도메인이 개발한 통계기법으로 산출)로 나눈 값입니다. 보유한 기술력대비 주기가 어느정도인지 가늠하는 수치로 동종기술로 분류된 테마내에서 상대비교를 통해 기업이 가치를 평가할 수 있는 보조지표입니다.  기술투자자가 낮을수록 "기술력대비 저평가" 되어있어 기술력관점에서 투자매력도가 높습니다. 즉, 기술투자지수가 낮다면 기업이 보유한 기술력보다 주가가 상대적으로 싸다는 의미인 것입니다.'/></th>
								<th>TSS <Tooltip desc='TSS는 기업이 가진 특허의 규모(quantity)와 기술 수준(quality)를 동시에 측정한 기술 경쟁력 지수입니다. 기업이 보유한 전체 특허를 점수화한 것으로 수치가 클 수록 수록 기술 경쟁력이 높음을 의미하며 다른 기업의 수치와 비교하여 상대적으로 평가할 수 있습니다.'/></th>
								<th>TCG <Tooltip desc='TCG는 기업이 취득한 특허들을 평가하여 합산한 점수와 이 점수를 기반으로 한 업계 순위에 근거하여 생성되었습니다. 특허 평가 점수는 ㈜위즈도메인이 개발한 통계기법에 의해 계산된 값입니다. 이 점수는 객관적인 평가기준에 따라 동종 기술분야의 특허들과 상대 비교하여 산출됩니다.'/></th>
								<th>APES <Tooltip desc='APES는 각 기술부문별 특허의 점수를 평균한 것으로, 개별 특허의 평가 점수는 특허 빅데이터 및 ㈜위즈도메인의 특허평가엔진(Patent Evaluation Engine)을 이용하여 산출합니다. 특허 평가 요소는 10여 가지가 있으며 평가 요소 중 하나를 예로 들면 다른 특허들로부터 인용을 많이 당한 특허는 좋은 점수를 받습니다.'/></th>
								<th>특허보유(건)</th>
								<th>기업가치추정</th>
								<th>시가총액 (억원)</th>
								<th>주가추이(최근30일)</th>
								<th>리포트</th>
								<!-- <th>리포트구매 <Tooltip classNm="left" desc="해당하는 설명을 써주세요!!"/></th> -->
							</tr>
						</thead>
						<tbody v-if="isLoading"> <!-- 테스트 하느라 분리했습니다. 실제 작업하실때는 편하신데로 작업해주시면 됩니다. -->
						<!-- 높이는 인라인으로 조정 가능 최소높이 150px -->
						<tr>
							<td class="loading" style="height:420px;" colspan="11">로딩중입니다</td>
							<!-- <td class="nodata" style="height:420px;" colspan="9">데이터가 없습니다</td> -->
						</tr>
						</tbody>
						<tbody v-else>
							<tr v-for="(stock,i) in stockList" v-bind:key="i" v-on:click=fnStockDetail(stock.isuSrtCd,stock.corp.bizno,stock.mktCd,$event)>
									<td>{{i+1+((pageNo)*pageSize)}}</td>
									<td>{{stock.isuKorNm}}</td>
									<td>{{stock.corp.tii.toFixed(2)}}</td>
									<td>{{stock.corp.tss.toFixed(2)}}</td>
									<td>{{stock.corp.tcg}}</td>
									<td>{{stock.corp.apes.toFixed(2)}}</td>
									<td>{{stock.corp.patentCount}}</td>
									<td>
										<div>
											<span>{{stock.corp.assdValue}}</span>
										</div>
									</td>
									<td>{{$addComma(stock.corp.mktCap)}}</td>
									<td>
										<span :class="$fnGetPriceClassVal(stock.corp.rctStkPriceRate)">
											{{$fnGetPriceTxtVal(stock.corp.rctStkPriceRate)}}%
										</span>
									</td>
									<td>
										<!--<button type="button" class="btn_excel">엑셀</button>-->
										<button type="button" class="btn_pdf" v-on:click="fnGetReport" >pdf</button>
									</td>
							</tr>
						</tbody>
					</table>
					<!-- <div class="loading" style="height:420px;">로딩중입니다</div> 높이는 인라인으로 조정 가능  -->
			<!-- 		<div class="btns">
						<button type="button" class="btn addBtn" v-on:click="fn_Click_Add_Btn" >더보기 +</button>
					</div> -->
					<div class="paging" ref="paging_btns">
						<button class="btn_prev" v-if="pageNo > 9" v-on:click="fn_Get_Stock_List((pageGroupNo*10)-1)">이전</button>
						<button v-bind:class="page.class" v-on:click="fn_Get_Stock_List(page.page)" v-for="(page, i) in stockPages" v-bind:key="i">
							{{page.page + 1}}
						</button>
						<button class="btn_next" v-if="pageGroupNo < pageGroups.length-1 && pageTotal > 10" v-on:click="fn_Get_Stock_List((pageGroupNo*10)+10)">다음</button>
					</div>
				</article>
			</section>
		</div>
	</div>
</template>

<script>
import Tooltip from '@/components/common/Tooltip.vue';
import SectorSelect from "@/components/select/SectorSelect";
import Select from '@/components/common/Select.vue';

export default {
	name: 'Sector',
	data() {
		return {
			body:''
			,stockList:[]
			,stockPages:[]
			,pageGroups:[]
			,pageGroupNo:0			
			,pageNo:1
			,pageSize:10
			,pageTotal:0
			,searchSectorNo:''
			,searchThemeNo:''
			,searchWrd:''
			,sortColumn:'TII'
			,sectorListCnt:''
			,itemChartData:''
			,sortOrdr:'ASC'
			,stockRcmnData:''
			,kospiUpDown:''
			,kosDaqUpDown:''
			,stockRcmnList:''
			,sectorName:''
			,chartOptions3: {
					title: {
						text: ''
					},
					chart:{
						type:'item',
            width: 600,
            height: 280,
					},
					legend: {
						labelFormat: '{name} <span style="opacity: 0.4">{y}</span>',
            enabled:false,
					},
					plotOptions: {
						series: {
							cursor: 'pointer',
							point: {
								events: {
									click:(e) => {
										this.fn_Get_Stock_Rcmn(e.point.sectorNo);
										this.fn_Get_SectorRanking_Chart_PriceRate(e.point.sectorNo);
										this.settargetSectorNo(e.point.sectorNo);
										this.$refs.sector.$children[0].$refs['sector_'+e.point.sectorNo][0].click()
									},
									legendItemClick:(e) => {
										this.fn_Get_Stock_Rcmn(e.target.sectorNo);
										this.fn_Get_SectorRanking_Chart_PriceRate(e.target.sectorNo);
										this.settargetSectorNo(e.target.sectorNo)
										return false;
									},  
								}
							},
						}
					},

					series: [{
						name: '테마 수',
						keys: ['name', 'y', 'color', 'label','sectorNo'],
						data: this.itemChartData
						,
						dataLabels: {
							enabled: true,
							format: '{point.label}'
						},

						// Circular options
						center: ['50%', '88%'],
						size: '170%',
						startAngle: -100,
						endAngle: 100
					}],

					responsive: {
						rules: [{
							condition: {
								maxWidth: 600
							},
							chartOptions3: {
								series: [{
									dataLabels: {
										distance: -30
									}
								}]
							}
						}]
					},
        credits: {
          enabled: false
        },
			},
			chartOptions: {
				chart: {
					type: 'area',
					height: 200,
					width: 350,
					zoomType: false
				}, navigator: {
					enabled: false
				}, scrollbar: {
					enabled: false
				}, rangeSelector : {
					enabled: false,
					selected : 5,
					inputEnabled: false
				}, plotOptions: {
					area: {
						threshold: 0,
						negativeFillColor: '#E8EFF9',
						fillColor: '#FAE8E8',
						lineWidth: 1.2
					}
				}, yAxis: {
					max: 0,
					min: 0,
					plotLines: [{
						color: 'red',
						dashStyle: 'shortdot',
						width: 2,
						value: 0
					}]
				},
        credits: {
          enabled: false
        },
        series:[{
            name:'KOSPI',
            data:[[0,0]]
          }
        ]
			},
			chartOptions2: {
				chart: {
					type: 'area',
					height: 200,
					width: 350,
					zoomType: false
				}, navigator: {
					enabled: false
				}, scrollbar: {
					enabled: false
				}, rangeSelector : {
					enabled: false,
					selected : 5,
					inputEnabled: false
				}, plotOptions: {
					area: {
						threshold: 0,
						negativeFillColor: '#E8EFF9',
						fillColor: '#FAE8E8',
						lineWidth: 1.2
					}
				}, yAxis: {
					max: 0,
					min: 0,
					plotLines: [{
						color: 'red',
						dashStyle: 'shortdot',
						width: 2,
						value: 0
					}]
				}, series: [
					{
						name: 'KOSDAQ',
						data: [[0.0]]
					}
				],
        credits: {
          enabled: false
        },
			},
      chartOptions4: {
        title:'종목추이',
        yAxis: {
          title: {
            text: ''
          },
        },
        xAxis:{
          type:'datetime',
          labels: {
            format: '{value:%Y-%m-%d}'
          },
        },
        tooltip: {
          split: true,
          valueSuffix: '%'
        },
        plotOptions: {
          series: {
            marker: {
              enabled: false
            },
          }
        },
        labels: {
          enabled: false//label 미사용 시 false로 지정.
        },
        series: [
            {
              name:'KOSPI',
              data:''
            },
            {
              name:'KOSDAQ',
              data:''
            },
            {
              name:'섹터추이',
              data:''
            }

         ],
        credits: {
          enabled: false
        },

      },
			targetSectorNo:null,
			targetThemeNo:null,
			isLoading:false,
      sectorLists:[],
		}
	},
	mounted() {
		this.fn_Get_Item_ChartData();
    this.fn_Get_SectorRanking_Chart_PriceRate("1");
    this.fn_Get_Stock_Rcmn("1");
		this.fn_Get_Stock_List(0);
		this.$refs.kosdaq1d_b.click();
   	this.$refs.kospi1d_b.click();
	},
	methods:{
		fn_Get_Item_ChartData () {
			 this.$api.fnGetApiCall("/sectorchart/itemdata")
			.then((res) => {
        var series_data = [], thedata= res.data;
				for (var i=0; i< thedata.length; i++) {
          var sector = {
            sectorName:thedata[i].sectorName,
            themeCnt:thedata[i].themeCnt,
            color:thedata[i].color,
            sectorNo:thedata[i].sectorNo,
          }
					series_data.push([thedata[i].sectorName, thedata[i].themeCnt, thedata[i].color,thedata[i].sectorName, thedata[i].sectorNo]);
          this.sectorLists.push(sector);
				}
				this.chartOptions3.series[0].data = series_data
			})
			.catch((err) => {
				console.log(err)
			})
		},
		fn_Get_Stock_List(pageNo)  {
			this.pageNo = pageNo;
			this.body = {
				pageNo:this.pageNo+1,
				pageSize:this.pageSize,
				searchSectorNo:this.searchSectorNo,
				searchThemeNo:this.searchThemeNo,
				searchWrd:this.searchWrd,
				sortColumn:this.sortColumn,
				sortOrdr:this.sortOrdr
			}
			if(pageNo != 0) {
				this.stockPages = this.pageGroups[Math.floor(pageNo/10)];
				this.pageGroupNo = Math.floor(pageNo/10);
				for (var idx in this.stockPages) {
					if (this.stockPages[idx].page == pageNo) {
						this.stockPages[idx].class = "on";
					} else {
						this.stockPages[idx].class = "";
					}
				}
			}

			this.$api.fnGetApiParamCall('/stockdata/list',this.body)
			.then((res) => {
				this.stockList = res.data.stks;
				//res.data.totalCnt
				this.pageTotal = Math.ceil(res.data.totalCnt/this.pageSize);
				this.stockPages = [];
				for(var i = 0; i < this.pageTotal; i++) {
					if(i == 0){
						this.stockPages.push({page:i,class:"on"});
					}else {
						this.stockPages.push({page:i,class:""});
					}
				}

				var _stockPages = [];
				this.pageGroups = [];
				for(var j = 0; j < this.pageTotal; j++) {
					if (j == 0) {
						_stockPages.push({page:j, class:"on"});
					} else {
						_stockPages.push({page:j, class:""});
					}
					if ((j+1)%10 == 0) {
						this.pageGroups.push(_stockPages);
						_stockPages = [];
					}
				}
				if (_stockPages.length > 0) {
					this.pageGroups.push(_stockPages);
				}
				this.stockPages = [];
				this.stockPages = this.pageGroups[pageNo];
				this.pageGroupNo = pageNo;

				if(pageNo != 0) {
					this.stockPages = this.pageGroups[Math.floor(pageNo/10)];
					this.pageGroupNo = Math.floor(pageNo/10);
					for (var idx in this.stockPages) {
						if (this.stockPages[idx].page == pageNo) {
							this.stockPages[idx].class = "on";
						} else {
							this.stockPages[idx].class = "";
						}
					}
				}
					
			})
			.catch((err) => {
				console.log(err)
			})
		},
		fn_Search(sortColumn, event) {
			if(sortColumn == 'TII') {
				this.sortOrdr = 'ASC';
			} else if(sortColumn == 'TSS') {
				this.sortOrdr = 'DESC';
			}
			const tabList = document.getElementsByClassName('searchBtn');
			const target = event.target;
			Array.prototype.forEach.call(tabList, item => item.classList.remove("on"));
			target.classList.add("on");
			this.sortColumn = sortColumn;
			this.fn_Get_Stock_List(0);
		},
		fn_isAssdValueHighOn(state) {
			if(state == '고평가') return true;
			else return false;
		},
		fn_isAssdValueLowOn(state) {
			if(state == '저평가') return true;
			else return false;
		},
		fn_isAssdValueMidOn(state) {
			if(state == '적정') return true;
			else return false;
		},
		kospi1d : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kospi1d_b.className = "on";
					this.$refs.kospi3m_b.className = "";
					this.$refs.kospi1y_b.className = "";
					const inqStrtDd = this.$getDateTime().substring(0,8);
					console.log("inqStrtDd: " + inqStrtDd);

					this.$api.fnGetApiCall('/sectorchart/stockIntraDay?&inqStrtDd='+inqStrtDd+'&issuecode=K1&marketcode=kospi')
							.then((res) => {
               	var json = res.data;
								var results = json.hisLists;
								const chartData = [];
								var max = 0;
								var min = 0;
                var maxDate = new Date();
                for(var idx in results) {
                  var tempDate = new Date();
                  if(results[idx].inddTm.startsWith("9")) {
                    tempDate.setUTCHours("0" + results[idx].inddTm.substring(0,1));
                    tempDate.setUTCMinutes(results[idx].inddTm.substring(1,3));
                    tempDate.setUTCSeconds(results[idx].inddTm.substring(3,5));
                    tempDate.setUTCMilliseconds(0);
                  } else {
                    tempDate.setUTCHours(results[idx].inddTm.substring(0,2));
                    tempDate.setUTCMinutes(results[idx].inddTm.substring(2,4));
                    tempDate.setUTCSeconds(results[idx].inddTm.substring(4,6));
                    tempDate.setUTCMilliseconds(0);
                  }
                  chartData.push([tempDate.getTime(), results[idx].inddClsprc]);
                  if (idx == 0) {
                    max = results[idx].inddClsprc;
                    min = results[idx].inddClsprc;
                    //maxXaxis = tempDate.getTime();
                    maxDate = tempDate;
                  } else {
                    if (max < results[idx].inddClsprc) {
                      max = results[idx].inddClsprc;
                    }
                    if (min > results[idx].inddClsprc) {
                      min = results[idx].inddClsprc;
                    }
                    if (maxDate.getTime() < tempDate.getTime()) {
                      //maxXaxis = tempDate.getTime()
                      maxDate = tempDate;
                    }
                  }
                }
                console.log(maxDate);
                if (maxDate.getTime() > 0) {
                  var openTime = new Date();
                  for (var hour = maxDate.getUTCHours(); hour < 16; hour++) {
                    openTime.setUTCHours(hour);
                    openTime.setUTCSeconds(0);
                    openTime.setUTCMilliseconds(0);
                    if (hour == maxDate.getUTCHours() && hour != 15) {
                      for (var minute = maxDate.getUTCMinutes(); minute < 60; minute++) {
                        openTime.setUTCMinutes(minute);
                        chartData.push([openTime.getTime(), null]);
                      }
                    } else if (hour == 15) {
                      for (minute = 0; minute < 31; minute++) {
                        openTime.setUTCMinutes(minute);
                        chartData.push([openTime.getTime(), null]);
                      }
                    } else {
                      for (minute = 0; minute < 60; minute++) {
                        openTime.setUTCMinutes(minute);
                        chartData.push([openTime.getTime(), null]);
                      }
                    }
                  }
                  this.chartOptions.series[0].data = chartData;
                  this.chartOptions.yAxis.min = min;
                  this.chartOptions.yAxis.max = max;
                  this.chartOptions.yAxis.plotLines[0].value = (json.prevTrdPrc);
                  this.chartOptions.plotOptions.area.threshold = (json.prevTrdPrc);
                  this.chartOptions.plotOptions.area.fillColor = '#FAE8E8';
                } else {
                  openTime = new Date();
                  for (hour = 9; hour < 16; hour++) {
                    openTime.setUTCHours(hour);
                    openTime.setUTCSeconds(0);
                    openTime.setUTCMilliseconds(0);
                    for (minute = 0; minute < 60; minute++) {
                      openTime.setUTCMinutes(minute);
                      chartData.push([openTime.getTime(), null]);
                    }
                  }
                  this.chartOptions.yAxis.min = 0;
                  this.chartOptions.yAxis.max = 0;
                  this.chartOptions.series[0].data = chartData;
                }
              })
							.catch((error) => {
								console.log(error);
							});

					this.$api.fnGetApiCall('/sectorchart/marketIndex?issuecode=K1&marketcode=kospi')
							.then((res) => {
                var json = res.data;
								this.$refs.kospiTrdPrc.innerText = json.trdPrc;
								if (json.cmpprevddTpCd == '2') {
									this.$refs.kospiCmpprevddPrc.innerText = "▲" + json.cmpprevddPrc;
									this.$refs.kospiCmpprevddPrc.className = "up";
                  this.kospiUpDown = "up";
									this.$refs.kospiRatio.innerText = "(+" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								} else if (json.cmpprevddTpCd == '5') {
									this.$refs.kospiCmpprevddPrc.innerText = "▼" + (json.cmpprevddPrc *= -1);
									this.$refs.kospiCmpprevddPrc.className = "down";
                  this.kospiUpDown = "down";
									this.$refs.kospiRatio.innerText = "(-" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								} else {
									this.$refs.kospiCmpprevddPrc.innerText = json.cmpprevddPrc;
									this.$refs.kospiRatio.innerText = "(" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								}
							})
							.catch((error) => {
								console.log(error);
							});
				} else {
					console.log("already selected...");
				}
			}

		},kospi3m : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kospi1d_b.className = "";
					this.$refs.kospi3m_b.className = "on";
					this.$refs.kospi1y_b.className = "";

					this.$api.fnGetApiCall('/sectorChart/stockHistory?inqEndDd=20211119&inqStrtDd=20210919&issuecode=K1&marketcode=kospi&reqCnt=100&trnsmCycleTpCd=D')
							.then((res) => {
                var json = res.data;
								this.chartOptions.plotOptions.area.threshold = 0;
								var results = json.hisLists;
								const chartData = [];
								var max = 0;
								var min = 0;
								for (var idx in results) {
									var tempDate = new Date();
									tempDate.setFullYear(parseInt(results[idx].trdDd.substring(0,4)));
									tempDate.setMonth(parseInt(results[idx].trdDd.substring(4,6)));
									tempDate.setDate(parseInt(results[idx].trdDd.substring(6,8)));
									tempDate.setUTCHours(0);
									tempDate.setUTCMinutes(0);
									tempDate.setUTCSeconds(0);
									tempDate.setUTCMilliseconds(0);

									//console.log(tempDate.getTime());
									chartData.push([tempDate.getTime(), results[idx].trdPrc]);
									if (idx == 0) {
										max = results[idx].trdPrc;
										min = results[idx].trdPrc;
									} else {
										if (max < results[idx].trdPrc) {
											max = results[idx].trdPrc;
										}
										if (min > results[idx].trdPrc) {
											min = results[idx].trdPrc;
										}
									}
								}

								console.log(chartData);
								this.chartOptions.series[0].data = chartData;
								console.log("@@@: " + max + ", " + min);
								this.chartOptions.yAxis.min = min;
								this.chartOptions.yAxis.max = max;
								this.chartOptions.plotOptions.area.fillColor = '#F2FFED';
								this.chartOptions.yAxis.plotLines[0].value = 0;

							})
							.catch((error) => {
								console.log(error);
							});
				}
			}
		},kospi1y : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kospi1d_b.className = "";
					this.$refs.kospi3m_b.className = "";
					this.$refs.kospi1y_b.className = "on";
				}
			}
		},kosdaq1d : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kosdaq1d_b.className = "on";
					this.$refs.kosdaq3m_b.className = "";
					this.$refs.kosdaq1y_b.className = "";
					const inqStrtDd = this.$getDateTime().substring(0,8);

					this.$api.fnGetApiCall('/sectorchart/stockIntraDay?inqStrtDd=' + inqStrtDd + '&issuecode=Q1&marketcode=kosdaq')
							.then((res) => {
                var json = res.data;

                console.log(json);
                var results = json.hisLists;
                console.log(results.length);
                const chartData = [];
                var max = 0;
                var min = 0;
                //var maxXaxis = 0;
                var maxDate = new Date();
                for (var idx in results) {
                  //var result = new Array(2);
                  var tempDate = new Date();
                  if(results[idx].inddTm.startsWith("9")) {
                    tempDate.setUTCHours("0" + results[idx].inddTm.substring(0,1));
                  tempDate.setUTCMinutes(results[idx].inddTm.substring(1,3));
                  tempDate.setUTCSeconds(results[idx].inddTm.substring(3,5));
                  tempDate.setUTCMilliseconds(0);
                } else {
                  tempDate.setUTCHours(results[idx].inddTm.substring(0,2));
                  tempDate.setUTCMinutes(results[idx].inddTm.substring(2,4));
                  tempDate.setUTCSeconds(results[idx].inddTm.substring(4,6));
                  tempDate.setUTCMilliseconds(0);
                }
                chartData.push([tempDate.getTime(), results[idx].inddClsprc]);
                //this.chartOptions2.series[0].data.push([tempDate.getTime(), results[idx].inddClsprc]);
                if (idx == 0) {
                  max = results[idx].inddClsprc;
                  min = results[idx].inddClsprc;
                  //maxXaxis = tempDate.getTime();
                  maxDate = tempDate;
                } else {
                  if (max < results[idx].inddClsprc) {
                    max = results[idx].inddClsprc;
                  }
                  if (min > results[idx].inddClsprc) {
                    min = results[idx].inddClsprc;
                  }
                  if (maxDate.getTime() < tempDate.getTime()) {
                    //maxXaxis = tempDate.getTime()
                    maxDate = tempDate;
                  }
                }
              }
          console.log(maxDate);
          if (maxDate.getTime() > 0) {
            console.log(maxDate.getUTCHours(), maxDate.getUTCMinutes());
            var openTime = new Date();
            for (var hour = maxDate.getUTCHours(); hour < 16; hour++) {
              console.log("@@@@ ", maxDate.getUTCHours());
              openTime.setUTCHours(hour);
              openTime.setUTCSeconds(0);
              openTime.setUTCMilliseconds(0);
              if (hour == maxDate.getUTCHours() && hour != 15) {
                for (var minute = maxDate.getUTCMinutes(); minute < 60; minute++) {
                  openTime.setUTCMinutes(minute);
                  chartData.push([openTime.getTime(), null]);
                }
              } else if (hour == 15) {
                for (minute = 0; minute < 31; minute++) {
                  openTime.setUTCMinutes(minute);
                  chartData.push([openTime.getTime(), null]);
                }
              } else {
                for (minute = 0; minute < 60; minute++) {
                  openTime.setUTCMinutes(minute);
                  chartData.push([openTime.getTime(), null]);
                }
              }
            }
            this.chartOptions2.series[0].data = chartData;
            console.log("@kosdaq max, min: " + max + ", " + min);
            this.chartOptions2.yAxis.min = min;
            this.chartOptions2.yAxis.max = max;
            console.log("@kosdaq threshold: " + (json.prevTrdPrc));
            this.chartOptions2.yAxis.plotLines[0].value = (json.prevTrdPrc);
            this.chartOptions2.plotOptions.area.threshold = (json.prevTrdPrc);
            this.chartOptions2.plotOptions.area.fillColor = '#FAE8E8';
          }
          else {
            openTime = new Date();
            for (hour = 9; hour < 16; hour++) {
              openTime.setUTCHours(hour);
              openTime.setUTCSeconds(0);
              openTime.setUTCMilliseconds(0);
              for (minute = 0; minute < 60; minute++) {
                openTime.setUTCMinutes(minute);
                chartData.push([openTime.getTime(), null]);
              }
            }
            this.chartOptions2.yAxis.min = 0;
            this.chartOptions2.yAxis.max = 0;
            this.chartOptions2.series[0].data = chartData;
            console.log("maxXaxis %v", chartData);
          }
        })
      .catch((error) => {
          console.log(error);
        });
					this.$api.fnGetApiCall('/sectorchart/marketIndex?issuecode=Q1&marketcode=kosdaq')
							.then((res) => {
                var json = res.data;
								console.log(json);
								this.$refs.kosdaqTrdPrc.innerText = json.trdPrc;
								if (json.cmpprevddTpCd == '2') {
									this.$refs.kosdaqCmpprevddPrc.innerText = "▲" + json.cmpprevddPrc;
									this.$refs.kosdaqCmpprevddPrc.className = "up";
                  this.kosDaqUpDown = "up";
									this.$refs.kosdaqRatio.innerText = "(+" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								} else if (json.cmpprevddTpCd == '5') {
									this.$refs.kosdaqCmpprevddPrc.innerText = "▼" + (json.cmpprevddPrc *= -1);
									this.$refs.kosdaqCmpprevddPrc.className = "down";
                  this.kosDaqUpDown = "down";
									this.$refs.kosdaqRatio.innerText = "(-" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								} else {
									this.$refs.kosdaqCmpprevddPrc.innerText = json.cmpprevddPrc;
									this.$refs.kosdaqRatio.innerText = "(" + (json.cmpprevddPrc * 100 / (json.trdPrc - json.cmpprevddPrc)).toFixed(2) + "%)";
								}
							})
							.catch((error) => {
								console.log(error);
							});



				}
			}
		},kosdaq3m : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kosdaq1d_b.className = "";
					this.$refs.kosdaq3m_b.className = "on";
					this.$refs.kosdaq1y_b.className = "";

					const currentDay = new Date();
					var inqStrtDd = currentDay.getFullYear() + '';
					var inqEndDd = currentDay.getFullYear() + '';

					if ((currentDay.getMonth()+1) < 10) {
						inqEndDd = inqEndDd + '0' + (currentDay.getMonth()+1) + '';
					} else {
						inqEndDd = inqEndDd + (currentDay.getMonth()+1) + '';
					}

					if ((currentDay.getMonth()-2) < 10) {
						inqStrtDd = inqStrtDd + '0' + (currentDay.getMonth()-2) + '';
					} else {
						inqStrtDd = inqStrtDd + (currentDay.getMonth()-2) + '';
					}

					if (currentDay.getDate() < 10) {
						inqStrtDd = inqStrtDd + '0' + currentDay.getDate() + '';
						inqEndDd = inqEndDd + '0' + currentDay.getDate() + '';
					} else {
						inqStrtDd = inqStrtDd + currentDay.getDate() + '';
						inqEndDd = inqEndDd + currentDay.getDate() + '';
					}

          this.$api.fnGetApiCall('/sectorchart/stockHistory?inqEndDd=' + inqEndDd + '&inqStrtDd=' + inqStrtDd + '&issuecode=Q1&marketcode=kosdaq&reqCnt=100&trnsmCycleTpCd=D')
							.then((res) => {
                var json = res.data;
								this.chartOptions2.plotOptions.area.threshold = 0;
								var results = json.hisLists;
								const chartData = [];
								var max = 0;
								var min = 0;
								for (var idx in results) {
									var tempDate = new Date();
									tempDate.setFullYear(parseInt(results[idx].trdDd.substring(0,4)));
									tempDate.setMonth((parseInt(results[idx].trdDd.substring(4,6))-1));
									tempDate.setDate(parseInt(results[idx].trdDd.substring(6,8)));
									tempDate.setUTCHours(0);
									tempDate.setUTCMinutes(0);
									tempDate.setUTCSeconds(0);
									tempDate.setUTCMilliseconds(0);

									//console.log(tempDate.getTime());
									chartData.push([tempDate.getTime(), results[idx].trdPrc]);
									if (idx == 0) {
										max = results[idx].trdPrc;
										min = results[idx].trdPrc;
									} else {
										if (max < results[idx].trdPrc) {
											max = results[idx].trdPrc;
										}
										if (min > results[idx].trdPrc) {
											min = results[idx].trdPrc;
										}
									}
								}
								console.log(chartData);
								this.chartOptions2.series[0].data = chartData;
								console.log("@@@: " + max + ", " + min);
								this.chartOptions2.yAxis.min = min;
								this.chartOptions2.yAxis.max = max;
								this.chartOptions2.plotOptions.area.fillColor = '#F2FFED';
								this.chartOptions2.yAxis.plotLines[0].value = 0;
							})
							.catch((error) => {
								console.log(error);
							});
				}
			}
		},kosdaq1y : function(event) {
			if (event) {
				if(!event.target.classList.contains('on')) {
					this.$refs.kosdaq1d_b.className = "";
					this.$refs.kosdaq3m_b.className = "";
					this.$refs.kosdaq1y_b.className = "on";
				}
			}
		},
		settargetSectorNo(id){
			this.targetSectorNo = id;
			this.targetThemeNo = '';
			this.searchSectorNo = id;
			this.searchThemeNo = '';
			this.pageNo = 1;
			this.fn_Get_Stock_List(0);
		},
		settargetThemeNo(id){
			this.targetThemeNo = id;
			this.searchThemeNo = id;
			this.pageNo = 1;
			this.fn_Get_Stock_List(0);
		},
    setstockListCnt(cnt) {
      this.pageSize = cnt;
      this.pageNo = 1;
      this.fn_Get_Stock_List(0);
    },
    fn_Get_SectorRanking_Chart_PriceRate(sectorNo) {
      this.$api.fnGetApiCall("/sectorchart/priceRate/"+sectorNo)
      .then((res) => {
        var json = res.data;
        var chartData = [];
        for(var idxKospi in json.kospi) {
          chartData.push([this.fnGetTime(json.kospi[idxKospi].trdDd),json.kospi[idxKospi].priceRate]);
		    }
        this.chartOptions4.series[0].data = chartData;

        chartData = [];
        for(var idxKosdaq in json.kosdaq) {
          chartData.push([this.fnGetTime(json.kosdaq[idxKosdaq].trdDd), json.kosdaq[idxKosdaq].priceRate]);
		    }
        this.chartOptions4.series[1].data = chartData;

         chartData = [];
        for(var idxSector in json.sector) {
           chartData.push([this.fnGetTime(json.sector[idxSector].trdDd), json.sector[idxSector].priceRate]);
         }
          this.chartOptions4.series[2].data = chartData;
         this.sectorName = json.sectorName;
      });

    },
    fn_Get_Stock_Rcmn(id) {
      this.$api.fnGetApiCall("/sectorchart/stockRcmn?securityType=UNICORNRANCH&stkCnt=5&sectorNo="+id)
          .then((res) => {
            console.log(res.data)
            var json = res.data;
            this.stockRcmnList = json;
            var chartData = [];
            var xLineData = [];
            for(var idx in json) {
                //var stockName = json[idx].stockName;
                var stockPr = json[idx].stockPr;
                var stockPrChartData = [];
                for(var idxPr in stockPr) {
                  stockPrChartData.push(stockPr[idxPr].priceRate);
                }
                if(idx == json.length -1) {
                  for(var idxPrDt in stockPr) {
                    xLineData.push(stockPr[idxPrDt].trdDd);
                  }
                }
              //this.chartOptions4.series[idx].name = stockName;
              //chartData.push([stockPrChartData]);
              //this.chartOptions4.series[idx].data = stockPrChartData;
            }
            console.log(chartData);
            //this.chartOptions4.xAxis.categories = xLineData;

          })
          .catch((err) => {
            console.log(err);
          })
    },
    fnGetPriceClassVal (val) {
      if(val  > 0) {
        return "txt_red";
      } else if(val == 0) {
        return "txt_gray";
      } else if(val < 0) {
        return "txt_blue";
      }
     },
    fnGetPriceTxtVal (val) {
      val = Math.round(val * 100) / 100
      if(val > 0) {
        return "+"+val;
      } else if(val == 0 || val < 0) {
        return val;
      }
    },
    fnGetTime(date_str){
	  return Date.UTC(
		  parseInt(date_str.substring(0,4)),
		  parseInt(date_str.substring(4,6))-1,
		  parseInt(date_str.substring(6,8)),
		  0,
		  0
		)
    },
    fnStockDetail(isuSrtCd, bizno, mktCd, e) {
      sessionStorage.setItem("detailBizNo", bizno);
      sessionStorage.setItem("detailIsuSrtCd", isuSrtCd);
      let mktCdNm = '';
      if(mktCd == '11') {
        mktCdNm = 'kospi';
      } else if(mktCd == '12') {
        mktCdNm = 'kosdaq';
      } else if(mktCd == '14') {
        mktCdNm = 'konex';
      } else if(mktCd == '15') {
        mktCdNm = 'etc';
      }

      if(e.target.localName.indexOf('td') > -1) {
        const param = {
          cmd: 'goStockDetail',
          bizNo : bizno,
          isuSrtCd : isuSrtCd,
          mkt: mktCdNm, // kosdaq 등 lowercase로
        };
        const targetOrigin = '*'; // 모든 사이트
        window.parent.postMessage(param,targetOrigin);
      } else if(e.target.localName.indexOf('button') > -1) {
        window.open("/report/stockReport?bizNo="+bizno+"&isuSrtCd="+isuSrtCd, "", "width=610,height=810");
      }
    },
    fnGetReport(e){
      console.log(e)
      return
    },
    fnClickSectorLaval(sectorNo) {
      this.fn_Get_Stock_Rcmn(sectorNo);
      this.fn_Get_SectorRanking_Chart_PriceRate(sectorNo);
      this.settargetSectorNo(sectorNo);
      this.$refs.sector.$children[0].$refs['sector_'+sectorNo][0].click()
    },
	},
	components : {
		Tooltip,
		SectorSelect,
		Select
	},
	beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_ranking";
	},
	metaInfo: {
		title: '섹터분석 | 10bagger',
	}
}
</script>