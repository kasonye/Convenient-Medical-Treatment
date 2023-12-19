<template>
	<view>
		<view class="goods" v-for="(item,index) in goodList">
			<image :src="item.imageUrl" mode="aspectFit"></image>
			<view class="goods_item">
				<view style="margin-bottom: 40rpx;"><text>{{item.medicineName}}</text></view>
				<view><text>库存: {{item.stockNumber}}</text></view>
				<view><text>￥{{item.costPrice}} * {{item.count}}</text></view>
			</view>
			<view>
				<view class="count">
					<view class="iconfont icon-minus-circle" @click="minus(item,index)"></view>
					<view>{{item.count}}</view>
					<view class="iconfont icon-plus-circle" @click="plus(item,index)"></view>
				</view>
			</view>
		</view>
		<view class="account">
			<view>Total: ￥{{total}}</view>
			<button type="warn" style="width: 100%;" @click="pay">提交订单</button>
		</view>
	</view>
</template>

<script>
	const BASE_API = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				cartList: [],
				goodList: [],
				token: '',
				userId: '',
				good: {},
				cart:{},
				total:0,
				stockNumber:''
			}
		},
		methods: {
			pay(){
				if(this.total!=0){
					uni.redirectTo({
							url: '/pages/payment/payment?total='+this.total+'&goodList='+JSON.stringify(this.goodList),
						})
				}else{
					uni.showToast({
						title:"购物车不能为空"
					})
				}
				
			},
			plus(item) {
				item.count = item.count + 1
				this.$forceUpdate()
				this.cart = {
					medicineId:item.medicineId,
					cartId:item.cartId,
					count:item.count,
					userId:this.userId
				}
				uni.request({
					url:BASE_API+'/cart',
					method:'PUT',
					data:this.cart,
					header:{
						'token':this.token
					},
					success: (res) => {
						this.total = this.total + item.costPrice
						this.$forceUpdate()
					}
				})
				console.log(item)
			},
			minus(item) {
				item.count = item.count - 1
				this.$forceUpdate()
				this.cart = {
					medicineId:item.medicineId,
					cartId:item.cartId,
					count:item.count,
					userId:this.userId
				}
				uni.request({
					url:BASE_API+'/cart',
					method:'PUT',
					data:this.cart,
					header:{
						'token':this.token
					},
					success: (res) => {
						this.total = this.total - item.costPrice
						this.$forceUpdate()
					}
				})
				if (item.count <= 0) {
					uni.showModal({
						title: "提示",
						content: "是否移除该药品",
						showCancel: true,
						cancelText: '取消',
						confirmText: '删除',
						success: (res) => {
							if (res.confirm) {
								// 用户点击确定
								uni.request({
									url: BASE_API + '/cart/' + item.cartId,
									method: 'DELETE',
									header: {
										'token': this.token
									},
									success: (re) => {
										this.cartList = []
										this.goodList = []
										uni.showToast({
											title: '移除成功'
										})
										uni.request({
											url: BASE_API + '/cart/' + this.userId,
											method: 'GET',
											header: {
												'token': this.token
											},
											success: (res) => {
												// res.data.map((re, index) => {
												this.cartList = res.data
												console.log(this.cartList)
												// })
												this.cartList.map((r, i) => {
													console.log(r)
													uni.request({
														url: BASE_API + '/medicine/' + r.medicineId,
														method: 'GET',
														header: {
															'token': this.token
														},
														success: (result) => {
															this.good = result.data.body
															this.good.cartId = r.cartId
															this.good.count = r.count
															this.goodList.push(this.good)
															console.log(this.goodList)
															this.$forceUpdate()
														}
													})
												})
											}
										})
										
									}
								})
								console.log(item)
							} else {
								// 否则点击了取消
								  item.count = 1;
								  this.$forceUpdate()
								  this.cart = {
								  	medicineId:item.medicineId,
								  	cartId:item.cartId,
								  	count:item.count,
								  	userId:this.userId
								  }
								  uni.request({
								  	url:BASE_API+'/cart',
								  	method:'PUT',
								  	data:this.cart,
								  	header:{
								  		'token':this.token
								  	},
								  	success: (res) => {
								  		this.total = this.total + item.costPrice
								  	}
								  })
							}
						}
					})
				}
				console.log(item)
			},
		},

		onShow() {
			this.cartList = []
			this.goodList = []
			this.total = 0
			this.token = uni.getStorageSync('token')
			this.userId = uni.getStorageSync('userId')
			uni.request({
				url: BASE_API + '/cart/' + this.userId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					// res.data.map((re, index) => {
					this.cartList = res.data
					console.log(this.cartList)
					// })
					this.cartList.map((r, i) => {
						console.log(r)
						uni.request({
							url: BASE_API + '/medicine/' + r.medicineId,
							method: 'GET',
							header: {
								'token': this.token
							},
							success: (result) => {
								this.good = result.data.body
								this.good.cartId = r.cartId
								this.good.count = r.count
								this.goodList.push(this.good)
								this.total = this.total + this.good.costPrice
								this.stockNumber = this.good.stockNumber
							}
						})
					})
				}
			})

		}
	}
</script>

<style>
	@import url("../../common/iconfont/iconfont.css");

	body {
		padding-bottom: 18%;
	}

	.goods {
		margin-top: 2%;
		margin-left: 2%;
		background-color: #fff;
		border-radius: 10rpx;
		width: 96%;
		height: 200rpx;
		display: flex;
		justify-content: space-between;
	}

	image {
		width: 40%;
		height: 100%;
		overflow: auto;
	}

	.count {
		flex-flow: row;
		justify-content: flex-end;
		display: flex;
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
