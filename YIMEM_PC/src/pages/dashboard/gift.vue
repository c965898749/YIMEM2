<template>
  <a-card>
    <div class="head_action" v-if="button.add">
      <a-button @click="Add" class="Add" type="primary">新增礼物</a-button>
    </div>
    <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.id" @change="handleTableChange">
      <img  style="width:60px;heigth:60px"  slot="img" slot-scope="text, record" :src=record.img :onerror="errorImg"/>
      <span slot="action" slot-scope="text, record">
        <a-divider type="vertical" v-if="record.state==1 && button.edit"/>
        <a @click="Edit(record)" v-if="button.edit">编辑</a>
        <a-divider type="vertical" v-if="button.edit"/>
        <a @click="Del(record.id)" v-if="button.del">删除</a>
        </span>
    </a-table>

    <a-modal centered :title="currentInfo==''?'新增礼物':'编辑礼物'" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" @submit="onSubmit" :model="currentInfo" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 14 }">
        <a-form-model-item label="礼物图片" prop="img">
          <div class="img" v-if="currentInfo.img != ''"><img :src="currentInfo.img"  /><a-icon type="delete" @click="delpic()"></a-icon></div>

          <a-upload name="file" :show-upload-list="false" :action="imgposturl" @change="handleChange" style="float:left;margin-left:10px;padding-top:0px;">
            <a-button class="uploadbtn"><a-icon type="upload" /> {{currentInfo.img==''?'上传图片':'重新上传'}} </a-button>
          </a-upload>

        </a-form-model-item>
        <a-form-model-item label="礼物名称" prop="name">
          <a-input autocomplete="autocomplete" placeholder="请输入礼物名称" v-model="currentInfo.name">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="所需积分" prop="point">
          <a-input autocomplete="autocomplete" placeholder="请输入所需积分数" v-model="currentInfo.point">
          </a-input>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-card>

</template>

<script>
  import {IsAction} from '@/utils/button'
  import {imgposturl} from '@/services/api'
  import {getGiftAll,asyncGiftAdd,asyncGiftDel} from '@/services/dashboard'

  export default {
    components: {},
    data () {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          }
        },
        imgposturl:'',
        visible:false,
        currentInfo: {},
        confirmLoading:false,
        rules: {
          img: [{ required: true, message: '请上传礼物图片'}],
          name: [{ required: true, message: '请输入礼物名称', trigger: 'blur'}],
          point: [{ required: true, message: '请输入礼物兑换所需积分', trigger: 'blur'}]
        },
        columns:[
          {
            title: '序号',
            dataIndex: 'id',
          },
          {
            title: '名称',
            dataIndex: 'name',
          },{
            title: '图片',
            dataIndex: 'img',
            scopedSlots: { customRender: 'img' }
          },
          {
            title: '所需积分',
            dataIndex: 'point',
          },{
            title: '操作',
            key: 'action',
            width:150,
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
        errorImg:'this.src="' + require('../../assets/img/undefined.png') + '"',
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.edit==undefined && this.button.del==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.imgposturl=imgposturl
      this.getEmptyInfo()
      this.getDataList()
    },
    methods: {
      getDataList() {
        this.loading = true
        getGiftAll(this.json).then(result => {
          this.Datalist=[]
          if(result.data.code==1){
            this.Datalist=result.data.data.content
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      getEmptyInfo(){
        this.currentInfo={
          name:'',
          img:'',
          point:'',
          state:1,
        }
      },
      Add(){
        this.getEmptyInfo()
        this.visible=true
      },
      Edit(list){
        this.currentInfo= {
          ...list
        }
        this.visible=true
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
      Del(id){
        const _self=this
        this.$confirm({
          title: '确认删除吗？',
          //content: '',
          onOk() {
           let requests ={
             vpnGiftAdd:{
                id:id
                }
            }
            asyncGiftDel(requests).then(result => {
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
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            if(this.currentInfo.id){
              requests={vpnGiftAdd:this.currentInfo}
            }else{
              requests={vpnGiftAdd:this.currentInfo}
            }
            asyncGiftAdd(this.currentInfo.id?2:1,requests).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.getDataList()
                this.visible=false
              }else{
                this.$message.error(result.data.msg)
              }
            })
          } else {
            return false;
          }
        })

      },
      Cancel(){
        this.visible=false
      },
      delpic(){
        this.currentInfo.img=''
      },
      handleChange(info) {
        if (info.file.status == 'done') {
          const response=info.file.response
          if(response.success){
            this.$message.success(response.msg)
            this.currentInfo.img=response.data.imageUrl
            console.log(this.currentInfo.img)
          }else{
            this.$message.success(response.msg)
          }
        } else if (info.file.status == 'error') {
          this.$message.error('上传失败')
        }
      }
    }
  }
</script>
<style lang="less">

.ant-form-item-children{
  .img{width:62px;height:62px;border-radius:4px;border:1px solid #ddd;position:relative;float:left;
    img{width:60px;height:60px;float:left}
    .anticon{width:30px;height:30px;display:inline-block;border-radius:40px;background:rgba(0,0,0,0.4);color:#fff;text-align:center;line-height:30px;position:absolute;left:15px;top:15px;z-index:9}

  }
  .uploadbtn{background:#1890ff;color:#fff;font-size:12px;}
}

</style>
