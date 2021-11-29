<template>
	<view class="content">
		<view class="topBox">
			<view class="textPhoe">
				绑定您的手机号
			</view>
			<view class="safeBind">
				绑定后会让您的账户更加安全
			</view>
		</view>
		<view class="inputBox">
			<view class="row">
				<view class="col_1">
					<image class="phoneIcon" src="../../static/images/login_icon01.png" mode=""></image>
				</view>
				<view class="col_11">
					<input type="text" v-model="phoneNum" placeholder="请输入手机号" />
				</view>
			</view>
			<view class="row">
				<view class="col_1">
					<image class="codeIcon" src="../../static/images/login_icon02.png" mode=""></image>
				</view>
				<view class="col_8">
					<input type="text" v-model="checkCode" placeholder="请输入验证码" />
				</view>
				<view class="col_3">
					<view class="codeTap" @click="sendMsg">
						获取验证码
					</view>
				</view>
			</view>
			<view class="row">
				<view class="col_1">
					<image class="phoneIcon" src="../../static/images/yaoshi.png" mode=""></image>
				</view>
				<view class="col_11">
					<input type="text" v-model="inviteCode" placeholder="邀请码(选填)" />
				</view>
			</view>
			</view>
			
		
		<view class="btn-row">
			<image @click="subBind" class="btnImg" src="../../static/images/login_btn_bj.png" mode=""></image>
		</view>
		
		
		<!-- 协议弹窗 -->
		<view v-show="userAgree" class="userAgree">
		  <view catchtouchmove="catchtouchmove" class="tips">
		    <view class="tips_box">
		      <view class="hint_view">
		        <view class="text">
		          <view class="hint1" bindtap='goToUserLicence' @click="goToUserLicence(item)">点击查看《一梦网》使用协议</view>
		        <view class="">
		        	这是一梦网的协议
		        </view>
				</view>
		      </view>
		      <button @click='tipAgree' class="agreeBtn" type='primary'>我已阅读并同意</button>
		    </view>
		  </view>
		</view>
	 <!-- 协议结束 -->
	 
	 
	</view>
</template>

<script>
	import {
		getOpenId,
		getPhoneCode,
		registbyphone
	} from '@/api/index.js'
	export default {
		data() {
			return {
			 userAgree:true,
				phoneNum: '',
				checkCode: '',
				openId: '',
				inviteCode:''
			};
		},
		onShow() {
			this.goLogin()
		},
		onLoad() {
			// var that = this
			// var userAgree = wx.getStorageSync('userAgree') || false
			// that.setData({
			//     userAgree
			// })
		},

		methods: {
			tipAgree(){
				this.userAgree=false
			},
		// 协议跳转
		goToUserLicence(item){
			uni.navigateTo({
			  url: '../../pagesA/userAgreement/userAgreement',
		 
			 
			})
		},
			 
			sendMsg() {
				if(this.phoneNum){
					let data = {
						phone: this.phoneNum
					}
					getPhoneCode(data).then(res => {
						if (res.code == 1) {
							uni.showToast({
								title: '验证码发送成功！',
								icon: 'none',
								duration: 1500
							})
						} else {
							uni.showToast({
								title: res.msg,
								icon: 'none',
								duration: 1500
							})
						}
					})
				}else{
					uni.showToast({
						title: '请先填入手机号码！',
						icon: 'none',
						duration: 1500
					})
				}
			},
			subBind() {
				let that=this;
				let code='';
				uni.login({
					provider: 'weixin',
					fail(res){
						console.log(res)
					},
					success: function(loginRes) {

						code=loginRes.code;
						let data = {
							phone: that.phoneNum,
							authCode: that.checkCode,
							code:code,
							//inviteNum:that.inviteNum
							inviteCode:that.inviteCode
						}
						registbyphone(data).then(res => {
							if(res.code==1){
								getApp().globalData.userId=res.data.userId;
								getApp().globalData.isLogin=1;
								uni.setStorageSync('token',res.data.token);
								uni.navigateBack({
									delta:1
								})
							}else{
								uni.showToast({
									title: res.msg,
									icon: 'none',
									duration: 1500
								})
							}
							
						})
					}
				})
				
			},
			goLogin() {
				let that = this;
				let code=''
				uni.login({
					provider: 'weixin',
					success: function(loginRes) {
						code=loginRes.code;
						let data = {
							code: code
						}
						getOpenId(data).then(res => {
							if (res.code == 1) {
								that.openId = res.data.openid;
								getApp().globalData.openId=res.data.openid;
							}
						})
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.tips {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    position: fixed;
	    left: 0;
	    top: 0;
	    z-index: 100;
	    width: 100%;
	    height: 100%;
	    background: rgba(0,0,0,0.7);
	}
	
	.tips .tips_box {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    width: 75%;
	    height: auto;
	    border-radius: 45rpx;
	    background: #fff;
	    overflow: hidden;
	}
	
	.tips .tips_box .hint_view {
	    display: flex;
	    align-items: center;
	}
	
	.tips .tips_box .hint_view .text {
	    display: flex;
	    flex-direction: column;
	    margin: 12rpx 24rpx;
	}
	
	.tips .tips_box .hint1 {
	    margin-top: 38rpx;
	    text-align: center;
	    font-size: 30rpx;
	    color: #1a1a1a;
	    line-height:52rpx;
	    border-bottom:1px solid;
	}
	
	.agreeBtn {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    margin: 32rpx 0 32px;
	    width: 70%;
	    line-height: 64rpx;
	    border-radius: 80rpx;
	    font-size: 32rpx;
	    letter-spacing: 6rpx;
	    color: #FFFFFF;
		background-color: blue;
	}
	.isTipShow {
	  display: block;
	}
	
	.isTipHide {
	  display: none;
	}
 // 协议CSS
	
	
	.topBox {
		padding: 200rpx 75rpx 50rpx;
	}

	.inputBox {
		padding: 0 75rpx;
	}

	.textPhoe {
		font-size: 48rpx;
		font-weight: bold;
		color: #1B1B1B;
	}

	.safeBind {
		font-size: 28rpx;
		font-weight: 500;
		color: #999999;
		margin-top: 20rpx;
	}

	.codeIcon,
	.phoneIcon {
		width: 32rpx;
		height: 32rpx;
		vertical-align: -7rpx;
	}

	.codeTap {
		font-size: 24rpx;
		font-weight: 400;
		color: #2E73FF;
		text-align: right;
	}

	.row {
		padding: 40rpx 0;
		border-bottom: 1rpx solid #E7E7E7;
	}

	.row input {
		font-size: 26rpx;
	}

	.btn-row {
		padding: 0 41rpx;
		margin-top: 50rpx;
	}

	.btnImg {
		width: 100%;
		height: 107rpx;
	}
</style>
