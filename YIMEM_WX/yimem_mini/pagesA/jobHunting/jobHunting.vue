<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					行业领域
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_trade" range-key="cName" data-kind='QS_trade' @change="pickChange">
							<input type="text" disabled class="picker" :value="jsonPost.hyName" placeholder="请选择行业领域" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<!-- TODO期望职位 -->
			<view class="inputBox">
				<view class="inputName">
					期望职位
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<view class="picker" @click="goPage" data-urlp="../treeJob/treeJob">
							<text v-if="jobKind.id">{{jobKind.label}}</text><text v-else>请选择期望职位</text>
						</view>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					期望月薪
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_wage" range-key="cName" data-kind='QS_wage' @change="pickChange">
							<input type="text" disabled class="picker" :value="jsonPost.yxName" placeholder="请选择期望月薪" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					工作地点
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="region" @change="bindRegionChange">
							<view class="picker" v-if="regionCode[0]==null">
								请选择工作地点
							</view>
							<view class="picker" v-else>
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
					工作性质
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_jobs_nature" range-key="cName" data-kind='QS_jobs_nature' @change="pickChange">
							<input type="text" class="picker" disabled :value="jsonPost.jzName" placeholder="请选择工作性质" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					工作状态
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_current" range-key="cName" data-kind='QS_current' @change="pickChange">
							<input type="text" disabled class="picker" :value="jsonPost.ztName" placeholder="请选择工作状态" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
		</view>
		<view class="saveBox">
			<image class="saveImg" src="../../static/images/jssave.png" mode="" @click="subJob"></image>
		</view>
	</view>
</template>

<script>
	import {
		vpnqsCategoryGetsvc,
		vpnjobWantedAddsvc
	} from "../../api/index.js"
	export default {
		data() {
			return {
				jsonPost: {
					hyName: '',
					hyCode: '',
					yxName: '',
					yxCode: '',
					ztName: '',
					ztCode: '',
					jzName: '',
					jzCode: ''
				},
				QS_trade: [], //行业分类
				QS_wage: [], //月薪分类
				QS_current: [], //目前状态分类
				QS_jobs_nature: [], //是兼职全职？
				region: [],
				regionCode: [],
				userId: '',
				jobKind: '',
				oneEducation: ''
			};
		},
		onLoad() {
			this.userId = getApp().globalData.userId;
			this.forGetAll();
			let oneEducation = uni.getStorageSync('oneEducation');
			uni.setStorageSync('oneEducation', '')
			if (oneEducation != '' && oneEducation.id) {
				this.oneEducation = oneEducation;
				this.initJob();
			}

		},
		onShow() {
			let jobKind = uni.getStorageSync('checkJob');
			uni.setStorageSync('checkJob', '')
			if (jobKind != '') {
				this.jobKind = jobKind;
			}

		},
		methods: {
			initJob() {
				let jobwant = this.oneEducation;
				let jsonClone = {
					hyName: jobwant.businessName,
					hyCode: jobwant.businessId,
					yxName: jobwant.expectedSalaryName,
					yxCode: jobwant.expectedSalaryId,
					ztName: jobwant.stateName,
					ztCode: jobwant.state,
					jzName: jobwant.propertyName,
					jzCode: jobwant.property,
					region: [jobwant.provinceName, jobwant.cityName, jobwant.countryName],
					regionCode: [jobwant.provinceId, jobwant.cityId, jobwant.countryId]
				};
				this.jobKind = {
					label: jobwant.jobCateName,
					id: jobwant.jobCateId
				};
				this.jsonPost = jsonClone;
			},
			goPage(e) {
				let url = e.currentTarget.dataset.urlp
				uni.navigateTo({
					url: url
				})
			},
			// 获取基础数据的请求
			getAllajax(otherName) {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: otherName
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						let resData = res.data.vpnqsCategoryGetResp
						if (otherName == 'QS_trade') {
							that.QS_trade = resData
						} else if (otherName == 'QS_wage') {
							that.QS_wage = resData
						} else if (otherName == 'QS_current') {
							that.QS_current = resData;
						} else if (otherName == 'QS_jobs_nature') {
							that.QS_jobs_nature = resData;
						}
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 循环获取基础数据
			forGetAll() {
				let that = this;
				let allWant = ['QS_trade', 'QS_wage', 'QS_current', 'QS_jobs_nature'];
				allWant.map(item => {
					that.getAllajax(item)
				})
			},
			// 目标居住地
			bindRegionChange(e) {
				this.region = e.detail.value
				this.regionCode = e.detail.code
			},
			// picker的Change
			pickChange(e) {
				let that = this;
				let kindType = e.currentTarget.dataset.kind;
				let val = e.detail.value;
				if (kindType == "QS_trade") {
					let qsTrade = that.QS_trade;
					that.jsonPost.hyName = qsTrade[val].cName;
					that.jsonPost.hyCode = qsTrade[val].cId;
				} else if (kindType == "QS_current") {
					let qsCurrent = that.QS_current;
					that.jsonPost.ztName = qsCurrent[val].cName;
					that.jsonPost.ztCode = qsCurrent[val].cId;
				} else if (kindType == "QS_wage") {
					let qsWage = that.QS_wage;
					that.jsonPost.yxName = qsWage[val].cName;
					that.jsonPost.yxCode = qsWage[val].cId;
				} else if (kindType == 'QS_jobs_nature') {
					let qsJobNature = that.QS_jobs_nature;
					that.jsonPost.jzName = qsJobNature[val].cName;
					that.jsonPost.jzCode = qsJobNature[val].cId;
				}

			},
			// 提交求职意向
			subJob() {
				let that = this;
				/* if (!(that.regionCode.length>0 && that.jsonPost.hyName && that.jsonPost.yxName && that.jsonPost.hyCode && that.jsonPost.yxCode &&
				 		that.jsonPost.ztCode && that.jsonPost.jzCode && that.jobKind.id && that.jsonPost.jzName && that.jsonPost.ztName)) {
				 	uni.showToast({
				 		title: '请检查都已填！',
				 		icon: 'none',
				 		duration: 1500
				 	})
				 	return false
				 } */
				 if (!(that.jsonPost.hyCode && that.jsonPost.yxCode && that.jsonPost.ztCode && that.jsonPost.jzCode && that.jobKind.id )) {
				  	uni.showToast({
				  		title: '请检查都已填！',
				  		icon: 'none',
				  		duration: 1500
				  	})
				  	return false
				  }
				let data = {
					vpnjobWantedAdd: {
						userId: that.userId,
						jobCateId: that.jobKind.id,
						provinceId: that.regionCode[0],
						cityId: that.regionCode[1],
						countryId: that.regionCode[2],
						property: that.jsonPost.jzCode,
						state: that.jsonPost.ztCode,
						businessId: that.jsonPost.hyCode,
						expectedSalaryId: that.jsonPost.yxCode
					}
				}
				vpnjobWantedAddsvc(data).then(res => {
					if (res.code == 1) {
						uni.showToast({
							title: '求职意向修改成功',
							icon: 'none',
							duration: 1500
						})
						setTimeout(() => {
							uni.navigateBack({
								delta: 1
							})
						}, 1500)
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.formBox {
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

	.sexBox text {
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
</style>
