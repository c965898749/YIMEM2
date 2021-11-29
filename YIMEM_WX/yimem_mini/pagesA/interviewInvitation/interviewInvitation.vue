<template>
	<view class="content">
		<view class="detailBox">
			<view class="scrollItem" @click="goToPage">
				<view class="itemTop">
					<view class="oneTop">
						<text class="workName">行政前台</text>
						<text class="workMoney">5K-8K</text>
					</view>
					<view class="oneMid">
						<text>南通崇川区 | 1-3年 | 专科</text>
						<text>06/12</text>
					</view>
					<view class="tagList">
						<uni-tag text="五险一金" type="default" size="small"></uni-tag>
						<uni-tag text="五险一金" type="default" size="small"></uni-tag>
						<uni-tag text="五险一金" type="default" size="small"></uni-tag>
						<uni-tag text="五险一金" type="default" size="small"></uni-tag>
					</view>
				</view>
				<view class="lineRow"></view>
				<view class="compnyBox">
					<view>
						<image class="compnyIcon" src="../../static/images/jhzu1.png" mode=""></image>
					</view>
					<view class="compDetail">
						<view class="compnyName">
							柏莱特科技有限公司
						</view>
						<view class="aboutComp">
							未融资 | 20-30人 | 移动互联网
						</view>
					</view>
					<view class="phoneBox">
						<image class="phoneIcon" src="../../static/images/phonea.png" mode=""></image>
					</view>
				</view>
			</view>
		</view>
		<view class="cardBox">
			<view class="cardTitle"> <text class="cardShu"></text> 面试信息</view>
			<view class="cardContent">
				<view class="row">
					<view class="col_3">
						面试时间：
					</view>
					<view class="col_9">
						2020/06/14 10:30
					</view>
				</view>
				<view class="row">
					<view class="col_3">
						面试地点：
					</view>
					<view class="col_9">
						南通市崇川区恒隆国际A座15楼
					</view>
				</view>
				<view class="row">
					<view class="col_3">
						面试备注：
					</view>
					<view class="col_9">
						请携带纸质简历和学位证书
					</view>
				</view>
			</view>
		</view>
		<view class="btn-row">
			<button class="btn kong">拒绝</button>
			<button class="btn shi">同意</button>
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
		padding: 30rpx;
	}

	// 面试详情
	.scrollItem {
		border-radius: 10rpx;
		background: #FFFFFF;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
	}

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

	.oneTop {
		display: flex;
		justify-content: space-between;
	}

	.oneMid {
		padding: 30rpx 0rpx;
		color: #919191;
		font-size: 26rpx;
		display: flex;
		justify-content: space-between;
	}

	.itemTop {
		padding: 30rpx;
	}

	.workName {
		font-size: 32rpx;
		font-weight: bold;
		color: #2C2C2C;
	}

	.workMoney {
		color: #2E73FF;
		font-size: 32rpx;
	}

	.itemBot {
		padding: 30rpx;
	}

	.botBox {
		display: flex;
		justify-content: space-between;
	}

	.blackText {
		color: #2C2C2C;
		font-size: 28rpx;
	}

	.redText {
		font-size: 32rpx;
		color: #FE2B67;
	}

	.moneyIcon {
		width: 24rpx;
		height: 30rpx;
		vertical-align: -5rpx;
		margin-right: 10rpx;
	}

	.lineRow {
		border-bottom: 1rpx solid #F3F3F3;
	}

	/*公司盒子*/
	.compnyBox {
		display: flex;
		align-items: center;
		padding: 30rpx 30rpx 40rpx;
	}

	.compDetail {
		padding-left: 20rpx;
	}

	.compnyIcon {
		border: 1rpx solid #F3F3F3;
		width: 100rpx;
		height: 100rpx;
		vertical-align: -10rpx;
	}

	.compnyName {
		font-size: 30rpx;
		font-weight: bold;
		margin-bottom: 15rpx;
	}

	.aboutComp {
		color: #919191;
		font-size: 26rpx;
	}
	.phoneIcon{
		height: 60rpx;
		width: 60rpx;
	}
	.phoneBox{
		width: 80rpx;
		text-align: center;
	}
	.compDetail{
		flex: 1;
		overflow-x: hidden;
	}

	/*cardbox*/
	.cardBox {
		padding: 30rpx;
	}

	.cardTitle {
		font-size: 30rpx;
		font-weight: bold;
	}

	.cardShu {
		background: #2E73FF;
		display: inline-block;
		width: 6rpx;
		height: 30rpx;
		border-radius: 6rpx;
		margin-right: 10rpx;
		vertical-align: -5rpx;
	}

	.litText {
		font-size: 26rpx;
		color: #2C2C2C;
		line-height: 36rpx;
		padding: 15rpx 0;
	}

	.cardContent {
		padding-top: 20rpx;
	}

	// 行
	.row {
		line-height: 50rpx;
		color: #919191;
		font-size: 28rpx;
	}

	.row .col_9 {
		color: #2C2C2C;
	}

	// btnRow
	.btn-row {
		padding: 20rpx;
		display: flex;
		justify-content: space-between;
		position: fixed;
		bottom: 0;
		left: 0;
		width: 100%;
		border-top: 1rpx solid #F3F3F3;
	}

	.btn {
		border: 1rpx solid #2E73FF;
		font-size: 30rpx;
		border-radius: 100rpx;
		width: 45%;
	}

	.kong {
		color: #2E73FF;
		background-color: #FFFFFF;
	}

	.shi {
		background: #2E73FF;
		color: #FFFFFF;
		box-shadow: 0 0 10rpx 0 rgba(0, 0, 0, 0.2);
	}
</style>
