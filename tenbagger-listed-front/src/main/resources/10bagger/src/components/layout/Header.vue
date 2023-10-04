<template>
	<div class="hwrap">
		<header>
			<div class="inner">
				<router-link to="/" class="logo">10bagger - 상장</router-link>
				<nav @mouseleave="offMenu">
					<ul class="gnb">
						<li>
							<button class="menu" @mouseover="onMenu"><strong>섹터/테마 기술랭킹 </strong></button>
							<ul class="dep">
								<li><router-link to="/sectorRanking" class="smenu">섹터/테마 랭킹</router-link></li>
								<li><router-link to="/themeRanking" class="smenu">섹터/테마 분석</router-link></li>
							</ul>
						</li>
						<li>
							<button class="menu" @mouseover="onMenu"><strong>섹터/투자마이닝</strong></button>
							<ul class="dep">
								<li><router-link to="/keywordMining" class="smenu">키워드 검색</router-link></li>
								<li><router-link to="/trendMining" class="smenu">트렌드 검색</router-link></li>
							</ul>
						</li>
						<li>
							<button class="menu" @mouseover="onMenu"><strong>AI기술종목분석</strong></button>
							<ul class="dep">
								<li><router-link to="/ready" class="smenu">추천 종목분석</router-link></li>
							</ul>
						</li>
						<li>
							<button class="menu" @mouseover="onMenu"><strong>매매신호서비스</strong></button>
							<ul class="dep">
								<li><router-link to="/ready" class="smenu">기술기반 매매신호</router-link></li>
							</ul>
						</li>
						<li>
							<button class="menu" @mouseover="onMenu"><strong>테마포착</strong></button>
							<ul class="dep">
								<li><router-link to="/ready" class="smenu">기술테마 이슈포착</router-link></li>
							</ul>
						</li>
					</ul>
				</nav>
				<div class="right">
					<div class="h_search">
						<input type="text" placeholder="종목명 또는 종목코드를 입력하십시오." class="ipt"/>
						<button	type="button" class="btn_search" @click="onSearch">검색</button>
					</div>
					<button type="button" class="btn_login">로그인</button>
						<!-- <button type="button">회원가입</button> -->
				</div>
			</div>
		</header>
	</div>
</template>

<script>
	export default {
		created() {
			document.addEventListener("click", e => {
				const search = document.getElementsByClassName("h_search")[0];
				const temp = e.target.className.split(" ");
				let target = e.target.className !== "" ? search.querySelector("." + temp[0]) : null;
				if(target=== null && temp[0] === "h_search") target = search;
				if(target === null) search.classList.remove("on");
			});
		},
		methods:{
			onMenu: () => {
				document.getElementsByTagName("nav")[0].classList.add("on");
			},
			offMenu: () => {
				document.getElementsByTagName("nav")[0].classList.remove("on");
			},
			onSearch: (e) => {
				console.log(e.target);
				const target = e.target.parentNode;
				if(target.classList.contains("on")){
					// target.classList.remove("on");
					//검색처리
				} else target.classList.add("on");
			},
		}
	}
</script>