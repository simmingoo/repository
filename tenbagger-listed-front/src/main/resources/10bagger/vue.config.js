const path = require('path');

module.exports = {
	chainWebpack: config => {
		config.resolve.alias
			.set('@', path.resolve(__dirname, 'src/'))
	},
	outputDir:"../static/",
	indexPath:"../templates/vue/index.html",
	// devServer: { proxy:"http://localhost:8888"},
	devServer: {
    historyApiFallback: true,
		proxy:"https://l.10bagger.co.kr"
	},
	publicPath:"",
	css: {
		sourceMap: true
	}
}
