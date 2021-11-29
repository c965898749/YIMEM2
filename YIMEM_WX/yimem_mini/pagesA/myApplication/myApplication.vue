<template>
	<view class="content">
		<view class="topTap">
			<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
		</view>
		<scroll-view scroll-y="true" class="allList" @scrolltolower="botMore">
			<view class="listBox">
				<view class="scrollItem" v-for="(item,index) in compJobList" :key="index" @click="toDetail(item.job)">
					<view class="itemBot">
						<view class="botBox">
							<view>
								<view class="typeOf" v-if="typet==1">
									<image class="sendIcon" src="../../static/images/yitoudi.png" mode=""></image>
									<text class="orangeText" v-if="item.flag!=2&&item.flag!=3">已投递</text><text
										class="orangeText" v-if="item.flag==2">已拒绝</text>
									<text class="orangeText" v-if="item.flag==3">已失效</text>
								</view>
								<view class="typeOf" v-if="typet==3">
									<image class="invitationIcon" src="../../static/images/eejjisi.png" mode="">
									</image> <text class="blueText">待面试</text>
								</view>
								<view class="typeOf" v-if="typet==7">
									<image class="entryIcon" src="../../static/images/jsinjob.png" mode=""></image>
									<text class="greenText">已入职</text>
								</view>
								<view class="typeOf" v-if="typet==0">
									<image class="noPassIcon" src="../../static/images/jsnopass.png" mode=""></image>
									<text class="blackText">不合适</text>
								</view>
								<text class="huiText">{{item.updateTime || ""}}</text>
							</view>
							<view>
								<!-- <button class="openType" v-if="typet==3" @click.stop="callOne(item.job.phone)">
									<image class="msgIcon" src="../../static/images/phonea.png" mode=""  ></image>
								</button> -->
								<button class="openType" v-if="typet==1" @click.stop open-type="contact">
									<image class="msgIcon" src="../../static/images/111223.png" mode=""></image>
								</button>
								<button class="woyilizhi" v-if="typet==6" @click.stop="tabToSure(6,item)">
									确认入职
								</button>
								<button class="woyilizhi" v-if="typet==7" @click.stop="tabToSure(7,item)">
									我已离职
								</button>
								<!-- <u-button class="woyilizhi" v-if="typet==7">我已离职</u-button> -->
								<text class="litHui" v-if="typet==0">职位关闭</text>
							</view>
						</view>
					</view>
					<view class="lineRow"></view>
					<view class="itemTop">
						<view class="oneTop">
							<text class="workName">{{item.job.name}}</text>
							<text class="workMoney"
								v-if="item.job.isAllDay==1">{{Math.round(item.job.lowSalary/100)/10}}-{{Math.round(item.job.higSalary/100)/10}}k</text>
							<text class="workMoney"
								v-if="item.job.isAllDay==0">{{item.job.partTimeSalary || '暂无'}}元/天</text>
						</view>
						<view class="oneMid" v-if="item.job.isAllDay==1">
							{{item.job.cityName || '暂无'}}{{item.job.countryName || '暂无'}} |
							{{item.job.jobExpName || '暂无'}} | {{item.job.educationExpName || '暂无'}}
						</view>
						<view class="tagList" v-if="item.job.isAllDay==1">
							<uni-tag :text="iOne.name" v-for="(iOne,indexOne) in item.job.jobCustomLightspot"
								type="default" :key="indexOne" size="small"></uni-tag>
							<uni-tag :text="iTwo.name" v-for="(iTwo,indexTwo) in item.job.jobLightspot" type="default"
								:key="indexTwo" size="small"></uni-tag>
						</view>
						<view class="" v-if="item.job.isAllDay==0">
							<view class="oneMid">{{item.job.countryName || '暂无'}} | 收{{item.job.peopleNum || '暂无'}}人 |
								{{item.job.timeliness || '暂无'}}
							</view>
						</view>
					</view>
					<view class="lineRow" v-if="typet==3"></view>
					<view class="placeRow" v-if="typet==3">
						<view class="lineOne">
							<view class="imgIcon">
								<text class="iconfont iconshijian"></text>
							</view>
							<text>面试时间：{{item.deliverDetail.time || "暂无"}}</text>
						</view>
						<view class="lineOne">
							<view class="imgIcon">
								<text class="iconfont icondidian"></text>
							</view>
							<text>面试地点：{{item.deliverDetail.deliverDetail || "暂无"}}</text>
						</view>
						<view class="lineOne">
							<view class="imgIcon">
								<text class="iconfont iconbeizhu"></text>
							</view>
							<text>面试备注：{{item.deliverDetail.remark || "暂无"}}</text>
						</view>
					</view>
				</view>
			</view>
			<view class="noMoreList" v-if="canLoad==false">
				没有更多。。。
			</view>
		</scroll-view>
		<!-- 选择入职时间 -->
		<uni-popup ref="hasSuccess" type="bottom">
			<view class="noSuccess">
				<view class="inviteTitle">
					入职时间 <uni-icons @click="()=>{this.$refs.hasSuccess.close()}" class="closeIcon" type="closeempty"
						size="20"></uni-icons>
				</view>
				<view class="inviteContent">
					<view class="row">
						<view class="col_3">
							入职时间
						</view>
						<view class="col_8">
							<picker mode="date" :value="time" @change="bindTimeChange">
								<view class="uni-input">{{time}}</view>
							</picker>
						</view>
						<view class="col_1">
							<uni-icons type="forward" size="20" color='#7E7E7E'></uni-icons>
						</view>
					</view>
					<view class="btnRow">
						<view class="sendBtn" @click="setUser">
							确认入职
						</view>
					</view>
				</view>
			</view>
		</uni-popup>
		<!-- 已入职后不合适 -->
		<uni-popup ref="inJobNoOk" type="bottom">
			<view class="noSuccess">
				<view class="inviteTitle">
					离职 <uni-icons @click="()=>{this.$refs.inJobNoOk.close()}" class="closeIcon" type="closeempty"
						size="20"></uni-icons>
				</view>
				<view class="inviteContent">
					<view class="row">
						<view class="col_3">
							离职时间
						</view>
						<view class="col_8">
							<picker mode="date" :value="time" @change="bindTimeChange">
								<view class="uni-input">{{time}}</view>
							</picker>
						</view>
						<view class="col_1">
							<uni-icons type="forward" size="20" color='#7E7E7E'></uni-icons>
						</view>
					</view>
					<view class="row">
						<view class="col_3">
							离职原因
						</view>
						<view class="col_8">
							<input type="text" placeholder="请输入离职原因" v-model="remark" />
						</view>
						<view class="col_1">
							<uni-icons type="forward" size="20" color='#7E7E7E'></uni-icons>
						</view>
					</view>
					<view class="btnRow">
						<view class="sendBtn" @click="setUser">
							确认离职
						</view>
					</view>
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import tapBar from "@/components/tapBar/tapBar.vue"
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uniPopupMessage from '@/components/uni-popup/uni-popup-message.vue'
	import uniPopupDialog from '@/components/uni-popup/uni-popup-dialog.vue'
	import {
		vpnUserDeliverDetailAddsvc
	} from "../../api/index.js"
	import {
		vpndeliverGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				options: ['已投递', '待面试', '已入职', '不合适'],
				activeIndex: 0,
				typet: 1, //0不合适 1已投递 2已邀约3待面试4推送企业 5企业面试6已面试7已入职8已离职
				userId: '',
				compJobList: [],
				pageIndex: 1,
				pageSize: 10,
				canLoad: true,
				typeList: {
					'0': 1,
					'1': 3,
					'2': 7,
					'3': 0
				},
				centerObj: {},
				centerType: '',
				time: '请选择时间',
				remark: ''
			}
		},
		components: {
			tapBar
		},
		onShow() {
			this.userId = getApp().globalData.userId;
			this.myApplicationAjax();
			this.showInit();
		},
		methods: {
			showInit() {
				this.centerObj = {},
					this.centerType = '',
					this.time = '请选择时间',
					this.remark = ''
			},
			// 加载更多
			botMore() {
				if (this.canLoad != false) {
					this.myApplicationAjax();
				}
			},
			//请求改变用户状态
			setUser() {
				let type = '';
				if (this.centerType == 6) {
					type = 7
				} else if (this.centerType == 7) {
					type = 9
				}
				let data = {
					vpnDeliverDetailAdd: {
						devId: this.centerObj.devId,
						leaveTime: this.time,
						resion: this.remark,
						state: type
					}
				}
				vpnUserDeliverDetailAddsvc(data).then(res => {
					if (res.code == 1) {
						this.$refs.hasSuccess.close();
						this.$refs.inJobNoOk.close();
						uni.showToast({
							title: '成功',
							duration: 1500,
							icon: 'none'
						})
						setTimeout(()=>{
							this.pageIndex = 1;
							this.canLoad = true;
							this.myApplicationAjax()
						},1500)
					} else {
						uni.showToast({
							title: '失败',
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			tabToSure(type, item) {
				this.centerObj = item;
				this.centerType = type;
				if (type == 6) {
					this.$refs.hasSuccess.open();
				} else if (type == 7) {
					this.$refs.inJobNoOk.open();
				}
			},
			// 切换时间
			bindTimeChange: function(e) {
				this.time = e.target.value
			},
			// 切换状态
			onClickItem(e) {
				let theIndex = e;
				let tpye = this.typeList[theIndex];
				this.typet = tpye;
				this.pageSize = 10;
				this.pageIndex = 1;
				this.canLoad = true;
				this.myApplicationAjax()
			},


			// 跳转到详情
			toDetail(item) {
				let jobId = item.jobId;
				uni.navigateTo({
					url: '../jobDetail/jobDetail?jobid=' + jobId
				})
			},
			myApplicationAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpndeliverGet: {
						userId: that.userId,
						state: that.typet
					}
				}
				vpndeliverGetsvc(data).then(res => {
					console.log(res)
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
	.topTap {
		height: 75rpx;
		padding-top: 10rpx;
	}

	.allList {
		height: calc(100% - 75rpx);
		background: #F6F6F6;

		.listBox {
			padding: 20rpx 30rpx;
		}
	}

	.scrollItem {
		border-radius: 10rpx;
		background: #FFFFFF;
		margin-bottom: 20rpx;
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
		padding: 30rpx 0rpx;
		color: #919191;
		font-size: 26rpx;
	}

	.itemTop {
		padding: 30rpx;
	}

	.workName {
		font-size: 32rpx;
		font-weight: bold;
		color: #2C2C2C;
	}

	.workMoney {
		color: #2E73FF;
		font-size: 32rpx;
	}

	.itemBot {
		padding: 20rpx 30rpx;
	}

	.botBox {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.orangeText {
		color: #FF8F4B;
		font-size: 26rpx;
		margin-left: 15rpx;
		margin-right: 15rpx;
	}

	.blueText {
		color: #2E73FF;
		font-size: 26rpx;
		margin-left: 15rpx;
		margin-right: 15rpx;
	}

	.blackText {
		color: #1B1B1B;
		font-size: 26rpx;
		margin-left: 15rpx;
		margin-right: 15rpx;
	}

	.greenText {
		color: #64D177;
		font-size: 26rpx;
		margin-left: 15rpx;
		margin-right: 15rpx;
	}

	.redText {
		font-size: 32rpx;
		color: #FE2B67;
	}

	.sendIcon {
		width: 24rpx;
		height: 27rpx;
		vertical-align: -4rpx;
	}

	.entryIcon {
		width: 29rpx;
		height: 26rpx;
		vertical-align: -5rpx;
	}

	.noPassIcon {
		width: 27rpx;
		height: 26rpx;
		vertical-align: -5rpx;
	}

	.msgIcon {
		width: 50rpx;
		height: 50rpx;
		vertical-align: -5rpx;
		margin-left: 20rpx;
	}

	.invitationIcon {
		width: 28rpx;
		height: 28rpx;
		vertical-align: -5rpx;
	}

	.lineRow {
		border-bottom: 2rpx solid #F3F3F3;
	}

	.typeOf {
		display: inline-block;
	}

	.huiText {
		color: #919191;
		font-size: 26rpx;
	}

	.activeTap {
		font-weight: bold !important;
	}

	// row
	.lineOne {
		font-size: 24rpx;
		color: #2C2C2C;
		line-height: 40rpx;
	}

	.placeRow {
		padding: 30rpx;
	}

	.imgIcon {
		display: inline-block;
		width: 60rpx;

		image {
			width: 30rpx;
			height: 30rpx;
		}
	}

	.openType {
		display: inline-block;
		font-size: 0;
		line-height: initial;
		background-color: #FFFFFF;
		padding: 0;
		vertical-align: -7rpx;
	}

	.woyilizhi {
		font-size: 24rpx;
		line-height: 50rpx;
		color: #FFFFFF;
		background: linear-gradient(-67deg, #2E73FF, #81AAFC);
		border-radius: 40rpx;

	}

	.openType::after {
		border: none;
	}

	.litHui {
		font-size: 26rpx;
		font-weight: 500;
		color: #999999;
	}

	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}
	
	//不合适
	.noSuccess {
		background: #FFFFFF;
	}
	
	.tapTextBot {
		font-size: 26rpx;
		font-weight: normal;
		color: #2C405A;
	}
	
	.openType .tapTextBot {
		margin-top: -5rpx;
	}
	
	.openType {
		padding: 0 !important;
		background: none !important;
		line-height: normal !important;
	}
	
	.openType::after {
		border: none !important;
	}
	
	.isagreetype {
		margin-left: 15rpx;
		padding: 5rpx 10rpx 8rpx;
		vertical-align: 5rpx;
		font-size: 18rpx;
		line-height: 18rpx;
		border-radius: 5rpx;
		background: #e7e9eb;
		color: #2E73FF;
	}
	
	.inviteTitle {
		font-size: 36rpx;
		font-weight: bold;
		color: #2A2A2A;
		padding: 45rpx 40rpx 0;
		position: relative;
		text-align: center;
	
		.closeIcon {
			position: absolute;
			right: 40rpx;
			top: 45rpx;
		}
	}
	
	.inviteContent {
		padding: 30rpx;
	
		.row {
			padding: 30rpx 0;
			border-bottom: 1rpx solid #F3F3F3;
	
			.col_3 {
				font-size: 28rpx;
				font-weight: 500;
				color: #2A2A2A;
			}
	
			.col_9,
			.col_8 {
				font-size: 26rpx;
				font-weight: 400;
				color: #7E7E7E;
			}
	
		}
	
		.btnRow {
			padding-top: 60rpx;
			padding-bottom: 30rpx;
	
			.sendBtn {
				margin: 0 auto;
				width: 620rpx;
				height: 80rpx;
				background: linear-gradient(0deg, #2E73FF, #4DA2FF);
				box-shadow: 0rpx 11rpx 29rpx 0rpx rgba(47, 117, 255, 0.59);
				border-radius: 40rpx;
				line-height: 80rpx;
				text-align: center;
				color: #FFFFFF;
			}
		}
	}
</style>
