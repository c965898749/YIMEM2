import {LOGIN, GetAuthCode,LoginByPhone,ROUTES,VpnqsCategory,ProvinceList,CityList,CountyList,Department,UserListAll,UserAdd,UserUpdate,UserState,UserDelete,RoleSearch,RoleCreate,RoleUpdate,RoleDelete,RoleAuthorityGet,RoleAuthoritySet,Authority,AuthorityGet,AuthoritySet} from '@/services/api'
import {request, METHOD, removeAuthorization} from '@/utils/request'

/**
 * 登录服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function login(username, password,rememberMe) {
  return request(LOGIN, METHOD.POST, {
    username: username,
    password: password,
    rememberMe:rememberMe
  })
}

export async function getRoutesConfig() {
  return request(ROUTES, METHOD.GET)
}

export async function asyncGetAuthCode(phone) {
  return request(GetAuthCode, METHOD.POST,{
    phone:phone
  })
}

export async function asyncLoginByPhone(phone, authCode,rememberMe) {
  return request(LoginByPhone, METHOD.POST,{
    phone: phone,
    authCode: authCode,
    rememberMe:rememberMe
  })
}

export async function asyncvpnqsCategoryGet(cAlias) {
  return request(VpnqsCategory, METHOD.POST,{
    vpnqsCategoryGet:{
      cAlias:cAlias
    }
  })
}

export async function GetAuthority() {
  return request(Authority, METHOD.POST)
}

export async function asyncAuthorityGet(roleId) {
  return request(AuthorityGet, METHOD.POST,{
    id:roleId
  })
}

export async function asyncAuthoritySet(roleId,menusid) {
  return request(AuthoritySet+'/'+roleId, METHOD.POST,{
    menusid:menusid
  })
}
/**
 * 获取省市县
 * */
export async function getProvinceListAll(provinceCode) {
  return request(ProvinceList, METHOD.POST,{
    vpnRmProvinceGet:{
      provinceCode:provinceCode,
      zhLabel:'',
    }
  })
}

export async function getCityListAll(provinceId) {
  return request(CityList, METHOD.POST,{
    vpnRmCityGet:{
      provinceId:provinceId,
      zhLabel:'',
    }
  })
}

export async function getCountyListAll(cityId) {
  return request(CountyList, METHOD.POST,{
    vpnRmCountyGet:{
      cityId:cityId,
      zhLabel:'',
    }
  })
}
/**
 * 获取部门
 * */
export async function getDepartmentAll() {
  return request(Department, METHOD.GET)
}
export async function DepartmentInfoById(id) {
  return request(Department+'/'+id, METHOD.GET)
}
export async function DepartmentCreate(requests) {
  if(requests.depId){
    return request(Department+'/'+requests.depId, METHOD.PUT,requests)
  }else{
    return request(Department, METHOD.POST,requests)
  }
}

export async function DepartmentDel(id) {
  return request(Department+'/'+id, METHOD.DELETE)
}

/**
 * 获取人员
 * */
export async function getUserlistAll(requests) {
  return request(UserListAll, METHOD.POST, requests)
}

export async function UserCreate(requests) {
  if(requests.id==undefined){
    return request(UserAdd, METHOD.POST, requests)
  }else{
    return request(UserUpdate, METHOD.POST, requests)
  }

}
export async function asyncUserState(requests) {
  return request(UserState, METHOD.POST, requests)
}

export async function asyncUserDelete(requests) {
  return request(UserDelete, METHOD.POST, requests)
}
/**
 * **/
export async function asyncRoleSearch(requests) {
  return request(RoleSearch, METHOD.POST, requests)
}
export async function asyncRoleCreate(requests) {
  if(requests.id==undefined){
    return request(RoleCreate, METHOD.POST, requests)
  }else{
    return request(RoleUpdate, METHOD.POST, requests)
  }

}
export async function asyncRoleDelete(id) {
  return request(RoleDelete+'/'+id, METHOD.POST)
}
export async function asyncRoleAuthorityGet(requests) {
  return request(RoleAuthorityGet, METHOD.POST, requests)
}
export async function asyncRoleAuthoritySet(requests) {
  return request(RoleAuthoritySet, METHOD.POST, requests)
}
/**
 * 退出登录
 */
export function logout() {
  localStorage.removeItem(process.env.VUE_APP_ROUTES_KEY)
  localStorage.removeItem(process.env.VUE_APP_PERMISSIONS_KEY)
  localStorage.removeItem(process.env.VUE_APP_ROLES_KEY)
  removeAuthorization()
}
export default {
  login,
  asyncGetAuthCode,
  asyncLoginByPhone,
  logout,
  asyncvpnqsCategoryGet,
  GetAuthority,
  asyncAuthorityGet,
  asyncAuthoritySet,
  getProvinceListAll,
  getCityListAll,
  getCountyListAll,
  getRoutesConfig,
  getDepartmentAll,
  DepartmentInfoById,
  DepartmentCreate,
  DepartmentDel,
  getUserlistAll,
  UserCreate,
  asyncUserState,
  asyncUserDelete,
  asyncRoleSearch,
  asyncRoleCreate,
  asyncRoleDelete,
  asyncRoleAuthorityGet,
  asyncRoleAuthoritySet
}
