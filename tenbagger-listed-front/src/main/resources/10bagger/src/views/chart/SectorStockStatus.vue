<template>
  <div class="container">
    <div class="inner">
      <div class="col">
        <div class="bx_chart mt35">
          <highcharts  :options="chartOptions" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SectorStockStatus",
  data() {
    return {
      chartOptions: {
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
    }
  },
  methods:{
    fnGetTime(date_str){
      return Date.UTC(
          parseInt(date_str.substring(0,4)),
          parseInt(date_str.substring(4,6))-1,
          parseInt(date_str.substring(6,8)),
          0,
          0
      )
    },
    fn_Get_SectorRanking_Chart_PriceRate(sectorNo) {
      this.$api.fnGetApiCall("/sectorchart/priceRate/"+sectorNo)
          .then((res) => {
            var json = res.data;
            var chartData = [];
            for(var idxKospi in json.kospi) {
              chartData.push([this.fnGetTime(json.kospi[idxKospi].trdDd),json.kospi[idxKospi].priceRate]);
            }
            console.log('chartOptions.series[0].data : %s',chartData)
            this.chartOptions.series[0].data = chartData;

            chartData = [];
            for(var idxKosdaq in json.kosdaq) {
              chartData.push([this.fnGetTime(json.kosdaq[idxKosdaq].trdDd), json.kosdaq[idxKosdaq].priceRate]);
            }
            console.log('chartOptions.series[1].data : %s',chartData)
            this.chartOptions.series[1].data = chartData;

            chartData = [];
            for(var idxSector in json.sector) {
              chartData.push([this.fnGetTime(json.sector[idxSector].trdDd), json.sector[idxSector].priceRate]);
            }
            console.log('chartOptions.series[2].data : %s',chartData)
            this.chartOptions.series[2].data = chartData;
            this.sectorName = json.sectorName;
            console.log('chartOptions : %s',JSON.stringify(this.chartOptions));

          });

    },
  },
  mounted() {
    this.fn_Get_SectorRanking_Chart_PriceRate(1);
  }
}
</script>
