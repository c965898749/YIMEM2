import {BannerAll,BannerUpload,BannerDel,NavigationAll,NavigationAdd,NavigationEdit,NavigationDel,GiftAll,GiftAdd,GiftEdit,GiftDel,BeginnerGet,BeginnerAdd,BeginnerUpdate,BeginnerDel,BeginnerDetail,customerGet,customerAdd,customerUpdate,customerDel,protocolGet,protocolAdd,protocolUpdate,protocolDel,protocolDetail,withdrawRuleGet,withdrawRuleAdd,withdrawRuleUpdate,withdrawRuleDel,withdrawRuleDetail} from '@/services/api'
import {request, METHOD} from '@/utils/request'

/**
 轮播图
 */
export async function getBannerlist(toUser) {
  return request(BannerAll, METHOD.POST, {
    vpnslideshowGet: {toUser:toUser}
  })
}

export async function asyncBannerUpload(toUser,requests) {
  return request(BannerUpload, METHOD.POST, {
    vpnslideshowGet: {
      toUser:toUser,
      pic:requests
    }
  })
}

export async function asyncBannerDel(ssId) {
  return request(BannerDel, METHOD.POST, {
    vpnslideshowGet: {ssId :ssId}
  })
}

/**
 * 导航栏
 */
export async function getNavigationAll(requests) {
  return request(NavigationAll, METHOD.POST, requests)
}

export async function asyncNavigationAdd(mode,requests) {
  if(mode==1){
    return request(NavigationAdd, METHOD.POST, requests)
  }else{
    return request(NavigationEdit, METHOD.POST, requests)
  }
}

export async function asyncNavigationDel(requests) {
  return request(NavigationDel, METHOD.POST,requests)
}

/**
 *新手引导
 */
export async function asyncBeginnerGet(requests) {
  return request(BeginnerGet, METHOD.POST, requests)
}

export async function asyncBeginnerAdd(mode,requests) {
  if(mode=='add'){
    return request(BeginnerAdd, METHOD.POST, requests)
  }else{
    return request(BeginnerUpdate, METHOD.POST, requests)
  }
}

export async function asyncBeginnerDel(beId) {
  return request(BeginnerDel, METHOD.POST, {
    vpnbeginnerDel:{
      beId:beId
    }

  })
}

export async function asyncBeginnerDetail(id) {
  return request(BeginnerDetail+'/'+id, METHOD.POST)
}
/**
 *客服
 */
export async function asynccustomerGet(requests) {
  return request(customerGet, METHOD.POST, requests)
}

export async function asynccustomerAdd(mode,requests) {
  if(mode=='add'){
    return request(customerAdd, METHOD.POST, requests)
  }else{
    return request(customerUpdate, METHOD.POST, requests)
  }
}

export async function asynccustomerDel(requests) {
  return request(customerDel, METHOD.POST,requests)
}

/**
 *协议
 */
export async function asyncprotocolGet(requests) {
  return request(protocolGet, METHOD.POST, requests)
}

export async function asyncprotocolAdd(mode,requests) {
  if(mode=='add'){
    return request(protocolAdd, METHOD.POST, requests)
  }else{
    return request(protocolUpdate, METHOD.POST, requests)
  }
}

export async function asyncprotocolDel(prId) {
  return request(protocolDel, METHOD.POST, {
    vpnprotocolDel:{
      prId:prId
    }
  })
}

export async function asyncprotocolDetail(id) {
  return request(protocolDetail+'/'+id, METHOD.POST)
}
/**
 *提现
 */
export async function asyncwithdrawRuleGet(requests) {
  return request(withdrawRuleGet, METHOD.POST, requests)
}

export async function asyncwithdrawRuleAdd(mode,requests) {
  if(mode=='add'){
    return request(withdrawRuleAdd, METHOD.POST, requests)
  }else{
    return request(withdrawRuleUpdate, METHOD.POST, requests)
  }
}

export async function asyncwithdrawRuleDel(wiId) {
  return request(withdrawRuleDel, METHOD.POST, {
    vpnwithdrawRuleDel:{
      wiId:wiId
    }
  })
}

export async function asyncwithdrawRuleDetail(id) {
  return request(withdrawRuleDetail+'/'+id, METHOD.POST)
}

/**
 * 礼物
 */
export async function getGiftAll(requests) {
  return request(GiftAll, METHOD.POST, requests)
}

export async function asyncGiftAdd(mode,requests) {
  if(mode==1){
    return request(GiftAdd, METHOD.POST, requests)
  }else{
    return request(GiftEdit, METHOD.POST, requests)
  }
}

export async function asyncGiftDel(requests) {
  return request(GiftDel, METHOD.POST,requests)
}
/**
 *
 */

export default {
  getBannerlist,
  asyncBannerUpload,
  asyncBannerDel,
  getNavigationAll,
  asyncNavigationAdd,
  asyncNavigationDel,
  getGiftAll,
  asyncGiftAdd,
  asyncGiftDel,
  asyncBeginnerGet,
  asyncBeginnerAdd,
  asyncBeginnerDel,
  asyncBeginnerDetail,
  asynccustomerGet,
  asynccustomerAdd,
  asynccustomerDel,
  asyncprotocolGet,
  asyncprotocolAdd,
  asyncprotocolDel,
  asyncprotocolDetail,
  asyncwithdrawRuleGet,
  asyncwithdrawRuleAdd,
  asyncwithdrawRuleDel,
  asyncwithdrawRuleDetail
}
