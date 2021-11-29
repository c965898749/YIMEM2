<template>
	<view class="content">
		<navBarTop title="南通市" :isBack="false">
		</navBarTop>
		<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">
			<view class="noHeadCont">
				<view class="serchBox">
					<uni-search-bar placeholder="搜索你想要的职位" @input="changeVal" @confirm="searchFun" clearButton="auto">
					</uni-search-bar>
				</view>
				<view>
					<view class="swiperWrap">
						<swiper class='swiper' indicator-dots autoplay circular indicator-active-color='#FFF'>
							<swiper-item v-for="(item,index) in imgList" v-if="index<4" :key="index">
								<image class="imageBanner" :src="item.url"></image>
							</swiper-item>
						</swiper>
					</view>
				</view>
			</view>
			<!-- 分销者和求职者 -->
			<!-- tab头部 -->
			<view class="gridBox" v-if="userState!=3">
				<view class="row">
					<view class="col_3">
						<block v-for="(item,index) in tapList" :key='index'>
							<view class="gridOne" v-if="index<2" @click="tapNav(item.naId)">
								<image :src="item.iconUrl"></image>
								<view>{{item.name}}</view>
							</view>
							<view class="gridOne" v-if="index==2" @click="goTogonglv(item)">
								<image :src="item.iconUrl"></image>
								<view>{{item.name}}</view>
							</view>
							<view class="gridOne" v-if="index==3" @click="goTopaihang(item)">
								<image :src="item.iconUrl"></image>
								<view>{{item.name}}</view>
							</view>
						</block>
					</view>
				</view>
			</view>
			<!-- 分销者 -->
			<view class="" v-if="userState==2">
				<view class="btn-row">
					<image class="btn-icon" src="../../static/images/allrec.png" @click="allrec"></image>
					<image class="btn-icon" src="../../static/images/keypoint.png" @click="goTogonglv"></image>
				</view>
				<swiper class="swiper litSwiper" :indicator-dots="false" :autoplay="true" :interval="2000" :vertical='true'
					:duration="200">
					<swiper-item v-for="(item,index) in listOfGg" :key='index'>
						<view class="notice-line">
							<view>
								<image src="../../static/images/notice.png" mode=""></image>
								分销者{{item.userName}}已成功提现{{item.money}}元
							</view>
						</view>
					</swiper-item>
				</swiper>

				<view class="lineRow"></view>
			</view>

			<!-- 分销者和求职者 -->
			<view class="" v-if="userState!=3">
				<view class="hot-tilte">
					<text v-if="userState==2">热门任务推荐</text>
					<text v-else>热门岗位推荐</text>
				</view>
				<view class="message-tag-line">

					<text v-if="userState==2">
						推荐求职者入职，即可获得高额奖励
					</text>
					<text v-else>
						更多热门职位推荐，随时随地找工作
					</text>
					<view class="end-tag" @click='openSelect'>
						筛选
						<view class="subscript"></view>
					</view>
				</view>
				<!-- <view class="row addPad">
					<view class="col_4">
						<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
					</view>
					<view class="col_5"></view>
					<view class="col_3 toEnd">
						<uni-tag text="筛选" type="default" @click='openSelect'></uni-tag>
					</view>
				</view> -->
				<view class="scrollList">
					<view class="scrollItem" @click="goToPage(item)" v-for="(item,index) in jobList" :key="index">
						<view class="itemTop">
							<view class="oneTop">
								<view class="left-row">
									<text class="workName">{{item.name}}</text>
									<view class="job-tag all-day" v-if="item.isAllDay==1">全职</view>
									<view class="job-tag part-time" v-else>兼职</view>
								</view>
								<!-- 求职-工作薪资 -->
								<view v-if="userState==1">
									<!-- 四舍五入保留一位小数 -->
									<text class="workMoney"
										v-if="item.isAllDay==1">{{Math.round(item.lowSalary/100)/10}}-{{Math.round(item.higSalary/100)/10}}k</text>
									<text class="workMoney" v-if="item.isAllDay==0">{{item.partTimeSalary}}元/天</text>
								</view>
								<!-- 分销-推荐佣金 -->
								<view v-if="userState==2 " class="left-row">
									<image src="../../static/images/money.png" class="money-icon"></image>
									<text class="blackText">佣金：</text>
									<text class="redText">{{item.brokerage}}元/人</text>
								</view>
							</view>
							<view class="oneMid item-near">招聘{{item.peopleNum || 0}}人 | {{item.jobExpName ||'暂无'}} |
								{{item.educationExpName || '暂无'}}
							</view>

						</view>
						<view class="company-info">
							<image class="company-pic" :src="item.logoUrl"></image>

							<view>
								<view class="companyName">{{item.companyName}}</view>
								<view class="blue-text" v-if="userState==2">人才入职满30天后可获得奖励金</view>
								<view class="gray-text" v-else>{{item.businessname || '未知'}} ｜ {{item.scale  || '未知'}}人</view>

							</view>
						</view>
						<view class="dashed"></view>
						<view class="itemBot oneTop">
							<view class="left-row">
								<text class="iconfont icondidian" style="color:#2E73FF"></text>
								<tetx class="address-text">{{item.cityName}}{{item.countryName}}</tetx>
							</view>
							<view class="textList">
								<text v-for="(one,indexp) in item.jobCustomLightspot" :key="indexp">
									{{one.name}}{{indexp<(item.jobCustomLightspot.length-1)?'｜':''}}
								</text>
								{{item.jobCustomLightspot.length>0 && item.jobLightspot.length>0?'｜':''}}
								<text v-for="(two,indext) in item.jobLightspot" :key="indext">
									{{two.name}}{{indext<(item.jobLightspot.length-1)?'｜':''}}
								</text>
							</view>
						</view>

					</view>
					<view class="noMoreList" v-if="canLoad==false">
						没有更多。。。
					</view>
				</view>
			</view>
			<!-- 企业端 -->
			<view class="" v-if="userState==3">
				<view class="row addPad">
					<view class=" jobList col_9 ">
						<!-- 职位列表 -->
						<tapBar :activeIndex="activeComp" :options="optionsComp" @tapChange="clickComp"></tapBar>
					</view>
					<view class="col_4"></view>
					<view>
						<text class="addIcon" @click="jobAdd">
							<text> + </text> 发布
						</text>
					</view>
				</view>
				<view class="scrollList">
					<view class="scrollItem" @click="goToPage(item)" v-for="(item,index) in compJobList" :key="index">
						<view class="row">
							<view class="itemTop col_8 itemLeft">
								<view class="oneTop">
									<view class="">
										<text class="workName">{{item.name}}</text>
										<text class="orangeText" v-if="item.isAllDay==1">全职</text>
										<text class="greenText" v-if="item.isAllDay==0">兼职</text>
									</view>
								</view>
								<view class="oneMid" v-if="item.isAllDay==1">{{item.cityName}}{{item.countryName}} |
									{{item.jobExpName || '暂无'}} | {{item.educationExpName || '暂无'}} |
									{{item.lowSalary}}-{{item.higSalary}}
								</view>
								<view class="oneMid" v-if="item.isAllDay==0">{{item.cityName}}{{item.countryName}} |
									收{{item.peopleNum}}人| {{item.timeliness}} | {{item.partTimeSalary}}元/天</view>
									<view  class="oneMid">
										<image src="../../static/images/money.png" class="money-icon"></image>
										<text class="blackText">佣金：</text>
										<text class="redText">{{item.brokerage}}元/人</text>
									</view>

							</view>
							
							<view class="col_4 itemRight"
								@click.stop="goPage('../../pageB/wantResume/wantResume',item)">
								<view class="jobNum">
									<text :class="item.isread==1?'litPoint':''">{{item.deliverMun}}</text>
								</view>
								<view class="jobText">
									应聘简历
								</view>
							</view>
						</view>
						<view class="lineRow"></view>
						<view class="botComp">
							<view class="oneBot" v-if="compState==1"
								@click.stop="goPage('../../pageB/jobadd/jobadd',item)">
								<uni-icons class="iconOne" type="compose" color="#2E73FF" size="15"></uni-icons> 编辑
							</view>
							<view class="oneBot" v-if="compState==2" @click.stop="delJob(item,index)">
								<uni-icons class="iconOne" type="trash" color="#2E73FF" size="15"></uni-icons> 删除
							</view>
							<view class="shuTxet"></view>
							<view class="oneBot" @click.stop="setRead(item)">
								<uni-icons class="iconOne" type="reload" color="#2E73FF" size="15"></uni-icons> 刷新
							</view>
							<view class="shuTxet"></view>
							<view class="oneBot" v-if="compState==1" @click.stop="closeItem(item,index)">
								<uni-icons class="iconOne" type="close" color="#2E73FF" size="15"></uni-icons> 关闭
							</view>
							<view class="oneBot" v-if="compState==2" @click.stop="showJob(item,index)">
								<uni-icons class="iconOne" type="paperplane" color="#2E73FF" size="15"></uni-icons> 发布
							</view>
						</view>
					</view>
					<view class="noMore" v-if="compJobList.length==0 &&compState!=2 ">
						<view class="noMoreText">
							您还没有发布职位
						</view>
						<view class="noMorelit">
							发布职位后可查看推荐人才哦～
						</view>
						<view class="">
							<view class="creatBtn" @click="jobAdd">
								立即发布
							</view>
						</view>
					</view>
					<view class="noMoreList" v-if="canLoad==false && compJobList.length>0">
						没有更多。。。
					</view>
				</view>
			</view>

		</scroll-view>
		<image class="fixe-area" src="../../static/images/cusservice.png" @tap="contactUser"> </image>
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
</template>

<script>
	import {
		vpnslideshowGetsvc,
		vpnnavigationGetsvc,
		vpnjobGetsvc,
		vpnjobUpdatesvc,
		updateIsread,
		vpnannouncementGetsvc
	} from '@/api/index.js'
	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	import uniGrid from "@/components/uni-grid/uni-grid.vue"
	import uniGridItem from "@/components/uni-grid-item/uni-grid-item.vue"
	import tapBar from "@/components/tapBar/tapBar.vue"
	import uniTag from "@/components/uni-tag/uni-tag.vue"
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import customerService from '@/components/customerService/customerService.vue'

	export default {
		data() {
			return {
				// 明哥
				imgUrl: getApp().globalData.imgUrl,
				// options: ['全职', '兼职'],
				optionsComp: ['正在发布', '已关闭'],
				tapList: [], //导航
				// activeIndex: 0,
				activeComp: 0,
				imgList: [], //轮播图
				popList: [], //筛选内容
				seleList: [{
					id: '',
					name: '全部职位'
				}, {
					id: 'QS_wage',
					name: '薪资最高'
				}, {
					id: 'QS_new',
					name: '最新发布'
				}, {
					id: 'QS_hot',
					name: '热门推荐'
				}],
				shareList: [{
					id: '',
					name: '全部职位'
				}, {
					id: 'QS_brokerage',
					name: '佣金最高'
				}, {
					id: 'QS_wage',
					name: '薪资最高'
				}, {
					id: 'QS_new',
					name: '最新发布'
				}],
				selectIndex: 0,
				userState: 1, //判断用户是什么身份
				selectChose: '', //筛选id
				isAllDay: null,
				pageSize: 10,
				pageIndex: 1,
				canLoad: true,
				tapNavId: "", //导航栏id
				jobList: [], //应聘者列表
				// 公司
				compenyInfo: '',
				compState: 1,
				compJobList: [], //企业招聘列表
				searchText: '',
				listOfGg: []
			}
		},
		components: {
			uniPopup,
			navBarTop,
			uniGrid,
			uniGridItem,
			tapBar,
			uniTag,
			customerService
		},
		onLoad() {
			this.getTapList();
		},
		onShow() {
			let statuse = getApp().globalData.userState;
			this.userState = statuse;
			this.initSeach();
			this.compenyInfo = getApp().globalData.compenyInfo;
			this.getBanner();
			if (statuse == 1) {
				this.popList = this.seleList;
				this.getJobList();
			} else if (statuse == 2) {
				this.popList = this.shareList;
				this.getJobList();
			} else if (statuse == 3) {
				this.getCompJobList();
			}
			this.getGgList();
		},
		methods: {
			// 获取公告列表
			getGgList() {
				vpnannouncementGetsvc().then(res => {
					if (res.code == 1) {
						this.listOfGg = res.data.list
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},
			contactUser() {
				this.$refs.customerService.open()
			},
			// 刷新
			setRead(obj) {
				let that = this;
				this.cleanPoint(obj, that.getCompJobList());
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
				this.selectIndex = 0;
				this.tapNavId = "";
				if (this.userState == 1 || this.userState == 2) {
					this.getJobList();
				} else if (this.userState == 3) {
					this.getCompJobList()
				}
			},
			// 搜索框变值
			changeVal(e) {
				this.searchText = e.value
				console.log(this.searchText)
			},
			// 关闭发布
			closeItem(item, indexp) {
				let that = this;
				let jobId = item.jobId;
				let compenyInfo = getApp().globalData.compenyInfo;
				let data = {
					vpnjobUpdate: {
						jobId: jobId,
						state: 2,
						companyId: compenyInfo.companyId
					}
				}
				let arr = that.compJobList;
				vpnjobUpdatesvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						arr.splice(indexp, 1);
						that.compJobList = arr;
						uni.showToast({
							duration: 1500,
							title: '关闭岗位成功',
							icon: 'none'
						})
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},
			// 发布工作
			showJob(item, indexp) {
				let that = this;
				let jobId = item.jobId;
				let compenyInfo = getApp().globalData.compenyInfo;
				let data = {
					vpnjobUpdate: {
						jobId: jobId,
						state: 0,
						companyId: compenyInfo.companyId
					}
				}
				let arr = that.compJobList;
				vpnjobUpdatesvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						arr.splice(indexp, 1);
						that.compJobList = arr;
						uni.showToast({
							duration: 1500,
							title: '发布岗位成功，等待审核',
							icon: 'none'
						})
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},
			// 删除工作 
			delJob(item, indexp) {
				let that = this;
				let jobId = item.jobId;
				let compenyInfo = getApp().globalData.compenyInfo;
				let data = {
					vpnjobUpdate: {
						jobId: jobId,
						state: 3,
						companyId: compenyInfo.companyId
					}
				}
				let arr = that.compJobList;
				vpnjobUpdatesvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						arr.splice(indexp, 1);
						that.compJobList = arr;
						uni.showToast({
							duration: 1500,
							title: '岗位删除成功',
							icon: 'none'
						})
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},
			// 跳转到编辑页面
			goPage(urlp, e) {
				let jobId = e.jobId;
				let that = this;
				if (urlp == '../../pageB/wantResume/wantResume') {
					that.cleanPoint(e);
				}
				uni.navigateTo({
					url: urlp + '?jobId=' + jobId
				})
			},
			// 跳转到添加
			jobAdd() {
				uni.navigateTo({
					url: '../../pageB/jobadd/jobadd'
				})
			},
			// 导航栏选择
			tapNav(id) {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.tapNavId = id;
				this.getJobList();

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
			// 公司切换
			clickComp(e) {
				this.pageIndex = 1;
				this.canLoad = true;
				if (e == 0) {
					this.compState = 1
				} else if (e == 1) {
					this.compState = 2
				}
				this.getCompJobList();
			},
			//切换tap
			// onClickItem(e) {
			// 	this.pageIndex = 1;
			// 	this.canLoad = true;
			// 	if (e == 1) {
			// 		this.isAllDay = 0
			// 	} else {
			// 		this.isAllDay = 1
			// 	}
			// 	this.getJobList();
			// },
			openSelect() {
				this.$refs.popup.open();
			},

			choseOne(indexp, id) {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectIndex = indexp;
				this.selectChose = id;
				this.getJobList();
				this.$refs.popup.close();
			},
			// 跳转到
			goToPage(item) {
				let jobId = item.jobId;
				uni.navigateTo({
					url: '../../pagesA/jobDetail/jobDetail?jobid=' + jobId
				})
			},
			// 跳转去招聘任务
			goTozhaopin(item) {
				uni.navigateTo({
					url: '../../pagesA/bindPhone/bindPhone'
				})
			},
			// 跳转去招聘攻略
			goTogonglv(item) {
				uni.navigateTo({
					url: '../../pagesA/jobMethods/jobMethods'
				})
			},
			// 跳转去新手指导	
			allrec() {
				uni.navigateTo({
					url: '../../pagesA/noviceGuidance/noviceGuidance'

				})
			},



			// 跳转去排行榜
			goTopaihang(item) {
				uni.navigateTo({
					url: '../../pagesA/charts/charts'
				})
			},
			// 获取轮播图
			getBanner() {
				let that = this;
				let data = {
					vpnslideshowGet: {
						toUser: this.userState
					}
				}
				vpnslideshowGetsvc(data).then(res => {
					if (res.code == 1) {
						that.imgList = res.data.vpnslideshowGetResp;
					} else {
						console.log(res.msg)
					}
				})
			},
			// 获取导航栏
			getTapList() {
				let that = this;
				vpnnavigationGetsvc().then(res => {
					console.log(res)
					if (res.code == 1) {
						that.tapList = res.data.content;
					} else {
						console.log(res.msg)
					}
				})
			},
			// 初始化数据
			initSeach() {
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectChose = '';
				this.selectIndex = 0;
				this.tapNavId = "";
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
					vpnjobGet: {
						mapZoom: that.selectChose,
						priority: that.tapNavId,
						isAllDay: that.isAllDay,
						name: that.searchText
					}
				}
				vpnjobGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.jobList = res.data.vpnjobGetResp;
						} else {
							that.jobList = [...that.jobList, ...res.data.vpnjobGetResp]
						}
						if (res.data.vpnjobGetResp.length < that.pageSize) {
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
					vpnjobGet: {
						companyId: that.compenyInfo.companyId,
						state: that.compState,
						name: that.searchText
					}
				}
				vpnjobGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.compJobList = res.data.vpnjobGetResp;
						} else {
							that.compJobList = [...that.compJobList, ...res.data.vpnjobGetResp]
						}
						if (res.data.vpnjobGetResp.length < that.pageSize) {
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

<style lang="less" scoped>
	.litSwiper{
		height: 70rpx;
	}
	.jobList {
		// text-align: center;
		width: 123rpx;
		height: 40rpx;
		line-height: 40rpx;
		flex-grow: 1;
		font-size: 34rpx;
		font-weight: bold;
		color: #2C2C2C;
	}

	.left-row {
		display: flex;
		align-items: center;
	}

	.serchBox {
		padding: 0 15rpx;
	}

	.swiperWrap {
		padding: 0 30rpx;
	}

	.imageBanner {
		width: 100%;
		height: 100%;
	}

	.gridBox {
		padding: 30rpx 30rpx;
	}

	.col_3 {
		display: flex;
	}

	.gridOne {
		text-align: center;
		margin: 20%;
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

	.btn-row {
		display: flex;
		justify-content: space-around;
		margin-bottom: 20rpx;
	}

	.btn-icon {
		height: 140rpx;
		width: 334rpx;
	}

	.notice-line {
		height: 68rpx;
		font-size: 30rpx;
		font-weight: 500;
		color: #2C2C2C;
		display: flex;
		justify-content: center;
		margin-bottom: 20rpx;

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
		display: flex;
		padding: 30rpx 30rpx;
	}

	.tapList {
		font-size: 34rpx !important;
		color: #2C2C2C !important;
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
		border-radius: 10rpx;
		background: #FFFFFF;
		margin-bottom: 20rpx;
		// box-shadow: 0 0 10rpx 0rpx #d8d8d8;
		color: #2C2C2C;

		font-size: 24rpx;
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
		width: 62rpx;
		height: 32rpx;
		border-radius: 0px 0px 4px 4px;
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
		padding: 0 0 33rpx 31rpx;
		display: flex;
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
		font-weight: bold;
		color: #2C2C2C;
	}

	.blue-text {
		font-size: 22rpx;
		font-weight: 400;
		color: #2E73FF;
		margin-top: 16rpx;
	}

	.gray-text {
		font-size: 22rpx;
		font-weight: 400;
		color: #919191;
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
		text-align: center;
		width: 123rpx;
		height: 40rpx;
		line-height: 40rpx;
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
		// display: block;
		font-size: 42rpx;
		font-weight: 500;
		color: #2E73FF;
	}

	.jobText {
		// display: block;
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
</style>
