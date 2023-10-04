import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state: {
		// isPageLoading:false,
		topData: { 
			// 공통 컴포먼트 분리하려고, api와 상관없이 임시로 만든 데이터 입니다. 개발 하실때 변경해서 사용하셔도 되요.
			// 초기 데이터 지우고, 종목이 선택될때 스토어 setTopData 호출해서 값설정해주시면 됩니다.
			type:"",
			tit:"",
			num:"",
			stock:"",
			tag:[],
			price:"",
			increase:{
				nm:"",
				price:[]
			},
			date:"2021/11/11 13:30",
			onWish:"",
			onReco:"", //on
		},


	},
	mutations: {
		// setPageLoading(state, payload) {
		// 	state.isPageLoading = payload;
		// },
		setTopData(state, payload) {
			state.topData = payload;
		},
	},
	actions: {
		setTopData({ commit }, request) {
			commit('setTopData', request);
		},
	},
	modules: {
	}
})
