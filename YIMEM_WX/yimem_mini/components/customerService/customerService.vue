<template>
	<view>
		<uniPopup ref="popup" type="center">
			<view class="popBox">
				<image class="kefuIcon" src="../../static/images/kefuimg.png" mode="widthFix"></image>
				<view class="popContent">
					<view class="centerBlack">
						客服电话
					</view>
					<view class="centerBlack">
						0513-81020304
					</view>
					<view class="huiText">
						服务时间：上午09:00-下午18:00
					</view>
					<view class="huiText">
						我们将会全心全意为您提供满意周到的咨询服务！
					</view>
				</view>
				<view class="popFoot">
					<view class="">
						<view class="btnBlue" @click="phoneCall">
							立即拨打
						</view>
					</view>
					<view class="cacelText">
						<text @click="cacelCall">取消</text>
					</view>
				</view>
			</view>
		</uniPopup>
	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	
	export default {
		components:{
			uniPopup,
			
		},
		data() {
			return {
				
			};
		},
		methods:{
			open(){
				this.$refs.popup.open()
			},
			cacelCall() {
				this.$refs.popup.close()
			},
			// 拨打电话
			phoneCall() {
				wx.makePhoneCall({
					phoneNumber: '051381020304' //仅为示例，并非真实的电话号码
				})
			},
		}
	}
</script>

<style  lang="less">
	// 弹出框
	.popBox {
		width: 627rpx;
		height: 758rpx;
		background: #FFFFFF;
		border-radius: 20rpx;
	
		.kefuIcon {
			width: 627rpx;
		}
	
		.popContent {
			padding: 40rpx 60rpx 0;

			.centerBlack {
				font-size: 32rpx;
				font-weight: bold;
				color: #1B1B1B;
				text-align: center;
				margin-bottom: 35rpx;
			}

			.huiText {
				font-size: 28rpx;
				font-weight: 500;
				color: #999999;
				line-height: 36rpx;
				margin-bottom: 20rpx;
			}
		}
		.popFoot {
			.btnBlue {
				height: 70rpx;
				line-height: 70rpx;
				border-radius: 70rpx;
				text-align: center;
				color: #FFFFFF;
				background: #2E73FF;
				box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
				font-size: 26rpx;
				width: 300rpx;
				margin: 0 auto;
			}
		
			.cacelText {
				margin-top: 20rpx;
				color: #2E73FF;
				font-size: 26rpx;
				text-align: center;
			}
			
		}
	}
</style>
