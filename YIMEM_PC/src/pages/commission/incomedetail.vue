<template>
  <a-card>
    <div class="p_title">
      <label>{{company.name}}</label>
    </div>
    <div class="descriptions_title"><label>公司信息</label></div>
    <a-descriptions>
      <a-descriptions-item label="公司名称">
        {{company.name}}
      </a-descriptions-item>
      <a-descriptions-item label="姓名">
        {{company.userName}}
      </a-descriptions-item>
      <a-descriptions-item label="公司职位">
        {{company.job}}
      </a-descriptions-item>
      <a-descriptions-item label="联系电话">
        {{company.phone}}
      </a-descriptions-item>
      <a-descriptions-item label="所属行业">
        {{company.businessName}}
      </a-descriptions-item>
      <a-descriptions-item label="公司规模">
        {{company.scale}}人
      </a-descriptions-item>
      <a-descriptions-item label="营业执照">
        <img :src="company.licenseUrl" width="100"/>
      </a-descriptions-item>
      <a-descriptions-item label="公司地址">
        {{company.adress}}
      </a-descriptions-item>
    </a-descriptions>

    <a-divider />

    <div class="pageTitle clearfix">
      <div class="table_title">入职列表</div>
      <div class="Searchbox clearfix">
        <a-form-model :model="json">

          <a-form-model-item style="width:200px">
            <a-input autocomplete="autocomplete" placeholder="请搜索姓名" v-model="json.vpnCompanyBillDetailGet.userName">
            </a-input>
          </a-form-model-item>
          <a-form-model-item style="width:80px">
            <a-button @click="Search" type="primary">查询</a-button>
          </a-form-model-item>
        </a-form-model>
      </div>
    </div>
    <a-table :columns="columns" :data-source="Datalist" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
      <span slot="info" slot-scope="text,record">{{record.user.userSex==1?'男':'女'}}|{{record.user.age}}|{{record.user.education}}|{{record.user.workage}}|{{record.user.cityName}}</span>

    </a-table>

  </a-card>

</template>

<script>
  import  $ from "jquery"
  import {GetcompanyDetail,GetCompanyBillDetail} from '@/services/commission'
  export default {
    name: 'list',
    components: {},
    data () {
      return {
        companyId:this.$route.query.companyId,
        company:{},
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpnCompanyBillDetailGet:{
            id:this.$route.query.id,
            userName:'',
          },
        },
        Datalist:[],
        columns:[
          {
            title: '应聘人',
            dataIndex: 'userName',
          },
          {
            title: '联系方式',
            dataIndex: 'userPhone',
          },
          {
            title: '基本信息',
            dataIndex: 'sex',
            scopedSlots: { customRender: 'info' },
          },
          {
            title: '应聘职位',
            dataIndex: 'jobName',
          },
          {
            title: '职位类型',
            dataIndex: 'jabCateName',
          },
          {
            title: '企业收费',
            dataIndex: 'jobMoney',
          },
          {
            title: '入职时间',
            dataIndex: 'userEntryTime',
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
      }
    },
    created() {
      this.getcompany()
      this.getDataList()

    },
    mounted(){

    },
    methods: {
      getcompany(){
        GetcompanyDetail(this.companyId).then(result => {
          if(result.data.code==1){
            this.company=result.data.data.vpncompanyGetResp
            $('.page-header-wide h1.title').html(this.company.name)
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      getDataList() {
        this.loading = true
        GetCompanyBillDetail(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnCompanyBillDetailGetResp
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
      }
    }
  }

</script>
<style lang="less">
  .page-header-wide{
    .main{min-height:46px;}
  }
  .p_title{width:100%;height:30px;line-height:30px;color: rgba(0, 0, 0, 0.85);position:absolute;top:-70px;left:0px;padding:0px 10px;
    label{font-size: 20px;color: rgba(0, 0, 0, 0.85);}
  }

  .descriptions_title{height:auto;margin-bottom:20px;
    label{height:30px;line-height:30px;color: rgba(0, 0, 0, 0.85);font-weight: bold;font-size: 16px;}
    span{background:#40a9ff;border-radius:3px;color:#fff;display:inline-block;height:30px;line-height:30px;padding:0px 10px;margin-left:10px;}
  }
  .pageTitle{height:auto;margin-top:10px;
    .table_title{float:left;height:40px;line-height:40px;color: rgba(0, 0, 0, 0.85);font-weight: bold;font-size: 16px;}
    .Searchbox{width:300px;float:right;}
  }
</style>
