import request from '@/request/index'

/** 获取板块（icon组） */
/* 绑定手机号 */
// 获取用户openid

export const getOpenId=data=>request({
	url: '/vpnservice/openid', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
//获取手机验证码
export const getPhoneCode=data=>request({
	url: '/auth/getnote', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 登陆
export const loginAjax=data=>request({
	url: '/vpnservice/login', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

//绑定手机号
export const registbyphone=data=>request({
	url: '/auth/weChatregistbyphone', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 获取用户信息
export const userbasic=data=>request({
	url: '/vpnservice/vpnuserbasic', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 获取公司信息
export const vpncompanyGetbyphonesvc=data=>request({
	url: '/vpnservice/vpncompanyGetbyphonesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
/* 首页 */
// 首页轮播图
export const vpnslideshowGetsvc=data=>request({
	url: '/vpnservice/vpnslideshowGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 获取导航栏接口  
export const vpnnavigationGetsvc=data=>request({
	url: '/vpnservice/vpnnavigationGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 岗位列表查询接口
export const vpnjobGetsvc=data=>request({
	url: '/vpnservice/vpnjobGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 岗位详情
export const vpnjobGetbyidsvc=data=>request({
	url: '/vpnservice/vpnjobGetbyidsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 单个企业详情
export const vpncompanyGetbyidsvc=data=>request({
	url: '/vpnservice/vpncompanyGetbyidsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 收藏岗位
export const vpncollectAddsvc=data=>request({
	url: '/vpnservice/vpncollectAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 检查是否收藏过这个岗位
export const vpniscollectJob=data=>request({
	url: '/vpnservice/vpniscollectJob', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 我要应聘
export const vpndeliverAddsvc=data=>request({
	url: '/vpnservice/vpndeliverAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 攻略列表
export const vpnstrategyGetsvc=data=>request({
	url: '/vpnservice/vpnstrategyGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 攻略详情
export const vpnstrategyGetbyidsvc=data=>request({
	url: '/vpnservice/vpnstrategyGetbyidsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 编辑个人信息也是简历基本信息
export const vpnuserUpdatesvc=data=>request({
	url: '/vpnservice/vpnuserUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 个人简历查询接口
export const vpnuserGetsvc=data=>request({
	url: '/vpnservice/vpnuserGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 分类查询接口
export const vpnqsCategoryGetsvc=data=>request({
	url: '/vpnservice/vpnqsCategoryGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 求职意向新增接口
export const vpnjobWantedAddsvc=data=>request({
	url: '/vpnservice/vpnjobWantedAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 期望职业类型
export const vpnjobcategory2Getsvc=data=>request({
	url: '/vpnservice/vpnjobcategory2Getsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 获取所学专业tree
export const vpnQsCategoryMajorGetsvc=data=>request({
	url: '/vpnservice/vpnQsCategoryMajorGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
//提交教育经历
export const vpneducationExpAddsvc=data=>request({
	url: '/vpnservice/vpneducationExpAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
//教育经历编辑
export const vpneducationExpUpdatesvc=data=>request({
 	url: '/vpnservice/vpneducationExpUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
 	method: 'POST',
 	header: {
 		'Content-Type': 'application/json', // post请求放开
 	},
 	data: data
 })
// 添加工作经验
export const vpnjobExpAddsvc=data=>request({
 	url: '/vpnservice/vpnjobExpAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
 	method: 'POST',
 	header: {
 		'Content-Type': 'application/json', // post请求放开
 	},
 	data: data
 })
 //工作经验修改
 export const vpnjobExpUpdatesvc=data=>request({
  	url: '/vpnservice/vpnjobExpUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
  })
//添加项目经验
export const vpnprojectExpAddsvc=data=>request({
  	url: '/vpnservice/vpnprojectExpAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 编辑项目经验
export const vpnprojectExpUpdatesvc=data=>request({
  	url: '/vpnservice/vpnprojectExpUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 添加语言能力
export const vpnlanguageAddsvc=data=>request({
  	url: '/vpnservice/vpnlanguageAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 编辑语言能力
export const vpnlanguageUpdatesvc=data=>request({
  	url: '/vpnservice/vpnlanguageUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 新增证书
export const vpncertificateAddsvc=data=>request({
  	url: '/vpnservice/vpncertificateAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 投诉建议
export const vpncomplaintAddsvc=data=>request({
  	url: '/vpnservice/vpncomplaintAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 我的应聘列表
export const vpndeliverGetsvc=data=>request({
  	url: '/vpnservice/vpndeliverGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
//我的收藏列表
export const vpncollectGetsvc=data=>request({
  	url: '/vpnservice/vpncollectGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
//收藏列表-企业端
export const vpncompanycollectdevGetsvc=data=>request({
  	url: '/vpnservice/vpncompanycollectdevGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
}) 
//简历-收藏
export const vpncompanycollectdevAddsvc=data=>request({
  	url: '/vpnservice/vpncompanycollectdevAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
}) 
//简历-是否收藏
export const vpncompanycollectdevsvc=data=>request({
  	url: '/vpnservice/vpncompanycollectdevsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
}) 

// 分销者钱包查询
export const vpnWalletGetsvc=data=>request({
  	url: '/vpnservice/vpnWalletGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 分销者提现接口
export const vpnApplicationForWithdrawalAddsvc=data=>request({
  	url: '/vpnservice/vpnApplicationForWithdrawalAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 提现规则
export const vpnwithdrawRuleGet=data=>request({
  	url: '/vpnservice/vpnwithdrawRuleGet', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

// 收支明细收入列表
export const vpnincomeDetailGetsvc=data=>request({
  	url: '/vpnservice/vpnincomeDetailGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 收支明细提现列表
export const vpnApplicationForWithdrawalGetsvc=data=>request({
  	url: '/vpnservice/vpnApplicationForWithdrawalGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

//消息通知列表
export const vpnnewsGetsvc=data=>request({
  	url: '/vpnservice/vpnnewsGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
export const vpnnewsReadsvc=data=>request({
	url: '/vpnservice/vpnnewsReadsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 通知同意或者拒绝
export const vpninviteUpdatesvc=data=>request({
  	url: '/vpnservice/vpninviteUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

// 申请分销者
export const vpndistributionAddsvc=data=>request({
  	url: '/vpnservice/vpndistributionAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 获取职位亮点
export const vpnjobLightenEnumGetsvc=data=>request({
  	url: '/vpnservice/vpnjobLightenEnumGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 岗位新增
export const vpnjobAddsvc=data=>request({
  	url: '/vpnservice/vpnjobAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 岗位修改
export const vpnjobUpdatesvc=data=>request({
  	url: '/vpnservice/vpnjobUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

// 单个工作应聘者的简历list
export const vpncompanydeliverGetsvc=data=>request({
  	url: '/vpnservice/vpncompanydeliverGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})


//我的推荐列表
export const vpnrecommendGetsvc=data=>request({
  	url: '/vpnservice/vpnrecommendGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 我的推荐的进展
export const vpnrecommendDetailGetsvc=data=>request({
  	url: '/vpnservice/vpnrecommendDetailGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})


// 获取qr
export const qrCode=data=>request({
  	url: '/vpnservice/qrCode', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

// 用户协议
export const vpnprotocolGet=data=>request({
  	url: '/vpnservice/vpnprotocolGet', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 新手引导
export const vpnbeginnerGet=data=>request({
  	url: '/vpnservice/vpnbeginnerGet', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 新手引导
export const vpnbeginnerGetById=data=>request({
  	url: '/vpnservice/Beginner/get/'+data.id, // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 分销排行榜
export const vpnbrokerageRankingListsvc=data=>request({
  	url: '/vpnservice/vpnbrokerageRankingListsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 分销到面排行榜
export const vpnInterviewRankingListsvc=data=>request({
  	url: '/vpnservice/vpnInterviewRankingListsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 分销推荐排行榜
export const vpnRecommendRankingList=data=>request({
  	url: '/vpnservice/vpnRecommendRankingListsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 企业新增
export const vpncompanyAddsvc=data=>request({
  	url: '/vpnservice/vpncompanyAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 企业修改接口
export const vpncompanyUpdatesvc=data=>request({
  	url: '/vpnservice/vpncompanyUpdatesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 企业人才进展
export const vpncompanydeliverDetailGetsvc=data=>request({
  	url: '/vpnservice/vpncompanydeliverDetailGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 推荐进展用户信息
export const vpnrecommendUserDetailGetsvc=data=>request({
  	url: '/vpnservice/vpnrecommendUserDetailGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 人才进展用户信息
export const vpncompanydeliverUserDetailGetsvc=data=>request({
  	url: '/vpnservice/vpncompanydeliverUserDetailGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 简历流程新增接口（企业邀请面试，企业已面试，企业不合适，企业入职，企业离职）
export const vpnDeliverDetailAddsvc=data=>request({
  	url: '/vpnservice/vpnDeliverDetailAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 刷新变成已读
export const updateIsread=data=>request({
  	url: '/vpnservice/updateIsread', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})
// 检查是否有新的消息
export const isnewmsg=data=>request({
  	url: '/vpnservice/isnewmsg', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})


// 检查是否有新的消息
export const vpnPointSigntsvc=data=>request({
	url: '/vpnservice/vpnPointSigntsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

export const vpnGetPointsvc=data=>request({
	url: '/vpnservice/vpnGetPointsvc/'+data.userId, // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

//获取积分兑换礼物列表
export const vpngetGiftList=data=>request({
  	url: '/vpnservice/getGiftList', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})

//积分兑换礼物
export const vpnpointToGift=data=>request({
  	url: '/vpnservice/pointToGift', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
  	method: 'POST',
  	header: {
  		'Content-Type': 'application/json', // post请求放开
  	},
  	data: data
})


export const inviteList=data=>request({
	url: '/vpnservice/inviteList/'+data.inviteCode, // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

export const vpnGetPointdetailsvc=data=>request({
	url: '/vpnservice/vpnGetPointdetailsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})



// 我的收藏列表
export const vpncollectRecSavesvc=data=>request({
	url: '/vpnservice/vpncollectRecSavesvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})

// 公告的列表
export const vpnannouncementGetsvc=data=>request({
	url: '/vpnservice/vpnannouncementGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 简历推荐  分销者
export const apiVpnrecommendGetsvc=data=>request({
	url: '/vpnservice/vpnrecommendGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 简历推荐  企业端
export const apiVpncompanydeliverGetsvc=data=>request({
	url: '/vpnservice/vpncompanydeliverGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 离职
export const vpnUserDeliverDetailAddsvc=data=>request({
	url: '/vpnservice/vpnUserDeliverDetailAddsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 取用户简历信息
export const vpncvFileServiceGetsvc=data=>request({
	url: '/vpnservice/vpncvFileServiceGetsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})
// 删除用户简历信息
export const vpncvFileServiceDelsvc=data=>request({
	url: '/vpnservice/vpncvFileServiceDelsvc', // 这种写法在配置baseUrl后可用，也可以直接用完整地址 如 http://xx.xx/test
	method: 'POST',
	header: {
		'Content-Type': 'application/json', // post请求放开
	},
	data: data
})


		














 
 
 
 


