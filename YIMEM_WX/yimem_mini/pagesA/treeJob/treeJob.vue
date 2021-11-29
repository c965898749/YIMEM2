<template>
	<view class="allBox">
		<view class="topBox">
			<ly-tree :tree-data="treeData" :ready="ready" :showRadio='true' node-key="id" @check='checkKey'>
			</ly-tree>
			<!-- @node-expand="handleNodeExpand" -->
			<!-- @node-click="handleNodeClick" -->
		</view>
		<view class="botBox">
			<button class="sureBtn" @click="goBack">确定</button>
		</view>
	</view>
</template>

<script>
	import {
		vpnjobcategory2Getsvc
	} from '../../api/index.js'
	import LyTree from '@/components/ly-tree/ly-tree.vue'
	export default {
		data() {
			return {
				ready: false, // 这里用于自主控制loading加载状态，避免异步正在加载数据的空档显示“暂无数据”
				treeData: []
			}
		},
		onLoad() {
			this.getJobTree()
			// 模拟异步请求
			// setTimeout(() => {
			// 	this.treeData = [{
			// 		id: 1,
			// 		label: '一级 1',
			// 		children: [{
			// 			id: 11,
			// 			label: '二级 1-1',
			// 			children: [{
			// 				id: 111,
			// 				label: '三级 1-1-1'
			// 			}]
			// 		}]
			// 	}, {
			// 		id: 2,
			// 		label: '一级 2',
			// 		children: [{
			// 			id: 21,
			// 			label: '二级 2-1',
			// 			children: [{
			// 				id: 211,
			// 				label: '三级 2-1-1'
			// 			}]
			// 		}, {
			// 			id: 22,
			// 			label: '二级 2-2',
			// 			children: [{
			// 				id: 221,
			// 				label: '三级 2-2-1'
			// 			}]
			// 		}]
			// 	}, {
			// 		id: 3,
			// 		label: '一级 3',
			// 		children: [{
			// 			id: 31,
			// 			label: '二级 3-1',
			// 			children: [{
			// 				id: 311,
			// 				label: '三级 3-1-1'
			// 			}]
			// 		}, {
			// 			id: 32,
			// 			label: '二级 3-2',
			// 			children: [{
			// 				id: 321,
			// 				label: '三级 3-2-1'
			// 			}]
			// 		}]
			// 	}];
			// this.ready = true;
			// }, 2000);
		},
		components: {
			LyTree
		},
		methods: {
			// 返回
			goBack(){
				uni.navigateBack({
					delta:1
				})
			},
			// 获取ztrr数据
			getJobTree() {
				let that = this;
				let data = {

				}
				vpnjobcategory2Getsvc(data).then(res => {
					this.ready = true;
					if (res.code == 1) {
						that.treeData = res.data.vpnqsCategoryJobsGetResp;
					} else {
						uni.showToast({
							title: res.msg,
							duration: 1500,
							icon: 'none'
						})
					}
				})
			},
			// uni-app中emit触发的方法只能接受一个参数，所以会回传一个对象，打印对象即可见到其中的内容
			// handleNodeClick(obj) {
			// 	console.log('handleNodeClick', JSON.stringify(obj));
			// },
			// handleNodeExpand(obj) {
			// 	console.log('handleNodeExpand', JSON.stringify(obj));
			// },
			checkKey(nodes) {
				let checkObj = nodes.data;
				uni.setStorageSync('checkJob', checkObj)
			}
		}
	}
</script>

<style>
	page,.allBox {
		height: 100%;
	}

	.topBox {
		height: calc(100% - 100rpx);
		overflow-y: auto;
	}

	.botBox {
		height: 100rpx;
	}
	.sureBtn{
		height: 80rpx;
		background: linear-gradient(0deg, #2E73FF, #4DA2FF);
		box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
		border-radius: 40rpx;
		width: 80%;
		margin: 0 auto;
		line-height: 80rpx;
		font-size: 30rpx;
		font-weight: 500;
		color: #FFFFFF;
	}
</style>
