
<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="stock"/>
			<div class="p_top"><h1>기업 기본 정보 및 개요</h1></div>
			<section class="content">
				<h2>종목섹터 및 테마</h2>
				<table class="tbl type_col">
					<colgroup>
						<col width="130px"/>
						<col width="244px"/>
						<col width="130px"/>
						<col/>
						<col width="130px"/>
						<col width="176px"/>
					</colgroup>
					<tbody>
						<tr>
							<th>대표섹터</th>
							<td><span>{{ keySectorNm }}</span> <em class="bar">|</em> <strong class="txt_blue f18">{{ rankTssInKeySector }}위</strong><span class="txt_gray">({{ rankTssInKeySectorMax }}기업중)</span></td>
							<th>대표테마</th>
							<td><strong class="f18">{{ keyThemeNm }}</strong> <em class="bar">|</em> <strong class="txt_blue f18">{{rankTssInKeyTheme}}위</strong><span class="txt_gray">({{ rankTssInKeyThemeMax }}기업중)</span></td>
							<th>시총</th>
							<td class="txt_red"><span class="f20">{{ mktCap }}</span><span>억원({{ mktCapScale }})</span></td>
						</tr>
						<tr>
							<th class="vtop">소속섹터/테마</th>
							<td colspan="5" class="pl10">
                <span class="txt_tag" v-for="(theme,i) in themeNames" v-bind:key="i" >#{{theme}}</span>
							</td>
						</tr>
					</tbody>
				</table>

				<h2>매매신호 및 주가차트</h2>
				<article class="area coln">
<!--					<div class="bx_stock_info shrink">-->
<!--						<div class="top">-->
<!--							<span class="sub_tit">텐베거매매신호</span>-->
<!--							<h1>오늘매도</h1>-->
<!--						</div>-->
<!--						<div class="cont">-->
<!--							<ul>-->
<!--								<li>-->
<!--									<div class="tit">신호발생</div>-->
<!--									<div class="desc">신호 11월 10일</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">신호가</div>-->
<!--									<div class="desc txt_red"><strong class="f18">99,800</strong>원</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">보유기간</div>-->
<!--									<div class="desc">7일</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">매수추천일</div>-->
<!--									<div class="desc">11월 15일</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">매수가</div>-->
<!--									<div class="desc txt_red"><strong class="f18">99,800</strong>원</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">현재수익률</div>-->
<!--									<div class="desc txt_blue"><strong class="f18">-5</strong>%</div>-->
<!--								</li>-->
<!--								<li>-->
<!--									<div class="tit">목표매도가</div>-->
<!--									<div class="desc txt_red"><strong class="f18">99,800</strong>원</div>-->
<!--								</li>-->
<!--							</ul>-->
<!--						</div>-->
<!--					</div>-->
					<div class="bt col">
						<div class="c_tap type1">
							<button type="button" ref="candleDay" class="tap on" v-on:click="fn_Get_Stock_Chart_History(-3,'M')">일봉</button>
							<button type="button" ref="candleWeek" class="tap"  v-on:click="fn_Get_Stock_Chart_History(-1,'Y')">주봉</button>
							<button type="button" ref="candleMonth" class="tap"  v-on:click="fn_Get_Stock_Chart_History(-3,'Y')">월봉</button>
						</div>
						<div class="bx_chart"><!--차트 완료 되면 chart_prepare 클래스는 빼주세요-->
              <highcharts :constructorType="'stockChart'" :options="stockChartOption"/>
						</div>
					</div>
				</article>

			</section>
			<div class="p_top"><h1>기술경쟁력 및 기업가치 추정</h1></div>
			<section class="content">
				<div class="unit bx_grage">
					<div class="ico_grade">
						<strong>{{ tcg }}</strong>
					</div>
					<div class="col"> 
						<h2>TCG(기술경쟁력) 등급</h2>
						<strong class="txt_blue">동사의 기술 경쟁력은 {{ tcg }} 로 업계 상위 <u>{{ tcgRatio }}</u>로 평가됩니다.</strong>
						<span class="txt_gray">기술경쟁력등급(TCG)은 취득한 특허들을 평가하여 합산한 점수와 이 점수를 기반으로 한 업계순위에 근거하여 생성되었습니다.</span>
					</div>
				</div>
				<h2>TSS(기술력) 점수</h2>
				<article class="area col3">
					<BxScore v-for="(data, index) in scoreData" :key="index" :data="data"/>
				</article>
				<h2>바이오니아 기술투자지수</h2>
				<article class="area col3">
					<BxScore v-for="(data, index) in comScoreData" :key="index" :data="data"/>
				</article>
				<h2>기업가치 추정</h2>
				<article class="area_value">
					<div class="col2">
						<div class="col mr35">
							<p>
								<strong class="txt_bg">{{ isuKorNm }} 특허보유건수는&nbsp;</strong><strong class="txt_bg txt_red">{{ patentCount }}&nbsp;</strong><strong class="txt_bg">건이며,</strong> <br/>
								<strong class="txt_bg">APES(Average Patent Evaluation Score:특허평가평균점수)는&nbsp;</strong><strong class="txt_bg txt_red">{{ apes }}&nbsp;</strong><strong class="txt_bg">점입니다.</strong>
							</p>
							<p>
								동종분야 기업들의 평균적인 기술력 대비 주가에 비하여 현재 기술력대비<br/>
								주가의 상태는<span class="txt_grean">{{ estimation }}</span> 되어있습니다.<br/>
								최근 5년간 동종업계 상위 10개 기업 시총 변화및 증가율은 <span class="txt_red">7.30%</span> 입니다.
							</p>
						</div>
						<div class="col bx_value_img">
							<div class="bx">
								<span class="label" :style="{left:quantVal+'%'}">
									{{ isuKorNm }}
								</span>
								<img src="@/img/detail/img_quant2.jpg" alt="평가배경">
							</div>
						</div>
					</div>
				</article>
			</section>
		</div>
  </div>
</template>

<script>
import TopTit from '@/components/layout/TopTit.vue';
import Tap from '@/components/layout/Tap.vue';
import BxScore from '@/components/item/BxScore.vue';

export default {
  name: 'Quant',
  computed:{
    stockChartOption() {
      let option = {...stock_chart_option}
      option.series[0].data = this.chartData.map(chartData => {
        let data =[];
        data = [
          chartData[0],
          chartData[1],
          chartData[2],
          chartData[3],
          chartData[4],
        ]
        return data;
      })
      option.series[1].data = this.volumeData.map(volumeData => {
        let data =[];
        data = ({
          x:volumeData[0].x,
          y:volumeData[0].y,
          color:volumeData[0].color
        });
        return data;
      })

      return option;
    }
  },
	data() {
		return {
      chartData:[],
      volumeData:[],
      keySectorNm:'',
      keyThemeNm:'',
      rankTssInKeySector:'',
      rankTssInKeySectorMax:'',
      rankTssInKeyTheme:'',
      rankTssInKeyThemeMax:'',
      mktCap:'',
      mktCapScale:'',
      themeNames:[],
      estimation:'',

			scoreData:[
				{
					tit:"",
					score: "",
					total: "",
					myScore: "",
					position: 0, //인라인으로 들어갈 퍼센트
				},{
          tit:"",
          score: "",
          total: "",
          myScore: "",
          position: 0, //인라인으로 들어갈 퍼센트
        },{
          tit:"",
          score: "",
          total: "",
          myScore: "",
          position: 0, //인라인으로 들어갈 퍼센트
        },
			],
			comScoreData:[
				{
          tit:"",
          score: "",
          total: "",
          myScore: "",
          position: 0, //인라인으로 들어갈 퍼센트
				},{
          tit:"",
          score: "",
          total: "",
          myScore: "",
          position: 0, //인라인으로 들어갈 퍼센트
				},{
          tit:"",
          score: "",
          total: "",
          myScore: "",
          position: 0, //인라인으로 들어갈 퍼센트
				}
			],
      bizno:'',
      isuSrtCd:'',
      patentCount:'',
      apes:'',
      tcg:'',
      tcgRatio:'',
      isuKorNm:'',
      quantVal:'',
		}
	},
	components : {
		TopTit,
		BxScore,
		Tap
	},
	metaInfo: {
		title: '종목개요 | 10bagger ',
	},
  mounted() {
    this.fn_Get_Stock_Detail();
    this.$refs.candleDay.click();
  },
  methods:{
    fn_Get_Stock_Detail() {
      this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/report/outline?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
          var json = res.data;
          this.isuKorNm = json.isuKorNm;
          this.keySectorNm = json.keySectorNm;
          this.keyThemeNm = json.keyThemeNm;
          this.rankTssInKeySector = json.rankTssInKeySector;
          this.rankTssInKeySectorMax = json.rankTssInKeySectorMax;
          this.rankTssInKeyTheme = json.rankTssInKeyTheme;
          this.rankTssInKeyThemeMax = json.rankTssInKeyThemeMax;
          this.mktCap = this.$addComma(json.mktCap);
          this.mktCapScale = json.mktCapScale;
          this.estimation = json.estimation;
          this.patentCount = json.patentCount;
          this.apes = json.corp.apes;
          this.tcg = json.tcg;
          this.tcgRatio = json.tcgRatio;
          for(let idx in json.themes) {
            this.themeNames.push(json.themes[idx].themeName);
          }
          this.scoreData[0].tit = json.isuKorNm;
          this.scoreData[0].score = json.tss;
          this.scoreData[0].total = json.rankTssMax;
          this.scoreData[0].myScore = json.rankTss;
          this.scoreData[0].position = json.rankTssPer;
          // this.scoreData[0].position = this.$calcPercent(json.rankTss, json.rankTssMax);

          this.scoreData[1].tit = '대표섹터 평균 TSS';
          this.scoreData[1].score = json.tssAvgKeySector;
          this.scoreData[1].total = json.rankTssInKeySectorMax;
          this.scoreData[1].myScore = json.rankTssInKeySector;
          this.scoreData[1].position = json.rankTssInKeySectorPer;
          // this.scoreData[1].position = this.$calcPercent(json.rankTssInKeySector, json.rankTssInKeySectorMax);

          this.scoreData[2].tit = '대표테마 평균 TSS';
          this.scoreData[2].score = json.tssAvgKeyTheme;
          this.scoreData[2].total = json.rankTssInKeyThemeMax;
          this.scoreData[2].myScore = json.rankTssInKeyTheme;
          this.scoreData[2].position = json.rankTssInKeyThemePer;
          // this.scoreData[2].position = this.$calcPercent(json.rankTssInKeyTheme, json.rankTssInKeyThemeMax);

          this.comScoreData[0].tit = json.isuKorNm;
          this.comScoreData[0].score = json.tii;
          this.comScoreData[0].total = json.rankTiiMax;
          this.comScoreData[0].myScore = json.rankTii;
          this.comScoreData[0].position = json.rankTiiPer;
          // this.comScoreData[0].position = this.$calcPercent(json.rankTii, json.rankTiiMax);

          this.comScoreData[1].tit = '대표섹터 평균 기술투자지수';
          this.comScoreData[1].score = json.tiiAvgKeySector;
          this.comScoreData[1].total = json.rankTiiInKeySectorMax;
          this.comScoreData[1].myScore = json.rankTiiInKeySector;
          this.comScoreData[1].position = json.rankTiiInKeySectorPer;
          // this.comScoreData[1].position = this.$calcPercent(json.rankTiiInKeySector, json.rankTiiInKeySectorMax);

          this.comScoreData[2].tit = '대표테마 평균 기술투자지수';
          this.comScoreData[2].score = json.tiiAvgKeyTheme;
          this.comScoreData[2].total = json.rankTiiInKeyThemeMax;
          this.comScoreData[2].myScore = json.rankTiiInKeyTheme;
          this.comScoreData[2].position = json.rankTiiInKeyThemePer;
          // this.comScoreData[2].position = this.$calcPercent(json.rankTiiInKeyTheme, json.rankTiiInKeyThemeMax);
          //2/5/8
          if(this.estimation == '저평가') {
            this.quantVal = 20;
          } else if(this.estimation == '고평가') {
            this.quantVal = 80;
          } else {
            this.quantVal = 50;
          }
      })
      .catch((err) => {
        console.log(err);
      })
    },
    async fn_Get_Stock_Chart_History(dateVal, dateType) {
      let inqEndDd, inqStrtDd,marketcode,trnsmCycleTpCd;
      inqEndDd = this.$getCurrentDate().substr(0,8);
      inqStrtDd = this.$getCalcDate(dateVal,dateType).substr(0,8);
      if(dateVal == -3 && dateType == 'M') {
        trnsmCycleTpCd = 'D'
        this.$refs.candleDay.classList.add("on");
        this.$refs.candleWeek.classList.remove("on");
        this.$refs.candleMonth.classList.remove("on");
      } else if (dateVal == -1 && dateType == 'Y') {
        trnsmCycleTpCd = 'W'
        this.$refs.candleDay.classList.remove("on");
        this.$refs.candleWeek.classList.add("on");
        this.$refs.candleMonth.classList.remove("on");
      } else if (dateVal == -3 && dateType == 'Y') {
        trnsmCycleTpCd = 'M'
        this.$refs.candleDay.classList.remove("on");
        this.$refs.candleWeek.classList.remove("on");
        this.$refs.candleMonth.classList.add("on");
      }
      console.log(inqEndDd);
      console.log(inqStrtDd);
      await this.$api.fnGetApiCall('/report/outline?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        marketcode = json.mkt.toLowerCase();
      })
      .catch((err) => {
        console.log(err);
      })
      this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/stk/history?reqCnt=100&trnsmCycleTpCd='+trnsmCycleTpCd+'&inqStrtDd='+inqStrtDd+'&inqEndDd='+inqEndDd+'&issuecode='+this.isuSrtCd+'&marketcode='+marketcode)
      .then((res) => {
        let stockHisLists = res.data.hisLists;
        this.chartData = [];
        this.chartData = stockHisLists.map(item => {
              let data = [];
              data.push(
                  this.fnGetTime(item.trdDd),
                  item.opnprc,
                  item.hgprc,
                  item.lwprc,
                  item.trdPrc
              );
              return data;
        })
        this.volumeData = [];
        this.volumeData = stockHisLists.map(item => {
          let volume = [];
          let color = 'red';
          if(item.trdPrc > item.opnprc) {
            color = 'red'
          }else {
            color = 'blue'
          }
          volume.push({
            x:this.fnGetTime(item.trdDd), // the date
            y:item.accTrdvol, // the volume
            color:color
          });
          return volume;
        })
      })
      .catch((err) => {
        console.log(err)
      })
    },
    fnGetTime(date_str){
      return Date.UTC(
          parseInt(date_str.substring(0,4)),
          parseInt(date_str.substring(4,6))-1,
          parseInt(date_str.substring(6,8)),
          parseInt(0),
          parseInt(0)
      )
    },
  },
  beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_detail";
      // document.getElementsByTagName("nav")[0].classList.add("on");
  },
  created() {
    this.bizno = sessionStorage.getItem('detailBizNo');
    this.isuSrtCd = sessionStorage.getItem('detailIsuSrtCd');
  }
}
const stock_chart_option = {
  credits:{
    enabled:false
  },
  xAxis:{
    type:'datetime',
    labels: {
      format: '{value:%Y-%m-%d}'
    },
  },
  rangeSelector: {
    allButtonsEnabled: false,
    buttons: [
    ],
    inputEnabled: false,
    selected: 0
  },
  title: {
    text: ''
  },

  chart:{
    width:1200,
    height:300
  },
yAxis: [{
    labels: {
      align: 'right',
      x: -3
    },
    title: {
      text: ''
    },
    height: '60%',
    lineWidth: 2,
    resize: {
      enabled: true
    }
  }, {
    labels: {
      align: 'right',
      x: -3
    },
    title: {
      text: ''
    },
    top: '65%',
    height: '35%',
    offset: 0,
    lineWidth: 2
  }],

  tooltip: {
    split: true,
    shared: true,
    formatter() {
      let data;
      let date = new Date(this.x);
      let year = date.getFullYear();
      let month = ("0" + (1 + date.getMonth())).slice(-2);
      let day = ("0" + date.getDate()).slice(-2);


      data = '거래일:'+year + "-" + month + "-" + day
      data += '<br>시가:'+addComma(this.points[0].point.open)
      data += '<br>종가:'+addComma(this.points[0].point.close)
      data += '<br>고가:'+addComma(this.points[0].point.high)
      data += '<br>저가:'+addComma(this.points[0].point.low)
      data += '<br>거래량:'+addComma(this.points[1].y)
      return data;
    },
  },
  plotOptions: {
    candlestick: {
      color: 'blue',
      upColor: 'red'
    }
  },
  navigator: {
    enabled: false,
  },
  series: [
  {
    type: 'candlestick',
    name: '',
    data: '',
  },
    {
    type: 'column',
    name: '거래량',
    data: '',
    yAxis: 1,
    dataGrouping: {
      units: []
    }
  }],
};
function addComma(value)  {
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
</script>