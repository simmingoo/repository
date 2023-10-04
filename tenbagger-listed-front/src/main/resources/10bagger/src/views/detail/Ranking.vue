
<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="ranking"/>
			<div class="p_top"><h1>대표 섹터 정보</h1></div>
			<section class="content">
				<h2>섹터 정보</h2>
				<table class="tbl type_col">
					<colgroup>
						<col width="215px"/>
						<col/>
						<col width="215px"/>
						<col/>
					</colgroup>
					<tbody>
						<tr>
							<th>대표섹터</th>
							<td>제약바이오</td>
							<th>{{isuKorNm}} 투자매력도 순위</th>
							<td><strong class="txt_blue f18">{{rankTii}}</strong><span>위 / {{rankTiiMax}}개 기업 중</span></td>
						</tr>
						<tr>
							<th>섹터평균 기술투자지수</th>
							<td class="txt_red"><strong class="f18">{{ tiiAvgKeySector }}</strong> <span>점</span></td>
							<th>{{isuKorNm}} 기술투자지수</th>
							<td>
								<strong class="txt_red f18">{{ tii }}</strong> <span class="txt_red">점</span>
								<em class="bar">|</em>
								<strong class="txt_blue f18">{{rankTiiInKeySectorMax}}</strong><span class="txt_blud">위</span><span> / {{ rankTiiInKeySectorMax }}개 기업 중</span>
							</td>
						</tr>
						<tr>
							<th>섹터평균 기술력(TSS) 점수</th>
							<td class="txt_red"><strong class="f18">{{ tssAvgKeySector }}</strong> <span>점</span></td>
							<th>{{isuKorNm}} 기술력(TSS) 점수</th>
							<td>
								<strong class="txt_red f18">{{ tss }}</strong> <span class="txt_red">점</span>
								<em class="bar">|</em>
								<strong class="txt_blue f18">{{ rankTssInKeySector }}</strong><span class="txt_blud">위</span><span> / {{ rankTssInKeySectorMax }}개 기업 중</span>
							</td>
						</tr>
					</tbody>
				</table>
				<h2>섹터 개요</h2>
				<div class="desc">
          {{keySectorDesc}}
				</div>
        <h2>섹터 랭킹</h2>
				<table class="tbl">
					<colgroup>
						<col width="70">
						<col>
						<col id="border_red">
						<col>
						<col>
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
							<th>특허보유건수<span class="txt_gray">(건)</span></th>
							<th>시가총액<span class="txt_gray">(억원)</span></th>
						</tr>
					</thead>
					<tbody>
          <tr v-for="(sectorStock,i) in sectorStockList" v-bind:key="i">
            <td>{{i+1}}</td>
            <td>{{sectorStock.isuKorNm}}</td>
            <td>{{sectorStock.tii.toFixed(2)}}</td>
            <td>{{sectorStock.tss.toFixed(2)}}</td>
            <td>{{sectorStock.tcg}}</td>
            <td>{{sectorStock.apes.toFixed(2)}}</td>
            <td>{{sectorStock.patentCount}}</td>
            <td>{{$addComma(sectorStock.mktCap)}}</td>
          </tr>
					</tbody>
				</table>
			</section>
			<div class="p_top"><h1>대표 테마 정보</h1></div>
			<section class="content">
				<h2>테마 정보</h2>
				<table class="tbl type_col">
					<colgroup>
						<col width="215px"/>
						<col/>
						<col width="215px"/>
						<col/>
					</colgroup>
					<tbody>
						<tr>
							<th>대표테마</th>
							<td>{{ keyThemeNm }}</td>
							<th>{{isuKorNm}} 투자매력도 순위</th>
							<td><strong class="txt_blue f18">{{rankTii}}</strong><span>위 / {{rankTiiMax}}개 기업 중</span></td>
						</tr>
						<tr>
							<th>테마평균 기술투자지수</th>
							<td class="txt_red"><strong class="f18">{{ tiiAvgKeyTheme }}</strong> <span>점</span></td>
							<th>{{isuKorNm}} 기술투자지수</th>
							<td>
								<strong class="txt_red f18">{{ tii }}</strong> <span class="txt_red">점</span>
								<em class="bar">|</em>
								<strong class="txt_blue f18">{{ rankTiiInKeyTheme }}</strong><span class="txt_blud">위</span><span> / {{ rankTiiInKeyThemeMax }}개 기업 중</span>
							</td>
						</tr>
						<tr>
							<th>테마평균 기술력(TSS) 점수</th>
							<td class="txt_red"><strong class="f18">{{ tssAvgKeyTheme }}</strong> <span>점</span></td>
							<th>{{isuKorNm}} 기술력(TSS) 점수</th>
							<td>
								<strong class="txt_red f18">{{ tss }}</strong> <span class="txt_red">점</span>
								<em class="bar">|</em>
								<strong class="txt_blue f18">{{rankTssInKeyTheme}}</strong><span class="txt_blud">위</span><span> / {{rankTssInKeyThemeMax}}개 기업 중</span>
							</td>
						</tr>
					</tbody>
				</table>
				<h2>테마 개요</h2>
				<div class="desc">
          {{keyThemeDesc}}
				</div>
        <h2>테마 랭킹</h2>
				<table class="tbl">
					<colgroup>
						<col width="70">
						<col>
						<col id="border_red">
						<col>
						<col>
						<col width="160">
						<col>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>순위</th> 
							<th>기업명</th>
              <th><button class="btn_sort down">정렬버튼</button> 기술투자 지수 <Tooltip desc='기술투자지수란 기업의 시가총액을 TSS 점수(위즈도메인이 개발한 통계기법으로 산출)로 나눈 값입니다. 보유한 기술력대비 주기가 어느정도인지 가늠하는 수치로 동종기술로 분류된 테마내에서 상대비교를 통해 기업이 가치를 평가할 수 있는 보조지표입니다.  기술투자자가 낮을수록 "기술력대비 저평가" 되어있어 기술력관점에서 투자매력도가 높습니다. 즉, 기술투자지수가 낮다면 기업이 보유한 기술력보다 주가가 상대적으로 싸다는 의미인 것입니다.'/><span class="txt_gray">(시총/TSS)</span></th>
              <th><button class="btn_sort down">정렬버튼</button>기술력점수 <Tooltip desc='TSS는 기업이 가진 특허의 규모(quantity)와 기술 수준(quality)를 동시에 측정한 기술 경쟁력 지수입니다. 기업이 보유한 전체 특허를 점수화한 것으로 수치가 클 수록 수록 기술 경쟁력이 높음을 의미하며 다른 기업의 수치와 비교하여 상대적으로 평가할 수 있습니다.'/><span class="txt_gray">(TSS)</span></th>
              <th><button class="btn_sort down">정렬버튼</button>기술경쟁력등급 <Tooltip desc='TCG는 기업이 취득한 특허들을 평가하여 합산한 점수와 이 점수를 기반으로 한 업계 순위에 근거하여 생성되었습니다. 특허 평가 점수는 ㈜위즈도메인이 개발한 통계기법에 의해 계산된 값입니다. 이 점수는 객관적인 평가기준에 따라 동종 기술분야의 특허들과 상대 비교하여 산출됩니다.'/><span class="txt_gray">(TCG)</span></th>
              <th><button class="btn_sort down">정렬버튼</button>특허평가평균점수 <Tooltip desc='APES는 각 기술부문별 특허의 점수를 평균한 것으로, 개별 특허의 평가 점수는 특허 빅데이터 및 ㈜위즈도메인의 특허평가엔진(Patent Evaluation Engine)을 이용하여 산출합니다. 특허 평가 요소는 10여 가지가 있으며 평가 요소 중 하나를 예로 들면 다른 특허들로부터 인용을 많이 당한 특허는 좋은 점수를 받습니다.'/><span class="txt_gray">(APES)</span></th>
							<th>특허보유건수<span class="txt_gray">(건)</span></th>
							<th>시가총액<span class="txt_gray">(억원)</span></th>
						</tr>
					</thead>
					<tbody>
            <tr v-for="(themeStock,i) in themeStockList" v-bind:key="i">
              <td>{{i+1}}</td>
              <td>{{themeStock.isuKorNm}}</td>
              <td>{{themeStock.tii.toFixed(2)}}</td>
              <td>{{themeStock.tss.toFixed(2)}}</td>
              <td>{{themeStock.tcg}}</td>
              <td>{{themeStock.apes.toFixed(2)}}</td>
              <td>{{themeStock.patentCount}}</td>
              <td>{{$addComma(themeStock.mktCap)}}</td>
            </tr>
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
  name: 'Ranking',
	data() {
		return {
      bizno:'',
      isuSrtCd:'',
      isuKorNm:'',
      keySectorNo:'',
      keySectorNm:'',
      keySectorDesc:'',
      keyThemeNo:'',
      keyThemeNm:'',
      keyThemeDesc:'',
      tii:'',
      tss:'',
      tssAvgKeySector:'',
      tssAvgKeyTheme:'',
      rankTssInKeySector:'',
      rankTssInKeySectorMax:'',
      rankTssInKeyTheme:'',
      rankTssInKeyThemeMax:'',
      tiiAvgKeySector:'',
      tiiAvgKeyTheme:'',
      rankTiiInKeySectorMax:'',
      rankTiiInKeyTheme:'',
      rankTiiInKeyThemeMax:'',
      rankTiiInKeySector:'',
      rankTii:'',
      rankTiiMax:'',
      sectorStockList:[],
      themeStockList:[],
		}
	},
	components : {
		TopTit,
		Tap,
		Tooltip
	},
	metaInfo: {
		title: '섹터 테마 랭킹 | 10bagger',
	},
  mounted() {
    this.fn_Get_Ranking_Detail();
    this.fn_Get_Ranking_Sector_Stock_List();
    this.fn_Get_Ranking_Theme_Stock_List();
  },
  methods:{
    fn_Get_Ranking_Detail() {
      this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/report/outline?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        var json = res.data;
        this.isuKorNm = json.isuKorNm;
        this.keySectorNo = json.keySectorNo;
        this.keySectorNm = json.keySectorNm;
        this.keySectorDesc = json.keySectorDesc;
        this.keyThemeNo = json.keyThemeNo;
        this.keyThemeNm = json.keyThemeNm;
        this.keyThemeDesc = json.keyThemeDesc;
        this.tii = json.tii;
        this.tss = json.tss;
        this.tssAvgKeySector = json.tssAvgKeySector;
        this.tssAvgKeyTheme = json.tssAvgKeyTheme;
        this.rankTssInKeySector = json.rankTssInKeySector;
        this.rankTssInKeySectorMax = json.rankTssInKeySectorMax;
        this.rankTssInKeyTheme = json.rankTssInKeyTheme;
        this.rankTssInKeyThemeMax = json.rankTssInKeyThemeMax;
        this.tiiAvgKeySector = json.tiiAvgKeySector;
        this.rankTiiInKeySectorMax = json.rankTiiInKeySectorMax;
        this.rankTiiInKeyTheme = json.rankTiiInKeyTheme;
        this.rankTiiInKeyThemeMax = json.rankTiiInKeyThemeMax;
        this.rankTiiInKeySector = json.rankTiiInKeySector;
        this.rankTii = json.rankTii;
        this.rankTiiMax = json.rankTiiMax;
        this.tiiAvgKeyTheme = json.tiiAvgKeyTheme;
      })
      .catch((err) => {
        console.log(err)
      })
    },
    fn_Get_Ranking_Sector_Stock_List() {
      this.$api.fnGetApiCall('/stock/compare/sector/'+this.bizno)
      .then((res) => {
        var json = res.data;
        this.sectorStockList = json;
      })
      .catch((err)=> {
        console.log(err);
      })
    },
    fn_Get_Ranking_Theme_Stock_List() {
      this.$api.fnGetApiCall('/stock/compare/theme/'+this.bizno)
      .then((res) => {
        var json = res.data;
        this.themeStockList = json;
      })
      .catch((err)=> {
        console.log(err);
      })
    },
  },
  beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_detail";
  },
  created() {
    this.bizno = sessionStorage.getItem('detailBizNo');
    this.isuSrtCd = sessionStorage.getItem('detailIsuSrtCd');
  }
}
</script>