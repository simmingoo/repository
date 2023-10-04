<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="skill"/>
			<div class="p_top"><h1>기업 기술 비교</h1></div>
			<section class="content">
				<h2>기업 기술비교 개요</h2>
				<ul class="list">
					<li>경쟁기업들의 기술 관련 항목을 상대적으로 비교한 결과를 확인할 수 있습니다.</li>
					<li>아래 입력 박스에 비교하고자하는 기업detailCorp명을 직접 입력하여 검색하여 추가하실 수 있습니다.</li>
					<li>최대 5개의 기업을 검색하여 추가하실 수 있으며, 미 입력 시 기술 기준 동종 분야에서 규모가 유사한 기업을 기본으로 비교하게 됩니다.</li>
				</ul>
				<h2>기업 기술비교 기업 선택</h2>
				<p class="desc">미 입력 시 자동으로 기업이 선택됩니다.</p>
				<div class="area_form">
					<div class="bx_search">
						<input type="text" class="ipt" placeholder="비교 대상 기업명 또는 기업코드 입력"><button type="button">검색</button>
					</div>
					<div class="bx_search">
						<input type="text" class="ipt" placeholder="비교 대상 기업명 또는 기업코드 입력"><button type="button">검색</button>
					</div>
					<div class="bx_search">
						<input type="text" class="ipt" placeholder="비교 대상 기업명 또는 기업코드 입력"><button type="button">검색</button>
					</div>
					<div class="bx_search">
						<input type="text" class="ipt" placeholder="비교 대상 기업명 또는 기업코드 입력"><button type="button">검색</button>
					</div>
					<div class="bx_search">
						<input type="text" class="ipt" placeholder="비교 대상 기업명 또는 기업코드 입력"><button type="button">검색</button>
					</div>
				</div>
				<div class="btns">
					<button class="btn"><strong>기업 기술비교 조회</strong></button>
				</div>
				<h2>기업 기술비교 결과</h2>
				<ul class="list">
					<li>경쟁 기업들의 등록 특허를 대상으로 <strong class="txt_point">기술 수준을 상대적으로 비교</strong>	한 결과입니다.</li>
					<li>아래 경쟁력 분석 차트에서 수평 축은 기술분야 특허의 평균점수(APES)를 나타내며 수직 축은 각 기술 분야에서 보유한 등록 특허 수를 표시합니다.</li>
					<li>고급 기술을 많이 보유할 수록 오른쪽 상단에 표시되고 보유한 기술이 작고 기술 수준이 낮을 수록 왼쪽 아래쪽에 위치하게 됩니다.</li>
				</ul>
				<div class="bx_chart chart_prepare">
					<h3>기업 기술력 비교</h3>
          <highcharts :options="competitionChart"/>
				</div>
				<table class="tbl">
					<colgroup>
						<col width="80">
						<col>
						<col id="border_red">
						<col>
						<col width="150">
						<col width="160">
						<col>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>순위</th>
							<th>기업명</th>
							<th>기술투자 지수 <Tooltip desc='기술투자지수란 기업의 시가총액을 TSS 점수(위즈도메인이 개발한 통계기법으로 산출)로 나눈 값입니다. 보유한 기술력대비 주기가 어느정도인지 가늠하는 수치로 동종기술로 분류된 테마내에서 상대비교를 통해 기업이 가치를 평가할 수 있는 보조지표입니다.  기술투자자가 낮을수록 "기술력대비 저평가" 되어있어 기술력관점에서 투자매력도가 높습니다. 즉, 기술투자지수가 낮다면 기업이 보유한 기술력보다 주가가 상대적으로 싸다는 의미인 것입니다.'/><span class="txt_gray">(시총/TSS)</span></th>
							<th>기술력점수 <Tooltip desc='TSS는 기업이 가진 특허의 규모(quantity)와 기술 수준(quality)를 동시에 측정한 기술 경쟁력 지수입니다. 기업이 보유한 전체 특허를 점수화한 것으로 수치가 클 수록 수록 기술 경쟁력이 높음을 의미하며 다른 기업의 수치와 비교하여 상대적으로 평가할 수 있습니다.'/><span class="txt_gray">(TSS)</span></th>
							<th>기술경쟁력등급 <Tooltip desc='TCG는 기업이 취득한 특허들을 평가하여 합산한 점수와 이 점수를 기반으로 한 업계 순위에 근거하여 생성되었습니다. 특허 평가 점수는 ㈜위즈도메인이 개발한 통계기법에 의해 계산된 값입니다. 이 점수는 객관적인 평가기준에 따라 동종 기술분야의 특허들과 상대 비교하여 산출됩니다.'/><span class="txt_gray">(TCG)</span></th>
							<th>특허평가평균점수 <Tooltip desc='APES는 각 기술부문별 특허의 점수를 평균한 것으로, 개별 특허의 평가 점수는 특허 빅데이터 및 ㈜위즈도메인의 특허평가엔진(Patent Evaluation Engine)을 이용하여 산출합니다. 특허 평가 요소는 10여 가지가 있으며 평가 요소 중 하나를 예로 들면 다른 특허들로부터 인용을 많이 당한 특허는 좋은 점수를 받습니다.'/><span class="txt_gray">(APES)</span></th>
							<th>특허등록<span class="txt_gray">(건)</span></th>
							<th>시가총액<span class="txt_gray">(억원)</span></th>
							<th>추가추이<span class="txt_gray">(최근30일)</span></th>
						</tr>
					</thead>
					<tbody>
            <template v-for="(similarCompany,i) in similarCompanys">
              <tr v-if="similarCompany.isuSrtCd != isuSrtCd" v-bind:key="i">
                <td>{{i}}</td>
                <td>{{similarCompany.name}}</td>
                <td>{{similarCompany.tii.toFixed(2)}}</td>
                <td>{{similarCompany.tss.toFixed(2)}}</td>
                <td>{{similarCompany.tcg}}</td>
                <td>{{similarCompany.apes}}</td>
                <td>{{similarCompany.patentCnt}}</td>
                <td>{{$addComma(similarCompany.mktCap)}}</td>
                <td>
                  <span :class="$fnGetPriceClassVal(similarCompany.rctStkPrice)">
                    {{ $fnGetPriceTxtVal(similarCompany.rctStkPrice)}}%
                  </span>
                </td>
              </tr>
            </template>
					</tbody>
				</table>
			</section>
		</div>
  </div>
</template>

<script>
import TopTit from '@/components/layout/TopTit.vue';
import Tap from '@/components/layout/Tap.vue';
import Tooltip from '@/components/common/Tooltip.vue';

export default {
  name: 'Compare',
  computed:{
    competitionChart() {
      let option = {...competition_chart_option}
      let y = [];
      option.series[0].data = this.quadrantCompetitors.map(quadrantCompetitor => {
        const data = {...competition_chart_data}
        data.name = quadrantCompetitor.quadrant;
        data.x = quadrantCompetitor.pes;
        data.y = quadrantCompetitor.patentCnt;
        y.push(quadrantCompetitor.patentCnt);
        return data;
      })
      return option;
    }
  },
	data() {
		return {
      bizno:'',
      isuSrtCd:'',
      similarCompanys:[],
      quadrantCompetitors:[],
		}
	},
	components : {
		TopTit,
		Tap,
		Tooltip
	},
	metaInfo: {
		title: '기술비교 | 10bagger',
	},
  mounted() {
    this.fn_Get_Skill_Competition();
  },
  methods: {
    fn_Get_Skill_Competition() {
      this.$api.fnGetApiCall('/report/competition?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        this.similarCompanys = json.similarCompanys;
        this.quadrantCompetitors = json.quadrantCompetitors;
      })
      .catch((err) => {
        console.log(err);
      })
    }

  },
  beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_detail";
  },
  created() {
    this.bizno = sessionStorage.getItem('detailBizNo');
    this.isuSrtCd = sessionStorage.getItem('detailIsuSrtCd');
  }
}
const competition_chart_option = {
  chart: {
    type: 'bubble',
    backgroundColor:'#F2F2F2',
    plotBorderWidth: 1,
    width:1100,
    height:400,
    marginLeft:200,
    marginRight:200,
    events: {
      load: function() {

        var chart = this;
        chart.renderer.label('<strong style="color:black;font-size:11px">다기술보유기업</strong></br>기술 수준은 높지 않지만</br> 특허를 많이 보유하고 있는</br> 기업', 10, 10)
            .add()
            .css({
              fontFamily:"NanumSquare",
              color:"#595959",
              fontSize:"10px",
              letterSpacing:"-0.5px"
            })
        chart.renderer.label('<strong style="color:black;font-size:11px">악세 기업</strong></br>기술 수준은 높지않고</br> 보유한 특허 수도 많지 않은</br> 기업', 10, 300)
            .add()
            .css({
              fontFamily:"NanumSquare",
              color:"#595959",
              fontSize:"10px",
              letterSpacing:"-0.5px"
            })
        chart.renderer.label('<strong style="color:black;font-size:11px">선도 기업</strong></br>기술 수준이 높고</br> 특허를 많이 보유한</br> 기업', 950, 10)
            .add()
            .css({
              fontFamily:"NanumSquare",
              color:"#595959",
              fontSize:"10px",
              letterSpacing:"-0.5px"
            })
        chart.renderer.label('<strong style="color:black;font-size:11px">전문 기업</strong></br>상대적으로 보유한 특허가</br> 많지는 않지만 높은 수준의</br> 기술을 보유한 기업', 950, 300)
            .add()
            .css({
              fontFamily:"NanumSquare",
              color:"#595959",
              fontSize:"10px",
              letterSpacing:"-0.5px"
            })
      }
    },

  },

  legend: {
    enabled: false
  },

  title: {
    text: ''
  },
  exporting: {
    enabled: false
  },
  credits: {enabled: false},
  xAxis: {
    tickAmount: 3,
    gridLineColor: '#808080',
    gridLineWidth: 0.7,
    lineWidth: 0,
    minorGridLineWidth: 0,
    minorTickLength: 0,
    tickLength: 0,
    min:0,
    max:100,
    tickInterval:50,
    title: {
      text: '특허평가 평균점수(APES)',
      style: {
        fontFamily: 'NanumSquare',
        color: '#595959',
        fontSize: '13px',
        letterSpacing: '-0.5px',
      }
    },
    labels: {
      style: {
        fontSize: '7px',
        color: '#BFBFBF'
      }
    }

  },

  yAxis: {
    gridLineColor: '#808080',
    tickAmount: 3,
    min:0,
    gridLineWidth: 0.7,
    minorGridLineWidth: 0,
    title: {
      text: '특허보유건수',
      style: {
        fontFamily: 'NanumSquare',
        color: '#595959',
        fontSize: '10px',
        letterSpacing: '-0.5px',
      }
    },
    plotLines: [
      {
        width: 1,
        value: 0
      }
    ],
    labels: {
      style: {
        fontSize: '7px',
        color: '#BFBFBF'
      }
    }

  },
  tooltip: {
    useHTML: true,
    headerFormat: '<div>',
    pointFormat: '<p>기업명: {point.name}</p>'+
        '<p>특허평가 평균점수: {point.x}</p>' +
        '<p>특허보유건수: {point.y}</p>',
    footerFormat: '</div>',

  },
  plotOptions: {
    series: {
      dataLabels: {
        enabled: true,
        format: '{point.name}',
        style: {
          fontSize: '15px',
        },
        allowOverlap:true,
        y:-9,
        animation:false
      }
    },
    bubble: {
      minSize: 15,
      maxSize: 15
    },
  },
  series: [{
    data: [],
    name: ''
  }],
};
const competition_chart_data = {
  name:'',
  x:'',
  y:'',
  z:1
};
</script>