import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [

  {
    path: '/',
		redirect: '/sectorRanking',
  },
	{
		path: '/sectorRanking',
		name: 'SectorRanking',
		component: () => import(/* webpackChunkName: "sector" */ '@/views/ranking/Sector.vue')
	},
	{
		path: '/themeRanking',
		name: 'ThemeRanking',
		component: () => import(/* webpackChunkName: "ranking" */ '@/views/ranking/Theme.vue')
	},
	{
		path: '/keywordMining',
		name: 'Keyword',
		component: () => import(/* webpackChunkName: "mining" */ '@/views/mining/Keyword.vue')
	},
	{
		path: '/trendMining',
		name: 'Trend',
		component: () => import(/* webpackChunkName: "mining" */ '@/views/mining/Trend.vue')
	},
	{
		path: '/ready',
		name: 'Ready',
		component: () => import(/* webpackChunkName: "etc" */ '@/views/etc/Ready.vue')
	},
	{
		path: '/reportstock',
		name: 'reportstock',
		component: () => import(/* webpackChunkName: "report" */ '@/views/report/Stock.vue')
	},
	{
		path: '/detailStock',
		name: 'detailStock',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/Stock.vue'),
	},
	{
		path: '/detailCorp',
		name: 'detailCorp',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/Corp.vue'),
	},
	{
		path: '/detailRanking',
		name: 'detailRanking',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/Ranking.vue'),
	},
	{
		path: '/detailSkill',
		name: 'detailSkill',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/Skill.vue'),
	},
	{
		path: '/detailFinance',
		name: 'detailFinance',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/Finance.vue'),
	},
	{
		path: '/detailFinanceInfo',
		name: 'detailFinanceInfo',
		component: () => import(/* webpackChunkName: "report" */ '@/views/detail/FinanceInfo.vue'),
	},
	{
		path: '/laSector',
		name: 'laSector',
		component: () => import(/* webpackChunkName: "sector" */ '@/views/ranking/LaSector.vue'),
	},
	{
		path: '/laTheme',
		name: 'laTheme',
		component: () => import(/* webpackChunkName: "ranking" */ '@/views/ranking/LaTheme.vue'),
	},
	{
		path: '/chartKospi',
		name: 'chartKospi',
		component: () => import(/* webpackChunkName: "chart" */ '@/views/chart/Kospi.vue')
	},
	{
		path: '/chartKosdaq',
		name: 'chartKosdaq',
		component: () => import(/* webpackChunkName: "chart" */ '@/views/chart/Kodaq.vue')
	},
	{
		path: '/chartSector',
		name: 'chartSector',
		component: () => import(/* webpackChunkName: "chart" */ '@/views/chart/Sector.vue')
	},
	{
		path: '/chartSectorStockStatus',
		name: 'chartSectorStockStatus',
		component: () => import(/* webpackChunkName: "chart" */ '@/views/chart/SectorStockStatus.vue')
	},
	{
		path: "*",
		redirect: '/sectorRanking',
	},
	{
		path: '/:catchAll(.*)',
		name: 'notFound',
		component: () => import(/* webpackChunkName: "etc" */ '@/views/etc/Ready.vue')
	},
]

const router = new VueRouter({
  routes,
  mode: 'history',
  base: process.env.BASE_URL
})
router.beforeEach((to, from, next) => {
	//store.commit('setPageLoading', true);
	next();
});

export default router
