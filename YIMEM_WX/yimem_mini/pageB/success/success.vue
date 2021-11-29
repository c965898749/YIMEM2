<template>
	<view class="content">
		<view class="pic">
			<image src="../../static/images/success.png" mode="widthFix"></image>
		</view>
		<view class="text">
			<view class="b_t">企业认证提交成功！</view>
			<view class="s_t">您提交的企业认证正在审核中，请耐心等待...</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				json:{
					name:'',
					mobile:'',
				}
			};
		},
		methods: {
			
		}
	}
</script>

<style lang="less">
.content{}
.pic{width:312rpx;text-align:center;margin:0rpx auto;padding:50rpx 0rpx;
	image{width:100%;display:block;}
}
.text{text-align:center;
	.b_t{color:#2E73FF;font-size:32rpx;font-weight:bold;}
	.s_t{color:#919191;font-size:24rpx;margin-top:24rpx;}
}
</style>
