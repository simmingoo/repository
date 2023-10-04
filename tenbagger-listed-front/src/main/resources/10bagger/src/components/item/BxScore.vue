
<template>
	<div class="bx_score col"> <!-- on -->
		<h3>{{data.tit}}</h3>
		<div class="point">
			<strong>{{data.score}}</strong> <span>점</span>
		</div>
		<div class="bar_score"><span class="fill" :class="{'full' : data.position ===  100}" :style="'width:'+data.position+'%'"><em>{{data.myScore}}위</em></span></div>
		<p>전체 <strong>{{data.total}}</strong>개 기업 중</p>
	</div>
</template>

<script>
export default {
	props: ['data'],
  name: 'BxScore',
}
</script>