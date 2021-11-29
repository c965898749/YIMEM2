const validatorPhone = (rule, value, callback) => {
  if (!value) {
    callback(new Error(rule.emptymsg))
  } else {
    if (!/^1[3456789]\d{9}$/.test(value)) {
      callback(new Error('请输入正确的手机号'))
    } else {
      callback()
    }
  }
}
//整数
const validatorNumber = (rule, value, callback) => {
  if (!value) {
    callback(new Error(rule.emptymsg))
  }else{
    if (!/^\d+$/.test(value)) {
      callback(new Error('请输入数字类型'))
    } else {
      callback()
    }
  }
}

//数字可以是小数
const validatorSNumber = (rule, value, callback) => {
  const empty=rule.emptymsg
  if (!value) {
    callback(new Error(empty))
  }else{
    if (!/^(([0-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,3})))$/.test(value)) {
      callback(new Error('请输入数字类型'))
    } else {
      callback()
    }
  }
}
export {
  validatorPhone,
  validatorNumber,
  validatorSNumber
}