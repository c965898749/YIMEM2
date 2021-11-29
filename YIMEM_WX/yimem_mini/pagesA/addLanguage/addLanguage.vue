<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					语言种类
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_language" range-key="cName" data-kind='QS_language' @change="pickChange">
							<input type="text" disabled class="picker" :value="jsonPost.langName" placeholder="请选择语言种类" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					语言听说能力
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_language_level" range-key="cName" data-kind='QS_language_level' @change="pickChangelsLv">
							<input type="text" disabled class="picker" :value="jsonPost.slangName" placeholder="请选择语言听说能力" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					语言读写能力
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="selector" :range="QS_language_level" range-key="cName" data-kind='QS_language_level' @change="pickChangewwLv">
							<input type="text" disabled class="picker" :value="jsonPost.rlangName"
							 placeholder="请选择语言读写能力" />
						</picker>
					</view>
					<view class="col_1">
						<uni-icons type="forward" color='#7E7E7E' size="20"></uni-icons>
					</view>
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
		vpnlanguageAddsvc,
		vpnlanguageUpdatesvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				QS_language: [],
				QS_language_level: [],
				jsonPost:{
					langName:'',
					langCode:'',
					slangName:'',
					slangCode:'',
					rlangName:'',
					rlangCode:''
				},
				userId: '',
				editeId:''
			};
		},
		onShow() {
			this.userId = getApp().globalData.userId;
			this.getLanguageAjax();
			this.getLanguageLvAjax();
			let editObj = uni.getStorageSync('oneEducation');
			if (editObj.id) {
				let jsons={
					langName:editObj.languageName,
					langCode:editObj.language,
					slangName:editObj.speakAbilityName,
					slangCode:editObj.speakAbility,
					rlangName:editObj.writeAbilityName,
					rlangCode:editObj.writeAbility
				}
				this.jsonPost=jsons;
				this.editeId = editObj.id;
			}
			uni.setStorageSync('oneEducation', '');
		},
		methods: {
			// 获取语言列表
			getLanguageAjax() {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: 'QS_language'
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						that.QS_language = res.data.vpnqsCategoryGetResp;
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 获取语言等级列表
			getLanguageLvAjax() {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: 'QS_language_level'
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						that.QS_language_level = res.data.vpnqsCategoryGetResp;
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 语言种类
			pickChange(e) {
				let val = e.detail.value;
				this.jsonPost.langName=this.QS_language[val].cName;
				this.jsonPost.langCode=this.QS_language[val].cId;
			},
			// 语言听说等级
			pickChangelsLv(e) {
				let val = e.detail.value;
				this.jsonPost.slangCode=this.QS_language_level[val].cId;
				this.jsonPost.slangName=this.QS_language_level[val].cName;
			},
			// 语言读写等级
			pickChangewwLv(e) {
				let val = e.detail.value;
				this.jsonPost.rlangCode=this.QS_language_level[val].cId;
				this.jsonPost.rlangName=this.QS_language_level[val].cName;
			},
			// 提交
			subAdd() {
				let that = this;
				if (!(that.jsonPost.langCode && that.jsonPost.slangCode && that.jsonPost.rlangCode)) {
					uni.showToast({
						title: "请检查是否已全部填写！",
						duration: 1500,
						icon: "none"
					})
					return false
				}
				if (that.editeId) {
					let data = {
						vpnlanguageUpdate: {
							id: that.editeId,
							userId: that.userId,
							language: that.jsonPost.langCode,
							speakAbility: that.jsonPost.slangCode,
							writeAbility: that.jsonPost.rlangCode,
						}
					}
					vpnlanguageUpdatesvc(data).then(res => {
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
						vpnlanguageAdd: {
							userId: that.userId,
							language: that.jsonPost.langCode,
							speakAbility: that.jsonPost.slangCode,
							writeAbility: that.jsonPost.rlangCode,
						}
					}
					vpnlanguageAddsvc(data).then(res => {
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

	.remarkClass {
		width: 100%;
	}
</style>
