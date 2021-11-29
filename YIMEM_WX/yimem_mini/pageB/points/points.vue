<template>
    <view class="content">
       <navBarTop title="我的积分" imgNavBg="../../static/images/jhzu7.png" backgroundColor="rgba(255, 255, 255, 0)" color="#FFFFFF"></navBarTop>
	   <view class="base-info">
		   <view class="headBox">
		   		<image  class="headImg" :src="avatar" mode="aspectFill"></image>
		   </view>
		   <view class="text-mid">
			   <view class="contdays" >
				   已经连续签到<text>{{point.contdays}}</text>天
			   </view>
			   <view class="detail" @click="toDetail">
				   <view>积分明细</view>
				   <image class="arrow-right" src="../../static/images/arrow-right.png"></image>
			   </view>
			   
		   </view>
		   <view class="point-area">
			   <view>
				   <view class="point-circle">
					   <uni-icons type="star-filled" color="#FFE433" :size="15"></uni-icons>
				   </view>
				   <view>{{point.value || 0}}</view>
				   
			   </view>
		   				   
		   </view>
	   </view>
		<view class="commoditys">
			<view class="scollBody">
				<view class="commoditys-title">
					积分兑换好礼
				</view>
				<scroll-view scroll-y class="commodity-list" @scrolltolower="reachBottom">
					<view class="listBox">
						<view class="commodity-item" v-for="(item,index) in commodityList" :key="index" >
							<image :src="item.img" class="commodity-image"></image>
							<view class="commodity-name">{{item.name}}</view>
							<view class="box-bottom">
								<view class="point-circle2"></view>
								<uni-icons type="star-filled" color="#FFE433" :size="10" class="icon-star"></uni-icons>
								<view class="cost-point">{{item.point}}</view>
								<image src="../../static/images/duihuan.png" @click.stop="changeToGift(item)" ></image>
							</view>
							
						</view>
					</view>
					<view class="noMoreList" v-if="canLoad==false">
						没有更多了。。。
					</view>
				</scroll-view>
				
			</view>
		</view>
    </view>
</template>
<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	
	import {vpnGetPointsvc,vpngetGiftList,vpnpointToGift} from "@/api/index.js"
	export default{
		components:{
			uniIcons,navBarTop
		},
		data(){
			return{
                avatar:"",
				userInfo: {},
				wxInfo: {},
				point:{},
				canLoad:true,
				pageIndex: 1,
				pageSize: 10,
				commodityList:[]
			}
		},
		onShow() {
			 this.wxInfo = getApp().globalData.wxInfo;
			 this.userInfo = getApp().globalData.userInfo;
			 if(this.userInfo.headImg){
				 this.avatar = this.userInfo.headImg
			 }else if( this.wxInfo.avatarUrl){
				 this.avatar = this.wxInfo.avatarUrl
			 }else{
				 this.avatar = '../../static/images/headimgMy.png';
			 }
			 //查询积分
			 this.getPoint();
			 this.getGiftList();
		},
		methods:{
			//到达底部
			reachBottom(){
				if(this.canLoad){
					this.getGiftList();
				}
			},
			goBack:function(){
				 uni.navigateBack({
					delta:1
				 })
			},
			toDetail(){
				uni.navigateTo({
					url: "../../pageB/pointDetail/pointDetail"
				})
			},
			getPoint(){
				let _this = this
				vpnGetPointsvc({userId : this.userInfo.userId}).then(res=>{
					console.log(res)
					if (res.code == 1) {
						_this.point = res.data
						console.log(_this.point,this.point)
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			changeToGift(item){
				let that = this;
				let id = item.id;
				let needPoint = item.point?item.point:0;
				let hasPoint = this.point.value?this.point.value:0;
				if(hasPoint<needPoint){
					uni.showToast({
						duration: 1500,
						title: '积分不足，兑换失败',
						icon: 'none'
					})
				}else{
					let data = {
						vpnGiftChange: {
							userId: this.userInfo.userId,
							giftId: id
						}
					}
					vpnpointToGift(data).then(res => {
						console.log(res)
						if (res.code == 1) {
							uni.showToast({
								duration: 1500,
								title: '兑换成功',
								icon: 'none'
							});
							setTimeout(()=>{
								that.getPoint();
							},1500)
						} else {
							uni.showToast({
								duration: 1500,
								title: res.msg,
								icon: 'none'
							})
						}
					})	
				}
			},
			getGiftList(){
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					}
				}
				vpngetGiftList(data).then(res=>{
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.commodityList = res.data.content;
						} else {
							that.commodityList = [...that.commodityList, ...res.data.content]
						}
						if (res.data.content == undefined || res.data.content.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			}
		}
	}
</script>
<style lang="less" scoped>
	.scollBody{
		height: 100%;
	}

    .myBanner {
        position: absolute;
        width: 100%;
        top: 0;
        left: 0;
        z-index: -1; 
		background-image: url("../../static/images/jhzu7.png");
		background-size: cover;
		background-repeat: no-repeat;
    }
	.topBox {
		margin-top: calc(--window-top);
		height:44px;
	}
	.base-info{
		margin-top: 35rpx;
		display: flex;
		margin-bottom: 40rpx;
	}
	.headBox {
		border: 4rpx solid #f9bf33;
		width: 120rpx;
		height: 120rpx;
		margin:0 30rpx 0 40rpx;
		border-radius: 120rpx;
		overflow: hidden;
		
		.headImg {
			width: 100%;
			height: 100%;
		}
	}
	
	.text-mid{
		
		padding-top: 16rpx;
		
		.contdays{
			
			font-size: 32rpx;
			font-weight: 500;
			color: #FFFFFF;
			>text{
				color:#FDC02E;
			}
		}
		.detail{
			
			display:flex;
			align-items: center;
			margin-top:20rpx;
			
			font-size: 26rpx;
			font-weight: 400;
			color: #BAD6FF;
			.arrow-right{
				width:27rpx;
				height:27rpx;
				margin-left:10rpx;
				margin-top: 2rpx;
			}
		}
	}
	.point-area{
		flex:1;
		position: relative;
		>view{
			position: absolute;
			top:18rpx;
			right:40rpx;
			width: 161rpx;
			height: 81rpx;
			border: 2rpx solid #72AAFF;
			border-radius: 25rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			
			
			font-size: 32rpx;
			font-weight: 500;
			color: #FFFFFF;
			padding: 0 30rpx 0 24rpx;
		}
		.point-circle{
			width: 44rpx;
			height:44rpx;
			background-image: url("../../static/images/point.png");
			background-repeat: no-repeat;
			background-size: cover;
			text-align: center;			
		}
	}
	
	.commoditys{
		
		flex:1;
		display: flex;
		flex-direction: column;
		align-items: center;
		min-height: 200px;
		margin-bottom: 20rpx;
	}
	.commoditys>view{
		overflow-y: auto;
		width: 700rpx;
		height: 100%;
		background: #FFFFFF;
		box-shadow: 0rpx 0rpx 27rpx 0rpx rgba(0, 0, 0, 0.16);
		border-radius: 10rpx;
	}
	.commoditys-title{
		padding: 37rpx;
		font-size: 32rpx;
		font-weight: bold;
		color: #2C2C2C;
	}
	.commodity-list{
		height: calc( 100% - 115rpx);
		width: 100%;
		.listBox{
			width: 100%;
			padding: 0 14rpx;
			.commodity-item{
				float: left;
				width: 50%;
				text-align: center;
				margin-bottom: 40rpx;
				.commodity-image{
					width: 297rpx;
					height: 250rpx;
					border-radius: 8rpx;
					margin: 0 auto;
				}
				.commodity-name{
					width: 297rpx;
					margin: 0 auto;
					text-align: left;
					font-size: 26rpx;
					font-weight: bold;
					color: #2A2A2A;
					margin-top: 20rpx;
					margin-bottom: 15rpx;
				}
				.box-bottom{
					
					width: 297rpx;
					margin: 0 auto;
					display: flex;
					.point-circle2{
						width: 30rpx;
						height:30rpx;
						background-image: url("../../static/images/point.png");
						background-repeat: no-repeat;
						background-size: cover;
					}
					.icon-star{
						margin: -8rpx 0 0 -24rpx;
					}
					.cost-point{
						flex:1;
						text-align: left;
						font-size: 26rpx;
						font-weight: 500;
						color: #FDBB27;
						margin-left: 10rpx;
						margin-top: -5rpx;
						
					}
					image{
						width: 68rpx;
						height: 49rpx;
					}
				}
				
			}
			
		}
		.noMoreList {
			width: 100%;
			margin: 0 auto;
			padding: 0 0 20rpx 0;
			text-align: center;
			color: #919191;
			font-size: 24rpx;
			float:left;
		}
	}
	
</style>

