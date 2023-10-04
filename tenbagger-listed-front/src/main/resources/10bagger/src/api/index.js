import axios from 'axios';
export default {
	//https -> http 호출 시 오류 있어 백엔드에서 호출 및 api 노출 시키지 않음
	name: 'api',
	async fnGetApiCall(url) {
		return await axios.get(url);
	},
	async fnGetApiParamCall(url, body) {
		return await axios.get(url,{params:body});
	},
}