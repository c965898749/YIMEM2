<template>
	<view class="content">
		<view class="topSearch">
			<view class="topSearchLeft">
				<uni-search-bar placeholder="搜索姓名" @input="changeVal" :radius="100" @confirm="searchs" cancelButton="none"></uni-search-bar>
			</view>
			<view class="topSearchRit" @click="openSelect">
				<view class="noTap" v-if="!selectShow">
					<image src="../../static/images/jhsx0.png" mode=""></image>筛选
				</view>
				<view class="hasTap" v-if="selectShow">
					<image src="../../static/images/jhsx1.png" mode=""></image>筛选
				</view>
			</view>
		</view>
		<scroll-view scroll-y="true" class="scrollBox">
			<view class="allList">
				<view class="oneItem" v-for="(item,index) in compJobList" :key="index" @click="goPage(item)">
					<view class="pepole">
						<view class="pname">
							{{item.name}}
						</view>
						<view class="">
							<text class="tagBox">{{item.state | filterState}}</text>
						</view>
					</view>
					<view class="lineRow"></view>
					<view class="pContent">
						<view class="pText">
							<text class="pTip">应聘职位：</text> <text>{{item.jobName || ""}}</text>
						</view>
						<view class="pText">
							<text class="pTip">报名时间：</text> <text>{{item.createTime}}</text>
						</view>
						<view class="pText">
							<text class="pTip">预估收益：</text> <text class="redMonye">￥{{item.money || 0}}</text>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
		<uni-popup ref="popup" type="bottom" @change="isClose">
			<view class="whiteBack">
				<view :class="['selectOne',selectIndex==item.id?'blueColor':'']" v-for="(item,index) in seleList" :key="index"
				 @click="choseOne(item.id)">
					{{item.text}}
				</view>
			</view>
		</uni-popup>
	</view>
</template>

<script>
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniPopup from '@/components/uni-popup/uni-popup.vue'
	import {
		vpnrecommendGetsvc
	} from "../../api/index.js"
	export default {
		data() {
			return {
				selectShow: false,
				seleList: [{
						id: null,
						text: '显示全部'
					},
					{
						id: 1,
						text: '已投递'
					},
					{
						id: 2,
						text: '已邀约'
					},
					{
						id: 3,
						text: '待面试'
					},
					{
						id: 6,
						text: '已面试'
					},
					{
						id: 7,
						text: '已入职'
					},
					{
						id: 0,
						text: '不合适'
					},
					{
						id: 8,
						text: '已离职'
					}
				],
				selectIndex: null,
				compJobList: [],
				userId: '',
				serchText: '',
				pageIndex: 1,
				pageSize: 10,
				canLoad: true,
			}
		},
		components: {
			uniSearchBar,
			uniPopup
		},
		filters: {
			filterState(val) {
				let states = {
					0: '不合适',
					1: '已投递',
					2: '已邀约',
					3: '待面试',
					4: '推送企业',
					5: '企业面试',
					6: '已面试',
					7: '已入职',
					8: '已离职'
				};
				return states[val]
			}
		},
		onLoad() {
			this.userId = getApp().globalData.userId;
			this.getSuggestAjax();
		},
		methods: {
			changeVal(e){
				this.searchText=e.value
			},
			openSelect() {
				this.selectShow = true;
				this.$refs.popup.open();
			},
			isClose(e) {
				if (e.show == false) {
					this.selectShow = false;
				}
			},
			choseOne(indexp) {
				this.selectIndex = indexp;
				this.getSuggestAjax();
				this.$refs.popup.close();
			},
			// 搜索
			searchs(e) {
				this.serchText = e.value;
				this.getSuggestAjax();
			},
			// 跳转到进度
			goPage(item) {
				uni.navigateTo({
					url: '../suggestProgress/suggestProgress?reid=' + item.delId
				})
			},
			// 加载更多
			botMore() {
				if (this.canLoad != false) {
					this.getAjax();
				}
			},
			// 获取推荐列表
			getSuggestAjax() {
				let that = this;
				let data = {
					svcCaller: {
						page: that.pageIndex,
						pageSize: that.pageSize
					},
					vpnrecommendGetResp: {
						userId: that.userId,
						state: that.selectIndex,
						name: that.serchText
					}
				}
				vpnrecommendGetsvc(data).then(res => {
					if (res.code == 1) {
						if (that.pageIndex == 1) {
							that.compJobList = res.data.vpnrecommendGetResp;
						} else {
							that.compJobList = [...that.compJobList, ...res.data.vpnrecommendGetResp]
						}
						if (res.data.vpnrecommendGetResp == undefined || res.data.vpnrecommendGetResp.length < that.pageSize) {
							that.canLoad = false;
						} else {
							that.pageIndex++
						}
					} else {
						console.log(res.msg)
					}
				})
			}
		}
	}
</script>

<style lang="less">
	.topSearch {
		padding: 0 20rpx;
		display: flex;
		align-items: center;

		.topSearchLeft {
			flex: 1;
		}

		.topSearchRit {
			width: 120rpx;
			text-align: center;

			image {
				width: 29rpx;
				height: 33rpx;
				margin-right: 10rpx;
				vertical-align: -7rpx;
			}

			.noTap {
				color: #1B1B1B;
				font-size: 26rpx;
			}

			.hasTap {
				color: #2E73FF;
				font-size: 26rpx;
			}
		}
	}

	.whiteBack {
		background: #ffffff;
		padding: 0 30rpx;
	}

	.selectOne {
		padding: 30rpx 0;
		border-bottom: 1rpx solid #F3F3F3;
		font-size: 28rpx;
		color: #1B1B1B;
	}

	.blueColor {
		color: #2E73FF;
	}

	.scrollBox {
		height: calc(100% - 104rpx);
		background: #F6F6F6;

		.allList {
			padding: 10rpx 30rpx;

			.oneItem {
				margin-top: 20rpx;
				height: 293rpx;
				background: #FFFFFF;
				border-radius: 10rpx;
				padding: 30rpx;

				.pepole {
					display: flex;
					justify-content: space-between;
					padding: 10rpx 0 20rpx;

					.pname {
						font-size: 30rpx;
						color: #2C2C2C;
						font-weight: bold;
					}
				}

				.pContent {
					.pText {
						padding: 7rpx 0;
						font-size: 26rpx;
						color: #2C2C2C;

						.pTip {
							color: #919191;
						}

						.redMonye {
							color: #FE2B67;
						}
					}
				}
			}
		}
	}

	.tagBox {
		display: inline-block;
		padding: 8rpx 12rpx;
		font-size: 20rpx;
		background: #FFEDE2;
		color: #FF8F4B;
		border-radius: 8rpx;
	}

	.lineRow {
		border-bottom: 1rpx solid #F3F3F3;
		margin-bottom: 10rpx;
	}
</style>
