<template>
	<view>

		<view style="width: 90%; margin: 200rpx auto;">
			<view style="margin-bottom: 70rpx; font-size: 60rpx; text-align: center; color: skyblue;">登 录</view>
			<view style="margin: 20rpx 0;">
				<uni-easyinput v-model="FormData.userLoginName" prefixIcon="person" placeholder="请输入用户名"
					name="username">
				</uni-easyinput>
			</view>
			<view style="margin: 20rpx 0;">
				<uni-easyinput type="password" prefixIcon="locked" v-model="FormData.userPassword" placeholder="请输入密码"
					name="password"></uni-easyinput>
			</view>
			<view style="margin-top: 40rpx;">
				<button type="primary" @click="login">登 录</button>
			</view>
			<view style="margin-top: 40rpx;">
				<navigator url='/pages/register/register' style="color: skyblue;">前往注册</navigator>
			</view>
		</view>
	</view>
</template>

<script>
	const baseUrl = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				FormData: {},
			}
		},
		methods: {
			login() {
				if (this.FormData.userLoginName == "" || this.FormData.userLoginName == null) {
					uni.showToast({
						title: '请输入用户名'
					})
					return false;
				}
				if (this.FormData.userPassword == "" || this.FormData.userPassword == null) {
					uni.showToast({
						title: '请输入密码'
					})
					return false;
				}
				console.log(this.FormData)
				this.request({
					url: '/user/login',
					method: 'post',
					data: this.FormData
				}).then(res => {
					if (res.statusCode !== 401) {
						uni.setStorageSync('token', res.data.token)
						uni.setStorageSync('userId', res.data.userId)
						uni.setStorageSync('userName', res.data.userLoginName)
						uni.switchTab({
								url: '/pages/index/index',
							}),
							uni.showToast({
								title: '登录成功'
							})

					}
				})
			}
		}
	}
</script>

<style>

</style>
