<template>
	<view class="content">
		<view class="detailBox">
			<view class="topBox">
				<view class="row">
					<view class="col_9 jobName">
						房产置业顾问
						<image class="jobIcon" src="../../static/images/somejob.png" mode=""></image>
						<image class="jobIcon" src="../../static/images/alljob.png" mode=""></image>
					</view>
					<view class="col_3 jobMoney">
						8-15K
					</view>
				</view>
				<view class="row">
					<view class="col_10 youNeed">
						南通开发区 | 1-3年 | 本科
					</view>
					<view class="col_2 timeText">
						06/12
					</view>
				</view>
			</view>
			<view class="oneCard">
				<view class="cardTitle">
					<text class="blueShu"></text>职位亮点
				</view>
				<view class="tagList">
					<uni-tag text="五险一金" type="default" size="small"></uni-tag>
					<uni-tag text="五险一金" type="default" size="small"></uni-tag>
					<uni-tag text="五险一金" type="default" size="small"></uni-tag>
					<uni-tag text="五险一金" type="default" size="small"></uni-tag>
				</view>
			</view>
			<view class="oneCard">
				<view class="cardTitle">
					<text class="blueShu"></text>职位详情
				</view>
				<view class="cardContent">
					<view class="jobLitTip">
						岗位职责:
					</view>
					<view class="jobListRow">
						<view class="jobTextRow">
							1,通过电话与QQ微信与客户进行有效沟通；
						</view>
						<view class="jobTextRow">
							2,具有良好的创新力，执行力，有团队精神；
						</view>
						<view class="jobTextRow">
							3,愿意长时间从事本工作；
						</view>
					</view>

					<view class="jobLitTip">
						任职资格：
					</view>
					<view class="jobListRow">
						<view class="jobTextRow">
							1,年龄：18-30岁；
						</view>
						<view class="jobTextRow">
							2,接受无经验的应届生，转行人事，只要踏实认真，公司有人
							愿意带你做；
						</view>
						<view class="jobTextRow">
							3,愿意长时间从事本工作；
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="btn-row">
			<view class="left-btn btn">
				关闭职位
			</view>
			<view class="right-btn btn">
				编辑职位
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style lang="less">
	.detailBox {
		padding: 26rpx 26rpx 100rpx;
	}

	.topBox {
		padding: 24rpx;
		line-height: 60rpx;
		min-height: 145rpx;
		background: #FFFFFF;
		box-shadow: 0rpx 0rpx 13rpx 0rpx rgba(199, 199, 199, 0.35);

		.jobName {
			font-size: 32rpx;
			font-weight: bold;
			color: #2C2C2C;

			.jobIcon {
				width: 62rpx;
				height: 32rpx;
				vertical-align: -10rpx;
				margin-left: 10rpx;
			}
		}

		.jobMoney {
			font-size: 32rpx;
			font-weight: 500;
			color: #2E73FF;
			text-align: right;
		}

		.youNeed {
			font-size: 26rpx;
			font-weight: 500;
			color: #2C2C2C;
		}

		.timeText {
			font-size: 22rpx;
			font-weight: 400;
			color: #919191;
			text-align: right;
		}
	}

	.oneCard {
		margin-top: 20rpx;

		.cardTitle {
			padding: 30rpx 0;
			font-size: 30rpx;
			font-weight: bold;
			color: #2C2C2C;

			.blueShu {
				display: inline-block;
				width: 6rpx;
				height: 30rpx;
				background: linear-gradient(0deg, #2E73FF, #4DA2FF);
				border-radius: 6rpx;
				vertical-align: -5rpx;
				margin-right: 13rpx;
			}
		}
	}

	// tagList
	.tagList {
		display: flex;
		flex-wrap: wrap;
	}

	.tagList text {
		color: #999999 !important;
	}

	.uni-tag {
		margin-right: 10rpx !important;
		margin-bottom: 10rpx !important;
	}

	// 职位亮点
	.jobLitTip {
		font-size: 28rpx;
		font-weight: 500;
		color: #2C2C2C;
	}

	.jobListRow {
		padding: 20rpx 0 40rpx;
	}

	.jobTextRow {
		line-height: 40rpx;
		font-size: 26rpx;
		font-weight: 500;
		color: #2C2C2C;
	}

	// fixed按钮行
	.btn-row {
		position: fixed;
		bottom: 0;
		left: 0;
		height: 100rpx;
		width: 750rpx;
		display: flex;
		justify-content: space-around;
		border-top:1rpx solid #E7E7E7;
		padding: 15rpx 20rpx;
		.btn{
			width: 335rpx;
			height: 70rpx;
			border-radius: 35rpx;
			font-size: 26rpx;
			font-weight: 500;
			line-height: 68rpx;
			text-align: center;
		}
		.left-btn{
			background: rgba(47, 117, 255, 0.12);
			border: 1rpx solid #2F75FF;
			text-align: center;
			color: #2F75FF;
		}
		.right-btn{
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
			color: #FFFFFF;
		}
	}
</style>
