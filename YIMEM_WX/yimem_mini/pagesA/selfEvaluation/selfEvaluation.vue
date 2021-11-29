<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					自我评价
				</view>
				<view class="inputRow row">
					<textarea value="" class="remarkClass" v-model="remark" placeholder="请输入自我评价" />
					</view>
			</view>
		</view>
		<view class="btnRow">
			<button class="subAdd" @click="subAdd">提交</button>
		</view>
	</view>
</template>

<script>
	import {vpnuserUpdatesvc } from '../../api/index.js'
	export default {
		data() {
			return {
				remark:'',
				userId:''
			}
		},
		onShow() {
			this.userId=getApp().globalData.userId;
		},
		methods: {
			subAdd(){
				let that=this;
				if (!(that.remark)) {
					uni.showToast({
						title: "请检查是否已全部填写！",
						duration: 1500,
						icon: "none"
					})
				}
				let data = {
					vpnuserUpdate: {
						userId: that.userId,
						remark:that.remark
					}
				}
				vpnuserUpdatesvc(data).then(res => {
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
		// border-bottom: 1rpx solid #F2F2F2;
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

