<template>
  <a-card>

    <div class="Searchbox clearfix">
      <a-form-model :model="json">

        <a-form-item label="推荐人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:180px">
          <a-input autocomplete="autocomplete" placeholder="推荐人" v-model="json.vpnApplicationForWithdrawalGet.userName">
          </a-input>
        </a-form-item>

        <a-form-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:180px">
          <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnApplicationForWithdrawalGet.state">
            <a-select-option v-for="(list,index) in stateName" :key="index">{{list}}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item style="width:240px">
          <a-range-picker @change="onChangeTime" :placeholder="['申请开始时间', '结束时间']"/>
        </a-form-item>
        <a-form-item style="width:80px">
          <a-button @click="Search" type="primary">查询</a-button>
        </a-form-item>

      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
      <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{stateName[text]}}</span>
      <span slot="action" slot-scope="text, record">
        <template v-if="record.state==1 && button.examine">
          <a @click="Examine(record,2)">发放</a>
          <a-divider type="vertical"/>
          <a @click="Examine(record,0)">驳回</a>
        </template>

        <a-popover placement="bottom" :content="record.reason" v-if="record.state==0">
          <a>原因</a>
        </a-popover>

      </span>
    </a-table>

    <a-modal centered title="驳回" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="examinejson" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
        <a-form-model-item label="驳回原因" prop="reason">
          <a-textarea autocomplete="autocomplete" v-model="examinejson.reason">
          </a-textarea>
        </a-form-model-item>

      </a-form-model>
    </a-modal>

  </a-card>

</template>

<script>
  import {IsAction} from '@/utils/button'
  import {GetApplicationForWithdrawal,UpdateApplicationForWithdrawal} from '@/services/commission'
  export default {
    name: 'list',
    components: {},
    data () {
      return {
        stateName:{
          0:'已驳回',
          1:'待审核',
          2:'已发放'
        },
        json:{
          svcCaller:{
            page:1,
            pageSize:20
          },
          vpnApplicationForWithdrawalGet:{
            userName:'',
            state:undefined,
            startTime:"",
            endTime:""
          },
        },
        Datalist:[],
        columns:[
          {
            title: '推荐人',
            dataIndex: 'userName',
          },
          {
            title: '联系电话',
            dataIndex: 'phone',
          },
          {
            title: '提现金额（元）',
            dataIndex: 'money',
          },
          {
            title: '可提现金额（元）',
            dataIndex: 'balance',
          },
          {
            title: '申请时间',
            dataIndex: 'applyTime',
          },
          {
            title: '状态',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state' },
          },
          {
            title: '操作',
            key: 'action',
            width:120,
            scopedSlots: { customRender: 'action' },
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        loading:false,
        visible:false,
        confirmLoading:false,
        examinejson:{
          id:0,
          state:0,
          reason:''
        },
        rules:{
          reason: [{ required: true, message: '请输入驳回原因',trigger: 'blur'}],
        },
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        GetApplicationForWithdrawal(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnApplicationForWithdrawalGet
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
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
      onChangeTime(date, dateString){
        this.json.vpnApplicationForWithdrawalGet.startTime=dateString[0]
        this.json.vpnApplicationForWithdrawalGet.endTime=dateString[1]
      },
      Examine(list,state){
        const _self=this
        this.examinejson.state=state
        this.examinejson.id=list.id
        if(state==0){
          this.visible=true
        }else if(state==2){
          this.$confirm({
            title: '发放',
            content: '是否同意发放“'+list.userName+'”的推荐佣金？',
            icon:'',
            onOk() {
              UpdateApplicationForWithdrawal(_self.examinejson).then(result => {
                if(result.data.code==1){
                  _self.$message.success(result.data.msg, 3)
                  _self.getDataList()
                }else{
                  _self.$message.error(result.data.msg)
                }
              })
            },
            onCancel() {

            },
          })
        }
      },
      onSubmit(){
        const _self=this
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading=true
            UpdateApplicationForWithdrawal(this.examinejson).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                _self.getDataList()
              }else{
                this.$message.error(result.data.msg)
              }
              this.visible=false
              this.confirmLoading=false
            })
          } else {
            return false;
          }
        })

      },
      Cancel(){
        this.visible=false
      },
    }
  }
</script>
<style lang="less">
  .ant-table-row{
    .state0 i{color:#bfbfbf}
    .state1 i{color:#1c90f5}
    .state2 i{color:#bfbfbf}
  }
</style>
