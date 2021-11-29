<template>
  <a-card>
    <div class="head_action" v-if="button.add">
    <a-button @click="Add" class="Add" type="primary">新增提现规则</a-button>
    </div>
    <a-list class="listbox" :loading="loading" item-layout="horizontal" :data-source="dataList">
      <div v-if="showLoadingMore" slot="loadMore" :style="{ textAlign: 'center', marginTop: '12px', height: '32px', lineHeight: '32px' }">
        <a-spin v-if="loadingMore" />
        <a-button v-else @click="onLoadMore">
          加载更多
        </a-button>
      </div>

      <a-list-item slot="renderItem" slot-scope="item">
        <a slot="actions" @click="Del(item.wiId)" v-if="button.del"><a-icon type="delete" /> 删除</a>
        <template v-if="button.edit">
        <a slot="actions" @click="Edit(item.wiId)"><a-icon type="form" /> 编辑</a>
        <a slot="actions" @click="SetState(item.wiId,0)" v-if="item.state==1"><a-icon type="check-circle" /> 停止</a>
        <a slot="actions" @click="SetState(item.wiId,1)" v-else><a-icon type="stop" /> 使用</a>
        </template>
        <a-list-item-meta :description="item.content">
          <a slot="title" @click="Detail(item.wiId)">{{ item.title }}</a>
        </a-list-item-meta>
        <span class="createTime">{{ item.createTime }}</span>
        <span :class="'state'+item.state">{{item.state==1?'使用中':'停止中'}}</span>
      </a-list-item>
    </a-list>
  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {asyncwithdrawRuleGet,asyncwithdrawRuleAdd,asyncwithdrawRuleDel} from '@/services/dashboard'
  export default {
    data() {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          }
        },
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        dataList:[],
        loading:true,
        loadingMore:false,
        showLoadingMore: false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      this.findList()
    },
    methods: {
      findList() {
        this.getData(res => {
          this.dataList = res;
        });
      },
      getData(callback){
        asyncwithdrawRuleGet(this.json).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.pagination.total=result.data.data.totalElements
            if(result.data.data.totalPages>this.json.svcCaller.page){
              this.showLoadingMore=true
            }else{
              this.showLoadingMore=false
            }
            callback(result.data.data.content)
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      onLoadMore(){
        this.json.svcCaller.page++
        this.getData(res => {
          this.dataList = this.dataList.concat(res)
        });
      },
      Search(){
        this.pagination.total=0
        this.json.svcCaller.page=1
        this.findList()
      },
      Detail(wiId){
        this.$router.push({path:'/dashboard/withdrawalrule/detail',query: {wiId:wiId}})
      },
      Add(){
        this.$router.push({path:'/dashboard/withdrawalrule/add'})
      },
      Edit(wiId){
        this.$router.push({path:'/dashboard/withdrawalrule/edit',query: {wiId:wiId}})
      },
      SetState(wiId,state){
        const _self=this
        this.$confirm({
          title: state==1?'确认使用吗？':'确认禁用吗？',
          //content: '',
          onOk() {
            const requests={
              vpnwithdrawRuleUpdate:{
                wiId:wiId,
                state:state
              }
            }
            asyncwithdrawRuleAdd('edit',requests).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.findList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      Del(wiId){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
            asyncwithdrawRuleDel(wiId).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.findList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      }
    }
  }
</script>
<style lang="less">
  .listbox{
    .ant-list-item-meta-title a{color:#1890ff;font-size:16px;font-weight:700;}
    .ant-list-item{position:relative;
      .ant-list-item-meta{padding-bottom:30px;min-height:80px;
        .ant-list-item-meta-description{line-height:24px;margin-top:10px;color:#666}
      }
      .createTime{position:absolute;left:0px;bottom:10px;color:#aaa;font-size:12px;}
      .state0,.state1{position:absolute;top:50%;margin-top:-56px;right:10px;z-index:9;font-weight:700}
      .state0{color:#cc0000;}
      .state1{color:#1890ff;}
      .ant-list-item-action a{color:#999;}
    }
  }
</style>
