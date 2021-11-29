<template>
	<view class="content">
		<view class="basicBox">
			<view class="inputBox">
				<view class="inputName">
					真实姓名
				</view>
				<view class="inputRow">
					<input type="text" v-model="userName" placeholder="请输入姓名" />
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					性别
				</view>
				<view class="inputRow sexBox">
					<text @click="checkSex(1)" :class="['sexOne',sexIndex==1?'checkOne':'']">男</text>
					<text @click="checkSex(0)" :class="['sexOne',sexIndex==0?'checkOne':'']">女</text>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					出生年月
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" @change="birthChange">
							<input class="uni-input" type="text" disabled :value="birthDay" placeholder="请输入您的生日" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					参加工作时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" @change="workChange">
							<input class="uni-input" type="text" disabled :value="workDay" placeholder="请输入您的生日" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					现居住城市
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="region" @change="bindRegionChange" >
							<view class="" v-if="regionCode[0]==null||regionCode[0]==undefined">
								请选择现居住城市
							</view>
							<view v-else class="uni-input">
								{{region[0]}}-{{region[1]}}-{{region[2]}}
							</view>
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					户籍所在地
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="region" @change="bindRegionChangeOld" >
							<view class="uni-input" v-if="oldRegionCode[0]==null||oldRegionCode[0]==undefined">
								请选择户籍所在地
							</view>
							<view v-else class="uni-input">
								{{oldRegion[0]}}-{{oldRegion[1]}}-{{oldRegion[2]}}
							</view>
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					联系号码
				</view>
				<view class="inputRow">
					<input type="text" v-model="phoneNum" placeholder="请输入联系号码" />
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					电子邮箱
				</view>
				<view class="inputRow">
					<input type="text" v-model="emails" placeholder="请输入电子邮箱" />
				</view>
			</view>
		</view>
		<view class="saveBox">
			<image class="saveImg" @click="subAjax" src="../../static/images/jssave.png" mode=""></image>
		</view>
	</view>
</template>

<script>
	import {
		vpnuserUpdatesvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				userName: '',
				phoneNum: '',
				emails: '',
				birthDay: '请选择出生年月',
				workDay: '请选择参加工作时间',
				region: [],
				regionCode: [],
				oldRegion: [],
				oldRegionCode: [],
				userInfo: '',
				userId: "",
				sexIndex:0,
			};
		},
		onShow() {
			this.userInfo = getApp().globalData.userInfo;
			this.userId = getApp().globalData.userId;
			let resumeInfo=uni.getStorageSync('resumeDetail');
			this.initResume(resumeInfo);
		},
		methods: {
			// 选择性别
			checkSex(sex){
				this.sexIndex=sex;

			},
			// 初始化个人信息
			initResume(res){
				this.userName=res.userName || '';
				this.phoneNum=res.phoneNum|| '';
				this.emails=res.emil|| '';
				this.birthDay=res.birthday|| '';
				this.workDay=res.jionJobTime|| '';
				this.regionCode=[res.provinceId,res.cityId,res.countryId] || [];
				this.region=[res.provinceName,res.cityName,res.countryName]|| [];
				this.oldRegion=[res.censusProvinceName,res.censusCityName,res.censusCountryName] || [];
				this.oldRegionCode=[res.censusProvinceId,res.censusCityId,res.censusCountryId]|| [];
				this.sexIndex=res.userSex|| 0;
			},
			// 生日选择
			birthChange(e) {
				this.birthDay = e.detail.value
			},
			// 工作日期
			workChange(e) {
				this.workDay = e.detail.value
			},
			// 现在居住地
			bindRegionChange(e) {
				this.region = e.detail.value
				this.regionCode = e.detail.code
			},
			// 祖籍
			bindRegionChangeOld(e) {
				this.oldRegion = e.detail.value
				this.oldRegionCode = e.detail.code
			},

			subAjax() {
				let that = this;
				/* if (!(that.userName && that.birthDay && that.workDay && that.emails && that.regionCode.length > 0 && that.oldRegionCode
						.length > 0)) {
					uni.showToast({
						title: "请检查是否都已填",
						icon: 'none',
						duration: 1500
					})
					return false
				} */
				if (!(that.userName && that.phoneNum)) {
					uni.showToast({
						title: "姓名及联系号码必填",
						icon: 'none',
						duration: 1500
					})
					return false
				}
				if (!(/^1[34578]\d{9}$/.test(that.phoneNum))) {
					uni.showToast({
						title: "请填写正确的手机号码",
						icon: 'none',
						duration: 1500
					})
					return false
				}


				let data = {
					vpnuserUpdate: {
						userId: that.userId,
						userName: that.userName,
						userSex: that.sexIndex,
						birthday: that.birthDay,
						jionJobTime: that.workDay,
						phoneNum: that.phoneNum,
						emil: that.emails,
						countryId: that.regionCode[2],
						cityId: that.regionCode[1],
						provinceId: that.regionCode[0], //现居
						censusCountryId: that.oldRegionCode[2],
						censusCityId: that.oldRegionCode[1],
						censusProvinceId: that.oldRegionCode[0],
					}
				}
				vpnuserUpdatesvc(data).then(res => {
					if (res.code == 1) {
						uni.navigateBack({
							delta: 1
						})
					} else {

					}
				})
			}
		}
	}
</script>

<style lang="less">
	.basicBox {
		padding: 26rpx;
	}

	.inputBox {
		border-bottom: 1rpx solid #F2F2F2;
		margin-bottom: 30rpx;

		.inputName {
			font-size: 22rpx;
			color: #1B1B1B;
			margin-bottom: 30rpx;
		}

		.inputRow {
			margin-bottom: 30rpx;
			font-size: 30rpx;
			color: #919191;
		}
	}

	.sexOne {
		display: inline-block;
		width: 150rpx;
		height: 50rpx;
		text-align: center;
		line-height: 50rpx;
		background: #F5F5F5;
		border-radius: 2rpx;
		font-size: 22rpx;
		font-weight: 400;
		color: #1B1B1B;
		margin-right: 20rpx;
	}

	.row .col_1 {
		text-align: right;
	}

	.saveBox {
		text-align: center;
	}

	.saveImg {
		width: 741rpx;
		height: 131rpx;
	}

	.uni-input {
		height: 50rpx;
	}

	.checkOne {
		background: #E7EFFF;
		color: #2E73FF;
		border: 1px solid #2E73FF;
	}
</style>
