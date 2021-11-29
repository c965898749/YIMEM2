<template>
	<view class="content">
		<view class="ad">
			<image src="../../static/images/joinreseller_adv.png" mode="widthFix"></image>
		</view>
		<view class="form">
			<!-- <view class="item flexCenter">
				<text class="title">姓名</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入您的真实姓名" v-model="json.name" placeholder-class="placeholderStyle"/>
				</view>
			</view>
			<view class="item flexCenter">
				<text class="title">手机号</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入您的手机号" v-model="json.mobile" placeholder-class="placeholderStyle"/>
				</view>
			</view> -->
			
			<view class="uploadIDcard">
				<text class="title">上传身份证正反面</text>
				<view class="upload z"><image data-img="isz" @click="upLoadImg" :src="zImg?zImg:'../../static/images/idcard1.png'" mode="widthFix"></image></view>
				<view class="tip">上传身份证信息面</view>
				<view class="upload f"><image data-img="isf" @click="upLoadImg" :src="fImg?fImg:'../../static/images/idcard2.png'" mode="widthFix"></image></view>
				<view class="tip">上传身份证国徽面</view>
			</view>
			
			<view class="tips">
				<view class="tit">注意事项</view>
				<view>
					<image src="../../static/images/idcard_attention.png" mode="widthFix"></image>
				</view>
				<view class="msg">
				1.身份证信息需清晰，无遮挡，证件四边角完整(无缺角)。<br/>
				2.身需要提供有效期在3个月以上的彩色二代身份证正反面。<br/>
				3.照片大小需要在2M以内。
				</view>
			</view>
			
			<view class="submitAction flexCenter" @click="submitAction">
					<text>提交申请</text>
			</view>
		</view>
	</view>
</template>

<script>
	import config from '../../request/config.js'
	import {vpndistributionAddsvc} from '../../api/index.js'
	export default {
		data() {
			return {
				// json:{
				// 	name:'',
				// 	mobile:''
				// },
				zImg:'',
				fImg:'',
				userId:''
			};
		},
		onShow() {
			this.userId=getApp().globalData.userId;
		},
		methods: {
			submitAction:function(){
				let that=this;
				if(!(that.zImg&&that.fImg)){
					uni.showToast({
						duration: 1500,
						title: '身份证正反面都需要上传哦！',
						icon: 'none'
					})
				}
				let data={
					vpndistributionAdd:{
						userId:that.userId,
						cardUrlA:that.zImg,
						cardUrlB:that.fImg
					}
				}
				vpndistributionAddsvc(data).then(res=>{
					if(res.code==1){
						uni.showToast({
							duration: 2000,
							title: '申请成功，请耐心等待审核！',
							icon: 'none'
						})
						setTimeout(()=>{
							uni.navigateBack({
								delta:1
							})
						},2000)
					}else{
						console.log(res.msg)
					}
				})
			},
			// 上传图片
			upLoadImg(e) {
				let imgtype=e.target.dataset.img;
				let that=this;
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						uni.showLoading({
							mask:true,
							title:'图片上传中···',
						})
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
								uni.hideLoading();
								let fileRes=JSON.parse(uploadFileRes.data);
								if(fileRes.success==1){
									if(imgtype=='isz'){
										that.zImg=fileRes.data.imageUrl
									}else if(imgtype=='isf'){
										that.fImg=fileRes.data.imageUrl
									}
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
			
		}
	}
</script>

<style lang="less">
.content{font-size:30rpx;}

.ad{height:auto;
	image{width:100%;display:block;}
}
.form{height:auto;padding:0rpx 30rpx;
	.item{width:100%;margin:0rpx auto;padding:30rpx 0rpx;border-bottom:1rpx solid #F2F2F2;justify-content: flex-start;background:#fff;align-items:initial;
		.title{width:140rpx;color:rgba(42,42,42,1);font-weight:500;}
		.content{flex: 1;min-height: 50rpx;position:relative;

		}
	}
	
	.uploadIDcard{height:auto;margin-top:30rpx;
		.title{color:rgba(42,42,42,1);font-weight:500;}
		.upload{width:500rpx;text-align:center;margin:20rpx auto;
			image{width:100%;display:block;}
		}

		.tip{text-align:center;font-size:28rpx;color:#666;}
	}
	.tips{height:auto;line-height:48rpx;margin-top:40rpx;
		.tit{color:#2E73FF;text-align:center;margin-bottom:20rpx;}
		.msg{color:#999;margin-top:20rpx;font-size:24rpx;}
	}
	.submitAction{width:100%;height:80rpx;background:linear-gradient(180deg,rgba(77,161,255,1),rgba(46,116,255,1));box-shadow:0rpx 6rpx 20rpx 0rpx rgba(46,116,255,0.59);border-radius:80rpx;margin:40rpx auto;color:#fff;}
}
</style>
