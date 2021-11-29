<template>
	<view>
		<view class="yingxiao">
			<image src="../../static/images/allpeople.png" mode=""></image>
		</view>
		<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">

			<view class="main"  v-for="(item,index) in strategyList" :key="index" @click="goToDetail(item.stId)" >
				<view class="left">
					<image :src="item.image" mode=""></image>
				</view>

				<view class="right">
					<view class="bold">
						{{item.title}}
					</view>
					<view class="detail-text">
						{{item.introduction}}
					</view>
					<view class="down">
						<uni-icons class="icon" size='28rpx' type="eye-filled" color='#919191'></uni-icons><!--  -->
						<view class="count">
							{{item.readCount}}
						</view>


					</view>
				</view>
			</view>

		</scroll-view>
	</view>
</template>

<script>
	import {
		vpnstrategyGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				strategyList: [],
				pageIndex: 1,
				pageSize: 10,
				canLoad: true,
				toUser:''
			}
		},
		onShow() {
			this.toUser=getApp().globalData.userState;
			this.getStrategyAjax();
		},
		methods: {
			getStrategyAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpnstrategyGet: {
						toUser: that.toUser
					}
				}
				vpnstrategyGetsvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.strategyList = res.data.content;
						} else {
							that.strategyList = [...that.strategyList, ...res.data.content]
						}
						if (res.data.content.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			},
			// 跳转
			goToDetail(id) {
				let stid = id;
				uni.navigateTo({
					url: '../../pagesA/GiveCommission/GiveCommission?stid=' + stid
				})
			},
			botMore() {
				if (this.canLoad != false) {
					this.getStrategyAjax();
				}
			},
		}
	}
</script>

<style lang="less" scoped>
	.scrollBox {
		width: 100%;
		min-height: 30rpx;
		flex: 1;
	}

	.yingxiao {
		width: 750rpx;
		height: 230rpx;

		image {
			width: 750rpx;
			height: 230rpx;
		}
	}

	.main {
		display: flex;
		width: 100%;
		padding: 20rpx 30rpx 12rpx 12rpx;

		.right {
			margin-left: 20rpx;

			.down {
				display: flex;
				margin-top: 22rpx;

				.count {
					margin-top: 5rpx;
					font-size: 24rpx;
					font-weight: 400;
					color: #919191;
				}

				.icon {
					width: 20rpx;
					height: 15rpx;

					margin-right: 20rpx;
				}
			}

			.bold {
				font-size: 30rpx;
				line-height: 36rpx;
				font-weight: bold;
				color: #2C2C2C;
				margin-bottom: 18rpx;
				margin-top: 6rpx;
				//对超出内容隐藏
				overflow: hidden;
				//显示省略符号来代表被修剪的文本
				text-overflow: ellipsis;
				//文本不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。
				white-space: nowrap;
			}

			.detail-text {
				//对超出内容隐藏
				overflow: hidden;
				//显示省略符号来代表被修剪的文本
				text-overflow: ellipsis;
				//文本不会换行，文本会在在同一行上继续，直到遇到 <br> 标签为止。
				white-space: nowrap;
				font-size: 28rpx;
				font-weight: 400;
				color: #1B1B1B;
			}
		}

		.left {
			width: 160rpx;
			height: 160rpx;
			border-radius: 20rpx;

			image {
				width: 160rpx;
				height: 160rpx;

			}
		}
	}
</style>
