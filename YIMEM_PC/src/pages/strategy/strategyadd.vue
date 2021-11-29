<template>
  <a-card>
    <a-form-model ref="ruleForm" :model="json" :rules="rules" :label-col="{ span: 3 }" :wrapper-col="{ span: 16 }">
      <a-form-model-item label="文章标题" prop="title">
        <a-input autocomplete="autocomplete" placeholder="请输入文章标题" v-model="json.title">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="文章副标题" prop="introduction">
        <a-input autocomplete="autocomplete" placeholder="请输入文章副标题" v-model="json.introduction">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="攻略图标"  required>
        <a-upload name="file" :show-upload-list="false" :action="imgposturl" @change="handleChange" style="float:left;margin-left:10px;padding-top:0px;">
          <div class="upBox">
            <img class="imgOf" v-if="json.image" :src="json.image" alt="avatar" />
            <div v-else>
              <a-icon :type="loadingimg ? 'loading' : 'plus'" />
              <div class="ant-upload-text">
                上传攻略图
              </div>
            </div>
          </div>
        </a-upload>
      </a-form-model-item>

      <a-form-model-item label="文章内容" prop="phone">
        <Editor :catchData="json.content" @geteditorcontent="geteditorcontent"/>
      </a-form-model-item>
      <a-form-model-item label="发布设置">
        <a-radio-group name="radioGroup" v-model="json.state">
          <a-radio :value="1">立即发布</a-radio>
          <a-radio :value="2">定时发布</a-radio>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="发布时间" prop="useTime" v-if="json.state==2">
        <a-date-picker v-model="json.useTime" show-time type="date" placeholder="选择发布时间" style="width: 100%;"/>
      </a-form-model-item>
      <a-form-model-item class="btn">
        <a-button :loading="loading" type="primary" @click="onSubmit">提交</a-button>
      </a-form-model-item>
    </a-form-model>
  </a-card>
</template>

<script>
  import Editor from '@/components/wangEnduit'
  import {asyncvpnstrategyAddsvc,asyncvpnstrategyDetail} from '@/services/strategy'
  import {imgposturl} from '@/services/api'

  import moment from "moment";
  export default {
    components: {Editor},
    data() {
      return {
        imgposturl,
        json:{
          stId:this.$route.query.stId,
          toUser:this.$route.query.toUser,
          title:"",
          content:"",
          state:1,
          useTime:''
        },
        rules: {
          image:[{ required: true, message: '请上传图片',trigger:'change'}],
          title: [{ required: true, message: '请输入文章标题',trigger: 'blur'}],
          introduction:[{ required: true, message: '请输入文章标题',trigger: 'blur'}],
          useTime: [{ required: true, message: '请选择定时发布时间', trigger: 'blur'}]
        },
        isClear:true,
        loading:false,
        loadingimg:false
      }
    },
    created() {
      if(this.json.stId != null){
        this.getData()
      }
    },
    methods: {
      handleChange(info) {
        this.loadingimg=true;
        if (info.file.status == 'done') {
          this.loadingimg=false;
          const response=info.file.response
          if(response.success){
            this.$message.success(response.msg);
            this.json.image=response.data.imageUrl
            console.log(this.json.iconUrl)
          }else{
            this.$message.success(response.msg)
          }
        } else if (info.file.status == 'error') {
          this.$message.error('上传失败')
        }
      },
      getData(){
        asyncvpnstrategyDetail(this.json.stId).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.json=result.data.data.vpnstrategyGet
            this.json.state=parseInt(this.json.state)
            this.json.useTime=this.json.useTime==null?'':moment(this.json.useTime,'HH:mm:ss')
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      geteditorcontent(val){
        this.json.content=val
      },
      onSubmit(){
        if (!this.json.image){
          this.$message.warning("请上传攻略图标")
        }
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            if(this.json.stId){
              requests={vpnstrategyUpdate:this.json}
            }else{
              requests={vpnstrategyAdd:this.json}
            }
            this.loading=true
            asyncvpnstrategyAddsvc(this.json.stId?'edit':'add',requests).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.$router.push({path:'/strategy/list',query: {toUser:this.json.toUser}})
              }else{
                this.$message.error(result.data.msg)
              }
              this.loading=false
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
  .btn{text-align:center;}
  .ant-calendar-picker-container{z-index:999999}
  .upBox{
    width: 100px;
    height: 100px;
    border: 1px solid #d9d9d9;
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
  }
  .imgOf{
    max-height: 100px;
    max-width: 100px;
  }
</style>
