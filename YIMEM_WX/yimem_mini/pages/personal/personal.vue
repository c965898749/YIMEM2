<template>
	<view class="content">
		<view class="" v-if="userState!=3">
			<!-- <u-picker mode="selector" v-model="show" :default-selector="[0]" :range="selector"></u-picker> -->
			<!-- <u-picker mode="selector" v-model="show" :default-selector="[0]" :range="selectorObj" range-key="cateName">
			</u-picker> -->
			<u-select mode="mutil-column-auto" v-model="show" @confirm="getArea" :list="multiSelector"></u-select>
		</view>
		<!-- <navBarTop title="南通市" :isBack="false"></navBarTop> -->
		<scroll-view scroll-y="true" class="scrollBox" @scrolltolower="botMore">
			<view class="noHeadCont" v-if="userState!=3">
				<view class="serchBox">
					<!-- <u-search placeholder="平面设计师" v-model="keyword"></u-search> -->
					<!-- <uni-search-bar placeholder="搜索你想要的职位" @input="changeVal" @confirm="searchFun"></uni-search-bar> -->
					<view @click='click()' class="custom-style"><text class="uni-lastmsg">{{placeText}}</text>
							<u-icon class="uicon" name="arrow-down"></u-icon>
					</view>
					<u-search class="usearch" action-text="取消" :clearabled="true" placeholder="平面设计师" v-model="keyword"
						@change="changeVal" @search="searchFun" animation></u-search>
				</view>
				<view>

				</view>
			</view>

			<!-- 分销者和求职者 -->
			<view class="" v-if="userState!=3">
				<view class="tabs">
					<u-tabs :list="list" :is-scroll="true" :current="current" @change="changeType"></u-tabs>
				</view>

				<view class="scrollList">
					<view class="scrollItem" @click="goToPage(item)" v-for="(item,index) in jobList" :key="index">
						<view class="itemTop">
							<view class="oneTop">
								<view class="left-row">
									<text class="workName">{{item.name}}</text>
									<view class="job-tag all-day" v-if="item.isAllDay==1">全职</view>
									<view class="job-tag part-time" v-else>兼职</view>
								</view>
								<view v-if="userState==1">
									<!-- 四舍五入保留一位小数 -->
									<text class="workMoney"
										v-if="item.isAllDay==1">{{Math.round(item.lowSalary/100)/10}}-{{Math.round(item.higSalary/100)/10}}k</text>
									<text class="workMoney" v-if="item.isAllDay==0">{{item.partTimeSalary}}元/天</text>
								</view>
							
								<!-- 分销-推荐佣金 -->
								<view v-if="userState==2" class="left-row">
									<image src="../../static/images/money.png" class="money-icon"></image>
									<text class="blackText">佣金：</text>
									<text class="redText">{{item.brokerage}}元/人</text>
								</view>
							</view>
							<view class="oneMid item-near">招聘{{item.peopleNum || 0}}人 | {{item.jobExpName  || '暂无'}} |
								{{item.educationExpName  || '暂无'}}
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
					<view class="col_12">
						<tapBar :activeIndex="activeComp" :options="optionsComp" @tapChange="clickComp"></tapBar>
					</view>
					<!-- 	<view class="col_4"></view>
					<view class="col_3 toEnd">
						<text class="addIcon" @click="jobAdd">
							<text> + </text> 发布
						</text>
					</view> -->
				</view>
				<view class="scrollList">
					<view class="scrollItem" @click="goToPage(item)" v-for="(item,index) in compJobList" :key="index">
						<view class="row">
							<view class="itemTop col_9 itemLeft">
								<view class="oneTop">
									<view class="">
										<text class="workName">{{item.name}}</text>
										<text class="orangeText" v-if="item.isAllDay==1">全职</text>
										<text class="greenText" v-if="item.isAllDay==0">兼职</text>
									</view>
								</view>
								<view class="oneMid" v-if="item.isAllDay==1">{{item.cityName}}{{item.countryName}} |
									{{item.jobExpName  || '暂无'}} | {{item.educationExpName  || '暂无'}} |
									{{item.lowSalary}}-{{item.higSalary}}
								</view>
								<view class="oneMid" v-if="item.isAllDay==0">{{item.cityName}}{{item.countryName}} |
									收{{item.peopleNum || 0}}人| {{item.timeliness || '暂无'}} | {{item.partTimeSalary}}元/天</view>

							</view>
							<view class="col_3 itemRight"
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

					<view class="fixPic">
						<image src="../../static/images/fabuzw.png" mode="widthFix" @click="jobAdd"></image>
					</view>


				</view>
			</view>

		</scroll-view>

		<customer-service ref='customerService'></customer-service>

	</view>
</template>

<script>
	import {
		vpnslideshowGetsvc,
		vpnjobGetsvc,
		vpnjobUpdatesvc,
		updateIsread
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
				// options: ['全职', '兼职'],
				optionsComp: ['招聘中', '已关闭'],
				tapList: [], //导航
				// activeIndex: 0,
				activeComp: 0,
				// GAIA添加

				keyword: '',
				show: false,
				selector: [1, 2],
				multiSelector: [{
					label: '南通市',
					value: '320600',
					children: [{
						label: '崇川区',
						value: '320602',
					}, {
						label: '港闸区',
						value: '320611',
					}, {
						label: '通州区',
						value: '320612',
					}, {
						label: '如东县',
						value: '320623',
					}, {
						label: '开发区',
						value: '320671',
					}, {
						label: '启东市',
						value: '320681',
					}, {
						label: '如皋市',
						value: '320682',
					}, {
						label: '海门市',
						value: '320684',
					}, {
						label: '海安市',
						value: '320685',
					}]
				}],
				// 这种情况需要指定range-key为cateName，否则组件不知道该显示对象的哪个属性
				selectorObj: [{
						cateName: '1',
						id: 1
					},
					{
						cateName: '2',
						id: 2
					}
				],
				
				list: [{
					name: '全部岗位'
				}, {
					name: '高薪岗位'
				}, {
					name: '热招岗位',
					// count: 5
				}],
				current: 0, //tabs切换

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
				compState: 1,
				compJobList: [], //企业招聘列表
				cityId: '320600',
				searchText: '',
				countryId: '',
				placeText: '南通',
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
			this.initSeach();
			this.compenyInfo = getApp().globalData.compenyInfo;
			if (statuse == 1 || statuse == 2) {
				this.getJobList();
			} else if (statuse == 3) {
				this.getCompJobList();
			}
		},
		methods: {
			getArea(one) {
				this.countryId=one[1].value;
				this.placeText = one[1].label;
				this.pageIndex=1;
				this.canLoad=true;
				this.getJobList()
			},
			click() {
				this.show = true;
			},

			changeType(index) {
				this.current = index;
				if (index == 1) {
					this.selectChose = 'QS_wage'
				} else if (index == 2) {
					this.selectChose = 'QS_hot'
				} else {
					this.selectChose = ''
				}
				this.pageIndex = 1;
				this.canLoad = true;
				this.getJobList();
			}, // tabs切换

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
				this.selectIndex = 0;
				if (this.userState == 1 || this.userState == 2) {
					this.getJobList();
				} else if (this.userState == 3) {
					this.getCompJobList()
				}
			},
			// 搜索框变值
			changeVal() {
				this.searchText = this.keyword
				// console.log(this.searchText)
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
			// 跳转到
			goToPage(item) {
				let jobId = item.jobId;
				uni.navigateTo({
					url: '../../pagesA/jobDetail/jobDetail?jobid=' + jobId
				})
			},

			// 初始化数据
			initSeach() {
				this.countryId= '';
				this.placeText= '南通';
				this.pageIndex = 1;
				this.pageSize = 10;
				this.canLoad = true;
				this.selectChose = '';
				this.selectIndex = 0;
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
						countryId:this.countryId,
						cityId:this.cityId,
						mapZoom: that.selectChose,
						priority: '',
						isAllDay: '',
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

<style lang="less">
	.uni-lastmsg {
		display: inline-block;
		max-width: 100rpx;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.fixPic {
		position: fixed;
		right: 240.8rpx;
		bottom: 0rpx;

		image {
			width: 270rpx;
			height: 104rpx;
		}

	}

	.uicon {
		margin-left: 10rpx;
		vertical-align: 8rpx;
	}

	.usearch {
		display: inline;
		flex-grow: 1;
		padding: 10rpx;
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
		background-color: #F8F8F8;
		padding: 25rpx;
	}

	.scrollItem {
		border-radius: 10rpx;
		background: #FFFFFF;
		margin-bottom: 20rpx;
		box-shadow: 0 0 10rpx 0rpx #d8d8d8;
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

		font-size: 24rpx;
		font-family: PingFang SC;
		font-weight: 400;
		color: #2C2C2C;
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
		font-family: PingFang SC;

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
		width: 21rpx;
		height: 20rpx;
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
	.workMoney {
		color: #2E73FF;
		font-size: 32rpx;
	}
	
	.money-icon {
		width: 24rpx;
		height: 30rpx;
		margin-right: 10rpx;
	}
</style>
