<template>
  <a-card>
    <a-tabs :default-active-key="tag" @change="callback">

      <a-tab-pane key="1" tab="分销者">

        <div class="Searchbox clearfix">
          <a-form-model :model="jobhunterjson">
            <a-form-item label="姓名：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:180px;">
              <a-input autocomplete="autocomplete" placeholder="姓名" v-model="jobhunterjson.vpnjobhunterGet.name">
              </a-input>
            </a-form-item>

            <a-form-item label="联系电话：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:220px;">
              <a-input autocomplete="autocomplete" placeholder="联系电话" v-model="jobhunterjson.vpnjobhunterGet.phone">
              </a-input>
            </a-form-item>

            <a-form-item style="width:60px;">
              <a-button :loading="loading" @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
          <span slot="action" slot-scope="text,record" v-if="button.examine">
            <a @click="SetState(record.id,record.name)" v-if="record.state==1">审核</a>
          </span>
        </a-table>

      </a-tab-pane>

      <a-tab-pane key="2" tab="求职者" force-render>
        <div class="Searchbox clearfix">
          <a-form-model :model="distributionjson">
            <a-form-item label="姓名：" :labelCol="{span: 6}" :wrapperCol="{span: 18}" style="width:180px;">
              <a-input autocomplete="autocomplete" placeholder="姓名" v-model="jobhunterjson.vpnjobhunterGet.name">
              </a-input>
            </a-form-item>

            <a-form-item label="联系电话：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:220px;">
              <a-input autocomplete="autocomplete" placeholder="联系电话" v-model="jobhunterjson.vpnjobhunterGet.phone">
              </a-input>
            </a-form-item>

            <a-form-item style="width:60px;">
              <a-button :loading="loading" @click="SearchDistribution" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>
        </div>

        <a-table :columns="distributioncolumns" :data-source="Datalist" :loading="loading" :pagination="distributionpagination" :rowKey="Datalist=>Datalist.id" @change="distributionChange">

        </a-table>

      </a-tab-pane>
    </a-tabs>

    <a-modal centered :title="'审核 - '+examinejson.name" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmitexamine" @cancel="Cancelexamine">
      <a-form-model :model="examinejson" ref="ruleForm" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 19 }">
        <a-form-model-item label="审核">
          <a-radio-group name="radioGroup" v-model="examinejson.state">
            <a-radio :value="2">通过</a-radio>
            <a-radio :value="0">不通过</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="原因" v-if="examinejson.state==0" prop="resion">
          <a-textarea autocomplete="autocomplete" placeholder="不通过原因" v-model="examinejson.resion">
          </a-textarea>
        </a-form-model-item>

      </a-form-model>
    </a-modal>

  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {vpnjobhunterGetList,asyncvpndistributionGet,asyncvpndistributionUpdate} from '@/services/member'
  export default {
    data() {
      return {
        tag:'1',
        jobhunterjson:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpnjobhunterGet:{
            phone:'',
            name:''
          }

        },
        distributionjson:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpndistributionGet:{
            phone:'',
            name:''
          }

        },
        rules: {
          resion:[{ required: true, message: '请输入不通过原因',trigger: 'blur'}],
        },
        examinejson:{
          id:0,
          name:'',
          state:2,
          resion:''
        },
        loading:false,
        Datalist:[],
        columns:[
          {
            title: '姓名',
            dataIndex: 'name',
          },{
            title: '联系电话',
            dataIndex: 'phone',
          },{
            title: '身份证',
            dataIndex: '',
          },{
            title: '申请时间',
            dataIndex: 'applyTime',
          },{
            title: '操作',
            key: 'action',
            width:60,
            scopedSlots: { customRender: 'action' }
          }
        ],
        distributioncolumns:[
          {
            title: '姓名',
            dataIndex: 'name',
          },{
            title: '联系电话',
            dataIndex: 'phone',
          },{
            title: '注册时间',
            dataIndex: 'createTime',
          }
        ],
        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        distributionpagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        confirmLoading:false,
        visible:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.examine==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.findList()
    },
    methods: {
      callback(key) {
        this.tag=key
        if(this.tag=='1'){
          this.Search()
        }else if(this.tag=='2'){
          this.SearchDistribution()
        }

      },
      findList(){
        this.loading=true
        if(this.tag=='1'){
          asyncvpndistributionGet(this.jobhunterjson).then(result => {
            if(result.data.code==1){
              this.Datalist=result.data.data.content
              this.pagination.total=result.data.data.totalElements
            }else{
              this.$message.error(result.data.msg)
            }
            this.loading=false
          })
        }else if(this.tag=='2'){
          vpnjobhunterGetList(this.distributionjson).then(result => {
            if(result.data.code==1){
              this.Datalist=result.data.data.content
              this.pagination.total=result.data.data.totalElements
            }else{
              this.$message.error(result.data.msg)
            }
            this.loading=false
          })
        }

      },
      handleTableChange(pagination) {
        this.jobhunterjson.svcCaller.page = pagination.current
        this.findList()
      },
      Search(){
        this.pagination.total=0
        this.jobhunterjson.svcCaller.page=1
        this.findList()
      },
      distributionChange(pagination){
        this.distributionjson.svcCaller.page = pagination.current
        this.findList()
      },
      SearchDistribution(){
        this.distributionpagination.total=0
        this.distributionjson.svcCaller.page=1
        this.findList()
      },
      onSubmitexamine(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading=true
            asyncvpndistributionUpdate({vpndistributionUpdate:this.examinejson}).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.findList()
                this.visible=false
                this.examinejson={
                  id:0,
                  name:'',
                  state:2,
                  resion:''
                }
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
      Cancelexamine(){
        this.visible=false
      },
      SetState(id,name){

        this.examinejson.name=name
        this.examinejson.id=id
        this.visible=true

      },
    }
  }
</script>
<style lang="less">

</style>
