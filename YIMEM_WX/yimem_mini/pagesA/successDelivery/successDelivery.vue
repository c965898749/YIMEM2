<template>
	<view class="content">
		<view class="">
			<view class="imgBox">
				<image src="../../static/images/success.png" mode=""></image>
			</view>
			<view class="successed">
				投递成功
			</view>
			<view class="msg">
				可在个人中心-我的应聘中查看已投递的职位
			</view>
			<view class="msg">
				{{timeNum}}秒后自动返回详情页...
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				timeNum: 10,
				timeObj: ''
			};
		},
		onShow() {
			this.timeLes();
		},
		onUnload() {
			clearInterval(this.timeObj)
		},
		methods: {
			timeLes(clean) {
				let that = this;
				that.timeObj = setInterval(() => {
					this.timeNum = this.timeNum - 1;
					if (this.timeNum == 0) {
						clearInterval(that.timeObj);
						that.back()
					}
				}, 1000)
			},
			back() {
				uni.switchTab({
					url: '../../pages/index/index'
				})
			}
		}
	}
</script>

<style lang="less">
	.imgBox {
		text-align: center;
		margin-top: 40rpx;
	}

	.imgBox image {
		width: 312rpx;
		height: 296rpx;
	}

	.successed {
		color: #2E73FF;
		font-size: 32rpx;
		font-weight: bold;
		text-align: center;
		padding: 40rpx 0 20rpx;
	}

	.msg {
		font-size: 24rpx;
		color: #919191;
		line-height: 38rpx;
		text-align: center;
	}
</style>
