<template>
	<view class="content">
		<view class="inputBox">
			<input type="text" :placeholder="holderName">
		</view>
		<view class="btnRow">
			<view class="btnOut">
				确定
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				listHolder: ['请输入姓名', '请输入手机号码', '请输入身份证号码'],
				holderName: ''
			};
		},
		onShow() {
			this.holderName = this.listHolder[0]
		}
	}
</script>

<style lang="less">
	.inputBox{
		padding:50rpx;
	}
	
	.btnRow {
		margin-top: 30rpx;

		.btnOut {
			margin: 0 auto;
			width: 690rpx;
			height: 80rpx;
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
			border-radius: 40rpx;
			font-size: 30rpx;
			line-height: 80rpx;
			color: #FFFFFF;
			text-align: center;
		}
	}
</style>
