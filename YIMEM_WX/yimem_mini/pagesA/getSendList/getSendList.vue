<template>
	<view class="content">
		<view class="tabBox">
			<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
		</view>
		<scroll-view scroll-y="true" @scrolltolower="botMore" class="scrollBox">
			<view class="tapOne" v-if="activeIndex==0">
				<view class="theOne" v-for="(item,index) in jobList" :key='index'>
					<uni-collapse accordion="true">
						<uni-collapse-item :title="item.time" :moreTip="'总收益 ￥'+item.totalmoney">
							<view class="listBox">
								<view class="listOne row" v-for="(itemp,indexp) in item.incomeDetails" :key="indexp">
									<view class="col_9">
										<view class="nameText">
											{{itemp.rUserName}}-{{itemp.rPhone}}
										</view>
										<view class="timeItem">
											到账时间：{{itemp.createdTime}}
										</view>
									</view>
									<view class="col_3">
										<text class="redMonye">￥</text> <text class="redNum">{{itemp.brokerage}}</text>
									</view>
								</view>
							</view>
						</uni-collapse-item>
					</uni-collapse>
				</view>
			</view>
			<view class="tapTwo" v-if="activeIndex==1">
				<view class="theOne" v-for="(item,index) in moneyList" :key='index'>
					<uni-collapse accordion="true">
						<uni-collapse-item :title="item.time" :moreTip="'共提现'+item.totalmoney">
							<view class="listBox">
								<view class="listOne row" v-for="(itemp,indexp) in item.applicationForWithdrawals" :key=indexp>
									<view class="col_2">
										<image class="bitIcon" src="../../static/images/jbjhseee.png" mode=""></image>
									</view>
									<view class="col_7">
										<view class="nameText">
											钱包余额提现
										</view>
										<view class="timeItem">
											{{itemp.state | stateFilter}}：{{itemp.updateTime || ""}}
										</view>
										<view class="timeItem" v-if="itemp.reason">
											驳回原因：{{itemp.reason || ""}}
										</view>
									</view>
									<view class="col_3">
										<text class="redMonye">￥</text> <text class="redNum">{{itemp.money || ""}}</text>
									</view>
								</view>
							</view>
						</uni-collapse-item>
					</uni-collapse>
				</view>
			</view>
		</scroll-view>

	</view>
</template>

<script>
	import tapBar from "@/components/tapBar/tapBar.vue"
	import uniCollapse from '@/components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '@/components/uni-collapse-item/uni-collapse-item.vue'
	import {
		vpnincomeDetailGetsvc,
		vpnApplicationForWithdrawalGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				imgUrl: getApp().globalData.imgUrl,
				options: ['收入明细', '提现记录'],
				activeIndex: 0,
				pageSize: 10,
				pageIndex: 1,
				canLoad: true,
				jobList: [],
				moneyList: [],
				userId: ''
			};
		},
		components: {
			tapBar,
			uniCollapse,
			uniCollapseItem
		},
		onLoad() {
			this.userId = getApp().globalData.userId;
			this.getListAjax();
		},
		filters:{
			stateFilter(val){
				let liststate={
					0:'拒绝时间',
					1:'等待审核',
					2:'提现时间'
				}
				return liststate[val]
			}
		},
		methods: {
			// toDo提现金额没有渲染



			//切换tap
			onClickItem(e) {
				this.activeIndex = e;
				this.jobList = [],
					this.moneyList = [],
					this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				if (e == 0) {
					this.getListAjax();
				} else {
					this.getMoneyListAjax();
				}
			},
			// 加载更多
			botMore() {
				if (this.canLoad != false) {
					if (this.activeIndex == 0) {
						this.getListAjax();
					} else {
						this.getMoneyListAjax();
					}
				}
			},
			// 获取收入列表；
			getListAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpnincomeDetailGet: {
						userId: that.userId
					}
				}
				vpnincomeDetailGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.jobList = res.data.vpnincomeDetailGetResp;
						} else {
							that.jobList = [...that.jobList, ...res.data.vpnincomeDetailGetResp]
						}
						if (res.data.vpnincomeDetailGetResp.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			},
			// 获取提现列表
			getMoneyListAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpnApplicationForWithdrawalGet: {
						userId: that.userId
					}
				}
				vpnApplicationForWithdrawalGetsvc(data).then(res => {
					if (res.code == 1) {
						console.log(res.data)
						if (that.pageIndex == 1) {
							that.moneyList = res.data.vpnApplicationForWithdrawalGet;
						} else {
							that.moneyList = [...that.moneyList, ...res.data.vpnApplicationForWithdrawalGet]
						}
						if (res.data.vpnApplicationForWithdrawalGet == undefined || res.data.vpnApplicationForWithdrawalGet.length <
							that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			},
		}
	}
</script>

<style lang="less">
	.tabBox {
		height: 70rpx;
	}

	.scrollBox {
		height: calc(100% - 80rpx);
	}

	.listBox {
		padding: 0 30rpx;

		.listOne {
			padding: 15rpx 0;
			.col_3 {
				text-align: right;
			}

			.col_2 {
				text-align: left;
			}

			.timeItem {
				font-size: 24rpx;
				font-weight: 400;
				color: #BABABA;
				line-height: 35rpx;
			}

			.bitIcon {
				height: 60rpx;
				width: 60rpx;
				vertical-align: -20rpx;

			}

			.nameText {
				font-size: 28rpx;
				color: #2C2C2C;
				line-height: 35rpx;
			}

			.redMonye {
				color: #FE2B67;
				font-size: 24rpx;
			}

			.redNum {
				color: #FE2B67;
				font-size: 36rpx;
			}
		}
	}
</style>
