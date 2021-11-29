<template>
  <a-card>

    <div class="Searchbox clearfix">
      <a-form-model :model="json">

        <a-form-item label="推荐人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:180px">
          <a-input autocomplete="autocomplete" placeholder="推荐人" v-model="json.vpnincomeDetailGet.userName">
          </a-input>
        </a-form-item>

        <a-form-item label="应聘人：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:180px">
          <a-input autocomplete="autocomplete" placeholder="应聘人" v-model="json.vpnincomeDetailGet.rUserName">
          </a-input>
        </a-form-item>

        <a-form-item label="应聘公司：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
          <a-input autocomplete="autocomplete" placeholder="应聘公司" v-model="json.vpnincomeDetailGet.companyName">
          </a-input>
        </a-form-item>

        <a-form-item style="width:240px">
          <a-range-picker @change="onChangeTime" :placeholder="['开始发放时间', '结束时间']"/>
        </a-form-item>

        <a-form-item style="width:80px">
          <a-button @click="Search" type="primary">查询</a-button>
        </a-form-item>

      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
    </a-table>

  </a-card>

</template>

<script>
  import {GetincomeDetail} from '@/services/commission'
  export default {
    name: 'list',
    components: {},
    data () {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:20
          },
          vpnincomeDetailGet:{
            userId:this.$route.query.userId,
            userName:"",
            rUserName:"",
            companyName:"",
            startTime:"",
            endTime:''
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
            title: '应聘人',
            dataIndex: 'rUserName',
          },
          {
            title: '联系电话',
            dataIndex: 'rPhone',
          },
          {
            title: '应聘职位',
            dataIndex: 'jobName',
          },
          {
            title: '应聘公司',
            dataIndex: 'companyName',
          },
          {
            title: '企业收费',
            dataIndex: 'money',
          },
          {
            title: '入职时间',
            dataIndex: 'entryTime',
          },
          {
            title: '推荐佣金',
            dataIndex: 'brokerage',
          },
          {
            title: '发放时间',
            dataIndex: 'createdTime',
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        loading:false,
      }
    },
    created() {
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        GetincomeDetail(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnincomeDetailGetResp
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
        this.json.vpnincomeDetailGet.startTime=dateString[0]
        this.json.vpnincomeDetailGet.endTime=dateString[1]
      },
    }
  }
</script>
<style lang="less">

</style>
