<template>
	<view class="content">
		<image class="myBanner" src="../../static/images/myBanner.png" mode="widthFix"></image>
		<view class="topBox">
			<view class="changeUserType" @click="goToPage" data-urlp="../../pageB/switch/switch"
				:style="'top:'+topHeight+'px'">
				<text v-if="userState==1">我是求职者</text>
				<text v-if="userState==2">我是分销者</text>
				<text v-if="userState==3">我是招聘者</text>
				<text class="iconfont iconqiehuan"></text>
			</view>
			<view class="magTop">
				<view class="headBox">
					<image v-if="userInfo.headImg" class="headImg" :src="userInfo.headImg" mode="aspectFill"></image>
					<image class="headImg" v-else
						:src="wxInfo.avatarUrl?wxInfo.avatarUrl:'../../static/images/headimgMy.png'" mode="aspectFill">
					</image>
				</view>

				<view class="isLog" v-if="isLogin==1">
					<view class="userName">
						<text v-if="userInfo.userName">{{userInfo.userName}}</text>
						<text v-else>{{wxInfo.nickName}}</text>
						<image class="editIcon" src="../../static/images/jheeead.png" mode=""
							data-urlp="../../pagesA/personalSet/personalSet" @click="goToPage"></image>
					</view>
					<view class="phone">
						手机号：{{userInfo.phoneNum}}
					</view>
				</view>
				<view class="isLog" v-else>

				</view>



				<button class="infoBtn" @click="getWxInfo" open-type="getUserInfo" v-if="isLogin!==1">注册/登陆</button>
				<button @click="sign" class="infoBtn2" open-type="getUserInfo" v-if="isLogin==1">
					<image class="checkin" src="../../static/images/checkin.png" mode=""></image>
					签到
				</button>



				<!-- <u-modal v-model="show" :content="content"></u-modal> -->

				<uniPopup ref="popup" type="center" v-model="show">
					<view class="popBox">
						<view class="cha" @click="close()">
							<uni-icons type="closeempty"></uni-icons>
						</view>


						<view class="imagepop">
							<image class="kefuIcon" src="../../static/images/now.png" mode="widthFix"></image>
						</view>
						<view class="popContent">
							<view class="centerBlack">
								签到成功
							</view>
							<view class="huiText">
								获得1积分，已连续签到{{point.contDays}}天
								<!--{{point.value}}  -->
							</view>
						</view>
					</view>
				</uniPopup>
			</view>
			<view class="tapList" v-if="userState==1">
				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/myApplication/myApplication">
					<image src="../../static/images/wodeyingpin.png" mode=""></image>
					<view class="">
						我的应聘
					</view>
				</view>
				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/resume/resume">
					<image src="../../static/images/wodejianli.png" mode=""></image>
					<view class="">
						我的简历
					</view>
				</view>
				<view class="oneTap" @click="goToPage" data-urlp='../../pagesA/myCollection/myCollection'>
					<image src="../../static/images/jhzusee11.png" mode=""></image>
					<view class="">
						我的收藏
					</view>
				</view>
				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/personalSet/personalSet">
					<image src="../../static/images/setdown.png" mode=""></image>
					<!-- <text class="redPoint" v-if="haveRead==1"></text> -->
					<view class="">
						设置中心
					</view>
				</view>
				<!-- 求职者页面 -->
			</view>
			<view class="tapList" v-if="userState==2">
				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/myWallet/myWallet">
					<image src="../../static/images/jhzu9.png" mode=""></image>
					<view class="">
						我的钱包
					</view>
				</view>

				<view class="oneTap" @click="goToPage" data-urlp='../../pagesA/myCollection/myCollection'>
					<image src="../../static/images/jhzusee11.png" mode=""></image>
					<view class="">
						我的收藏
					</view>
				</view>

				<view class="oneTap" @click="goToPage" data-urlp="../../pageB/inviteFriends/inviteFriends">
					<!-- 地址没换 -->
					<image src="../../static/images/muzhi.png" mode=""></image>
					<view class="">
						邀请好友
					</view>
				</view>


				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/personalSet/personalSet">
					<image src="../../static/images/setdown.png" mode=""></image>
					<!-- <text class="redPoint" v-if="haveRead==1"></text> -->
					<view class="">
						设置中心
					</view>
				</view>
				<!-- 分摊者 -->
			</view>
			<view class="tapList" v-if="userState==3">
				<view class="oneTap" @click="goToPage" data-urlp='../../pagesA/companyInformation/companyInformation'>
					<image src="../../static/images/gongsi.png" mode=""></image>
					<view class="">
						公司信息
					</view>
				</view>
				<view class="oneTap" @click="goToPage" data-urlp='../../pagesA/myCollection/myCollection'>
					<image src="../../static/images/shoucang.png" mode=""></image>
					<view class="">
						我的收藏
					</view>
				</view>
				<view class="oneTap" @click="goToPage" data-urlp="../../pageB/deposit/deposit">
					<image src="../../static/images/money1.png" mode=""></image>
					<view class="">
						我的押金
					</view>
				</view>

				<view class="oneTap" @click="goToPage" data-urlp="../../pagesA/personalSet/personalSet">
					<image src="../../static/images/setdown.png" mode=""></image>
					<!-- <text class="redPoint" v-if="haveRead==1"></text> -->
					<view class="">
						设置中心
					</view>
				</view>



				<!-- 招聘者信 -->
			</view>
		</view>
		<view class="funcList">
			<view class="">
				更多功能
			</view>
			<view class="moreFun">
				<uni-grid :column="4" :show-border="false" :square="false">
					<uni-grid-item v-if="userInfo.isdistribution==0 && userState==1">
						<view class="gridOne" @click="goToPage" data-urlp="../../pageB/joinreseller/joinreseller">
							<image src="../../static/images/sqfxs.png" mode=""></image>
							<view class="">
								分销申请
							</view>
						</view>
					</uni-grid-item>
					<uni-grid-item v-if="userState==2">
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/myApplication/myApplication">
							<image src="../../static/images/jh1272.png" mode=""></image>
							<view class="">
								我的应聘
							</view>
						</view>
					</uni-grid-item>
					<uni-grid-item v-if="userState==2">
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/resume/resume">
							<image src="../../static/images/jhzu6.png" mode=""></image>
							<view class="">
								我的简历
							</view>
						</view>
					</uni-grid-item>


					<uni-grid-item>
						<view class="gridOne" @click="goToPage" data-urlp="../../pageB/points/points">
							<image src="../../static/images/points.png" mode=""></image>
							<view class="">
								我的积分
						 
							</view>
						</view>
					</uni-grid-item>

					<uni-grid-item v-if="userState==2">
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/noviceGuidance/noviceGuidance">
							<image src="../../static/images/jhzuenmmm.png" mode=""></image>
							<view class="">
								新手引导
							</view>
						</view>
					</uni-grid-item>

					<uni-grid-item v-if="userState!=1">
						<view class="gridOne" @click="goToPage" data-urlp='../../pagesA/userAgreement/userAgreement'>
							<image src="../../static/images/jhzudeeee.png" mode=""></image>
							<view class="">
								用户协议
							</view>
						</view>
					</uni-grid-item>


					<uni-grid-item>
						<view class="gridOne" @click="contactUser">
							<image src="../../static/images/jhzu212544.png" mode=""></image>
							<view class="">
								联系客服
							</view>
						</view>
					</uni-grid-item>

					<uni-grid-item>
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/complaint/complaint">
							<image src="../../static/images/jhzu33344.png" mode=""></image>
							<view class="">
								投诉建议
							</view>
						</view>
					</uni-grid-item>

					<!-- 
					<uni-grid-item v-if="userState==2">
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/getSendList/getSendList">
							<image src="../../static/images/jhzu4.png" mode=""></image>
							<view class="">
								收支明细
							</view>
						</view>
					 
					</uni-grid-item>
					<uni-grid-item v-if="userState==2">
						<view class="gridOne" @click="goToPage" data-urlp="../../pagesA/talentRanking/talentRanking">
							<image src="../../static/images/jhzu5.png" mode=""></image>
							<view class="">
								排行榜
							</view>
						</view>
					</uni-grid-item> -->



					<!-- 求职者 -->

				</uni-grid>
				<image class="fixe-area" src="../../static/images/gift.png" @click="gift"> </image>
			</view>
		</view>
		<customer-service ref='customerService'></customer-service>
	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import {
		loginAjax,
		userbasic,
		vpncompanyGetbyphonesvc,
		isnewmsg,
		vpnPointSigntsvc
	} from '../../api/index.js'
	import uniGrid from "@/components/uni-grid/uni-grid.vue"
	import uniGridItem from "@/components/uni-grid-item/uni-grid-item.vue"
	import customerService from '@/components/customerService/customerService.vue'
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	// import moment from 'moment'

	export default {
		data() {
			return {
				show: false,
				isLogin: 0,
				userInfo: {},
				headImg: '',
				wxInfo: {},
				phoneNum: '',
				topHeight: '',
				userState: '',
				haveRead: 0,
				point: {}
			};
		},
		components: {
			uniGrid,
			uniGridItem,
			customerService,
			uniPopup,
			uniIcons
		},
		onLoad() {
			// this.loginIndex();
			// this.getWxInfo();
		},
		onShow() {
			this.topHeight = getApp().globalData.topHeight;
			this.userState = getApp().globalData.userState;
			this.isLogin = getApp().globalData.isLogin;
			this.loginIndex();
			// if (getApp().globalData.userId) {
			// 	this.getUser(getApp().globalData.userId);
			// } else {
			// 	this.loginIndex();
			// 	// this.getWxInfo();
			// }
		},
		methods: {
			close() {
				this.show = false;
				this.$refs.popup.close()
			},
			sign() {
				let data = {
					vpnPointAdd: {
						// lastSign: moment().format("YYYY-MM-DD"),
						lastSign:"",
						value: 1,
						userId: this.userInfo.userId
					}
				}
				let _this = this
				vpnPointSigntsvc(data).then(res => {
					if (res.code == 1) {
						_this.open();
						_this.point ={contDays:res.data.contDays,value:res.data.value}
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			open() {
				this.show = true;
				this.$refs.popup.open()
			},
			cacelCall() {
				this.$refs.popup.close()
			},

			contactUser() {
				this.$refs.customerService.open()
			},


			goToPage(e) {
				let urlp = e.currentTarget.dataset.urlp;
				uni.navigateTo({
					url: urlp
				})
			},
			goToPageSwich(e) {
				let urlp = e.currentTarget.dataset.urlp;
				uni.switchTab({
					url: urlp
				})
			},
			// 检查是否有新消息
			isHaveNew(stats) {
				let that = this;
				let data = {
					identity: stats
				}
				isnewmsg(data).then(res => {
					if (res.code == 1) {
						that.haveRead = res.data.state
						if (res.data.state > 0) {
							uni.setTabBarBadge({
								index: 3,
								text: res.data.state.toString()
							})
						}

					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},

			loginIndex() {
				let that = this;
				let code = '';
				uni.login({
					provider: 'weixin',
					success: function(loginRes) {
						code = loginRes.code;
						uni.getUserInfo({
							provider: 'weixin',
							success: function(res) {
								let wxInfo = res.userInfo;
								that.wxInfo = wxInfo;
								getApp().globalData.wxInfo = wxInfo;
							}
						})
						let data = {
							code: code
						};
						loginAjax(data).then(res => {
							if (res.code == 1) {
								that.isLogin = 1;
								getApp().globalData.isLogin = 1;
								getApp().globalData.userId = res.data.userId;
								uni.setStorageSync('token', res.data.token);
								that.getUser(res.data.userId)
							} else if (res.code == 403) {
								that.isLogin = 0;
								uni.showToast({
									title: res.msg,
									icon: 'none',
									duration: 1500
								})
							} else {
								uni.showToast({
									title: res.msg,
									icon: 'none',
									duration: 1500
								})
							}
						})
					}
				})
			},
			// 获取用户信息
			getUser(id) {
				let that = this;
				let data = {
					id: id
				}
				userbasic(data).then(res => {
					if (res.code == 1) {
						console.log(res);
						that.userInfo = res.data;
						getApp().globalData.userInfo = res.data;
						if (getApp().globalData.userState != 3) {
							let ustate = 1;
							if (res.data.isdistribution == 0) {
								ustate = 1
							} else {
								ustate = 2
							}
							getApp().globalData.userState = ustate;
							that.userState = ustate;
						}
						that.isHaveNew(getApp().globalData.userState);
						that.phoneNum = res.data.phoneNum;
						that.getCompeny();
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			// 获取公司信息
			getCompeny() {
				let that = this;
				let data = {
					phone: that.phoneNum
				}
				vpncompanyGetbyphonesvc(data).then(res => {
					if (res.code == 1) {
						getApp().globalData.compenyInfo = res.data.vpncompanyGetResp;
						if (res.data.vpncompanyGetResp) {
							getApp().globalData.compenyState = res.data.vpncompanyGetResp.state;
						}
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			// 跳转兑换积分页面
				gift() {
					uni.navigateTo({
						url: '../../pageB/points/points'
					})
				},
		 
			// 获取微信的信息
			getWxInfo() {
				uni.navigateTo({
					url: '../../pagesA/bindPhone/bindPhone'
				})
			}
		}
	}
</script>

<style lang="less" scoped>
	// 弹出框
	.popBox {
		position: relative;
		width: 624rpx;
		height: 445rpx;
		background: #FFFFFF;
		border-radius: 20rpx;

		.cha {
			font-size: 40rpx;
			position: absolute;
			width: 48rpx;
			height: 48px;
			right: 20rpx;
			margin-top: 20rpx;


		}

		.imagepop {
			padding-top: 40rpx;
			width: 182rpx;
			height: 182rpx;
			margin: 0 auto;

			.kefuIcon {
				width: 182rpx;
				height: 182rpx;
			}
		}


		.popContent {
			padding: 70rpx 0rpx 130rpx;
			width: 364rpx;
			height: 99rpx;
			margin: 0 auto;

			.centerBlack {
				font-size: 44rpx;
				font-weight: bold;
				color: #222222;
				text-align: center;
				margin-bottom: 20rpx;
				// line-height: 70rpx;
			}

			.huiText {
				margin: 0 auto;
				font-size: 28rpx;
				font-weight: 500;
				color: #999999;
				// line-height: 36rpx;
				margin-bottom: 20rpx;
			}
		}

	}

	// popup结束



	.checkin {
		width: 28rpx;
		height: 28rpx;
		margin-right: 10rpx;
		display: inline-block;
	}

	.fixe-area {

		margin-top: 30rpx;
		width: 100%;
		height: 150rpx;

	}

	.myBanner {
		position: absolute;
		width: 100%;
		top: 0;
		left: 0;
		z-index: -1;
	}

	.topBox {
		margin: 200rpx 25rpx 0;
		background: #FFFFFF;
		height: 366rpx;
		box-shadow: 0px 0px 20rpx 0px rgba(0, 0, 0, 0.16);
		border-radius: 10rpx;
	}

	.magTop {
		display: flex;
		margin-top: 30rpx;
	}

	.isLog {
		min-height: 90rpx;
		margin-bottom: 40rpx;
		margin-top: 10rpx;
	}

	.headBox {
		border: 6rpx solid #FFFFFF;
		width: 120rpx;
		height: 120rpx;
		margin: 0 auto;
		border-radius: 120rpx;
		overflow: hidden;
	}

	.headImg {
		width: 100%;
		height: 100%;
	}

	.userName {
		font-size: 32rpx;
		color: #2A2A2A;
		font-weight: 500;
		margin: 15rpx 0;
	}

	.editIcon {
		width: 22rpx;
		height: 22rpx;
		margin-left: 10rpx;
	}

	.phone {
		color: #BCBCBC;
		font-size: 26rpx;
		text-align: center;

	}

	.tapList {
		margin-top: 20rpx;
		display: flex;
		justify-content: space-around;
	}

	.oneTap {
		text-align: center;
		font-size: 28rpx;
		color: #303030;
		font-weight: 400;

		view {
			margin-top: 15rpx;
		}

		image {
			width: 60rpx;
			height: 60rpx;
		}
	}

	.infoBtn {
		font-size: 30rpx;
		line-height: normal;
		background: #FFFFFF;
		padding: 14rpx;
	}

	.infoBtn2 {
		padding: 8rpx;
		color: #FFFFFF;
		font-size: 28rpx;
		line-height: normal;
		width: 139rpx;
		height: 54rpx;
		margin-top: 30.5rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		border-radius: 27px;

	}

	button::after {
		border: none;
	}

	.funcList {
		padding: 30rpx;
		font-size: 30rpx;
		color: #1B1B1B;
		font-weight: bold;
	}

	.moreFun {
		margin-top: 40rpx;
	}

	.gridOne {
		padding: 20rpx 0;
		text-align: center;
		font-size: 26rpx;
		color: #2C2C2C;
		font-weight: 400;

		image {
			width: 44rpx;
			height: 42rpx;
		}

		view {
			margin-top: 10rpx;
		}
	}






	.changeUserType {
		position: absolute;
		line-height: 32px;
		color: #FFFFFF;
		font-size: 28rpx;

		.iconqiehuan {
			margin-left: 10rpx;
			font-size: 25rpx;
		}
	}

	.redPoint {
		display: inline-block;
		position: absolute;
		background: #ff1e0a;
		width: 15rpx;
		height: 15rpx;
		border-radius: 15rpx;
	}
</style>
