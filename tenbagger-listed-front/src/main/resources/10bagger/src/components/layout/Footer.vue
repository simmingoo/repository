<template>
	<footer>
		<button type="button" class="btn_top" @click="goTop()"><span>TOP</span></button>
		<div class="fmenu">
			<div class="inner">
				<router-link to="/">서비스 약관 / 정책</router-link>
				<router-link to="/" class="on">개인정보 처리방침</router-link>
				<router-link to="/">원격지원</router-link>
				<router-link to="/">고객센터</router-link>
				<router-link to="/">공지사항</router-link>
				<router-link to="/">기술기업풀</router-link>
				<router-link to="/">AI 바우처</router-link>
			</div>
		</div>
		<div class="inner finfo">
			<div class="flogo">10bagger</div>
			<ul>
				<li class="noti">
					10bagger 사이트에서 게재되는 정보는 오류 및 지연이 있을 수 있으며 그 이용에 따르는 책임은 이용자 본인에게 있습니다.<br/>
					또한 이용자는 본 정보를 무단 복사, 전재 할 수 없습니다.
				</li>
				<li class="address"><strong>㈜유니콘랜치</strong> 서울시 영등포구 여의서로 43, 16층 (한서리서파크 16층)</li>
				<li class="biz">Copyright since 2021 © <strong>Unicorn Ranch</strong> Co.,Ltd. All Rights Reserved</li>
			</ul>
		</div>
	</footer>
</template>

<script>
	export default {
		methods:{
			goTop() {
				window.scrollTo({top:0, behavior:'smooth'});
			},
		}
	}
</script>