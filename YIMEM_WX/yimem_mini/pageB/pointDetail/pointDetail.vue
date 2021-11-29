<template>
  <view class="content">
    <view class="header-row">
      <view class="uTabs">
        <u-tabs :list="tabList" :is-scroll="true" :current="current" @change="change"></u-tabs>
      </view>
      <view class="filter-cdt"  @click="show = true">
        {{list[selectIndex].label}}
        <image src="../../static/images/shaixuan2.png"></image>
        <u-select v-model="show" :list="list" :default-value="[0]" @confirm="selectConfirm"></u-select>

      </view>
    </view>
    <swiper class="swiper-box" :current="swiperCurrent"  @animationfinish="animationfinish">
      <swiper-item class="swiper-item">、
        <points-list ref="income"></points-list>
      </swiper-item>
      <swiper-item class="swiper-item">
        <points-list ref = "expenses"></points-list>
      </swiper-item>

    </swiper>

  </view>
</template>

<script>
import pointsList from "./module/pointsList.vue"
export default {
  components:{pointsList},
  data() {
    return {
      current:0,
      swiperCurrent:0,
      userId: '',
      tabList:[
        {
          name: '积分获取'
        },
        {
          name: '积分支出'
        }
      ],
      show: false,
      selectIndex:0,
      list: [
        {
          value: 'month',
          label: '本月',
          extra: 0
        },
        {
          value: 'year',
          label: '本年',
          extra: 1
        }
      ],
    }
  },
  onShow(){
    //查询
    this.$refs.income.init(this.list[this.selectIndex].value,1)
    this.$refs.expenses.init(this.list[this.selectIndex].value,0)
  },
  methods: {
    change(index) {
      this.current = index;
      this.swiperCurrent = index;
    },
    selectConfirm(arr){
      if(this.selectIndex != arr[0].extra){
        this.$refs.income.init(this.list[this.selectIndex].value,1)
        this.$refs.expenses.init(this.list[this.selectIndex].value,0)
      }
      this.selectIndex = arr[0].extra
    },
    animationfinish({ detail: { current } }) {
      this.swiperCurrent = current;
      this.current = current;
    },
  }
}
</script>

<style lang="less" scoped>
.header-row{
  display:flex;
  justify-content: space-around;
  align-items: center;
  .uTabs{
    width:70%;
  }
  .filter-cdt{
    display: flex;
    font-size: 26rpx;
    font-weight: 400;
    color: #2E73FF;
    align-items: center;
    image{
      margin-left: 10rpx;
      width: 32rpx;
      height: 30rpx;
    }
  }
}

.swiper-box {
  flex: 1;
  .swiper-item {
    height: 100%;
  }
}

</style>
