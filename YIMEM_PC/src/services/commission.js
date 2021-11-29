import {IncomeGet,incomeDetailGet,CompanyBillGet,CompanyBillUpdate,companyGet,CompanyBillDetail,ApplicationForWithdrawal,ApplicationForWithdrawalUpdate} from '@/services/api'
import {request, METHOD} from '@/utils/request'

export async function GetIncome(requests) {
  return request(IncomeGet, METHOD.POST, requests)
}

export async function GetincomeDetail(requests) {
  return request(incomeDetailGet, METHOD.POST, requests)
}
export async function GetCompanyBill(requests) {
  return request(CompanyBillGet, METHOD.POST, requests)
}
export async function asyncCompanyBillUpdate(requests) {
  return request(CompanyBillUpdate, METHOD.POST, {vpnCompanyBillUpdate:requests})
}

export async function GetcompanyDetail(companyId) {
  return request(companyGet+'/'+companyId, METHOD.POST)
}

export async function GetCompanyBillDetail(requests) {
  return request(CompanyBillDetail, METHOD.POST, requests)
}

export async function GetApplicationForWithdrawal(requests) {
  return request(ApplicationForWithdrawal, METHOD.POST, requests)
}

export async function UpdateApplicationForWithdrawal(requests) {
  return request(ApplicationForWithdrawalUpdate, METHOD.POST, {vpnApplicationForWithdrawalUpdate:requests})
}
export default {
  GetIncome,
  GetincomeDetail,
  GetCompanyBill,
  asyncCompanyBillUpdate,
  GetcompanyDetail,
  GetCompanyBillDetail,
  GetApplicationForWithdrawal,
  UpdateApplicationForWithdrawal
}