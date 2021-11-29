<template>
  <a-modal centered :title="currentDepInfo==''?'新增部门':'编辑部门'" :visible="depvisible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
    <a-form-model ref="ruleForm" @submit="onSubmit" :model="json" :rules="rules" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="部门名称" prop="name">
        <a-input autocomplete="autocomplete" placeholder="请输入部门名称" v-model="json.name">
        </a-input>
      </a-form-model-item>

      <a-form-model-item label="上级部门" prop="fDepId">
        <a-tree-select v-model="value" style="width: 100%" :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }" :default-active-first-option="false" :tree-data="deplist" placeholder="请选择上级部门" tree-default-expand-all />
      </a-form-model-item>
      <a-form-model-item label="部门负责人" prop="manager.id">
        <a-select show-search v-model="json.manager.id" placeholder="请选择部门负责人" :default-active-first-option="false" :filter-option="false" :not-found-content="null" @search="handleSearch" @change="handleChange">
          <a-select-option v-for="list in ulist" :key="list.id">
            {{ list.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
    </a-form-model>
  </a-modal>
</template>

<script>
  import {DepartmentCreate,getUserlistAll} from '@/services/user'
  export default {
    name: 'depadd',
    props: ['deplist','depvisible','currentDepInfo'],
    data() {
      return {
        labelCol: { span: 5 },
        wrapperCol: { span: 14 },
        value:undefined,
        ulist:[],
        json:{},
        rules: {
          name: [{ required: true, message: '请输入部门名称', whitespace: true,trigger: 'blur'}],
          fDepId: [{ required: true, message: '请选择上级部门', trigger: 'blur'}],
          manager: {
            id:[{ required: true, message: '请选择部门负责人', trigger: 'blur'}]
          },
        },
        confirmLoading: false
      }
    },
    created() {
      this.getjson()
    },
    watch: {
      currentDepInfo() {

        if(this.currentDepInfo != ''){
          this.ulist=[{id:this.currentDepInfo.manager.id,name:this.currentDepInfo.manager.name}]
          this.json=this.currentDepInfo
          this.value=this.json.fDepId
        }else{
          this.ulist=[]
          this.getjson()
        }
      },
      value(value) {
        this.json.fDepId=value
      }
    },
    methods: {
      getjson(){
        this.json={
          name:'',
          manager: {
            id:undefined,
            name:''
          },
          fDepId:undefined
        }
      },
      handleSearch(value) {
        const json={
          svcCaller:{
            page:1,
            pageSize:10
          },
          depId:'',
          keyword:value
        }
        getUserlistAll(json).then(result => {
          if(result.data.code==1){
            this.ulist=result.data.data.content
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      handleChange(value) {
        this.json.manager.id=value
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading = true
            DepartmentCreate(this.json).then(result => {
              if(result.data.code==1){
                this.$emit('setdepmodal',true)
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
        this.$emit('setdepmodal',false)
      }
    }
  }
</script>

<style scoped lang="less">

</style>