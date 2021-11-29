<template>
	<view class="content">
		<view class="">
			<image class="bannerImg" src="../../static/images/strategyBanner2.png" mode="widthFix"></image>
		</view>
		<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">

			<view class="listTip">
				<view class="tipOne" v-for="(item,index) in strategyList" :key="index" @click="goToDetail(item.stId)">
					<view class="tipBox"> 
						<!-- <view>
							<image class="leftImg" src="../../static/images/imgIcon.png" mode=""></image>
						</view> -->
						<view class="rightBox">
							<view class="rightTitle">
								{{item.title}}
							</view>
							<view class="rightCont">
								{{item.content}}
							</view>
							<view class="rightBot">
								<uni-icons type="eye-filled" size="15" color='#919191'></uni-icons> <text class="seeNum">{{item.readCount}}</text>
							</view>
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
			};
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
			goToDetail(id) {
				let stid = id;
				uni.navigateTo({
					url: '../../pagesA/strategyDetails/strategyDetails?stid=' + stid
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

<style lang="less">
	.text{
		 
		margin: 40rpx;
		 font-size: 28rpx;
	}
	
	.bannerImg {
		width: 100%;
		height: 233rpx;
	}

	.scrollBox {
		min-height: 30rpx;
		flex: 1;
	}

	.tipOne {
		margin: 0 25rpx;
		border-bottom: 1rpx solid #F3F3F3;
		padding: 30rpx 0;
	}

	.tipBox {
		display: flex;
	}

	.seeNum {
		color: #919191;
		font-size: 20rpx;
		margin-left: 10rpx;
	}

	.leftImg {
		width: 160rpx;
		height: 160rpx;
	}

	.rightBox {
		flex: 1;
		padding-left: 20rpx;
		overflow: hidden;
	}

	.rightTitle {
		font-size: 30rpx;
		color: #2C2C2C;
		font-weight: bold;
		line-height: 36rpx;
	}

	.rightCont {
		margin-top: 15rpx;
		color: #1B1B1B;
		font-size: 24rpx;
		line-height: 36rpx;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}

	.rightBot {
		margin-top: 30rpx;
	}
</style>
