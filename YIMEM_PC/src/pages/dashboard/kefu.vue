<template>
  <a-card>
    <div class="head_action" v-if="button.add">
      <a-button @click="Add" class="Add" type="primary">新增客服</a-button>
    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.cusId" @change="handleTableChange">

      <span slot="toUser" slot-scope="text, record">{{topUser[record.toUser]}}</span>
      <span slot="time" slot-scope="text, record">{{record.startTime}} - {{record.endTime}}</span>
      <span slot="action" slot-scope="text, record">
        <a @click="Edit(record)" v-if="button.edit">编辑</a>
        <a-divider type="vertical" v-if="button.edit"/>
        <a @click="Del(record.cusId)" v-if="button.del">删除</a>
        </span>
    </a-table>

    <a-modal centered :title="currentInfo==''?'新增客服':'编辑客服'" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="currentInfo" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="服务类型" prop="toUser">

          <a-select placeholder="请选择服务类型" @change="ChangetoUser" v-model="currentInfo.toUser">
            <a-select-option v-for="(list,index) in topUser" :key="index">
              {{ list }}
            </a-select-option>
          </a-select>

        </a-form-model-item>
        <a-form-model-item label="备注">
          <a-textarea autocomplete="autocomplete" placeholder="备注" v-model="currentInfo.remark">
          </a-textarea>
        </a-form-model-item>

        <a-form-model-item label="服务时间" prop="startTime">

          <a-time-picker :open.sync="TimeStar" v-model="currentInfo.startTime">
            <a-button slot="addon" size="small" type="primary" @click="TimehandleClose">
              确定
            </a-button>
          </a-time-picker>
          -
          <a-time-picker :open.sync="TimeEnd" v-model="currentInfo.endTime">
            <a-button slot="addon" size="small" type="primary" @click="TimehandleClose">
              确定
            </a-button>
          </a-time-picker>

        </a-form-model-item>

        <a-form-model-item label="客服电话" prop="phone">
          <a-input autocomplete="autocomplete" placeholder="请输入客服电话" v-model="currentInfo.phone">
          </a-input>
        </a-form-model-item>

      </a-form-model>
    </a-modal>

  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import moment from 'moment';
  import {validatorPhone} from '@/utils/validata'
  import {asynccustomerGet,asynccustomerAdd,asynccustomerDel} from '@/services/dashboard'
  export default {
    data() {
      return {
        topUser:{
          1:'求职者',
          2:'分销者',
          3:'企业端'
        },
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
        },
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        columns:[
          {
            title: '服务类型',
            dataIndex: 'toUser',
            scopedSlots: { customRender: 'toUser' },
          },
          {
            title: '客服电话',
            dataIndex: 'phone',
          },{
            title: '服务时间',
            dataIndex: 'startTime',
            scopedSlots: { customRender: 'time' }
          },{
            title: '备注',
            dataIndex: 'remark',
          },{
            title: '更新时间',
            dataIndex: 'createTime',
          },{
            title: '操作',
            key: 'action',
            width:110,
            scopedSlots: { customRender: 'action' },
          }
        ],
        rules: {
          toUser: [{ required: true, message: '请选择服务类型',trigger: 'blur'}],
          startTime: [{ required: true, message: '请选择服务开始时间', trigger: 'blur'}],
          endTime: [{ required: true, message: '请选择服务结束时间', trigger: 'blur'}],
          phone: [{ required: true, validator:validatorPhone,emptymsg: '请选输入手机号码', trigger: 'blur'}]
        },
        Datalist:[],
        currentInfo:{},
        TimeStar:false,
        TimeEnd:false,
        visible:false,
        loading:false,
        confirmLoading:false,
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
      moment,
      getDataList() {
        asynccustomerGet(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.content
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      ChangetoUser(value){
        this.currentInfo.toUser=value
      },
      handleTableChange(pagination) {
        this.json.svcCaller.page = pagination.current
        this.getDataList()
      },
      getEmptyInfo(){

        this.currentInfo={
          toUser:undefined,
          startTime:null,
          endTime:null,
          remark:"",
          phone:"",
        }
      },
      Add(){
        this.getEmptyInfo()
        this.visible=true
      },
      Edit(list){
        list.toUser=list.toUser=='0'?undefined:list.toUser
        this.currentInfo= {
          ...list
        }
        this.currentInfo.startTime=moment(this.currentInfo.startTime,'HH:mm:ss')
        this.currentInfo.endTime=moment(this.currentInfo.endTime,'HH:mm:ss')

        this.visible=true
      },
      TimehandleClose() {
        this.TimeStar = false;
        this.TimeEnd = false;
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            let json=Object.assign({}, this.currentInfo)
            json.startTime=json.startTime.format('HH:mm:ss')
            json.endTime=json.endTime.format('HH:mm:ss')
            if(this.currentInfo.cusId){
              requests={vpncustomerUpdate:json}
            }else{
              requests={vpncustomerAdd:json}
            }
            this.confirmLoading=true

            asynccustomerAdd(this.currentInfo.cusId?'edit':'add',requests).then(result => {
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
      Del(cusId){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
            let requests ={
              vpncustomerDel:{
                cusId:cusId
              }
            }
            asynccustomerDel(requests).then(result => {
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
    }
  }
</script>
<style lang="less">
</style>
