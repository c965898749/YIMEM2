<template>
	<view class="content">
		<u-navbar title="消息通知" :is-back="false">
			<view solt="left">
				<view @click="clear" class="navbar-left">
					<image src="../../static/images/clear.png"></image>
					全部已读
				</view>
			</view>
		</u-navbar>
		<view class="warp">
			<!-- <view class="uTabs" v-if="userState==2">
				<u-tabs :list="list1" :is-scroll="true" :current="current" @change="changeTab"></u-tabs>
			</view>
			<view class="uTabs" v-if="userState==1">
				<u-tabs :list="list2" :is-scroll="true" :current="current" @change="changeTab"></u-tabs>
			</view>
			<view class="uTabs" v-if="userState==3">
				<u-tabs :list="list3" :is-scroll="true" :current="current" @change="changeTab"></u-tabs>
			</view> -->
			<view class="uTabs">
				<u-tabs :list="list" :is-scroll="true" :current="current" @change="changeTab"></u-tabs>
			</view>
			<swiper class="swiper-box" :current="swiperCurrent"  @animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<news-item ref = "news0"></news-item>
				</swiper-item>
				<!-- <swiper-item class="swiper-item"> 
					<news-item :newType="1" ref = "news1"></news-item>
				</swiper-item> 
				<swiper-item class="swiper-item">
					<news-item :newType="2" ref = "news2"></news-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<news-item :newType="3" ref = "news3"></news-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<news-item :newType="4" ref = "news4"></news-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<news-item :newType="5" ref = "news5"></news-item>
				</swiper-item>
				<swiper-item class="swiper-item">
					<news-item :newType="6" ref = "news6"></news-item>
				</swiper-item> -->
			</swiper>
		</view>

	</view>
</template>

<script>
	import {vpnnewsReadsvc} from "../../api";

	import newsItem from "@/components/newsItem/newsItem.vue";
	export default {
		components:{newsItem},
		data() {
			return {//1-系统消息，2-活动通知，3-面试通知，4-入职通知，5-奖励到账通知，6-提现到账通知
			    list: [],
				list1: [{
					id:'',
					name: '全部消息'
				}, {
					id:1,
					name: '系统通知'
				}, {
					id:2,
					name: '活动通知'
				}, {
					id:3,
					name: '面试通知'
				}, {
					id:4,
					name: '入职通知'
				}, {
					id:5,
					name: '奖励通知'
				}, {
					id:6,
					name: '提现通知'
				}],
				list2: [{
					id:'',
					name: '全部消息'
				}, {
					id:1,
					name: '系统通知'
				}, {
					id:2,
					name: '活动通知'
				}, {
					id:3,
					name: '面试通知'
				}, {
					id:4,
					name: '入职通知'
				}],
				list3: [{
					id:'',
					name: '全部消息'
				}, {
					id:1,
					name: '系统通知'
				}, {
					id:2,
					name: '活动通知'
				}, {
					id:3,
					name: '面试通知'
				}, {
					id:4,
					name: '入职通知'
				}],
				current: 0,
				swiperCurrent:0,
				userId: '',
				userState: 1, //判断用户是什么身份
				newType:''
			};
		},
		onShow() {
			this.userId = getApp().globalData.userId;
			this.userState = getApp().globalData.userState;
			this.newType='';
			if(this.userState==2){
				this.list=this.list1;
			}else if(this.userState==1){
				this.list=this.list2;
			}else{
				this.list=this.list3;
			}
			this.current = 0;
			this.swiperCurrent=0;
			//this.$refs.news0.getList(1);
			this.$refs.news0.getListByType(1,this.newType);
		},
		methods: {
			clear() {
				//移除  消息的角标

				let data = {
					vpnnewsGet: {
						toUserId: this.userId,
						identity: this.userState,
					}
				}
				let _this = this
				vpnnewsReadsvc(data).then(res=>{
					if (res.code == 1) {
						//调用item 的遍历list 清空数据的已读
						_this.$refs.news0.clear();
						_this.$refs.news0.getListByType(1,_this.newType);
						uni.removeTabBarBadge({index:3})
					}
				})

			},
			animationfinish({ detail: { current } }) {
				//this.swiperCurrent = current;
				this.current = current;
			},
			changeTab(index) {
				console.log("******"+index);
				this.$refs.news0.clear();
				this.current = index;
				let newType=this.list[index].id;
				this.newType=newType;
				this.$refs.news0.getListByType(1,newType);
			},


		}
	}
</script>

<style lang="less">
	.warp{
		display: flex;
		flex-direction: column;
		flex: 1;
		width: 100%;
	}
	.swiper-box {
		flex: 1;
	}
	.swiper-item {
		height: 100%;
	}
	.navbar-left {
		width: 160rpx;
		margin-left: 40rpx;
		display: flex;
		align-items: center;
		font-size: 24rpx;
		font-weight: 400;
		color: #2C2C2C;
	}

	.navbar-left>image {
		width: 31rpx;
		height: 31rpx;
		margin-right: 8rpx;
	}

	.noMoreList {
		text-align: center;
		padding:0 0 20rpx;
	}

	.content {
		background: #F6F6F6;
	}
</style>
