<template>
	<view class="content">
		<view class="formBox">
			<view class="inputBox">
				<view class="inputName">
					证书名称
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<input type="text" v-model="certificate" placeholder="请输入证书名称">
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					获取证书时间
				</view>
				<view class="inputRow row">
					<view class="col_11">
						<picker mode="date" :start="startDate" :end="endDate" @change="bindDateChange">
							<input type="text" v-model="startTime" placeholder="请输入获取证书时间">
						</picker>
					</view>
				</view>
			</view>
			<view class="inputBox">
				<view class="inputName">
					请上传证书
				</view>
				<view class="inputRow row">
					<view class="upImg" @click="upLoadImg">
						<image class="imgIcon" v-if="certificateImg" :src="certificateImg" mode="widthFix"></image>
						<uni-icons v-else type="cloud-upload" color='#D8D7D7' size="30"></uni-icons>
					</view>
				</view>
			</view>
		</view>
		<view class="btnRow">
			<button class="subAdd" @click="subAdd">提交</button>
		</view>
	</view>
</template>

<script>
	import config from '../../request/config.js'
	import {
		vpncertificateAddsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				startDate: "1920-12-01",
				endDate: '2120-12-01',
				startTime: '',
				certificate: '',
				certificateImg: '',
				userId: ''
			};
		},
		onShow() {
			this.userId = getApp().globalData.userId;
		},
		methods: {
			// 选择时间
			bindDateChange(e) {
				this.startTime = e.detail.value;
			},
			// 上传证书
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
									that.certificateImg = fileRes.data.imageUrl
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
							}
						})
					}
				});
			},
			// 提交
			subAdd() {
				let that = this;
				if (!(that.startTime && that.certificate && that.certificateImg)) {
					uni.showToast({
						title: "请检查是否已全部填写！",
						duration: 1500,
						icon: "none"
					})
					return false
				}
				let data = {
					vpncertificateAdd: {
						userId: that.userId,
						name: that.certificate,
						acquireTime: that.startTime,
						url: that.certificateImg
					}
				}
				vpncertificateAddsvc(data).then(res => {
					if (res.code == 1) {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
						setTimeout(() => {
							uni.navigateBack({
								delta: 1
							})
						}, 1500)
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})

			},
		}
	}
</script>

<style lang="less">
	.formBox {
		padding: 26rpx;
		height: calc(100% - 100rpx);
		overflow-y: auto;
	}

	.inputBox {
		border-bottom: 1rpx solid #F2F2F2;
		margin-bottom: 30rpx;

		.inputName {
			font-size: 22rpx;
			color: #1B1B1B;
			margin-bottom: 30rpx;
		}

		.inputRow {
			margin-bottom: 30rpx;
			font-size: 30rpx;
			color: #919191;
		}
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
	}

	.remarkClass {
		width: 100%;
	}

	.upImg {
		width: 200rpx;
		height: 200rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		border: 1rpx solid #D8D7D7;
		border-radius: 5rpx;
	}

	.imgIcon {
		width: 100%;
		max-height: 100%;
	}
</style>
