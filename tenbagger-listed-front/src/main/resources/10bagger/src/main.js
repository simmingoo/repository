import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import VueMeta from 'vue-meta';
import '@/scss/style.scss';
import api from '@/api';
import Highcharts from "highcharts";
import HighchartsVue from "highcharts-vue";
import Item from "highcharts/modules/item-series";
import stockChart from 'highcharts/modules/stock'
import drilldown from 'highcharts/modules/drilldown';
import Maps from "highcharts/modules/map";
import loadWordcloud from 'highcharts/modules/wordcloud';
import CommonUtils from './js/common.js'

drilldown( Highcharts);
stockChart(Highcharts)
Item(Highcharts);
Maps(Highcharts);
loadWordcloud(Highcharts);

Vue.use(HighchartsVue);
Vue.prototype.$api = api;
Vue.config.productionTip = false;
Vue.use(VueMeta);
Vue.use(CommonUtils);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
