<template>
    <view style="height: 100%">
        <scroll-view scroll-y class="allList" @scrolltolower="reachBottom">
            <!-- 滑动翻页 -->

            <view class="listBox">
                <view class="oneItem" v-for="(item,index) in msgList" :key="index">

                    <view class="creatTime">
                        {{item.createTime}}
                    </view>
                    <view class="msgContent">
                        <u-badge :is-dot="true" type="error" :offest="[20,20]" v-if="item.isread===1"></u-badge>
                        <view class="tipTop">

                            【{{item.title || ''}}】
                        </view>
                        <view class="tipType">
                            {{item.content ||''}}
                        </view>



                        <view class="btn-group" v-if="item.isagree==0">
                            <button class="mini-btn unSure" plain=true type="default" @click="bRefuse(item,index)"
                                    size="mini">拒绝</button>
                            <button class="mini-btn sureBtn" plain=true type="default" @click="bSure(item,index)"
                                    size="mini">同意</button>
                        </view>
                        <view class="rowType">
                            <text v-if="item.isagree==2">您已经拒绝</text>
                            <text v-if="item.isagree==1">您已同意</text>
                            <text v-if="item.isagree==3">已失效</text>
                        </view>
                    </view>
                </view>
            </view>
            <view class="noMoreList" v-if="canLoad==false && msgList.length>0">

                没有更多了哦~
            </view>
        </scroll-view>
    </view>
</template>
<script>
    import {vpninviteUpdatesvc, vpnnewsGetsvc} from "../../api";

    export default {
        /* props: {
            // 开始的数值，默认从0增长到某一个数
            newType: {
                type: Number,
                default: null
            },
        }, */
        data(){
            return{
                canLoad: true,
                msgList: [],
                pageIndex: 1,
                pageSize: 10,
				newType:''
            }
        },
        methods:{
            //到达底部
            reachBottom(){
				if(this.canLoad){
					this.getList();
				}
            },
            clear(){

            },
			getListByType(pageIndex,typeid) {
			
			    let that = this;
				that.newType=typeid;
			    if(pageIndex){
			        that.pageIndex = 1
			    }
			    let data = {
			        svcCaller: {
			            page: that.pageIndex,
			            pageSize: that.pageSize
			        },
			        vpnnewsGet: {
			            toUserId: getApp().globalData.userId,
			            identity: getApp().globalData.userState,
			            newType:typeid
			        }
			    }
			    vpnnewsGetsvc(data).then(res => {
			        
			        if (res.code == 1) {
			            if (that.pageIndex == 1) {
			                that.msgList = res.data.vpnnewsGetResp;
			            } else {
			                that.msgList = [...that.msgList, ...res.data.vpnnewsGetResp]
			            }
			            if (res.data.vpnnewsGetResp.length < that.pageSize) {
			                that.canLoad = false;
			            } else {
			                that.pageIndex++
			            }
			            if(res.data.noreadcount>0){
			                uni.setTabBarBadge({
			                    index: 3,
			                    text: res.data.noreadcount
			                })
			            }else{
			                uni.removeTabBarBadge({index:3})
			            }
			        } else {
			            console.log(res.msg)
			        }
			    })
			},
            getList(pageIndex) {

                let that = this;
                if(pageIndex){
                    that.pageIndex = 1
                }
                let data = {
                    svcCaller: {
                        page: that.pageIndex,
                        pageSize: that.pageSize
                    },
                    vpnnewsGet: {
                        toUserId: getApp().globalData.userId,
                        identity: getApp().globalData.userState,
                        newType:that.newType
                    }
                }
                vpnnewsGetsvc(data).then(res => {
                    
                    if (res.code == 1) {
                        if (that.pageIndex == 1) {
                            that.msgList = res.data.vpnnewsGetResp;
                        } else {
                            that.msgList = [...that.msgList, ...res.data.vpnnewsGetResp]
                        }
                        if (res.data.vpnnewsGetResp.length < that.pageSize) {
                            that.canLoad = false;
                        } else {
                            that.pageIndex++
                        }
                        if(res.data.noreadcount>0){
                            uni.setTabBarBadge({
                                index: 3,
                                text: res.data.noreadcount
                            })
                        }else{
                            uni.removeTabBarBadge({index:3})
                        }
                    } else {
                        console.log(res.msg)
                    }
                })
            },
            bRefuse: function(item, index) {
                let id = item.id;
                let that = this;
                let data = {
                    vpninviteUpdate: {
                        id: id,
                        state: 2
                    }
                }
                vpninviteUpdatesvc(data).then(res => {
                    if (res.code == 1) {
                        that.msgList[index].isagree = 2;
                        uni.showToast({
                            title: '您已拒绝邀约',
                            duration: 1500,
                            icon: 'none'
                        })
                    } else {
                        uni.showToast({
                            title: res.msg,
                            duration: 1500,
                            icon: 'none'
                        })
                    }
                })
            },
            bSure: function(item, index) {
                console.log('同意', item)
                let id = item.id;
                let that = this;
                let data = {
                    vpninviteUpdate: {
                        id: id,
                        state: 1
                    }
                }
                vpninviteUpdatesvc(data).then(res => {
                    if (res.code == 1) {
                        that.msgList[index].isagree = 1;
                        uni.showToast({
                            title: '您已同意邀约',
                            duration: 1500,
                            icon: 'none'
                        })
                    } else {
                        uni.showToast({
                            title: res.msg,
                            duration: 1500,
                            icon: 'none'
                        })
                    }
                })
            }
        }
    }

</script>
<style lang="less">
    .allList {
        height: 100%;

        .listBox {
            padding: 28rpx;
        }
    }

    .oneItem {
        margin-bottom: 30rpx;

        .creatTime {
            text-align: center;
            padding: 10rpx 0 20rpx;
            font-size: 26rpx;
            color: #AFAFAF;
        }

        .msgContent {
            min-height: 163rpx;
            background: #FFFFFF;
            border-radius: 10rpx;
            padding: 30rpx;
            color: #1B1B1B;
            position: relative;
            .tipTop {
                font-weight: bold;
                font-size: 32rpx;
            }

            .tipType {
                margin-top: 20rpx;
                margin-left: 20rpx;
                font-size: 28rpx;

            }
        }
    }
    .btn-group {
        text-align: right;
        margin-top: 30rpx;

        .sureBtn {
            color: #FFFFFF !important;
            margin-left: 10rpx;
            background: #2E73FF !important;
            border: 1rpx solid #2E73FF !important;
        }

        .unSure {
            color: #7E7E7E !important;
            border: 1rpx solid #C6C6C6 !important;
        }

    }
	.noMoreList {
		text-align: center;
		color: #919191;
		font-size: 26rpx
	}
</style>

