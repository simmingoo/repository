<template>
  <div class="container">
    <div class="inner">
      <div class="col">
        <h2 class="mb50">섹터별 테마유형 추이</h2>
        <!-- <h3 class="tit_chart mt40 mb50">섹터별 테마유형 추이</h3> -->
        <highcharts  :options="chartOptions" />
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
    </div>
  </div>

</template>

<script>
export default {
  name: "Sector",
  data() {
    return {
        sectorLists:[],
        itemChartData:'',
        chartOptions: {
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

                  //
                  // click:(e) => {
                  //   this.fn_Get_Stock_Rcmn(e.point.sectorNo);
                  //   this.fn_Get_SectorRanking_Chart_PriceRate(e.point.sectorNo);
                  //   this.settargetSectorNo(e.point.sectorNo);
                  //   this.$refs.sector.$children[0].$refs['sector_'+e.point.sectorNo][0].click()
                  // },
                  //     legendItemClick:(e) => {
                  //   this.fn_Get_Stock_Rcmn(e.target.sectorNo);
                  //   this.fn_Get_SectorRanking_Chart_PriceRate(e.target.sectorNo);
                  //   this.settargetSectorNo(e.target.sectorNo)
                  //   return false;
                  // },
                  //
                  //
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
            chartOptions: {
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
    }
  },
  methods:{
    fn_Get_Item_ChartData () {
      this.$api.fnGetApiCall("/sectorchart/itemdata")
          .then((res) => {
            var series_data = [], thedata= res.data;
            for (var i=0; i< thedata.length; i++) {
              let themeCht = this.fn_Get_Random_Cht();
              var sector = {
                sectorName:thedata[i].sectorName,
                themeCnt:themeCht,
                color:thedata[i].color,
                sectorNo:thedata[i].sectorNo,
              }
              series_data.push([thedata[i].sectorName, themeCht, thedata[i].color,thedata[i].sectorName, thedata[i].sectorNo]);
              this.sectorLists.push(sector);
            }
            console.log("chartOptions : %s",JSON.stringify(this.chartOptions))
            console.log("chartOptions.series[0].data : %s",series_data)
            this.chartOptions.series[0].data = series_data
          })
          .catch((err) => {
            console.log(err)
          })
    },
    fn_Get_Random_Cht() {
      const rand_1_10 = Math.floor(Math.random() * 10) + 1;
      return rand_1_10;
    }
  },
  mounted() {
    this.fn_Get_Item_ChartData();
  }
}
</script>