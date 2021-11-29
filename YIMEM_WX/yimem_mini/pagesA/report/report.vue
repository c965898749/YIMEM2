<template>
	<view class="content">
		<view class="report-area">
			<view  class="report-text-s">
				<textarea class="report-text" v-model="contents" placeholder="请输入您遇到的问题描述" :maxlength="300"/>
				<view class="r-btm-num">{{contents.length}}/300</view>
			</view>
		</view>
		<view class="photo">
			<u-upload ref="uUpload" :action="action" :auto-upload="false" :max-count="1" :header="hearder"
					  upload-text="上传照片" @on-uploaded="uploaded" @on-error="error"></u-upload>
		</view>
		<view class="btnRow">
			<button class="subAdd" @click="sub">提交</button>
		</view>
	</view>
</template>

<script>
	import config from "../../request/config";
	import {vpncomplaintAddsvc} from "../../api";

	export default {
		data() {
			return {
				jobId:"",
				contents:"",
				userInfo: '',
				hearder:'',
				action:''
			}
		},
		onLoad(options) {

			if (options.jobid) {
				this.jobId = options.jobid;
			}

			let token= uni.getStorageSync('token');
			this.header = {
				'charset':'UTF-8',
				'token': token,
			}
			this.userInfo = getApp().globalData.userInfo
			this.action=config.baseUrl.default+'/vpnservice/upload';
			console.log(this.jobId)
		},
		methods: {
			error(){
				uni.showToast({
					title: '请输入你的投诉或者建议',
					icon: 'none',
					duration: 1500
				})
			},

			//上传完成
			uploaded(list,name){
				console.log(list,name)
				let imageUrl = ""
				if(list && list.length>0)
					imageUrl = list[0].response.data.imageUrl
				let that = this;

				let data = {
					vpncomplaintAdd: {
						content: that.contents,
						userId: that.userInfo.userId,
						phone: that.userInfo.phoneNum,
						imageUrl:imageUrl
					}
				}
				vpncomplaintAddsvc(data).then(res => {
					if (res.code == 1) {
						uni.showToast({
							title: '提交成功！',
							icon: 'none',
							duration: 1500
						})
						setTimeout(() => {
							uni.navigateBack({
								delta: 1
							})
						}, 1500)
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			sub() {
				if(!this.contents || this.contents.length===0){
					uni.showToast({
						title: '请输入你的投诉或者建议',
						icon: 'none',
						duration: 1500
					})
				}else{
					this.$refs.uUpload.upload();

				}


			}
		}
	}
</script>

<style lang="less">
	.report-area{
		display: flex;
		justify-content: center;
		padding-top: 20rpx;
	}
	.report-text-s{
		width: 700rpx;
		height: 420rpx;
		background: #F6F6F6;
		border-radius: 10rpx;
		padding:10rpx 10rpx 0 10rpx;
	}
	.report-text{
		width: 680rpx;
		height: 360rpx;
	}
	.r-btm-num{
		display: flex;
		flex-direction: row-reverse;
		font-size: 28rpx;
		font-weight: 500;
		color: #999999;
	}
	.btnRow {
		padding: 10rpx 30rpx;
		position: fixed;
		left: 0;
		bottom: 0;
		width: 100%;
	}

	.subAdd {
		height: 80rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
		border-radius: 40rpx;
		line-height: 80rpx;
		color: #FFFFFF;
		font-size: 30rpx;
		text-align: center;
		width: 690rpx;
		font-weight: 500;
		margin: 0 auto;
	}
	.photo{
		padding: 10rpx 0 0 25rpx;
	}
</style>
