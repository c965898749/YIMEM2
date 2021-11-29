<template>
	<view>
		<u-navbar :title="title" :is-back="true"></u-navbar>
		<view :class="{inputFocus:isfocus,inputBlur:!isfocus}">
			<view>
				<input v-model="value" @focus="focus" @blur = "blur"/>
			</view>
		</view>
		
		<view class="btnRow">
			<view class="btnOut" @click="save">
				保存修改
			</view>
		</view>
	</view>
</template>

<script>
	import {vpnuserUpdatesvc} from "../../api/index.js"
	// import {checkIDCard} from "../../api/util.js"
	export default {
		data() {
			return {
				userInfo:{},
				title:'',
				value:'',
				option:'',
				isfocus:false
			}
		},
		onLoad(options)  {
			this.userInfo=getApp().globalData.userInfo;
			let option = options.option;
			console.log(option)
			if(option==="userName"){
				this.title = "修改姓名"
				this.value = this.userInfo.userName
			}else if(option==="phoneNum"){
				this.title = "修改手机号" 	
				this.value = this.userInfo.phoneNum
				
			}else{
				this.title = "修改身份证号" 
				this.value = this.userInfo.identityNum
					
			}
			this.option = option
		},
		methods: {
			save(){
				let data={userId:this.userInfo.userId}
				if(this.value==null||this.value=='')
				{
					this.showToast("修改内容为空")
					return;
				}
				if(this.option==="userName"){
					
					data.userName=this.value
					
				}else if(this.option==="phoneNum"){
					//校验手机号码
					//需要添加 手机验证码 模块 
					data.phoneNum=this.value
				}else{
					//校验身份证号
					/* if(!checkIDCard(this.value)){
						this.showToast("请输入正确的身份证号")
						return;
					} */
					data.identityNum=this.value
				}
				let that=this;
				
				vpnuserUpdatesvc({vpnuserUpdate:data}).then(res=>{
					if(res.code==1){
						if(that.option==="userName"){
							getApp().globalData.userInfo.userName=that.value
						}else if(that.option==="phoneNum"){
							//校验手机号码
							getApp().globalData.userInfo.phoneNum=that.value
						}else{
							//校验身份证号
							getApp().globalData.userInfo.identityNum=that.value
						}
						that.showToast("修改成功")
						uni.navigateBack({
						    delta: 1
						});	
					}else{
						that.showToast(res.msg)
					}
				})
			},
			showToast(title){
				uni.showToast({
				    title: title,
				    duration: 1500,
				    icon: 'none'
				})
			},
			
			focus(){
				this.isfocus=true;
				console.log(this.isfocus)
				
			},
			blur(){
				this.isfocus=false;
				console.log(this.isfocus)
				
			}
		}
	}
</script>

<style scoped lang="less">
.btnRow {
		margin-top: 30rpx;
	
		.btnOut {
			margin: 0 auto;
			width: 690rpx;
			height: 80rpx;
			background: linear-gradient(0deg, #2E73FF, #4DA2FF);
			box-shadow: 0rpx 8rpx 23rpx 1rpx rgba(51, 122, 255, 0.59);
			border-radius: 40rpx;
			font-size: 30rpx;
			line-height: 80rpx;
			color: #FFFFFF;
			text-align: center;
		}
	}
	.inputFocus,.inputBlur{
		
		padding: 80rpx 30rpx 0rpx 30rpx;
		margin-bottom: 50rpx;
		view{
			padding-bottom: 30rpx;
		}
		input{
			font-family: PingFang SC;
			color: #1B1B1B;
		}
	}
	
	.inputFocus{
		view{
			border-bottom: 1rpx #2E73FF solid;
			
		}
	}
	
	.inputBlur{
		view{
			border-bottom: 1rpx #E7E7E7 solid;
		}
	}
</style>
