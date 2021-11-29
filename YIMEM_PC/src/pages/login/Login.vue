<template>
  <common-layout>
    <div class="top">
      <div class="header">
        <span class="title">{{systemLogoName}}</span>
      </div>
      <div class="desc">一梦管理平台</div>
    </div>
    <div class="login">
      <a-form-model ref="ruleForm" :model="json" :rules="rules">
        <a-tabs size="large" :tabBarStyle="{textAlign: 'center'}" style="padding: 0 2px;" @change="getlogintype">
          <a-tab-pane tab="账户密码登录" key="1">
            <a-alert type="error" :closable="true" v-show="error" :message="error" showIcon style="margin-bottom: 24px;" />
            <a-form-model-item prop="name">
              <a-input autocomplete="autocomplete" size="large" placeholder="账号" v-model="json.name">
                <a-icon slot="prefix" type="user" />
              </a-input>
            </a-form-model-item>
            <a-form-model-item prop="password">
              <a-input size="large" placeholder="密码" autocomplete="autocomplete" type="password" v-model="json.password">
                <a-icon slot="prefix" type="lock" />
              </a-input>
            </a-form-model-item>
          </a-tab-pane>

          <a-tab-pane tab="手机号登录" key="2">

            <a-form-model-item prop="phone">
              <a-input size="large" placeholder="手机号" autocomplete="autocomplete" v-model="json.phone">
                <a-icon slot="prefix" type="mobile" />
              </a-input>
            </a-form-model-item>

            <a-form-model-item prop="authCode">

                <a-col :span="16">
                  <a-input size="large" placeholder="验证码" autocomplete="autocomplete" v-model="json.authCode">
                    <a-icon slot="prefix" type="mail" />
                  </a-input>
                </a-col>
                <a-col :span="8" class="authCode_btn">
                  <a-button v-show="sendAuthCode" style="width: 100%" class="captcha-button" size="large" @click="getauthCode" type="primary" :loading="senloading">{{senloading?'正在获取':'获取验证码'}}</a-button>
                  <a-button v-show="!sendAuthCode" style="width: 100%" class="captcha-button" size="large">{{auth_time}}秒</a-button>
                </a-col>

            </a-form-model-item>
          </a-tab-pane>

        </a-tabs>
        <div>
          <a-checkbox :checked="json.ischeck" @change="json.ischeck=!json.ischeck">自动登录</a-checkbox>
          <a style="float: right">忘记密码</a>
        </div>
        <a-form-model-item>
          <a-button :loading="logging" style="width: 100%;margin-top: 24px" type="primary" size="large" @click="onSubmit">登录</a-button>
        </a-form-model-item>
        <!--<div>
          其他登录方式
          <a-icon class="icon" type="alipay-circle" />
          <a-icon class="icon" type="taobao-circle" />
          <a-icon class="icon" type="weibo-circle" />
          <router-link style="float: right" to="/dashboard/workplace" >注册账户</router-link>
        </div>-->
      </a-form-model>
    </div>
  </common-layout>
</template>

<script>
  import {validatorPhone} from '@/utils/validata'
import CommonLayout from '@/layouts/CommonLayout'
import {login, asyncGetAuthCode,asyncLoginByPhone,getRoutesConfig} from '@/services/user'
import {setAuthorization} from '@/utils/request'
import {loadRoutes} from '@/utils/routerUtil'
import {mapMutations} from 'vuex'

export default {
  name: 'Login',
  components: {CommonLayout},
  data () {
    return {
      logintype:1,
      json:{
        name:'',
        password:'',
        phone:'',
        authCode:'',
        ischeck:true,
      },
      rules: {
        name: [{ required: true, message: '请输入账户名', whitespace: true,trigger: 'blur'}],
        password: [{ required: true, message: '请输入密码', whitespace: true,trigger: 'blur'}],
        phone: [{ required: true, validator: validatorPhone,emptymsg:'请输入登录手机号', whitespace: true,trigger: 'blur' }],
        authCode:[{ required: true, message: '请输入验证码', whitespace: true,trigger: 'blur'}]
      },
      senloading:false,
      logging: false,
      error: '',
      sendAuthCode: true,
      auth_time: 120,
      form: this.$form.createForm(this)
    }
  },
  computed: {
    systemLogoName () {
      return this.$store.state.setting.systemLogoName
    }
  },
  methods: {
    ...mapMutations('account', ['setUser', 'setPermissions', 'setRoles']),
    getlogintype(activeKey){
      this.logintype=activeKey
      console.log(this.logintype)
    },
    onSubmit () {

      this.$refs.ruleForm.validateField(this.logintype==1?['name','password']:['phone','authCode'],valid => {
        if (!valid) {
          this.logging = true
          const {name, password, phone,authCode,ischeck} = this.json
          if(this.logintype==1){
            login(name, password,ischeck).then(this.afterLogin)
          }else{
            asyncLoginByPhone(phone, authCode,ischeck).then(this.afterLogin)
          }

        } else {
          return false;
        }
      });
    },
    afterLogin(res) {
      this.logging = false
      const loginRes = res.data
      if (loginRes.code == 1) {
        //loginRes.data.permissions = [{id: 'queryForm', operation: ['add', 'edit']}]
        //loginRes.data.roles = [{id: 'admin', operation: ['add', 'edit', 'delete']}]

        const {overTime,user} = loginRes.data
        loginRes.data.expireAt =overTime
        this.setUser(user)
        // this.setPermissions(permissions)
        // this.setRoles(roles)
        localStorage.setItem("token",loginRes.data.token)
        setAuthorization({token: loginRes.data.token, expireAt: new Date(loginRes.data.expireAt)})
        // 获取路由配置
        getRoutesConfig().then(result => {
          const routesConfig = [{
            router: 'root',
            children: result.data.data
          }]
          loadRoutes(routesConfig)
          this.$router.push('/index')
          this.$message.success(loginRes.msg, 3)
        })
      } else {
        this.$message.error(loginRes.msg, 3)
      }
    },
    getauthCode(){
      this.$refs.ruleForm.validateField('phone', (valid) => {
        if (!valid) {
          this.senloading=true
          const phone=this.json.phone
          asyncGetAuthCode(phone).then((result) => {
            this.senloading=false
            if(result.data.code==1){
              this.auth_time = 120
              this.sendAuthCode = false

              this.getAuthCode();
              this.$message.info('验证码已发送至手机，请注意查收');
            }else{
              this.error = result.data.msg
            }
          })
        }
      });
    },
    // 倒计时
    getAuthCode() {
      if (this.authTimeTimer) {
        clearTimeout(this.authTimeTimer);
      }
      this.authTimeTimer = setTimeout(() => {
        this.auth_time -= 1;
        if (this.auth_time < 0) {
          this.sendAuthCode = true;
          clearTimeout(this.authTimeTimer);
        } else {
          this.getAuthCode();
        }
      }, 1000);
    },
  }
}
</script>

<style lang="less" scoped>
  .common-layout{
    .top {
      text-align: center;
      .header {
        height: 44px;
        line-height: 44px;
        a {
          text-decoration: none;
        }
        .logo {
          height: 44px;
          vertical-align: top;
          margin-right: 16px;
        }
        .title {
          font-size: 33px;
          color: @title-color;
          font-family: 'Myriad Pro', 'Helvetica Neue', Arial, Helvetica, sans-serif;
          font-weight: 600;
          position: relative;
          top: 2px;
        }
      }
      .desc {
        font-size: 14px;
        color: @text-color-second;
        margin-top: 12px;
        margin-bottom: 40px;
      }
    }
    .login{
      width: 368px;
      margin: 0 auto;
      @media screen and (max-width: 576px) {
        width: 95%;
      }
      @media screen and (max-width: 320px) {
        .captcha-button{
          font-size: 14px;
        }
      }
      .anticon{
        color:#1890ff;
      }
      .icon {
        font-size: 24px;
        color: @text-color-second;
        margin-left: 16px;
        vertical-align: middle;
        cursor: pointer;
        transition: color 0.3s;

        &:hover {
          color: @primary-color;
        }
      }
      .authCode_btn{
        padding-left:4px;
        button{font-size:12px;}
      }
    }
  }
</style>
