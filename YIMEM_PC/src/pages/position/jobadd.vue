<template>
  <a-card>
    <a-form-model ref="ruleForm" :model="json" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">
      <a-form-model-item label="工作性质">
        <a-radio-group v-model="json.isAllDay">
          <a-radio :value="1">全职</a-radio>
          <a-radio :value="0">兼职</a-radio>
        </a-radio-group>

        <a-checkbox v-model="json.isgraduate">
          可接受应届生
        </a-checkbox>
      </a-form-model-item>

      <a-form-model-item label="公司名称" prop="companyId" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select show-search v-model="json.companyId" placeholder="请选择公司名称" :default-active-first-option="false" :filter-option="false" :not-found-content="null" @search="handleSearch">
          <a-select-option v-for="list in companyList" :key="list.companyId">
            {{ list.name }}
          </a-select-option>
        </a-select>
      </a-form-model-item>

      <a-form-model-item label="是否显示公司名称" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-radio-group name="radioGroup" v-model="json.showcompany">
          <a-radio :value="1">是</a-radio>
          <a-radio :value="0">否</a-radio>
        </a-radio-group>
      </a-form-model-item>

      <a-form-model-item label="职位名称" prop="name" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-input autocomplete="autocomplete" placeholder="请输入职位名称，如销售" v-model="json.name">
        </a-input>
      </a-form-model-item>
      <a-form-model-item :label="json.isAllDay==1?'职位类别':'兼职类别'" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }" prop="jobCateId">
        <a-tree-select v-model="json.jobCateId" style="width: 100%" :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }" :default-active-first-option="false" :tree-data="CategoryJobsList" placeholder="选择职位类别" tree-default-expand-all />
      </a-form-model-item>

      <div v-if="json.isAllDay==1">
        <a-form-model-item label="企业收费" prop="money" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-input autocomplete="autocomplete" placeholder="请输入企业收费金额" v-model="json.money" suffix="元/人">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="佣金推荐" prop="brokerage" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-input autocomplete="autocomplete" placeholder="请输入佣金推荐" v-model="json.brokerage" suffix="元/人">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="佣金规则" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-textarea autocomplete="autocomplete" placeholder="请详细描述职位推荐佣金规则" rows="4" v-model="json.rule">
          </a-textarea>
        </a-form-model-item>
        <a-form-model-item label="工作城市" prop="provinceId" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-form-model-item prop="provinceId" style="width:calc(50% - 5px);float:left;margin-bottom:0px;">
            <a-select placeholder="请选择省" :default-active-first-option="false" v-model="json.provinceId" @change="findCityList">
              <a-select-option v-for="list in ProvinceList" :key="list.provinceCode">
                {{ list.zhLabel }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item prop="cityId" style="width:calc(50% - 5px);margin-left:10px;float:left;margin-bottom:0px;">
            <a-select placeholder="请选择城市" :default-active-first-option="false" v-model="json.cityId">
              <a-select-option v-for="list in CityList" :key="list.uuid">
                {{ list.zhLabel }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
        </a-form-model-item>
        <a-form-model-item label="综合薪资" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }" prop="lowSalary">
          <a-form-model-item prop="lowSalary" :style="{ display: 'inline-block', width: 'calc(50% - 12px)','margin-bottom':'0px' }">
          <a-input autocomplete="autocomplete" placeholder="最低综合薪资(必填)" v-model="json.lowSalary">
          </a-input>
          </a-form-model-item>
          <span :style="{ display: 'inline-block', width: '24px', textAlign: 'center' }">-</span>
          <a-form-model-item prop="higSalary" :style="{ display: 'inline-block', width: 'calc(50% - 12px)','margin-bottom':'0px' }">
          <a-input autocomplete="autocomplete" placeholder="最高综合薪资(必填)" v-model="json.higSalary">
          </a-input>
          </a-form-model-item>
        </a-form-model-item>
        <a-form-model-item label="招聘人数" prop="peopleNum" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-input autocomplete="autocomplete" placeholder="招聘人数" v-model="json.peopleNum" suffix="人">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="员工福利">
        <a-checkbox-group v-model="json.jobLightspotIds">
          <a-checkbox  v-for="i in jobLightspotList" :value="i.jobLightenId" :key="i.jobLightenId">{{i.name}}</a-checkbox>
        </a-checkbox-group>
      </a-form-model-item>
      </div>
      <div v-if="json.isAllDay==0">
        <a-form-model-item label="招聘人数" prop="peopleNum" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-input autocomplete="autocomplete" placeholder="招聘人数" v-model="json.peopleNum">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="兼职时效" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-radio-group name="radioGroup" v-model="json.timeliness">
            <a-radio value="短期">短期</a-radio>
            <a-radio value="长期">长期</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="兼职日期">
          <a-date-picker v-model="json.startTime" show-time type="date" placeholder="开始时间"/>
          -
          <a-date-picker v-model="json.endTime" show-time type="date" placeholder="结束时间"/>
        </a-form-model-item>
        <a-form-model-item label="薪资水平" prop="partTimeSalary" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-input autocomplete="autocomplete" placeholder="请输入薪资水平" v-model="json.partTimeSalary" suffix="元/天">
          </a-input>
        </a-form-model-item>
        <a-form-model-item label="结算方式" prop="payments" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
          <a-select v-model="json.payments" placeholder="请选择结算方式" :default-active-first-option="false">
            <a-select-option value="天">天</a-select-option>
            <a-select-option value="月">月</a-select-option>
            <a-select-option value="季">季</a-select-option>
            <a-select-option value="年">年</a-select-option>
          </a-select>
        </a-form-model-item>

      </div>

      <a-divider />
      <a-form-model-item label="学历要求" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select placeholder="学历不限" :default-active-first-option="false" v-model="json.educationExp">
          <a-select-option v-for="list in educationList" :key="list.cId">
            {{ list.cName }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="工作经验" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select placeholder="工作经验不限" :default-active-first-option="false" v-model="json.jobExp">
          <a-select-option v-for="list in experienceList" :key="list.cId">
            {{ list.cName }}
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="性别要求" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }">
        <a-select placeholder="性别不限" :default-active-first-option="false" v-model="json.jobSex">
          <a-select-option key="1">
            男
          </a-select-option>
          <a-select-option key="0">
          女
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="年龄要求" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }" prop="startAge">
        <a-form-model-item prop="startAge" :style="{ display: 'inline-block', width: 'calc(50% - 12px)','margin-bottom':'0px' }">
        <a-input autocomplete="autocomplete" placeholder="最低年龄" v-model="json.startAge">
        </a-input>
        </a-form-model-item>
        <span :style="{ display: 'inline-block', width: '24px', textAlign: 'center' }">-</span>
        <a-form-model-item prop="endAge" :style="{ display: 'inline-block', width: 'calc(50% - 12px)','margin-bottom':'0px' }">
        <a-input autocomplete="autocomplete" placeholder="最高年龄" v-model="json.endAge">
        </a-input>
        </a-form-model-item>
      </a-form-model-item>
      <a-form-model-item label="职位描述" :label-col="{ span: 4 }" :wrapper-col="{ span: 10 }" extra="建议分调描述岗位的工作职现，以及详情的任职，请勿在描述中留下电话，邮箱等">
        <a-textarea autocomplete="autocomplete" placeholder="备注" rows="4" v-model="json.jobContent">
        </a-textarea>
      </a-form-model-item>
      <a-form-model-item class="btn">
        <a-button :loading="loading" type="primary" @click="onSubmit">提交</a-button>
      </a-form-model-item>
    </a-form-model>
  </a-card>
</template>

<script>
  import {validatorNumber,validatorSNumber} from '@/utils/validata'
  import {getProvinceListAll,getCityListAll,asyncvpnqsCategoryGet} from '@/services/user'
  import {asyncvpnjobAdd,asynvpnjobDetail,asyncvpnqsCategoryJobsGet,vpnjobLightenEnumGetAllList} from '@/services/job'
  import {getvpncompanyGetList} from '@/services/member'
  export default {
    components: {},
    data() {
      return {
        json:{
          jobId:this.$route.query.jobId,
          isAllDay:1,
          name:"",
          companyId:undefined,
          showcompany:1,
          isgraduate:true,
          jobCateId:undefined,
          money:'',
          brokerage:'',
          rule:'',
          provinceId:undefined,
          cityId:undefined,
          lowSalary:'',
          higSalary:'',
          peopleNum:'',
          jobLightspotIds:[],
          timeliness:'长期',
          startTime:'',
          endTime:'',
          partTimeSalary:'',
          payments:undefined,
          educationExp:undefined,
          jobExp:undefined,
          jobSex:undefined,
          startAge:'',
          endAge:'',
          jobContent:''
        },
        rules: {
          name: [{ required: true, message: '请输入职位名称',trigger: 'blur'}],
          companyId: [{ required: true, message: '请选择所属公司',trigger: 'blur'}],
          jobCateId: [{ required: true, message: '请选择职位类别',trigger: 'blur'}],
          money: [{ required: true, validator:validatorNumber,emptymsg: '请输入企业收费',trigger: 'blur'}],
          brokerage: [{ required: true, validator:validatorNumber,emptymsg: '请输入佣金推荐',trigger: 'blur'}],
          provinceId: [{ required: true, message: '请选择省',trigger: 'blur'}],
          cityId: [{ required: true, message: '请选择市',trigger: 'blur'}],
          lowSalary: [{ required: true, validator:validatorSNumber,emptymsg: '请输入最低综合薪资',trigger: 'blur'}],
          higSalary: [{ required: true, validator:validatorSNumber,emptymsg: '请输入最高综合薪资',trigger: 'blur'}],
          peopleNum: [{ required: true, validator:validatorNumber,emptymsg: '请输入招聘人数',trigger: 'blur'}],
          startAge: [{ required: true, validator:validatorNumber,emptymsg: '请输入最低年龄要求',trigger: 'blur'}],
          endAge: [{ required: true, validator:validatorNumber,emptymsg: '请输入最高年龄要求',trigger: 'blur'}],
          partTimeSalary:[{ required: true, message: '请输入薪资水平',trigger: 'blur'}],
          payments:[{ required: true, message: '请选择结算方式',trigger: 'blur'}],
        },
        CategoryJobsList:[],
        ProvinceList:[],
        CityList:[],
        companyList:[],
        jobLightspotList:[],
        educationList:[],
        experienceList:[],
        loading:false
      }
    },
    created() {
      this.findProvinceList()
      this.getCategoryJobs()
      this.findjobLightspot()
      this.findeducationList()
      this.findexperienceList()
      this.findcompanyList()
      if(this.json.jobId != null){
        this.getData()
      }
    },
    methods: {
      getData(){
        asynvpnjobDetail(this.json.jobId).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.json=result.data.data.vpnjobGetResp
            this.json.isAllDay=parseInt(this.json.isAllDay)
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      getCategoryJobs() {
        asyncvpnqsCategoryJobsGet().then(result => {
          if(result.data.code==1){
            this.CategoryJobsList=result.data.data.vpnqsCategoryJobsGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      findjobLightspot(){
        vpnjobLightenEnumGetAllList().then(result => {
          if(result.data.code==1){
            this.jobLightspotList=result.data.data.vpnjobLightenEnumGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      findcompanyList(name){
        const json={
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpncompanyGet:{
            name:name==undefined?'':name
          }
        }
        getvpncompanyGetList(json).then(result => {
          if(result.data.code==1){
            this.companyList=result.data.data.vpncompanyGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })

      },
      handleSearch(value) {
        this.findcompanyList(value)
      },
      findProvinceList(){
        getProvinceListAll().then(result => {
          this.loading = false
          if(result.data.code==1){
            this.ProvinceList=result.data.data.vpnRmProvinceGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      findCityList(){
        this.json.cityId=undefined
        if(this.json.provinceId != undefined && this.json.provinceId>0){
          getCityListAll(this.json.provinceId).then(result => {
            this.loading = false
            if(result.data.code==1){
              this.CityList=result.data.data.vpnRmCityGetResp
            }else{
              this.$message.error(result.data.msg)
            }
          })
        }
      },
      findeducationList(){
        asyncvpnqsCategoryGet('QS_education').then(result => {
          if(result.data.code==1){
            this.educationList=result.data.data.vpnqsCategoryGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      findexperienceList(){
        asyncvpnqsCategoryGet('QS_experience').then(result => {
          if(result.data.code==1){
            this.experienceList=result.data.data.vpnqsCategoryGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      onSubmit(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            let requests=''
            if(this.json.jobId){
              requests={vpnjoblUpdate:this.json}
            }else{
              requests={vpnjobAdd:this.json}
            }
            this.loading=true
            asyncvpnjobAdd(this.json.jobId?'edit':'add',requests).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.$router.push({path:'/job/list'})
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
</style>
