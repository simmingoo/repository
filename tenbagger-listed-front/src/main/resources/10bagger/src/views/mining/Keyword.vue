<template>
	<div class="container">
		<div class="inner">
			<div class="p_top">
				<h1>키워드 검색</h1>
				<span class="desc">기술관련 키워드를 입력하면 그에 맞는 기업들로 투자마이닝을 수행합니다.</span>
			</div>
			<div class="content">
				<div class="c_top">
					<h2>검색어 입력</h2>
					<span class="desc">기술관련 키워드를 입력하면 그에 맞는 기업들로 투자마이닝을 수행합니다.</span>
				</div>
				<div class="area_search">
					<div class="c_tap">
						<button type="button" class="tap on">키워드 입력을 통한 마이닝</button>
						<button type="button" class="tap">문장 입력을 통한 마이닝</button>
					</div>
					<div class="bx_search">
						<input type="text" placeholder="마이닝을 수행하기 위한 검색어를 입력하여 주십시오."/>
						<button type="button">검색</button>
					</div>
				</div>
			</div>
			<section class="content">
				<article class="list_company">
					<div class="c_tap">
						<button type="button" class="tap on">전체기업(55)</button>
						<button type="button" class="tap">기술력 TOP10</button>
						<button type="button" class="tap">투자매력 TOP10</button>
						<button type="button" class="tap">상업기업 in TOP100</button>
						<button type="button" class="tap">신규 진출기업</button>
					</div>
					<MiningCompany v-for="item in arr_temp" :key="item"/>
					<div class="btns"><button type="button" class="btn">더보기 +</button></div>
				</article>
			</section>
		</div>
	</div>
</template> 

<script>
import MiningCompany from '@/components/item/MiningCompany.vue';

export default {
	name: 'Investment',
	data() {
		return {
			arr_temp: [1,2,3,4,5],
		}
	},
	components: {
		MiningCompany,
	},
	beforeCreate() {
		document.getElementsByTagName("html")[0].className = "p_mining";
	},
	metaInfo: {
		title: '키워드 검색 | 10bagger',
	}
}
</script>
