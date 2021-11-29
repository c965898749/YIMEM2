<template>
	<view class="content">
		<view class="title">押金充值</view>
		<view class="betweenRow"> 
			<view>账户余额</view>
			<view>
				<text class="money" v-model="nowMoney"> </text>元
			</view>
		</view>
		<view class="center-parent">
			<view>
				<view class="row">
					<icon type="success" size="20" color="#2E73FF"/>
					<view class="gary-text">押金充值</view>
				</view>
				<view class="row input-money">
					<u-input type="number" v-model="deposit" input-align="right" :clearable="false"  readonly trim='true' placeholder="请输入金额"
					:custom-style="{fontSize: '36rpx',fontWeight: 'bold',color: '#2C2C2C'}"/>
					<view>元</view>
				</view>
			</view>
		</view>
		<view class="remarks">
			注：
			<view>
				押金充值金额2000元，发布职位不限个数。 
			</view>
			<view>
				如有问题，请点击我的-联系客服进行反馈
			</view>
		</view>
		<view class="btnRow">
			<view class="btnOut" @click="chongzhi()">
				确认充值
			</view>
		</view>
		
		
		
		
		<!-- 弹出层 -->
		<uniPopup ref="popup" type="center" v-model="sshow">
			<view class="popBox">
				<view class="cha" @click="close()">
					<uni-icons type="closeempty"></uni-icons>
				</view>
		
		
				<view class="imagepop">
					<image class="kefuIcon" src="../../static/images/now.png" mode="widthFix"></image>
				</view>
				<view class="popContent">
					<view class="centerBlack">
						充值成功
					</view>
					<view class="huiText">
					成功充值押金2000元整
					</view>
				</view>
			</view>
		</uniPopup>
		
		
	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
		import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
		data() {
			return {
				deposit:2000,
				money:'',
		           nowMoney:0,
				show: false
				
			}
		},
		components:{
			uniPopup,
			uniIcons
		},
		methods: {
			chongzhi(){
			   	this.show = true;
			   	this.$refs.popup.open()
			 
			  this.money=this.deposit
			  this.nowMoney=this.money
			},
			close() {
				this.show = false;
				this.$refs.popup.close()
			},
		}
	}
</script>
			
<style lang="less" scoped>
	
	// 弹出框
	.popBox {
		position: relative;
		width: 624rpx;
		height: 445rpx;
		background: #FFFFFF;
		border-radius: 20rpx;
	
		.cha {
			font-size: 40rpx;
			position: absolute;
			width: 48rpx;
			height: 48px;
			right: 20rpx;
			margin-top: 20rpx;
	
	
		}
	
		.imagepop {
			padding-top: 40rpx;
			width: 182rpx;
			height: 182rpx;
			margin: 0 auto;
	
			.kefuIcon {
				width: 182rpx;
				height: 182rpx;
			}
		}
	
	
		.popContent {
			padding: 70rpx 0rpx 156rpx;
			width: 364rpx;
			height: 99rpx;
			margin: 0 auto;
	
			.centerBlack {
				font-size: 44rpx;
				font-weight: bold;
				color: #222222;
				text-align: center;
				margin-bottom: 20rpx;
				// line-height: 70rpx;
			}
	
			.huiText {
				// margin: 0 ;
				text-align: center;
				font-size: 28rpx;
				font-weight: 500;
				color: #999999;
				// line-height: 36rpx;
				margin-bottom: 20rpx;
			}
		}
	
	}
	
	// popup结束
	
	
	
	.title{
		padding: 80rpx 0 0 32rpx;
		font-size: 48rpx;
		font-weight: bold;
		color: #2C2C2C;
	}
	.betweenRow{
		padding: 78rpx 32rpx;
		display:flex;
		justify-content: space-between;
		font-size: 30rpx;
		font-weight: bold;
		color: #2C2C2C;
	}
	.money{
		font-size: 50rpx;
	}
	.center-parent{
		display: flex;
		justify-content: center;
		
		>view{
			width: 691rpx;
			height: 114rpx;
			background: #F7F6FB;
			border: 1rpx solid #2E73FF;
			border-radius: 10rpx;
			display: flex;
			justify-content:space-between;
			align-items: center;
			padding: 0 20rpx;
		}
	}
	.row{
		display: flex;
		align-items: center;
	}
	.gary-text{
		font-size: 28rpx;
		font-weight: 500;
		color: #A7A7A7;
		width: 200rpx;
		margin-left: 10rpx;
	}
	.input-money{
		
		font-size: 36rpx;
		font-weight: bold;
		color: #2C2C2C;
		>view{
			margin-left: 10rpx;
			margin-top: -2rpx;
		}
	}
	.remarks{
		padding: 56rpx 32rpx;
		
		font-size: 34rpx;
		font-weight: 500;
		color: #919191;
		>view{
			margin-top: 36rpx;
			font-size: 26rpx;
		}
	}
	
	.btnRow {
		margin-top: 30rpx;
	
		.btnOut {
			margin: 0 auto;
			width: 690rpx;
			height: 80rpx;
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
			border-radius: 40rpx;
			font-size: 30rpx;
			line-height: 80rpx;
			color: #FFFFFF;
			text-align: center;
		}
	}
</style>
