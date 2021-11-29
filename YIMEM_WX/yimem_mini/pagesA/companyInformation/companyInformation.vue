<template>
	<view class="content">
		<view class="form">
			<view class="item flexCenter">
				<text class="title">公司logo</text>
				<view class="content">
					<image @click="upLoadImg" class="logoGs" :src="comLogo?comLogo:'../../static/images/shangchuanlogo.png'" mode=""></image>
				</view>
			</view>
			<view class="item flexCenter">
				<text class="title">公司全称</text>
				<view class="content">
					<input class="input" type="text" disabled placeholder="请输入您的公司的全称" v-model="theCompany.name" placeholder-class="placeholderStyle" />
				</view>
			</view>

			<view class="item flexCenter">
				<text class="title">所属行业</text>
				<view class="content">
					<picker mode="selector" :range="QS_trade" range-key="cName" @change="pickChange">
						<input type="text" disabled class="input" :value="json.QS_tradeName" placeholder="请选择行业领域" />
					</picker>
				</view>
				<view class="rightIcon">
					<uni-icons type="forward" size="20" color="#7E7E7E"></uni-icons>
				</view>
			</view>

			<view class="item flexCenter">
				<text class="title">公司规模</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入公司规模" v-model="json.scale" placeholder-class="placeholderStyle" />
				</view>
				<!-- <view class="rightIcon">
					<uni-icons type="forward" size="20" color="#7E7E7E"></uni-icons>
				</view> -->
			</view>
			<view class="item flexCenter">
				<text class="title">公司地址</text>
				<view class="content">
					<input class="input" type="text" placeholder="请输入公司地址" v-model="json.place" placeholder-class="placeholderStyle" />
				</view>
				<!-- <view class="rightIcon">
					<uni-icons type="forward" size="20" color="#7E7E7E"></uni-icons>
				</view> -->
			</view>
			<view class="uploadIDcard">
				<text class="title">上传营业执照</text>
				<view class="upload z">
					<image :src="theCompany.licenseUrl" mode="widthFix"></image>
				</view>
			</view>
			<view class='submitAction flexCenter' @click='subAjax'>
				<text>保存信息</text>
			</view>

		</view>
	</view>
</template>

<script>
	import config from '../../request/config.js'
	import {
		vpnqsCategoryGetsvc,
		vpncompanyUpdatesvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				theCompany: '',
				json: {
					QS_tradeCode: '',
					QS_tradeName: '',
					scale: '',
					place: ''
				},
				comLogo: '',
				QS_trade: []
			}
		},
		onLoad() {
			this.getHangYe();
			let compenyInfo = getApp().globalData.compenyInfo;
			this.theCompany = compenyInfo;
			this.comLogo = compenyInfo.logourl;
			let jsonS={
				QS_tradeCode:compenyInfo.businessId,
				QS_tradeName:compenyInfo.businessName,
				scale:compenyInfo.scale,
				place:compenyInfo.adress
			}
			this.json=jsonS;
		},
		methods: {
			// 修改信息
			subAjax: function() {
				let that = this;
				let data = {
					vpncompanyUpdate: {
						companyId: that.theCompany.companyId,
						businessId: that.json.QS_tradeCode,
						logourl: that.comLogo,
						adress: that.json.place,
						scale:that.json.scale
					}
				}
				vpncompanyUpdatesvc(data).then(res => {
					console.log(res)
					if (res.code == 1) {
						uni.showToast({
							title: '公司完善成功',
							icon: 'none',
							duration: 1500
						})
						setTimeout(function() {
							uni.navigateBack({
								delta: 1
							})
						}, 1300)
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			// 获取行业列表
			getHangYe() {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: 'QS_trade'
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						that.QS_trade = res.data.vpnqsCategoryGetResp
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// picker的Change
			pickChange(e) {
				let val = e.detail.value;
				this.json.QS_tradeCode = this.QS_trade[val].cId;
				this.json.QS_tradeName = this.QS_trade[val].cName;
			},
			// 上传公司logo
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
									that.comLogo = fileRes.data.imageUrl;
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
							complete: function() {
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

	.logoGs {
		width: 150rpx;
		height: 150rpx;
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

			.rightIcon {
				width: 40rpx;
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
