<template>
	<view class="navBoxall"  :style="isFix?'padding-top:'+topHeight+'px':''">
		<image class="navImgbg" :src="imgNavBg" mode="widthFix" v-if="imgNavBg"></image>
		<view :style="'background-color:'+backgroundColor+';padding-top:'+topHeight+'px'" :class="isFix?'fixNavBarBox':''">
			<view class="navBar">
				<view class="topWidth">
					<uni-icons v-if="isBack" type="back" size="25" :color="color" @click="goBack"></uni-icons>
				</view>
				<view class="titleZ" :style="'color:'+color">
					{{title}} <uni-icons v-if="helps" @click="takeOut" class="iconHelp" type="help" size="19" color="#FFFFFF"></uni-icons>
				</view>
				<view class="topWidth">
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
		props: {
			isBack:{
				type: Boolean,
				default: true
			},
			color: {
				type: String,
				default: "#000001"
			},
			backgroundColor: {
				type: String,
				default: "#FFFFFF"
			},
			title: {
				type: String,
				default: ""
			},
			isFix: {
				type: Boolean,
				default: false
			},
			imgNavBg:{
				type: String,
				default:''
			},
			helps:{
				type: Boolean,
				default: false
			}
		},
		data() {
			return {
				topHeight: parseInt(getApp().globalData.topHeight) + 6,
				isEnd:''
			};
		},
		 components: {uniIcons},
		 methods: {
			 takeOut:function() {
			 	this.$emit('tapHelp')
			 },
			 goBack:function(){
				 uni.navigateBack({
				 	delta:1
				 })
			 }
		 }
	}
</script>

<style>
	.fixNavBarBox {
		position: fixed;
		width: 100%;
		z-index: 100;
		top: 0;
	}
.navBoxall{
	position: relative;
}
	.fixNavBar {
		height: 32px;
	}

	.navBar {
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 32px;
	}

	.topWidth {
		width: 100rpx;
		text-align: center;
	}

	.iconfont {
		font-size: 25px;
	}

	.titleZ {
		flex: 1;
		text-align: center;
		font-size: 17px;
		font-weight: bold;
	}
	.navImgbg{
		width:750rpx;
		z-index: -5;
		position: fixed;
		top: 0;
		left: 0;
	}
	.iconHelp{
		margin-left: 20rpx;
	}
</style>
