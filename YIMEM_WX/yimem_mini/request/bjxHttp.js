
import config from './config.js' // 配置
import { cache } from './cache.js' // 缓存

export default class BjxHttp {
	constructor(options) {
		// 参数
		this.options = options
		// 请求参数
		this.params = {}
		// 请求返回结果 多图时使用
		this.result = []
		// 多图时 计数
		this.index = 0
		// 请求类型 type
		this.type = options.type == 'file' ? 'uploadFile' : 'request',
		this.options.type = this.type
		// loading
		this.loading = config.loading
		if(typeof options.loading == 'boolean') {
			this.loading = options.loading
		}
		// 设置参数
		this.setOption()
	}
	// 设置参数
	setOption(){
		// 请求前
		if(config.requestBefore){
			this.options = config.requestBefore(this.options) || this.options
		}
		// 请求地址
		if(!this.options.url){
			console.error('url不能为空')
			return 
		}
		let url = '$' + this.options.url.toLocaleString(this.options.url)
		//  非完整地址则使用配置文件中的 baseUrl
		if(url.indexOf('http://') == -1 && this.options.url.indexOf('https://') == -1){
			// 根据urlType 选择地址，无则使用 'default'
			let urlType = this.options.urlType ? this.options.urlType : 'default'
			if(!config.baseUrl[urlType] || config.baseUrl[urlType] == undefined){
				console.error(`请求地址: ${urlType} 未设置`)
			}
			this.options.url = config.baseUrl[urlType] + this.options.url
		}
		// 请求方法
		this.options.method = this.options.method || 'GET'
		// 简单参数过滤
		let fieldObj = {
			request: ['url','data','header','method','dataType','responseType','sslVerify'],
			uploadFile: ['url','fileType','filePath','name','header','formData']
		}
		let field = fieldObj[this.type]
		for(let i = 0; i < field.length; i++){
			if(this.options[field[i]]){
				this.params[field[i]] = this.options[field[i]]
			}
		}
		// 多图处理
		if(this.options.files){
			this.params.filePath = this.options.files[0]
		}
	}
	// 运行
	run(){
		// 缓存检查
		let cacheData = this.checkCache()
		// 取缓存或发新请求
		let runFn = cacheData ? this.checkCache : this.send
		// 根据传参决定是否返回 Promise对象
		if(this.options.success || this.options.fail){
			return runFn(this.options.success,this.options.fail)
		}else{
			// 返回Promise对象
			return new Promise(runFn)
		}
	}
	// 缓存检查
	checkCache = (resolve, reject) => {
		if(this.options.cache && this.type == 'request'){
			let cacheData = cache(this.options.cache.key)
			if(cacheData || cacheData == 0) {
				resolve && resolve(cacheData)
				return true
			}
		}
		return false
	}
	// 发送请求
	send = (resolve, reject) => {
		// loading 开
		if(this.loading && !cache('request_loading') && !this.index) {
			uni.showLoading({mask: true, title: '加载中'});
			cache('request_loading',true,60)
		}
		return uni[this.type](this.params).then(res => {
			// loading 关
			if(this.loading && cache('request_loading') && (!this.options.files || this.options.files.length -1  <= this.index)) {
				uni.hideLoading();
				cache('request_loading',false)
			}
			let [err,data] = res
			if(err){
				// 请求失败1 无返回或请求超时
				// 统一异常处理
				if(config.errorHandle){
					let msg = ''
					switch(err.errMsg) {
						case 'request:fail request connect error': msg = '网络连接失败';break;
						case 'request:fail timeout' : msg = '网络连接超时';break;
						default: msg = '网络错误';
					}
					uni.showToast({
						icon: 'none',
						title: msg,
						duration: 3000
					})
				}
				reject && reject(err)
				return 
			}
			// 转json
			if(this.type != 'request' && typeof data.data == 'string') {
				data.data = JSON.parse(data.data)
			}
			// 回调函数
			let callBack = resolve
			// 判断请求是成功还是失败 根据config中successCode设置的状态码决定
			if(config.successCode.includes(data.statusCode)) {
				// 设置数据缓存
				if(this.options.cache){
					let time = this.options.cache.time >= 0 ? this.options.cache.time : config.cacheTime
					cache(this.options.cache.key,data.data, time)
				}
			}else{
				// 请求失败2 
				// 统一异常处理
				if(data.statusCode == 401){
					// uni.showToast({
					// 	icon: 'none',
					// 	title: '您还未登录',
					// 	duration: 3000
					// });
					
					if(uni.getStorageSync('productUrl')){
						uni.navigateTo({
							url: "/pages/login/login?type=2&productUrl=" + uni.getStorageSync('productUrl')
						  })
					}else{
						uni.navigateTo({
							url: "/pages/login/login?type=1"
						  })
					}
					
					  callBack = reject
				}else if(data.statusCode == 502){
					uni.showToast({
						icon: 'none',
						title: '系统更新中',
						duration: 3000
					});
				}else{
					if(config.errorHandle){
						uni.showToast({
							icon: 'none',
							title: '请求失败：' + data.statusCode,
							duration: 3000
						});
					}
					callBack = reject
				}
			}
			// 请求后
			if(config.requestAfter){
				data.data = config.requestAfter(data.data,this.options,this.index) || data.data
			}
			this.index++
			if(this.type == 'request' || !this.options.files){
				callBack && callBack(data.data)
			}else{
				// 多图上传处理
				this.result.push(data.data)
				if(this.index >= this.options.files.length){
					resolve && resolve(this.result)
				}else{
					// 多图循环上传
					this.params.filePath = this.options.files[this.index]
					return this.send(resolve, reject)
				}
			}
		})
	}
}