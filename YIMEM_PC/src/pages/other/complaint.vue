<template>
  <a-card>
    <div class="Searchbox clearfix">
      <a-form-model :model="json">
        <a-form-item label="姓名：" :labelCol="{span: 5}" :wrapperCol="{span: 19}" style="width:200px">
          <a-input autocomplete="autocomplete" placeholder="姓名" v-model="json.vpncomplaintGet.userName">
          </a-input>
        </a-form-item>

        <a-form-item label="电话：" :labelCol="{span: 5}" :wrapperCol="{span: 19}" style="width:200px">
          <a-input autocomplete="autocomplete" placeholder="电话" v-model="json.vpncomplaintGet.phone">
          </a-input>
        </a-form-item>
        <a-form-item style="width:30%;">
          <a-range-picker @change="onChangeTime" :placeholder="['创建开始时间', '创建结束时间']"/>
        </a-form-item>
        <a-form-item style="width:80px;">
          <a-button @click="Search" type="primary">查询</a-button>
        </a-form-item>


      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.comId" @change="handleTableChange">
      <a-popover placement="bottom" slot="content" slot-scope="text, record">
        <template slot="content">
          <p style="width:300px">{{record.content}}</p>
        </template>
        <span>{{record.content.substring(0,10)}}</span>
      </a-popover>

      <span slot="action" slot-scope="text, record" v-if="button.del">
            <a @click="Del(record.comId)">删除</a>
          </span>
    </a-table>
  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {getvpncomplaintGet,asyncvpncomplaintDel} from '@/services/other'
  export default {
    data() {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpncomplaintGet:{
            userName:"",
            startTime:"",
            endTime:"",
            phone:""
          }
        },
        columns:[
          {
            title: '姓名',
            dataIndex: 'userName'
          },{
            title: '联系电话',
            dataIndex: 'phone',
          },{
            title: '投诉内容',
            dataIndex: 'content',
            scopedSlots: { customRender: 'content'},
          },{
            title: '投诉时间',
            dataIndex: 'createTime',
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
        Datalist:[],
        loading:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.del==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading=true
        getvpncomplaintGet(this.json).then(result => {
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
        this.json.vpncomplaintGet.startTime=dateString[0]
        this.json.vpncomplaintGet.endTime=dateString[1]
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
      Del(comId){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
            asyncvpncomplaintDel(comId).then(result => {
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

</style>
