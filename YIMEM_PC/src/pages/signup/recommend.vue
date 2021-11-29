<template>
  <a-card>
    <a-tabs :default-active-key="tag" @change="callback">
      <a-tab-pane key="1" tab="企业面试">
        <div class="Searchbox clearfix">

          <a-form-model :model="searchjson">

            <a-form-model-item label="应聘人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:160px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.userName">
              </a-input>
            </a-form-model-item>

            <a-form-model-item label="推荐人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.reUserName">
              </a-input>
            </a-form-model-item>

            <a-form-model-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:200px;">
              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="searchjson.vpndeliverGet.state">
                <a-select-option v-for="(list,index) in stateName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-model-item>

            <a-form-model-item label="入职时间：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.day">
              </a-input>
            </a-form-model-item>

            <a-form-model-item>
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-model-item>
          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="DataList" :pagination="pagination" :rowKey="Datalist=>Datalist.devId" @change="handleTableChange">
          <a slot="userName" slot-scope="text,record" @click="Detail(record.devId,record.userId)">{{record.userName}}</a>
          <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{stateName[text]}}</span>
          <span slot="action" slot-scope="text, record">
            <a @click="Detail(record.devId,record.userId)">查看</a>
          </span>
        </a-table>

      </a-tab-pane>
      <a-tab-pane key="2" tab="委托面试">
        <div class="Searchbox clearfix">

          <a-form-model :model="searchjson">

            <a-form-model-item label="应聘人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:160px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.userName">
              </a-input>
            </a-form-model-item>

            <a-form-model-item label="推荐人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.reUserName">
              </a-input>
            </a-form-model-item>

            <a-form-model-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:200px;">
              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="searchjson.vpndeliverGet.state">
                <a-select-option v-for="(list,index) in stateName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-model-item>

            <a-form-model-item label="入职时间：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px;">
              <a-input autocomplete="autocomplete" placeholder="请输入" v-model="searchjson.vpndeliverGet.day">
              </a-input>
            </a-form-model-item>

            <a-form-model-item>
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-model-item>
          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="DataList" :pagination="pagination" :rowKey="Datalist=>Datalist.devId" @change="handleTableChange">
          <a slot="userName" slot-scope="text,record" @click="Detail(record.devId,record.userId)">{{record.userName}}</a>
          <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{stateName[text]}}</span>
          <span slot="action" slot-scope="text, record">
            <a-popover v-if="record.state==1 || record.state==2 || record.state==3">
              <template slot="content">
                <p><a @click="DeliverDetailAdd(0,record.devId,record.userName)" v-if="record.state==1 || record.state==2 || record.state==3">不适合</a></p>
                <p><a @click="DeliverDetailAdd(2,record.devId,record.userName)" v-if="record.state==1">邀面试</a></p>
                <p><a @click="DeliverDetailAdd(4,record.devId,record.userName)" v-if="record.state==3">推送企业</a></p>
              </template>
              <a>操作</a>
            </a-popover>
            <a-divider type="vertical" v-if="record.state==1 || record.state==2 || record.state==3"/>
            <a @click="Detail(record.devId,record.userId)">查看</a>
          </span>
        </a-table>

      </a-tab-pane>
    </a-tabs>

    <a-modal centered title="邀请面试" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="addjson" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="面试时间" prop="time">
          <a-date-picker v-model="addjson.time" show-time type="date" placeholder="请选择面试时间" style="width: 100%;"/>
        </a-form-model-item>

        <a-form-model-item label="面试地点" prop="address">
          <a-input autocomplete="autocomplete" placeholder="面试地点" v-model="addjson.address">
          </a-input>
        </a-form-model-item>

        <a-form-model-item label="面试备注">
          <a-textarea autocomplete="autocomplete" placeholder="面试备注" v-model="addjson.remark">
          </a-textarea>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-card>
</template>

<script>
  import {GetTJQYdeliver,GetTJWTdeliver,asyncvpnDeliverDetailAdd} from '@/services/job'
  export default {
    components: {},
    data() {
      return {
        tag:1,
        stateName:{
          0:'不合适',
          1:'已投递',
          2:'已邀约',
          3:'待面试',
          4:'已推荐',
          5:'企业面试',
          6:'已面试',
          7:'已入职',
          8:'已离职'
        },
        searchjson:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpndeliverGet:{
            reUserName:'',
            userName:'',
            state:undefined,
            day:''
          }
        },
        DataList:[],
        columns:[
          {
            title: '应聘人',
            dataIndex: 'userName',
            scopedSlots: { customRender: 'userName'}
          },{
            title: '联系电话',
            dataIndex: 'phoneNum',
          },{
            title: '推荐人',
            dataIndex: 'reUserName',
          },{
            title: '联系电话',
            dataIndex: 'rePhone',
          },{
            title: '应聘职位',
            dataIndex: 'jobName',
          },{
            title: '职位类型',
            dataIndex: 'jobCateName',
          },{
            title: '应聘公司',
            dataIndex: 'companyName',
          },{
            title: '状态',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state' }
          },{
            title: '更新时间',
            dataIndex: 'updateTime',
          },{
            title: '操作',
            key: 'action',
            width:120,
            scopedSlots: { customRender: 'action' }
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        addjson:{
          devId:0,
          state:0,
          remark:'',
          address:'',
          time:''
        },
        rules: {
          time: [{ required: true, message: '请选择面试时间',trigger: 'blur'}],
          address: [{ required: true, message: '请输入面试地址', trigger: 'blur'}]
        },
        visible:false,
        confirmLoading:false
      }
    },
    created() {
      this.findList()
    },
    methods: {
      callback(key) {
        this.tag=key
        this.findList()
      },
      handleTableChange(pagination) {
        this.liverjson.svcCaller.page = pagination.current
        this.findList()
      },
      findList(){
        if(this.tag=='1'){
          GetTJQYdeliver(this.searchjson).then(result => {
            if(result.data.code==1){
              this.DataList=result.data.data.vpndeliverGetResp
            }else{
              this.$message.error(result.data.msg)
            }
          })
        }else if(this.tag=='2'){
          GetTJWTdeliver(this.searchjson).then(result => {
            if(result.data.code==1){
              this.DataList=result.data.data.vpndeliverGetResp
            }else{
              this.$message.error(result.data.msg)
            }
          })
        }

      },
      Search(){
        this.pagination.total=0
        this.searchjson.svcCaller.page=1
        this.findList()
      },
      Detail(devId,userId){
        this.$router.push({path:'/job/personnelspeed',query: {devId:devId,userId:userId}})
      },
      DeliverDetailAdd(state,devId,userName){
        const _self=this
        if(state==0 || state==4){

          this.$confirm({
            title: state=='0'?'不合适':'推送企业',
            content: state=='0'?'是否将人才"'+userName+'"标记不为合适？':'是否将人才"'+userName+'"推送至企业？',
            onOk() {
              const requests={
                devId:devId,
                state:state
              }
              asyncvpnDeliverDetailAdd(requests).then(result => {
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
        }else if(state==2){
          this.addjson.state=state
          this.addjson.devId=devId
          this.visible=true
        }
      },
      Cancel(){
        this.visible=false
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading=true
            asyncvpnDeliverDetailAdd(this.addjson).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.findList()
                this.visible=false
              }else{
                this.$message.error(result.data.msg)
              }
              this.confirmLoading=false
            })
          } else {
            return false;
          }
        })

      },

    }
  }
</script>
<style lang="less">

  .ant-table-row{
    .state0 i{color:#bfbfbf}
    .state1 i{color:#1c90f5}
    .state2 i{color:#1c90f5}
    .state3 i{color:#1c90f5}
    .state4 i{color:#1c90f5}
    .state5 i{color:#1c90f5}
    .state6 i{color:#1c90f5}
    .state7 i{color:#59be24}
    .state8 i{color:#bfbfbf}
  }
</style>
