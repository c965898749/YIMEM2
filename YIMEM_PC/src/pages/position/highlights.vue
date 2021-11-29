<template>
  <a-card>
    <div class="head_action" v-if="button.add">
    <a-button @click="Add" class="Add" type="primary">新增职位亮点</a-button>
    </div>
    <div class="Searchbox clearfix">
      <a-form-model :model="json">

          <a-form-item label="职位类型：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:240px">
            <a-input autocomplete="autocomplete" placeholder="职位类型" v-model="json.vpnjobLightenEnumGet.name">
            </a-input>
          </a-form-item>

          <a-form-item style="width:260px">
            <a-range-picker @change="onChangeTime" :placeholder="['创建开始时间', '创建结束时间']"/>
          </a-form-item>

          <a-form-item style="width:80px">
            <a-button :loading="subloading" @click="Search" type="primary">查询</a-button>
          </a-form-item>

      </a-form-model>

    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.jobLightenId" @change="handleTableChange">
      <span slot="action" slot-scope="text, record">
          <template v-if="button.edit">
          <a @click="Edit(record)">编辑</a>
          <a-divider type="vertical" />
          </template>
          <a @click="Del(record.jobLightenId)" v-if="button.del">删除</a>
      </span>
    </a-table>

    <a-modal centered :title="currentInfo==''?'新增职位亮点':'编辑职位亮点'" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="currentInfo" :rules="rules" :label-col="{ span: 7 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="职位亮点名称" prop="name">
          <a-input autocomplete="autocomplete" placeholder="职位类型" v-model="currentInfo.name">
          </a-input>
        </a-form-model-item>

      </a-form-model>
    </a-modal>

  </a-card>

</template>

<script>
  import {IsAction} from '@/utils/button'
  import {vpnjobLightenEnumGetList,asyncvpnjobLightenEnumAdd,asyncvpnjobLightenEnumDel} from '@/services/job'
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
          vpnjobLightenEnumGet:{
            name:"",
            startTime:"",
            endTime:"",
            state:1
          },
        },
        columns:[
          {
            title: '职位亮点名称',
            dataIndex: 'name',
          },{
            title: '创建时间',
            dataIndex: 'createTime',
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
        rules: {
          name: [{ required: true, message: '请输入职位类型',trigger: 'blur'}],
        },
        Datalist:[],
        pageCount:0,
        recordCount:0,
        loading:false,
        subloading:false,
        visible:false,
        confirmLoading:false,
        currentInfo:'',
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
      getDataList() {
        this.loading = true
        vpnjobLightenEnumGetList(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnjobLightenEnumGetResp
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      onChangeTime(date, dateString){
        console.log(date,dateString)
        this.json.vpnjobLightenEnumGet.startTime=dateString[0]
        this.json.vpnjobLightenEnumGet.endTime=dateString[1]
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
      getEmptyInfo(){
        this.currentInfo={
          name:"",
          state:1
        }
      },
      Add(){
        this.getEmptyInfo()
        this.visible=true
      },
      Edit(list){
        this.currentInfo={
          ...list
        }
        this.visible=true
      },
      Del(id){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
            asyncvpnjobLightenEnumDel(id).then(result => {
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
      Cancel(){
        this.visible=false
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            if(this.currentInfo.jobLightenId){
              requests={vpnjobLightenEnumUpdate:this.currentInfo}
            }else{
              requests={vpnjobLightenEnumAdd:this.currentInfo}
            }
            this.confirmLoading=true
            asyncvpnjobLightenEnumAdd(this.currentInfo.jobLightenId?'edit':'add',requests).then(result => {
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
    }
  }
</script>
<style lang="less">

  .ant-input-lg{font-size:12px;}
</style>
