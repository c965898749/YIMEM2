<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					公司名称
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" v-model="company" placeholder="公司名称">
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					职位名称
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" v-model="position" class="picker" placeholder="请输入职位名称" />
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					薪资
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" v-model="salary" placeholder="请输入薪资">
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					入职时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" :start="startDate" :end="endDate" data-dates="start" @change="bindDateChange">
							<input type="text" v-model="startTime" placeholder="请输入您入职时间">
						</picker>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					离职时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" :start="startDate" :end="endDate" data-dates='ended' @change="bindDateChange">
							<input type="text" v-model="endTime" placeholder="请输入您离职时间">
						</picker>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					备注
				</view>
				<view class="inputRow row">
					<textarea value="" class="remarkClass" v-model="remark" placeholder="请输入备注" />
					</view>
			</view>
		</view>
		<view class="btnRow">
			<button class="subAdd" @click="subAdd">提交</button>
		</view>
	</view>
</template>

<script>
	import {vpnjobExpAddsvc,vpnjobExpUpdatesvc} from '../../api/index.js'
	export default {
		data() {
			return {
				startDate: "1920-12-01",
				endDate: '2120-12-01',
				startTime: '',
				endTime: '',
				userId: '',
				company:'',
				position:'',
				salary:'',
				remark:'',
				editeId:''//需要编辑的id
			}
		},
		onShow() {
			this.userId=getApp().globalData.userId;
			let editeObj=uni.getStorageSync('oneEducation')
			if(editeObj.id){
				this.startTime= editeObj.startTime;
				this.endTime= editeObj.endTime;
				this.company=editeObj.company;
				this.position=editeObj.position;
				this.salary=editeObj.salary;
				this.remark=editeObj.remark;
				this.editeId=editeObj.id;
			}
			uni.setStorageSync('oneEducation','');
		},
		methods: {
			// 选择时间
			bindDateChange(e) {
				let that = this;
				let dates = e.currentTarget.dataset.dates;
				if (dates == 'start') {
					that.startTime = e.detail.value;
				} else if (dates == "ended") {
					that.endTime = e.detail.value;
				}
			},
			// 提交
			subAdd() {
				let that = this;
			    if (!(that.company&&that.position&&that.salary&&that.startTime && that.endTime)) {
				 	uni.showToast({
				 		title: "请检查是否已全部填写！",
				 		duration: 1500,
				 		icon: "none"
				 	})
				 	return false
				}
				if(that.editeId){
					let data = {
						vpnjobExpUpdate: {
							id:that.editeId,
							userId: that.userId,
							company: that.company,
							position: that.position,
							remark: that.remark,
							salary:that.salary,
							startTime: that.startTime,
							endTime: that.endTime
						}
					}
					vpnjobExpUpdatesvc(data).then(res => {
						if (res.code == 1) {
							uni.showToast({
								title: res.msg,
								duration: 1500,
								icon: 'none'
							})
							setTimeout(() => {
								uni.navigateBack({
									delta: 1
								})
							}, 1500)
						} else {
							uni.showToast({
								title: res.msg,
								duration: 1500,
								icon: 'none'
							})
						}
					})
				}else{
					let data = {
						vpnjobExpAdd: {
							userId: that.userId,
							company: that.company,
							position: that.position,
							remark: that.remark,
							salary:that.salary,
							startTime: that.startTime,
							endTime: that.endTime
						}
					}
					vpnjobExpAddsvc(data).then(res => {
						if (res.code == 1) {
							uni.showToast({
								title: res.msg,
								duration: 1500,
								icon: 'none'
							})
							setTimeout(() => {
								uni.navigateBack({
									delta: 1
								})
							}, 1500)
						} else {
							uni.showToast({
								title: res.msg,
								duration: 1500,
								icon: 'none'
							})
						}
					})
				}
			},
			
		}
	}
</script>

<style lang="less">
	.formBox {
		padding: 26rpx;
		height: calc(100% - 100rpx);
		overflow-y: auto;
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

	.btnRow {
		padding: 10rpx 30rpx;
		position: fixed;
		left: 0;
		bottom: 0;
		width: 100%;
	}

	.subAdd {
		height: 80rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
		border-radius: 40rpx;
		line-height: 80rpx;
		color: #FFFFFF;
		font-size: 30rpx;
		text-align: center;
	}
	.remarkClass{
		width: 100%;
	}
</style>
