// 视图组件
const view = {
  tabs: () => import('@/layouts/tabs'),
  blank: () => import('@/layouts/BlankView'),
  page: () => import('@/layouts/PageView')
}

// 路由组件注册
const routerMap = {
  login: {
    path: 'login',
    name: '登录页',
    component: () => import('@/pages/login')
  },
  root: {
    path: '/',
    name: '一梦网',
    component: view.tabs
  },
  index: {
    path: 'index',
    invisible: true,
    name: '首页',
    component: () => import('@/pages/index')
  },
  BD: {
    path: 'dashboard',
    name: '基础数据',
    icon: 'dashboard',
    component: view.page
  },
  BD_LB: {
    name: '首页轮播图',
    path: 'banner',
    component: () => import('@/pages/dashboard/banner'),
    page: {
      title: '轮播图管理',
      breadcrumb: ['基础数据', '轮播图管理']
    },
    // button:['del','add']
  },
  BD_NV: {
    name: '导航栏维护',
    path: 'navigation',
    component: () => import('@/pages/dashboard/navigation'),
    page: {
      title: '导航栏维护',
      breadcrumb: ['基础数据', '导航栏维护']
    },
    // button:['del','add','edit']
  },
  BD_NU: {
    path: '/dashboard/novice/list',
    name: '新手引导',
    component: view.blank,
  },
  BD_NU_LIST: {
    path: '/dashboard/novice/list',
    name: '新手引导',
    invisible: true,
    component: () => import('@/pages/dashboard/novice'),
    page: {
      title: '基础数据',
      breadcrumb: ['基础数据','新手引导']
    }
  },
  BD_NU_CX: {
    name: '新手引导详情',
    path: '/dashboard/novice/detail',
    invisible: true,
    component: () => import('@/pages/dashboard/novicedetail'),
    page: {
      title: '基础数据',
      breadcrumb: ['基础数据','新手引导', '新手引导详情']
    }
  },
  BD_NU_XZ: {
    name: '新增新手引导',
    path: '/dashboard/novice/add',
    invisible: true,
    component: () => import('@/pages/dashboard/noviceadd'),
    page: {
      title: '基础数据',
      breadcrumb: ['基础数据','新手引导', '新增新手引导']
    }
  },
  BD_NU_XG: {
    name: '编辑新手引导',
    path: '/dashboard/novice/edit',
    invisible: true,
    component: () => import('@/pages/dashboard/noviceadd'),
    page: {
      title: '基础数据',
      breadcrumb: ['基础数据','新手引导', '编辑新手引导']
    }
  },
  BD_GM: {
    name: '客服管理',
    path: 'gm',
    component: () => import('@/pages/dashboard/kefu'),
    page: {
      title: '客服管理',
      breadcrumb: ['基础数据','客服管理']
    }
  },
  BD_XY: {
    path: '/dashboard/agreement/list',
    name: '用户协议',
    component: view.blank,
  },
  BD_XY_LIST: {
    name: '用户协议',
    path: '/dashboard/agreement/list',
    invisible: true,
    component: () => import('@/pages/dashboard/agreement'),
    page: {
      title: '用户协议',
      breadcrumb: ['基础数据','用户协议']
    }
  },
  BD_XY_CX: {
    name: '用户协议详情',
    path: '/dashboard/agreement/detail',
    invisible: true,
    component: () => import('@/pages/dashboard/agreementdetail'),
    page: {
      title: '用户协议详情',
      breadcrumb: ['基础数据', '用户协议','用户协议详情']
    }
  },
  BD_XY_XZ: {
    name: '新增用户协议',
    path: '/dashboard/agreement/add',
    invisible: true,
    component: () => import('@/pages/dashboard/agreementadd'),
    page: {
      title: '新增用户协议',
      breadcrumb: ['基础数据', '用户协议','新增用户协议']
    }
  },
  BD_XY_XG: {
    name: '编辑用户协议',
    path: '/dashboard/agreement/edit',
    invisible: true,
    component: () => import('@/pages/dashboard/agreementadd'),
    page: {
      title: '编辑用户协议',
      breadcrumb: ['基础数据', '用户协议','编辑用户协议']
    }
  },
  BD_TX: {
    path: '/dashboard/withdrawalrule/list',
    name: '提现规则',
    component: view.blank,
  },
  BD_TX_LIST: {
    name: '提现规则',
    path: '/dashboard/withdrawalrule/list',
    invisible: true,
    component: () => import('@/pages/dashboard/withdrawalrule'),
    page: {
      title: '提现规则',
      breadcrumb: ['基础数据', '提现规则']
    }
  },
  BD_TX_CX: {
    name: '提现规则详情',
    path: '/dashboard/withdrawalrule/detail',
    invisible: true,
    component: () => import('@/pages/dashboard/withdrawalruledetail'),
    page: {
      title: '提现规则',
      breadcrumb: ['基础数据', '提现规则','提现规则详情']
    }
  },
  BD_TX_XZ: {
    name: '新增提现规则',
    path: '/dashboard/withdrawalrule/add',
    invisible: true,
    component: () => import('@/pages/dashboard/withdrawalruleadd'),
    page: {
      title: '新增提现规则',
      breadcrumb: ['基础数据', '提现规则','新增提现规则']
    }
  },
  BD_TX_XG: {
    name: '编辑提现规则',
    path: '/dashboard/withdrawalrule/edit',
    invisible: true,
    component: () => import('@/pages/dashboard/withdrawalruleadd'),
    page: {
      title: '编辑提现规则',
      breadcrumb: ['基础数据', '提现规则','编辑提现规则']
    }
  },
  BD_LW: {
    name: '礼物管理',
    path: 'gift',
    component: () => import('@/pages/dashboard/gift'),
    page: {
      title: '礼物管理',
      breadcrumb: ['基础数据', '礼物管理']
    },
    //button:['del','add','edit']
  },
  PM: {
    name: '职位管理',
    path: 'position',
    icon: 'file-done',
    component: view.page
  },
  PM_LX:{
    name: '职位类型',
    path: 'classtype',
    component: () => import('@/pages/position/classtype'),
    page: {
      title: '职位类型',
      breadcrumb: ['职位管理', '职位类型']
    }
  },
  PM_LD:{
    name: '职位亮点',
    path: 'highlights',
    component: () => import('@/pages/position/highlights'),
    page: {
      title: '职位亮点',
      breadcrumb: ['职位管理', '职位亮点']
    }
  },
  PM_LB:{
    path: '/job/list',
    name: '职位列表',
    component: view.blank,
  },
  PM_LB_LIST:{
    name: '职位列表',
    path: '/job/list',
    invisible: true,
    component: () => import('@/pages/position/joblist'),
    page: {
      title: '职位列表',
      breadcrumb: ['职位管理', '职位列表']
    }
  },
  PM_LB_CX: {
    name: '职位详情',
    path: '/job/detail',
    invisible: true,
    component: () => import('@/pages/position/jobdetail'),
    page: {
      title: '职位详情',
      breadcrumb: ['职位管理','职位列表', '职位详情']
    }
  },
  PM_LB_XZ: {
    name: '新增职位',
    path: '/job/add',
    invisible: true,
    component: () => import('@/pages/position/jobadd'),
    page: {
      title: '新增职位',
      breadcrumb: ['职位管理','职位列表', '新增职位']
    }
  },
  PM_LB_GX: {
    name: '编辑职位',
    path: '/job/edit',
    invisible: true,
    component: () => import('@/pages/position/jobadd'),
    page: {
      title: '编辑职位',
      breadcrumb: ['职位管理','职位列表', '编辑职位']
    }
  },
  PM_LB_JLLIST: {
    name: '人才简历详情',
    path: '/job/personneldetail',
    invisible: true,
    component: () => import('@/pages/position/personneldetail'),
    page: {
      title: '人才简历详情',
      breadcrumb: ['职位管理','职位列表', '职位详情','人才进度','人才简历详情']
    }
  },
  PM_LB_JLCX: {
    name: '人才进度',
    path: '/job/personnelspeed',
    invisible: true,
    component: () => import('@/pages/position/personnelspeed'),
    page: {
      title: false,
      breadcrumb: ['职位管理','职位列表', '职位详情','人才进度']
    }
  },
  RM: {
    name: '报名管理',
    path: 'signup',
    icon: 'form',
    component: view.page
  },
  RM_ZJ:{
    name: '直接报名',
    path: 'bm',
    component: () => import('@/pages/signup/bm'),
    page: {
      title: '直接报名',
      breadcrumb: ['报名管理','直接报名']
    }
  },
  RM_TJ:{
    name: '推荐报名',
    path: 'recommend',
    component: () => import('@/pages/signup/recommend'),
    page: {
      title: '推荐报名',
      breadcrumb: ['报名管理','推荐报名']
    }
  },
  IM: {
    name: '攻略管理',
    icon: 'container',
    path: 'strategy',
    component: view.page
  },
  IM_LB:{
    path: '/strategy/list',
    name: '攻略列表',
    component: view.blank,
  },
  IM_LB_LIST:{
    name: '攻略列表',
    path: '/strategy/list',
    invisible: true,
    component: () => import('@/pages/strategy/querylist'),
    page: {
      title: '攻略列表',
      breadcrumb: ['攻略管理','攻略列表']
    }
  },
  IM_LB_CX: {
    name: '攻略详情',
    path: '/strategy/detail',
    invisible: true,
    component: () => import('@/pages/strategy/strategydetail'),
    page: {
      title: '攻略详情',
      breadcrumb: ['攻略管理', '攻略详情']
    }
  },
  IM_LB_XZ: {
    name: '新增攻略',
    path: '/strategy/add',
    invisible: true,
    component: () => import('@/pages/strategy/strategyadd'),
    page: {
      title: '新增攻略',
      breadcrumb: ['攻略管理', '新增攻略']
    }
  },
  IM_LB_XG: {
    name: '编辑攻略',
    path: '/strategy/edit',
    invisible: true,
    component: () => import('@/pages/strategy/strategyadd'),
    page: {
      title: '编辑攻略',
      breadcrumb: ['攻略管理', '编辑攻略']
    }
  },
  CM: {
    name: '佣金管理',
    icon: 'transaction',
    path: 'commission',
    component: view.page
  },
  CM_YF_LB:{
    name: '已发放列表',
    path: '/issued/list',
    component: view.blank,
  },
  CM_YFLB_CK:{
    name: '已发放列表',
    path: '/issued/list',
    invisible: true,
    component: () => import('@/pages/commission/querylist'),
    page: {
      title: '已发放列表',
      breadcrumb: ['佣金管理', '已发放列表']
    }
  },
  CM_YFLB_CZ:{
    name: '已发放详情',
    path: '/issued/detail',
    invisible: true,
    component: () => import('@/pages/commission/querydetail'),
    page: {
      title: '已发放详情',
      breadcrumb: ['佣金管理', '已发放列表','发放详情']
    }
  },
  CM_TX:{
    name: '提现审核',
    path: 'audit',
    component: () => import('@/pages/commission/auditlist'),
    page: {
      title: '提现审核',
      breadcrumb: ['佣金管理', '提现审核']
    }
  },
  CM_SR:{
    name: '收入管理',
    path: '/income/list',
    component: view.blank,
  },
  CM_SR_LB:{
    name: '收入管理',
    path: '/income/list',
    invisible: true,
    component: () => import('@/pages/commission/incomelist'),
    page: {
      title: '收入管理',
      breadcrumb: ['佣金管理', '收入管理']
    }
  },
  CM_SR_CK:{
    name: '收入管理详情',
    path: '/income/detail',
    invisible: true,
    component: () => import('@/pages/commission/incomedetail'),
    page: {
      title: false,
      breadcrumb: ['佣金管理', '收入管理','收入管理详情']
    }
  },
  MM: {
    name: '会员管理',
    path: 'member',
    icon: 'user',
    component: view.page
  },
  MM_QY:{
    path: '/qy/list',
    name: '企业会员',
    component: view.blank,
  },
  MM_QY_LIST:{
    name: '企业会员',
    path: '/qy/list',
    invisible: true,
    component: () => import('@/pages/member/company'),
    page: {
      title: '企业会员',
      breadcrumb: ['会员管理', '企业会员']
    }
  },
  MM_QY_XZ  : {
    name: '新增企业会员',
    path: '/qy/add',
    invisible: true,
    component: () => import('@/pages/member/companyuseradd'),
    page: {
      title: '新增企业会员',
      breadcrumb: ['会员管理', '新增企业会员']
    }
  },
  MM_GR:{
    name: '个人会员',
    path: 'personal',
    component: () => import('@/pages/member/personal'),
    page: {
      title: '企业会员',
      breadcrumb: ['会员管理', '个人会员']
    }
  },
  AM: {
    name: '授权管理',
    path: 'authorization',
    icon: 'lock',
    component: view.page
  },
  AM_RY:{
    name: '人员管理',
    path: 'user',
    component: () => import('@/pages/authorization/userlist'),
    page: {
      title: '人员管理',
      breadcrumb: ['授权管理', '人员管理']
    }
  },
  AM_JS:{
    name: '角色管理',
    path: 'role',
    component: () => import('@/pages/authorization/rolelist'),
    page: {
      title: '角色管理',
      breadcrumb: ['授权管理', '角色管理']
    }
  },
  TM: {
    name: '投诉管理',
    path: 'complain',
    icon: 'exclamation-circle',
    component: view.page
  },
  AM_LB:{
    name: '投诉列表',
    path: 'list',
    component: () => import('@/pages/other/complaint'),
    page: {
      title: '投诉列表',
      breadcrumb: ['投诉管理', '投诉列表']
    }
  },
  exp403: {
    authority: '*',
    name: 'exp403',
    path: '403',
    component: () => import('@/pages/exception/403')
  },
  exp404: {
    name: 'exp404',
    path: '404',
    component: () => import('@/pages/exception/404')
  },
  exp500: {
    name: 'exp500',
    path: '500',
    component: () => import('@/pages/exception/500')
  },
}
export default routerMap

