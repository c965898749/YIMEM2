<template>
	<view class="content">
		<view class="topTab">
			<tapBar :activeIndex="activeIndex" :options="options" @tapChange="onClickItem"></tapBar>
		</view>
		<scroll-view scroll-y="true" class="scrollHeight">
			<view class="listBox">
				<view class="oneBox">
					<view class="oneTop row">
						<view class="col_9">
							<view class="jobName">
								平面设计师 <image class="jobIcon" src="../../static/images/somejob.png" mode=""></image>
								<image class="jobIcon" src="../../static/images/alljob.png" mode=""></image>
							</view>
							<view class="jobGood">
								南通崇川区 | 1-3年 | 专科 | 5-8k
							</view>
						</view>
						<view class="col_3">
							<view class="getJobNum">
								60
							</view>
							<view class="jobTip">
								应聘简历
							</view>
						</view>
					</view>
					<view class="oneBottom" v-if="activeIndex==0">
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="compose" color='#2E73FF' size='15'></uni-icons>编辑
						</view>
						<view class="shuHui">

						</view>
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="reload" color='#2E73FF' size='15'></uni-icons>刷新
						</view>
						<view class="shuHui">

						</view>
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="close" color='#2E73FF' size='15'></uni-icons>关闭
						</view>
					</view>
					<view class="oneBottom" v-if="activeIndex==1">
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="trash" color='#2E73FF' size='15'></uni-icons>删除
						</view>
						<view class="shuHui">

						</view>
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="compose" color='#2E73FF' size='15'></uni-icons>编辑
						</view>
						<view class="shuHui">

						</view>
						<view class="oneBtn">
							<uni-icons class="iconMarg" type="paperplane" color='#2E73FF' size='15'></uni-icons>发布
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
		<view class="btn-row">
			<view class="btnBlue">
				发布新职位
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				options: ['发布中', '已关闭'],
				activeIndex: 1,
			};
		},
		methods: {
			//切换tap
			onClickItem(e) {
				if (this.activeIndex != e) {
					this.activeIndex = e
				}
			},
		}
	}
</script>

<style lang="less">
	.topTab {
		height: 70rpx;
	}

	.scrollHeight {
		height: calc(100% - 70rpx);
	}

	.listBox {
		padding: 20rpx 25rpx 100rpx;
		background: #F8F8F8;
		height: 100%;
	}

	.oneBox {
		min-height: 244rpx;
		background: #FFFFFF;

		.oneTop {
			.jobName {
				font-size: 32rpx;
				font-weight: bold;
				color: #2C2C2C;

				.jobIcon {
					width: 62rpx;
					height: 32rpx;
					vertical-align: -10rpx;
					margin-left: 15rpx;
				}
			}

			.jobGood {
				font-size: 22rpx;
				font-weight: 400;
				color: #2C2C2C;
				margin-top: 25rpx;
			}

			.getJobNum {
				font-size: 42rpx;
				font-weight: 500;
				color: #2E73FF;
				text-align: center;
			}

			.jobTip {
				margin-top: 15rpx;
				text-align: center;
				font-size: 22rpx;
				font-weight: 400;
				color: #919191;
			}

			padding: 30rpx;
			border-bottom: 1rpx solid #F2F2F2;
		}

		.oneBottom {
			font-size: 24rpx;
			line-height: 84rpx;
			display: flex;
			align-items: center;

			.oneBtn {
				flex: 1;
				text-align: center;
			}

			.shuHui {
				display: inline-block;
				width: 1rpx;
				height: 30rpx;
				border: 1rpx solid #F2F2F2;
			}

			.iconMarg {
				margin-right: 10rpx;
				vertical-align: -3rpx;
			}
		}
	}

	.btn-row {
		position: fixed;
		width: 100%;
		left: 0;
		bottom: 0;
		padding: 14rpx 30rpx;

		.btnBlue {
			width: 690rpx;
			height: 70rpx;
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			box-shadow: 0px 8px 23px 1px rgba(51, 122, 255, 0.59);
			border-radius: 35rpx;
			text-align: center;
			line-height: 70rpx;
			font-weight: 500;
			color: #FFFFFF;
		}
	}
</style>
