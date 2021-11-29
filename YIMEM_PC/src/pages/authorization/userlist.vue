<template>
  <div class="page-main clearfix">
    <div class="deplist">
      <h4>
        <span>部门管理</span>
        <a-button type="primary" @click="orgAdd()" v-if="button.add">新增部门</a-button>
      </h4>
      <div class="box">

        <a-tree show-line :treeData="deplist" @select="onSelect">
          <template slot="custom" slot-scope="item">
            <div class="tree-view-item">
              <span class="tree-view-left">{{ item.title }}</span>
              <div class="tree-view-right" v-if="userjson.depId==item.key">
                <a-icon type="form" @click="orgEdit(item)" v-if="button.edit"/>
                <a-icon type="delete" @click="orgDelete(item.key)" v-if="button.del"/>
              </div>
            </div>
          </template>
        </a-tree>

      </div>
    </div>
    <div class="ulist">
      <h4>
        <span>{{DepInfo==''?'全部':DepInfo.name}}</span>
        <a-button type="primary" @click="useradd" v-if="button.add">新增员工</a-button>
      </h4>
      <div class="box">
        <div class="Searchbox clearfix">
          <a-form-model :model="userjson">
            <a-form-model-item>
              <a-input autocomplete="autocomplete" placeholder="请输入员工姓名" v-model="userjson.keyword">
              </a-input>
            </a-form-model-item>
            <a-form-model-item>
              <a-button :loading="subloading" type="primary" @click="Search">查询</a-button>
            </a-form-model-item>
          </a-form-model>
        </div>
      <a-table :rowKey="uesrlist=>uesrlist.id" :columns="columns" :data-source="uesrlist" :loading="loading" :pagination="pagination" @change="handleTableChange">
        <img  class="headimg" slot="headimg" slot-scope="text, record" :src=record.headImgUrl />
        <span slot="state" slot-scope="text">{{text==1?'启用':'禁用'}}</span>
        <span slot="roles" slot-scope="text">{{text.name}}</span>

        <span slot="action" slot-scope="text, record">
          <template v-if="button.edit">
          <a @click="Disable(record.id,record.state)">{{record.state==1?'禁用':'启用'}}</a>
          <a-divider type="vertical" />
          <a @click="useredit(record)">编辑</a>
          <a-divider type="vertical" />
          </template>
          <a @click="Del(record.id)" v-if="button.del">删除</a>
        </span>
      </a-table>
      </div>
    </div>

    <depadd :depvisible="depvisible" :currentDepInfo="currentDepInfo==''?'':currentDepInfo" :deplist="deplist" v-on:setdepmodal="setdepmodal"/>
    <useradd :uservisible="uservisible" :currentuserInfo="currentuserInfo==''?'':currentuserInfo" :deplist="deplist" v-on:setusermodal="setusermodal"/>
  </div>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {getDepartmentAll,DepartmentDel,getUserlistAll,asyncUserState,asyncUserDelete,DepartmentInfoById} from '@/services/user'
  import depadd from './components/depadd'
  import useradd from './components/useradd'
  export default {
    name: 'userlist',
    components: {depadd,useradd},
    data () {
      return {
        NodeTreeItem: null, // 右键菜单
        tmpStyle: '',
        deplist:[],
        DepInfo:'',
        currentDepInfo:'',
        currentuserInfo:'',
        userjson:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          depId:'',
          keyword:""
        },
        columns:[
          {
            title: '头像',
            dataIndex: 'headImgUrl',
            scopedSlots: { customRender: 'headimg' },
            width:80,
          },
          {
            title: '姓名',
            dataIndex: 'name',
          },{
            title: '联系电话',
            dataIndex: 'phone',
          },{
            title: '岗位角色',
            dataIndex: 'roles',
            scopedSlots: { customRender: 'roles' },
          },{
            title: '账号',
            dataIndex: 'account',
          },{
            title: '是否启用',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state' },
          },{
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
            width:160,
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          //showSizeChanger: true,
        },
        uesrlist:[],
        pageCount:0,
        recordCount:0,
        loading:false,
        subloading:false,
        depvisible:false,
        uservisible:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.edit==undefined && this.button.del==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.getDepList()
      this.getUserList()
    },
    methods: {
      getDepList() {
        this.loading = true
        getDepartmentAll().then(result => {
          if(result.data.code==1){
            this.deplist=this.setName(result.data.data)
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      setName(datas){
        let res = []
        datas.forEach((item) => {
          item.scopedSlots={"title":"custom"}
          item.children=(item.children && item.children.length>0) ? this.setName(item.children) : []
          res.push(item)
        })
        return res
      },
      getUserList() {
        this.loading = true
        getUserlistAll(this.userjson).then(result => {
          this.uesrlist=[]
          if(result.data.code==1){
            this.uesrlist=result.data.data.content
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      onSelect (selectedKeys) {
        if(selectedKeys[0] != undefined){
          this.userjson.depId = selectedKeys[0]
          this.Search()
          this.getDepInfo()
        }
      },
      getDepInfo(Edit){
        DepartmentInfoById(this.userjson.depId).then(result => {
          if(result.data.code==1){
            this.DepInfo=result.data.data
            if(Edit){
              this.currentDepInfo=this.DepInfo
              this.depvisible=true
            }
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      setdepmodal(v){
        this.depvisible=false
        if(v){
          this.getDepList()
          if(this.currentDepInfo != ''){
            this.getDepInfo()
          }

        }
      },
      orgAdd () {
        this.currentDepInfo=''
        this.depvisible=true
      },
      orgEdit () {
        this.getDepInfo('edit')

      },
      orgDelete (key) {
        const _self=this
        this.$confirm({
          title: '确认删除此部门吗？',
          //content: '',
          onOk() {
            DepartmentDel(key).then(result => {
              if(result.data.code==1){
                _self.uesrlist=[]
                _self.$message.success(result.data.msg, 3)
                _self.getDepList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      handleTableChange(pagination) {
        this.userjson.svcCaller.page = pagination.current
        this.getUserList()
      },
      Search(){
        console.log(11)
        this.pagination.total=0
        this.userjson.svcCaller.page=1
        this.getUserList()
      },
      Disable(id,state){
        const _self=this
        this.$confirm({
          title: state==1?'确认禁用该员工吗？':'确认启用该员工吗？',
          //content: '',
          onOk() {
            asyncUserState({id:id,state:(state==1?0:1)}).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.getUserList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      setusermodal(v){
        this.uservisible=false
        if(v){
          this.getUserList()
        }
      },
      useradd(){
        this.currentuserInfo=''
        this.uservisible=true
      },
      useredit(list){
        this.currentuserInfo={
          ...list
        }
        this.uservisible=true
      },
      Del(id){
        const _self=this
        this.$confirm({
          title: '确认删除该员工吗？',
          //content: '',
          onOk() {
            asyncUserDelete({id:id}).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.getUserList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
    }
  }
</script>
<style lang="less">
  .page-main{
    background:#fff;
    height:auto;
  }
.deplist{
  width:30%;height:auto;float:left;
  h4{min-height:62px;padding:15px;border-bottom:1px solid #f1f1f1;
    span{font-weight:700;line-height:30px;}
    button{float:right;margin-left:10px;font-weight:normal;}
  }
  .box{height:auto;padding:15px;}
  .ant-tree li{
    .tree-view-left{padding:0px 5px;float:left;}
    .tree-view-right{float:right;margin-left:15px;
      .anticon{color:#1890ff;margin:0px 5px;}
    }
    .ant-tree-node-content-wrapper.ant-tree-node-selected{background:none}
    .ant-tree-node-content-wrapper.ant-tree-node-selected .tree-view-left{background:#bae7ff;border-radius:3px;border:1px solid #1890ff;display:inline-block}
  }
}
.ulist{
  width:70%;height:auto;float:right;border-left:1px solid #f1f1f1;
  h4{min-height:62px;padding:15px;border-bottom:1px solid #f1f1f1;
    span{font-weight:700;line-height:30px;}
    button{float:right;font-weight:normal;}
  }
  .box{height:auto;padding:15px;
    .headimg{width:40px;height:40px;border-radius:100px;}
  }

}
  .ant-input-lg{font-size:12px;}
</style>
