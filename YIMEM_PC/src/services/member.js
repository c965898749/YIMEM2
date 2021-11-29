import {vpncompanyGet,vpncompanyAdd,vpncompanyUpdate,vpnjobhunterGet,vpndistributionGet,vpndistributionUpdate} from '@/services/api'
import {request, METHOD} from '@/utils/request'

export async function getvpncompanyGetList(requests) {
  return request(vpncompanyGet, METHOD.POST, requests)
}

export async function asyncvpncompanyAdd(requests) {
  return request(vpncompanyAdd, METHOD.POST, requests)
}
export async function asyncvpncompanyUpdate(requests) {
  return request(vpncompanyUpdate, METHOD.POST, requests)
}
export async function vpnjobhunterGetList(requests) {
  return request(vpnjobhunterGet, METHOD.POST, requests)
}
export async function asyncvpndistributionGet(requests) {
  return request(vpndistributionGet, METHOD.POST, requests)
}
export async function asyncvpndistributionUpdate(requests) {
  return request(vpndistributionUpdate, METHOD.POST, requests)
}

export default {
  getvpncompanyGetList,
  asyncvpncompanyAdd,
  asyncvpncompanyUpdate,
  vpnjobhunterGetList,
  asyncvpndistributionGet,
  asyncvpndistributionUpdate
}