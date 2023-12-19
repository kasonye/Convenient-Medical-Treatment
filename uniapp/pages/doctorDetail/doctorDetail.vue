<template>
	<view class="goods_detail">
		<image :src="doctor.imageUrl"></image>
		<view><text>{{doctor.doctorName}}</text></view>
		<view class="box1">
			<view class="price">
				<text>{{positionName}}</text>
			</view>
			<view class="goods_name">{{message.goods_name}}</view>
		</view>
		<view class="line"></view>
		<view class="box2">
			<view>年龄：{{doctor.age}}</view>
			<view>部门：{{departmentName}}</view>
			<view class="example-body">
				<text>请选择预约的时间：</text>
				<uni-datetime-picker type="date" :clear-icon="false" v-model="appointmentDate"
					@change="maskClick($event)" />
			</view>
		</view>
		<view class="line"></view>
		<button type="warn" style="margin-top: 100rpx;" class="saveButton" @click="save">提交预约信息</button>
	</view>
</template>

<script>
	const BASE_URL = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				doctor: {},
				departmentName: '',
				positionName: '',
				userId: '',
				token: '',
				appointmentDate: '',
				appointment: {},
			}
		},
		methods: {
			maskClick(e) {
				this.appointmentDate = e
				console.log(e)
			},
			save() {
				if (this.appointmentDate == '') {
					uni.showToast({
						title: '请选择预约时间'
					})
				} else {
					this.appointment.doctorId = this.doctor.doctorId
					this.appointment.userId = this.userId
					this.appointment.appointmentDate = this.appointmentDate
					uni.request({
						url:BASE_URL+'/appointment/web',
						method:'POST',
						data:this.appointment,
						header:{
							'token':this.token
						},
						success: (res) => {
							uni.showToast({
								title:'预约成功'
							})
							uni.redirectTo({
								url:'/pages/appointment/appointment'
							})
						}
					})
					
				}


			}
		},
		onLoad(options) {
			this.token = uni.getStorageSync('token');
			this.userId = uni.getStorageSync('userId');
			this.doctor = JSON.parse(options.good)
			uni.request({
				url: BASE_URL + '/department/' + this.doctor.departmentId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					this.departmentName = res.data.body.departmentName
				}
			})
			uni.request({
				url: BASE_URL + '/position/' + this.doctor.positionId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					this.positionName = res.data.body.positionName
				}
			})
		}
	}
</script>

<style>
	.saveButton {
		width: 100%;
		position: fixed;
		bottom: 0;
	}

	image {
		width: 100%;
		height: 400rpx;
	}

	.price {
		font-size: 35rpx;
		color: $shop-color;
		line-height: 80rpx;
	}

	text:nth-child(2) {
		font-size: 30rpx;
		color: #ccc;
		text-decoration: line-through;
		margin-left: 20rpx;
	}

	.goods_price {
		font-size: 32rpx;
		line-height: 60rpx;
	}

	.box1 {
		padding: 10px;
	}

	.line {
		width: 750rpx;
		height: 10rpx;
		background: #eee;
	}

	.box2 {
		padding: 0 10px;
		font-size: 32rpx;
		line-height: 70rpx;
	}

	.tit {
		font-size: 32rpx;
		padding-left: 10px;
		border-bottom: 1px solid #eee;
		line-height: 70rpx;
	}

	.content {
		font-size: 28rpx;
		padding: 10px;
		line-height: 50rpx;
	}
</style>
