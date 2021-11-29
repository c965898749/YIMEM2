<template>
  <a-card>

    <div class="Searchbox clearfix">
      <a-form-model :model="json">

        <a-form-item label="推荐人：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:240px">
          <a-input autocomplete="autocomplete" placeholder="公司名称" v-model="json.vpnIncomeGet.userName">
          </a-input>
        </a-form-item>

        <a-form-item label="联系电话：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:250px">
          <a-input autocomplete="autocomplete" placeholder="联系电话" v-model="json.vpnIncomeGet.phone">
          </a-input>
        </a-form-item>

        <a-form-item style="width:80px">
          <a-button @click="Search" type="primary">查询</a-button>
        </a-form-item>

      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">

      <span slot="action" slot-scope="text, record">
          <a @click="Detail(record.userId)">查看</a>
      </span>
    </a-table>

  </a-card>

</template>

<script>
  import {GetIncome} from '@/services/commission'
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
          vpnIncomeGet:{
            phone:"",
            userName:'',
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
          },{
            title: '发放总佣金（元）',
            dataIndex: 'money',
          },{
            title: '推荐人数（人）',
            dataIndex: 'rUserNum',
          },{
            title: '操作',
            key: 'action',
            width:60,
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
      }
    },
    created() {
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        GetIncome(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnIncomeGetResp
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
      Detail(userId){
        this.$router.push({path:'/issued/detail',query: {userId:userId}})
      }
    }
  }
</script>
<style lang="less">

</style>
