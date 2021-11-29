<template>
	<view class="content">
		<view class="topSearch">
			<view class="topSearchLeft">
				<uni-search-bar placeholder="搜索姓名" :radius="100" @input="changeVal" @confirm="searchBox" cancelButton="none"></uni-search-bar>
			</view>
			<view class="topSearchRit" @click="openSelect">
				<view class="noTap" v-if="!selectShow">
					<image src="../../static/images/jhsx0.png" mode=""></image>筛选
				</view>
				<view class="hasTap" v-if="selectShow">
					<image src="../../static/images/jhsx1.png" mode=""></image>筛选
				</view>
			</view>
		</view>
		<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">
			<view class="allList">
				<view class="oneItem" v-for="(item,index) in compJobList" :key="index" @click="goPage(item.devId)">
					<view class="leftItem">
						<image class="headImg" :src="item.headImg?item.headImg:'../../static/images/headimgMy.png'" mode=""></image>
					</view>
					<view class="rightItem">
						<view class="pepole">
							<view class="pname">
		 						{{item.userName}}
							</view>
							<view class="">
								<text class="tagBox">{{item.state | filterState}}</text>
							</view>
						</view>
						<view class="pContent">
							<view class="">
								<view class="pText">
									<text class="pTip">{{item.userSex==0?'女':'男'}} | {{item.user.age || 0}}岁 | {{item.education || '未知'}}｜{{item.user.workage || 0}}年｜{{item.cityName || '未知'}}</text>
								</view>
								<view class="pText">
									<text>应聘职位：{{item.job.name}}</text>
								</view>
								<view class="">
									<!-- <text class="blueText">{{item.job.lowSalary}}-{{item.job.higSalary}}</text> -->
									<text class="workMoney"
										v-if="item.job.isAllDay==1">{{Math.round(item.job.lowSalary/100)/10}}-{{Math.round(item.job.higSalary/100)/10}}k</text>
									<text class="workMoney"
										v-if="item.job.isAllDay==0">{{item.job.partTimeSalary}}元/天</text>
								</view>
							</view>
							<view class="">
								<!-- <image class="phoneIcon" src="../../static/images/phonea.png" mode="" @click="contactUser"></image> -->
							</view>
						</view>
						<!-- <view class="pText betweenRow">
							<text class="blueText">{{item.user.expectedSalaryName || ''}}</text> <text class="litTip">{{item.updateTime||''}}{{item.state | filterState}}</text>
						</view> -->
					</view>
				</view>
			</view>
			<view class="noMoreList" v-if="canLoad==false">
				没有更多。。。
			</view>
		</scroll-view>
		<uni-popup ref="popup" type="bottom" @change="isClose">
			<view class="whiteBack">
				<view :class="['selectOne',selectIndex==item.id?'blueColor':'']" v-for="(item,index) in seleList" :key="index"
				 @click="choseOne(item.id)">
					{{item.name}}
				</view>
			</view>
		</uni-popup>
		
			<customer-service ref='customerService'></customer-service>
			 
	</view>
</template>

<script>
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
		import customerService from '@/components/customerService/customerService.vue'
	import {
		vpncompanydeliverGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				selectShow: false,
				seleList: [{
					id: '',
					name: '全部职位'
				}, {
					id: '0',
					name: '不合适'
				}, {
					id: '1',
					name: '已投递'
				}, {
					id: '2',
					name: '已邀约'
				}, {
					id: '3',
					name: '待面试'
				}, {
					id: '4',
					name: '推送企业'
				}, {
					id: '5',
					name: '企业面试'
				}, {
					id: '6',
					name: '已面试'
				}, {
					id: '7',
					name: '已入职'
				}, {
					id: '8',
					name: '已离职'
				}, {
					id: '9',
					name: '离职审核中'
				}],
				selectIndex: null,
				jobId: '',
				compenyInfo: '',
				userId: '',
				compJobList: [],
				pageIndex: 1,
				pageSize: 10,
				canLoad: true,
				searchText: ''
			}
		},
		components: {
			uniSearchBar,
			uniPopup,
			customerService,
		},
		onLoad(options) {
			this.jobId = options.jobId;
			this.compenyInfo = getApp().globalData.compenyInfo;
			this.userId = getApp().globalData.userId;
			this.getAjax();
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
					9:'离职审核中',
					10:'是已收佣',
				};
				return states[val]
			}
		},
		methods: {
			// 拨打电话
			// phoneCall(phone) {
			// 	uni.makePhoneCall({
			// 		phoneNumber: phone //仅为示例
			// 	});
			// },
			contactUser() {
				this.$refs.customerService.open()
			},
			// 加载更多
			botMore() {
				if (this.canLoad != false) {
					this.getAjax();
				}
			},
			// 跳转到人才进展
			goPage(id){
				uni.navigateTo({
					url:'../talentDevelopment/talentDevelopment?devId='+id
				})
			},
			changeVal(e){
				this.searchText=e.value
			},
			// 获取应聘者列表
			getAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpndeliverGet: {
						companyId: that.compenyInfo.companyId,
						jobId: that.jobId,
						state: that.selectIndex,
						key: that.searchText
					}

				}
				vpncompanydeliverGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.compJobList = res.data.vpndeliverGetResp;
						} else {
							that.compJobList = [...that.compJobList, ...res.data.vpndeliverGetResp]
						}
						if (res.data.vpndeliverGetResp.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			},
			// 搜索
			searchBox(e) {
				let theText = e.value;
				this.searchText = theText;
				this.pageIndex = 1;
				this.pageSize = 10;
				this.getAjax();
			},
			openSelect() {
				this.selectShow = true;
				this.$refs.popup.open();
			},
			isClose(e) {
				if (e.show == false) {
					this.selectShow = false;
				}
			},
			choseOne(indexp) {
				this.selectIndex = indexp;
				this.pageIndex = 1;
				this.pageSize = 10;
				this.getAjax();
				this.$refs.popup.close();
			},
		}
	}
</script>

<style lang="less">
	.topSearch {
		padding: 0 20rpx;
		display: flex;
		align-items: center;

		.topSearchLeft {
			flex: 1;
		}

		.topSearchRit {
			width: 120rpx;
			text-align: center;

			image {
				width: 29rpx;
				height: 33rpx;
				margin-right: 10rpx;
				vertical-align: -7rpx;
			}

			.noTap {
				color: #1B1B1B;
				font-size: 26rpx;
			}

			.hasTap {
				color: #2E73FF;
				font-size: 26rpx;
			}
		}
	}

	.whiteBack {
		background: #ffffff;
		padding: 0 30rpx;
	}

	.selectOne {
		padding: 30rpx 0;
		border-bottom: 1rpx solid #F3F3F3;
		font-size: 28rpx;
		color: #1B1B1B;
	}

	.blueColor {
		color: #2E73FF;
	}

	.scrollBox {
		height: calc(100% - 104rpx);
		background: #F6F6F6;

		.allList {
			padding: 10rpx 20rpx;

			.oneItem {
				margin-top: 20rpx;
				min-height: 248rpx;
				background: #FFFFFF;
				border-radius: 10rpx;
				padding: 30rpx 20rpx 10rpx;
				position: relative;
				.leftItem {
					width: 120rpx;
					display: inline-block;
					vertical-align: top;
				}

				.rightItem {
					width: 540rpx;
					display: inline-block;
				}

				.pepole {
					display: flex;
					justify-content: space-between;

					.pname {
						font-size: 32rpx;
						color: #2C2C2C;
						font-weight: bold;
					}
				}

				.pText {
					padding: 7rpx 0;
					font-size: 26rpx;
					color: #2C2C2C;

					.pTip {
						color: #919191;
					}
				}

				.betweenRow {
					display: flex;
					justify-content: space-between;
				}

				.blueText {
					font-size: 32rpx;
					font-weight: 500;
					color: #2E73FF;
				}

				.litTip {
					font-size: 22rpx;
					font-weight: 400;
					color: #ACACAC;
					line-height: 36rpx;
				}

				.pContent {
					display: flex;
					justify-content: space-between;
				}
			}
		}
	}

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

	.headImg {
		width: 101rpx;
		height: 101rpx;
		border-radius: 100rpx;
	}

	.phoneIcon {
		width: 50rpx;
		height: 50rpx;
		margin-top: 20rpx;
	}
	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}
</style>
