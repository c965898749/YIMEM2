<template>
	<view class="content">
		<navBarTop title="我的钱包" color="#FFFFFF" backgroundColor="rgba(0,0,0,0)" imgNavBg="../../static/images/jhzu7.png"></navBarTop>
		<view class="topBox">
			<view class="canGet">
				可提现收益(元）
			</view>
			<view class="">
				<text class="typeMoney">¥</text> <text class="moneyNum">{{showObj.money || 0}}</text>
			</view>
		</view>
		<view class="getMoney">
			<view class="moneyTitle">
				提现金额 <text class="blueAll" @click="getAlls">全部提现</text>
			</view>
			<view class="row inputBox">
				<view class="col_1 inType">
					¥
				</view>
				<view class="col_8">
					<input class="inputType" type="number" v-model="moneys" @change="changeMoney" />
				</view>
				<view class="col_3 tapBlue">
					<text @click="goPage('../getSendList/getSendList')">收支明细</text>
				</view>
			</view>
			<view class="btn-row">
				<button class="btn kong" open-type="share">分享收益</button>
				<button class="btn shi" @click="withdrawMoney">申请提现</button>
			</view>
			<view class="someAbout" @click="goPage('../cashRules/cashRules')">
				提现规则 <image class="iconRight" src="../../static/images/jsmany.png" mode=""></image>
			</view>
		</view>
	</view>
</template>

<script>
	import navBarTop from "../../components/nav-bar-top/nav-bar-top.vue"
	import {
		vpnWalletGetsvc,
		vpnApplicationForWithdrawalAddsvc
	} from "../../api/index.js"
	export default {
		data() {
			return {
				showObj: '',
				userId: '',
				moneys: ''
			};
		},
		onShow() {
			this.userId = getApp().globalData.userId;
			this.moneyAjax();
			this.moneys = '';
		},
		components: {
			navBarTop,
		},
		methods: {
			onShareAppMessage: function(ops) {
				var that = this
				return {
					title: '一梦人才',
					path: '/pages/index/index'
				}
			},
			// 跳转到收支明细
			goPage(e) {
				uni.navigateTo({
					url: e
				})
			},
			// 监听input
			changeMoney(e) {
				let moneyt = e.detail.value;
				if (this.showObj) {
					if (this.moneys > this.showObj.money) {
						this.moneys = this.showObj.money;
					}
				} else {
					this.moneys = 0
				}
			},
			//获取金钱
			moneyAjax() {
				let that = this;
				let data = {
					vpnWalletGet: {
						userId: that.userId
					}
				}
				vpnWalletGetsvc(data).then(res => {
					if (res.code == 1) {
						that.showObj = res.data.vpnWalletGetResp;
					}
				})
			},
			// 全部提现
			getAlls() {
				if (this.showObj) {
					this.moneys = this.showObj.money;
				}else{
					this.moneys=0
				}
			},
			// 提现
			withdrawMoney() {
				let that = this;
				if(this.moneys<=0){
					this.moneys=0;
					uni.showToast({
						duration: 1500,
						title: '输入的金额需要大于0',
						icon: 'none'
					})
					return false
				}
				let data = {
					vpnApplicationForWithdrawalAdd: {
						userId: that.userId,
						money: that.moneys
					}
				}
				uni.showModal({
					title: '提示',
					content: '确定去提现吗?',
					success: function(res) {
						if (res.confirm) {
							vpnApplicationForWithdrawalAddsvc(data).then(resp => {
								if (resp.code == 1) {
									uni.navigateTo({
										url: '../submitApplication/submitApplication'
									})
								} else {
									uni.showToast({
										duration: 1500,
										title: resp.msg,
										icon: 'none'
									})
								}
							})

						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});

			}
		}
	}
</script>

<style lang="less">
	.topBox {
		margin: 30rpx;
		color: #FFFFFF;
		font-size: 30rpx;

		.canGet {
			margin-bottom: 20rpx;
		}

		.typeMoney {
			font-size: 36rpx;
			margin-right: 10rpx;
		}

		.moneyNum {
			font-size: 72rpx;
		}
	}

	.getMoney {
		margin: 10rpx 30rpx 0;
		height: 530rpx;
		background: #FFFFFF;
		border-radius: 10rpx;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
	}

	.moneyTitle {
		padding: 30rpx 30rpx;
		font-size: 28rpx;
		color: #1B1B1B;
	}

	.inputBox {
		padding: 20rpx 30rpx 30rpx;
		border-bottom: 1rpx solid #F6F6F6;

		.inType {
			font-size: 72rpx;
			color: #1B1B1B;
		}

		.inputType {
			font-size: 72rpx;
			height: 90rpx;
		}

		.tapBlue {
			color: #2E73FF;
			font-size: 28rpx;
			text-align: right;
			padding-top: 30rpx;
		}
	}

	.btn-row {
		padding: 40rpx 30rpx;
		display: flex;
		justify-content: space-between;
	}

	.btn {
		border: 1rpx solid #2E73FF;
		font-size: 30rpx;
		border-radius: 100rpx;
		width: 45%;
	}

	.kong {
		color: #2E73FF;
		background-color: #FFFFFF;
	}

	.shi {
		background: #2E73FF;
		color: #FFFFFF;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
	}

	.someAbout {
		text-align: center;
		font-size: 28rpx;
		color: #1B1B1B;
	}

	.iconRight {
		width: 6rpx;
		height: 16rpx;
		margin-left: 15rpx;
	}

	.blueAll {
		color: #2E73FF;
		font-size: 28rpx;
		float: right;
	}
</style>
