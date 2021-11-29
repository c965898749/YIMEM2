<template>
	<view class="tapList">
		<view class="tapItem" v-for="(item,index) in options" :key="index">
			<view :class="indexTap==index?'activeTap':'tapName'" @click="changeTap" :data-index="index">
				{{item}}
			</view>
			<view class="tapLitBlue" v-if="index==indexTap">
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props:{
			options: {
			  type: Array,
			  value: []
			},
			activeIndex: {
			  type: String,
			  value: '1'
			},
		},
		watch:{	
		     activeIndex:{//深度监听，可监听到对象、数组的变化
		         handler(val){
		            this.indexTap= val
		         },
		     }
		 },
		data() {
			return {
				indexTap:this.activeIndex
			};
		},
		methods:{
			changeTap:function(e){
				let indexClone=e.currentTarget.dataset.index;
				this.indexTap=indexClone;
				this.$emit('tapChange',indexClone)
			}
		}
	}
</script>

<style>
.tapList{
	display: flex;
	flex-direction: row;
	width: 100%;
}
.tapList>view{
	flex: 1;
	text-align: center;
	font-size: 26rpx;
	color: #6B7B8A;
	display: flex;
	flex-direction: column;
	align-items: center;
}
.tapName{
	padding-bottom: 15rpx;
}
.activeTap{
	color: #4E66FC;
	font-size: 30rpx;
	padding-bottom: 15rpx;
}

.tapLitBlue{
	width: 30rpx;
	height: 4rpx;
	background:rgba(78,102,252,1);
	border-radius:4rpx;
}
</style>
