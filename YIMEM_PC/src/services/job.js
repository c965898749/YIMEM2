import {vpnDeliverLeaveAudit,vpnjobcategoryGet,vpnjobcategoryAdd,vpnjobcategoryUpdate,vpnjobcategoryDel,vpnqsCategoryJobsGet,vpnjobLightenEnumGetAll,vpnjobLightenEnumGet,vpnjobLightenEnumAdd,vpnjobLightenEnumUpdate,vpnjobLightenEnumDel,vpnjobGet,vpnjobAdd,vpnjobUpdate,jobUpdateState,vpnjobDel,vpndeliverGet,vpndeliverDetail,vpnuserGet,vpnjobWantedGet,vpnDeliverDetailAdd,vpnuserbasicDeli,ZJQYdeliverGet,ZJWTdeliverGet,TJQYdeliverGet,TJWTdeliverGet} from '@/services/api'
import {request, METHOD} from '@/utils/request'

/**
 职位类别企佣收费
 */
export async function vpnjobcategoryGetList(requests) {
  return request(vpnjobcategoryGet, METHOD.POST, requests)
}

export async function asyncvpnjobcategoryAdd(mode,requests) {
  if(mode=='add'){
    return request(vpnjobcategoryAdd, METHOD.POST, requests)
  }else{
    return request(vpnjobcategoryUpdate, METHOD.POST, requests)
  }
}

export async function asyncvpnjobcategoryDel(Id) {
  return request(vpnjobcategoryDel, METHOD.POST, {
    vpnjobcategoryDel: {jobCateId :Id}
  })
}

export async function asyncvpnqsCategoryJobsGet() {
  return request(vpnqsCategoryJobsGet, METHOD.POST)
}

/**
 职位亮点
 */
export async function vpnjobLightenEnumGetAllList() {
  return request(vpnjobLightenEnumGetAll, METHOD.POST)
}

export async function vpnjobLightenEnumGetList(requests) {
  return request(vpnjobLightenEnumGet, METHOD.POST, requests)
}

export async function asyncvpnjobLightenEnumAdd(mode,requests) {
  if(mode=='add'){
    return request(vpnjobLightenEnumAdd, METHOD.POST, requests)
  }else{
    return request(vpnjobLightenEnumUpdate, METHOD.POST, requests)
  }
}

export async function asyncvpnjobLightenEnumDel(Id) {
  return request(vpnjobLightenEnumDel, METHOD.POST, {
    vpnjobLightenEnumDel: {jobLightenId :Id}
  })
}
/**
 职位
 */
export async function vpnjobGetList(requests) {
  return request(vpnjobGet, METHOD.POST, requests)
}

export async function asyncvpnjobAdd(mode,requests) {
  if(mode=='add'){
    return request(vpnjobAdd, METHOD.POST, requests)
  }else{
    return request(vpnjobUpdate, METHOD.POST, requests)
  }
}

export async function asyncjobUpdateState(requests) {
  return request(jobUpdateState, METHOD.POST, requests)
}

export async function asyncvpnjobDel(Id) {
  return request(vpnjobDel, METHOD.POST, {
    vpnjobDel: {jobId :Id}
  })
}

export async function asynvpnjobDetail(jobId) {
  return request(vpnjobGet+'/'+jobId, METHOD.POST)
}

export async function asyncvpndeliverGet(requests) {
  return request(vpndeliverGet, METHOD.POST,requests)
}


export async function asyncvpnuserbasicDeli(devId) {
  return request(vpnuserbasicDeli+'/'+devId, METHOD.POST)
}

export async function asyncvpndeliverDetail(devId) {
  return request(vpndeliverDetail+'/'+devId, METHOD.POST)
}

export async function asyncvpndeliverDetailNo(requests) {
  return request(vpndeliverDetail, METHOD.POST,requests)
}


export async function asyncvpnuserGet(userId) {
  return request(vpnuserGet, METHOD.POST,{
    vpnuserGet:{userId:userId}
  })
}

export async function asyncvpnjobWantedGet() {
  return request(vpnjobWantedGet, METHOD.POST)
}

export async function asyncvpnDeliverDetailAdd(requests) {
  return request(vpnDeliverDetailAdd, METHOD.POST, {
    vpnDeliverDetailAdd:requests
  })
}

export async function GetZJQYdeliver(requests) {
  return request(ZJQYdeliverGet, METHOD.POST,requests)
}

export async function GetZJWTdeliver(requests) {
  return request(ZJWTdeliverGet, METHOD.POST,requests)
}

export async function GetTJQYdeliver(requests) {
  return request(TJQYdeliverGet, METHOD.POST,requests)
}

export async function GetTJWTdeliver(requests) {
  return request(TJWTdeliverGet, METHOD.POST,requests)
}
export async function VpnDeliverLeaveAudit(requests) {
  return request(vpnDeliverLeaveAudit, METHOD.POST,requests)
}



export default {
  VpnDeliverLeaveAudit,
  vpnjobcategoryGetList,
  asyncvpnjobcategoryAdd,
  asyncvpnjobcategoryDel,
  vpnjobLightenEnumGetAllList,
  vpnjobLightenEnumGetList,
  asyncvpnjobLightenEnumAdd,
  asyncvpnjobLightenEnumDel,
  asyncvpnqsCategoryJobsGet,
  vpnjobGetList,
  asyncvpnjobAdd,
  asyncjobUpdateState,
  asyncvpnjobDel,
  asynvpnjobDetail,
  asyncvpndeliverGet,
  asyncvpndeliverDetail,
  asyncvpnuserbasicDeli,
  asyncvpnuserGet,
  asyncvpnjobWantedGet,
  asyncvpnDeliverDetailAdd,
  GetZJQYdeliver,
  GetZJWTdeliver,
  GetTJQYdeliver,
  GetTJWTdeliver,
}
