
<template>
	<div class="top_tit colb">
		<div class="left">
			<div class="area coln">
				<div class="bx_tit shrink">
					<span>({{this.$store.state.topData.type}})</span><strong class="tit ml5">{{this.$store.state.topData.tit}}</strong>
					<span class="txt_gray">{{this.$store.state.topData.num}}</span>
				</div>
				<div class="bx_themes col">
					<strong class="txt_hlight">{{this.$store.state.topData.stock}}</strong>
					<strong v-for="(item, index) in this.$store.state.topData.tag" :key="index" class="txt_hlight line">{{item}}</strong>
				</div>
			</div>
			<div :class="'bx_info ' + this.$store.state.topData.increase.nm">
				<strong>{{this.$store.state.topData.price}}</strong><span>원</span>
				<span>{{this.$store.state.topData.increase.nm ==="up" ? "▲" : "▼"}} {{this.$store.state.topData.increase.price[0]}}</span> <span>{{this.$store.state.topData.increase.price[1]}}</span>
				<span v-if="this.$store.state.topData.date !== undefined" class="txt_gray">{{this.$store.state.topData.date}} 기준</span>
				<span v-if="this.$store.state.topData.desc !== undefined" class="txt_gray">{{this.$store.state.topData.desc}} 기준</span>
			</div>
		</div>
		<div class="right shrink">
			<button :class="'btn_wish ' + this.$store.state.topData.onWish">관심목록</button>
			<button :class="'btn_reco ' + this.$store.state.topData.onReco">추천종목</button><br/>
			<button class="btn_down mt5">분석 리포트 다운로드</button>
		</div>
	</div>
</template>

<script>
export default {
	// props: ['data'],
  name: 'TopTit',
}
</script>