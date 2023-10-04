<template>
	<div id="app">
		<keep-alive><Header v-if="checkHeaderFooterUrl($route.name)"/></keep-alive>
		<router-view/>
		<keep-alive><Footer v-if="checkHeaderFooterUrl($route.name)"/></keep-alive>
		<!-- <Loading v-if="this.$store.state.isPageLoading"/> -->
	</div>
</template>

<script>
import Header from '@/components/layout/Header.vue';
import Footer from '@/components/layout/Footer.vue';
// import Loading from '@/components/layout/Loading.vue';

export default {
	components : {
		Header,
		Footer,
		// Loading,
	},
  methods:{
    checkHeaderFooterUrl(urlName) {
      if(urlName == 'laSector' || urlName == 'laTheme' || urlName=='chartKospi'
      || urlName == 'chartKosdaq' || urlName=='chartSector' || urlName=='chartSectorStockStatus') {
        return false;
      } else {
        return true;
      }
    }
  }
}
</script>