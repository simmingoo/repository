<template>
  <div class="container">
    <div class="inner">
      <div class="top_reco">
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
            </div>
            <div class="inner_chart">
              <highcharts :constructorType="'stockChart'" class="hc" :options="chartOptions" ref="kosdaqc"></highcharts>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "kodaq",
  data() {
    return {
        kosDaqUpDown:'',
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
    }
  },
  mounted() {
    this.$refs.kosdaq1d_b.click();
  },
  methods:{
    kosdaq1d : function(event) {
      if (event) {
        if(!event.target.classList.contains('on')) {
          this.$refs.kosdaq1d_b.className = "on";
          const inqStrtDd = this.$getDateTime().substring(0,8);

          this.$api.fnGetApiCall('/sectorchart/stockIntraDay?inqStrtDd=' + inqStrtDd + '&issuecode=Q1&marketcode=kosdaq')
              .then((res) => {
                var json = res.data;
                var results = json.hisLists;
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
                  //this.chartOptions.series[0].data.push([tempDate.getTime(), results[idx].inddClsprc]);
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
                      maxDate = tempDate;
                    }
                  }
                }
                if (maxDate.getTime() > 0) {
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
                  console.log("chartOptions.series[0].data : %s",chartData)
                  console.log("chartOptions.yAxis.min : %s",min)
                  console.log("chartOptions.yAxis.max : %s",max)
                  console.log("chartOptions.yAxis.plotLines[0].value : %s",json.prevTrdPrc)
                  console.log("chartOptions.plotOptions.area.threshold : %s",json.prevTrdPrc)
                  console.log("chartOptions.plotOptions.area.fillColor : %s",'#FAE8E8')
                  console.log("chartOptions : %s", JSON.stringify(this.chartOptions));
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
                  console.log("chartOptions.series[0].data : %s", chartData);
                  console.log("chartOptions.yAxis.min : %s", chartData);
                  console.log("chartOptions.yAxis.max : %s", chartData);
                  console.log("chartOptions : %s", JSON.stringify(this.chartOptions));
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
    }
  }
}
</script>