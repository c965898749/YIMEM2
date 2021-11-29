<template>
  <a-card>
    <a-form-model ref="ruleForm" :model="json" :rules="rules" :label-col="{ span: 3 }" :wrapper-col="{ span: 16 }">
      <a-form-model-item label="文章标题" prop="title">
        <a-input autocomplete="autocomplete" placeholder="请输入文章标题" v-model="json.title">
        </a-input>
      </a-form-model-item>

      <a-form-model-item label="文章内容" prop="phone">
        <Editor :catchData="json.content" @geteditorcontent="geteditorcontent"/>
      </a-form-model-item>
      <a-form-model-item label="发布设置">
        <a-radio-group name="radioGroup" v-model="json.state">
          <a-radio :value="0">停用</a-radio>
          <a-radio :value="1">立即发布</a-radio>
          <a-radio :value="2">定时发送</a-radio>
        </a-radio-group>
      </a-form-model-item>
      <a-form-model-item label="发布时间" prop="useTime">
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
  import {asyncwithdrawRuleAdd,asyncwithdrawRuleDetail} from '@/services/dashboard'
  import moment from "moment";
  export default {
    components: {Editor},
    data() {
      return {
        json:{
          wiId:this.$route.query.wiId,
          toUser:1,
          title:"",
          content:"",
          state:1,
          useTime:''
        },
        rules: {
          title: [{ required: true, message: '请输入文章标题',trigger: 'blur'}],
          useTime: [{ required: true, message: '请选择定时发送时间', trigger: 'blur'}]
        },
        isClear:true,
        loading:false
      }
    },
    created() {
      if(this.json.wiId != null){
        this.getData()
      }
    },
    methods: {
      getData(){
        asyncwithdrawRuleDetail(this.json.wiId).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.json=result.data.data.vpnwithdrawRuleGetResp
            this.json.state=parseInt(this.json.state)
            this.json.useTime=moment(this.json.useTime,'HH:mm:ss')
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      geteditorcontent(val){
        this.json.content=val
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            if(this.json.wiId){
              requests={vpnwithdrawRuleUpdate:this.json}
            }else{
              requests={vpnwithdrawRuleAdd:this.json}
            }
            this.loading=true
            asyncwithdrawRuleAdd(this.json.wiId?'edit':'add',requests).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.$router.push({path:'/dashboard/withdrawalrule/list'})
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
</style>
