<template>
  <a-card>

    <div class="Searchbox clearfix">
      <a-form-model :model="json">

        <a-form-item label="公司名称：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:250px">
          <a-input autocomplete="autocomplete" placeholder="公司名称" v-model="json.vpnCompanyBillGet.companyName">
          </a-input>
        </a-form-item>

        <a-form-item label="状态：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:200px">
          <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnCompanyBillGet.state">
            <a-select-option key="0">未完成</a-select-option>
            <a-select-option key="1">已完成</a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item style="width:80px">
          <a-button @click="Search" type="primary">查询</a-button>
        </a-form-item>

      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
      <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{text==0?'未完成':'已完成'}}</span>
      <span slot="action" slot-scope="text, record">
        <template v-if="record.state==0 && button.collection">
          <a @click="Collection(record)">收款</a>
          <a-divider type="vertical"/>
        </template>
        <a @click="Detail(record.companyId,record.id)">查看</a>
      </span>
    </a-table>

    <a-modal centered title="收款" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="moneyjson" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="应收金额">
          <a-input autocomplete="autocomplete" disabled placeholder="应收金额" v-model="moneyjson.sum" suffix="元">
          </a-input>
        </a-form-model-item>

        <a-form-model-item label="已收金额">
          <a-input autocomplete="autocomplete" disabled placeholder="已收金额" v-model="moneyjson.oldpaid" suffix="元">
          </a-input>
        </a-form-model-item>

        <a-form-model-item label="当前收款" prop="paid">
          <a-input autocomplete="autocomplete" placeholder="当前收款" v-model="moneyjson.paid" suffix="元">
          </a-input>
        </a-form-model-item>

      </a-form-model>
    </a-modal>

  </a-card>

</template>

<script>
  import {IsAction} from '@/utils/button'
  import {validatorSNumber} from '@/utils/validata'
  import {GetCompanyBill,asyncCompanyBillUpdate} from '@/services/commission'
  export default {
    name: 'list',
    components: {},
    data () {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpnCompanyBillGet:{
            companyName:"",
            state:undefined,
          },
        },
        Datalist:[],
        columns:[
          {
            title: '公司名称',
            dataIndex: 'companyName',
          },
          {
            title: '应收金额（元）',
            dataIndex: 'sum',
          },{
            title: '已收金额（元）',
            dataIndex: 'paid',
          },{
            title: '入职人数（人）',
            dataIndex: 'memberNum',
          },{
            title: '状态',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state' },
          },{
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
        moneyjson:{},
        rules:{
          paid: [{ required: true, validator:validatorSNumber,emptymsg: '请输入当前收款金额',trigger: 'blur'}],
        },
        loading:false,
        visible:false,
        confirmLoading:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      console.log(this.button)
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        GetCompanyBill(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnCompanyBillGetResp
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
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            asyncCompanyBillUpdate(this.moneyjson).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.getDataList()
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
      Cancel(){
        this.visible=false
      },
      Collection(list){
        this.moneyjson.id=list.id
        this.moneyjson.sum=list.sum
        this.moneyjson.oldpaid=list.paid
        this.visible=true
      },
      Detail(companyId,id){
        this.$router.push({path:'/income/detail',query: {companyId:companyId,id:id}})
      }
    }
  }
</script>
<style lang="less">
  .ant-table-row{
    .state0 i{color:#bfbfbf}
    .state1 i{color:#1694ff}
  }
</style>
