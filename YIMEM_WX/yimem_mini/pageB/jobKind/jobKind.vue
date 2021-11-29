<template>
	<view class="content">
		<view class="topSearch">
			<!-- 设置圆角 -->
			<uni-search-bar :radius="100" @confirm="search" cancelButton="none" placeholder="搜索你想要职位类型"></uni-search-bar>
		</view>
		<scroll-view scroll-y="true" >
			<view class="listBox">
				<view class="oneBox row" v-for="(item,index) in 7">
					<view class="col_7">
						人事/财务/行政
					</view>
					<view class="col_5">
						费用：<text class="redText">800</text><text class="litRedText">元/人</text>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="less">
.topSearch{
	padding:0 15rpx;
}
.listBox{
	padding: 26rpx;
	.oneBox{
		padding: 35rpx 0;
		border-bottom: 1rpx solid #F2F2F2;
		font-size: 28rpx;
		color: #2C2C2C;
		.col_5{
			text-align: right;
		}
		.redText{
			font-size: 32rpx;
			color: #FE2B67;
		}
		.litRedText{
			font-size: 24rpx;
			color: #FE2B67;
		}
	}
}
</style>
