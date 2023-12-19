<template>
	<view>

		<view style="width: 90%; margin: 200rpx auto;">
			<view style="margin-bottom: 70rpx; font-size: 60rpx; text-align: center; color: royalblue;">注 册</view>
			<view style="margin: 20rpx 0;">
				<uni-easyinput v-model="FormData.userLoginName" prefixIcon="person" placeholder="请输入用户名"
					name="username"></uni-easyinput>
			</view>
			<view style="margin: 20rpx 0;">
				<uni-easyinput type="password" prefixIcon="locked" v-model="FormData.userPassword" placeholder="请输入密码"
					name="password">
				</uni-easyinput>
			</view>
			<view style="margin: 20rpx 0;">
				<uni-easyinput type="password" prefixIcon="locked" v-model="FormData.confirm" placeholder="请输入密码">
				</uni-easyinput>
			</view>
			<view style="margin-top: 40rpx;">
				<button type="primary" @click="register">注 册</button>
			</view>
			<view style="margin-top: 40rpx;">
				<navigator url='/pages/login/login' style="color: royalblue;">前往登录</navigator>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		request
	} from '../../utils/request';
	const baseUrl = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				FormData: {},

			}
		},
		methods: {
			register() {
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
				if (this.FormData.confirm == "" || this.FormData.confirm == null) {
					uni.showToast({
						title: '请输入确认密码'
					})
					return false;
				}
				if (this.FormData.userPassword != this.FormData.confirm) {
					uni.showToast({
						title: '密码输入不一致'
					})
				} else {
					console.log(this.FormData)
					// uni.request({
					// 	url: baseUrl + '/user',
					// 	method: 'GET',
					// 	success: (res) => {
					// 		uni.navigateTo({
					// 			url: '/pages/index/index'
					// 		})

					// 	}
					// })
					this.request({
						url: '/user/register',
						method: 'post',
						data: this.FormData
					}).then(res => {
						if (res.statusCode == '200') {
							uni.navigateTo({
									url: '/pages/login/login',
								}),
								uni.showToast({
									title: '注册成功'
								})
						}else{
							uni.showToast({
								title: '用户名已存在'
							})
						}
					})
				}

			}
		}
	}
</script>

<style>

</style>
