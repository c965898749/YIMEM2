<template>
  <a-card>
    <div class="title">{{Detail.title}}</div>
    <div class="date">发布时间：{{Detail.createTime}}</div>
    <div class="content" v-html="Detail.content"></div>
  </a-card>
</template>
<script>
  import {asyncBeginnerDetail} from '@/services/dashboard'
  export default {
    data() {
      return {
        beId:this.$route.query.beId,
        Detail:{},
      }
    },
    created() {
      this.getData()
    },
    methods: {
      getData(){
        asyncBeginnerDetail(this.beId).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.Detail=result.data.data.vpnbeginnerGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      }
    }
  }
</script>
<style lang="less">
  .ant-card-body{padding:30px;}
.title{height:auto;text-align:center;font-size:24px;line-height:30px;color:#333;}
  .date{color:#999;height:40px;line-height:40px;margin-top:10px;}
  .content{height:auto;line-height:24px;margin-top:20px;
  img{max-width:100%}
  }
</style>