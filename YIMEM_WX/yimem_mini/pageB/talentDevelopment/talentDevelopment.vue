<template>
	<view class="content">
		<navBarTop title="人才进展" color="#FFFFFF" backgroundColor="rgba(0,0,0,0)"
			imgNavBg="../../static/images/jhzu2.png"></navBarTop>
		<view class="oneItem">
			<view class="itemLeft">
				<image class="headImg" :src="users.headImg?users.headImg:'../../static/images/headimgMy.png'" mode="">
				</image>
			</view>
			<view class="itemRight">
				<view class="pepole">
					<view class="pname">
						{{users.userName}}-{{users.phoneNum}}
					</view>
					<view class="">
						<text class="tagBox">{{users.state | filterState}}</text>
					</view>
				</view>
				<view class="pContent">
					<view class="pText">
						<view class="pTip">{{users.userSex==1?'男':'女'}} | {{users.age?users.age+'岁 | ':''}}
							{{users.education||'未知'}} | {{users.workage?users.workage+'年 | ':''}}
							{{users.cityName||'未知'}}</view>
					</view>
					<view class="pText">
						<text>应聘职位：{{users.jobName||''}}</text>
					</view>
					<view class="pText cardBot">
						<view class="blueText">
							{{users.expectedSalaryName || '暂无'}}
						</view>
						<view class="">
							<image @click="goTo(users.userId)" class="seeBook" src="../../static/images/jhseebod.png"
								mode=""></image>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="cardBox">
			<view class="cardTitle"> <text class="cardShu"></text> 进展详情</view>
			<view class="cardContent">
				<view class="stepingBox" v-for="(item,index) in listCon" :key="index">
					<view class="stepTitleFont">
						<image src="../../static/images/jhclise.png" mode=""></image><text
							class="stepleftf">{{item.state | filterState}} <text class="isagreetype"
								v-if="item.state==2">{{item.flag | flagName}}</text></text>
					</view>
					<view class="blueLineBox">
						<view class="huiLine">
							<view class="stepTip">
								更新时间：<text class="timeText">{{item.createTime}}</text>
							</view>
						</view>
						<view class="huiLine" v-if="item.state==2">
							<view class="stepTip">
								面试地点：<text class="timeText">{{item.adress}}</text>
							</view>
						</view>
						<view class="huiLine" v-if="item.state==2">
							<view class="stepTip">
								面试备注：<text class="timeText">{{item.remark}}</text>
							</view>
						</view>
					</view>
				</view>

			</view>
		</view>
		<view class="footBox">
			<view class="inFootBox">
				<view class="footTapBox">
					<view class="">
						<image src="../../static/images/jsnook.png" mode="" @click="sureAgain"></image>
						<!-- <image v-else src="../../static/images/jhnokese.png" mode=""></image> -->
						<view class="tapTextBot">
							不合适
						</view>
					</view>
					<!-- <view class="" v-if="compneyInfo.isEntrust==false">
						<image v-if="users.state==1" src="../../static/images/jhesdsssdd.png" mode="" @click="inviteInterview"></image>
						<image v-else src="../../static/images/jheedfaaas.png" mode=""></image>
						<view class="tapTextBot">
							邀面试
						</view>
					</view> -->
					<view class="">
						<image v-if="users.state==3||users.state==4" src="../../static/images/jsinjobt.png" mode="" @click="hasSuccess">
						</image>
						<image v-else src="../../static/images/jsinjobdid.png" mode=""></image>
						<view class="tapTextBot">
							准入职
						</view>
					</view>
					<view class="">
						<button class="openType" type="default" open-type="contact">
							<image src="../../static/images/jssayline.png" mode=""></image>
							<view class="tapTextBot">
								在线沟通
							</view>
						</button>

					</view>
				</view>
			</view>
		</view>
		<!-- 再次确认不合适 -->
		<uni-popup ref="popup" type="dialog">
			<uni-popup-dialog mode='base' type="info" message="成功消息" content="请确定是否将该人才标记为不合适？" @confirm="confirm">
			</uni-popup-dialog>
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
						<view class="sendBtn" @click="quitJob">
							确认离职
						</view>
					</view>
				</view>
			</view>
		</uni-popup>
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
						<view class="sendBtn" @click="subType(7)">
							确认入职
						</view>
					</view>
				</view>
			</view>
		</uni-popup>

		<!-- 邀请面试 -->
		<!-- <uni-popup ref="inviteBook" type="bottom">
			<view class="inviteBox">
				<view class="inviteTitle">
					面试邀请 <uni-icons @click="closeInvite" class="closeIcon" type="closeempty" size="20"></uni-icons>
				</view>
				<view class="inviteContent">
					<view class="row">
						<view class="col_3">
							联系电话
						</view>
						<view class="col_9">
							<input type="text" v-model="phones" />
						</view>
					</view>

					<view class="row">
						<view class="col_3">
							面试日期
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
							面试地点
						</view>
						<view class="col_9">
							<input type="text" v-model="adres" />
						</view>
					</view>
					<view class="row">
						<view class="col_3">
							面试备注
						</view>
						<view class="col_9">
							<input type="text" v-model="remark" placeholder="请输入面试备注" />
						</view>
					</view>
					<view class="btnRow">
						<view class="sendBtn" @click="subType(2)">
							发送面试邀请
						</view>
					</view>
				</view>
			</view>
		</uni-popup> -->
	</view>
</template>

<script>
	import navBarTop from "../../components/nav-bar-top/nav-bar-top.vue"
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import uniPopupMessage from '@/components/uni-popup/uni-popup-message.vue'
	import uniPopupDialog from '@/components/uni-popup/uni-popup-dialog.vue'
	import {
		vpnUserDeliverDetailAddsvc,
		vpncompanydeliverDetailGetsvc,
		vpncompanydeliverUserDetailGetsvc,
		vpnDeliverDetailAddsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				time: '请选择时间',
				devId: '',
				listCon: '',
				users: '',
				compneyInfo: '',
				adres: '',
				phones: '',
				remark: ''
			};
		},
		components: {
			navBarTop,
			uniPopup,
			uniPopupMessage,
			uniPopupDialog
		},
		filters: {
			flagName(val) {
				let statet = ['未选择', '同意', '不同意', '已失效'];
				return statet[val]
			},
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
		onLoad(optins) {
			this.devId = optins.devId;
			let compeny = getApp().globalData.compenyInfo;
			this.compneyInfo = compeny;
			this.adres = compeny.adress;
			this.phones = compeny.phone;
			this.initAjax();
			this.getUser();
		},
		methods: {
			quitJob(){
				let that=this;
				let data = {
					vpnDeliverDetailAdd: {
						devId: this.devId,
						state: 9,
						leaveTime: this.time,
						resion:this.remark,
					}
				}
				vpnUserDeliverDetailAddsvc(data).then(res=>{
					if(res.code==1){
						that.$refs.inJobNoOk.close();
						uni.showToast({
							title: '离职成功',
							duration: 1500,
							icon: 'none'
						})
						setTimeout(()=>{
							that.initAjax();
							that.getUser();
						},1500)
					}else{
						uni.showToast({
							title:res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			subType(type) {
				let that = this;
				let data = '';
				if (type == 2) {
					data = {
						vpnDeliverDetailAdd: {
							devId: that.devId,
							adress: that.adres,
							remark: that.remark,
							phone: that.phones,
							time: that.time,
							state: 2
						}
					}
				} else {
					data = {
						vpnDeliverDetailAdd: {
							devId: that.devId,
							entryTime: that.time,
							state: 7
						}
					}
				}
				vpnDeliverDetailAddsvc(data).then(res => {
					if (res.code == 1) {
						that.getUser();
						this.$refs.hasSuccess.close();
						let messages = '';
						if (type == 2) {
							messages = '已成功邀约'
						} else {
							messages = '已成功入职'
						}
						uni.showToast({
							title: messages,
							duration: 1500,
							icon: 'none'
						})
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 不合适
			confirm() {
				let that = this;
				let data = {
					vpnDeliverDetailAdd: {
						devId: that.devId,
						state: 0
					}
				}
				vpnDeliverDetailAddsvc(data).then(res => {
					if (res.code == 1) {
						that.$refs.popup.close()
						uni.showToast({
							title: '成功拒绝面试者',
							duration: 1500,
							icon: 'none'
						})
					} else {
						let msge = ""
						if (res.msg == "失败") {
							msge = "您已经拒绝过他了，不能更残忍的再拒绝一次"
						}
						uni.showToast({
							title: msge,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 跳转到简历
			goTo(id) {
				uni.navigateTo({
					url: '../../pagesA/previewResume/previewResume?userid=' + id
				})
			},
			// 获取用户信息
			getUser() {
				let that = this;
				let data = {
					id: that.devId
				}
				vpncompanydeliverUserDetailGetsvc(data).then(res => {
					console.log(res)
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
			// 获取进展
			initAjax() {
				let that = this;
				let data = {
					id: that.devId
				}
				vpncompanydeliverDetailGetsvc(data).then(res => {
					if (res.code == 1) {
						console.log(res)
						that.listCon = res.data.vpndeliverGetResp
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},

			// 再次确认不合适
			sureAgain() {
				if (this.users.state == 7) {
					this.$refs.inJobNoOk.open()
				} else {
					this.$refs.popup.open();
				}
			},
			// 邀请面试
			inviteInterview() {
				this.$refs.inviteBook.open()
			},
			// 关闭面试
			closeInvite() {
				this.$refs.inviteBook.close()
			},
			// 切换时间
			bindTimeChange: function(e) {
				this.time = e.target.value
			},
			// 入职
			hasSuccess: function() {
				this.$refs.hasSuccess.open();
			}
		}
	}
</script>

<style lang="less">
	.oneItem {
		margin: 60rpx 25rpx 0;
		min-height: 258rpx;
		background: #FFFFFF;
		border-radius: 10rpx;
		padding: 30rpx 30rpx 20rpx;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
		position: relative;

		.itemLeft {
			width: 120rpx;
			display: inline-block;
			vertical-align: top;
		}

		.itemRight {
			width: 520rpx;
			display: inline-block;
		}

		.pepole {
			display: flex;
			justify-content: space-between;

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

	.seeBook {
		width: 145rpx;
		height: 69rpx;
	}

	.headImg {
		width: 102rpx;
		height: 102rpx;
		border-radius: 102rpx;
	}

	/*cardbox*/
	.cardBox {
		padding: 30rpx;
		margin: 25rpx;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
		flex: 1;
		overflow-y: auto;
		min-height: 100rpx;
	}

	.blueText {
		font-size: 32rpx;
		font-weight: 500;
		color: #2E73FF;
		padding-top: 10rpx;
	}

	.cardTitle {
		font-size: 30rpx;
		font-weight: bold;
		margin-bottom: 20rpx;
	}

	.cardBot {
		display: flex;
		justify-content: space-between;
		padding: 0 0;
		// line-height: 50rpx;
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
		padding: 10rpx 0 10rpx
	}

	// foot
	.footBox {
		width: 100%;
		height: 160rpx;

		.inFootBox {
			position: absolute;
			bottom: 0;
			left: 0;
			width: 100%;
			height: 110rpx;
			background: linear-gradient(0deg, #FFFFFF, #E6F2FF);

			.footTapBox {
				display: flex;
				margin-top: -50rpx;
			}

			.footTapBox>view {
				flex: 1;
				text-align: center;
				font-size: 26rpx;
				font-weight: bold;
				color: #1B1B1B;

				image {
					width: 100rpx;
					height: 100rpx;
				}
			}
		}
	}

	// popBox
	.inviteBox {
		background: #FFFFFF;
		height: 740rpx;
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
</style>
