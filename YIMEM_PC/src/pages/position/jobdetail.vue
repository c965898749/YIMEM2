<template>
  <a-card>
    <div class="descriptions_title"><label>职位信息</label><span>{{jsonstateName[json.state]}}</span></div>
    <a-descriptions>

      <a-descriptions-item label="职位名称">
        {{json.name}}
      </a-descriptions-item>
      <a-descriptions-item label="职位类型">
        {{json.jobCateName}}
      </a-descriptions-item>
      <a-descriptions-item label="招聘要求">
        {{json.jobExpName}}/{{json.educationExpName}}/{{json.lowSalary}}-{{json.higSalary}}
      </a-descriptions-item>
      <a-descriptions-item label="招聘城市">
        {{json.provinceName}}{{json.cityName}}
      </a-descriptions-item>
      <a-descriptions-item label="企业收费">
        {{json.money}}元/人
      </a-descriptions-item>
      <a-descriptions-item label="推荐佣金">
        {{json.brokerage}}元/人
      </a-descriptions-item>
      <a-descriptions-item label="职位亮点" :span="2">
        <span v-for="(i,index) in json.jobLightspot" :key="index">{{i.name}}<a-divider type="vertical"/></span>
      </a-descriptions-item>
      <a-descriptions-item label="公司地址">
        {{json.companyAdress}}
      </a-descriptions-item>
      <a-descriptions-item label="职位描述">
        {{json.jobContent}}
      </a-descriptions-item>
    </a-descriptions>

    <a-divider/>

    <div class="pageTitle clearfix">
      <div class="table_title">应聘简历</div>
      <div class="Searchbox clearfix">
        <a-form-model :model="liverjson">
          <a-form-model-item label="状态：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
            <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="liverjson.vpndeliverGet.state">
              <a-select-option v-for="(list,index) in stateName" :key="index">
                {{ list }}
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item style="width:140px">
            <a-input autocomplete="autocomplete" placeholder="请搜索姓名" v-model="liverjson.vpndeliverGet.userName">
            </a-input>
          </a-form-model-item>
          <a-form-model-item style="width:80px">
            <a-button @click="Search" type="primary">查询</a-button>
          </a-form-model-item>
        </a-form-model>
      </div>
    </div>
    <a-table :columns="columns" :data-source="liverList" :pagination="pagination" :rowKey="liverList=>liverList.devId"
             @change="handleTableChange">
      <a slot="userName" slot-scope="text,record" @click="Detail(record.devId,record.userId)">{{record.userName}}</a>
      <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{stateName[text]}}</span>
      <span slot="action" slot-scope="text, record">
            <a @click="Detail(record.devId,record.userId)">简历</a>
          </span>
    </a-table>

  </a-card>
</template>

<script>
  import {asynvpnjobDetail, asyncvpndeliverDetailNo} from '@/services/job'

  export default {
    components: {},
    data() {
      return {
        jsonstateName: {
          0: '待审核',
          1: '招聘中',
          2: '停止招聘'
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
        json: {
          jobId: this.$route.query.jobId,
        },
        liverjson: {
          svcCaller: {
            page: 1,
            pageSize: 10
          },
          vpndeliverGet: {
            jobId: this.$route.query.jobId,
            state: undefined,
            userName: ''
          }
        },
        liverList: [],
        columns: [
          {
            title: '应聘人',
            dataIndex: 'userName',
            scopedSlots: {customRender: 'userName'}
          }, {
            title: '联系方式',
            dataIndex: 'phoneNum',
          }, {
            title: '基本信息',
            dataIndex: 'userSex',
            scopedSlots: {customRender: 'info'}
          }, {
            title: '状态',
            dataIndex: 'state',
            scopedSlots: {customRender: 'state'}
          }, {
            title: '更新时间',
            dataIndex: 'createTime',
          }, {
            title: '操作',
            key: 'action',
            width: 60,
            scopedSlots: {customRender: 'action'}
          }
        ],
        pagination: {
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper: true
        },
      }
    },
    created() {
      this.getData()
      this.findliverList()
    },
    methods: {
      getData() {
        asynvpnjobDetail(this.json.jobId).then(result => {
          if (result.data.code == 1) {
            this.json = result.data.data.vpnjobGetResp
          } else {
            this.$message.error(result.data.msg)
          }
        })
      },
      handleTableChange(pagination) {
        this.liverjson.svcCaller.page = pagination.current
        this.findList()
      },
      findliverList() {
        asyncvpndeliverDetailNo(this.liverjson).then(result => {
          if (result.data.code == 1) {
            this.liverList = result.data.data.vpndeliverGetResp
          } else {
            this.$message.error(result.data.msg)
          }
        })
      },
      Search() {
        this.pagination.total = 0
        this.liverjson.svcCaller.page = 1
        this.findliverList()
      },
      Detail(devId, userId) {
        this.$router.push({path: '/job/personnelspeed', query: {devId: devId, userId: userId}})
      }
    }
  }
</script>
<style lang="less">
  .descriptions_title {
    height: auto;
    margin-bottom: 20px;

    label {
      height: 30px;
      line-height: 30px;
      color: rgba(0, 0, 0, 0.85);
      font-weight: bold;
      font-size: 16px;
    }

    span {
      background: #40a9ff;
      border-radius: 3px;
      color: #fff;
      display: inline-block;
      height: 30px;
      line-height: 30px;
      padding: 0px 10px;
      margin-left: 10px;
    }
  }

  .pageTitle {
    height: auto;

    .table_title {
      float: left;
      height: 40px;
      line-height: 40px;
      color: rgba(0, 0, 0, 0.85);
      font-weight: bold;
      font-size: 16px;
    }

    .Searchbox {
      width: 450px;
      float: right;
    }
  }

  .ant-table-row {
    .state0 i {
      color: #999
    }

    .state1 i {
      color: #e7642e
    }

    .state2 i {
      color: #1890ff
    }
  }
</style>
