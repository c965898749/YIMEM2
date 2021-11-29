<template>
	<view class="">
		
		<view class="charts" @click="goToyongjin(item)">
			<image src="../../static/images/yongjin.png" mode=""></image>
		</view>
		<view class="charts" @click="goTodaomian(item)">
			<image src="../../static/images/daomian.png" mode=""></image>
		</view>
		<view class="charts" @click="goTotuijian(item)">
			<image src="../../static/images/invite.png" mode=""></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
		 // 跳转去佣金页面
			goToyongjin(item) {
				uni.navigateTo ({
					url: '../../pagesA/talentRanking/talentRanking' 
				})
			},
			 // 跳转去刀面页面
			goTodaomian(item) {
				uni.navigateTo ({
					url: '../../pagesA/talentDaomian/talentDaomian' 
				})
			},
			
			// 跳转去推荐页面
						goTotuijian(item) {
							uni.navigateTo ({
								url: '../../pagesA/talentTuijian/talentTuijian' 
							})
						},
		}
	}
</script>

<style lang="less" scoped>
.charts{
	 width: 684rpx;
	 height: 226rpx;
	 margin-left: 32rpx;
	 margin-bottom: 29rpx;
	 
	 image{
		 width: 684rpx;
		 height: 226rpx;
	 }
}
</style>
