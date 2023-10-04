<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="corp"/>
			<div class="p_top"><h1>기업 기본 정보 및 개요</h1></div>
			<section class="content">
				<article class="area colb">
					<div class="mr50">
						<h2 class="mt0">기업 개요 및 투자 정보</h2>
						<p class="desc">
							{{description}}
						</p>
						<h2>기업 정보</h2>
						<table class="tbl type_col">
						<colgroup>
							<col width="115px"/>
							<col width="450px"/>
							<col width="115px"/>
							<col/>
						</colgroup>
						<tbody>
							<tr>
								<th>본사주소</th>
								<td colspan="3">{{koraddr}}</td>
							</tr>
							<tr>
								<th>홈페이지</th>
								<td>{{homepurl}}</td>
								<th>대표전화</th>
								<td>{{ tel }}</td>
							</tr>
							<tr>
								<th>설립일</th>
								<td>{{etbDate}}</td>
								<th>시총</th>
								<td><strong class="txt_red">{{ $addComma(mktCap) }}</strong>억원({{ mktCapScale }})</td>
							</tr>
							<tr>
                <th>발행주식주</th>
                <td><em class="txt_label">보통</em>{{ $addComma(listShrs) }}주 <em class="txt_label blue"></em></td>
								<th>종업원수</th>
								<td><strong class="txt_blue">{{ $addComma(empnum) }}</strong>명 ({{ $getDateConvert(empnumBseDate) }})기준</td>
							</tr>
						</tbody>
					</table>
					</div>

          <!--
					<div class="bx_stock_info type1">
						<div class="bx_taps">
							<button class="tap on">투자정보</button>
							<button class="tap">호가10단계</button>
						</div>
						<div class="cont">
							<ul>
								<li>
									<span class="tit">시가총액</span>
									<span class="desc">{{ $addComma(mktCap) }}억원</span>
								</li>
								<li>
									<span class="tit">시가총액순위</span>
									<span class="desc txt_red">코스피100위</span>
								</li>
								<li>
									<span class="tit">상장주식수</span>
									<span class="desc">80,000,000주</span>
								</li>
								<li>
									<span class="tit">액면가 매매단위</span>
									<span class="desc">5,000원 <em class="bar">|</em> 1주</span>
								</li>
								<li class="bt">
									<span class="tit">외국인한도(A)</span>
									<span class="desc">11,686,538주</span>
								</li>
								<li>
									<span class="tit">외국인한도(B)</span>
									<span class="desc">2,449,961주</span>
								</li>
								<li>
									<span class="tit">외국인소진율(B/A)</span>
									<span class="desc">20.96%</span>
								</li>
								<li class="bt">
									<span class="tit">투자의견 목표주가(A)</span>
									<span class="desc txt_blue"><span class="txt_red">4.00매수</span> <em class="bar">|</em> 468,000</span>
								</li>
								<li>
									<span class="tit">52주 최고 <em class="bar">|</em> 52주 최저</span>
									<span class="desc txt_red">{{ $addComma(hgPrc52w) }}<em class="bar">|</em> {{ $addComma(lwPrc52w) }}</span>
								</li>
								<li class="bt">
									<span class="tit">PER <em class="bar">|</em> EPS (2021.06)</span>
									<span class="desc"><span class="txt_red">4.00배</span> <em class="bar">|</em> 468원</span>
								</li>
								<li>
									<span class="tit">추정PER <em class="bar">|</em> EPS</span>
									<span class="desc">5000배<em class="bar">|</em> 34500원</span>
								</li>
								<li>
									<span class="tit">PBR <em class="bar">|</em> EPS(2021.06)</span>
									<span class="desc">5배<em class="bar">|</em> 500원</span>
								</li>
								<li>
									<span class="tit">배당수익률(2020.12)</span>
									<span class="desc">53%</span>
								</li>
								<li class="bt">
									<span class="tit">동일업종 per</span>
									<span class="desc ">34배</span>
								</li>
								<li>
									<span class="tit">동일업종 등락률</span>
									<span class="desc txt_blue">-2.17%</span>
								</li>
							</ul>
						</div>
					</div>
					-->
				</article>
			</section>
			<div class="p_top"><h1>연구개발 집중도</h1></div>
			<section class="content">
				<h2>주요기술부문</h2>
				<div class="desc">최근 5년간 {{isuKorNm}}의 출원특허를 대상으로 특허 활동을 분석하여, 출원이 활발한 상위 4개 분야를 주요 기술 분야로 선정합니다. {{isuKorNm}}의 주요 기술 분야는 아래와 같습니다.</div>
				<article class="area col4 mt10">
					<StepScore v-for="(data, index) in comStepData" :key="index" :data="data"/>
				</article>
				<article class="area coln mt20">
					<ul class="list type2 col">
						<li><strong class="txt_point">기술력 점수 (Technology Strength Score: TSS)</strong><br/>TSS는 기업이 가진 특허의 규모(quantity)와 기술 수준(quality)를 동시에 측정한 기술 경쟁력 지수입니다.</li>
						<li>기업이 보유한 전체 특허를 점수화 한 것으로 수치가 클 수록 기술 경쟁력이 높음을 의미하며 다른 기업의 수치와 비교하여 상대적으로 평가할 수 있습니다.</li>
						<li>기술력 점수 순위 각 기술분야에서 다른 기업의 기술력 점수와 비교하여 산출한 등수이며, 기술력 점수를 보유하지 않은 기업은 비교 대상에서 제외됩니다.</li>
						<li>R&D 집중도 최근 5년 동안 출원한 전체 특허에 대한 각 분야의 특허 출원 비율입니다.</li>
					</ul>
					<div class="shrink ml15">
						<img src="@/img/detail/img_corp1.jpg" alt="평가배경">
					</div>
				</article>
				<h2>주요기술부문별 특허 출원 동향</h2>
				<div class="desc">최근 10년 {{isuKorNm}}의 주요기술부문별 출원 동향을 표시합니다.</div>
				<div class="bx_chart chart_prepare">
          <highcharts :options="patentChart"></highcharts>
				</div>
				<div class="area">
					<table class="tbl type2">
						<colgroup>
							<col width="110"/>
							<col width="200"/>
							<col/>
						</colgroup>
						<thead>
							<tr>
								<th colspan="2">기술부분</th>
								<th colspan="10">출원건수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th class="center">IRC 코드</th>
								<th class="center">기술분문명</th>
                <th class="center" v-for="(annuals,i) in annuals.slice()" v-bind:key="i">{{annuals.year}}</th>
							</tr>
							<tr v-for="(ipcs,i) in ipcs" v-bind:key="i">
								<th class="center">{{ ipcs.ic }}</th>
								<th class="center">{{ ipcs.icName }}</th>
								<td class="center" v-for="(annuals,j) in ipcs.annuals.slice()" v-bind:key="j" >{{ $getTableNumberValue(annuals.cnt) }}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</section>
      <div class="p_top"><h1>특허 출원 및 등록 동향</h1></div>
      <section class="content">
				<h2>특허 활동 동향 개요</h2>
				<article class="area_tip col3">
					<div class="bx_tip col">
						<div class="top"><span class="label">TIP1</span><h3>출원특허</h3></div>
						<div class="cont">
							<span>발명자가 발명을 특허권리로 인정받기 위해 특허청에 제출한 문서.</span>
						</div>
					</div>
					<div class="bx_tip col">
						<div class="top"><span class="label">TIP2</span><h3>등록특허</h3></div>
						<div class="cont">
							<span>출원 후 특허청의 심사를 통과하여 특허에 대한 권리가 인정된 것.</span>
						</div>
					</div>
					<div class="bx_tip col">
						<div class="top"><span class="label">TIP3</span><h3>보유특허</h3></div>
						<div class="cont">
							<span>현재 보유하고 있으며 권리가 유효한 등록특허. (매입,매각을 통해 보유상황이 달라질 수 있음)</span>
						</div>
					</div>
				</article>
				<ul class="list">
					<li>{{isuKorNm}}는 최근 20년간 <strong>{{ appPatent }}건 출원</strong>하여 <strong>{{ regPatent }}건이 등록</strong>	되어 특허권을 획득하였습니다.</li>
					<li>{{ transferedPatent }}건의 등록특허를 외부에서 매입하였으며, 현재 <strong>권리유효한 특허는 {{ alivePatent }}건</strong>입니다.</li>
					<li><strong>{{ foreignPatent }}건의 해외 특허를 보유</strong>하고 있습니다.</li>
				</ul>
				<div class="area col2">
					<div class="col">
						<h2>특허 출원 동향</h2>
						<div class="desc">
							일반적으로 R&D 투자가 많을 수록 그 결과물인 특허 출원수가 증가하게 됩니다.<br/>
							기업의 R&D 규모가 클수록 특허 출원 수가 많고, 새로운 제품을 출시하거나 신기술을 개발하는 시점에 특허 출원이 급증하는 경향이 있습니다.
						</div>
						<div class="bx_chart chart_prepare">
							<h3>최근 10년간 특허 출원 동향</h3>
							<highcharts :options="annualAppChart"/>
						</div>
          </div>
          <div class="col ml40">
						<h2>특허 등록 동향</h2>
						<div class="desc">
							등록특허는 까다로운 기술 심사를 통과하여 독점적인 권리가 인증된 특허를 의미합니다.<br/>
							등록된 특허를 많이 보유할수록 실질적 으로 기술경쟁력이 있다고 할 수 있습니다.<br/><br/>
						</div>
						<div class="bx_chart chart_prepare">
							<h3>최근 10년간 특허 등록 동향</h3>
							<highcharts :options="annualRegChart"/>
						</div>
					</div>
        </div>
      </section>

      <div class="p_top"><h1>특허 포트폴리오 현황</h1></div>
      <section class="content">
				<div class="desc">
					{{isuKorNm}}의 기술 부문별 특허 현황으로 보유특허를 대상으로 합니다. 특허권을 획득하거나 특허 권리를 유지하기 위해서는 많은 비용이 소요되기 때문에 기업이 필요한 분야에 집중하여 특허를 보유 하게 됩니다. 따라서 기업이 보유하고 있는 특허의 기술 분포를 통해 그 기업이 집중하고 있는 기술 분야와 R&D 집중 분야를 추 측할 수 있습니다
				</div>
				<div class="area col2 mt30">
					<div class="col mr40">
						<div class="bx_chart chart_prepare mt0">
							<highcharts :options="portfolioChart"/>
						</div>
					</div>
					<div class="col">
						<table class="tbl type2">
							<colgroup>
								<col/>
								<col width="138"/>
								<col width="102"/>
							</colgroup>
							<thead>
								<tr>
									<th>[IPC 코드] 기술 부문</th>
									<th>비율(%)</th>
									<th>특허수</th>
								</tr>
							</thead>
							<tbody>
                <tr v-for="(portfolio,i) in portfolios" v-bind:key="i">
                  <th>[{{portfolio.ic}}]{{portfolio.icName}}</th>
                  <th>{{portfolio.ratio}}</th>
                  <th>{{portfolio.cnt}}</th>
                </tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>
			<div class="p_top"><h1>키워드 변동</h1></div>
			<section class="content">
				<h2>기술 키워드 변동</h2>
				<div class="desc">
						{{isuKorNm}}의 출원 특허를 대상으로 특허 문헌에 사용한 기술 키워드의 분포를 표시합니다. 과거의 기술 키워드와 최근 키워드의 변화를 통해 집중하고있는 기술 분야의 변동을 파악할 수 있습니다.
				</div>
        <div class="area col2">
          <div class="col mr40">
            <div class="bx_chart chart_prepare">
              <h3>{{set4Title}}</h3>
              <highcharts :options="set4Chart"/>
            </div>
          </div>
          <div class="col">
            <div class="bx_chart chart_prepare">
              <h3>{{ set3Title }}</h3>
              <highcharts :options="set3Chart"/>
            </div>
          </div>
        </div>
        <div class="area col2">
          <div class="col mr40">
            <div class="bx_chart chart_prepare">
              <h3>{{ set2Title }}</h3>
              <highcharts :options="set2Chart"/>
            </div>
          </div>
          <div class="col">
            <div class="bx_chart chart_prepare">
              <h3>{{ set1Title }}</h3>
              <highcharts :options="set1Chart"/>
            </div>
          </div>
        </div>
				<h2>키워드변동</h2>
				<div class="area col2">
					<div class="col mr40">
						<table class="tbl type2">
							<colgroup>
								<col width="120"/>
								<col/>
								<col width="120"/>
							</colgroup>
							<thead>
								<tr>
									<th colspan="3" class="big">2015년 이전</th>
								</tr>
							</thead>
							<tbody>
								<tr class="sub_top">
									<th class="center">순위</th>
									<th class="center">키워드</th>
									<th class="center">변동구분</th>
								</tr>
                <tr v-for="(beforeWord, i) in beforeWords" v-bind:key="i">
                  <td>{{beforeWord.rank}}</td>
                  <td>{{beforeWord.word}}</td>
                  <td v-if="beforeWord.wordChangeType == 'New'">
                    <em class="txt_yellow">◎</em>
                  </td>
                  <td v-else-if="beforeWord.wordChangeType == 'Except'">
                    <em class="txt_gray">X</em>
                  </td>
                  <td v-else-if="beforeWord.wordChangeType == 'Up'">
                    <em class="txt_red">▲</em>
                  </td>
                  <td v-else-if="beforeWord.wordChangeType == 'Down'">
                    <em class="txt_blue">▼</em>
                  </td>
                  <td v-else-if="beforeWord.wordChangeType == 'Same'">
                    =
                  </td>
                </tr>
							</tbody>
						</table>
					</div>
					<div class="col">
						<table class="tbl type2">
							<colgroup>
								<col width="120"/>
								<col/>
								<col width="120"/>
							</colgroup>
							<thead>
								<tr>
									<th colspan="3" class="big">2015년 이후</th>
								</tr>
							</thead>
							<tbody>
								<tr class="sub_top">
									<th class="center">순위</th>
									<th class="center">키워드</th>
									<th class="center">변동구분</th>
								</tr>
                <tr v-for="(afterWord, i) in afterWords" v-bind:key="i">
                  <td>{{afterWord.rank}}</td>
                  <td>{{afterWord.word}}</td>
                  <td v-if="afterWord.wordChangeType == 'New'">
                    <em class="txt_yellow">◎</em>
                  </td>
                  <td v-else-if="afterWord.wordChangeType == 'Except'">
                    <em class="txt_gray">X</em>
                  </td>
                  <td v-else-if="afterWord.wordChangeType == 'Up'">
                    <em class="txt_red">▲</em>
                  </td>
                  <td v-else-if="afterWord.wordChangeType == 'Down'">
                    <em class="txt_blue">▼</em>
                  </td>
                  <td v-else-if="afterWord.wordChangeType == 'Same'">
                    =
                  </td>
                </tr>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</div>
  </div>
</template>

<script>
import TopTit from '@/components/layout/TopTit.vue';
import Tap from '@/components/layout/Tap.vue';
import StepScore from '@/components/item/StepScore.vue';

export default {
  name: 'Corp',
  computed: {
    patentChart() {
      let option = {...patent_chart_option};
      option.series = this.ipcs.map( ipc => {
        const data = {...patent_chart_data};
        data.name = ipc.icName;
        data.data = ipc.annuals.reverse().map( ann => ann.cnt );
        return data;
      });
      if(this.ipcs.length>0)
        option.xAxis.categories = this.ipcs[0].annuals.map( ann=> ann.year);

      return option;
    },
    annualAppChart() {
      let option = {...annualApp_chart_option};
      option.xAxis.categories = this.annualApp.map(a => a.year);
      option.series[0].data = this.annualApp.map(a => a.cnt);
      option.series[0].name = this.isuKorNm;
      return option;
    },
    annualRegChart () {
      let option = {...annualReg_chart_option};
      option.xAxis.categories = this.annualReg.map(a => a.year);
      option.series[0].data = this.annualReg.map(a => a.cnt);
      option.series[0].name = this.isuKorNm;
      return option;
    },
    portfolioChart() {
      let option = {...portfolio_chart_option};
      option.series[0].data = this.portfolios.map(portfolio => {
        const data = {...portfolio_chart_data};
        data.name = portfolio.ic;
        data.y = portfolio.ratio;
        return data;
      });
      return option;
    },
    set1Chart() {
      let option = {...keyword_chart_option};
      option.series[0].data = this.keywordSets.set1.words.map(keyword => {
        const data = {...keyword_chart_data}
        data.name = keyword.word;
        data.weight = keyword.weight;
        return data;
      })
      return option;
    },
    set2Chart() {
      let option = {...keyword_chart_option};
      option.series[0].data = this.keywordSets.set2.words.map(keyword => {
        const data = {...keyword_chart_data}
        data.name = keyword.word;
        data.weight = keyword.weight;
        return data;
      })
      return option;
    },
    set3Chart() {
      let option = {...keyword_chart_option};
      option.series[0].data = this.keywordSets.set3.words.map(keyword => {
        const data = {...keyword_chart_data}
        data.name = keyword.word;
        data.weight = keyword.weight;
        return data;
      })
      return option;
    },
    set4Chart() {
      let option = {...keyword_chart_option};
      option.series[0].data = this.keywordSets.set4.words.map(keyword => {
        const data = {...keyword_chart_data}
        data.name = keyword.word;
        data.weight = keyword.weight;
        return data;
      })
      return option;
    },
  },
	data() {
		return {
			comStepData:[],
      bizno:'',
      isuSrtCd:'',
      description:'',
      koraddr:'',
      homepurl:'',
      tel:'',
      etbDate:'',
      mktCap:'',
      mktCapScale:'',
      empnum:'',
      empnumBseDate:'',
      listShrs:'',
      bnknm:'',
      bnkBrnm:'',
      isuKorNm:'',
      ipcs:[],
      annuals:[],
      hgPrc52w:'',
      lwPrc52w:'',
      appPatent:'',
      regPatent:'',
      alivePatent:'',
      foreignPatent:'',
      transferedPatent:'',
      quadrantIcs:[],
      beforeWords:[],
      afterWords:[],
      annualApp:[],
      annualReg:[],
      portfolios:[],
      keywordSets:'',
      set1Title:'',
      set2Title:'',
      set3Title:'',
      set4Title:'',
      set1Data:[],
      set2Data:[],
      set3Date:[],
      set4Data:[],
		}
	},
	components : {
		TopTit,
		Tap,
		StepScore
	},
	metaInfo: {
		title: '기업기술개요 | 10bagger',
	},
  mounted() {
    this.fn_Get_Corp_Info_Detail();
    this.fn_Get_Corp_Tech_Detail();
    this.fn_Get_Corp_Keyword_Detail();
    this.fn_Get_Patent_Activity_Detail();
  },
  methods: {
    fn_Get_Corp_Info_Detail() {
      this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/report/outline?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        let corp = json.corp;
        this.isuKorNm = json.isuKorNm;
        this.description = json.description;
        this.koraddr = corp.koraddr;
        this.homepurl = corp.homepurl;
        this.tel = corp.tel;
        this.etbDate = corp.etbDate == null?this.$getDateConvert2(corp.obzDate)+"(개업일)":this.$getDateConvert2(corp.etbDate);
        this.mktCap = corp.mktCap;
        this.mktCapScale = json.mktCapScale;
        this.empnum = corp.empnum;
        this.empnumBseDate = corp.empnumBseDate;
        this.bnknm = corp.bnknm;
        this.bnkBrnm = corp.bnkBrnm;
      })
      .catch((err) => {
        console.log(err)
      })
      this.$api.fnGetApiCall('/report/stock?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let jsonStock = res.data;
        this.hgPrc52w = jsonStock.hgPrc52w;
        this.lwPrc52w = jsonStock.lwPrc52w;
        this.listShrs = jsonStock.listShrs;

      })
      .catch((err) =>{
        console.log(err)
      })
    },
    fn_Get_Corp_Tech_Detail() {
      this.$api.fnGetApiCall('/report/technology?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) =>{
        let json = res.data;
        this.ipcs = json.ipcs;
        this.quadrantIcs = json.quadrantIcs;
        this.annuals = this.ipcs[0].annuals;
        for(let idx in this.ipcs) {
          let comStepDataForm =
              {
                num:'0'+(Number(idx)+1),
                tit:this.ipcs[idx].icName,
                score:this.ipcs[idx].tss,
                total:this.ipcs[idx].countCompany,
                myScore:this.ipcs[idx].rankTss,
                rnDConcentration:this.ipcs[idx].rnDConcentration,
              }
          this.comStepData.push(comStepDataForm);
        }
      })
      .catch((err) => {
        console.log(err);
      })
    },

    fn_Get_Corp_Keyword_Detail() {
      this.$api.fnGetApiCall('/report/keyword?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        this.beforeWords = json.beforeWords;
        this.afterWords = json.afterWords;
        const keywordDataSet = {};
        for(let idx in json) {
          if(idx.indexOf('set') > -1) {
            keywordDataSet[idx] = json[idx];
          }
        }
        this.keywordSets = keywordDataSet;
        this.set1Title = this.fn_Get_Set_Title_Name(this.keywordSets.set1.name);
        this.set2Title = this.fn_Get_Set_Title_Name(this.keywordSets.set2.name);
        this.set3Title = this.fn_Get_Set_Title_Name(this.keywordSets.set3.name);
        this.set4Title = this.fn_Get_Set_Title_Name(this.keywordSets.set4.name);
      })
      .catch((err) => {
        console.log(err)
      })
    },
    fn_Get_Patent_Activity_Detail() {
      this.$api.fnGetApiCall('/report/patentactivity?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        this.foreignPatent = json.foreignPatent;
        this.transferedPatent = json.transferedPatent;
        this.appPatent = json.appPatent;
        this.regPatent = json.regPatent;
        this.alivePatent = json.alivePatent;
        this.annualApp = json.annualApp;
        this.annualReg = json.annualReg;
        this.portfolios = json.portfolios;
      })
      .catch((err) => {
        console.log(err);
      })
    },
    fn_Get_Set_Title_Name(set) {
      if(set.length != 0) {
        let titleSplit = set.split(':');
        return titleSplit[0]+'년 - '+titleSplit[1]+'년';
      }else {
        return '-';
      }
    }
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
const patent_chart_data = {
  label: {
    enabled: false
  },
  marker: {
    enabled: true,
    symbol: 'circle',
    radius: 3,
    states: {
      hover: {
        enabled: false,
      }
    }
  }
}

const patent_chart_option = {
  chart: {
    backgroundColor: "#FFF",
    width: 1100
  },
  title: {
    text: "",
  },
  yAxis: {
    title: {
      text: "특허출원건수",
      style: {
        fontFamily: "NanumSquare",
        color: "#595959",
        fontSize: "10px",
        letterSpacing: "-0.5px",
      },
    },
    min: 0,
    tickInterval: 1,
    labels: {
      style: {
        fontSize: "7px",
        color: "#BFBFBF",
      },
    },
  },

  xAxis: {
    title: {
      text: "특허출원연도",
      style: {
        fontFamily: "NanumSquare",
        color: "#595959",
        fontSize: "10px",
        letterSpacing: "-0.5px",
      },
    },
    categories: [], // 2011, 2012
    labels: {
      style: {
        fontSize: "7px",
        color: "#BFBFBF",
      },
    },
    lineWidth: 0,
  },

  legend: {
    layout: "vertical",
    align: "right",
    itemMarginBottom: 10,
    verticalAlign: "middle",
    itemStyle: {
      fontFamily: "NanumSquare",
      fontSize: "8px",
      color: "#595959",
      letterSpacing: "-0.4px",
    },
  },
  tooltip: { enabled: false },

  credits: {
    enabled: false,
  },
  exporting: {
    enabled: false,
  },
  plotOptions: {
    series: {
      states: {
        inactive: {
          opacity: 1,
        },
        hover: {
          enabled: false,
        },
      },
      events: {
        legendItemClick: function () {
          return false;
        },
      },
    },
  },
};
const annualApp_chart_option = {
  chart: {
    width: 450,
    height: 130,
    backgroundColor:'rgba(255, 255, 255, 0)',
    plotBorderWidth: 1,
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
    min:0,
    tickInterval:5,
    title: {
      text: ''
    },
    labels:{
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold'
      }
    }
  },

  xAxis: {
    categories:[],
    labels: {
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold'
      }
    }
  },

  legend: {
    layout: 'vertical',
    align: 'left',
    floating:true,
    verticalAlign: 'bottom',
    itemStyle: {
      fontFamily: 'NanumSquare',
      fontSize: '8px',
      color: '#BFBFBF',
      fontWeight:'bold'
    },
    x:-18,
    y:20
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
      },
      animation:false
    }
  },
  series: [
      {
    name: '',
    label: {
      enabled: false
    },
    marker: {
      enabled: true,
      symbol: 'circle',
      radius: 3,
    },
    color: '#AE2318',
    dataLabels: {
      formatter: function() {
        if (this.y == 0) {
          return 'N/A';
        }
        else {
          return this.y;
        }
      },
      enabled: true,
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold',
        textOutline:'0px'
      }
    }
  }],

};

const annualReg_chart_option = {
  chart: {
    width: 450,
    height: 130,
    backgroundColor:'rgba(255, 255, 255, 0)',
    plotBorderWidth: 1,
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
    min:0,
    tickInterval:5,
    title: {
      text: ''
    },
    labels:{
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold'
      }
    }
  },

  xAxis: {
    categories:[],
    labels: {
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold'
      }
    }
  },

  legend: {
    layout: 'vertical',
    floating:true,
    align: 'left',
    verticalAlign: 'bottom',
    itemStyle: {
      fontFamily: 'NanumSquare',
      fontSize: '8px',
      color: '#BFBFBF',
      fontWeight:'bold'
    },
    x:-18,
    y:20
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
      },
      animation:false
    }
  },

  series: [{
    name: '',
    label: {
      enabled: false
    },
    marker: {
      enabled: true,
      symbol: 'circle',
      radius: 3,
    },
    color: '#AE2318',
    data: [],
    dataLabels: {
      formatter: function() {
        if (this.y == 0) {
          return 'N/A';
        }
        else {
          return this.y;
        }
      },
      enabled: true,
      style: {
        fontFamily: 'NanumSquare',
        fontSize: '8px',
        color: '#BFBFBF',
        fontWeight:'bold',
        textOutline:'0px'
      }
    }
  }],
};

const portfolio_chart_option = {
  chart: {
    width: 400,
    height: 213,
    backgroundColor:'rgba(255, 255, 255, 0)',
    type: 'pie'
  },
  exporting: {
    enabled: false
  },
  credits: {enabled: false},
  title: {
    text: ''
  },
  tooltip: {
    enabled: false
  },
  accessibility: {
    point: {
      valueSuffix: '%'
    }
  },
  plotOptions: {
    pie: {
      allowPointSelect: false,
      dataLabels: {
        enabled: true,
        distance: 5,
        format: '<b>{point.name}</b>, {point.percentage:.2f} %',
        padding: 0,
        style: {
          fontWeight:"900",
          fontSize: '8px'
        },
      },
      animation:false
    }
  },
  series: [{
    data: []
  }]
};
const portfolio_chart_data = {
  name:'',
  y:''
};
const keyword_chart_option = {
  chart: {
    width: 300,
    height:300
  },
  title: {
    text: ''
  },
  tooltip: {
    enabled: false
  },
  exporting: {
    enabled: false
  },
  credits: {
    enabled: false
  },
  series: [{
    type: 'wordcloud',
    data: [],
    name: 'Occurrences'
  }]
};
const keyword_chart_data = {
  name:'',
  weight:''
}
</script>