<template>
	<view style="height: 100%">
	    <scroll-view scroll-y class="allList" @scrolltolower="reachBottom">
			<view class="listBox">
				<view class="oneItem" v-for="(item,index) in pointList" :key="index">
					<view class="parting" v-if="index!=0"></view>
					<view class="around-area">
						<view>
							<view class="point-title">{{item.content}}</view>
							<view class="point-time">{{item.createtime}}</view>
						</view>
						<view class="point-value" :style="{coloe:item.flag==1?'#2E73FF':'#FE2B67'}">
							{{item.flag==1?'+':'-' }}{{item.point}}
						</view>
					</view>
				</view>
			</view>
			<view class="noMoreList" v-if="canLoad==false && pointList.length>0">
			
			    没有更多了哦~
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import {vpnGetPointdetailsvc} from "../../../api"
	export default{
		data(){
			return{
				canLoad:true,
				pointList:[],
				pageIndex: 1,
				pageSize: 10,
				mark:"month",
				flag:1,
			}
		},
		methods:{
			
			init(mark,flag){
				this.pageIndex = 1;
				this.mark = mark
				this.flag = flag;
				this.getPoints();
			},
			reachBottom(){
				this.getPoints();
			},
			getPoints(){
				let data={
					svcCaller:{
						page: this.pageIndex,
						pageSize: this.pageSize
					},
					vpnPointAdd:{
						userId:getApp().globalData.userId,
						mark:this.mark,
						flag:this.flag
					}
				};
				let that = this;
				
				vpnGetPointdetailsvc(data).then(res=>{
					console.log(res)
					if (res.code == 1) {
					    if (that.pageIndex == 1) {
					        that.pointList = res.data;
					    } else {
					        that.pointList = [...that.pointList, ...res.data]
					    }
						if (res.data.length < that.pageSize) {
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

<style lang="less" scoped>
	.allList {
	    height: 100%;
	
	    .listBox {
	        padding:0 60rpx;
			
			.oneItem{
				height: 144rpx;
				
				.parting{
					width:100%;
					height: 1rpx;
					background: #E4E4EE;
				}
				.around-area{
					display: flex;
					justify-content: space-between;
					align-items: center;
					padding-top: 35rpx;
				}
			}
	    }
	}
	.point-title{
		font-size: 30rpx;
		font-weight: bold;
		color: #292929;
	}
	.point-time{
		font-size: 24rpx;
		font-weight: 400;
		color: #969696;
		margin-top: 10rpx;
	}
	.point-value{
		font-size: 30rpx;
		font-weight: 500;
		color: #2E73FF;
	}
	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}
</style>
