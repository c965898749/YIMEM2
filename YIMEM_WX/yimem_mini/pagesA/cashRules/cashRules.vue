<template>
	<view class="cashRule">
		<rich-text :nodes="cashDetail | formatRichText"></rich-text>
	</view>
</template>

<script>
	import {
		vpnwithdrawRuleGet
	} from '../../api/index.js'
	export default {
		data() {
			return {
				userState: '',
				cashDetail: ''
			};
		},
		onLoad() {
			this.userState = getApp().globalData.userState;
			this.getCashAjax();
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
		methods: {
			getCashAjax: function() {
				let that = this;
				let data = {
					toUser: that.userState
				}
				vpnwithdrawRuleGet(data).then(res => {
					if (res.code == 1) {
						that.cashDetail = res.data.content
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
	.cashRule {
		padding: 30rpx;
	}
</style>
