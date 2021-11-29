<template>
	<view class="content">
		<view class="form">
			<view class="item flexCenter">
				<text class="title">公司全称</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入您的公司的全称" v-model="json.companyname" placeholder-class="placeholderStyle" />
				</view>
			</view>

			<view class="item flexCenter">
				<text class="title">姓名</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入您的真实姓名" v-model="json.name" placeholder-class="placeholderStyle" />
				</view>
			</view>

			<view class="item flexCenter">
				<text class="title">公司职位</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入您所在公司的职位" v-model="json.job" placeholder-class="placeholderStyle" />
				</view>
			</view>

			<view class="uploadIDcard">
				<text class="title">上传营业执照</text>
				<view class="upload z" @click="upLoadImg">
					<image :src="companyImg?companyImg:'../../static/images/1276@2x.png'" mode="widthFix"></image>
				</view>
				<view class="tip">上传营业执照信息面</view>
			</view>

			<view class="tips">
				<view class="tit">注意事项</view>
				<view class="msg">
					1.拍摄与所填<text>公司全称一致</text>证件四边角完整(无缺角)。<br />
					2.最新下发的营业执照。<br />
					3.营业执照信息和公章清晰可辨。
				</view>
			</view>

			<view class='submitAction flexCenter' @click='subAjax'>
				<text>提交认证</text>
			</view>

		</view>

		<!-- <view class="switchtab">切换身份</view> -->

	</view>
</template>

<script>
	import config from '../../request/config.js'
	import {
		vpncompanyAddsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				json: {
					companyname: '',
					name: '',
					job: ''
				},
				companyImg: '',
				phonet: ''
			};
		},
		onShow() {
			this.phonet = getApp().globalData.userInfo.phoneNum;
		},
		methods: {
			subAjax: function() {
				let that = this;
				let data = {
					vpncompanyAdd: {
						phone: that.phonet,
						name: that.json.companyname,
						userName: that.json.name,
						job: that.json.job,
						licenseUrl: that.companyImg
					}
				}
				vpncompanyAddsvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						uni.redirectTo({
							url:'../success/success'
						})
					} else {
						uni.showToast({
							title:res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			upLoadImg() {
				let that = this;
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						let imgList = res.tempFiles[0];
						if (imgList.size > 2 * 1024 * 1024) {
							uni.showToast({
								title: '上传的图片不可以超过2M',
								icon: 'none',
								duration: 1500
							})
							return false
						}
						uni.showLoading({
							title: '上传中。。。',
							mask: true
						})
						let urlp = config.baseUrl.default;
						uni.uploadFile({
							url: urlp + '/vpnservice/upload',
							filePath: imgList.path,
							name: 'file',
							formData: {
								'user': 'test'
							},
							success: function(uploadFileRes) {
								let fileRes = JSON.parse(uploadFileRes.data);
								if (fileRes.success == 1) {
									that.companyImg = fileRes.data.imageUrl;
								} else {
									uni.showToast({
										title: fileRes.msg,
										duration: 1500,
										icon: 'none'
									})
								}
							},
							fail: function(errRes) {
								uni.showToast({
									title: errRes,
									icon: 'none',
									duration: 1500
								})
							},
							complete:function(){
								uni.hideLoading();
							}
						})
					}
				});
			},
		}
	}
</script>

<style lang="less">
	.content {
		font-size: 30rpx;
	}

	.ad {
		height: auto;

		image {
			width: 100%;
			display: block;
		}
	}

	.form {
		height: auto;
		padding: 0rpx 30rpx;
		border-top: 16rpx solid #F6F6F6;

		.item {
			width: 100%;
			margin: 0rpx auto;
			padding: 30rpx 0rpx;
			border-bottom: 1rpx solid #F2F2F2;
			justify-content: flex-start;
			background: #fff;
			align-items: initial;

			.title {
				width: 140rpx;
				color: rgba(42, 42, 42, 1);
				font-weight: 500;
			}

			.content {
				flex: 1;
				min-height: 50rpx;
				position: relative;

			}
		}

		.uploadIDcard {
			height: auto;
			margin-top: 30rpx;

			.title {
				color: rgba(42, 42, 42, 1);
				font-weight: 500;
			}

			.upload {
				width: 500rpx;
				text-align: center;
				margin: 20rpx auto;

				image {
					width: 100%;
					display: block;
				}
			}

			.tip {
				text-align: center;
				font-size: 28rpx;
				color: #666;
			}
		}

		.tips {
			height: auto;
			line-height: 48rpx;
			margin-top: 40rpx;

			.tit {}

			.msg {
				color: #999;
				margin-top: 20rpx;
				font-size: 24rpx;

				text {
					color: #2E73FF;
				}
			}
		}

		.submitAction {
			width: 100%;
			height: 80rpx;
			background: linear-gradient(180deg, rgba(77, 161, 255, 1), rgba(46, 116, 255, 1));
			box-shadow: 0rpx 6rpx 20rpx 0rpx rgba(46, 116, 255, 0.59);
			border-radius: 80rpx;
			margin: 40rpx auto;
			color: #fff;
		}

	}

	// .switchtab{text-align:center;color:#2E73FF;font-size:28rpx;margin-bottom:40rpx;}
</style>
