<template>
  <a-card>
    <a-form-model ref="ruleForm" :model="json" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">

      <a-form-model-item label="公司名称" prop="name" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入公司名称" v-model="json.name">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="联系人" prop="name" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入联系人" v-model="json.userName">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="联系人职位" prop="job" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select v-model="json.job" placeholder="请选择联系人职位" :default-active-first-option="false" :filter-option="false" :not-found-content="null">
          <a-select-option v-for="list in jobList" :key="list.cId">
            {{ list.cName }}
          </a-select-option>
        </a-select>

      </a-form-model-item>
      <a-form-model-item label="联系电话" prop="phone" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入联系电话" v-model="json.phone">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="所属行业" prop="businessId" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select v-model="json.businessId" placeholder="请选择所属行业" :default-active-first-option="false" :filter-option="false" :not-found-content="null">
          <a-select-option v-for="list in businessList" :key="list.cId">
            {{ list.cName }}
          </a-select-option>
        </a-select>

      </a-form-model-item>
      <a-form-model-item label="公司规模" prop="scale" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入公司规模" v-model="json.scale"  suffix="人">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="公司地址" prop="adress" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入公司地址" v-model="json.adress">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="营业执照" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-upload name="file" list-type="picture-card" class="upload" :show-upload-list="false" :action="imgposturl" :before-upload="beforeUpload" @change="uploadhandleChange">
          <div class="headimg" v-if="json.licenseUrl"><img :src="json.licenseUrl" alt="头像" width="100" /></div>
          <div v-else>
            <a-icon :type="loading ? 'loading' : 'plus'" />
            <div class="ant-upload-text">上传营业执照</div>
          </div>
        </a-upload>
      </a-form-model-item>

      <a-form-model-item class="btn">
        <a-button :loading="loading" type="primary" @click="onSubmit">提交</a-button>
      </a-form-model-item>
    </a-form-model>
  </a-card>
</template>

<script>
  import {imgposturl} from '@/services/api'
  import {validatorPhone,validatorNumber} from '@/utils/validata'
  import {asyncvpnqsCategoryGet} from '@/services/user'
  import {asyncvpncompanyAdd} from '@/services/member'
  export default {
    components: {},
    data() {
      return {
        imgposturl:imgposturl,
        json:{
          name:'',
          userName:'',
          job:undefined,
          phone:'',
          businessId:undefined,
          scale:'',
          adress:'',
          licenseUrl:'',
        },
        rules: {
          name: [{ required: true, message: '请输入企业名称',trigger: 'blur'}],
          userName: [{ required: true, message: '请输入联系人',trigger: 'blur'}],
          job: [{ required: true, message: '请选择联系人职位',trigger: 'blur'}],
          phone: [{ required: true, validator: validatorPhone,emptymsg:'请输入联系电话',trigger: 'blur'}],
          businessId: [{ required: true, message: '请选择所属行业',trigger: 'blur'}],
          scale: [{ required: true, validator: validatorNumber,emptymsg: '请输入公司规模',trigger: 'blur'}],
          adress: [{ required: true, message: '请输入公司地址',trigger: 'blur'}]
        },
        businessList:[],
        jobList:[],
        loading:false
      }
    },
    created() {
      this.findjobList()
      this.findbusinessList()

    },
    methods: {
      findjobList(){
        asyncvpnqsCategoryGet('QS_station').then(result => {
          if(result.data.code==1){
            this.jobList=result.data.data.vpnqsCategoryGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      findbusinessList(){
        asyncvpnqsCategoryGet('QS_trade').then(result => {
          if(result.data.code==1){
            this.businessList=result.data.data.vpnqsCategoryGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.loading=true
            asyncvpncompanyAdd({vpncompanyAdd:this.json}).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.$router.push({path:'/member/qy/list'})
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
      uploadhandleChange(info) {
        if (info.file.status == 'done') {
          const response=info.file.response

          if(response.success){

            this.$message.success(response.msg)
            this.json.licenseUrl=response.data.imageUrl

          }else{
            this.$message.success(response.msg)
          }

        } else if (info.file.status == 'error') {
          this.$message.error('上传失败')
        }

      },
      beforeUpload(file) {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
          this.$message.error('图片格式不正确');
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('图片最大不可超过2M');
        }
        return isJpgOrPng && isLt2M;
      },

    }
  }
</script>
<style lang="less">
  .ant-upload.ant-upload-select-picture-card{width:200px;height:150px;
    .ant-upload{color:#ccc;
      .anticon-plus{font-size:36px;color:#ccc;}
      .ant-upload-text{margin-top:10px;}
    }

  }
  .btn{text-align:center;}
</style>
