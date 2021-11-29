<template>
	<view class="u-content">
		<view class="bold">
			{{detailOne.title}}
		</view>


		<view class="main">
			<view class="time">
				发布时间：{{detailOne.useTime||detailOne.createTime}}
			</view>
			<view class="down">
				<uni-icons class="icon" size='28rpx' type="eye-filled" color='#919191'></uni-icons>
				<view class="count">
					{{detailOne.readCount}}
				</view>
			</view>
		</view>

		<view class="uparse">
			<u-parse :html="detailOne.content"></u-parse>
		</view>


	</view>
</template>

<script>
	import {
		vpnstrategyGetbyidsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				stId: '',
				detailOne: {}
			}
		},
		onLoad(options) {
			this.stId = options.stid;
			this.getStrategy();
		},
		methods: {
			getStrategy() {
				let that = this;
				let data = {
					id: that.stId
				}
				vpnstrategyGetbyidsvc(data).then(res => {
					if (res.code == 1) {
						console.log(res)
						that.detailOne = res.data.vpnstrategyGet
						console.log(that.detailOne)
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.u-content {
		margin-top: 50rpx;

		.bold {
			height: 34rpx;
			font-size: 36rpx;
			font-family: PingFang SC;
			font-weight: bold;
			color: #2C2C2C;
			margin-left: 37rpx;
			margin-bottom: 40rpx;
		}

		.uparse {
			margin-left: 37rpx;
			margin-bottom: 40rpx;
		}


		.main {
			display: flex;

			.down {
				display: flex;

				.count {
					font-size: 20rpx;
					margin-top: 8rpx;
				}

				.icon {
					width: 20rpx;
					height: 15rpx;
					margin: 0 20rpx;
				}

			}

			.time {
				margin-left: 37rpx;
				margin-bottom: 60rpx;
				font-size: 24rpx;
				width: 289rpx;
				height: 23rpx;
				font-family: PingFang SC;
				font-weight: 500;
				color: #919191;
			}
		}
	}
</style>
