import {vpnstrategyGetsvc,vpnstrategyAddsvc,vpnstrategyUpdatesvc,vpnstrategyDelsvc,vpnstrategyState} from '@/services/api'
import {request, METHOD} from '@/utils/request'

export async function asyncvpnstrategyGetsvc(requests) {
  return request(vpnstrategyGetsvc, METHOD.POST, requests)
}

export async function asyncvpnstrategyDetail(stId) {
  return request(vpnstrategyGetsvc+'/'+stId, METHOD.POST)
}

export async function asyncvpnstrategyAddsvc(mode,requests) {
  if(mode=='add'){
    return request(vpnstrategyAddsvc, METHOD.POST, requests)
  }else{
    return request(vpnstrategyUpdatesvc, METHOD.POST, requests)
  }

}

export async function asyncvpnstrategyDelsvc(stId) {
  return request(vpnstrategyDelsvc, METHOD.POST,{
    vpnstrategyDel :{stId:stId}
  })
}

export async function asyncvpnstrategyState(requests) {
  return request(vpnstrategyState, METHOD.POST,requests)
}
export default {
  asyncvpnstrategyGetsvc,
  asyncvpnstrategyDetail,
  asyncvpnstrategyAddsvc,
  asyncvpnstrategyDelsvc,
  asyncvpnstrategyState
}