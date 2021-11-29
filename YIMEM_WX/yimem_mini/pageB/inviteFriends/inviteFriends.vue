<template>
	<view class="content">
		<navBarTop title="邀请好友" imgNavBg="../../static/images/myBanner.png" backgroundColor="rgba(255, 255, 255, 0)" color="#FFFFFF"></navBarTop>
		<view class="suggest-box">
			<view class="box-left">
				<view class="suggest-title">邀请码</view>
				<view class="suggest-code">{{inviteCode}}</view>
			</view>
			<button class="copyCode" @click="copyCode">复制邀请码</button>
		</view>
		<view class="friends-list">
			<view class="friends-title">邀请列表</view>
			<scroll-view scroll-y="true" class="scrollBox">
				<view v-for="(item,index) in friends" :key = "index" >
					<view class="separation" v-if="index!==0"></view>
					<view class="friend-item">
						<view class="left">
							<image :src="item.headImg" mode=""></image>
						</view>
						
						<view class="right">
							<view class="name">
								{{item.userName}}
							</view>
							<view class="time">
								加入时间：{{item.createtime}}
							</view>
						</view>
						
					</view>
					
					
				</view>
				
				<!-- <view class="noMoreList" v-if="canLoad==false">
					没有更多。。。
				</view> -->
			</scroll-view>
		</view>
		
	</view>
</template>

<script>

	import {
		inviteList
	} from "../../api/index.js"
	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	
	export default {
		data() {
			return {
				inviteCode:'',
				// pageIndex: 1,
				// pageSize: 10,
				canLoad: true,
				friends:[]
			}
		},
		components: {
			navBarTop
		},
		filters: {
			
		},
		onLoad() {
			this.inviteCode = getApp().globalData.userInfo.inviteCode;
			//获取邀请列表
			this.getInviteAjax();
		},
		methods: {
			copyCode(){
				uni.setClipboardData({
				    data: this.inviteCode,
				    success: function () {
				       uni.showToast({
				       	title: "复制成功",
				       	duration: 1500,
				       	icon: 'none'
				       }) 
				    },
					fail() {
						uni.showToast({
							title: "复制失败，请稍后再试",
							duration: 1500,
							icon: 'none'
						}) 
					}
				});
			},
			
			// 加载更多
			botMore() {
				if (this.canLoad != false) {
					this.getAjax();
				}
			},
			// 获取推荐列表
			getInviteAjax() {
				let that = this;
				let data = {
					inviteCode: that.inviteCode,
				}
				inviteList(data).then(res => {
					
					if (res.code == 1) {
						console.log(res.data)
						that.friends = res.data
					} else {
						console.log(res.msg)
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.suggest-box{
		width: 700rpx;
		height: 195rpx;
		margin-left: calc(50% - 350rpx);
		margin-top:66rpx;
		background: #FFFFFF;
		box-shadow: 0rpx 0rpx 27rpx 0rpx rgba(0, 0, 0, 0.16);
		border-radius: 10rpx;
		display: flex;
		justify-content: space-between;
		.box-left{
			padding:37rpx  0  50rpx 40rpx;
			.suggest-title{
				font-size: 32rpx;
				font-weight: bold;
				color: #2C2C2C;
			}
			.suggest-code{
				font-size: 48rpx;
				font-weight: 500;
				color: #2E73FF;
				margin-top: 20rpx;
			}
		}
		.copyCode{
			
			
			width: 179rpx;
			height: 54rpx;
			line-height:54rpx;
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			border-radius: 27rpx;
			font-size: 28rpx;
			font-weight: 500;
			color: #FFFFFF;
			margin-top: 105rpx;
			margin-right: 40rpx;
						
		}
		
	}
	.friends-list{
		width: 700rpx;
		flex:1;
		margin-left: calc(50% - 350rpx);
		margin-top: 20rpx;
		background: #FFFFFF;
		box-shadow: 0px 0px 27px 0px rgba(0, 0, 0, 0.16);
		border-radius: 10px;
		.friends-title{
			font-size: 32rpx;
			font-weight: bold;
			color: #2C2C2C;
			padding: 40rpx 0 0 39rpx;
		}
		.scrollBox {
			flex:1;
			
			.friend-item{
				display: flex;
				margin-top: 20rpx;
				.left{
					margin-left: 43rpx;
					image{
						 width: 69rpx;
						 height: 69rpx;
						 border-radius: 50%;
					 }
				 }
				 .right{
					 margin-left: 10px;
					 .name{
						 height: 27rpx;
						 font-size: 28rpx;
						 font-family: PingFang SC;
						 font-weight: bold;
						 color: #2C2C2C;
						 margin-bottom: 15rpx;
					 }
					 .time{
						 height: 22rpx;
						 font-size: 22rpx;
						 font-family: PingFang SC;
						 font-weight: 400;
						 color: #919191;
					 }
				 }
			}
		}
	}
	
	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}
	
	.separation{
		width: 660rpx;
		height: 10rpx;
		margin-left: 20rpx;
		border-bottom: 1px solid #F3F3F3;
	}
	
</style>
