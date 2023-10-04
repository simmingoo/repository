
<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="finance"/>
			<div class="p_top"><h1>기업 재무 비교</h1></div>
			<section class="content">
				<h2>기업 재무비교 개요</h2>
				<ul class="list">
					<li>경쟁기업들의 재무 관련 항목을 상대적으로 비교한 결과를 확인할 수 있습니다.</li>
					<li>아래 입력 박스에 비교하고자하는 기업명을 직접 입력하여 검색하여 추가하실 수 있습니다.</li>
					<li>최대 5개의 기업을 검색하여 추가하실 수 있으며, 미 입력 시 기술 기준 동종 분야에서 규모가 유사한 기업을 기본으로 비교하게 됩니다.</li>
				</ul>
				<h2>기업 재무비교 기업 선택</h2>
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
					<button class="btn"><strong>기업 재무비교 조회</strong></button>
				</div>
				<h2>기업 재무비교 결과</h2>
				<ul class="list">
					<li>경쟁기업들의 등록 특허를 대상으로 기업의 <strong class="txt_point">재무 수준을 상대적으로 비교</strong>한 결과입니다.</li>
					<li>PER, PBR 수치는 최근 4분기 기준입니다.</li>
					<li><em class="txt_red f10">●</em> 종목은 IFRS(연결) 회계기준을 적용한 종목입니다. (최근 분기보고서 기준이며, 기준 분기는 업종마다 다를 수 있습니다.)</li>
					<li>IFRS(연결) 적용 종목의 당기순이익 및 가치지표(주당순이익, PER, PBR)는 지배주주지분을 기준으로 산출하였습니다.</li>
				</ul>
				<table class="tbl">
					<colgroup>
						<col width="16.6%">
						<col width="16.6%">
						<col width="16.6%">
						<col width="16.6%">
						<col width="16.6%">
						<col width="16.6%">
						<col width="16.6%">
					</colgroup>
					<thead>
						<tr>
							<th>종목명<span class="txt_gray">(종목코드)</span></th>
							<th>단위<span class="txt_gray"></span></th>
							<th>{{ copHeaderVO.c1 }}<span class="txt_gray">({{ copHeaderVO.c1IsuSrtNo }})</span></th>
							<th>{{ copHeaderVO.c2 }}<span class="txt_gray">({{ copHeaderVO.c2IsuSrtNo }})</span></th>
							<th>{{ copHeaderVO.c3 }}<span class="txt_gray">({{ copHeaderVO.c3IsuSrtNo }})</span></th>
							<th>{{ copHeaderVO.c4 }}<span class="txt_gray">({{ copHeaderVO.c4IsuSrtNo }})</span></th>
							<th>{{ copHeaderVO.c5 }}<span class="txt_gray">({{ copHeaderVO.c5IsuSrtNo }})</span></th>
						</tr>
					</thead>
					<tbody>
            <tr v-for="(corpItemData,i) in  investmentCorpsData.copItemVOS"  v-bind:key="i">
              <template v-if="corpItemData.itemName === 'PER' || corpItemData.itemName === 'PBR'">
                <td class="left">{{corpItemData.itemName}}</td>
                <td class="left">{{corpItemData.itemUnit}}</td>
                <td class="left" >{{$checkNullValPerPbr(corpItemData.c1)}}</td>
                <td class="left" >{{$checkNullValPerPbr(corpItemData.c2)}}</td>
                <td class="left" >{{$checkNullValPerPbr(corpItemData.c3)}}</td>
                <td class="left" >{{$checkNullValPerPbr(corpItemData.c4)}}</td>
                <td class="left" >{{$checkNullValPerPbr(corpItemData.c5.toFixed(2))}}</td>
              </template>
              <template v-else>
                <td class="left">{{corpItemData.itemName}}</td>
                <td class="left">{{corpItemData.itemUnit}}</td>
                <td class="left" v-bind:class="fn_Get_Txt_Color(corpItemData.c1)">{{$checkNullVal(corpItemData.c1)}}</td>
                <td class="left" v-bind:class="fn_Get_Txt_Color(corpItemData.c2)">{{$checkNullVal(corpItemData.c2)}}</td>
                <td class="left" v-bind:class="fn_Get_Txt_Color(corpItemData.c3)">{{$checkNullVal(corpItemData.c3)}}</td>
                <td class="left" v-bind:class="fn_Get_Txt_Color(corpItemData.c4)">{{$checkNullVal(corpItemData.c4)}}</td>
                <td class="left" v-bind:class="fn_Get_Txt_Color(corpItemData.c5.toFixed(2))">{{$checkNullVal(corpItemData.c5.toFixed(2))}}</td>
              </template>
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

export default {
  name: 'Finance',
	data() {
		return {
      investmentAnnual:[],
      bizno:'',
      isuSrtCd:'',
      investmentCorpsData:'',
      copHeaderVO:'',
		}
	},
  computed:{

  },
  mounted() {
    this.fn_Get_Finance_investment_corps_detail();
  },
  methods:{
    fn_Get_Txt_Color(val) {
      if(val > 0) {
        return "";
      } else if(val < 0) {
        return "txt_red";
      } else {
        return "";
      }
    },
    fn_Get_Finance_investment_corps_detail() {
      this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/report/competition?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        let json = res.data;
        let params = '';
        params = 'c1='+this.isuSrtCd;
        for(let idx in  json.similarCompanys) {
          if(json.similarCompanys[idx].isuSrtCd != this.isuSrtCd) {
            params += '&c1='+json.similarCompanys[idx].isuSrtCd;
          }
        }
        this.$api.fnGetApiCall('https://c-api.10bagger.co.kr/v1/finance/investment/corps?'+params)
        .then((resInvestmentCorps) => {
          let jsonInvestmentCorps =  resInvestmentCorps.data;
          this.investmentCorpsData = jsonInvestmentCorps;
          this.copHeaderVO = jsonInvestmentCorps.copHeaderVO;
        })
        .catch((err) => {
          console.log(err)
        })

      })
      .catch((err) => {
        console.log(err)
      })
    }
  },
	components : {
		TopTit,
		Tap,
	},
	metaInfo: {
		title: '재무비교 | 10bagger',
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