<template>
	<view class="content" v-if="jobDetial.name">
		<view class="jobDetial">
			<view class="scrollItem">
				<view class="itemTop">
					<view class="oneTop">
						<view class="left-row">
							<text class="workName">{{jobDetial.name}}</text>
							<view class="job-tag all-day" v-if="jobDetial.isAllDay==1">全职</view>
							<view class="job-tag part-time" v-else>兼职</view>
						</view>
						<text class="workMoney" v-if="jobDetial.isAllDay==1">{{Math.round(jobDetial.lowSalary/100)/10}}-{{Math.round(jobDetial.higSalary/100)/10}}k</text>
						<text class="workMoney" v-if="jobDetial.isAllDay==0">{{jobDetial.partTimeSalary}}元/天</text>
					</view>
					<view class="oneTop">
						<view class="oneMid">招聘{{jobDetial.peopleNum}}人 | {{jobDetial.jobExpName || '暂无'}} | {{jobDetial.educationExpName || '暂无'}} </view>
						<view class="oneMid">{{dateFormart(jobDetial.createTime)}}</view>
					</view>
				</view>
				<view class="lineRow" v-if="userState==2"></view>
				<view class="itemBot" v-if="userState==2">
					<view class="botBox">
						<text class="blackText">推荐奖励：</text>
						<view>
							<image class="moneyIcon" src="../../static/images/eeejjjiu.png" mode=""></image>
							<text class="redText">{{jobDetial.brokerage}}元/人</text>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="cardBox" v-if="userState==2">
			<view class="cardTitle"> <text class="cardShu"></text> 职位推荐规则</view>
			<view class="cardContent">
				<text class="blackText">佣金：</text><text class="redText">{{jobDetial.brokerage}}元/人</text>
				<view class="litText">
					{{jobDetial.rule || '暂无'}}
				</view>
			</view>
		</view>
		<view class="cardBox" v-if="jobDetial.isAllDay==0">
			<view class="cardTitle"> <text class="cardShu"></text> 职位信息</view>
			<view class="cardContent">
				<view class="">
					<text class="blackText">招聘人数：</text><text class="oneMid">{{jobDetial.peopleNum}}人</text>
				</view>
				<view class="">
					<text class="blackText">兼职时期：</text><text class="oneMid">{{jobDetial.timeliness}}</text>
				</view>
				<view class="">
					<text class="blackText">兼职日期：</text><text class="oneMid">{{jobDetial.startTime}}/{{jobDetial.endTime}}</text>
				</view>
			</view>
		</view>
		<view class="cardBox" >
			<view class="cardTitle"> <text class="cardShu"></text> 职位亮点</view>
			<view class="cardContent">
				<view class="tagList">
					<!-- <uni-tag :text="item.name" v-for="(item,index) in jobDetial.jobLightspot" type="default" size="small"></uni-tag> -->
					<!-- <uni-tag :text="item.name" v-for="(item,index) in jobDetial.jobCustomLightspot" type="default" size="small"></uni-tag> -->
		<view class="tag">
			好好学习
		</view>
		<view class="tag">
			天天向上
		</view>
				</view>
			</view>
		</view>
		<view class="cardBox">
			<view class="cardTitle"> <text class="cardShu"></text> 职位详情</view>
			<view class="cardContent">
				<!-- <text class="blackText">岗位职责：</text>
				<view class="litList">
					{{jobDetial.jobContent}}
				</view> -->
				<rich-text :nodes="jobDetial.jobContent || '暂无'"></rich-text>
				<!-- <view class="litList">
					1,通过电话与QQ微信与客户进行有效沟通；
				</view> -->
				<!-- <view class="litList">
					2,具有良好的创新力，执行力，有团队精神；
				</view>
				<view class="litList">
					3,愿意长时间从事本工作；
				</view> -->
				<!-- <view class="litText">
					任职资格：
				</view>
				<view class="litList">
					{{jobDetial.remark || "暂无"}}
				</view> -->
				<!-- <view class="litList">
					1,年龄：18-30岁；
				</view>
				<view class="litList">
					2,接受无经验的应届生，转行人事，只要踏实认真，公司有人
					愿意带你做；
				</view>
				<view class="litList">
					3,愿意长时间从事本工作；
				</view> -->
			</view>
		</view>
		<view class="cardBox">
			<view class="cardTitle"> <text class="cardShu"></text> 公司介绍</view>
			<view class="cardContent">
				<view class="compnyBox">
					<view>
						<image class="compnyIcon" :src="jobDetial.company.logourl" mode=""></image>
					</view>
					<view class="compDetail">
						<view class="compnyName">
							{{jobDetial.company.name}}
						</view>
						<view class="aboutComp">
							{{jobDetial.company.scale?(jobDetial.company.scale+'人'):'暂无'}} | {{jobDetial.company.businessName || '暂无'}}
						</view>
					</view>
				</view>
			</view>
			<view class="page-section page-section-gap">
				<map style="width: 100%; height: 300rpx;" :latitude="latitude" :longitude="longitude" :markers="covers"
				 :enable-scroll="false">
					<cover-view slot="callout">
						<cover-view marker-id="1" class="mapOver">
							<cover-view class="coverTitle">工作地点</cover-view>
							<cover-view class="coverContent">{{jobDetial.company.adress || jobDetial.countryName}}</cover-view>
						</cover-view>
					</cover-view>
				</map>
			</view>
		</view>
		<view class="footBox">
			<view class="heartBox" @tap="toSave">
				<uni-icons type="heart" v-if="!isHave"  size="25"></uni-icons>
				<uni-icons type="heart-filled" v-if="isHave"  color='red' size="25"></uni-icons>
				<view class="odd">收藏</view>
			</view>
			<view class="heartBox" @tap="toReport">
				<icon type="warn"   size="23" class="warn-icon" color="#b2b2b2"></icon>
				<view class="odd">举报</view>
				
			</view>
			<view class="wantDo">
				<view class="halfWidth" >
					<button class="btn plain-btn" @click="openShare">我要分享</button>
				</view>
				<view class="halfWidth">
					<button class="btn btn-blue" @click="popupmsg">我要应聘</button>
				</view>
			</view>
		</view>
		<!-- 绘制出来的canvas -->
		<!-- <canvas canvas-id="canvas1" class="share__canvas"></canvas> -->

		<uni-popup ref="popup" type="bottom">
			<view class="popBox">
				<view class="popTitle">
					分享职位
				</view>
				<view class="popContent">
					<view class="">
						<button class="shareBtn" open-type="share">
							<image src="../../static/images/wxIcon.png" mode=""></image>
							<view class="shareName">
								微信好友
							</view>
						</button>
					</view>
					<view class="" @click="setQr">
						<button class="shareBtn">
							<image src="../../static/images/imgIcon.png" mode=""></image>
							<view class="shareName">
								生成海报
							</view>
						</button>
					</view>
				</view>
				<view class="popFoot" @click="closePop">
					取消
				</view>
			</view>
		</uni-popup>
		<uni-popup ref="popupmsg" type="dialog">
			<uni-popup-dialog type="info" mode="base" :content="'请确认向职位“'+jobDetial.name+'”投递简历？'" @confirm="confirm"></uni-popup-dialog>
		</uni-popup>
		<uni-popup ref="popupQr" type="center">
			<view class="qrBox">
				<view class="shareQrBg" :style="'height:'+bgHeight+'px;width:'+bgWidth+'px;'">
					<canvas canvasId='canvasIdT' style="width: 100%;height: 100%;" />
				</view>
				<view class="" @click="setDomToCanvas">
					保存
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import {
		vpnjobGetbyidsvc,
		vpncollectAddsvc,
		vpniscollectJob,
		vpndeliverAddsvc,
		qrCode,
		loginAjax,
		userbasic,
	} from '@/api/index.js'
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uniPopupDialog from '@/components/uni-popup/uni-popup-dialog.vue'
	// import moment from 'moment' 
	export default {  
		data() {  
			return { 
				jobId: '',
				latitude: 32.01,
				longitude: 120.86,
				isHave: false, //是否收藏
				covers: [{
					id: 1,
					latitude: 32.01,
					longitude: 120.86,
					width: 14,
					height: 20,
					customCallout: {
						display: "ALWAYS",
					}
				}],
				jobDetial: {}, //岗位详情
				userState: '',
				drawImage1: '',
				userInfo: '',
				isLogin: '',
				isRecommend: 0, //是否是分享的 0是否 1是
				qrImg: "",
				bgWidth:'',
				bgHeight:''
			};
		},
		components: {
			uniPopup,
			uniPopupDialog
		},
		onLoad(options) {
			if (options.jobid) {
				this.jobId = options.jobid;
			}
			if (options.scene) {
				let sceneClone = JSON.parse(options.scene);
				this.jobId = sceneClone.jobid;
				this.isRecommend = 1;
				getApp().globalData.reUserId = sceneClone.ruserid;
				this.loginIndex();
				// console.log(sceneClone.jobid)
				// console.log(sceneClone.ruserid)
			}
			this.jobDetialAjax();
		},
		onShow() {
			this.userState = getApp().globalData.userState;
			this.userInfo = getApp().globalData.userInfo;
			this.isLogin = getApp().globalData.isLogin;
			console.log("用户信息")
			if (this.userInfo) {
				this.isHaveGet();
				this.getQrImg();
			}
		},
		methods: {
			dateFormart(str){
				// return  moment(str, "YYYY-MM-DD").format("MM/YYYY");
			},
			// 绘制分享海报图片
			makePicture() {
				let that = this;
				uni.showLoading({
					title: '图片制作中',
					mask: true
				})
				let bgUrl = 'http://ax-jhrc.oss-cn-shanghai.aliyuncs.com/2021/01/cf284fb4-0c86-45c8-b514-425967031c57.png'
				let imgUrl = this.formatHttp(bgUrl);
				let erwei = this.qrImg;
				let codeUrl = this.formatHttp(erwei);

				Promise.all([
					that.getImageInfo(imgUrl)
					// that.getImageInfo(codeUrl)
				]).then((data) => {
					const systemInfo = uni.getSystemInfoSync();
					const prop = (data[0].width / data[0].height);
					const firstwith = systemInfo.windowWidth;
					const bgWidth = parseInt(firstwith) * 0.85;
					const bgHeight = bgWidth / prop;
					that.bgHeight=bgHeight;
					that.bgWidth=bgWidth;
					console.log(data[0].width + "-" + data[0].height + "-" + bgWidth + "-" + bgHeight)
					let canvasData = {
						canvasWidth: bgWidth + 'px',
						canvasHeight: bgHeight + 'px',
					}

					const ctx = uni.createCanvasContext('canvasIdT');
					ctx.drawImage(data[0].path, 0, 0, data[0].width, data[0].height, 0, 0, bgWidth, bgHeight);
					const standard = bgHeight * 0.4; // 底部内容垂直中线与背景图底部距离
					//绘画工作名字
					ctx.font = "16px bold 黑体";
					// 设置颜色
					ctx.fillStyle = "#1B1B1B";
					// 设置水平对齐方式
					ctx.textAlign = "left";
					// 设置垂直对齐方式
					ctx.textBaseline = "middle";
					// 绘制文字（参数：要写的字，x坐标，y坐标）
					ctx.fillText('行政前台', 40, bgHeight - 430);
					
					// 绘画工资
					ctx.font = "16px bold 黑体";
					// 设置颜色
					ctx.fillStyle = "#2E73FF";
					// 设置水平对齐方式
					ctx.textAlign = "left";
					// 设置垂直对齐方式
					ctx.textBaseline = "middle";
					// 绘制文字（参数：要写的字，x坐标，y坐标）
					ctx.fillText('5000-8000', 200, bgHeight - 430);
					
					// 绘制二维码
					// const codeWh = 60; // 二维码图片宽度
					// const codeX = bgWidth - 20 - codeWh;
					// const codeY = bgHeight - standard - codeWh / 2;
					// ctx.drawImage(data[1].path, codeX, codeY, codeWh, codeWh);
					ctx.draw();
					uni.hideLoading();
					// this.tabCanvasAction({ 'canvasData.makeStatus': true }, () => {
					//   utils.hideLoading();
					// })
				}, (err) => {
					console.log(err);

				})

			},

			// 获取图片信息
			getImageInfo(imageUrl) {
				return new Promise((resolve, reject) => {
					uni.getImageInfo({
						src: imageUrl,
						success: (res) => {
							resolve(res);
						},
						fail: (err) => {
							reject(err);
						}
					})
				})
			},
			// 格式化https
			// http://thirdwx.qlogo.cn     https://wx.qlogo.cn
			formatHttp(url) {
				if (url.substr(0, 5) === 'http:') {
					if (url.substr(0, 14) === 'http://thirdwx') {
						return 'https://wx' + url.substring(14);
					}
					return 'https' + url.substring(4);
				}
				return url;
			},
			// 登陆
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
								setTimeout(() => {
									uni.navigateTo({
										url: '../../pages/myIndex/myIndex'
									})
								}, 1200)
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
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			// 分享
			onShareAppMessage: function(res) {
				let that = this;
				let scene = {
					jobid: that.jobId,
					ruserid: that.userInfo.userId
				}
				return {
					title: that.jobDetial.name,
					path: 'pagesA/jobDetail/jobDetail?scene=' + JSON.stringify(scene),
					// path: 'pagesA/jobDetail/jobDetail?jobid=' + that.jobId + '&ruserid=' + that.userInfo.userId,
				}
			},


			// 设置qr
			setQr() {

				this.$refs.popupQr.open();
				this.makePicture();
				this.$refs.popup.close();
			},
			// 获取qr
			getQrImg() {
				let that = this;
				let scene = {
					jobid: that.jobId,
					ruserid: that.userInfo.userId
				}
				let data = {
					path: 'pagesA/jobDetail/jobDetail',
					scene: JSON.stringify(scene)
				}
				qrCode(data).then(res => {
					if (res.code == 1) {
						that.qrImg = res.data.filePath
					}
				})

			},

			// 收藏
			toSave() {
				if (this.isLogin == 0) {
					uni.showModal({
						title: '提示',
						content: '请先登录，才能收藏哦',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '../../pages/myIndex/myIndex'
								})
							}
						}
					});
					return false;
				}
				let that = this;
				this.isHave = !this.isHave;
				let isGet = this.isHave;
				let states = ''
				if (isGet == true) {
					states = 1
				} else {
					states = 0
				}
				let data = {
					vpncollectAdd: {
						userId: that.userInfo.userId,
						jobId: that.jobId,
						state: states
					}
				}
				vpncollectAddsvc(data).then(res => {
					if (res.code == 1) {
						if (states == 1) {
							uni.showToast({
								title: "收藏成功",
								icon: 'none',
								duration: 1500
							})
						} else {
							uni.showToast({
								title: "取消成功",
								icon: 'none',
								duration: 1500
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
			toReport(){
				if (this.isLogin == 0) {
					uni.showModal({
						title: '提示',
						content: '登录后才可以获得奖励',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '../../pages/myIndex/myIndex'
								})
							}
						}
					});
					return false;
				}
				let jobId = this.jobId;
				uni.navigateTo({
					url: '../../pagesA/report/report?jobid=' + jobId
				})
			},
			// 查看是否收藏过
			isHaveGet() {
				let that = this;
				let data = {
					userId: that.userInfo.userId,
					jobId: that.jobId
				}
				vpniscollectJob(data).then(res => {
					if (res.code == 1) {
						if (res.data.iscollect == 1) {
							that.isHave = true;
						} else {
							that.isHave = false;
						}
					} else {
						console.log("是否收藏", res.msg)
					}
				})
			},

			// 打开
			openShare() {
				if (this.isLogin == 0) {
					uni.showModal({
						title: '提示',
						content: '登录后才可以获得奖励',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '../../pages/myIndex/myIndex'
								})
							}
						}
					});
					return false;
				}
				this.$refs.popup.open();
			},
			// 关闭弹出
			closePop() {
				this.$refs.popup.close();
			},
			//确认msg
			popupmsg() {
				if (this.isLogin == 0) {
					uni.showModal({
						title: '提示',
						content: '登录后才可以应聘该职位',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '../../pages/myIndex/myIndex'
								})
							}
						}
					});
					return false;
				}
				this.$refs.popupmsg.open();
			},
			// confirm提交简历
			confirm() {
				let that = this;
				let data = {
					vpndeliverAdd: {
						userId: that.userInfo.userId,
						jobId: that.jobId,
						isRecommend: that.isRecommend, //是否为分享的
						reUserId: getApp().globalData.reUserId //分享者的id
					}
				}
				console.log('提交的信息', data)
				vpndeliverAddsvc(data).then(res => {
					console.log(res)
					if(res.code==1){
						uni.redirectTo({
							url: '../successDelivery/successDelivery'
						})
					}else{
						this.$refs.popupmsg.close();
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}

				})
			},

			// 获取岗位详情
			jobDetialAjax() {
				let that = this;
				let data = {
					id: that.jobId
				}
				vpnjobGetbyidsvc(data).then(res => {
					console.log("vpnjobGetbyidsvc",res)
					if (res.code == 1) {
						that.jobDetial = res.data.vpnjobGetResp;
					} else {
						console.log('岗位详情', res.msg)
					}
				})
			},
		}

	}
</script>

<style lang="less">
	.left-row{
		display: flex;
		align-items: center;
	}
	.content {
		overflow-y: auto;
		padding-bottom: 100rpx;
	}

	.lineRow {
		border-bottom: 1rpx solid #F3F3F3;
	}

	.jobDetial {
		padding: 30rpx;
	}

	.scrollItem {
		border-radius: 10rpx;
		box-shadow: 0rpx 0rpx 10rpx 10rpx #E5E5E5;
	}

	.tagList {
		display: flex;
		flex-wrap: wrap;
		padding: 15rpx;
		.tag{
			width: 118rpx;
			height: 46rpx;
			background: #F7F9FC;
			border-radius: 4rpx;
			font-size: 22rpx;
			font-family: PingFang SC;
			font-weight: 400;
			color: #919191;
			text-align: center;
			line-height: 46rpx;
			margin-right: 14rpx;
		}
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
.job-tag{
		width: 62rpx;
		height: 32rpx;
		border-radius: 0px 0px 4px 4px;
		margin-left: 24rpx;
		display:flex;
		align-items: center;
		justify-content: center;
		font-size: 20rpx;
		font-family: PingFang SC;
		font-weight: 500;
	}
	.all-day{
		background: #FFEFE3;
		color: #FF8528;
	}
	.part-time{
		background: #EDFAF7;
		color: #43C4A9;
	}
	.workMoney {
		color: #2E73FF;
		font-size: 32rpx;
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
	}

	.redText {
		font-size: 32rpx;
		color: #FE2B67;
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

	/*cardbox*/
	.cardBox {
		padding: 30rpx;
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

	.litText {
		font-size: 26rpx;
		color: #2C2C2C;
		line-height: 36rpx;
		padding: 15rpx 0;
	}

	.cardContent {
		padding-top: 20rpx;
	}

	/*	*/
	.litList {
		font-size: 26rpx;
		color: #2C2C2C;
		line-height: 30rpx;
		padding: 15rpx 0;
	}

	/*公司盒子*/
	.compnyBox {
		display: flex;
		align-items: center;
		padding: 10rpx 0 40rpx;
	}

	.compDetail {
		padding-left: 20rpx;
	}

	.compnyIcon {
		width: 100rpx;
		height: 100rpx;
		vertical-align: -10rpx;
	}

	.compnyName {
		font-size: 30rpx;
		font-weight: bold;
		margin-bottom: 15rpx;
	}

	.aboutComp {
		color: #919191;
		font-size: 26rpx;
	}

	/*公司盒子*/
	.mapOver {
		background: #FFFFFF;
		padding: 10rpx;
	}

	.coverTitle {
		font-size: 26rpx;
		font-weight: bold;
	}

	.coverContent {
		color: #919191;
		font-size: 24rpx;
		margin-top: 10rpx;
	}

	/*底部收藏*/
	.footBox {
		padding: 0 30rpx;
		display: flex;
		align-items: center;
		position: fixed;
		height: 100rpx;
		bottom: 0;
		left: 0;
		background: #FFFFFF;
		width: 100%;
		.odd{
			margin-left: 8rpx;
		}
		
	}

	.heartBox {
		width: 100rpx;
		padding: 0 20rpx;
		font-size: 20rpx;
		font-weight: 500;
		color: #2C2C2C;

	}

	.wantDo {
		flex: 1;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.halfWidth {
		padding: 0 15rpx;
		width: 50%;
	}

	.widthAll {
		width: 100%;
	}

	.btn {
		width: 100%;
		height: 70rpx;
		border: 1px solid #2E73FF;
		border-radius: 35px;
		font-size: 26rpx;
		line-height: 68rpx;
	}

	.plain-btn {
		background: rgba(46, 115, 255, 0.08);
		color: #2E73FF;
	}

	.btn-blue {
		color: #ffffff;
		background: #2E73FF;
	}

	/*	分享弹出*/
	.popBox {
		background: #ffffff;
		border-radius: 20rpx 20rpx 0 0;
	}

	.popTitle {
		font-size: 32rpx;
		color: #303133;
		font-weight: bold;
		text-align: center;
		line-height: 60rpx;
		padding-top: 30rpx;
	}

	.popContent {
		height: 250rpx;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.popContent image {
		width: 90rpx;
		height: 90rpx;
	}

	.shareName {
		font-size: 22rpx;
		color: #7E7E7E;
		padding-top: 20rpx;
	}

	.popFoot {
		height: 93rpx;
		background: #F5F5F5;
		line-height: 93rpx;
		text-align: center;
		font-size: 30rpx;
		font-weight: 500;
		color: #303133;
	}

	// 分享的背景
	.shareQrBg {
		margin: 0 auto;
		z-index: 200;
	}

	.shareBtn {
		background-color: #FFFFFF;
		line-height: 30rpx;
	}

	.shareBtn::after {
		border: none;
	}
	.warn-icon{
		padding-top:10rpx
	}
</style>
