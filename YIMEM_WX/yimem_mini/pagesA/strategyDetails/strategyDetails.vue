<template>
	<view class="content">
		<view class="conBox">
			<view class="strateTitle">
				{{detailOne.title}}
			</view>
			<rich-text v-if="detailOne.content" :nodes="detailOne.content | formatRichText"></rich-text>
		</view>

	</view>
</template>

<script>
	import {
		vpnstrategyGetbyidsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				stId: '',
				detailOne: {}
			}
		},
		onLoad(options) {
			this.stId = options.stid;
			this.getStrategy();
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
			getStrategy() {
				let that = this;
				let data = {
					id: that.stId
				}
				vpnstrategyGetbyidsvc(data).then(res => {
					if (res.code == 1) {
						console.log(res)
						that.detailOne = res.data.vpnstrategyGet
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			}
		}
	}
</script>

<style>
	.conBox {
		padding: 0 30rpx 30rpx;
	}

	.strateTitle {
		font-size: 30rpx;
		text-align: center;
		line-height: 60rpx;
		font-weight: 600;
	}
</style>
