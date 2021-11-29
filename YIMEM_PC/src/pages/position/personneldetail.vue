<template>
  <a-card>
    <div class="info">
      <img :src="json.headImg" class="headimg"/>
      <h4>{{json.userName}}</h4>
      <p>{{json.userSex=='1'?'男':'女'}} | {{json.birthday}} | {{json.workage}}年工作经验 | 现居{{json.provinceName}}{{json.cityName}}</p>
      <p>毕业学校：{{json.school}}</p>
      <p>
        <span><a-icon type="mobile" /> {{json.phoneNum}}</span>
        <span style="margin-left:20px;"><a-icon type="mail" /> {{json.emil}}</span>
      </p>
    </div>
    <a-divider />
    <div class="main-box">
      <h4>求职意向</h4>
      <div class="li-box" v-for="(list,index) in json.jobWanteds" :key="'jobWanteds_'+index">
        <p>期望职位：{{list.jobCateName}}</p>
        <p>期望地址：{{list.cityName}}</p>
        <p>期望月薪：{{list.startMoney}}-{{list.endMoney}}</p>
        <p>工作性质：{{list.isAllDay==1?'全职':'兼职'}}</p>
        <p>工作状态：{{list.propertyName}}</p>
      </div>

      <h4>工作经历</h4>
      <div class="li-box" v-for="(list,index) in json.jobExps" :key="'jobExps_'+index">
        <p class="title">{{list.company}}</p>
        <p><span>部门：{{list.department}}</span><span style="margin-left:20px">岗位：{{list.position}}</span><span style="margin-left:20px">薪资：{{list.salary}}元/月</span></p>
        <p>{{list.remark}}</p>
        <span class="time">{{list.startTime}}-{{list.endTime}}</span>
      </div>

      <h4>教育经历</h4>
      <div class="li-box" v-for="(list,index) in json.educationExps" :key="'educationExps_'+index">
        <p class="title">{{list.school}}</p>
        <p>{{list.majorName}} / {{list.educationName}}</p>
        <span class="time">{{list.startTime}}-{{list.endTime}}</span>
      </div>

      <h4>项目经验</h4>
      <div class="li-box" v-for="(list,index) in json.projectExps" :key="'projectExps_'+index">
        <p class="title">{{list.title}}</p>
        <p>{{list.remark}}</p>
      </div>

      <h4>语言能力</h4>
      <div class="li-box" v-for="(list,index) in json.languages" :key="'languages_'+index">
        <p class="title">{{list.languageName}}</p>
        <p>{{list.speakAbilityName}} | {{list.writeAbilityName}}</p>
      </div>

      <h4>获得证书</h4>
      <div class="li-box" v-for="(list,index) in json.certificates" :key="'certificates_'+index">
        <p>证书名称：{{list.name}}</p>
      </div>

      <h4>自我描述</h4>
      <div class="li-box">
        <p>{{json.remark}}</p>
      </div>

    </div>
  </a-card>
</template>

<script>
  import {asyncvpnuserGet} from '@/services/job'
  export default {
    components: {},
    data() {
      return {
        userId:this.$route.query.userId,
        json:{

        },
      }
    },
    created() {
      this.getData()
    },
    methods: {
      getData(){
        asyncvpnuserGet(this.userId).then(result => {
          if(result.data.code==1){
            this.json=result.data.data.vpnuserGetResp
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
    }
  }
</script>
<style lang="less">
.info{height:auto;position:relative;padding-left:100px;
.headimg{width:60px;height:60px;border-radius:100px;position:absolute;left:0px;top:0px;z-index:99;border:1px solid #ddd;}
  h4{color:#1890ff;height:auto;margin-bottom:10px;font-weight:700;font-size:16px;}
  p{line-height:20px;color:#999;padding:5px 0px;margin:0px;}
}
  .main-box{height:auto;
  h4{height:50px;line-height:50px;font-size:16px;font-weight:700;position:relative;padding-left:16px;}
    h4:after{content:'';width:4px;height:18px;background:#1890ff;position:absolute;left:0px;top:16px;z-index:9}
    .li-box{height:auto;padding:10px 0px;position:relative;
    p{margin:0px;line-height:20px;padding:5px 0px;}
      .title{font-weight:700;margin-bottom:10px;color:#333;font-size:16px;}
      .time{position:absolute;right:0px;top:14px;z-index:9;color:#aaa;}
    }
  }
</style>
