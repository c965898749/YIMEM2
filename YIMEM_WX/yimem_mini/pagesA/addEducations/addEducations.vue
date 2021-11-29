<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					毕业学校
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" v-model="schoolt" placeholder="请输入您毕业学校">
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					入学时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" :start="startDate" :end="endDate" data-dates="start" @change="bindDateChange">
							<input type="text" disabled v-model="startTime" placeholder="请输入您入学时间">
						</picker>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					毕业时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" :start="startDate" :end="endDate" data-dates='ended' @change="bindDateChange">
							<input type="text" disabled v-model="endTime" placeholder="请输入您毕业时间">
						</picker>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					学历
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_education" range-key="cName" data-kind='QS_trade' @change="pickChange">
							<input type="text" disabled class="picker" :value="educationName" placeholder="请选择您的学历" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					所学专业
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" disabled :value="checkMajor.label" placeholder="请选择您的专业" @click="this.showTree=true">
					</view>
				</view>
				<view class="" v-if="showTree">
					<ly-tree :tree-data="treeData" :defaultCheckedKeys="defaultId" :ready="ready" :showRadio='true' node-key="id"
					 @check='checkKey'>
					</ly-tree>
				</view>
			</view>
		</view>
		<view class="btnRow">
			<button class="subAdd" @click="subAdd">提交</button>
		</view>
	</view>
</template>

<script>
	import {
		vpnqsCategoryGetsvc,
		vpnQsCategoryMajorGetsvc,
		vpneducationExpAddsvc,
		vpneducationExpUpdatesvc
	} from '../../api/index.js'
	import LyTree from '@/components/ly-tree/ly-tree.vue'
	export default {
		data() {
			return {
				QS_education: [],
				educationName: '',
				educationCode: '',
				schoolt: '',
				ready: false, // 这里用于自主控制loading加载状态，避免异步正在加载数据的空档显示“暂无数据”
				showTree: false,
				treeData: [],
				checkMajor: {}, //选中的专业对象
				startDate: "1920-12-01",
				endDate: '2120-12-01',
				startTime: '',
				endTime: '',
				userId: '',
				defaultId: '',
				editeId: '' //编辑的id
			}
		},
		onShow() {
			this.getEducationsAjax();
			this.getTree();
			this.userId = getApp().globalData.userId;
			let editObj = uni.getStorageSync('oneEducation');
			if (editObj.id) {
				this.startTime = editObj.startTime;
				this.endTime = editObj.endTime;
				this.schoolt = editObj.school;
				this.defaultId = editObj.major;
				this.editeId = editObj.id;
				this.educationCode = editObj.education;
				this.educationName = editObj.educationName;
				this.checkMajor = {
					id: editObj.major,
					label: editObj.majorName
				}
			}
			uni.setStorageSync('oneEducation', '');
		},
		components: {
			LyTree
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
			// 获取学历列表
			getEducationsAjax() {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: 'QS_education'
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						that.QS_education = res.data.vpnqsCategoryGetResp;
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 选择学历
			pickChange(e) {
				let val = e.detail.value;
				this.educationCode = this.QS_education[val].cId;
				this.educationName = this.QS_education[val].cName;
			},
			// 获取ztrr数据
			getTree() {
				let that = this;
				let data = {}
				vpnQsCategoryMajorGetsvc(data).then(res => {
					this.ready = true;
					if (res.code == 1) {
						that.treeData = res.data.vpnqsCategoryJobsGetResp;
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 提交
			subAdd() {
				let that = this;
				 if (!(that.schoolt && that.educationCode && that.checkMajor.id && that.startTime && that.endTime)) {
				 	uni.showToast({
						title: "请检查是否已全部填写！",
				 		duration: 1500,
				 		icon: "none"
				 	})
				 	return false
				 }
				if (that.editeId) {
					let data = {
						vpneducationExpUpdate: {
							id: that.editeId,
							userId: that.userId,
							school: that.schoolt,
							education: that.educationCode,
							major: that.checkMajor.id,
							startTime: that.startTime,
							endTime: that.endTime
						}
					}
					vpneducationExpUpdatesvc(data).then(res => {
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
				} else {
					let data = {
						vpneducationExpAdd: {
							userId: that.userId,
							school: that.schoolt,
							education: that.educationCode,
							major: that.checkMajor.id,
							startTime: that.startTime,
							endTime: that.endTime
						}
					}
					vpneducationExpAddsvc(data).then(res => {
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
			// 选取
			checkKey(nodes) {
				this.checkMajor = nodes.data;
				this.showTree = false
			}
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
</style>
