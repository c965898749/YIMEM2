<template>
	<view class="content">
		<!-- <view class="tapTop">
			<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
		</view> -->
		<!-- <navBarTop title="简历预览" imgNavBg="../../static/images/hjessddrr.png" backgroundColor="rgba(0,0,0,0)" color="#FFFFFF"></navBarTop> -->
		
		<u-tabs name="cate_name" count="cate_count" :list="list" :is-scroll="false" :current="current" @change="change"></u-tabs>
		
		<view class="contentBox" v-if="current==0">
			<view class="headerBox">
				<view class="boxImg">
					<image class="headerImg" :src="resumeDetail.headImg?resumeDetail.headImg:'../../static/resumeIcon/shangchuan.png'"
					 mode="aspectFill"></image>
					<image v-if="resumeDetail.userSex==0" class="sexIcon" src="../../static/resumeIcon/girl.png" mode=""></image>
					<image v-else class="sexIcon" src="../../static/images/boy.png" mode=""></image>
				</view>
				
				<view class="boxTop">
					<view class="left">
						<view class="nameText">
							{{resumeDetail.userName || '暂无'}}
						</view>
						<view class="aboutUser">
							{{resumeDetail.userSex == 0?'女':'男'}}｜{{resumeDetail.age || '0'}}岁｜{{resumeDetail.education || '暂无'}} |
							{{resumeDetail.workage || '0'}}年 | {{resumeDetail.censusCountryName || '暂无'}}
						</view>
							
					</view>
				
				
				<view class="right">
					
					<view class="heartBox" @tap="toSave">
						<uni-icons type="heart" v-if="!isHave"  size="15"></uni-icons>
						<uni-icons type="heart-filled" v-if="isHave"  color='red' size="15"></uni-icons>
						<view class="odd">收藏</view>
					</view>
					
				</view>
						
						
				
				</view>
				
				
			</view>
		<view class="rowLine">
			<view class="">
				<image class="iconPhone" src="../../static/resumeIcon/zhiengduixiang.png" mode=""></image>
				{{resumeDetail.phoneNum || '暂无'}}
			</view>
			<view class="">
				<image class="iconEmail" src="../../static/resumeIcon/shiliangtu.png" mode=""></image>
				{{resumeDetail.emil || '暂无'}}
			</view>
		</view>
			
			
			<view class="allContent">
				<!-- 求职意向 -->
				<view class="cardBox">
					<view class="cardTitle">
						<view class="tipLeft">
							求职意向
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
							工作/实习经验
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
							教育经历
						</view>
					</view>
					<view class="cardContent">
						<view class="oneBox" v-for="(item,index) in resumeDetail.educationExps" :key="index">
							<view class="oneItem">
								<view class="leftBox">
									<view class="scollRoundBox">
										<image class="scollRound" src="../../static/resumeIcon/tuoyuan.png" mode=""></image>
									</view>
									<view class="timeItem">
										{{item.startTime}}/{{item.endTime}}
									</view>
								</view>
							</view>
							<view class="oneContent">
								<view class="">
									{{item.school}}
								</view>
								<view class="">
									{{item.educationName}} | {{item.majorName}}
								</view>
							</view>
						</view>

					</view>
				</view>
				<!-- 项目经验 -->
				<view class="cardBox">
					<view class="cardTitle">
						<view class="tipLeft">
							项目经验
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
										{{item.startTime}}/{{item.endTime}}
									</view>
								</view>
							</view>
							<view class="oneContent">
								<view class="">
									{{item.title}}
								</view>
								<view class="">
									{{item.remark}}
								</view>
							</view>
						</view>
					</view>
				</view>
				<!-- 语言能力 -->
				<view class="cardBox">
					<view class="cardTitle">
						<view class="tipLeft">
							语言能力
						</view>
					</view>
					<view class="cardContent">
						<view class="row huiTextRow" v-for="(item,index) in  resumeDetail.languages" :key="index">
							<view class="col_2">
								语言种类
							</view>
							<view class="col_9">
								<text class="blackText">{{item.languageName}}</text>（听说能力{{item.speakAbilityName}} | 读写能力{{item.writeAbilityName}}）
							</view>
						</view>
					</view>
				</view>
				<!-- 获得证书 -->
				<view class="cardBox">
					<view class="cardTitle">
						<view class="tipLeft">
							获得证书
						</view>
					</view>
					<view class="cardContent">
						<view class="row huiTextRow" v-for="(item,index) in resumeDetail.certificates" :key="index">
							<view class="col_2">
								证书名称
							</view>
							<view class="col_8">
								<text class="blackText">{{item.name}}</text>（{{item.acquireTime}}）
							</view>
							<view class="col_2 seeBook" @click="showImg(item)">
								查看证书
							</view>
						</view>
					</view>
				</view>
				<!-- 自我评价 -->
				<view class="cardBox">
					<view class="cardTitle">
						<view class="tipLeft">
							自我评价
						</view>
					</view>
					<view class="cardContent">
						<view class="selfSay">
							{{resumeDetail.remark}}
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="tapTwo" v-if="current==1 && cvFile.id">
			<view class="listBox" >
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
				</view>
			</view>
			<view class="uploadBox">
				<view class="btnUpdate" @click="downloadFile">
					<image class="uploadIcon" src="../../static/images/jsupdown.png" mode="" ></image> 附件下载
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import navBarTop from "@/components/nav-bar-top/nav-bar-top.vue"
	import {
		vpnuserGetsvc,
		vpncompanycollectdevAddsvc,
		vpncompanycollectdevsvc,
		vpncvFileServiceGetsvc
	} from '../../api/index.js'
	export default {
		data() {
			return {
				isHave: false, //是否收藏
				resumeDetail: '',
				userId: '',//简历的用户id
				isLogin: '',
				compenyInfo: '',
				cvFile:{},
				list: [{
									cate_name: '在线简历'
								}, {
									cate_name: '附件简历'
								}, ],
								current: 0
			}
		},
		components: {
			navBarTop
		},
		onLoad(options) {
			this.userId = parseInt(options.userid);
		},
		onShow() {
			// this.resumeDetail = uni.getStorageSync('resumeDetail');
			//this.userId = getApp().globalData.userId;
			this.isLogin = getApp().globalData.isLogin;
			this.compenyInfo = getApp().globalData.compenyInfo;
			this.userDetail();
			this.isHaveGet();
			this.getResumeFile();
			
		},
		methods: {
			// 收藏
			toSave() {
				if (this.isLogin == 0) {
					uni.showModal({
						title: '提示',
						content: '请先登录，才能收藏哦',
						success: (res) => {
							if (res.confirm) {
								uni.switchTab({
									url: '../../pages/myIndex/myIndex'
								})
							}
						}
					});
					return false;
				}
				let that = this;
				this.isHave = !this.isHave;
				let isGet = this.isHave;
				let states = ''
				if (isGet == true) {
					states = 1
				} else {
					states = 0
				}
				let data = {
					vpncollectAdd: {
						companyId: that.compenyInfo.companyId,
						devUserId: that.userId,
						state: states
					}
				}
				vpncompanycollectdevAddsvc(data).then(res => {
					if (res.code == 1) {
						if (states == 1) {
							uni.showToast({
								title: "收藏成功",
								icon: 'none',
								duration: 1500
							})
						} else {
							uni.showToast({
								title: "取消成功",
								icon: 'none',
								duration: 1500
							})
						}
			
					} else {
						uni.showToast({
							title: res.msg,
							icon: 'none',
							duration: 1500
						})
					}
				})
			},
			
			// 查看是否收藏过
			isHaveGet() {
				let that = this;
				let data = {
					companyId: that.compenyInfo.companyId,
					devUserId: that.userId
				}
				vpncompanycollectdevsvc(data).then(res => {
					if (res.code == 1) {
						if (res.data.iscollect == 1) {
							that.isHave = true;
						} else {
							that.isHave = false;
						}
					} else {
						console.log("是否收藏", res.msg)
					}
				})
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
						/* uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						}) */
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
				downloadFile: function(){
					let that = this;
					if(that.cvFile!=null  && that.cvFile.url!=null && that.cvFile.url!=''){
						wx.downloadFile({
							url:that.cvFile.url, 
							success:function(res){
								console.log(res);
								var rr=res.tempFilePath; 
								wx.openDocument({
								    filePath: res.tempFilePath,
								    success(){
										console.log("1111");
								    }
								})
							}
						})
					}else{
						uni.showToast({
							title:'文件不存在',
							duration:1500,
							icon:'none'
						})
					}
				},
			// 切换头部
				change(index) {
							this.current = index;
						},
						
			showImg(item) {
				uni.previewImage({
					urls: [item.url]
				});
			},
			userDetail() {
				let that = this;
				let data = {
					vpnuserGet: {
						userId: that.userId
					}
				}
				vpnuserGetsvc(data).then(res=>{
					if(res.code==1){
						that.resumeDetail=res.data.vpnuserGetResp;
					}else{
						uni.showToast({
							title:res.msg,
							duration:1500,
							icon:'none'
						})
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.contentBox {
		flex: 1;
		min-height: 20rpx;
		padding: 30rpx 26rpx 0;
		overflow-y: auto;
		.headerBox{
			display: flex;
		}
	
	}

	.allContent {
		background: #FFFFFF;
	}

	.boxImg {
		// margin: 0 auto;
		width: 137rpx;
		height: 137rpx;
		position: relative;

		.headerImg {
			// margin-left:29rpx;
			width: 133.8rpx;
			height: 136.8rpx;
			 
border-radius: 50%;
			background: #FFFFFF;
			border: 6rpx solid #FFFFFF;
			box-shadow: 0rpx 2rpx 14rpx 0rpx rgba(207, 207, 207, 0.35);
		}

		.sexIcon {
			width: 30rpx;
			height: 30rpx;
			position: absolute;
			bottom: 0;
			right: 0rpx;
		}
	}


	.nameText {
		font-size: 36rpx;
		font-weight: bold;
		color: #2C2C2C;
		// text-align: center;
		margin-top: 15rpx;
		margin-bottom: 15rpx;
		font-family: PingFang SC;
	}

	.aboutUser {
		font-size: 26rpx;
		font-weight: 500;
		color: #999999;
		// text-align: center;
		margin-bottom: 15rpx;
	}

	.iconPhone {
		width: 18rpx;
		height: 27rpx;
		vertical-align: -5rpx;
		margin-right: 10rpx;
	}

	.iconEmail {
		width: 20rpx;
		height: 16rpx;
		margin-right: 10rpx;
	}

	.boxTop {
		display: flex;
		padding-left: 30rpx;
		padding-bottom: 30rpx;
		border-bottom: 1rpx solid #F2F2F2;
		.heartBox{
			display: flex;
			margin-top: 75rpx;
			margin-left: 20rpx;
			// width: 49rpx;
			// height: 25rpx;
		}
	}
 
	.rowLine {
		// margin: 10rpx;
		font-size: 26rpx;
		color: #2C2C2C;
		view{
			margin: 20rpx 0;
		}
	}

	// 详情后
	.cardBox {
		border-bottom: 1rpx solid #F2F2F2;

		.cardTitle {
			padding: 25rpx 0 0;
			display: flex;
			justify-content: space-between;
			align-items: center;

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
		font-size: 26rpx;
		font-weight: bold;
		color: #2C2C2C;
	}

	.uschool {
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
		width: 24rpx;
		height: 24rpx;
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

	.seeBook {
		font-weight: 400;
		color: #2E73FF;
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
</style>
