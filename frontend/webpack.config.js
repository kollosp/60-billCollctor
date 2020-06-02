const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin')
const ExtractTextPlugin = require("extract-text-webpack-plugin")
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const { VueLoaderPlugin } = require('vue-loader')

module.exports = {
	mode: 'development',
	entry: {
		index: './src/index.js',
	},
	output: {
		path: path.resolve(__dirname, '../BillManagementSystem/static'),
		filename: '[name].js',
		publicPath: ''
	},
	devServer: {
		path: path.resolve(__dirname, '../BillManagementSystem/static'),
		compress: true,
		port: 9000
	},
	module: {
		rules: [{
			test: /\.(png|svg|jpg|gif)$/,
			use: [{
				loader: 'url-loader',
				options: {
					name: '[hash].[ext]',
					outputPath: '/',
					esModule: false,
				}
			}]
		},
			{
				test: /\.m?js$/,
				exclude: /(node_modules|bower_components)/,
				use: {
					loader: 'babel-loader',
					options: {
						presets: ['@babel/preset-env']
					}
				}
			},{
				test: /\.s[ac]ss$/i,
				use: [
					MiniCssExtractPlugin.loader,
					{
						loader: 'css-loader',
						options: { sourceMap: true }
					},
					'sass-loader',
				],
			},{
				test: /\.css$/,
				use: [
					'vue-style-loader',
					'css-loader'
				]
			},{
				test: /\.vue$/,
				loader: 'vue-loader',
			},{
				test: /\.(pdf)$/,
				use: [
					{
						loader: 'file-loader',
						options: {
							name: '[name].[ext]'
						}
					},
				]
			}
		]
	},
	plugins: [
		new HtmlWebpackPlugin({
			template: "./src/views/index.html",
			filename: "index.html",
			chunks: ['index']
		}),
		//new ExtractTextPlugin('css/styles.css'),
		new MiniCssExtractPlugin({
			filename: 'styles.css',
			chunkFilename: '[id].css',
		}),
		new VueLoaderPlugin(),
	],
	watch: false
};
