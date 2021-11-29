<template>
	<view class="content">
		<view class="persenBox">
			<view class="row">
				<!-- <view class="col_3">
					
					<view class="huiText">
						点击编辑头像
					</view> 
				</view> -->
				<view class="col_3 imgRight" @click="upLoadImg">
					<image class="headImg" v-if="headImg!=''" :src="headImg" mode=""></image>
					<image v-else class="headImg" :src="userInfo.headImg?userInfo.headImg:'../../static/images/headimgMy.png'" mode=""></image>
				</view>
				<view class="nameText">
					<text v-if="userInfo.userName">{{userInfo.userName}}</text>
					<text v-else>{{wxInfo.nickName}}</text>
				</view>
			</view>
			<view class="boxCont">
				<u-cell-group>
					<u-cell-item @click="changeInfo('userName')" title="姓名" :value="userInfo.userName?userInfo.userName:wxInfo.nickName"></u-cell-item>
					<u-cell-item title="手机号" :value="userInfo.phoneNum"></u-cell-item><!-- @click="changeInfo('phoneNum')" 手机号验证码-->
					<u-cell-item @click="changeInfo('identityNum')" title="身份证号" :value="userInfo.identityNum | userCardFilte"></u-cell-item>
				</u-cell-group>
			</view>		
				
		</view>
		<!-- <view class="btnRow">
			<view class="btnOut">
				退出登录
			</view>
		</view> -->
	</view>
</template>

<script>
	import config from '../../request/config.js'
	import {vpnuserUpdatesvc} from "../../api/index.js"
	export default {
		data() {
			return {
				userInfo:{},
				headImg:'',
				wxInfo:{}
			};
		},
		onShow() {
			this.userInfo=getApp().globalData.userInfo;
			this.wxInfo=getApp().globalData.wxInfo;
		},
		filters:{
			userCardFilte(val){
				if(!val){
					return '暂无'
				}
				let strcard=val.replace(/^(.{4})(?:\d+)(.{4})$/,"$1******$2");
				return strcard
			}
		},
		methods: {
			changeInfo(path){
				//直接跳转
				uni.navigateTo({
					url: "../../pageB/changeInfo/changeInfo" + '?option=' + path
				})
				
			},
			upLoadImg() {	
				let that=this;
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						let imgList = res.tempFiles[0];
						if (imgList.size > 2 * 1024*1024) {
							uni.showToast({
								title: '上传的图片不可以超过2M',
								icon: 'none',
								duration: 1500
							})
							return false
						}
						let urlp=config.baseUrl.default;
						uni.uploadFile({
							url: urlp+'/vpnservice/upload',
							filePath: imgList.path,
							name: 'file',
							formData: {
								'user': 'test'
							},
							success: function(uploadFileRes) {
								let fileRes=JSON.parse(uploadFileRes.data);
								if(fileRes.success==1){
									that.editHead(fileRes);
								}else{
									uni.showToast({
										title:fileRes.msg,
										duration:1500,
										icon:'none'
									})
								}
							},
							fail:function(errRes){
								uni.showToast({
									title:errRes,
									icon:'none',
									duration:1500
								})
							}
						})
					}
				});
			},
			editHead(fileRes){
				let that=this;
				let data={
					vpnuserUpdate:{
						userId:that.userInfo.userId,
						headImg:fileRes.data.imageUrl
					}
				}
				vpnuserUpdatesvc(data).then(res=>{
					if(res.code==1){
						that.headImg=fileRes.data.imageUrl;
					}
				})
			}
		}
	}
</script>

<style lang="less">
	 
	.persenBox {
		padding: 30rpx;

		.row {
			border-bottom: 1rpx solid #F3F3F3;
			padding: 40rpx 0;


			.col_8 {
				font-size: 24rpx;
				font-weight: 500;
				color: #101010;
				text-align: right;
			}

			.col_1 {
				text-align: right;
			}

			.imgRight {
				width: 150rpx;
				height: 150rpx;
				text-align: right;
				border-radius: 70rpx;
			}
			

			.leftLable {
				font-size: 28rpx;
				font-weight: 500;
				color: #1B1B1B;
			}

			.nameText {
				font-size: 40rpx;
				font-weight: bold;
				color: #101010;
				margin-bottom: 20rpx;
				margin-top: 20rpx;
			}

			.huiText {
				font-size: 30rpx;
				font-weight: 400;
				color: #999999;
			}

			.headImg {
				width: 135rpx;
				height: 135rpx;
				border-radius: 70rpx;
			}
		}

		.boxCont {
			line-height: 30rpx;
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
