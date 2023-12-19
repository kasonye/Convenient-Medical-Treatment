<template>
	<view class="goods_detail">
		<image :src="good.imageUrl"></image>
		<view><text>{{good.medicineName}}</text></view>
		<view class="box1">
			<view class="price">
				<text>￥{{good.costPrice}}</text>
				<text>￥{{good.sellingPrice}}</text>
			</view>
		</view>
		<view class="line"></view>
		<view class="box2">
			<view>库存：{{good.stockNumber}}</view>
			<view>制造商：{{good.manufacturer}}</view>
		</view>
		<view class="line"></view>
		<view class="count">
			<view class="iconfont icon-minus-circle" @click="minus(it,id)"></view>
			<view>{{count}}</view>
			<view class="iconfont icon-plus-circle" @click="plus(it,id)"></view>
		</view>
		<button type="warn" style="margin-top: 100rpx;" class="saveButton" @click="save">添加购物车</button>
	</view>
</template>

<script>
	const BASE_API = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				good: {},
				count:0,
				token: '',
				userId:'',
				cart:{}
			}
		},
		methods: {
			minus(it,id){
				if(this.count>0){
					this.count = this.count - 1
					this.$forceUpdate();
					this.good.count = this.count
					console.log(this.good)
				}
			},
			plus(it,id){
				this.count = this.count + 1
				this.$forceUpdate();
				this.good.count = this.count
				console.log(this.good)
			},
			save(){
				this.cart.userId = this.userId
				this.cart.medicineId = this.good.medicineId
				this.cart.count = this.good.count
				if(this.good.count == 0){
					uni.showToast({
						title:'数量不能为0'
					})
				}else{
					uni.request({
						url:BASE_API+'/cart',
						method:'POST',
						data:this.cart,
						header:{
							'token':this.token
						},
						success: (res) => {
							console.log(res)
							uni.showToast({
								title:'添加成功'
							})
						}
					})
				}
			}
		},
		onLoad(options) {
			this.userId = uni.getStorageSync('userId')
			this.token = uni.getStorageSync('token')
			this.good = JSON.parse(options.good)
			this.good.count = this.count
			console.log(this.good)
		}
	}
</script>

<style>
	@import url("../../common/iconfont/iconfont.css");
	.count {
		flex-flow: row;
		justify-content: flex-end;
		display: flex;
	}
	.saveButton {
		width: 100%;
		position:fixed;
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
