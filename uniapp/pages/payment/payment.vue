<template>
	<view>
		<uni-section title="支付" type="line" padding>
			<text style="font-size: 50rpx;">您总共需要支付￥{{total}}元</text>
		</uni-section>
		<uni-section padding>
			<!-- <uni-easyinput v-model="amount" focus placeholder="￥请输入金额"></uni-easyinput> -->
			<image src="../../static/payment.jpg" mode="aspectFit"></image>
		</uni-section>
		<button type="warn" class="payBtn" @click="pay">确认支付</button>
	</view>
</template>

<script>
	const BASE_API = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				total: 0,
				goodList: [],
				token: '',
				userId: '',
				order: {},
				orderItem:{},
				medicine:{},
				stockNumber:'',
				appointment:{}
			}
		},
		methods: {
			pay() {
				this.goodList.map((goodRes)=>{
					uni.request({
						url:BASE_API+'/medicine/'+goodRes.medicineId,
						method:'GET',
						header:{
							'token':this.token
						},
						success: (medicineRes) => {
							this.medicine = medicineRes.data.body
							uni.setStorageSync('stockNumber',this.medicine.stockNumber)
						}
					})
				})
				this.stockNumber = uni.getStorageSync('stockNumber')
				console.log(this.stockNumber)
				if(this.stockNumber!="0"){
				uni.request({
					url:BASE_API+'/order',
					method:'POST',
					data:this.order,
					header:{
						'token':this.token
					},
					success: (res) => {
						this.goodList.map((re)=>{
							this.orderItem.medicineId = re.medicineId
							this.orderItem.orderId = res.data.orderId
							this.orderItem.price = re.costPrice
							this.orderItem.quantity = re.count
							console.log(res)							
							uni.request({
								url:BASE_API+'/orderItem',
								method:'POST',
								data:this.orderItem,
								header:{
									'token':this.token
								},
								success: (r) => {
									this.medicine.stockNumber = this.medicine.stockNumber - re.count
									uni.request({
										url:BASE_API+'/medicine',
										method:'PUT',
										data:this.medicine,
										header:{
											'token':this.token
										},
										success: (updateMedicineRes) => {
											console.log(updateMedicineRes)
										}
									})
								}
							})
							uni.request({
								url:BASE_API+'/cart/'+re.cartId,
								method:'DELETE',
								header:{
									'token':this.token
								},
								success: (result) => {
									console.log(result)
								}
								
							})
						})
						uni.redirectTo({
							url:'/pages/order/order'
						})
					}
				})
				}else{
					console.log(this.stockNumber)
					uni.showToast({
						title:'库存不足'
					})
				}
				
			}
		},
		onLoad(options) {
			this.token = uni.getStorageSync('token')
			this.userId = uni.getStorageSync('userId')
			this.total = options.total
			this.goodList = JSON.parse(options.goodList)
			uni.request({
				url: BASE_API + '/user/' + this.userId,
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					console.log(this.goodList)
					this.order.userId = res.data.body.userId
					this.order.address = res.data.body.address
					this.order.phone = res.data.body.phone
					this.order.userName = res.data.body.userName
					this.order.totalPrice = this.total
				}
			})
		}
	}
</script>

<style>
	body {
		background-color: #fff;
	}

	.payBtn {
		width: 100%;
		position: fixed;
		bottom: 0;
	}
</style>
