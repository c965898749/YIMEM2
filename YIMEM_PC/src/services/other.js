import {vpncomplaintGet,vpncomplaintDel} from '@/services/api'
import {request, METHOD} from '@/utils/request'

/**
 轮播图
 */
export async function getvpncomplaintGet(requests) {
  return request(vpncomplaintGet, METHOD.POST, requests)
}


export async function asyncvpncomplaintDel(comId) {
  return request(vpncomplaintDel, METHOD.POST, {
    vpncomplaintDel: {comId :comId}
  })
}

export default {
  getvpncomplaintGet,
  asyncvpncomplaintDel
}
