<template>
	<view>
		<view class="order" v-for="(item,index) in goodList">
			<view>订单号: {{item.orderId}}</view>
			<view class="goods" v-for="(it,id) in item.orderItems">
				<image :src="it.medicine.imageUrl" mode="aspectFit"></image>
				<view class="goods_item">
					<view style="margin-bottom: 80rpx;"><text>{{it.medicine.medicineName}}</text></view>
					<view><text>￥{{it.medicine.costPrice}} * {{it.quantity}}</text></view>
				</view>
			</view>
			<view style="margin-top: 30rpx;">Total: {{item.totalPrice}}</view>
			<view style="margin-top: 30rpx;">Create Date: {{dateFormat(item.createTime)}}</view>
			<view style="margin-top: 30rpx;">Status: {{item.isSent}}</view>
			<view v-if="item.isSent == 'To be delivered'" style="margin-top: 30rpx;">
				<button type="primary" @click="confirm(item,index)" style="margin-bottom: 3%;">确认订单</button>
				<button type="warn" @click="cancel(item,index)">取消订单</button>
			</view>
		</view>
	</view>
</template>

<script>
	const BASE_URL = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				goodList: [],
				token: '',
				userId: '',
				orderItem:{}
			}
		},
		methods: {
			confirm(item,index){
				this.orderItem = item
				this.orderItem.isSent = "Received"
				uni.request({
					url:BASE_URL+'/order',
					method:'PUT',
					header: {
						'token': this.token
					},
					data:this.orderItem,
					success: (res) => {
						console.log(res)
					}
				})
			},
			cancel(item,index){
				this.orderItem = item
				this.orderItem.isSent = "Cancelled"
				uni.request({
					url:BASE_URL+'/order',
					method:'PUT',
					header: {
						'token': this.token
					},
					data:this.orderItem,
					success: (res) => {
						console.log(res)
					}
				})
			},
			dateFormat(time) {
				let date = new Date(time);
				let year = date.getFullYear();
				// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
				let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
				let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
				let hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
				let minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
				let seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
				// 拼接
				// return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
				return year + "-" + month + "-" + day;
			}

		},
		onShow() {
			this.goodList = []
			this.token = uni.getStorageSync('token')
			this.userId = uni.getStorageSync('userId')
			uni.request({
				url: BASE_URL + '/order/user?userId=' + this.userId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					this.goodList = res.data
				}
			})
		}
	}
</script>

<style>
	
	.order {
		width: 96%;
		height: auto;
		background-color: #fff;
		margin-top: 2%;
		padding:10rpx;
		border-radius: 10rpx;
		border: #f5f5f5 5rpx solid;
	}

	.goods {
		margin-top: 2%;
		margin-left: 2%;
		background-color: #fff;
		border-radius: 10rpx;
		width: 96%;
		height: 200rpx;
		display: flex;
		justify-content: start;
		border: #f5f5f5 5rpx solid;
	}

	image {
		width: 40%;
		height: 100%;
		overflow: auto;
	}

	.account {
		width: 100%;
		display: flex;
		justify-content: start;
		position: fixed;
		bottom: 0;
		background-color: #fff;
	}
</style>
