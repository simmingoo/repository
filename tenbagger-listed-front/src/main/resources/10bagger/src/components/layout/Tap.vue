
<template>
	<div class="p_tap">
		<router-link to="/detailStock" class="tap" :class="{'on' : targetNm === 'stock'}"><span>종목개요</span></router-link>
		<router-link to="/detailCorp" class="tap" :class="{'on' : targetNm === 'corp'}"><span>기업기술개요</span></router-link>
		<router-link to="/detailRanking" class="tap" :class="{'on' : targetNm === 'ranking'}"><span>섹터테마랭킹</span></router-link>
		<router-link to="/detailSkill" class="tap" :class="{'on' : targetNm === 'skill'}"><span>기술비교</span></router-link>
		<router-link to="/detailFinance" class="tap" :class="{'on' : targetNm === 'finance'}"><span>재무비교</span></router-link>
		<router-link to="/detailFinanceInfo" class="tap" :class="{'on' : targetNm === 'info'}"><span>재무정보</span></router-link>
<!--		<router-link to="/temp" class="tap" :class="{'on' : targetNm === 'temp'}"><span>매매신호</span></router-link>-->
<!--		<router-link to="/temp" class="tap" :class="{'on' : targetNm === 'temp'}"><span>추천정보</span></router-link>-->
<!--		<router-link to="/temp" class="tap" :class="{'on' : targetNm === 'temp'}"><span>뉴스/공시</span></router-link>-->
<!--		<router-link to="/temp" class="tap" :class="{'on' : targetNm === 'temp'}"><span>토론방</span></router-link>-->
	</div>
</template>

<script>
export default {
	props: ['targetNm'],
  name: 'Tap',
}
</script>