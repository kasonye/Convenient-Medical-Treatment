<template>
	<view>

		<uni-section title="姓名" type="line" padding>
			<uni-easyinput v-model="form.userName" focus placeholder="请输入内容"></uni-easyinput>
		</uni-section>
		<uni-section title="性别" type="line" padding>
			<uni-easyinput v-model="form.gender" focus placeholder="请输入内容"></uni-easyinput>
		</uni-section>
		<uni-section title="年龄" type="line" padding>
			<uni-easyinput v-model="form.age" focus placeholder="请输入内容"></uni-easyinput>
		</uni-section>
		<uni-section title="电话" type="line" padding>
			<uni-easyinput v-model="form.phone" focus placeholder="请输入内容"></uni-easyinput>
		</uni-section>
		<uni-section title="地址" type="line" padding>
			<uni-easyinput v-model="form.address" focus placeholder="请输入内容"></uni-easyinput>
		</uni-section>

		<button type="warn" style="margin-top: 100rpx;" class="saveButton" @click="save">保存资料</button>

		</scroll-view>
	</view>
</template>

<script>
	const baseUrl = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				form: {},
				userId: '',
				token: '',
				imageValue: []
			}
		},
		onShow() {
			this.token = uni.getStorageSync('token');
			this.userId = uni.getStorageSync('userId');
			uni.request({
				url: baseUrl + '/user/' + this.userId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					this.form = res.data.body
				}
			})
		},
		methods: {
			save() {
				uni.request({
					url: baseUrl + '/user',
					method: 'PUT',
					data: this.form,
					header: {
						'token': this.token
					},
					success: (res) => {
						if (res.data.statusCodeValue == 200) {
							uni.showToast({
								title: '保存成功'
							})
						} else {
							uni.showToast({
								title: '数据不规范'
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
	body {
		background-color: #fff;
		padding-bottom: 20%;
	}

	.saveButton {
		width: 100%;
		position: fixed;
		bottom: 0;
	}

	image {
		margin-left: 20%;
		width: 60%;
		border-radius: 100%;
		height: 400rpx;
	}
</style>
