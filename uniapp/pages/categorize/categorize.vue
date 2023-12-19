<template>
	<view class="classify">
		<!-- 分类部分 -->
		<view class="cate-left">
			<view :class="['cate-item',activeIndex==index?'active':'']" v-for="(item,index) in cateList" :key="index"
				@click="changeActive(index)">{{item.categoryName}}</view>
		</view>

		<view class="cate-right">
			<view class="cate-content" v-for="(it,id) in goodList" :key="id" @click="nav(it,id)">
				<image :src="it.imageUrl" mode=""></image>
				<view class="cate-text">{{it.medicineName}}
				</view>
			</view>
		</view>
	</view>

</template>

<script>
	const BASE_API = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				scrollTop: 0,
				// 初始化的分类列表
				cateList: [],
				// 当前点击项的索引号
				activeIndex: 0,
				// 分类下的商品信息
				goodList: [],
				token: '',
				cartList:[],
				userId: '',
				good:{}
			}
		},
		methods: {
			nav(it,id){
				this.good = it
				uni.navigateTo({
					url:'/pages/goodsDetail/goodsDetail?good='+JSON.stringify(this.good)
				})
			},
			changeActive(i) {
				this.activeIndex = i
				// 已经获取到索引号
				this.goodList = this.cateList[i].medicineList
			},
			plus(item, index) {
				item.count = item.count + 1
				this.$forceUpdate()
				console.log(item)
			},
			minus(item, index) {
				if (item.count > 0) {
					item.count = item.count - 1
					this.$forceUpdate()
				}
				console.log(item)
			},
			addCart() {
				this.cartList = []
				this.goodList.map((res,index) => {
					this.cartList.push({
						medicineId:res.medicineId,
						count:res.count,
						userId:this.userId
					})
					if(this.cartList[index].count>0){
						uni.request({
							url:BASE_API+'/cart/'+this.userId+'/'+this.cartList[index].medicineId,
							method:'GET',
							header:{
								'token':this.token
							},
							success: (re) => {
								if(re.data == ""){
									this.cartList[index].cartId = ''
									uni.request({
										url:BASE_API+'/cart',
										method:'POST',
										data:this.cartList[index],
										header:{
											'token':this.token
										},
										success: (r) => {
											uni.showToast({
												title:'添加购物车成功'
											})
											res.count = 0
											this.$forceUpdate()
										}
									})
								}else{
									this.cartList[index].cartId = re.data.cartId
									this.cartList[index].count = this.cartList[index].count + re.data.count
									uni.request({
										url:BASE_API+'/cart',
										method:'PUT',
										data:this.cartList[index],
										header:{
											'token':this.token
										},
										success: (r) => {
											uni.showToast({
												title:'添加购物车成功'
											})
											res.count = 0
											this.$forceUpdate()
										}
									})
								}
							}
						})
					}
				})
				
				
			}

		},
		onShow() {
			this.token = uni.getStorageSync('token')
			this.userId = uni.getStorageSync('userId')
			uni.request({
				url: BASE_API + '/category',
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					this.cateList = res.data.body
					this.goodList = this.cateList[0].medicineList

				}
			})
		}
	}
</script>

<style>
	@import url("../../common/iconfont/iconfont.css");

	.addCart {
		width: 84%;
		position: fixed;
		bottom: 0;
	}

	.count {
		flex-flow: row;
		justify-content: flex-end;
		display: flex;
	}

	.classify {
		background-color: #fff;
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: space-between;
	}

	.cate-left {
		width: 200rpx;
		height: 100vh;
		overflow: auto;
		background-color: #F6F6F6;
	}

	.cate-item {
		width: 100%;
		height: 100rpx;
		line-height: 100rpx;
		padding-left: 40rpx;
		box-sizing: border-box;
	}

	.active {
		background-color: #FFFFFF;
		position: relative;
		padding-left: 40rpx;
		box-sizing: border-box;
	}

	.active ::before {
		content: '';
		display: block;
		width: 5rpx;
		border-radius: 6rpx;
		height: 50rpx;
		background-color: #FC4353;
		position: absolute;
		left: 5rpx;
		top: 30rpx;
	}

	.cate-right {
		width: 80%;
		height: 100vh;
		overflow: auto;
	}

	.cate-content {
		float: left;
		width: 90%;
		height: 185rpx;
		text-align: center;
		margin: 20rpx 0 0 15rpx;
		padding: 5rpx;
		background: #f1f1f1;
		border-radius: 20rpx;
	}

	image {
		width: 100rpx;
		height: 100rpx;
	}

	.cate-text {
		font-size: 34rpx;
	}
</style>
