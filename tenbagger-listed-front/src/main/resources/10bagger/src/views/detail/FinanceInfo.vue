
<template>
  <div class="container">
		<div class="inner">
			<TopTit/><!-- store 에서 상태관리하는걸로 만들었습니다. -->
			<Tap targetNm="info"/>
			<div class="p_top"><h1>포괄 손익 계산서</h1></div>
			<section class="content">
<!--				<div class="bx_chart chart_prepare">&lt;!&ndash;차트 완료 되면 chart_prepare 클래스는 빼주세요&ndash;&gt;-->
<!--					Chart Area-->
<!--				</div>-->
				<table class="tbl type2">
					<colgroup>
						<col width="407"/>
					</colgroup>
					<thead>
						<tr>
							<th>항목</th>
              <th v-for="(fiscalDate, i) in fiscalDates" v-bind:key="i">
                {{fiscalDate}}
              </th>
						</tr>
					</thead>
					<tbody>

            <tr v-for="(financeItem,i) in financeItems" v-bind:key="i">
              <th>{{financeItem.itemName}}</th>
              <template v-for="(financeItemDetail, j) in financeItem" >
                <td v-if="fn_Check_FinanceItem_T(j)" v-bind:key="j" v-bind:class="fn_Get_Txt_Color(financeItemDetail)">
                  {{$checkNullVal(financeItemDetail)}}
                </td>
              </template>
            </tr>
					</tbody>
				</table>
			</section>
			<div class="p_top"><h1>재무상태표</h1></div>
			<section class="content">
<!--				<div class="bx_chart chart_prepare">&lt;!&ndash;차트 완료 되면 chart_prepare 클래스는 빼주세요&ndash;&gt;-->
<!--					<h3>재무상태 요약</h3>-->
<!--					Chart Area-->
<!--				</div>-->
				<table class="tbl type2">
					<colgroup>
						<col width="216"/>
						<col width="347"/>
					</colgroup>
					<thead>
						<tr>
							<th colspan="2">항목</th>
              <th v-for="(fundamentalFiscalDates, i) in fundamentalFiscalDates" v-bind:key="i">{{fundamentalFiscalDates}}</th>
						</tr>
					</thead>
					<tbody>
            <tr v-for="(fundmentalItem, i) in fundamentalItems" v-bind:key="i">
              <template v-if="i < fundamentalItems.length-1">
                <template v-if="fundmentalItem.itemNum == 1">
                  <template v-if=" i == 0">
                    <th colspan="2">{{fundmentalItem.itemName}}</th>
                  </template>
                  <template v-else>
                    <template v-if="fundmentalItem.itemGroupNum == fundamentalItems[i+1].itemGroupNum">
                      <th>{{fundmentalItem.itemGroupName}}</th>
                      <th>{{fundmentalItem.itemName}}</th>
                    </template>
                    <template v-else>
                      <th colspan="2">{{fundmentalItem.itemName}}</th>
                    </template>
                  </template>
                </template>
                <template v-else>
                  <template v-if="fundmentalItem.itemGroupNum == fundamentalItems[i-1].itemGroupNum">
                    <th></th>
                    <th>{{fundmentalItem.itemName}}</th>
                  </template>
                </template>
              </template>
              <template v-else-if="i == fundamentalItems.length-1">
                <template v-if="fundmentalItem.itemGroupNum == fundamentalItems[i-1].itemGroupNum">
                  <th></th>
                  <th>{{fundmentalItem.itemName}}</th>
                </template>
                <template v-else>
                  <th>{{fundmentalItem.itemGroupName}}</th>
                  <th>{{fundmentalItem.itemName}}</th>
                </template>
              </template>
              <template v-for="(fundmentalItemDetail,j) in fundmentalItem">
                 <td v-if="fn_Check_FinanceItem_T(j)" v-bind:key="j" v-bind:class="fn_Get_Txt_Color(fundmentalItemDetail)">
                    {{$checkNullVal(fundmentalItemDetail)}}
                 </td>
              </template>
            </tr>
					</tbody>
				</table>
			</section>
			<div class="p_top"><h1>현금흐름표</h1></div>
			<section class="content">
<!--				<div class="bx_chart chart_prepare">&lt;!&ndash;차트 완료 되면 chart_prepare 클래스는 빼주세요&ndash;&gt;-->
<!--					Chart Area-->
<!--				</div>-->
				<table class="tbl type2">
					<colgroup>
						<col width="407"/>
					</colgroup>
					<thead>
						<tr>
							<th>IFRS (연결)</th>
              <th v-for="(cashFlowDate,i) in cashFlowDates" v-bind:key="i">
                {{cashFlowDate}}
              </th>
						</tr>
					</thead>
					<tbody>

          <template v-for="(cashFlowItem,i) in cashFlowItems">

                <tr v-bind:key="i">
                    <th><strong>{{cashFlowItem.itemName}}</strong></th>
                    <template v-for="(cashFlowItemDetail,j) in cashFlowItem">
                      <th v-if="fn_Check_FinanceItem_T(j)" v-bind:key="j" v-bind:class="fn_Get_Txt_Color(cashFlowItemDetail)" class="center">
                        {{$checkNullVal(cashFlowItemDetail)}}
                      </th>
                    </template>
                </tr>

                <template v-if="cashFlowItem.subLevel != null">

                    <tr v-for="(cashFlowSublevel) in cashFlowItem.subLevel" v-bind:key="cashFlowSublevel.itemSeq">
                      <td class="left pl45">{{cashFlowSublevel.itemName}}</td>
                      <template v-for="(cashFlowSublevelDetail,l) in cashFlowSublevel" >
                        <td v-if="fn_Check_FinanceItem_T(l)" v-bind:key="l" v-bind:class="fn_Get_Txt_Color(cashFlowSublevelDetail)">
                          {{$checkNullVal(cashFlowSublevelDetail)}}
                        </td>
                      </template>
                    </tr>

                </template>

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

export default {
  name: 'FinanceInfo',
	data() {
		return {
      fiscalDates:'',
      financeItems:[],
      bizno:'',
      isuSrtCd:'',
      fundamentalFiscalDates:'',
      fundamentalItems:[],
      cashFlowDates:'',
      cashFlowItems:[],
		}
	},
	components : {
		TopTit,
		Tap,
	},
	metaInfo: {
		title: '재무정보 | 10bagger',
	},
  mounted() {
    this.fn_Get_Finance_IncomeStatement_Detail();
    this.fn_Get_Finance_Fundamental_Detail();
    this.fn_Get_Finance_CashFlow_Detail();
  },
  methods:{
    fn_Get_Finance_IncomeStatement_Detail () {
      this.$api.fnGetApiCall('/finance/incomestatement?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        var json = res.data;
        this.fiscalDates = json.fiscalDate;
        this.financeItems = json.financeItems;
      })
      .catch((err) => {
        console.log(err);
      })
    },
    fn_Get_Finance_Fundamental_Detail () {
      this.$api.fnGetApiCall('/finance/fundamental?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        var json = res.data;
        this.fundamentalFiscalDates = json.fiscalDate;
        this.fundamentalItems = json.fundamentalItems;
      })
      .catch((err) => {
        console.log(err);
      })
    },
    fn_Get_Finance_CashFlow_Detail() {
      this.$api.fnGetApiCall('/finance/cashflow?bizNo='+this.bizno+'&isuSrtCd='+this.isuSrtCd)
      .then((res) => {
        var json = res.data;
        this.cashFlowDates = json.fiscalDate;
        this.cashFlowItems = json.financeItems;
      })
      .catch((err) =>{
        console.log(err);
      })
    },
    fn_Check_FinanceItem_T(val) {
        if(val.toString().startsWith('t')){
          return true;
        }else {
          return false;
        }
    },
    fn_Get_Txt_Color(val) {
      if(val > 0) {
        return "";
      } else if(val < 0) {
        return "txt_red";
      } else {
        return "";
      }
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
</script>