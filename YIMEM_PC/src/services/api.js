//跨域代理前缀
// const API_PROXY_PREFIX='/api'
// const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX
//const BASE_URL = process.env.VUE_APP_API_BASE_URL
// const BASE_URL = 'http://192.168.2.128:1206'
const BASE_URL = 'http://localhost:1206'
// const BASE_URL = 'http://192.168.0.105:1206'
// const BASE_URL = 'http://192.168.43.79:1206'

module.exports = {
  BASE_URL:`${BASE_URL}`,
  LOGIN:`${BASE_URL}/auth/login`,
  GetAuthCode:`${BASE_URL}/auth/getnote`,
  LoginByPhone:`${BASE_URL}/auth/loginbyphone`,
  ROUTES:`${BASE_URL}/admin/authority/menu`,
  VpnqsCategory:`${BASE_URL}/vpnservice/vpnqsCategoryGetsvc`,
  Authority:`${BASE_URL}/admin/authority`,
  AuthorityGet:`${BASE_URL}/admin/role/authority/get`,
  AuthoritySet:`${BASE_URL}/admin/role/authority/set`,

  ProvinceList:`${BASE_URL}/vpnservice/vpnRmProvinceGetsvc`,
  CityList:`${BASE_URL}/vpnservice/vpnRmCityGetsvc`,
  CountyList:`${BASE_URL}/vpnservice/vpnRmCountyGetsvc`,

  imgposturl:`${BASE_URL}/vpnservice/upload`,

  Department:`${BASE_URL}/admin/department`,
  UserListAll:`${BASE_URL}/admin/staff/search`,
  UserAdd:`${BASE_URL}/admin/staff/create`,
  UserUpdate:`${BASE_URL}/admin/staff/update`,
  UserState:`${BASE_URL}/admin/staff/state`,
  UserDelete:`${BASE_URL}/admin/staff/delete`,
  RoleSearch:`${BASE_URL}/admin/role/search`,
  RoleCreate:`${BASE_URL}/admin/role/create`,
  RoleUpdate:`${BASE_URL}/admin/role/update`,
  RoleDelete:`${BASE_URL}/admin/role/delete/`,
  RoleAuthorityGet:`${BASE_URL}/admin/role/authority/get`,
  RoleAuthoritySet:`${BASE_URL}/admin/role/authority/set`,

  BannerAll:`${BASE_URL}/admin/Slideshow/get`,
  BannerUpload:`${BASE_URL}/admin/Slideshow/upload`,
  BannerDel:`${BASE_URL}/admin/Slideshow/del`,

  NavigationAll:`${BASE_URL}/admin/Navigation/get`,
  NavigationAdd:`${BASE_URL}/admin/Navigation/add`,
  NavigationEdit:`${BASE_URL}/admin/Navigation/update`,
  NavigationDel:`${BASE_URL}/admin/Navigation/del`,

  GiftAll:`${BASE_URL}/admin/gift/getList`,
  GiftAdd:`${BASE_URL}/admin/gift/updateGift`,
  GiftEdit:`${BASE_URL}/admin/gift/updateGift`,
  GiftDel:`${BASE_URL}/admin/gift/delGift`,

  BeginnerGet:`${BASE_URL}/admin/Beginner/get`,
  BeginnerAdd:`${BASE_URL}/admin/Beginner/add`,
  BeginnerUpdate:`${BASE_URL}/admin/Beginner/update`,
  BeginnerDel:`${BASE_URL}/admin/Beginner/del`,
  BeginnerDetail:`${BASE_URL}/vpnservice/Beginner/get`,

  customerGet:`${BASE_URL}/admin/customer/get`,
  customerAdd:`${BASE_URL}/admin/customer/add`,
  customerUpdate:`${BASE_URL}/admin/customer/update`,
  customerDel:`${BASE_URL}/admin/customer/del`,

  protocolGet:`${BASE_URL}/admin/protocol/get`,
  protocolAdd:`${BASE_URL}/admin/protocol/add`,
  protocolUpdate:`${BASE_URL}/admin/protocol/update`,
  protocolDel:`${BASE_URL}/admin/protocol/del`,
  protocolDetail:`${BASE_URL}/vpnservice/protocol/get`,

  withdrawRuleGet:`${BASE_URL}/admin/withdrawRule/get`,
  withdrawRuleAdd:`${BASE_URL}/admin/withdrawRule/add`,
  withdrawRuleUpdate:`${BASE_URL}/admin/withdrawRule/update`,
  withdrawRuleDel:`${BASE_URL}/admin/withdrawRule/del`,
  withdrawRuleDetail:`${BASE_URL}/vpnservice/withdrawRule/get`,

  vpnjobcategoryGet:`${BASE_URL}/admin/vpnjobcategoryGetsvc`,
  vpnjobcategoryAdd:`${BASE_URL}/admin/vpnjobcategoryAddsvc`,
  vpnjobcategoryUpdate:`${BASE_URL}/admin/vpnjobcategoryUpdatesvc`,
  vpnjobcategoryDel:`${BASE_URL}/admin/vpnjobcategoryDelsvc`,
  vpnqsCategoryJobsGet:`${BASE_URL}/admin/vpnqsCategoryJobsGetsvc`,

  vpnjobLightenEnumGetAll:`${BASE_URL}/vpnservice/vpnjobLightenEnumGetsvc`,
  vpnjobLightenEnumGet:`${BASE_URL}/admin/vpnjobLightenEnumGetsvc`,
  vpnjobLightenEnumAdd:`${BASE_URL}/admin/vpnjobLightenEnumAddsvc`,
  vpnjobLightenEnumUpdate:`${BASE_URL}/admin/vpnjobLightenEnumUpdatesvc`,
  vpnjobLightenEnumDel:`${BASE_URL}/admin/vpnjobLightenEnumDelsvc`,
  vpnDeliverLeaveAudit:`${BASE_URL}/admin/vpnDeliverLeaveAudit`,//确认离职 及仍在职

  vpnjobGet:`${BASE_URL}/admin/vpnjobGetsvc`,
  vpnjobAdd:`${BASE_URL}/admin/vpnjobAddsvc`,
  vpnjobUpdate:`${BASE_URL}/admin/vpnjobUpdatesvc`,
  jobUpdateState:`${BASE_URL}/admin/vpnjobUpdateStatesvc`,
  vpnjobDel:`${BASE_URL}/admin/vpnjobDelsvc`,
  vpndeliverGet:`${BASE_URL}/vpnservice/vpndeliverGetsvc`,
  vpndeliverDetail:`${BASE_URL}/admin/vpndeliverGetsvc`,

  vpnuserbasicDeli:`${BASE_URL}/vpnservice/vpnuserbasicDeli`,
  vpnuserGet:`${BASE_URL}/admin/vpnuserGetsvc`,
  vpnjobWantedGet:`${BASE_URL}/vpnservice/vpnjobWantedGetsvc`,
  vpnDeliverDetailAdd:`${BASE_URL}/admin/vpnDeliverDetailAddsvc`,


  vpncompanyGet:`${BASE_URL}/admin/vpncompanyGetsvc`,
  vpncompanyAdd:`${BASE_URL}/admin/vpncompanyAddsvc`,
  vpncompanyUpdate:`${BASE_URL}/admin/vpncompanyUpdatesvc`,

  vpnjobhunterGet:`${BASE_URL}/admin/vpnjobhunterGetsvc`,
  vpndistributionGet:`${BASE_URL}/admin/vpndistributionGetsvc`,
  vpndistributionUpdate:`${BASE_URL}/admin/vpndistributionUpdatesvc`,

  vpnstrategyGetsvc:`${BASE_URL}/vpnservice/vpnstrategyGetsvc`,
  vpnstrategyAddsvc:`${BASE_URL}/admin/vpnstrategyAddsvc`,
  vpnstrategyUpdatesvc:`${BASE_URL}/admin/vpnstrategyUpdatesvc`,
  vpnstrategyDelsvc:`${BASE_URL}/admin/vpnstrategyDelsvc`,
  vpnstrategyState:`${BASE_URL}/admin/vpnstrategyState`,

  vpncomplaintGet:`${BASE_URL}/admin/vpncomplaintGetsvc`,
  vpncomplaintDel:`${BASE_URL}/admin/vpncomplaintDelsvc`,

  ZJQYdeliverGet:`${BASE_URL}/admin/ZJQYdeliverGetsvc`,
  ZJWTdeliverGet:`${BASE_URL}/admin/ZJWTdeliverGetsvc`,
  TJQYdeliverGet:`${BASE_URL}/admin/TJQYdeliverGetsvc`,
  TJWTdeliverGet:`${BASE_URL}/admin/TJWTdeliverGetsvc`,

  IncomeGet:`${BASE_URL}/admin/financial/vpnIncomeGetsvc`,
  incomeDetailGet:`${BASE_URL}/admin/financial/vpnincomeDetailGetsvc`,
  CompanyBillGet:`${BASE_URL}/admin/financial/vpnCompanyBillGetsvc`,
  CompanyBillUpdate:`${BASE_URL}/admin/financial/vpnCompanyBillUpdatesvc`,
  companyGet:`${BASE_URL}/admin/vpncompanyGetsvc`,
  CompanyBillDetail:`${BASE_URL}/admin/financial/vpnCompanyBillDetailGetsvc`,
  ApplicationForWithdrawal:`${BASE_URL}/admin/financial/vpnApplicationForWithdrawalGetsvc`,
  ApplicationForWithdrawalUpdate:`${BASE_URL}/admin/financial/vpnApplicationForWithdrawalUpdatesvc`,
}
