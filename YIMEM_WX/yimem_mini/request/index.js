import BjxHttp from './bjxHttp.js'

export default function(data) {
	let bjxHttp  = new BjxHttp(data)
	return bjxHttp.run()
}

//文档 https://ext.dcloud.net.cn/plugin?id=928

// 参数		必填	值类型		默认值			说明
// type		否		String		request		请求类型 图片上传时改为 file
// url		是		String					请求地址
// urlType	否		String		default		根据设定值，从配置的baseUrl中选择地址
// header	否		Object					请求头
// loading	否		Boolean					是否显示加载loading,优先级大于config中loading项
// success	否		Function				成功回调函数
// fail		否		Function				失败回调函数

// post的时候需要在请求头中加上'Content-Type': "application/x-www-form-urlencoded"，config.js中

// 当type 为 request 时的参数
// 参数			必填	值类型						默认值		说明
// data			否		Object/String/ArrayBuffer				请求的参数
// method		否		String						GET			请求方法
// dataType		否		String						json		如果设为 json，会尝试对返回的数据做一次 JSON.parse
// responseType	否		String						text		设置响应的数据类型。合法值：text、arraybuffe
// sslVerify	否		Boolean						true		请求的参数
// cache		否		Object									数据缓存对象中有两个参数，一个是key(缓存名，必须)，另一个是time（缓存有效时间单位/秒，可选）如 {key: 'cache_data1',time: 60}


// 当type 为 file 时的参数(图片上传)
// 参数			必填	值类型						默认值		说明
// files		否		Array									需要上传的文件列表。使用 files 时，filePath 和 name 不生效
// fileType		否		String									文件类型，image/video/audio，仅支付宝小程序，且必填
// filePath		是		String									要上传文件资源的路径
// name			是		String									文件对应的 key , 开发者在服务器端通过这个 key 可以获取到文件二进制内容
// formData		是		Object									HTTP 请求中其他额外的 form data