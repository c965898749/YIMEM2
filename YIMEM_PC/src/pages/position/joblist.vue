<template>
  <a-card>
    <div class="head_action">
      <a-button @click="SetState(2)" class="Stop" type="primary" v-if="json.vpnjobGet.weTO==1 && button.edit">停止招聘</a-button>
      <a-button @click="SetState(1)" class="Star" type="primary" v-if="json.vpnjobGet.weTO==1 && button.edit">开始招聘</a-button>
      <a-button @click="Send()" class="Send" type="primary" v-if="button.edit">发布</a-button>
      <a-button @click="Add" class="Add" type="primary" v-if="json.vpnjobGet.weTO==1 && button.add">新增岗位</a-button>
    </div>

    <a-tabs :default-active-key="json.vpnjobGet.weTO" @change="callback">
      <a-tab-pane key="1" tab="企业面试">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="职位名称：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
              <a-input autocomplete="autocomplete" placeholder="职位名称" v-model="json.vpnjobGet.name">
              </a-input>
            </a-form-item>
            <a-form-item label="招聘城市：" :labelCol="{span: 5}" :wrapperCol="{span: 19}" style="width:330px">

              <a-select placeholder="请选择省份" :default-active-first-option="false" v-model="json.vpnjobGet.provinceId" @change="findCityList" style="width:100px">
                <a-select-option v-for="list in ProvinceList" :key="list.provinceCode">
                  {{ list.zhLabel }}
                </a-select-option>
              </a-select>

              <a-select placeholder="请选择城市" :default-active-first-option="false" v-model="json.vpnjobGet.cityId" style="width:140px;margin-left:10px">
                <a-select-option v-for="list in CityList" :key="list.uuid">
                  {{ list.zhLabel }}
                </a-select-option>
              </a-select>

            </a-form-item>
            <a-form-item label="公司名称：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
              <a-input autocomplete="autocomplete" placeholder="公司名称" v-model="json.vpnjobGet.companyName">
              </a-input>
            </a-form-item>
            <a-form-item label="状态：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:160px">
              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnjobGet.state">
                <a-select-option v-for="(list,index) in statName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item style="width:80px">
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-item>
          </a-form-model>

        </div>

        <a-table :columns="columns" :rowSelection="{type:'checkbox',selectedRowKeys:selectedRowKeys,onSelect:checkbox,onSelectAll:checkboxAll}" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.jobId" @change="handleTableChange">
          <a slot="name" slot-scope="text,record" @click="Detail(record.jobId)">{{record.name}}</a>
          <span slot="cityName" slot-scope="text,record">{{record.cityName}}</span>
          <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{statName[text]}}</span>
          <span slot="action" slot-scope="text, record">
            <template v-if="button.examine && record.state==0">
            <a @click="Examine(record.jobId)">审核</a>
            <a-divider type="vertical"/>
            </template>
            <template v-if="button.edit">
            <a @click="Send(record.jobId)" v-if="record.state==1">发布</a>
            <a-divider type="vertical" v-if="record.state==1"/>

            <a @click="Edit(record.jobId)">编辑</a>
            <a-divider type="vertical" v-if="record.state==1 || record.state==2"/>
            <a @click="SetState(2,record.jobId,record.name)" v-if="record.state==1">停止招聘</a>
            <a @click="SetState(1,record.jobId,record.name)" v-if="record.state==2">开始招聘</a>
            </template>
          </span>
        </a-table>

      </a-tab-pane>
      <a-tab-pane key="2" tab="委托面试">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="职位名称：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
              <a-input autocomplete="autocomplete" placeholder="职位名称" v-model="json.vpnjobGet.name">
              </a-input>
            </a-form-item>
            <a-form-item label="招聘城市：" :labelCol="{span: 5}" :wrapperCol="{span: 19}" style="width:330px">

              <a-select placeholder="请选择省份" :default-active-first-option="false" v-model="json.vpnjobGet.provinceId" @change="findCityList" style="width:100px">
                <a-select-option v-for="list in ProvinceList" :key="list.provinceCode">
                  {{ list.zhLabel }}
                </a-select-option>
              </a-select>

              <a-select placeholder="请选择城市" :default-active-first-option="false" v-model="json.vpnjobGet.cityId" style="width:140px;margin-left:10px">
                <a-select-option v-for="list in CityList" :key="list.uuid">
                  {{ list.zhLabel }}
                </a-select-option>
              </a-select>

            </a-form-item>
            <a-form-item label="公司名称：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:200px">
              <a-input autocomplete="autocomplete" placeholder="公司名称" v-model="json.vpnjobGet.companyName">
              </a-input>
            </a-form-item>
            <a-form-item label="状态：" :labelCol="{span: 8}" :wrapperCol="{span: 16}" style="width:160px">
              <a-select placeholder="请选择状态" :default-active-first-option="false" v-model="json.vpnjobGet.state">
                <a-select-option v-for="(list,index) in statName" :key="index">
                  {{ list }}
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-form-item style="width:80px">
              <a-button @click="Search" type="primary">查询</a-button>
            </a-form-item>
          </a-form-model>

        </div>

        <a-table :columns="columns" :rowSelection="{type:'checkbox',selectedRowKeys:selectedRowKeys,onSelect:checkbox,onSelectAll:checkboxAll}" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.jobId" @change="handleTableChange">
          <a slot="name" slot-scope="text,record" @click="Detail(record.jobId)">{{record.name}}</a>
          <span slot="cityName" slot-scope="text,record">{{record.cityName}}</span>
          <span slot="state" slot-scope="text" :class="'state'+text"><i>●</i> {{statName[text]}}</span>
          <span slot="action" slot-scope="text, record">
            <a @click="Examine(record.jobId)" v-if="record.state==0">审核</a>
            <a @click="Send(record.jobId)" v-if="record.state==1">发布</a>
          </span>
        </a-table>
      </a-tab-pane>
    </a-tabs>

    <a-modal centered title="发布到" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmit" @cancel="Cancel">
<!--      :rules="rules"-->
      <a-form-model  :model="sendjson"  :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
        <a-form-model-item label="导航栏" prop="priority">
          <a-select show-search v-model="sendjson.priority"  placeholder="请选择发布到的导航栏" :default-active-first-option="true" :filter-option="false" :not-found-content="null" @change="handleChange">
            <a-select-option v-for="list in navlist" :key="list.naId">
              {{ list.name }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

  </a-card>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {getProvinceListAll,getCityListAll} from '@/services/user'
  import {vpnjobGetList,asyncjobUpdateState} from '@/services/job'
  import {getNavigationAll} from '@/services/dashboard'
  export default {
    data() {
      return {
        statName:{
          0:'待审核',
          1:'招聘中',
          2:'已停止'
        },
        ProvinceList:[],
        CityList:[],
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpnjobGet:{
            weTO:1,
            companyName:'',
            provinceId:undefined,
            cityId:undefined,
            name:'',
            state:undefined
          }
        },
        selectedRowKeys:[],
        columns:[
          {
            title: '职位名称',
            dataIndex: 'name',
            scopedSlots: { customRender: 'name'}
          },
          {
            title: '职位类型',
            dataIndex: 'jobCateName',
          },{
            title: '招聘城市',
            dataIndex: 'cityName',
            scopedSlots: { customRender: 'cityName'}
          },{
            title: '招聘公司',
            dataIndex: 'companyName',
          },{
            title: '企业收费',
            dataIndex: 'money',
          },{
            title: '推荐佣金',
            dataIndex: 'brokerage',
          },{
            title: '状态',
            dataIndex: 'state',
            scopedSlots: { customRender: 'state' }
          },{
            title: '报名人数',
            dataIndex: 'peopleNum',
          },{
            title: '发布时间',
            dataIndex: 'createTime',
          },{
            title: '操作',
            key: 'action',
            width:180,
            scopedSlots: { customRender: 'action' }
          }
        ],

        pagination:{
          total: 0,
          pageSize: 10,
          showSizeChanger: true,
          showQuickJumper:true
        },
        Datalist:[],
        navlist:[],
        sendjson:{
          jobId:[],
          state:1,
          priority:undefined
        },
        // rules:{
        //   priority: [{ required: true, message: '请选择发布到的导航栏'}],
        // },
        loading:true,
        visible:false,
        confirmLoading:false,
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      if(this.button.edit==undefined && this.button.examine==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.findList()
      this.findProvinceList()
      this.findCityList();
      this.handleSearch();
    },
    methods: {
      callback(key) {
        this.json.vpnjobGet.weTO=parseInt(key)
        this.Search()

        if(key==1){
          this.columns[9].width=180
        }else{
          this.columns[9].width=60
        }

      },
      findList() {
        vpnjobGetList(this.json).then(result => {
          this.loading = false
          if(result.data.code==1){
            this.Datalist=result.data.data.vpnjobGetResp
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
        })
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
        this.json.vpnjobGet.cityId=undefined
        if(this.json.vpnjobGet.provinceId != undefined && this.json.vpnjobGet.provinceId>0){
          getCityListAll(this.json.vpnjobGet.provinceId).then(result => {
            this.loading = false
            if(result.data.code==1){
              this.CityList=result.data.data.vpnRmCityGetResp
            }else{
              this.$message.error(result.data.msg)
            }
          })
        }
      },
      checkbox(record){
        let rowKeys=this.selectedRowKeys
        if(rowKeys.length>0 && rowKeys.includes(record.jobId)){
          rowKeys.splice(rowKeys.indexOf(record.jobId),1)
        }else{
          rowKeys.push(record.jobId)
        }
        this.selectedRowKeys = rowKeys;
      },
      checkboxAll(selected){
        if(selected){
          for(var i=0;i<this.Datalist.length;i++){
            this.selectedRowKeys.push(this.Datalist[i].jobId)
          }
        }else{
          this.selectedRowKeys=[]
        }
      },
      handleTableChange(pagination) {
        this.json.svcCaller.page = pagination.current
        this.findList()
      },
      Search(){
        this.pagination.total=0
        this.json.svcCaller.page=1
        this.findList()
      },
      Detail(jobId){
        this.$router.push({path:'/job/detail',query: {jobId:jobId}})
      },
      Add(){
        this.$router.push({path:'/job/add'})
      },
      Edit(jobId){
        this.$router.push({path:'/job/edit',query: {jobId:jobId}})
      },
      Examine(jobId){
        const _self=this
        var requests={
          vpnjobUpdate:{
            jobId:[jobId],
            state:1
          }
        }
        this.$confirm({
          title: '确认审核发布招聘',
          //content: '',
          onOk() {
            asyncjobUpdateState(requests).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.findList()
              }else{
                _self.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      SetState(state,id,name){
        const _self=this
        var jobId=[]
        if(id == undefined){
          if(this.selectedRowKeys.length<=0){
            this.$error({
              title: '请选择您要处理的职位',
            });
            return false
          }
          jobId=this.selectedRowKeys

        }else{
          jobId=[id]
        }
        var requests={
          vpnjobUpdate:{
            jobId:jobId,
            state:state
          }
        }
        this.$confirm({
          title: '提示',
          content: state==1?(id==undefined?'是否开始招聘这些职位吗？':'是否开始招聘"'+name+'"的职位？'):(id==undefined?'是否停止招聘这些职位吗？':'是否停止招聘“'+name+'”的职位？'),
          onOk() {
            asyncjobUpdateState(requests).then(result => {
              if(result.data.code==1){
                _self.$message.success(result.data.msg, 3)
                _self.findList()
              }else{
                _self.$message.error(result.data.msg)
              }
            })
          },
          onCancel() {

          },
        })
      },
      Send(jobId){
        console.log(jobId,this.selectedRowKeys.length)
        if(jobId == undefined){
          if(this.selectedRowKeys.length<=0){
            this.$error({
              title: '请选择发布到的导航栏',
            });
            return false
          }
          this.sendjson.jobId=this.selectedRowKeys

        }else{
          this.sendjson.jobId=[jobId]
        }
        this.visible=true
      },
      handleSearch(value) {
        const json={
          svcCaller:{
            page:1,
            pageSize:10
          },
          name:value
        }
        getNavigationAll(json).then(result => {
          if(result.data.code==1){
            let list=result.data.data.content
            let obj={
              naId: '',
              name: "发布到默认职位"
            }
            let newList=[obj,...list]
            this.navlist=newList
          }else{
            this.$message.error(result.data.msg)
          }
        })
      },
      handleChange(value) {
        this.sendjson.priority=value
      },
      onSubmit(){
        const _self=this
        asyncjobUpdateState({vpnjobUpdate:_self.sendjson}).then(result => {
          if(result.data.code==1){
            _self.$message.success(result.data.msg, 3)
            _self.visible=false;
            _self.findList()
          }else{
            _self.$message.error(result.data.msg)
          }
        })
      },
      Cancel(){
        this.visible=false
      },
    }
  }
</script>
<style lang="less">
  .head_action{
    .Stop{background:#fff;border:1px solid #ddd;color:#999;}
    .Star{background:#e7f5f8;color:#1890ff;}
    .Send{}
  }
  .ant-table-row{
    .state0 i{color:#e5642d}
    .state1 i{color:#1791ff}
    .state2{color:#db4562}
  }
</style>
