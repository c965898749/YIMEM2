<template>
  <a-modal centered :title="currentuserInfo==''?'新增员工':'编辑员工'" :visible="uservisible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
    <a-form-model ref="ruleForm" @submit="onSubmit" :model="json" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="员工头像" class="headimgbox">

        <a-upload name="file" list-type="picture-card" class="avatar-uploader" :show-upload-list="false" :action="imgposturl" :before-upload="beforeUpload" @change="uploadhandleChange">
          <div class="headimg" v-if="json.headImgUrl"><img :src="json.headImgUrl" alt="头像" width="100" /></div>
          <div v-else>
            <a-icon :type="loading ? 'loading' : 'plus'" />
            <div class="ant-upload-text">
              上传头像
            </div>
          </div>
        </a-upload>

      </a-form-model-item>
      <a-form-model-item label="员工姓名" prop="name">
        <a-input autocomplete="autocomplete" placeholder="请输入员工姓名" v-model="json.name">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="联系电话" prop="phone">
        <a-input autocomplete="autocomplete" placeholder="请输入联系电话" v-model="json.phone">
        </a-input>
      </a-form-model-item>

      <a-form-model-item label="所属部门" prop="deps.depId">
        <a-tree-select v-model="value" style="width: 100%" :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }" :default-active-first-option="false" :tree-data="deplist" placeholder="请选择所属部门" tree-default-expand-all />
      </a-form-model-item>

      <a-form-model-item label="岗位角色" prop="roles.id">
        <a-select placeholder="请选择岗位角色" :default-active-first-option="false" @change="handleChange" v-model="json.roles.id">
            <a-select-option v-for="list in rolelist" :key="list.id">
              {{ list.name }}
            </a-select-option>
        </a-select>

      </a-form-model-item>

      <a-form-model-item label="账号" prop="account">
        <a-input autocomplete="autocomplete" placeholder="请输入账号" v-model="json.account">
        </a-input>
      </a-form-model-item>
      <a-form-model-item label="密码">
        <a-input autocomplete="autocomplete" placeholder="不填写默认为初始密码1234" v-model="json.password">
        </a-input>
      </a-form-model-item>
    </a-form-model>
  </a-modal>
</template>

<script>
  import {validatorPhone} from '@/utils/validata'
  import {imgposturl} from '@/services/api'
  import {UserCreate,asyncRoleSearch} from '@/services/user'
  export default {
    name: 'useradd',
    props: ['deplist','uservisible','currentuserInfo'],
    data() {
      return {
        imgposturl:imgposturl,
        labelCol: { span: 4 },
        wrapperCol: { span: 14 },
        rolelist:[],
        value:undefined,
        json:{},
        rules: {
          name: [{ required: true, message: '请输入角色名称', trigger: 'blur'}],
          phone: [{ required: true, validator: validatorPhone,emptymsg: '请输入联系电话', trigger: 'blur' }],
          deps:{
            depId:[{ required: true, message: '请选择所属部门', trigger: 'blur'}]
          },
          roles:{
            id:[{ required: true, message: '请选择岗位角色', trigger: 'blur'}]
          },
          account: [{ required: true, message: '请输入账号', trigger: 'blur'}]
        },
        confirmLoading: false
      }
    },
    created() {
      this.getjson()
      this.getRuleList()
    },
    watch: {
      currentuserInfo() {
        if(this.currentuserInfo != ''){
          this.json=this.currentuserInfo
          this.value=this.json.deps.depId
        }else{
          this.getjson()
        }
        console.log(this.json)
      },
      value(value) {
        this.json.deps.depId=value
      }
    },
    methods: {
      getjson(){
        this.json={
          headImgUrl:'',
          name:'',
          phone:'',
          deps: {depId:undefined},
          roles: {id:undefined},
          account:'',
          password:'',
          state:1
        }
      },
      getRuleList() {
        this.loading = true
        const json={svcCaller:{
            page:1,
            pageSize:10
          },
          keyword:""}
        asyncRoleSearch(json).then(result => {
          this.Datalist=[]
          if(result.data.code==1){
            this.rolelist=result.data.data.content
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading = false
        })
      },
      handleChange(value) {
        this.json.roles.id=value
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading = true
            UserCreate(this.json).then(result => {
              if(result.data.code==1){
                this.$emit('setusermodal',true)
              }else{
                this.$message.error(result.data.msg)
              }
              this.confirmLoading = false
            })
          } else {
            return false;
          }
        })
      },
      Cancel(){
        this.$emit('setusermodal',false)
      },
      uploadhandleChange(info) {
        if (info.file.status == 'done') {
          const response=info.file.response

          if(response.success){

            this.$message.success(response.msg)
            this.json.headImgUrl=response.data.imageUrl

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

<style scoped lang="less">
.headimgbox{height:110px}
</style>