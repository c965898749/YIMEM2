<template>
  <a-card>
    <div class="head_action" v-if="button.add">
      <a-button @click="RoleAdd" class="Add" type="primary">新增角色</a-button>
    </div>
    <div class="Searchbox clearfix">
      <a-form-model :model="json">
        <a-form-item style="width:200px">
          <a-input autocomplete="autocomplete" placeholder="请输入角色名称" v-model="json.keyword">
          </a-input>

        </a-form-item>
      <a-form-item>
        <a-button :loading="subloading" @click="Search" type="primary">查询</a-button>
      </a-form-item>
      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
      <span slot="action" slot-scope="text, record">
          <template v-if="button.edit">
          <a @click="Edit(record)">编辑</a>
          <a-divider type="vertical" />
          </template>
          <template v-if="button.setauthority">
          <a @click="SetAuthority(record)">操作权限</a>
          <a-divider type="vertical" />
          </template>
          <a @click="Del(record.id)" v-if="button.del">删除</a>
        </span>
    </a-table>

    <a-modal centered :title="currentInfo.id==undefined?'新增角色':'编辑角色'" :visible="addvisible" :confirm-loading="addconfirmLoading" @ok="addonSubmit" @cancel="addCancel">
      <a-form-model ref="ruleForm" :model="currentInfo" :rules="addrules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
        <a-form-model-item label="角色名称" prop="name">
          <a-input autocomplete="autocomplete" size="large" placeholder="请输入角色名称" v-model="currentInfo.name">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="角色备注">
          <a-input autocomplete="autocomplete" size="large" placeholder="请输入角色备注" v-model="currentInfo.remark">
          </a-input>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal centered title="操作权限" :visible="setvisible" :confirm-loading="setconfirmLoading" @ok="setonSubmit" @cancel="setCancel">
      <div class="rolename">角色：{{setCurrentInfo.roleName}}</div>
      <a-tree checkable v-model="setCurrentInfo.menusId" :tree-data="AuthorityList" class="setmenutree"/>
    </a-modal>
  </a-card>

</template>

<script>
  import {IsAction} from '@/utils/button'
  import {asyncRoleSearch,asyncRoleCreate,asyncRoleDelete,GetAuthority,asyncAuthorityGet,asyncAuthoritySet} from '@/services/user'

  export default {
    name: 'rolelist',
    components: {},
    data () {
      return {
        json:{
          svcCaller:{
              page:1,
              pageSize:10
          },
          keyword:""
        },
        columns:[
          {
            title: '序号',
            dataIndex: 'id',
          },
          {
            title: '角色名称',
            dataIndex: 'name',
          },{
            title: '备注',
            dataIndex: 'remark',
          },{
            title: '更新时间',
            dataIndex: 'updateTime',
          },{
            title: '操作',
            key: 'action',
            width:180,
            scopedSlots: { customRender: 'action' },
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        Datalist:[],
        pageCount:0,
        recordCount:0,
        loading:false,
        subloading:false,
        AuthorityList:[],
        addrules: {
          name: [{ required: true, message: '请输入角色名称', whitespace: true,trigger: 'blur'}],
        },
        addvisible:false,
        addconfirmLoading:false,
        currentInfo:{},
        setvisible:false,
        setconfirmLoading:false,
        setCurrentInfo:{
          roleId:0,
          roleName:'',
          menusId:[]
        },
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.edit==undefined && this.button.del==undefined && this.button.setauthority==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        asyncRoleSearch(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.content
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      findAuthorityList(){
        GetAuthority().then(result => {
          if(result.data.code==1){
            this.AuthorityList=result.data.data
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      getAuthoritybyid(){

      },
      handleTableChange(pagination) {
        this.json.svcCaller.page = pagination.current
        this.getDataList()
      },
      Search(){
        this.pagination.total=0
        this.json.svcCaller.page=1
        this.getDataList()
      },
      RoleAdd(){
        this.currentInfo= {
          name:'',
          remark:'',
          state:1
        }
        this.addvisible=true
      },
      Edit(list){
        this.currentInfo={
          ...list
        }
        this.addvisible=true
      },
      SetAuthority(list){
        if(this.AuthorityList.length<=0){
          this.findAuthorityList()
        }
        this.setCurrentInfo.roleId=list.id
        this.setCurrentInfo.roleName=list.name
        this.setCurrentInfo.menusId=[]
        asyncAuthorityGet(this.setCurrentInfo.roleId).then(result => {
          if(result.data.code==1){
            this.setCurrentInfo.menusId=result.data.data.menusid
            this.setvisible=true
          }else{
            this.$message.error(result.data.msg);
          }
          this.loading = false
        })
      },
      Del(id){
        const _self=this
        this.$confirm({
          title: '确认删除角色吗？',
          //content: '',
          onOk() {
            asyncRoleDelete(id).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.getDataList()
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      addonSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.addconfirmLoading = true
            asyncRoleCreate(this.currentInfo).then(result => {
              if(result.data.code==1){
                this.addvisible=false
                this.getDataList()
              }else{
                this.$message.error(result.data.msg)
              }
              this.addconfirmLoading = false
            })
          } else {
            return false;
          }
        })
      },
      addCancel(){
        this.addvisible=false
      },
      setonSubmit(){
        const _self=this
        asyncAuthoritySet(this.setCurrentInfo.roleId,this.setCurrentInfo.menusId).then(result => {
          if(result.data.code==1){
            this.$message.success(result.data.msg,2,function(){
              _self.setvisible = false
            })

          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      setCancel(){
        this.setvisible=false
      },
    }
  }
</script>
<style lang="less">
  .rolename{height:40px;font-weight:700;color:#333;}
.setmenutree{height:400px;overflow-y:auto}
.ant-input-lg{font-size:12px;}
</style>
