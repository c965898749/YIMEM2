/*
* 缓存（同步）
* @param {string} key 缓存名
* @param {*} data 缓存数据 留空则为获取数据
* 	-> 1.不传data为获取缓存 如：cache(key)
* 	-> 2.移除时，data值为null 如：cache(key,null)
* 	-> 3.其他值为设置缓存 如：cache(key, {key: 'abc'})
* @param {number} time 缓存有效期 单位s 默认0 长期有效 如：cache(key,data,80)
*/
export const cache = function(key, data = undefined, time = 0) {
	if(!key) {
		return null
	}
	let nowTime = new Date().getTime()
	if(data == undefined) {
		// 获取
		let val = uni.getStorageSync(key)
		if(val){
			if(val.time && val.time < nowTime){
				uni.removeStorageSync(key) // 过期移除
				return null
			}else{
				return val.data
			}
		}
	}if(data == null){
		// 移除
		uni.removeStorageSync(key)
	}else{
		// 设置
		if(time > 0 ) {
			time = nowTime + time * 1000
		}
		return uni.setStorageSync(key,{data:data,time:time})
	}
}