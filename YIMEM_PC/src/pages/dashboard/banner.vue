<template>
  <div class="page-main">
    <a-tabs default-active-key="1" @change="callback">
      <a-tab-pane key="1" tab="求职者">
        <div class="clearfix">
          <div class="picList">
            <a-card class="" v-for="(list,index) in dataList" :key="index"><img :src="list.url" width="100%"/><a-icon type="delete" @click="delpic(list.ssId)" v-if="button.del"/></a-card>
          </div>

          <a-upload name="file" list-type="picture-card" :action="imgposturl" :multiple="true" :file-list="fileList" @preview="handlePreview" @change="handleChange" v-if="button.add">
            <div v-if="fileList.length < 20">
              <a-icon type="plus" />
              <div class="ant-upload-text">
                <p>上传图片</p>
                <p>支持jpg,png,gif格式，文件不超过2M</p>
              </div>
            </div>
          </a-upload>
        </div>

        <a-button :loading="uploading" @click="check" size="large" type="primary" class="subclass" v-if="button.add">保存并提交</a-button>

      </a-tab-pane>
      <a-tab-pane key="2" tab="分销者" force-render>

        <div class="clearfix">
          <div class="picList">
            <a-card class="" v-for="(list,index) in dataList" :key="index"><img :src="list.url"/><a-icon type="delete" @click="delpic(list.ssId)" v-if="button.del"/></a-card>
          </div>

          <a-upload name="file" list-type="picture-card" :action="imgposturl" :multiple="true" :file-list="fileList" @preview="handlePreview" @change="handleChange" v-if="button.add">
            <div v-if="fileList.length < 20">
              <a-icon type="plus" />
              <div class="ant-upload-text">
                <p>上传图片</p>
                <p>支持jpg,png,gif格式，文件不超过2M</p>
              </div>
            </div>
          </a-upload>
        </div>

        <a-button :loading="uploading" @click="check" size="large" type="primary" class="subclass" v-if="button.add">保存并提交</a-button>

      </a-tab-pane>
      <a-tab-pane key="3" tab="企业端">

        <div class="clearfix">
          <div class="picList">
            <a-card class="" v-for="(list,index) in dataList" :key="index"><img :src="list.url"/><a-icon type="delete" @click="delpic(list.ssId)" v-if="button.del"/></a-card>
          </div>

          <a-upload name="file" list-type="picture-card" :action="imgposturl" :multiple="true" :file-list="fileList" @preview="handlePreview" @change="handleChange" v-if="button.add">
            <div v-if="fileList.length < 20">
              <a-icon type="plus" />
              <div class="ant-upload-text">
                <p>上传图片</p>
                <p>支持jpg,png,gif格式，文件不超过2M</p>
              </div>
            </div>
          </a-upload>
        </div>

        <a-button :loading="uploading" @click="check" size="large" type="primary" class="subclass" v-if="button.add">保存并提交</a-button>

      </a-tab-pane>
    </a-tabs>

    <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
      <img alt="example" style="width: 100%" :src="previewImage" />
    </a-modal>
  </div>
</template>

<script>
  import {IsAction} from '@/utils/button'
  import {imgposturl} from '@/services/api'
  import {getBannerlist,asyncBannerUpload,asyncBannerDel} from '@/services/dashboard'

export default {
  data() {
    return {
      imgposturl:imgposturl,
      toUser:1,
      dataList:[],
      fileList:[],
      loading:false,
      uploading:false,
      previewVisible:false,
      previewImage:'',
      button:{}
    }
  },
  created() {
    this.findList()
    this.button=IsAction(this.$route.meta.button)
  },
  methods: {
    callback(key) {
      this.toUser=key
      this.fileList=[]
      this.findList()
    },
    findList(){
      this.loading = true
      getBannerlist(this.toUser).then(result => {
        if(result.data.code==1){
          this.dataList=result.data.data.vpnslideshowGetResp
        }else{
          this.$message.error(result.data.msg)
        }
        this.loading = false
      })
    },
    handleChange({fileList}) {
      this.fileList=fileList
    },
    handleCancel() {
      this.previewVisible = false;
    },
    handlePreview(file) {
      this.previewImage = file.response.data.imageUrl || file.thumbUrl;
      this.previewVisible = true;
    },
    check(){
      this.uploading = true
      let picData=[]
      console.log(this.fileList)
      for(var i=0;i<this.fileList.length;i++){
        if(this.fileList[i].status=="done"){
         picData.push(this.fileList[i].response.data)
        }
      }
      if(picData.length<=0){
        this.$message.error('请上传图片')
        return false
      }
      asyncBannerUpload(this.toUser,picData).then(result => {
        if(result.data.code==1){
          this.findList()
          this.fileList=[]
        }else{
          this.$message.error(result.data.msg)
        }
        this.uploading = false
      })
    },
    delpic(ssId){
      const _self=this
      this.$confirm({
        title: '确认删除吗？',
        //content: '',
        onOk() {
          let closeMessage = _self.$message.loading('正在删除...')
          asyncBannerDel(ssId).then(result => {
            closeMessage.then(() => _self.$message.success(result.data.msg, 2.5))
            if(result.data.code==1){
              _self.findList()
            }else{
              _self.error = result.data.msg
            }
          })
        },
        onCancel() {

        },
      })

    }
  }
}
</script>
<style lang="less">
.page-main{background:#fff;heigith:auto;padding:0px 15px 15px 15px}
.subclass{margin-top:20px;padding:0px 30px;font-size:14px;}

.picList{height:auto;
  .ant-card{width:220px;float:left;margin-right:8px;margin-bottom:8px;text-align:center;vertical-align:top;position:relative;
    .anticon{position:absolute;top:10px;right:10px;width:30px;height:30px;border-radius:30px;background:rgba(0,0,0,0.4);text-align:center;line-height:30px;display:inline-block;color:#fff}
  }
}


.ant-upload.ant-upload-select-picture-card{width:220px;height:220px;border-radius:0px;
  .ant-upload-text{font-size:12px;color:#aaa;line-height:24px;}
}
.ant-upload-select-picture-card i {
  font-size: 40px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
.ant-upload-list-picture-card-container{width:220px;height:220px;}
.ant-upload-list-picture-card .ant-upload-list-item{width:220px;height:220px;border-radius:0px;}
</style>
