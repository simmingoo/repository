
<template>
	<div class="container">
		<div class="inner">
			<div class="p_top">
				<h1>TOP5 상위테마 분석</h1>
				<span class="desc">현재기준 인기있는 상위테마를 기준으로 한 분석을 제공합니다.</span>
			</div>
			<RankingTheme v-for="item in [1]" :key="item" :id="item" @callSectorReport="callSectorReport"/>
      <div style="height: 1200px;">
        <embed  height="100%" width="100%" ref="_sector_report" src="">
      </div>
		</div>
	</div>
</template>

<script>
import RankingTheme from '@/components/item/RankingTheme.vue';

export default {
	name: 'Theme',
	data() {
		return {
		}
	},
  methods:{
    callSectorReport (value) {
      value = this.$fillZero(value,3);
      this.$refs._sector_report.src='/sectorreport/p/'+value;
    }
  },
	components: {
		RankingTheme,
	},
	beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_ranking";
	},
	metaInfo: {
		title: '테마분석 | 10bagger',
	}
}
</script>
