<template>
	<view class="contentBox">
		<navBarTop title=" " imgNavBg="../../static/images/tuijian.png" backgroundColor="rgba(255, 255, 255, 0)" color="#FFFFFF"></navBarTop>
		<image class="yjpbh" src="../../static/images/yjphb.png" mode=""></image>
		<view class="conetent">
			<view class="tapBoxTop">
				<tapBar :options="options" @tapChange="changeType"></tapBar>
				<view class="chenHeight">
				</view>
			</view>
			<view class="newContent">
				<view class="topTotall">
					<view class="totalls">
						<view class="">
							所得佣金
						</view>
						<view class="bigFont">
							{{recommendObj.money | captionToZero}}
						</view>
					</view>
					<view class="litShu">

					</view>
					<view class="totalls">
						<view class="">
							我的名次
						</view>
						<view class="bigFont">
							{{recommendObj.id | captionToZero}}
						</view>
					</view>
				</view>
				<!-- 排行 -->
				<view class="pList">
					<view class="boxRow">
						<view class="bigTip">
							<text class="blueshu"></text>排行榜
						</view>
					</view>
					<view class="bootTableTop">
						<view class="bootTopOne">
							排名
						</view>
						<view class="bootTopTwo">
							昵称
						</view>
						<view class="bootTopTh">
							所得佣金
						</view>
					</view>
					<view class="pListBox">
						<view class="pOneBox" v-if="recommendList[0]">
							<view class="leftTop">
								<image class="topIcon" src="../../static/images/top01.png" mode=""></image>
							</view>
							<view class="leftHead">
								<image class="topOneHead" src="../../static/images/111.png" mode=""></image>
								<view class="headImgBox">
									<image class="headimg" :src="recommendList[0].headImg?recommendList[0].headImg:'../../static/images/headimgMy.png'"
									 mode=""></image>
									<!-- <image src="../../static/images/headimgMy.png" mode=""></image> -->
								</view>
							</view>
							<view class="middleName">
								{{recommendList[0].userName | captionToKong}}
							</view>
							<view class="rightNum">
								{{recommendList[0].money | captionToZero}}
								<text class="topLitSize">元</text>
							</view>
						</view>
						<view class="pOneBox" v-if="recommendList[1]">
							<view class="leftTop">
								<image class="topIcon" src="../../static/images/top02.png" mode=""></image>
							</view>
							<view class="leftHead">
								<image class="topOneHead" src="../../static/images/222.png" mode=""></image>
								<view class="headImgBox">
									<image class="headimg" :src="recommendList[1].headImg?recommendList[1].headImg:'../../static/images/headimgMy.png'"
									 mode=""></image>
								</view>
							</view>
							<view class="middleName">
								{{recommendList[1].userName | captionToKong}}
							</view>
							<view class="rightNum">
								{{recommendList[1].money | captionToZero}}
								<text class="topLitSize">元</text>
							</view>
						</view>
						<view class="pOneBox" v-if="recommendList[2]">
							<view class="leftTop">
								<image class="topIcon" src="../../static/images/top03.png" mode=""></image>
							</view>
							<view class="leftHead">
								<image class="topOneHead" src="../../static/images/333.png" mode=""></image>
								<view class="headImgBox">
									<image class="headimg" :src="recommendList[2].headImg?recommendList[2].headImg:'../../static/images/headimgMy.png'"
									 mode=""></image>
								</view>
							</view>
							<view class="middleName">
								{{recommendList[2].userName | captionToKong}}
							</view>
							<view class="rightNum">
								{{recommendList[2].money | captionToZero}}
								<text class="topLitSize">元</text>
							</view>
						</view>
						<view class="pOneBox" v-for="(item,index) in recommendList" v-if="index>2" :key="index">
							<view class="leftTop">
								<text class="topNumb">{{index+1==10?'10':'0'+(index+1)}}</text>
							</view>
							<view class="leftHead">
								<view class="headImgBox">
									<image class="headimg" :src="item.headImg?item.headImg:'../../static/images/headimgMy.png'" mode=""></image>
								</view>
							</view>
							<view class="middleName">
								{{item.userName | captionToKong}}
							</view>
							<view class="rightNum">
								{{item.money | captionToZero}}
								<text class="topLitSize">元</text>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	import tapBar from '@/components/tapBar/tapBar.vue'
	import {
		vpnbrokerageRankingListsvc
	} from "../../api/index.js"
	export default {
		data() {
			return {
				options: ['本周', '本月', '总'],
				timeIndex: 1,
				userId: '',
				recommendObj:{},
				recommendList: [] //推荐达人列表
			}
		},
		components: {
			navBarTop,
			tapBar
		},
		filters: {
			captionToZero: function(value) {
				if (value == undefined || value == null || value == '') {
					value = 0
				}
				return value
			},
			captionToKong: function(value) {
				if (value == undefined || value == null || value == '') {
					value = ''
				}
				return value
			},
			//钱的过滤
			filterMoney: function(money) {
				if (money == null || money == NaN || money == "" || money == undefined) {
					return 0
				}
				let num = parseInt(money).toFixed(0);
				let snum = 0;
				if (num >= 10000 && num < 100000000) {
					snum = (num / 10000).toFixed(0);
				} else if (num >= 100000000) {
					snum = (num / 100000000).toFixed(0);
				} else {
					snum = num
				}
				return snum
			},
			filterMoneyP: function(money) {
				if (money == null || money == NaN || money == "" || money == undefined) {
					return ""
				}
				let num = parseInt(money).toFixed(0);
				let snum = "";
				if (num >= 10000 && num < 100000000) {
					snum = "万"
				} else if (num >= 100000000) {
					snum = "亿"
				}
				return snum
			},
		},
		onLoad() {
			this.userId = getApp().globalData.userId;
			this.initRecommend();
		},
		methods: {
			//切换房屋状态
			changeType: function(e) {
				this.timeIndex = parseInt(e) + 1;
				this.initRecommend();
			},
			// 初始化推荐达人
			initRecommend: function() {
				let that = this;
				let data = {
					vpnbrokerageRankingList: {
						userId: that.userId,
						ranking: that.timeIndex
					}
				}
				vpnbrokerageRankingListsvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						that.recommendObj = res.data.vpnincomeDetailGetResp;
						that.recommendList=res.data.vpnincomeDetailGetResp.rankingList||[];
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}


				})
			}
		}
	}
</script>

<style lang="less">
	page,
	.contentBox {
		height: 100%;
		display: flex;
		flex-direction: column;
		.yjpbh{
			width: 540rpx;
			height: 120rpx;
			margin-left: 102rpx;
			margin-top: 20rpx;
		}
	}

	.conetent {
		flex: 1;
		border-radius: 40rpx 40rpx 0 0;
		margin-top: 80rpx;
		display: flex;
		flex-direction: column;

	}

	.newContent {
		flex: 1;
		overflow-y: auto;
		border-radius: 40rpx 40rpx 0 0;
		padding-top: 40rpx;
		background-color: #FFFFFF;
		margin-top: -90rpx;
	}

	.litShu {
		border: 1rpx solid #E5E5E5;
	}

	.topTotall {
		display: flex;
		justify-content: center;
	}

	.totalls {
		width: 48%;
		font-size: 26rpx;
		color: #7E7E7E;
		text-align: center;
	}

	.bigFont {
		color: #1B1B1B;
		font-size: 52rpx;
		line-height: 70rpx;
	}

	.font32 {
		font-size: 32rpx;
	}

	.tapBoxTop {
		background: rgba(0, 0, 0, 0.3);
		border-radius: 40rpx 40rpx 0 0;
		height: 156rpx;
		padding-top: 10rpx;
		width: 100%;

	}

	/* 排行 */
	.pList {
		flex: 1;
	}

	.bigTip {
		color: #1B1B1B;
		font-size: 36rpx;
		font-weight: bold;
		margin-bottom: 10rpx;
	}

	.blueshu {
		border: 2rpx solid #4E66FC;
		display: inline-block;
		height: 28rpx;
		margin-right: 30rpx;
		vertical-align: -7rpx;
	}

	.boxRow {
		padding: 30rpx;
	}

	.bootTableTop {
		padding: 0 30rpx;
		display: flex;
		flex-direction: row;
		color: #BDCADB;
		font-size: 26rpx;
		line-height: 50rpx;
	}

	.bootTopOne {
		width: 60rpx;
	}

	.bootTopTwo {
		flex: 1;
		padding-left: 80rpx;
	}

	.bootTopTh {
		width: 150rpx;
		text-align: right;
	}

	/* list排行列表 */
	.pListBox {
		padding: 0 30rpx;
	}

	.topIcon {
		width: 37rpx;
		height: 44rpx;
		margin-top: 10rpx;
	}

	.pOneBox {
		display: flex;
		flex-direction: row;
		padding: 20rpx 0;
		align-items: center;
	}

	.leftTop {
		width: 60rpx;
	}

	.leftHead {
		width: 110rpx;
		position: relative;
	}

	.middleName {
		flex: 1;
		font-size: 28rpx;
		font-weight: bold;
		color: #1B1B1B;
	}

	.rightNum {
		width: 150rpx;
		text-align: right;
		color: #1B1B1B;
		font-size: 38rpx;
	}

	.topNumb {
		background-color: #D8D7D7;
		font-size: 20rpx;
		color: #FFFFFF;
		padding: 5rpx 8rpx;
		border-radius: 4rpx
	}

	.topOneHead {
		width: 85rpx;
		height: 97rpx;
		position: absolute;
	}

	.headImgBox {
		margin-left: 4rpx;
		margin-top: 17rpx;

	}

	.topLitSize {
		font-size: 24rpx;
	}

	.headimg {
		width: 76rpx;
		height: 76rpx;
		border-radius: 80rpx;
	}

	.chenHeight {
		height: 100rpx;
	}

	/*  */
	.tapList>view {
		font-size: 28rpx !important;
		color: #FFFFFF !important;
	}

	.activeTap {
		font-size: 32rpx !important;
		color: #FFFFFF !important;
		font-weight: bold;
	}

	.tapLitBlue {
		width: 0 !important;
		height: 0 !important;
	}
</style>
