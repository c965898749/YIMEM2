<template>
	<view class="all">
		<!-- 求职者看到的状态 -->
		<view class="u-content" v-if="userState==1">
			<u-parse :html="content"></u-parse>
		</view>
		<view class="content" v-if="userState!==1">
			<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">
				<view class="noHeadCont">
					<view class="serchBox">
						<u-search class="usearch" :animation="true" :show-action="false" :clearabled="true"
							placeholder="搜索姓名/手机号" v-model="keyword" @change="changeVal" @search="searchFun"></u-search>
						<view class="shaixuan" @click="openSelect">
							<image src="../../static/images/shaixuan.png" mode=""></image>
							筛选
						</view>
					</view>
				</view>
				<!-- 分销者和求职者 -->
				<view class="" v-if="userState!=1">
					<!-- 	<view class="hot-tilte">
							<text v-if="userState==2">热门任务推荐</text>
							<text v-else>热门岗位推荐</text>
						</view> -->
					<!-- <view class="message-tag-line"> -->
					<!-- 
							<text v-if="userState==2">
								推荐求职者入职，即可获得高额奖励
							</text>
							<text v-else>
								更多热门职位推荐，随时随地找工作						
							</text> -->
					<!-- <view class="end-tag" @click='openSelect'>
								筛选
								<view class="subscript"></view>
							</view> -->
					<!-- </view> -->
					<!-- 	<view class="row addPad">
						<view class="col_4">
							<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
						</view>
						<view class="col_5"></view> -->
					<!-- 	<view class="col_3 toEnd">
							<uni-tag text="筛选" type="default" @click='openSelect'></uni-tag>
						</view> -->
					<!-- </view> -->

					<view class="scrollList">
						<!-- 分销 -->
						<view class="allList" v-if="userState==2">
							<view class="scrollItem" @click="goToPage(item)" v-for="(item,index) in jobList" :key="index">
								<view class="itemTop">
									<view class="oneTop">
										<view class="left-row">
											<text class="workName">{{item.name}}</text>
										</view>
										<view class="left-row">
											<view class="job-tag all-day" >{{item.state | filterState}}</view>
										</view>
									</view>
								</view>
								<view class="company-info">
									<!-- <image class="company-pic" :src="item.logoUrl"></image> -->
									<view>
										<view class="companyName">应聘职位：{{item.jobName}}</view>
										<view class="blue-text">报名时间：{{item.createTime}}</view>
										<view class="gray-text">预估收益：
											<text class="redTexta">￥{{item.money}}</text>
										</view>
									</view>
								</view>
							</view>
						</view>
						<!-- 企业 -->
						<view class="allList" v-if="userState==3">
							<view class="oneItem" v-for="(item,index) in compJobList" :key="index" @click="goPage(item)">
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
												<text class="pTip">{{item.userSex==0?'女':'男'}} | {{item.user.age || '0' }}岁 | {{item.education || '未知'}}｜{{item.user.workage || '0' }}年｜{{item.cityName || '未知'}}</text>
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
					</view>
				</view>
			</scroll-view>
			<uni-popup ref="popup" type="bottom">
				<view class="whiteBack">
					<view :class="['selectOne',selectIndex==index?'blueColor':'']" v-for="(item,index) in popList"
						:key="index" @click="choseOne(index,item.id)">
						{{item.name}}
					</view>
				</view>
			</uni-popup>
			<customer-service ref='customerService'></customer-service>
		</view>
	</view>
</template>

<script>
	import {
		vpnslideshowGetsvc,
		vpnnavigationGetsvc,
		apiVpnrecommendGetsvc,
		apiVpncompanydeliverGetsvc,
		vpnjobGetsvc,
		vpnjobUpdatesvc,
		updateIsread,
		vpnbeginnerGetById
	} from '@/api/index.js'

	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	import uniGrid from "@/components/uni-grid/uni-grid.vue"
	import uniGridItem from "@/components/uni-grid-item/uni-grid-item.vue"
	import tapBar from "@/components/tapBar/tapBar.vue"
	import uniTag from "@/components/uni-tag/uni-tag.vue"
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import customerService from '@/components/customerService/customerService.vue'
	import uTabs from "@/uview-ui/components/u-tabs/u-tabs.vue"
	import uDropdown from "@/uview-ui/components/u-dropdown/u-dropdown.vue"
	import uDropdownItem from "@/uview-ui/components/u-dropdown-item/u-dropdown-item.vue"

	// import uSearch  from "@/uview-ui/components/u-search/u-search.vue"

	export default {
		data() {
			return {
				imgUrl: getApp().globalData.imgUrl,
			
				// activeIndex: 0,
				activeComp: 0,
				popList: [], //筛选内容
				content: '',
				userState: 1, //判断用户是什么身份

				// 分销者筛选
				shareList: [{
					id: '1',
					name: '已投递'
				}, {
					id: '0',
					name: '不合适'
				}, {
					id: '7',
					name: '已入职'
				}, {
					id: '8',
					name: '已离职'
				}],
				compnayList: [{
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
				selectIndex: 0,
				userState: 1, //判断用户是什么身份
				selectChose: '', //筛选id
				isAllDay: 1,
				pageSize: 10,
				pageIndex: 1,
				canLoad: true,
				jobList: [], //应聘者列表
				// 公司
				compenyInfo: '',
				compJobList: [], //企业招聘列表
				searchText: ''
			}
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
		components: {
			uniPopup,
			navBarTop,
			uniGrid,
			uniGridItem,
			tapBar,
			uniTag,
			customerService,
			uTabs,
			uDropdown,
			uDropdownItem
			// uSearch
		},
		
		onShow() {
			let statuse = getApp().globalData.userState;
			this.userState = statuse;

			let _this = this;
			vpnbeginnerGetById({
				id: 0
			}).then(res => {
				_this.content = res.data.vpnbeginnerGetResp.content
			});


			this.initSeach();
			this.compenyInfo = getApp().globalData.compenyInfo;
			// this.getBanner();
			if (statuse == 1) {
				this.getJobList();
			} else if (statuse == 2) {
				this.popList = this.shareList;
				this.getJobList();
			} else if (statuse == 3) {
				this.popList = this.compnayList;
				this.getCompJobList();
			}
		},
		watch: {
			"userState": {
				immediate: true, // 首次加载的时候执行函数
				handler: function(newVal) {
					if (newVal === 1) {
						vpnbeginnerGetById({
							id: 0
						}).then(res => {
							console.log(res);
						});
					}
				}
			}
		},
		methods: {
			// 筛选的打开和关闭
			openSelect() {
				this.$refs.popup.open();
			},

			cleanPoint(item, callback) {
				let that = this;
				let data = {
					id: item.jobId
				}
				updateIsread(data).then(res => {
					if (res.code == 1) {

						if (typeof callback == "function") {
							that.pageIndex = 1;
							callback();
						}
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},
			// 搜索键
			searchFun() {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectChose = '';
				if (this.userState == 1 || this.userState == 2) {
					this.getJobList();
				} else if (this.userState == 3) {
					this.getCompJobList()
				}
			},
			// 搜索框变值
			changeVal(e) {
				this.searchText = this.keyword
			},

			// 加载更多
			botMore() {
				if (this.userState != 3) {
					if (this.canLoad != false) {
						this.getJobList();
					}
				} else {
					if (this.canLoad != false) {
						this.getCompJobList();
					}
				}
			},

			choseOne(indexp, id) {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectChose = id;
				if(this.userState==2){
					this.getJobList();
				}else{
					this.getCompJobList();
				}
				this.$refs.popup.close();
			},
			// 跳转到
			goPage(item){
				let devId = item.devId;
				uni.navigateTo({
					url: '../../pageB/talentDevelopment/talentDevelopment?devId=' + devId
				})
			},
			// 跳转到
			goToPage(item) {
				let delId = item.delId;
				uni.navigateTo({
					url: '../../pagesA/suggestProgress/suggestProgress?delId=' + delId
				})
			},

			// 初始化数据
			initSeach() {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectChose = '';
				this.searchText = '';
			},
			//获取job列表
			getJobList() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpnrecommendGetResp: {
						userId: getApp().globalData.userId,
						state: that.selectChose,
						name: that.searchText
					}
				}
				apiVpnrecommendGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.jobList = res.data.vpnrecommendGetResp;
						} else {
							that.jobList = [...that.jobList, ...res.data.vpnrecommendGetResp]
						}
						if (res.data.vpnrecommendGetResp.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			},
			//获取企业job列表
			getCompJobList() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpndeliverGet: {
						companyId: that.compenyInfo.companyId,
						state: that.selectChose,
						key: that.searchText
					}
				}
				apiVpncompanydeliverGetsvc(data).then(res => {
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
			}


		}
	}
</script>

<style lang="less">
	.u-content {
		// margin-top: 100rpx;
		// color: $u-content-color;
		font-size: 20rpx;
		line-height: 1.5;
		padding: 24rpx;
	}

	.uicon {
		margin-left: 10rpx;
	}

	.usearch {
		margin-left: 30rpx;
		display: inline;
		width: 574rpx;
		/* padding: 10rpx; */
		margin-bottom: 19rpx;

	}

	.shaixuan {
		margin: 15rpx 10rpx;

		image {
			width: 29rpx;
			height: 33rpx;
		}

	}

	.custom-style {
		display: inline-block;
		padding: 20rpx;
	}

	.city {
		float: left;
		width: 200rpx;
		display: inline-block;
	}

	.left-row {
		display: flex;
		align-items: center;
	}

	.serchBox {
		display: flex;
		padding: 0 15rpx;

	}

	.swiperWrap {
		padding: 0 30rpx;
	}

	.imageBanner {
		width: 100%;
		height: 100%;
	}

	.redTexta {
		font-size: 30rpx;
		color: #FE2B67;
		font-weight: 500;

	}

	.gridBox {
		padding: 30rpx 30rpx;
	}

	.gridOne {
		text-align: center;
	}

	.gridOne view {
		font-size: 24rpx;
		margin-bottom: 20rpx;
	}

	.gridOne image {
		width: 110rpx;
		height: 100rpx;
		border-radius: 10rpx;
	}


	.btn-icon {
		height: 140rpx;
		width: 334rpx;
	}


	.notice-line>view {
		width: 702rpx;
		height: 68rpx;
		display: flex;
		align-items: center;
		background: rgba(46, 115, 255, 0.08);
	}

	.notice-line>view>image {
		width: 36rpx;
		height: 33rpx;
		margin: 0 21rpx;
	}

	.lineRow {
		width: 100%;
		height: 16rpx;
		background: #F6F6F6;
		border-radius: 1px;
	}

	.hot-tilte {
		margin: 21rpx 0 24rpx 27rpx;
		font-size: 34rpx;
		font-family: PingFang SC;
		font-weight: bold;
		color: #2C2C2C;
	}

	.message-tag-line {
		display: flex;
		justify-content: space-between;
		font-size: 26rpx;
		font-family: PingFang SC;
		font-weight: 500;
		color: #919191;
		margin: 0 0 33rpx 27rpx;
		align-items: center;
	}

	.end-tag {
		width: 128rpx;
		height: 53rpx;
		margin-right: 29rpx;
		background: #F6F6F6;
		/* border-radius: 10rpx; */
		text-align: center;
		padding-top: 10rpx;
		font-size: 24rpx;
		font-family: PingFang SC;
		font-weight: 500;
		color: #2C2C2C;
		position: relative;
	}

	.subscript {
		position: absolute;
		right: 7rpx;
		bottom: 7rpx;
		width: 0;
		height: 0;
		border-bottom: 13rpx solid #1B1B1B;
		border-left: 13rpx solid transparent;


	}

	.addPad {
		padding: 30rpx 30rpx 0;
	}

	.toEnd {
		text-align: end;
	}

	.toEnd image {
		width: 128rpx;
		height: 53rpx;
	}

	.noHeadCont {
		min-height: 20rpx;
	}

	.scrollBox {
		flex: 1;
		min-height: 20rpx;
	}

	.scrollList {
		background-color: #F6F6F6;
		padding: 25rpx;
	}

	.scrollItem {
		margin: 0 auto;
		border-radius: 10rpx;
		background: #FFFFFF;
		margin-bottom: 20rpx;
		box-shadow: 0 0 10rpx 0rpx #d8d8d8;
		width: 690rpx;
		height: 293rpx;
	}

	.tagList {
		display: flex;
		flex-wrap: wrap;
	}

	.tagList text {
		color: #999999 !important;
	}

	.uni-tag {
		margin-right: 10rpx !important;
		margin-bottom: 10rpx !important;
	}

	.oneTop {
		display: flex;
		justify-content: space-between;

	}

	.oneMid {
		padding: 30rpx 0rpx 0rpx;
		color: #919191;
		font-size: 26rpx;
	}

	.item-near {
		padding: 10rpx 0rpx 0rpx;
	}

	.itemTop {
		padding: 30rpx;
	}

	.workName {
		font-size: 32rpx;
		font-weight: bold;
		color: #2C2C2C;
	}

	.job-tag {
		padding: 8rpx 12rpx;
		height: 32rpx;
		border-radius:4rpx;
		margin-left: 24rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 20rpx;
		font-family: PingFang SC;
		font-weight: 500;
	}

	.all-day {
		background: #FFEFE3;
		color: #FF8528;
	}

	.part-time {
		background: #EDFAF7;
		color: #43C4A9;
	}

	.workMoney {
		color: #2E73FF;
		font-size: 32rpx;
	}

	.money-icon {
		width: 24rpx;
		height: 30rpx;
		margin-right: 10rpx;
	}

	.itemBot {
		padding: 30rpx;
	}

	.botBox {
		display: flex;
		justify-content: space-between;
	}

	.blackText {
		color: #2C2C2C;
		font-size: 28rpx;
		font-weight: bold;
	}

	.redText {
		font-size: 30rpx;
		color: #FE2B67;
		font-weight: 500;

	}

	.company-info {
		padding: 0 0 20rpx 31rpx;
		/* display: flex; */
	}

	.company-pic {
		width: 97rpx;
		height: 80rpx;
		border-radius: 10px;
		box-shadow: 0 0 2rpx 0rpx #d8d8d8;
		margin-right: 11rpx;
	}

	.company-pic>image {}

	.companyName {
		font-size: 24rpx;
		/* font-weight: bold; */
		color: #2C2C2C;
	}

	.blue-text {
		font-size: 24rpx;
		font-weight: 400;
		/* color: #2E73FF; */
		margin-top: 16rpx;
	}

	.gray-text {
		font-size: 24rpx;
		font-weight: 400;
		/* color: #919191; */
		margin-top: 16rpx;
	}

	.dashed {
		width: 100%;
		border-bottom: 2rpx dashed #F3F3F3;
	}

	.address-text {
		font-size: 24rpx;
		font-weight: 500;
		color: #2C2C2C;
		margin-left: 15rpx;
	}

	.textList {
		margin-top: 8rpx;
		font-size: 24rpx;
		font-weight: 500;
		color: #2C2C2C;
	}

	.moneyIcon {
		width: 24rpx;
		height: 30rpx;
		vertical-align: -5rpx;
		margin-right: 10rpx;
	}

	.whiteBack {
		background: #ffffff;
		padding: 0 30rpx;
		max-height: 500rpx;
		overflow-y: auto;
	}

	.selectOne {
		padding: 30rpx 0;
		border-bottom: 1rpx solid #F3F3F3;
		font-size: 28rpx;
		color: #1B1B1B;
	}

	.uni-searchbar__box {
		border: none !important;
	}

	.blueColor {
		color: #2E73FF;
	}

	.addIcon {
		color: #2E73FF;
		font-size: 28rpx;
		font-weight: 500;
		color: #2E73FF;
	}

	.orangeText {
		margin-left: 15rpx;
		padding: 5rpx 10rpx;
		background: #FFEFE3;
		border-radius: 0rpx 0rpx 4rpx 4rpx;
		font-size: 20rpx;
		font-weight: 500;
		color: #FF8528;
	}

	.greenText {
		margin-left: 15rpx;
		padding: 5rpx 10rpx;
		background: #EDFAF7;
		border-radius: 0rpx 0rpx 4rpx 4rpx;
		font-size: 20rpx;
		font-weight: 500;
		color: #43C4A9;
	}

	.itemRight {
		text-align: center;
		padding: 30rpx 0;
	}

	.itemLeft {
		padding-right: 0;
	}

	.jobNum {
		font-size: 42rpx;
		font-weight: 500;
		color: #2E73FF;
	}

	.jobText {
		font-size: 22rpx;
		font-weight: 400;
		color: #919191;
		margin-top: 15rpx;
	}

	.litPoint {
		position: relative;
	}

	.litPoint::after {
		position: absolute;
		top: 0;
		content: '';
		display: inline-block;
		width: 10rpx;
		height: 10rpx;
		border-radius: 10rpx;
		background: #FE2B67;
	}

	/* 企业底部 */
	.botComp {
		display: flex;
		align-items: center;
		line-height: 80rpx;
	}

	.oneBot {
		flex: 1;
		font-size: 24rpx;
		font-weight: 400;
		color: #2C2C2C;
		text-align: center;
	}

	.iconOne {
		margin-right: 10rpx;
		vertical-align: -2rpx;
	}

	.shuTxet {
		width: 1rpx;
		height: 36rpx;
		border: 1rpx solid #F2F2F2;
	}

	.noMore {
		padding: 40rpx 30rpx 0;
	}

	.noMoreText {
		font-size: 36rpx;
		font-weight: bold;
		color: #2C2C2C;
		text-align: center;
		margin-bottom: 30rpx;
	}

	.noMorelit {
		font-size: 28rpx;
		font-weight: 500;
		color: #C1C5D0;
		text-align: center;
		margin-bottom: 80rpx;
	}

	.creatBtn {
		width: 450rpx;
		height: 80rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
		border-radius: 80rpx;
		line-height: 80rpx;
		color: #FFFFFF;
		text-align: center;
		margin: 0 auto 100rpx;

	}

	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}

	.magBtn {
		margin-bottom: 10rpx;
	}

	.fixe-area {
		position: fixed;
		bottom: 117rpx;
		right: 47rpx;
		width: 100rpx;
		height: 100rpx;
	}
	
	// 公司的
	.allList {
	
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
	}
</style>
