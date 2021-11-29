<template>
  <a-card>
    <div class="head_action" v-if="button.add">
      <a-button @click="Add" class="Add" type="primary">新增企业会员</a-button>
    </div>
    <a-tabs :default-active-key="json.vpncompanyGet.tag" @change="callback">
      <a-tab-pane key="1" tab="全部会员">
        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="企业名称：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px">
              <a-input autocomplete="autocomplete" placeholder="企业名称" v-model="json.vpncompanyGet.name">
              </a-input>
            </a-form-item>

            <a-form-item label="专职客服：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px" v-if="json.vpncompanyGet.tag==1">
              <a-input autocomplete="autocomplete" placeholder="专职客服" v-model="json.vpncompanyGet.gmName">
              </a-input>
            </a-form-item>

            <a-form-item label="联系人：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:200px" v-else>
              <a-input autocomplete="autocomplete" placeholder="联系人" v-model="json.vpncompanyGet.userName">
              </a-input>
            </a-form-item>

            <a-form-item label="委托面试：" :labelCol="{span: 9}" :wrapperCol="{span: 15}" style="width:180px">
              <a-select placeholder="是否委托面试" :default-active-first-option="false" v-model="json.vpncompanyGet.isEntrust">
                <a-select-option value="1">
                  是
                </a-select-option>
                <a-select-option value="0">
                  否
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:60px;">
            <a-button :loading="loading" @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.companyId" @change="handleTableChange">
          <span slot="licenseUrl" slot-scope="text,record"><img :src="record.licenseUrl" width="40" :onerror="errorImg" v-if="record.licenseUrl!=''"/></span>
          <span slot="isEntrust" slot-scope="text,record">
            <a-switch :checked-children="record.isEntrust?'开':'关'" v-model="record.isEntrust" :loading="swichloading" un-checked-children="关" @click="SetEntrust(record.companyId,record.isEntrust)" v-if="button.examine"/>
            <a-switch :checked-children="record.isEntrust?'开':'关'" v-model="record.isEntrust" un-checked-children="关" disabled v-else/>

          </span>
          <span slot="action" slot-scope="text,record">
            <!--<a @click="SetState(1,record.companyId,record.name)" v-if="record.state==0 && button.examine">审核</a>-->
            <!--<a @click="SetState(2,record.companyId,record.name)" v-if="record.state==1 && button.distribution">分配</a>-->
            <a @click="SetState(2,record.companyId,record.name)" v-if="record.state==2 && button.distribution">分配</a>
          </span>
        </a-table>

      </a-tab-pane>
      <a-tab-pane key="2" tab="待审核" force-render>

        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="企业名称：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px">
              <a-input autocomplete="autocomplete" placeholder="企业名称" v-model="json.vpncompanyGet.name">
              </a-input>
            </a-form-item>

            <a-form-item label="联系人：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px">
              <a-input autocomplete="autocomplete" placeholder="联系人" v-model="json.vpncompanyGet.userName">
              </a-input>
            </a-form-item>

            <a-form-item label="委托面试：" :labelCol="{span: 9}" :wrapperCol="{span: 15}" style="width:180px">
              <a-select placeholder="是否委托面试" :default-active-first-option="false" v-model="json.vpncompanyGet.isEntrust">
                <a-select-option value="1">
                  是
                </a-select-option>
                <a-select-option value="0">
                  否
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:60px;">
              <a-button :loading="loading" @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.companyId" @change="handleTableChange">
          <span slot="licenseUrl" slot-scope="text,record"><img :src="record.licenseUrl" width="40" :onerror="errorImg" v-if="record.licenseUrl!=''"/></span>
          <span slot="isEntrust" slot-scope="text,record">
            <a-switch :checked-children="record.isEntrust?'开':'关'" v-model="record.isEntrust" :loading="swichloading" un-checked-children="关" @click="SetEntrust(record.companyId,record.isEntrust)"/>
          </span>
          <span slot="action" slot-scope="text,record">
            <!--<a @click="SetState(1,record.companyId,record.name)" v-if="record.state==0 && button.examine" >审核</a>
            <a @click="SetState(2,record.companyId,record.name)" v-if="record.state==1 && button.distribution">分配</a>-->
            <a @click="SetState(1,record.companyId,record.name)" v-if="record.state==1 && button.examine" >审核</a>
            <a @click="SetState(2,record.companyId,record.name)" v-if="record.state==2 && button.distribution">分配</a>
          </span>
        </a-table>

      </a-tab-pane>
      <a-tab-pane key="3" tab="我的客户">

        <div class="Searchbox clearfix">
          <a-form-model :model="json">
            <a-form-item label="企业名称：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px">
              <a-input autocomplete="autocomplete" placeholder="企业名称" v-model="json.vpncompanyGet.name">
              </a-input>
            </a-form-item>

            <a-form-item label="联系人：" :labelCol="{span: 7}" :wrapperCol="{span: 17}" style="width:220px">
              <a-input autocomplete="autocomplete" placeholder="联系人" v-model="json.vpncompanyGet.userName">
              </a-input>
            </a-form-item>

            <a-form-item label="委托面试：" :labelCol="{span: 9}" :wrapperCol="{span: 15}" style="width:180px;">
              <a-select placeholder="是否委托面试" :default-active-first-option="false" v-model="json.vpncompanyGet.isEntrust">
                <a-select-option value="1">
                  是
                </a-select-option>
                <a-select-option value="0">
                  否
                </a-select-option>
              </a-select>
            </a-form-item>

            <a-form-item style="width:60px;">
              <a-button :loading="loading" @click="Search" type="primary">查询</a-button>
            </a-form-item>

          </a-form-model>
        </div>

        <a-table :columns="columns" :data-source="Datalist" :loading="loading" :pagination="pagination" :rowKey="Datalist=>Datalist.companyId" @change="handleTableChange">
          <span slot="licenseUrl" slot-scope="text,record"><img :src="record.licenseUrl" width="40" :onerror="errorImg" v-if="record.licenseUrl!=''"/></span>
          <span slot="isEntrust" slot-scope="text,record">
            <a-switch :checked-children="record.isEntrust?'开':'关'" v-model="record.isEntrust" :loading="swichloading" un-checked-children="关" @click.native="SetEntrust(record.companyId,record.isEntrust)"/>
          </span>
          <span slot="action" slot-scope="text,record">
           <!-- <a @click="SetState(2,record.companyId,record.name)" v-if="record.state==1 && button.examine">审核 </a>-->
            <!--<a @click="SetState(2,record.companyId,record.name)" v-if="record.state==1 && button.distribution">分配</a>-->
            <a @click="SetState(2,record.companyId,record.name)" v-if="record.state==2 && button.distribution">分配</a>
          </span>
        </a-table>

      </a-tab-pane>
    </a-tabs>

    <a-modal centered :title="'审核 - '+examinejson.name" :visible="visible" :confirm-loading="confirmLoading" @ok="onSubmitexamine" @cancel="Cancelexamine">
      <a-form-model :model="examinejson" ref="ruleForm" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 19 }">
        <a-form-model-item label="审核">
          <a-radio-group name="radioGroup" v-model="examinejson.state">
            <a-radio :value="2">通过</a-radio>
            <a-radio :value="0">不通过</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="原因" v-if="examinejson.state==0" prop="resion">
          <a-textarea autocomplete="autocomplete" placeholder="不通过原因" v-model="examinejson.resion">
          </a-textarea>
        </a-form-model-item>

      </a-form-model>
    </a-modal>
    <a-modal centered :title="'分配 - '+examinejson.name" :visible="distributionvisible" :confirm-loading="distributionconfirmLoading" @ok="onSubmitdistribution" @cancel="Canceldistribution">

      <a-form-model :model="distributionjson" ref="ruleForm" :rules="rules" :label-col="{ span: 4 }" :wrapper-col="{ span: 19 }">
        <a-form-model-item label="专属客服" prop="cusId">
          <a-select show-search v-model="distributionjson.cusId" placeholder="请选择专属客服" :default-active-first-option="false" :filter-option="false" :not-found-content="null" @search="handleSearch" @change="handleChange">
            <a-select-option v-for="list in ulist" :key="list.id">
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
  import {getvpncompanyGetList,asyncvpncompanyUpdate} from '@/services/member'
  import {getUserlistAll} from '@/services/user'
  export default {
    data() {
      return {
        json:{
          svcCaller:{
            page:1,
            pageSize:10
          },
          vpncompanyGet:{
            tag:1,
            name:'',
            userName:'',
            gmName:'',
            isEntrust:undefined
          }
        },
        columns:[
          {
            title: '企业名称',
            dataIndex: 'name',
          },{
            title: '姓名',
            dataIndex: 'userName',
          },{
            title: '联系电话',
            dataIndex: 'phone',
          },{
            title: '公司职位',
            dataIndex: 'job',
          },{
            title: '营业执照',
            dataIndex: 'licenseUrl',
            scopedSlots: { customRender: 'licenseUrl' }
          },{
            title: '委托面试',
            dataIndex: 'isEntrust',
            scopedSlots: { customRender: 'isEntrust' }
          },{
            title: '专职客服',
            dataIndex: 'gmName',
          },{
            title: '操作',
            key: 'action',
            width:60,
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
        rules: {
          resion:[{ required: true, message: '请输入不通过原因',trigger: 'blur'}],
          cusId:[{ required: true, message: '请选择专属客服',trigger: 'blur'}],
        },
        examinejson:{
          name:'',
          companyId:0,
          state:2,
          resion:''
        },
        distributionjson:{
          name:'',
          companyId:0,
          cusId:undefined
        },
        ulist:[],
        swichloading:false,
        loading:false,
        visible:false,
        confirmLoading:false,
        distributionvisible:false,
        distributionconfirmLoading:false,
        errorImg:'this.src="' + require('../../assets/img/undefined.png') + '"',
        button:{}
      }
    },
    created() {
      this.button=IsAction(this.$route.meta.button)
      console.log(this.$route.meta)
      if(this.button.examine==undefined && this.button.distribution==undefined){
        this.columns.splice(this.columns.length-1, 1)
      }
      this.findList()
    },
    methods: {
      callback(key) {
        this.json.vpncompanyGet.tag=key
        this.Search()
      },
      findList(){
        this.loading=true
        getvpncompanyGetList(this.json).then(result => {
          if(result.data.code==1){
            this.Datalist=result.data.data.vpncompanyGetResp

            // for(var i=0;i<this.Datalist.length;i++){
            //   this.Datalist[i].isEntrust=this.Datalist[i].isEntrust==1?true:false
            //
            // }
            this.pagination.total=result.data.data.totalElements
          }else{
            this.$message.error(result.data.msg)
          }
          this.loading=false
        })
      },
      Search(){
        this.pagination.total=0
        this.json.svcCaller.page=1
        this.findList()
      },
      handleTableChange(pagination) {
        this.json.svcCaller.page = pagination.current
        this.findList()
      },
      SetEntrust(companyId,isEntrust){
        const _self=this

        const requests={
          vpncompanyUpdate:{
            companyId:companyId,
            isEntrust:isEntrust?1:0
          }
        }

        asyncvpncompanyUpdate(requests).then(result => {
          if(result.data.code==1){
            _self.$message.success(result.data.msg, 3)

          }else{
            _self.error = result.data.msg
          }
          _self.findList()
        })

      },
      Add(){
        this.$router.push({path:'/qy/add'})
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
        this.distributionjson.cusId=value
      },
      SetState(state,companyId,name){
        if(state==1){
          this.examinejson.name=name
          this.examinejson.companyId=companyId
          this.visible=true
        }else if(state==2){
          this.distributionjson.name=name
          this.distributionjson.companyId=companyId
          this.distributionvisible=true
        }

      },
      onSubmitexamine(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.confirmLoading=true
            asyncvpncompanyUpdate({vpncompanyUpdate:this.examinejson}).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.findList()
                this.visible=false
              }else{
                this.$message.error(result.data.msg)
              }
              this.confirmLoading=false
            })
          } else {
            return false;
          }
        })
      },
      Cancelexamine(){
        this.visible=false
      },
      onSubmitdistribution(){
        this.$refs.ruleForm.validate(valid => {
          if (valid) {
            this.distributionconfirmLoading=true
            asyncvpncompanyUpdate({vpncompanyUpdate:this.distributionjson}).then(result => {
              if(result.data.code==1){
                this.$message.success(result.data.msg, 3)
                this.findList()
                this.distributionvisible=false
              }else{
                this.$message.error(result.data.msg)
              }
              this.distributionconfirmLoading=false
            })
          } else {
            return false;
          }
        })
      },
      Canceldistribution(){
        this.distributionvisible=false
      }
    }
  }
</script>
<style lang="less">

</style>
