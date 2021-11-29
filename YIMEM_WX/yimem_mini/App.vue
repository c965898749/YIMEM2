<script>
	export default {
		onLaunch: function() {
			const updateManager = wx.getUpdateManager()
			updateManager.onUpdateReady(function() {
				wx.showModal({
					title: '更新提示',
					content: '新版本已经准备好，是否重启应用？',
					success(res) {
						if (res.confirm) {
							// 新的版本已经下载好，调用 applyUpdate 应用新版本并重启
							updateManager.applyUpdate()
						}
					}
				})
			})
			console.log('App Launch');
		},
		onShow: function() {
			console.log('App Show');
			let that = this;
			uni.getSystemInfo({
				success(res) {
					that.globalData.topHeight = res.statusBarHeight
				}
			});
			// uni.getLocation({
			// 	type: 'wgs84',
			// 	geocode: true,
			// 	success: function(res) {
			// 		let addressLAL = {};
			// 		addressLAL.longitude = res.longitude; //经度
			// 		addressLAL.latitude = res.latitude; //纬度
			// 		that.globalData.addressLAL = addressLAL;
			// 	}
			// })
		},
		onHide: function() {
			console.log('App Hide');
		},
		globalData: {
			topHeight: '',
			imgUrl: '',
			userState: 1, //1求职者，2分销者，3企业端
			userId: '',
			userInfo: "", //用户信息
			compenyInfo: '', //公司信息
			compenyState:'',//1待审核 2通过 0不通过
			isLogin: 0, //0未登录，1是登录
			reUserId: '', //分享者的id
			wxInfo: {}
		},
	};
</script>

<style lang="scss">
	@import "uview-ui/index.scss";
	@import url("static/icon/icons.css");
	/* 解决头条小程序组件内引入字体不生效的问题 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		src: url('/static/uni.ttf');
	}
 
	/* #endif */
	view {
		box-sizing: border-box;
	}

	page,
	.content {
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.row:before,
	.row:after {
		content: "";
		display: block;
		height: 0;
		width: 0;
		visibility: hidden;
		clear: both;
	}

	[class*='col_'] {
		float: left;
		min-height: 1px;
		width: 8.33%;
		box-sizing: border-box;
		-moz-box-sizing: border-box;
		-o-box-sizing: border-box;
		-ms-box-sizing: border-box;
		-webkit-box-sizing: border-box;
	}

	.col_1 {
		width: 8.33%;
	}

	.col_2 {
		width: 16.66%;
	}

	.col_3 {
		width: 25%;
	}

	.col_4 {
		width: 33.33%;
	}

	.col_5 {
		width: 41.66%;
	}

	.col_6 {
		width: 50%;
	}

	.col_7 {
		width: 58.33%;
	}

	.col_8 {
		width: 66.66%;
	}

	.col_9 {
		width: 75%;
	}

	.col_10 {
		width: 83.33%;
	}

	.col_11 {
		width: 91.66%;
	}

	.col_12 {
		width: 100%;
	}

	.flexCenter {
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.flexLeft {
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		align-items: flex-start;
		flex-wrap: wrap;
	}

	.icon_right {
		content: "";
		position: absolute;
		bottom: calc((100% - 12rpx) / 2);
		width: 14rpx;
		height: 14rpx;
		border: 1rpx solid #999;
		border-left: transparent;
		border-top: transparent;
		transform: rotate(-45deg);
		right: 30rpx;
		top: 14rpx;
		z-index: 99;
	}
</style>
