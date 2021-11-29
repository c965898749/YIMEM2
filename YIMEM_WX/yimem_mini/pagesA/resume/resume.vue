<template>
	<view class="content">
		<view class="tapTop">
			<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
		</view>
		<view class="tapOne" v-if="activeIndex==0">
			<view class="headBox">
				<view class="moreGood">
					完善后职位匹配度更高哦！
				</view>
				<view class="upImgBox" @click="upLoadImg">
					<image class="upImg" :src="headImg" v-if="headImg" mode=""></image>
					<image class="upImg" v-else src="../../static/resumeIcon/shangchuan.png" mode=""></image>
				</view>
				<view class="previewBox">
					<image class="previewIcon" @click="toPage" :data-urlp="'../previewResume/previewResume?userid='+userId" src="../../static/resumeIcon/yulan.png" mode=""></image>
				</view>
			</view>
			<!-- 基本信息 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="infomationIcon" src="../../static/resumeIcon/information.png" mode=""></image>基本信息
						<text class="erroBox">必填</text>
					</view>
					<view class="">
						<image class="editIcon" @click="toPage" data-urlp="../basicInformation/basicInformation" src="../../static/resumeIcon/bianji22.png"
						 mode=""></image>
					</view>
				</view>
				<view class="cardContent">
					<view class="row">
						<view class="uname">
							{{resumeDetail.userName || '暂无'}}
						</view>
						<view class="uschool">
							<text v-if="resumeDetail.userSex">{{resumeDetail.userSex==1?'男':'女'}} | </text> <text v-if="resumeDetail.age">
								{{resumeDetail.age}}岁｜</text>
							<text> {{resumeDetail.education || '暂无'}} | </text> <text>
								{{resumeDetail.workage?resumeDetail.workage+'年':'暂无'}}|</text>
							<text>{{resumeDetail.cityName || '暂无'}}</text>
						</view>
					</view>
					<view class="row">
						<view class="col_6">
							<image class="phoneIcon" src="../../static/resumeIcon/zhiengduixiang.png" mode=""></image>
							{{resumeDetail.phoneNum || "暂无"}}
						</view>
						<view class="col_6">
							<image class="eamailIcon" src="../../static/resumeIcon/shiliangtu.png" mode=""></image>
							{{resumeDetail.emil || "暂无"}}
						</view>
					</view>
				</view>
			</view>
			<!-- 求职意向 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="intentionIcon" src="../../static/resumeIcon/yixiang.png" mode=""></image>求职意向
					</view>
					<view class="">
						<image class="editIcon" @click="goPage('../jobHunting/jobHunting',resumeDetail.jobWanteds[0])"  src="../../static/resumeIcon/bianji22.png"
						 mode=""></image>
					</view>
				</view>
				<view class="cardContent">
					<view class="row">
						<view class="col_2 huiText">
							职位类别
						</view>
						<view class="col_10">
							{{resumeDetail.jobWanteds[0].jobCateName || '暂无'}}
						</view>
					</view>
					<view class="row">
						<view class="col_2 huiText">
							期望薪资
						</view>
						<view class="col_10">
							{{resumeDetail.jobWanteds[0].expectedSalaryName || '暂无'}}
						</view>
					</view>
					<view class="row">
						<view class="col_2 huiText">
							工作地点
						</view>
						<view class="col_10">
							{{resumeDetail.jobWanteds[0].cityName || '暂无'}}
						</view>
					</view>
					<view class="row">
						<view class="col_2 huiText">
							工作性质
						</view>
						<view class="col_10">
							{{resumeDetail.jobWanteds[0].propertyName || '暂无'}}
						</view>
					</view>
					<view class="row">
						<view class="col_2 huiText">
							工作状态
						</view>
						<view class="col_10">
							{{resumeDetail.jobWanteds[0].stateName || '暂无'}}
						</view>
					</view>
				</view>
			</view>
			<!-- 工作/实习经验 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="workIcon" src="../../static/resumeIcon/gongzuojinyan.png" mode=""></image>工作/实习经验
					</view>
					<view class="">
						<text class="addTap" @click="toPage" data-urlp="../addWorkExperience/addWorkExperience">+ 添加</text>
					</view>
				</view>
				<view class="cardContent">
					<view class="oneBox" v-for="(item,index) in resumeDetail.jobExps" :key="index">
						<view class="oneItem">
							<view class="leftBox">
								<view class="scollRoundBox">
									<image class="scollRound" src="../../static/resumeIcon/tuoyuan.png" mode=""></image>
								</view>
								<view class="timeItem">
									{{item.startTime || '暂无'}}/{{item.endTime || '暂无'}}
								</view>
							</view>
							<view class="">
								<image class="editIcon" @click="goPage('../addWorkExperience/addWorkExperience',item)" src="../../static/resumeIcon/bianji22.png"
								 mode=""></image>
							</view>
						</view>
						<view class="oneContent">
							<view class="">
								{{item.company || '暂无'}}
							</view>
							<view class="">
								{{item.position || '暂无'}}
							</view>
						</view>
					</view>

				</view>
			</view>
			<!-- 教育经历 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="teachIcon" src="../../static/resumeIcon/jiaoyu.png" mode=""></image>教育经历
					</view>
					<view class="">
						<text class="addTap" @click="toPage" data-urlp="../addEducations/addEducations">+ 添加</text>
					</view>
				</view>
				<view class="cardContent">
					<view class="oneBox" v-for="(item,indext) in resumeDetail.educationExps" :key="indext">
						<view class="oneItem">
							<view class="leftBox">
								<view class="scollRoundBox">
									<image class="scollRound" src="../../static/resumeIcon/tuoyuan.png" mode=""></image>
								</view>
								<view class="timeItem">
									{{item.startTime || '暂无'}}/ {{item.endTime || '暂无'}}
								</view>
							</view>
							<view class="">
								<image class="editIcon" src="../../static/resumeIcon/bianji22.png" mode="" @click="goPage('../addEducations/addEducations',item)"></image>
							</view>
						</view>
						<view class="oneContent">
							<view class="">
								{{item.school || '暂无'}}
							</view>
							<view class="">
								{{item.educationName || '暂无'}} | {{item.majorName || '暂无'}}
							</view>
						</view>
					</view>

				</view>
			</view>
			<!-- 项目经验 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="projectIcon" src="../../static/resumeIcon/xiangmu.png" mode=""></image>项目经验
					</view>
					<view class="">
						<text class="addTap" @click="toPage" data-urlp="../addProjectExperience/addProjectExperience">+ 添加</text>
					</view>
				</view>
				<view class="cardContent">
					<view class="oneBox" v-for="(item,index) in resumeDetail.projectExps" :key="index">
						<view class="oneItem">
							<view class="leftBox">
								<view class="scollRoundBox">
									<image class="scollRound" src="../../static/resumeIcon/tuoyuan.png" mode=""></image>
								</view>
								<view class="timeItem">
									{{item.startTime || '暂无'}}/ {{item.endTime || '暂无'}}
								</view>
							</view>
							<view class="">
								<image @click="goPage('../addProjectExperience/addProjectExperience',item)" class="editIcon" src="../../static/resumeIcon/bianji22.png"
								 mode=""></image>
							</view>
						</view>
						<view class="oneContent">
							<view class="">
								{{item.title || '暂无'}}
							</view>
							<view class="">
								{{item.remark || '暂无'}}
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 语言能力 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="langugIcon" src="../../static/resumeIcon/duoyuyan.png" mode=""></image>语言能力
						<!-- <text class="erroBox">必填</text> -->
					</view>
					<view class="">
						<text class="addTap" @click="toPage" data-urlp="../addLanguage/addLanguage">+ 添加</text>
					</view>
				</view>
				<view class="cardContent">
					<view class="row huiTextRow" v-for="(item,index) in resumeDetail.languages" :key="index">
						<view class="col_2">
							语言种类
						</view>
						<view class="col_9">
							<text class="blackText">{{item.languageName || '暂无'}}</text>（听说能力{{item.speakAbilityName || '暂无'}} | 读写能力{{item.writeAbilityName || '暂无'}}）
						</view>
						<view class="col_1">
							<image class="editIcon" @click="goPage('../addLanguage/addLanguage',item)" src="../../static/resumeIcon/bianji22.png"
							 mode=""></image>
						</view>
					</view>
				</view>
			</view>
			<!-- 获得证书 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="bookIcon" src="../../static/resumeIcon/wenjianzhengshu.png" mode=""></image>获得证书
						<!-- <text class="erroBox">必填</text> -->
					</view>
					<view class="">
						<text class="addTap" @click="toPage" data-urlp="../addCertificate/addCertificate">+ 添加</text>
					</view>
				</view>
				<view class="cardContent">
					<view class="row huiTextRow" v-for="(item,index) in resumeDetail.certificates" :key="index">
						<view class="col_2">
							证书名称
						</view>
						<view class="col_8">
							<text class="blackText">{{item.name || '暂无'}}</text>（{{item.acquireTime || '暂无'}}）
						</view>
						<view class="col_2 textRight">
							<text class="blueText" @click="showCertificate(item.url)">查看证书</text>
						</view>
					</view>
				</view>
			</view>
			<!-- 自我评价 -->
			<view class="cardBox">
				<view class="cardTitle">
					<view class="tipLeft">
						<image class="msgIcon" src="../../static/resumeIcon/daipingjia.png" mode=""></image>自我评价
						<!-- <text class="erroBox">必填</text> -->
					</view>
					<view class="">
						<image class="editIcon" @click="toPage" data-urlp="../selfEvaluation/selfEvaluation" src="../../static/resumeIcon/bianji22.png"
						 mode=""></image>
					</view>
				</view>
				<view class="cardContent">
					<view class="row huiTextRow">
						{{resumeDetail.remark || '暂无'}}
					</view>
				</view>
			</view>
		</view>
		<view class="tapTwo" v-if="activeIndex==1">
			<view class="listBox" v-if="cvFile.id">
				<view class="listOne row">
					<view class="col_2">
						<image class="pdfIcon" src="../../static/images/jstpdf.png" mode=""></image>
					</view>
					<view class="col_9">
						<view class="twoName">
							{{cvFile.filename}}
						</view>
						<view class="twoTime">
							{{cvFile.createtime}}<text style="margin-left:20rpx;">{{renderSize(cvFile.filesize)}}</text>
						</view>
					</view>
					<view class="col_1" @click="delFile(cvFile.id)">
						<image class="delIcon" src="../../static/images/new_del_icon.png" mode=""></image>
					</view>
				</view>
			</view>
			<view class="uploadBox">
				<view class="btnUpdate" @click="chooseMessageFile">
					<image class="uploadIcon" src="../../static/images/jsupdown.png" mode="" ></image> 附件上传
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		vpnuserGetsvc,
		vpnuserUpdatesvc,
		vpncvFileServiceGetsvc,
		vpncvFileServiceDelsvc
	} from '../../api/index.js'
	import config from '../../request/config.js'
	import tapBar from "@/components/tapBar/tapBar.vue"
	export default {
		data() {
			return {
				imgUrl: getApp().globalData.imgUrl,
				options: ['在线简历', '附件简历'],
				activeIndex: 0,
				userId: '',
				resumeDetail: {},
				headImg: "",
				cvFile:{}
			};
		},
		components: {
			tapBar
		},
		onShow() {
			this.userId = getApp().globalData.userId;
			this.getResumeAjax();
			this.getResumeFile();
		},
		methods: {
			// 显示证书
			showCertificate(img) {
				uni.previewImage({
					urls: [img]
				});
			},
			// //切换tap
			onClickItem(e) {
				if (this.activeIndex != e) {
					this.activeIndex = e
				}
			},
			// 个人简历查询接口
			getResumeAjax() {
				let that = this;
				let data = {
					vpnuserGet: {
						userId: that.userId
					}
				}
				vpnuserGetsvc(data).then(res => {
					if (res.code == 1) {
						that.resumeDetail = res.data.vpnuserGetResp;
						that.headImg = res.data.vpnuserGetResp.headImg;
						uni.setStorageSync('resumeDetail', res.data.vpnuserGetResp)
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}

				})
			},
			// 简历附件上传
			chooseMessageFile: function (e) {
			    var that = this;
				let token= uni.getStorageSync('token');
			    wx.chooseMessageFile({
			      count: 1,
			      type: 'file',
			      success(res) {
			        var filename = res.tempFiles[0].name
			        console.info(filename);
			 
					let urlp = config.baseUrl.default;
					let fileinfo = res.tempFiles[0];
					if (fileinfo.size > 20 * 1024 * 1024) {
						uni.showToast({
							title: '上传的文件不可以超过20M',
							icon: 'none',
							duration: 1500
						})
						return false
					} 
					uni.uploadFile({
						url: urlp + '/vpnservice/vpncvFileServiceAddsvc',
						filePath: fileinfo.path,
						header: {
						'content-type': 'multipart/form-data',
						'charset':'UTF-8',
						'token': token,
						},
						name: 'file',
						formData: {
							'userId': that.userId,
							'filename': filename
						},
						success: function(uploadFileRes) {
							let fileRes = JSON.parse(uploadFileRes.data);
							if (fileRes.code == 1 && fileRes.data.cvFile!=null && fileRes.data.cvFile.length>0) {
								that.cvFile = fileRes.data.cvFile[0];
								
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
			  //取简历附件信息
			  getResumeFile(){
				  let that = this;
				  let data = {
				  	id: that.userId
				  }
				  vpncvFileServiceGetsvc(data).then(res => {
				  	if (res.code == 1 && res.data.cvFile!=null && res.data.cvFile.length>0) {
				  		that.cvFile = res.data.cvFile[0];
				  	} else {
				  	}
				  
				  })
				  
			  },
			  //删除简历附件
			  delFile(id){
				  let that = this;
				  let data = {
				  	id: id
				  }
				  vpncvFileServiceDelsvc(data).then(res => {
				  	if (res.code == 1 ) {
				  		that.cvFile = {};
				  	} else {
				  		uni.showToast({
				  			duration: 1500,
				  			title: res.msg,
				  			icon: 'none'
				  		})
				  	}
				  })
				  
			  },
			  renderSize(filesize) {
			          if (null == filesize || filesize == '') {
			              return "0 Bytes";
			          }
			          var unitArr = new Array("Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB");
			          var index = 0;
			          var srcsize = parseFloat(filesize);
			          index = Math.floor(Math.log(srcsize) / Math.log(1024));
			          var size = srcsize / Math.pow(1024, index);
			          size = size.toFixed(2);//保留的小数位数
			          return size + unitArr[index];
			      },
			// 选择头像
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
									that.editHead(fileRes);
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
			editHead(fileRes) {
				let that = this;
				let data = {
					vpnuserUpdate: {
						userId: that.userId,
						headImg: fileRes.data.imageUrl
					}
				}
				vpnuserUpdatesvc(data).then(res => {
					if (res.code == 1) {
						that.headImg = fileRes.data.imageUrl;
					}
				})
			},
			// 页面跳转
			toPage(e) {
				let urlp = e.currentTarget.dataset.urlp;
				uni.navigateTo({
					url: urlp
				})
			},
			// 跳转到教育经历编辑页面
			goPage(page, item) {
				console.log('需要编辑',item);
				uni.setStorageSync('oneEducation', item)
				uni.navigateTo({
					url: page
				})
			}
		}
	}
</script>

<style lang="less">
	.tapTop {
		padding: 0 100rpx;
	}

	.headBox {
		position: relative;
	}

	.moreGood {
		font-size: 20rpx;
		font-weight: 400;
		color: #919191;
		margin-left: 130rpx;
		padding-top: 10rpx;
	}

	.upImgBox {
		text-align: center;
		padding: 30rpx;
	}

	.upImg {
		width: 160rpx;
		height: 160rpx;
		border-radius: 160rpx;
	}

	.previewBox {
		position: absolute;
		right: 0;
		top: 70rpx;

		.previewIcon {
			width: 145rpx;
			height: 69rpx;
		}
	}

	.cardBox {
		padding: 0 25rpx;

		.cardTitle {
			padding: 25rpx 0;
			display: flex;
			justify-content: space-between;
			align-items: center;
			border-bottom: 1rpx solid #F2F2F2;

			.addTap {
				font-size: 24rpx;
				color: #2E73FF;
			}

			.tipLeft {
				font-size: 28rpx;
				font-weight: bold;
				color: #2C2C2C;



				.erroBox {
					margin-left: 10rpx;
					display: inline-block;
					font-size: 20rpx;
					font-weight: 400;
					color: #FFFFFF;
					width: 60rpx;
					height: 28rpx;
					text-align: center;
					background: #FE2B67;
					border-radius: 0rpx 15rpx 15rpx 20rpx;
				}
			}
		}

		.cardContent {
			font-size: 24rpx;
			color: #2C2C2C;
			line-height: 50rpx;
			padding: 20rpx 0;
		}
	}

	//oneBox
	.oneBox {
		.oneItem {
			display: flex;
			justify-content: space-between;
			font-size: 24rpx;

			.leftBox>view {
				display: inline-block;
			}

			.timeItem {
				color: #919191;
			}
		}

		.oneContent {
			padding-left: 30rpx;
			margin-left: 17rpx;
			border-left: 1rpx solid #F2F2F2;
		}
	}

	//oneItem
	.huiText {
		color: #919191;
	}

	.huiTextRow {
		color: #919191;

		.col_1 {
			text-align: right;
		}
	}

	.blackText {
		color: #2C2C2C;
	}

	.uname {
		display: inline-block;
		font-size: 26rpx;
		font-weight: bold;
		color: #2C2C2C;
		margin-right: 15rpx;
	}

	.uschool {
		display: inline-block;
		font-size: 24rpx;
		font-weight: 500;
		color: #919191;
	}

	// icon
	.scollRound {
		width: 36rpx;
		height: 36rpx;
		vertical-align: -10rpx;
	}

	.langugIcon {
		width: 27rpx;
		height: 27rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.bookIcon {
		width: 27rpx;
		height: 27rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.msgIcon {
		width: 27rpx;
		height: 25rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.projectIcon {
		width: 26rpx;
		height: 26rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.teachIcon {
		width: 34rpx;
		height: 24rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.infomationIcon {
		width: 25rpx;
		height: 29rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	.editIcon {
		width: 30rpx;
		height: 30rpx;
	}

	.phoneIcon {
		width: 18rpx;
		height: 27rpx;
		margin-right: 20rpx;
		vertical-align: -5rpx;
	}

	.eamailIcon {
		width: 20rpx;
		height: 16rpx;
		margin-right: 20rpx;
	}

	.workIcon {
		width: 28rpx;
		height: 26rpx;
		margin-right: 15rpx;
	}

	.intentionIcon {
		width: 30rpx;
		height: 30rpx;
		margin-right: 15rpx;
		vertical-align: -4rpx;
	}

	// tapTwo

	.listBox {
		padding: 26rpx;
	}

	.listOne {
		border-bottom: 1rpx solid #F2F2F2;
		padding: 28rpx 0;
	}

	.listOne .col_1 {
		text-align: right;
	}

	.pdfIcon {
		width: 47rpx;
		height: 48rpx;
		margin-top: 15rpx;
	}

	.delIcon {
		width: 22rpx;
		height: 24rpx;
		margin-top: 20rpx;
	}

	.twoName {
		font-size: 28rpx;
		font-weight: bold;
		color: #2C2C2C;
		margin-bottom: 10rpx;
	}

	.twoTime {
		font-size: 22rpx;
		font-weight: 400;
		color: #7E7E7E;
	}

	.uploadBox {
		margin-top: 80rpx;
	}

	.btnUpdate {
		margin: 0 auto;
		width: 560rpx;
		height: 94rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
		border-radius: 47rpx;
		color: #FFFFFF;
		text-align: center;
		font-size: 30rpx;
		line-height: 94rpx;
	}

	.uploadIcon {
		width: 45rpx;
		height: 38rpx;
		margin-right: 20rpx;
		vertical-align: -10rpx;
	}

	.blueText {
		font-size: 20rpx;
		font-weight: 400;
		color: #2E73FF;
	}

	.textRight {
		text-align: right;
	}
</style>
