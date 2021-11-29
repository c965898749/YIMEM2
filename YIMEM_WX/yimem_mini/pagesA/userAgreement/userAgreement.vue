<template>
	<view class="content">
		
		<view class="userBox">
			<view class="userTile">
				用户协议和隐私政策
			</view>
			<view class="userContent">
				<rich-text :nodes="mentDetail | formatRichText"></rich-text>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		vpnprotocolGet
	} from '../../api/index.js'
	export default {
		data() {
			return {
				userState: '',
				mentDetail: ''
			};
		},
		filters: {
			/**
			 * 处理富文本里的图片宽度自适应
			 * 1.查找img标签有无style属性，如果没有，加上style
			 * 2.所有标签style后追加 max-width:100% !important;
			 * 4.去掉<br/>标签
			 * @param html
			 * @returns {void|string|*}
			 */
			formatRichText(html) { //控制小程序中图片大小
				let newContent = html.replace(/<img[^>]*>/gi, function(match, capture) {
					console.log(match.search(/style=/gi));
					if (match.search(/style=/gi) == -1) {
						match = match.replace(/\<img/gi, '<img style=""');
					}
					return match;
				});
				newContent = newContent.replace(/style="/gi, '$& max-width:100% !important; ');
				newContent = newContent.replace(/<br[^>]*\/>/gi, '');
				return newContent;
			}
		},
		onLoad() {
			this.userState = getApp().globalData.userState;
			this.getUserMent();
		},
		methods: {
			getUserMent() {
				let that = this;
				let data = {
					toUser: that.userState
				}
				vpnprotocolGet(data).then(res => {
					if (res.code == 1) {
						that.mentDetail = res.data.content
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.content {
		padding: 20rpx 25rpx 60rpx;
		background-color: #F6F6F6;
	}

	.userBox {
		height: 100%;
		background: #FFFFFF;
		border-radius: 10rpx;

		.userTile {
			font-size: 40rpx;
			font-weight: bold;
			color: #2C3240;
			line-height: 130rpx;
			border-bottom: 1rpx solid #E5E5E5;
			text-align: center;
		}

		.userContent {
			font-size: 26rpx;
			font-weight: 500;
			color: #2C3240;
			line-height: 39rpx;
			padding: 35rpx 37rpx;
		}
	}
</style>
