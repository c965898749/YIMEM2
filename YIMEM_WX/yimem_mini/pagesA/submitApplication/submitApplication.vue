<template>
	<view class="content">
		<view class="">
			<view class="imgBox">
				<!-- 图片要换 -->
				<image src="../../static/images/havesent.png" mode=""></image>
			</view>
			<view class="blueText">
				提现申请提交成功
			</view>
			<view class="litText">
				感谢您的支持，审核后会尽快
			</view>
			<view class="litText">
				将钱打入您的微信钱包中，请耐心等待～
			</view>
			<view class="btn-group">
				<button class="blueBtn" @click="finishs">知道了</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			finishs(){
				uni.navigateBack({
					delta:1
				})
			}
		}
	}
</script>

<style lang="less">
.imgBox{
	margin-top: 30rpx;
	text-align: center;
	image{
		width: 330rpx;
		height: 275rpx;
	}
}
.blueText{
	font-size: 36rpx;
	color: #2E73FF;
	font-weight: bold;
	line-height: 60rpx;
	text-align: center;
	margin:10rpx 0rpx 30rpx;
}
.litText{
	text-align: center;
	font-size: 28rpx;
	font-weight: 500;
	color: #999999;
	line-height: 46rpx;
}
.btn-group{
	margin-top: 50rpx;
	text-align: center;
	.blueBtn{
		width: 353rpx;
		line-height: 100rpx;
		text-align: center;
		color: #2E73FF;
		font-size: 36rpx;
		border: 1rpx solid #2E73FF;
		border-radius: 100rpx;
		background: #FFFFFF;
	}
}
</style>
