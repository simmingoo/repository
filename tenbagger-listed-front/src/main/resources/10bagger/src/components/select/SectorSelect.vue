<template>
	<div class="col">
		<h3>
			{{tit}} <span class="num">{{cnt>0 ? cnt : 0}}</span>개
		</h3>
		<Select :list="list" @setTargetNo="setTargetNo"/>
	</div>
</template>

<script>
import Select from '@/components/common/Select.vue';

export default {
	props: ["tit", "type","targetNo"],
	components : {
		Select,
	},
	data() {
		return {
			cnt: 0,
			list:[],
		}
	},
	methods: {
		fn_Get_Sector_List() {
			this.$api.fnGetApiCall("/sector/list")
			.then((res) => {
				this.cnt = res.data.length;
				this.list.push({name: '섹터를 선택하십시오', select: true});
				res.data.map(item => {
						this.list.push({
							name:item.sectorName,
							no:item.sectorNo,
              ref:'sector'
						});
				});
			})
			.catch((err) => {
				console.log(err)
			});
		},
		fn_Get_Theme_List() {
			//테스트로 임의로 작업했습니다.
			if(this.targetNo === null || this.targetNo <= 0) {
				this.list.push({name: '섹터를 선택하십시오', select: true});
			} else {
				this.$api.fnGetApiCall("/sector/theme/list/"+this.targetNo)
				.then((res) => {
					this.cnt = res.data.length;
					this.list.push({name: '테마를 선택하십시오', select: true});
					res.data.map(item => {
						this.list.push({
							name:item.themeName,
							no:item.themeNo,
              ref:'theme'
						});
					});
				})
				.catch((err) => {
					console.log(err);
					this.list = [];
					this.list.push({name: '섹터를 선택하십시오', select: true});
				});
			}
		},
		setTargetNo(no){
			this.$emit("setTargetNo", no);
		}
	},
	mounted() {
		switch(this.type){
			case "sector":
				this.fn_Get_Sector_List();
				break;
			case "theme":
				this.fn_Get_Theme_List();
				break;
			default:
				this.fn_Get_Sector_List();
				break;
		}
	}
}
</script>
