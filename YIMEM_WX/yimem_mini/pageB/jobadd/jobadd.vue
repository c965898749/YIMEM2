<template>
	<view class="content">
		<view class="">
			<view :style="'padding-top:'+topHeight"></view>
			<view class="tab">
				<view class="backIcon">
					<uni-icons type="back" size="25" color="#000001" @click="goBack"></uni-icons>
				</view>
				<text :class="json.type==1?'act':''" @click="tab(1)">全职</text>
				<text :class="json.type==2?'act':''" @click="tab(2)">兼职</text>
			</view>
		</view>
		<view class="form">
			<view class="item">
				<text class="title">职位名称</text>
				<view class="content">
					<input class="input" type="text" placeholder="请填写职位名称" v-model="json.name"
						placeholder-class="placeholderStyle" />
				</view>
			</view>
			<view class="item">
				<text class="title">{{json.type==1?'职位类型':'兼职类型'}}</text>
				<view class="content">
					<text @click="goPage" data-urlp="../../pagesA/treeJob/treeJob"
						:class="['select',json.jobCateId==0?'':'act']">{{json.jobCateId=='' ?'请选择职位类型' : json.jobCateName}}
						<text class="redText">{{json.jobCateId=='' ?'' :'-'+ json.jobMoney+'元/人'}}</text></text>
					<text class="icon_right"></text>
				</view>
			</view>

			<template v-if="json.type==1">
				<view class="row flexCenter">
					<view class="item">
						<text class="title">经验</text>
						<view class="content">
							<picker mode="selector" :range="QS_experience" range-key="cName" data-kind='QS_experience'
								@change="pickChange">
								<input type="text" disabled class="picker litInput" :value="json.jobExpName"
									placeholder="请选择工作经验" />
							</picker>
						</view>
					</view>
					<view class="item">
						<text class="title">最低学历</text>
						<view class="content">
							<picker mode="selector" :range="QS_education" range-key="cName" data-kind='QS_education'
								@change="pickChange">
								<input type="text" disabled class="picker litInput" :value="json.educationExpName"
									placeholder="请选择最低学历" />
							</picker>
						</view>
					</view>
					<view class="item">
						<text class="title">薪资范围</text>
						<view class="content col flexCenter">
							<input class="input litInput" type="text" placeholder="最低薪资" v-model="json.lowSalary"
								placeholder-class="placeholderStyle" />
							-
							<input class="input litInput" type="text" placeholder="最高薪资" v-model="json.higSalary"
								placeholder-class="placeholderStyle" />
						</view>
					</view>

				</view>

				<view class="item">
					<text class="title">职位描述</text>
					<view class="content" style="height: 80rpx;">
						<textarea value="" placeholder="请填写职位描述" v-model="json.jobContent" />
						<!-- <input class="input" type="text" placeholder="请填写职位描述" v-model="json.jobContent" placeholder-class="placeholderStyle" /> -->
					</view>
				</view>
				<view class="item">
					<text class="title">职位亮点</text>
					<view class="content labelList flexLeft">
						<view :class="['label', labelchose.includes(item)?'blueText':'']"
							v-for="(item,index) in labelList" :key="index" @click="setLight(item)">{{item.name}}</view>
					</view>
				</view>
			</template>
			<template v-if="json.type==2">
				<view class="item">
					<text class="title">招聘人数</text>
					<view class="content">
						<input class="input" type="text" placeholder="请填写招聘人数" v-model="json.peopleNum"
							placeholder-class="placeholderStyle" />
						<text class="dw">人</text>
					</view>
				</view>

				<view class="item" style="margin-left: 10rpx;">
					<text class="title">兼职时效</text>
					<view class="content ">
						<!-- labelList flexLeft -->
						<!-- <view :class="['label',json.timeliness=='短期'?'act':'']">短期</view>
						<view :class="['label',json.timeliness=='长期'?'act':'']">长期</view>
						<picker mode="selector" :range="jobType" @change="jobChange">
							<input type="text" disabled class="picker" :value="json.timeliness" placeholder="请选择兼职时效" />
						</picker> -->
						<view class="content labelList flexLeft">
							<u-subsection :list="list" :current="current" @change="logOrShot"></u-subsection>
						</view>
					</view>
				</view>

				<view class="item">
					<text class="title">兼职日期</text>
					<view class="content col flexCenter">
						<view class="input">
							<picker mode="date" data-datetype='startTime' @change="bindDateChange">
								<input type="text" disabled placeholder="开始时间" v-model="json.startTime"
									placeholder-class="placeholderStyle" />
							</picker>
						</view>
						-
						<view class="input">
							<picker mode="date" data-datetype='endTime' @change="bindDateChange">
								<input type="text" disabled placeholder="结束时间" v-model="json.endTime"
									placeholder-class="placeholderStyle" />
							</picker>
						</view>
					</view>
				</view>

				<view class="item">
					<text class="title">薪资水平</text>
					<view class="content">
						<input class="input" type="text" placeholder="请填写薪资水平" v-model="json.partTimeSalary"
							placeholder-class="placeholderStyle" />
						<text class="dw">元/天</text>
					</view>
				</view>

				<view class="item">
					<text class="title">结算方式</text>
					<view class="content">
						<picker mode="selector" :range="settlement" @change="bindSettlement">
							<input type="text" disabled class="picker" :value="json.payments" placeholder="请选择结算方式" />
						</picker>
					</view>
				</view>

				<view class="item">
					<text class="title">工作内容</text>
					<view class="content" style="height: 40rpx;">
						<textarea value="" placeholder="请填写职位描述" v-model="json.jobContent" />
						<!-- <input class="input" type="text" placeholder="请填写工作内容" v-model="json.jobContent" placeholder-class="placeholderStyle" /> -->
					</view>
				</view>
			</template>

			<view class="item">
				<text class="title">工作地点</text>
				<view class="content" @click="()=>{this.showPlace=true}">
					<view class="picker" v-if="regionCode.length==0" style="color: #919191;">
						请选择工作地点
					</view>
					<view class="picker" v-else>
						{{region[0]}}-{{region[1]}}
					</view>
					<u-select mode="mutil-column-auto" v-model="showPlace" @confirm="getArea" :list="multiSelector"></u-select>
				
				</view>
			</view>



			<view class="item">
				<text class="title">招聘人数</text>
				<view class="content">
					<input class="input" type="text" placeholder="请填写招聘人数" v-model="json.peopleNum"
						placeholder-class="placeholderStyle" />
					<text class="dw">人</text>
				</view>
			</view>





			<view class="agree">
				<checkbox-group @change="checkChange">
					<label>
						<checkbox value="1" />我已阅读并同意遵守
						<view class="blue" style="color: #2E73FF;">
							《依梦网职位委托规则》
						</view>

						，为保障您的利 益请仔细阅读
					</label>
				</checkbox-group>
			</view>

			<view class="submit flexCenter">
				<!-- <view class="submitSave flexCenter" @click="addJobAjax">
					<text>保存</text>
				</view> -->
				<view class="submitAction flexCenter'" @click="addJobAjax">
					<text>发布</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		vpnqsCategoryGetsvc,
		vpnjobLightenEnumGetsvc,
		vpnjobAddsvc,
		vpnjobGetbyidsvc
	} from "../../api/index.js"
	export default {
		data() {
			return {
				list: [{
						name: '短期'
					},
					{
						name: '长期'
					}
				],
				current: 1,
			

				topHeight: getApp().globalData.topHeight + 6 + 'px',
				json: {
					type: 1,
					name: '',
					jobCateId: '',
					jobCateName: '',
					jobMoney: '',
					jobExp: 0,
					lowSalary: '',
					higSalary: '',
					jobExpName: '',
					educationExp: 0,
					educationExpName: '',
					peopleNum: '',
					timeliness: '短期',
					startTime: '',
					endTime: '',
					partTimeSalary: '',
					payments: '',
					jobContent: '',
					adress: ''
				},
				jobType: [
					'短期',
					'长期'
				],
				settlement: [
					'天',
					'月',
					'季',
					'年'
				],
				agree: 0,
				labelList: [],
				labelchose: [],
				jobKind: '',
				QS_experience: [], //工作经验分类
				QS_education: [], //学历分类
				QS_experienceIndex: '',
				QS_educationIndex: '',
				region: [],
				regionCode: [],
				jzOrqz: {
					1: 1,
					2: 0
				},
				jobId: '', //编辑时的jobId
				jobDetail: {},
				compenyInfo: '',
				multiSelector: [{
					label: '南通市',
					value: '320600',
					children: [{
						label: '崇川区',
						value: '320602',
					}, {
						label: '港闸区',
						value: '320611',
					}, {
						label: '通州区',
						value: '320612',
					}, {
						label: '如东县',
						value: '320623',
					}, {
						label: '开发区',
						value: '320671',
					}, {
						label: '启东市',
						value: '320681',
					}, {
						label: '如皋市',
						value: '320682',
					}, {
						label: '海门市',
						value: '320684',
					}, {
						label: '海安市',
						value: '320685',
					}]
				}],
				showPlace:false
			};
		},
		onLoad(options) {
			this.forGetAll();
			this.getLightAjax();
			let compenyInfo = getApp().globalData.compenyInfo;
			if (compenyInfo.companyId) {
				this.compenyInfo = compenyInfo;
			} else {
				uni.showToast({
					duration: 1500,
					title: '请先绑定公司，或者等待审核通过',
					icon: 'none'
				})
			}
			this.jobId = options.jobId;
			if (options.jobId) {
				this.getJobDetail();
			}
		},
		onShow() {
			let jobKind = uni.getStorageSync('checkJob');
			uni.setStorageSync('checkJob', '')
			if (jobKind != '') {
				this.jobKind = jobKind;
				this.$set(this.json, 'jobCateId', jobKind.id);
				this.$set(this.json, 'jobCateName', jobKind.label)
				this.$set(this.json, 'jobMoney', jobKind.money)
			}

		},
		methods: {
			getArea(one) {
				this.region=[one[0].label,one[1].label];
				this.regionCode = [one[0].value,one[1].value];
			},
			logOrShot(indexs) {
				this.json.timeliness = this.list[indexs].name;
			},
			// 编辑，获取工作详情；
			getJobDetail() {
				let that = this;
				let data = {
					id: that.jobId
				}
				vpnjobGetbyidsvc(data).then(res => {
					if (res.code == 1) {
						that.jobDetail = res.data.vpnjobGetResp;
						that.initJob();
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 编辑，初始化数据
			initJob() {
				let jsont = {
					type: this.jobDetail.isAllDay == 1 ? 1 : 2,
					name: this.jobDetail.name || '',
					jobCateId: this.jobDetail.jobCateId || '',
					jobCateName: this.jobDetail.jobCateName || '',
					jobMoney: this.jobDetail.money || 0,
					jobExp: this.jobDetail.jobExp || '',
					higSalary: this.jobDetail.higSalary || '',
					lowSalary: this.jobDetail.lowSalary || '',
					jobExpName: this.jobDetail.jobExpName || '',
					educationExp: this.jobDetail.educationExp || '',
					educationExpName: this.jobDetail.educationExpName || '',
					peopleNum: this.jobDetail.peopleNum || '',
					timeliness: this.jobDetail.timeliness,
					startTime: this.jobDetail.startTime || '',
					endTime: this.jobDetail.endTime || '',
					partTimeSalary: this.jobDetail.partTimeSalary || '',
					payments: this.jobDetail.payments || '',
					jobContent: this.jobDetail.jobContent || '',
					// adress: this.jobDetail.adress || '',
				};
				this.current = this.jobDetail.timeliness == '长期' ? 1 : 0;
				this.regionCode = [this.jobDetail.cityId, this.jobDetail.countryId];
				this.region = [this.jobDetail.cityName, this.jobDetail.countryName];
				this.labelchose = this.jobDetail.jobLightspot;
				this.json = jsont;
			},
			// 点击添加
			setLight(one) {
				let chosed = new Set(this.labelchose);
				if (chosed.has(one)) {
					chosed.delete(one)
				} else {
					chosed.add(one)
				}
				this.labelchose = [...chosed]
			},
			// 获取切换的值
			checkChange(e) {
				let val = e.detail.value;
				this.agree = val[0];
			},
			// 选择兼职长短期
			jobChange(e) {
				let indexp = e.detail.value;
				this.json.timeliness = this.jobType[indexp];
			},
			// 切换结算方式
			bindSettlement(e) {
				let indexp = e.detail.value;
				this.json.payments = this.settlement[indexp];
			},
			// picker时间
			bindDateChange: function(e) {
				let types = e.target.dataset.datetype;
				let jsDate = e.target.value
				if (types == 'startTime') {
					this.$set(this.json, 'startTime', jsDate)
				} else if (types == "endTime") {
					this.$set(this.json, 'endTime', jsDate)
				}

			},
			
			// 获取亮点
			getLightAjax() {
				let that = this;
				let data = {
					vpnjobLightenEnumGet: {

					}
				}
				vpnjobLightenEnumGetsvc(data).then(res => {
					if (res.code == 1) {
						that.labelList = res.data.vpnjobLightenEnumGetResp;
					}
				})
			},
			// picker的Change
			pickChange(e) {
				let that = this;
				let kindType = e.currentTarget.dataset.kind;
				let val = e.detail.value;
				if (kindType == "QS_experience") {
					that.QS_experienceIndex = val;
					that.$set(that.json, 'jobExp', that.QS_experience[that.QS_experienceIndex].cId);
					// that.$set(that.json, 'jobExpName', that.QS_experience[that.QS_experienceIndex].cName);
					that.json.jobExpName = that.QS_experience[that.QS_experienceIndex].cName;
				} else if (kindType == "QS_education") {
					that.QS_educationIndex = val;
					that.$set(that.json, 'educationExp', that.QS_education[that.QS_educationIndex].cId);
					that.$set(that.json, 'educationExpName', that.QS_education[that.QS_educationIndex].cName);
				}
			},
			// 循环获取基础数据
			forGetAll() {
				let that = this;
				let allWant = ['QS_experience', 'QS_education'];
				allWant.map(item => {
					that.getAllajax(item)
				})
			},
			// 获取基础数据的请求
			getAllajax(otherName) {
				let that = this;
				let data = {
					vpnqsCategoryGet: {
						cAlias: otherName
					}
				}
				vpnqsCategoryGetsvc(data).then(res => {
					if (res.code == 1) {
						let resData = res.data.vpnqsCategoryGetResp
						if (otherName == 'QS_experience') {
							that.QS_experience = resData
						} else if (otherName == 'QS_education') {
							that.QS_education = resData
						}
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// 跳转
			goPage(e) {
				console.log(e)
				let url = e.currentTarget.dataset.urlp
				uni.navigateTo({
					url: url
				})
			},
			// 返回
			goBack: function() {
				uni.navigateBack({
					delta: 1
				})
			},

			tab: function(n) {
				this.json.type = n
			},
			addJobAjax() {
				let that = this;
				if (that.agree != 1) {
					uni.showToast({
						duration: 1500,
						title: '你还没有阅读《一梦职位委托规则》',
						icon: 'none'
					})
					return false
				}
				if (!(that.json.name && that.json.jobCateId && that.region && that.regionCode.length > 0 && that.json
						.jobContent)) {
					uni.showToast({
						duration: 1500,
						title: '请检查是否填写完整',
						icon: 'none'
					})
					return false
				}
				let data = '';
				let qdata = {
					vpnjobAdd: {
						jobId: that.jobId,
						companyId: that.compenyInfo.companyId,
						isAllDay: that.jzOrqz[that.json.type],
						name: that.json.name,
						jobCateId: that.json.jobCateId,
						jobExp: that.json.jobExp,
						educationExp: that.json.educationExp,
						countryId: that.regionCode[1],
						cityId: that.regionCode[0],
						jobContent: that.json.jobContent,
						jobLightspot: that.labelchose,
						lowSalary: that.json.lowSalary,
						higSalary: that.json.higSalary,
						peopleNum: that.json.peopleNum,
					}
				}
				let jdata = {
					vpnjobAdd: {
						jobId: that.jobId,
						companyId: that.compenyInfo.companyId,
						isAllDay: that.jzOrqz[that.json.type],
						name: that.json.name,
						jobCateId: that.json.jobCateId,
						countryId: that.regionCode[1],
						cityId: that.regionCode[0],
						peopleNum: that.json.peopleNum,
						startTime: that.json.startTime,
						endTime: that.json.endTime,
						partTimeSalary: that.json.partTimeSalary,
						timeliness: that.json.timeliness, //兼职时效
						payments: that.json.payments, //结算方式
						jobContent: that.json.jobContent,
					}
				}
				if (that.json.type == 1) {
					// 全职
					if (!(that.json.jobExp && that.json.educationExp)) {
						uni.showToast({
							duration: 1500,
							title: '请检查是否填写完整',
							icon: 'none'
						})
						return false
					}
					data = qdata;
				} else if (that.json.type == 2) {
					// 兼职
					if (!(that.json.peopleNum && that.json.startTime && that.json.endTime && that.json.partTimeSalary &&
							that.json.timeliness &&
							that.json.payments)) {
						uni.showToast({
							duration: 1500,
							title: '请检查是否填写完整',
							icon: 'none'
						})
						return false
					}
					data = jdata;
				}
				vpnjobAddsvc(data).then(res => {
					if (res.code == 1) {
						uni.showToast({
							duration: 1500,
							title: '发布成功，等待审核',
							icon: 'none'
						})
						setTimeout(function() {
							uni.navigateBack({
								delta: 1
							})
						}, 1300)
					} else {
						uni.showToast({
							duration: 1500,
							title: res.msg,
							icon: 'none'
						})
					}
				})
			},


		}
	}
</script>

<style lang="less">
	.content {
		font-size: 30rpx;
	}

	.tab {
		height: 32px;
		line-height: 32px;
		text-align: center;

		text {
			padding: 0rpx 60rpx;
			position: relative;
			color: #919191;
			font-size: 26rpx;
			display: inline-block;
		}

		.act {
			color: #2C2C2C;
			font-size: 32rpx;
			font-weight: bold;

			:after {
				content: '';
				width: 34rpx;
				height: 4rpx;
				background: linear-gradient(90deg, rgba(77, 162, 255, 1), rgba(46, 115, 255, 1));
				position: absolute;
				left: 50%;
				bottom: 0rpx;
				z-index: 99;
				border-radius: 10rpx;
				margin-left: -17rpx;
				box-shadow: 0rpx 2rpx 4rpx 0rpx rgba(46, 116, 255, 0.59);
			}
		}

	}

	.backIcon {
		position: absolute;
		text-align: center;

		text {
			padding: 0 20rpx;
		}
	}

	.form {
		padding: 0rpx 30rpx;
		flex: 1;
		overflow-y: auto;
		min-height: 20rpx;

		.item {
			width: 100%;
			margin: 0rpx auto;
			padding: 30rpx 0rpx;
			border-bottom: 1rpx solid #F2F2F2;

			.title {
				color: rgba(42, 42, 42, 1);
				font-weight: 500;
				font-size: 24rpx;
			}

			.content {
				margin-top: 20rpx;
				position: relative;

				.input {
					color: #444;
				}

				.select {
					color: #888;
				}

				.select.act {
					color: #444
				}

				.dw {
					position: absolute;
					right: 10rpx;
					top: 50%;
					z-index: 99;
					color: #2C2C2C;
					font-size: 30rpx;
					height: 60rpx;
					line-height: 60rpx;
					margin-top: -30rpx;
				}

				.icon_right {}
			}

			.labelList {
				margin: 10rpx -10rpx 0rpx;

				.label {
					background: #F5F5F5;
					color: #1B1B1B;
					font-size: 24rpx;
					margin: 10rpx;
					padding: 0rpx 30rpx;
					height: 50rpx;
					line-height: 45rpx;
				}

				.label.act {
					border: 1px solid #2E73FF;
					color: #2E73FF;
					background: #E7EFFF;
				}
			}
		}

		.col {
			.input {
				flex: 1;
				text-align: center;
			}
		}

		.row {
			border-bottom: 1rpx solid #F2F2F2;
			padding: 30rpx 0rpx;

			.item {
				flex: 1;
				border-right: 1rpx solid #F2F2F2;
				border-bottom: none;
				padding: 0rpx;
				text-align: center;

			}

			.item:last-child {
				border: none;
			}
		}

		.agree {
			.blue {
				display: inline-block;
			}

			margin-top: 30rpx;
			font-size: 24rpx;
			color: #919191;
			line-height: 40rpx;

			checkbox {
				transform: scale(0.6)
			}
		}

		.submit {
			padding: 30rpx 0rpx;

			view {
				flex: 1;
				margin: 0rpx 10rpx;
				height: 80rpx;
				border-radius: 80rpx;
				color: #fff;
			}

			.submitSave {
				border: 1px solid rgba(46, 115, 255, 1);
				color: rgba(46, 115, 255, 1);
			}

			.submitAction {
				background: linear-gradient(180deg, rgba(77, 162, 255, 1), rgba(46, 115, 255, 1));
				box-shadow: 0rpx 6rpx 20rpx 0rpx rgba(46, 116, 255, 0.59);
			}
		}

	}

	.blueText {
		background: #E7EFFF !important;
		border: 1px solid #2E73FF;
		border-radius: 2px;
		color: #2E73FF !important;
	}

	.redText {
		color: #FE2B67;
	}

	.litInput {
		font-size: 24rpx !important;
	}
</style>
