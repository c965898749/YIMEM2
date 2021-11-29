<template>
	<view class="content">
		<view class="b_t">请选择您的身份</view>
		<view class="s_t">您可以在“我的”中进行切换</view>
		<view class="tab">
			<view class="item" @click="changeType" data-type='1'>
				<image src="../../static/images/position.png" mode="widthFix"></image>
			</view>
			<view class="item" @click="changeType" data-type='3'>
				<image src="../../static/images/resume.png" mode="widthFix"></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				compenyState: '',
			};
		},
		onShow() {
			this.compenyState = getApp().globalData.compenyState;
		},
		methods: {
			changeType: function(e) {
				let type = e.currentTarget.dataset.type;
				let compenyState = getApp().globalData.compenyState; //1待审核 2通过 0不通过
				let compenyInfo=getApp().globalData.compenyInfo;
				if (type == 3) {
					if(!compenyInfo){
						uni.showModal({
							title: '提示',
							content: '您还没有注册公司，是否先去注册您的公司',
							success: function(res) {
								if (res.confirm) {
									uni.redirectTo({
										url: '../company/company'
									})
								}
							}
						});
						return false;
					}
					if (compenyState == 0) {
						uni.showModal({
							title: '提示',
							content: '您公司审核不通过，请重新提交公司信息',
							success: function(res) {
								if (res.confirm) {
									uni.redirectTo({
										url: '../company/company'
									})
								}
							}
						});
					} else if (compenyState == 1) {
						uni.showToast({
							title:'您公司正在审核中，请耐心等待。。。',
							icon:"none",
							duration:1500
						})
					} else {
						getApp().globalData.userState = type;
						uni.navigateBack({
							delta: 1
						})
					}
				} else {
					getApp().globalData.userState = type;
					uni.navigateBack({
						delta: 1
					})
				}
			}
		}
	}
</script>

<style lang="less">
	.content {
		font-size: 30rpx;
		padding: 36rpx;
	}

	.b_t {
		font-size: 48rpx;
		text-align: center;
	}

	.s_t {
		font-size: 26rpx;
		color: #999;
		font-size: 26rpx;
		text-align: center;
		margin-top: 18rpx;
	}

	.tab {
		height: auto;
		margin-top: 66rpx;
		;

		.item {
			height: auto;
			padding: 20rpx 0rpx;

			image {
				width: 100%;
				display: block;
			}
		}
	}
</style>
