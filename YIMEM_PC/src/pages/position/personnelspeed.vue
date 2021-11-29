<template>
  <a-card>
    <div class="p_title">
      <label>{{json.userName}}</label>
      <a-button @click="Detail" type="primary">查看详情</a-button>
    </div>

    <a-descriptions>
      <a-descriptions-item label="姓名">
        {{json.userName}}
      </a-descriptions-item>
      <a-descriptions-item label="联系方式">
        {{json.phoneNum}}
      </a-descriptions-item>
      <a-descriptions-item label="基本信息">
        {{json.userSex=='1'?'男':'女'}}/{{json.age}}岁/{{json.education}}/{{json.workage}}年/{{json.cityName}}
      </a-descriptions-item>
      <a-descriptions-item label="应聘职位">
        {{json.provinceName}}{{json.jobName}}
      </a-descriptions-item>
      <a-descriptions-item label="应聘公司">
        {{json.companyName}}
      </a-descriptions-item>
      <a-descriptions-item label="期望薪资">
        {{json.startMoney}}-{{json.endMoney}}k
      </a-descriptions-item>
    </a-descriptions>

    <a-divider/>
    <div class="speedlist">
      <div class="li" v-for="(list,index) in speedList" :key="index">
        <div class="createTime">{{list.createTime}}</div>
        <div class="stateName">{{stateName[list.state]}}<span v-if="list.flag>0" :class="'flag'+list.flag">{{flagName[list.flag]}}</span>
        </div>
        <div class="info">
          <p v-if="list.time !='' && list.time != null">面试时间：{{list.time}}</p>
          <p v-if="list.address !='' && list.address != null">面试地址：{{list.address}}</p>
          <p v-if="list.remark !='' && list.remark != null">面试备注：{{list.remark}}</p>
        </div>

      </div>

    </div>

    <div class="action" v-if="speedList.length>0">
      <a-button @click="DeliverDetailAdd(0)" type="primary" v-if="json.state==1 || json.state==2 || json.state==3">
        <a-icon type="close-circle"/>
        不合适
      </a-button>
      <a-button @click="DeliverDetailAdd(2)" type="primary" v-if="json.state==1">邀面试</a-button>
      <a-button @click="DeliverDetailAdd(4)" type="primary" v-if="json.state==3">推送企业</a-button>
      <a-button @click="DeliverDetailAdd(7)" type="primary" v-if="json.state==4">准入职</a-button>
      <a-button @click="isOrNotJob(8)" type="primary" v-if="json.state==9">确认离职</a-button>
      <a-button @click="isOrNotJob(7)" type="primary" v-if="json.state==9">仍在职</a-button>
    </div>
    <!--邀请面试-->
    <a-modal centered title="邀请面试" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
      <a-form-model ref="ruleForm" :model="addjson" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="面试时间" prop="time">
          <a-date-picker v-model="addjson.time" show-time type="date" placeholder="请选择面试时间" style="width: 100%;"/>
        </a-form-model-item>

        <a-form-model-item label="面试地点" prop="address">
          <a-input autocomplete="autocomplete" placeholder="面试地点" v-model="addjson.address">
          </a-input>
        </a-form-model-item>

        <a-form-model-item label="面试备注">
          <a-textarea autocomplete="autocomplete" placeholder="面试备注" v-model="addjson.remark">
          </a-textarea>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!--    准入职-->
    <a-modal centered title="准入职" :visible="showGetVisibel" :confirm-loading="confirmLoading" @ok="canGetJob"
             @cancel="closeGetJob">
      <a-form-model ref="ruleFormJob" :model="getJobJson" :rules="rules" :label-col="{ span: 5 }"
                    :wrapper-col="{ span: 16 }">
        <a-form-model-item label="入职时间" prop="entryTime">
          <a-date-picker v-model="getJobJson.entryTime" show-time type="date" placeholder="请选择入职时间"
                         style="width: 100%;"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
<!--    是否离职-->
    <a-modal centered title="离职审核" :visible="isLiveVisibel" :confirm-loading="confirmLoading" @ok="ajaxisOrNotJob" @cancel="closeIsOrNot">
      <a-form-model ref="ruleFormIsLeave" :model="isLive" :rules="rules" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">

        <a-form-model-item label="审核时间" prop="leaveTime">
          <a-date-picker v-model="isLive.leaveTime" show-time type="date" placeholder="请选择审核时间" style="width: 100%;"/>
        </a-form-model-item>

        <a-form-model-item label="备注">
          <a-textarea autocomplete="autocomplete" placeholder="备注" v-model="isLive.resion">
          </a-textarea>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-card>
</template>

<script>
  import {VpnDeliverLeaveAudit,asyncvpnuserbasicDeli, asyncvpndeliverDetail, asyncvpnDeliverDetailAdd} from '@/services/job'

  export default {
    components: {},
    data() {
      return {
        jsonstateName: {
          0: '待审核',
          1: '招聘中',
          2: '停止招聘'
        },
        flagName: {
          0: "未选择",
          1: "已同意",
          2: "已拒绝",
          3: "已失效"
        },
        stateName: {
          0: '不合适',
          1: '已投递',
          2: '已邀约',
          3: '待面试',
          4: '推送企业',
          5: '企业面试',
          6: '已面试',
          7: '已入职',
          8: '已离职',
          9:'离职审核中'
        },
        devId: this.$route.query.devId,
        json: {},
        addjson: {
          devId: this.$route.query.devId,
          state: 2,
          remark: '',
          address: '',
          time: ''
        },
        rules: {
          time: [{required: true, message: '请选择面试时间', trigger: 'change'}],
          address: [{required: true, message: '请输入面试地址', trigger: 'blur'}],
          entryTime: [{required: true, message: '请选择入职时间', trigger: 'change'}],
          leaveTime:[{required: true, message: '请选择审核时间', trigger: 'change'}]
        },
        speedList: [],
        visible: false,
        confirmLoading: false,
        //准入职
        showGetVisibel: false,
        getJobJson: {
          devId: this.$route.query.devId,
          state: 7,
          entryTime: ''
        },
        //  是否离职
        isLiveVisibel:false,
        isLive: {
          devId: parseInt(this.$route.query.devId),
          state: '',
          resion: '',
          leaveTime: "",
        }
      }
    },
    created() {
      this.getData()
      this.findspeedList()
    },
    methods: {
      closeGetJob() {
        this.showGetVisibel = false;
      },
      closeIsOrNot(){
        this.isLiveVisibel=false;
      },
      isOrNotJob(state){
        this.isLiveVisibel=true;
        this.isLive.state=state;
      },
      //是否已经离职
      ajaxisOrNotJob() {
        this.$refs.ruleFormIsLeave.validate(valid => {
          if (valid) {
            VpnDeliverLeaveAudit({
              vpnDeliverDetailAdd:{
                ...this.isLive
              }
            }).then(res => {
              if (res.data.code == 1) {
                this.$message.success(res.data.msg);
                this.isLiveVisibel = false;
                this.findspeedList();
                this.getData();
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      //准入职
      canGetJob() {
        this.$refs.ruleFormJob.validate(valid => {
          if (valid) {
            asyncvpnDeliverDetailAdd({
              ...this.getJobJson
            }).then(res => {
              if (res.data.code == 1) {
                this.$message.success(res.data.msg);
                this.showGetVisibel = false;
                this.findspeedList();
                this.getData();
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      getData() {
        asyncvpnuserbasicDeli(this.devId).then(result => {
          if (result.data.code == 1) {
            this.json = result.data.data
          } else {
            this.$message.error(result.data.msg)
          }
        })
      },
      findspeedList() {
        asyncvpndeliverDetail(this.devId).then(result => {
          if (result.data.code == 1) {
            this.speedList = result.data.data.vpndeliverGetResp
          } else {
            this.$message.error(result.data.msg)
          }
        })
      },
      Detail() {
        this.$router.push({path: '/job/personneldetail', query: {userId: this.json.userId}})
      },
      DeliverDetailAdd(state) {
        const _self = this
        if (state == 0 || state == 4) {

          this.$confirm({
            title: state == '0' ? '不合适' : '推送企业',
            content: state == '0' ? '是否将人才' + this.json.userName + '标记不为合适？' : '是否将人才' + this.json.userName + '推送至企业？',
            onOk() {
              const requests = {
                devId: _self.devId,
                state: state
              }
              asyncvpnDeliverDetailAdd(requests).then(result => {
                if (result.data.code == 1) {
                  _self.$message.success(result.data.msg, 3)
                  _self.findspeedList()
                } else {
                  this.$message.error(result.data.msg)
                }
              })
            },
            onCancel() {

            },
          })
        } else if (state == 2) {
          this.visible = true
        } else if (state == 7) {
          this.showGetVisibel = true
        }
      },
      Cancel() {
        this.visible = false
      },
      onSubmit() {
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading = true
            asyncvpnDeliverDetailAdd(this.addjson).then(result => {
              if (result.data.code == 1) {
                this.$message.success(result.data.msg, 3)
                this.findspeedList()
                console.log(this.json)
                this.visible = false
              } else {
                this.$message.error(result.data.msg)
              }
              this.confirmLoading = false
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
  .page-header-wide {
    .main {
      min-height: 46px;
    }
  }

  .p_title {
    width: 100%;
    height: 30px;
    line-height: 30px;
    color: rgba(0, 0, 0, 0.85);
    position: absolute;
    top: -70px;
    left: 0px;
    padding: 0px 10px;

    label {
      font-size: 20px;
      color: rgba(0, 0, 0, 0.85);
    }

    .ant-btn {
      float: right;
    }
  }

  .speedlist {
    padding-left: 120px;

    .li {
      position: relative;
      border-left: 2px solid #1890ff;
      padding: 0px 10px 20px 20px;
      margin: 0px;

      .createTime {
        width: 100px;
        position: absolute;
        left: -100px;
        top: 0px;
        text-align: center;
        color: #999;
      }

      .info {
        height: auto;
        margin-top: 10px;

        p {
          line-height: 24px;
          color: #999;
          margin: 0px
        }
      }

      .stateName {
        font-weight: 700;

        span {
          margin-left: 10px;
          border-radius: 3px;
          display: inline-block;
          padding: 0px 10px;
          color: #fff;
          font-weight: normal;
        }

        .flag1 {
          background: #1890ff;
        }

        .flag2 {
          background: #dd495b;
        }

        .flag3 {
          background: #dcdcdc;
        }
      }

    }

    .li:after {
      content: '';
      width: 12px;
      height: 12px;
      border-radius: 20px;
      background: #1890ff;
      position: absolute;
      left: -7px;
      top: 0px;
      border: 2px solid #fff;
    }

    .li:last-child {
      padding-bottom: 0px;
    }
  }

  .action {
    text-align: center;
    margin-top: 40px;

    .ant-btn-primary {
      border: 1px solid #1890ff;
      color: #1890ff;
      background: #fff;
      margin: 0px 10px;
    }
  }
</style>
