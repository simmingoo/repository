

<template>
	<div class="bx_step col"> <!-- on -->
		<h3><em class="num">{{data.num}}</em>{{data.tit}}</h3>
		<ul>
			<li>
				<div class="tit">기술력 점수(TSS)</div>
				<div class="desc">{{data.score}}</div>
			</li>
			<li>
				<div class="tit">기술력 순위</div>
				<div class="desc"><span class="txt_gray">{{data.total}}개 기업 중 /</span><strong class="point">{{data.myScore}}위</strong></div>
			</li>
			<li>
				<div class="tit">R&D 집중도</div>
				<div class="desc">{{data.rnDConcentration}}%</div>
			</li>
		</ul>
		<div class="bar"><span class="fill" :class="{'full' : data.rnDConcentration ===  100}" :style="'width:'+data.rnDConcentration+'%'"></span></div>
	</div>
</template>

<script>
export default {
	props: ['data'],
  name: 'StepScore',
}
</script>