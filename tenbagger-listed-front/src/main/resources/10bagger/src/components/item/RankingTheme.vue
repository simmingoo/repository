<template>
	<section class="content">
		<article class="area_theme col2">
			<div class="col bx_chart"><!--차트 완료 되면 chart_prepare 클래스는 빼주세요-->
				<highcharts :constructorType="'chart'" class="chart" :options="top5_theme_chart_options" ref="top5_theme_chart"></highcharts>
			</div>
			<div class="col">
				<div v-if="drillDownLevel === 0">
					<table class="tbl sm">
						<colgroup>
							<col width="50"/>
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>섹터명</th>
								<th>테마수</th>
								<th>등락률</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(list,i) in itemList" v-bind:key="i">
									<td>{{i+1+((pageNo)*pageSize)}}</td>
									<td>{{list.sectorName}}</td>
									<td>{{list.themeCnt}}</td>
									<td>{{list.avgPriceRate.toFixed(2)}} %</td>
							</tr>
						</tbody>
					</table>
					<div class="paging" ref="paging_btns">
						<button v-bind:class="page.class" v-on:click="fn_Get_Full_Sector(i)" v-for="(page, i) in itemPages" v-bind:key="i">
							{{page.page + 1}}
						</button>
					</div>
				</div>
				<div v-else-if="drillDownLevel === 1">
					<table class="tbl sm">
						<colgroup>
							<col width="50"/>
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>종목명</th>
								<th>등락률</th>
								<th>기술투자지수</th>
								<th>기술력점수(TSS)</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(list,i) in itemList" v-bind:key="i">
									<td>{{i+1+((pageNo)*pageSize)}}</td>
									<td>{{list.isuKorNm}}</td>
									<td>{{list.priceRate.toFixed(2)}} %</td>
									<td>{{list.tii.toFixed(2)}}</td>
									<td>{{list.tss.toFixed(2)}}</td>
							</tr>
						</tbody>
					</table>
					<div class="paging" ref="paging_btns">
						<button v-if="pageNo > 9" v-on:click="fn_Get_Sector_Stks(currentSector, (pageGroupNo*10)-1)">...</button>
						<button v-bind:class="page.class" v-on:click="fn_Get_Sector_Stks(currentSector, page.page)" v-for="(page, i) in itemPages" v-bind:key="i">
							{{page.page + 1}}
						</button>
						<button v-if="pageGroupNo < pageGroups.length-1 && pageTotal > 10" v-on:click="fn_Get_Sector_Stks(currentSector, (pageGroupNo*10)+10)">...</button>
					</div>
				</div>
				<div v-else>
					<table class="tbl sm">
						<colgroup>
							<col width="50"/>
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>종목명</th>
								<th>등락률</th>
								<th>기술투자지수</th>
								<th>기술력점수(TSS)</th>
							</tr>
						</thead>
						<tbody>
							<tr v-for="(list,i) in itemList" v-bind:key="i">
									<td>{{i+1+((pageNo)*pageSize)}}</td>
									<td>{{list.isuKorNm}}</td>
									<td>{{list.priceRate.toFixed(2)}} %</td>
									<td>{{list.tii.toFixed(2)}}</td>
									<td>{{list.tss.toFixed(2)}}</td>
							</tr>
						</tbody>
					</table>
					<div class="paging" ref="paging_btns">
						<button v-if="pageNo > 9" v-on:click="fn_Get_Theme_Stks(currentSector, (pageGroupNo*10)-1)">...</button>
						<button v-bind:class="page.class" v-on:click="fn_Get_Theme_Stks(currentSector, page.page)" v-for="(page, i) in itemPages" v-bind:key="i">
							{{page.page + 1}}
						</button>
						<button v-if="pageGroupNo < pageGroups.length-1 && pageTotal > 10" v-on:click="fn_Get_Theme_Stks(currentSector, (pageGroupNo*10)+10)">...</button>
					</div>
				</div>
			</div>
		</article>
	</section>
</template>

<script>

export default {
	name: 'RankingTheme',
	mounted() {
		this.fn_Get_Top7_Sector();
		this.fn_Get_Full_Sector(0);
		this.fn_Get_Full_Sector_Cnt();
		this.$children[0].chart.vueRef = this;
	},
	methods: {
		fn_Get_Top7_Sector () {
			this.$api.fnGetApiCall('/sector/pr?issueYn=Y&pageNo=1&pageSize=7')
				.then((res) => {
					var sectors = res.data;
					const sectorChart = [];
					for (var idx in sectors) {
						if (idx == 7) {
							break;
						}
						var sector = {};
						sector.name = sectors[idx].sectorName;
						sector.y = sectors[idx].avgPriceRate;
						sector.drilldown = sectors[idx].sectorNo;
            sector.color = this.$getSectorColor(sectors[idx].sectorNo);
						sector.drilldownlevel = 1;
						sectorChart.push(sector);
					}

					var series = new Object();
					series.name = '섹터';
					series.colorByPoint = true;
					series.data = sectorChart;
					this.top5_theme_chart_options.series = series;
				})
				.catch((error) => {
					console.log(error);
				});


		},
		fn_Get_Full_Sector (pageNo) {
			this.pageNo = pageNo;
			console.log("paging: " + pageNo)
			for (var idx in this.itemPages) {
				if (this.itemPages[idx].page == pageNo) {
					this.itemPages[idx].class = "on";
				} else {
					this.itemPages[idx].class = "";
				}
			}
			this.$api.fnGetApiCall('/sector/pr?issueYn=N&pageNo=' + (pageNo + 1) + '&pageSize=7')
				.then((res) => {
					this.itemList = res.data
				})
				.catch((error) => {
					console.log(error);
				});


		},
		fn_Get_Full_Sector_Cnt () {
			this.$api.fnGetApiCall('/sector/cnt')
				.then((res) => {
					console.log("list total: " + res.data);
					this.pageTotal = Math.ceil(res.data/this.pageSize);
					console.log("page total: " + this.pageTotal);
          this.itemPages = [];
					for (var i = 0; i < this.pageTotal; i++) {
						if (i == 0) {
							this.itemPages.push({page:i, class:"on"});
						} else {
							this.itemPages.push({page:i, class:""});
						}
					}
					console.log(this.itemPages);
				})
				.catch((error) => {
					console.log(error);
				});


		},
		fn_Get_Sector_Stks (sectorNo, pageNo) {
			this.drillDownLevel = 1;
			this.currentSector = sectorNo;
			this.pageNo = pageNo;
			console.log("sectorNo, paging, pageGroupNo, length: %i, %i, %i, %i", sectorNo, pageNo, this.pageGroupNo, this.pageGroups.length);
			if (pageNo != 0) {
				this.itemPages = this.pageGroups[Math.floor(pageNo/10)];
				this.pageGroupNo = Math.floor(pageNo/10);
				for (var idx in this.itemPages) {
					if (this.itemPages[idx].page == pageNo) {
						this.itemPages[idx].class = "on";
					} else {
						this.itemPages[idx].class = "";
					}
				}

			}
			fetch('/stock/pr/sector?pageNo=' + (pageNo + 1) + '&pageSize=7&sectorNo=' + sectorNo)
				.then((response) => {
					if(response.ok) {
						return response.json();
					}
					throw new Error('error');
				})
				.then((json) => {
					this.itemList = json.stks;
					this.pageTotal = Math.ceil(json.totalCnt/this.pageSize);
					console.log("page total: " + this.pageTotal);
					if (this.pageNo == 0) {
						var _itemPages = [];
						this.pageGroups = [];
						for (var i = 0; i < this.pageTotal; i++) {
							if (i == 0) {
								_itemPages.push({page:i, class:"on"});
							} else {
								_itemPages.push({page:i, class:""});
							}
							if ((i+1)%10 == 0) {
								this.pageGroups.push(_itemPages);
								_itemPages = [];
							}
						}
						if (_itemPages.length > 0) {
							this.pageGroups.push(_itemPages);
						}
						console.log(this.pageGroups);
						this.itemPages = [];
						this.itemPages = this.pageGroups[pageNo];
						this.pageGroupNo = pageNo;
					}
				})
				.catch((error) => {
					console.log(error);
				});

		},
		fn_Get_Theme_Stks (themeNo, pageNo) {
			this.drillDownLevel = 2;
			this.currentSector = themeNo;
			this.pageNo = pageNo;
			console.log("themeNo, paging, pageGroupNo, length: %i, %i, %i, %i", themeNo, pageNo, this.pageGroupNo, this.pageGroups.length);
			if (pageNo != 0) {
				this.itemPages = this.pageGroups[Math.floor(pageNo/10)];
				this.pageGroupNo = Math.floor(pageNo/10);
				for (var idx in this.itemPages) {
					if (this.itemPages[idx].page == pageNo) {
						this.itemPages[idx].class = "on";
					} else {
						this.itemPages[idx].class = "";
					}
				}

			}
			fetch('/stock/pr/theme?pageNo=' + (pageNo + 1) + '&pageSize=7&themeNo=' + themeNo)
				.then((response) => {
					if(response.ok) {
						return response.json();
					}
					throw new Error('error');
				})
				.then((json) => {
					this.itemList = json.stks;
					this.pageTotal = Math.ceil(json.totalCnt/this.pageSize);
					console.log("page total: " + this.pageTotal);
					if (this.pageNo == 0) {
						var _itemPages = [];
						this.pageGroups = [];
						for (var i = 0; i < this.pageTotal; i++) {
							if (i == 0) {
								_itemPages.push({page:i, class:"on"});
							} else {
								_itemPages.push({page:i, class:""});
							}
							if ((i+1)%10 == 0) {
								this.pageGroups.push(_itemPages);
								_itemPages = [];
							}
						}
						if (_itemPages.length > 0) {
							this.pageGroups.push(_itemPages);
						}
						console.log(this.pageGroups);
						this.itemPages = [];
						this.itemPages = this.pageGroups[pageNo];
						this.pageGroupNo = pageNo;
					}
				})
				.catch((error) => {
					console.log(error);
				});

		},
    fn_Call_Sector_Url(sectorNo){
      this.$emit('callSectorReport',sectorNo);
    }
	},
	data() {
		return {
			itemList:[]
			,itemPages:[]
			,pageGroups:[]
			,pageGroupNo:0
			,drillDownLevel:0
			,pageNo:1
			,pageSize:7
			,pageTotal:0
			,currentSector:0
			,currentTheme:0
			,top5_theme_chart_options: {
				chart: {
					type: 'column',
					height: 320,
					width: 520,
					events: {
						drilldown: function(e) {
							console.log(e);
							if (!e.seriesOptions) {
								console.log(e.point.options.name + "/" + e.point.options.drilldown + "/" + e.point.options.drilldownlevel);
								if(e.point.options.drilldownlevel == 1) {
									fetch('/theme/pr?pageNo=1&pageSize=5&sectorNo=' + e.point.options.drilldown)
										.then((response) => {
											if(response.ok) {
												return response.json();
											}
											throw new Error('error');
										})
										.then((json) => {
											var themes = json,
											chart = this,
											drilldowns = {},
											theme = {name:e.point.name, data:[]};
											for (var idx in themes) {
												var data = {};
												data.name = themes[idx].themeName;
												data.y = themes[idx].avgPriceRate;
												data.drilldown = themes[idx].themeNo;
												data.drilldownlevel = 2;
												theme.data.push(data);
											}
											drilldowns[e.point.name] = theme;
											var series = drilldowns[e.point.name];
											chart.addSeriesAsDrilldown(e.point, series);
										})
										.catch((error) => {
											console.log(error);
										});
                  this.vueRef.fn_Call_Sector_Url(e.point.options.drilldown);
									this.vueRef.fn_Get_Sector_Stks(e.point.options.drilldown, 0);
								} else {
									this.vueRef.fn_Get_Theme_Stks(e.point.options.drilldown, 0);
								}
							}
						},
						drillup: function(e) {
							console.log(e);
							this.vueRef.drillDownLevel = 0;
							this.vueRef.fn_Get_Full_Sector(0);
							this.vueRef.fn_Get_Full_Sector_Cnt();
						}
					}
				}, title: {
					text: ''

				}, subtitle: {
					text: ''
				}, credits: {
					enabled: false
				}, accessibility: {
					announceNewData: {
						enabled: true
					}
				}, xAxis: {
					type: 'category',
					labels: {
						rotation: 0
					}
				}, yAxis: {
					title: {
						text: '등락률'
					}

				}, legend: {
					enabled: false
				}, plotOptions: {
					series: {
						borderWidth: 0,
						dataLabels: {
							enabled: true,
							format: '{point.y:.1f} %'
						}
					}
				}, tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b><br/>'
				}, series: [
					{
						name: "섹터/테마",
						colorByPoint: true,
						data: []
					}
				], drilldown: {
					series: []
				}
			}
		}
	}
}
</script>