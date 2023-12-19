const BASE_URL = `http://192.168.43.151:9090`

export const request = (options) => {
	return new Promise((resolve, reject) => {
		uni.request({
			url: BASE_URL + options.url,
			method: options.method || 'GET',
			header: {token: uni.getStorageSync('token') ? uni.getStorageSync('token').token : ''},
			data: options.data || {},
			success: (res) => {
				const data = res
				console.log(data)
				if (data.statusCode === 401) {
					uni.navigateTo({
						url: '/pages/login/login'
					})
					return
				} else if(data.statusCode !== 200) {
					uni.showToast({
						icon: 'error',
						title: '操作错误'
					})
				}
				resolve(data) 
			},
			fail: (error) => {
				uni.showToast({
					icon: 'error',
					title: '系统错误'
				})
				reject(error)
			}
		})
	})
}