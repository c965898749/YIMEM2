<template>
	<view class="content">
		<navBarTop title="推荐进展" color="#FFFFFF" backgroundColor="rgba(0,0,0,0)"
			imgNavBg="../../static/images/jhzu2.png"></navBarTop>
		<view class="oneItem">
			<view class="pepole">
				<view class="pname">
					{{users.userName}}-{{users.phoneNum}}
				</view>
				<view class="">
					<text class="tagBox">{{users.state | filterState}}</text>
				</view>
			</view>
			<view class="lineRow"></view>
			<view class="pContent">
				<view class="pText">
					<text class="pTip">应聘职位：</text> <text>{{users.jobName}}</text>
				</view>
				<view class="pText">
					<text class="pTip">预估收益：</text> <text class="redMonye">￥{{users.brokerage}}</text>
				</view>
			</view>
		</view>
		<view class="cardBox">
			<view class="cardTitle"> <text class="cardShu"></text> 进展详情</view>
			<view class="cardContent">
				<view class="stepingBox" v-for="(item,index) in proDetail" :key="index">
					<view class="stepTitleFont">
						<image src="../../static/images/jhclise.png" mode=""></image><text
							class="stepleftf">{{item.state | filterState}}</text>
					</view>
					<view class="blueLineBox">
						<view class="huiLine">
							<!-- <view class="stepTip">
								到账收益：<text class="redMonye">{{item.money}}</text>
							</view> -->
							<view class="stepTip">
								更新时间：<text class="timeText">{{item.createTime}}</text>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>

		<view class="down">
			<image src="../../static/images/warn.png" mode=""></image>
			<view class="downText">
				如果招聘官推荐的求职者入职不满30天或中途
				离职，则此人奖励无效!
			</view>
		</view>
	</view>
</template>

<script>
	import navBarTop from "../../components/nav-bar-top/nav-bar-top.vue"
	import {
		vpnrecommendDetailGetsvc,
		vpnrecommendUserDetailGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				reId: '',
				proDetail: '',
				users: '',
			};
		},
		filters: {
			filterState(val) {
				let states = {
					0: '不合适',
					1: '已投递',
					2: '已邀约',
					3: '待面试',
					4: '推送企业',
					5: '企业面试',
					6: '已面试',
					7: '已入职',
					8: '已离职',
					9: '离职审核中',
					10: '是已收佣',
				};
				return states[val]
			}
		},
		components: {
			navBarTop,
		},
		onLoad(options) {
			this.reId = options.delId;
			this.getDetail();
			this.userDetail();
		},
		methods: {
			userDetail: function() {
				let that = this;
				let data = {
					id: that.reId
				}
				vpnrecommendUserDetailGetsvc(data).then(res => {
					if (res.code == 1) {
						that.users = res.data
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			getDetail: function() {
				let that = this;
				let data = {
					id: that.reId
				}
				vpnrecommendDetailGetsvc(data).then(res => {
					if (res.code == 1) {
						that.proDetail = res.data.vpndeliverGetResp
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
	.content{
		
	}
	.down {

		.downText {

			font-size: 26rpx;
			font-family: PingFang SC;
			font-weight: 500;
			color: #919191;
			margin-left: 20rpx;

		}

		image {
			margin-top: 10rpx;
			margin-left: 20rpx;
			width: 63rpx;
			height: 43rpx;

		}

		padding: 26rpx;
		position: fixed;
		display: flex;
		bottom: 0;
		margin: 30rpx;
		width: 690rpx;
		height: 115rpx;
		background: #E1E1E1;
		border-radius: 10rpx;
	}

	.oneItem {
		margin: 60rpx 25rpx 0;
		min-height: 245rpx;
		background: #FFFFFF;
		border-radius: 10rpx;
		padding: 30rpx;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
		.pepole {
			display: flex;
			justify-content: space-between;
			padding: 10rpx 0 20rpx;
			.pname {
				font-size: 30rpx;
				color: #2C2C2C;
				font-weight: bold;
			}
		}

		.pContent {
			.pText {
				padding: 7rpx 0;
				font-size: 26rpx;
				color: #2C2C2C;
				.pTip {
					color: #919191;
				}
				.redMonye {
					color: #FE2B67;
				}
			}
		}
	}

	/*cardbox*/
	.cardBox {
		overflow-y: auto;
		min-height: 300rpx;
		flex: 1;
		padding: 30rpx;
		margin: 25rpx;
		padding-bottom: 120rpx;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
	}

	.cardTitle {
		font-size: 30rpx;
		font-weight: bold;
	}

	.cardShu {
		background: #2E73FF;
		display: inline-block;
		width: 6rpx;
		height: 30rpx;
		border-radius: 6rpx;
		margin-right: 10rpx;
		vertical-align: -5rpx;
	}

	.cardContent {
		padding-top: 20rpx;
	}

	/**/

	.tagBox {
		display: inline-block;
		padding: 8rpx 12rpx;
		font-size: 20rpx;
		background: #FFEDE2;
		color: #FF8F4B;
		border-radius: 8rpx;
	}

	.lineRow {
		border-bottom: 1rpx solid #F3F3F3;
		margin-bottom: 10rpx;
	}

	/* 项目进度 */

	.stepingBox image {
		width: 42rpx;
		height: 42rpx;
	}

	.stepTitleFont {
		color: #2A2A2A;
		font-size: 28rpx;
		font-weight: bold;
		height: 40rpx;
		display: flex;
		align-items: flex-start
	}

	.stepleftf {
		padding-top: 5rpx;
		margin-left: 30rpx;
		font-size: 26rpx;
		color: #2E73FF;
		font-weight: bold;
	}

	.timeText {
		color: #1B1B1B;
		font-size: 24rpx;
	}

	.blueLineBox {
		padding: 0 0rpx 10rpx 45rpx;
		margin-left: 18rpx;
		border-left: 4rpx solid rgba(78, 102, 252, 0.5);
	}

	.stepTip {
		color: #999999;
		font-size: 24rpx;
		line-height: 30rpx;
		padding: 20rpx 0 20rpx
	}
</style>
