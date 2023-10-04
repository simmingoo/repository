<template>
	<div class="slct" :style="{ width: width + 'px' }" :class="{classNm : classNm !== undefined}">
		<div class="slct_tit" @click="toggleSlct">{{tit}}</div>
		<ul class="slct_cont">
			<li v-for="(item, index) in options" :class="{select : item.select === true}" :key="index" :data-id="index" :data-no="item.no" :data-value="item.name" :ref="item.ref+'_'+item.no" @click="onClickItem">{{item.name}}</li>
		</ul>
	</div>
</template>
<script>
export default {
	props: ['list', 'width','classNm'],
	data() {
		return {
			tit: "",
			on: false,
			options: this.list,
		}
	},
	methods: {
		toggleSlct(e) {
			const slctList = document.getElementsByClassName("slct");
			const target = e.target.parentNode;
			const onState = target.classList.contains("on") ? true : false;
			Array.prototype.forEach.call(slctList, item => item.classList.remove("on"));
			if (!onState) target.classList.add("on");
			else target.classList.remove("on");
		},
		onClickItem(e) {
			const id = e.target.getAttribute("data-id");
			const target = e.target.parentNode.parentNode;
			this.options.map((item, index) => {
				item.select = false;
				if (index == id) {
					item.select = true; 
					this.tit = item.name;
					this.on = false;
				}
			});
			target.classList.remove("on");
			this.$emit("setTargetNo", e.target.getAttribute("data-no"));
		},
		setTit(){
			this.options.map(item => {
				if (item.select === true) {
					this.tit = item.name;
					return;
				}
			});
		}
	},
	created() {
		document.addEventListener("click", e => {
			const slctList = document.getElementsByClassName("slct");
			if(slctList.length > 0){
				const temp = e.target.className.split(" ");
				const target = e.target.className !== "" ? slctList[0].querySelector("." + temp[0]) : null;
				if (target === null) {
					Array.prototype.forEach.call(slctList, item => item.classList.remove("on"));
				}
			}
		});
	},
  mounted() {
		this.setTit();
  },
	beforeUpdate(){
		this.setTit();
	}
}
</script>