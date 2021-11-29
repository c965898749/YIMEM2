<template>
  <a-card>
    <div class="head_action">
      <a-button @click="SetState(0)" class="stop" type="primary" v-if="button.edit">下线</a-button>
      <a-button @click="SetState(1)" class="send" type="primary" v-if="button.edit">发布</a-button>
      <a-button @click="Add" class="Add" type="primary" v-if="button.add">新增攻略</a-button>
    </div>

    <a-tabs :default-active-key="json.vpnstrategyGet.toUser" @change="callback">
      <a-tab-pane key="1" tab="求职者">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:160px;">

              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnstrategyGet.state">
                <a-select-option v-for="(list,index) in stateName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:260px;">
              <a-range-picker @change="onChangeTime" :placeholder="['创建开始时间', '创建结束时间']"/>
            </a-form-item>

            <a-form-item style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="关键词搜索" v-model="json.vpnstrategyGet.title">
              </a-input>
            </a-form-item>

            <a-form-item style="width:80px;">
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>

        </div>
        <a-table :columns="columns" :rowSelection="{type:'checkbox',selectedRowKeys:selectedRowKeys,onSelect:checkbox,onSelectAll:checkboxAll}" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.stId" @change="handleTableChange">
          <a slot="tname" slot-scope="text,record" @click="Detail(record.stId)">{{record.title}}</a>
          <span slot="state" slot-scope="text, record" :class="'state'+text"><i>●</i> {{stateName[record.state]}}</span>
          <span slot="action" slot-scope="text, record">
            <a @click="SetState(1,record.stId,record.title)" v-if="record.state==0 || record.state==2">发布</a>
            <a @click="SetState(0,record.stId,record.title)" v-if="record.state==1">下线</a>
            <a-divider type="vertical" />

            <a @click="Edit(record.stId)">编辑</a>
            <a-divider type="vertical" />
            <a @click="Del(record.stId)">删除</a>
          </span>
        </a-table>
      </a-tab-pane>
      <a-tab-pane key="2" tab="分销者">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:160px;">

              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnstrategyGet.state">
                <a-select-option v-for="(list,index) in stateName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:260px;">
              <a-range-picker @change="onChangeTime" :placeholder="['创建开始时间', '创建结束时间']"/>
            </a-form-item>

            <a-form-item style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="关键词搜索" v-model="json.vpnstrategyGet.title">
              </a-input>
            </a-form-item>

            <a-form-item style="width:80px;">
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>

        </div>
        <a-table :columns="columns" :rowSelection="{type:'checkbox',selectedRowKeys:selectedRowKeys,onSelect:checkbox,onSelectAll:checkboxAll}" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.stId" @change="handleTableChange">
          <a slot="tname" slot-scope="text,record" @click="Detail(record.stId)">{{record.title}}</a>
          <span slot="state" slot-scope="text, record" :class="'state'+text"><i>●</i> {{stateName[record.state]}}</span>
          <span slot="action" slot-scope="text, record">
            <a @click="SetState(1,record.stId,record.title)" v-if="record.state==0 || record.state==2">发布</a>
            <a @click="SetState(0,record.stId,record.title)" v-if="record.state==1">下线</a>
            <a-divider type="vertical" />

            <a @click="Edit(record.stId)">编辑</a>
            <a-divider type="vertical" />
            <a @click="Del(record.stId)">删除</a>
          </span>
        </a-table>
      </a-tab-pane>
      <a-tab-pane key="3" tab="企业端">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:160px;">

              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnstrategyGet.state">
                <a-select-option v-for="(list,index) in stateName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:260px;">
              <a-range-picker @change="onChangeTime" :placeholder="['创建开始时间', '创建结束时间']"/>
            </a-form-item>

            <a-form-item style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="关键词搜索" v-model="json.vpnstrategyGet.title">
              </a-input>
            </a-form-item>

            <a-form-item style="width:80px;">
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>

        </div>
        <a-table :columns="columns" :rowSelection="{type:'checkbox',selectedRowKeys:selectedRowKeys,onSelect:checkbox,onSelectAll:checkboxAll}" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.stId" @change="handleTableChange">
          <a slot="tname" slot-scope="text,record" @click="Detail(record.stId)">{{record.title}}</a>
          <span slot="state" slot-scope="text, record" :class="'state'+text"><i>●</i> {{stateName[record.state]}}</span>
          <span slot="action" slot-scope="text, record">
            <template v-if="button.edit">
            <a @click="SetState(1,record.stId,record.title)" v-if="record.state==0 || record.state==2">发布</a>
            <a @click="SetState(0,record.stId,record.title)" v-if="record.state==1">下线</a>
            <a-divider type="vertical" />
            <a @click="Edit(record.stId)">编辑</a>
            <a-divider type="vertical" />
            </template>
            <a @click="Del(record.stId)" v-if="button.del">删除</a>
          </span>
        </a-table>
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {asyncvpnstrategyGetsvc,asyncvpnstrategyState,asyncvpnstrategyDelsvc} from '@/services/strategy'
  export default {
    data() {
      return {
        stateName:{
          0:'已下线',
          1:'已发布',
          2:'未发布',
        },
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpnstrategyGet:{
            toUser:this.$route.query.toUser==null?1:this.$route.query.toUser,
            title:'',
            startTime:'',
            endTime:'',
            state:undefined
          }
        },
        columns:[
          {
            title: '攻略标题',
            dataIndex: 'title',
            scopedSlots: { customRender: 'tname' }
          },{
            title: '浏览量',
            dataIndex: 'readCount',
          },{
            title: '状态',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state'},
          },{
            title: '发布时间',
            dataIndex: 'createTime',
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
        selectedRowKeys:[],
        Datalist:[],
        loading:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.edit==undefined && this.button.del==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.getDataList()
    },
    methods: {
      callback(key) {
        this.json.vpnstrategyGet.toUser=key
        this.Search()
      },
      getDataList() {
        this.loading=true
        asyncvpnstrategyGetsvc(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.content
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading=false
        })
      },
      onChangeTime(date, dateString){
        this.json.vpnstrategyGet.startTime=dateString[0]
        this.json.vpnstrategyGet.endTime=dateString[1]
      },
      Search(){
        this.pagination.total=0
        this.json.svcCaller.page=1
        this.getDataList()
      },
      handleTableChange(pagination) {
        this.json.svcCaller.page = pagination.current
        this.getDataList()
      },
      checkbox(record){
        let rowKeys=this.selectedRowKeys
        if(rowKeys.length>0 && rowKeys.includes(record.stId)){
          rowKeys.splice(rowKeys.indexOf(record.stId),1)
        }else{
          rowKeys.push(record.stId)
        }
        this.selectedRowKeys = rowKeys;
      },
      checkboxAll(selected){
        if(selected){
          for(var i=0;i<this.Datalist.length;i++){
            this.selectedRowKeys.push(this.Datalist[i].stId)
          }
        }else{
          this.selectedRowKeys=[]
        }
      },
      Detail(stId){
        this.$router.push({path:'/strategy/detail',query: {stId:stId,toUser:this.json.vpnstrategyGet.toUser}})
      },
      Add(){
        this.$router.push({path:'/strategy/add',query: {toUser:this.json.vpnstrategyGet.toUser}})
      },
      Edit(stId){
        this.$router.push({path:'/strategy/edit',query: {stId:stId,toUser:this.json.vpnstrategyGet.toUser}})
      },
      SetState(state,id,name){
        const _self=this

        var stId=[]
        if(id == undefined){
          if(this.selectedRowKeys.length<=0){
            this.$error({
              title: '请选择您要处理的攻略',
            });
            return false
          }
          stId=this.selectedRowKeys

        }else{
          stId=[id]
        }
        var requests={
          vpnstrategyUpdate:{
            stId:stId,
            state:state
          }
        }
        this.$confirm({
          title: state==1?(id==undefined?'是否发布这些攻略？':'是否发布"'+name+'"攻略？'):(id==undefined?'是否下线这些攻略？':'是否攻略“'+name+'”攻略？'),
          //content: '',
          onOk() {
            asyncvpnstrategyState(requests).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.getDataList()
                _self.selectedRowKeys=[]
              }else{
                this.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      Del(stId){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
            asyncvpnstrategyDelsvc(stId).then(result => {
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
      }
    }
  }
</script>
<style lang="less">
  .head_action{
    .stop{background:#fff;border:1px solid #ddd;color:#999;}
    .send{background:#e7f5f8;color:#1890ff;}
  }
  .ant-table-row{
    .state0 i{color:#bfbfbf}
    .state1 i{color:#1c90f5}
    .state2 i{color:#bfbfbf}
  }
</style>
