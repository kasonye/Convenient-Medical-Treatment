<template>
	<view>
		<view class="page-section-spacing">
			<swiper class="swiper" circular :indicator-dots="indicatorDots" indicator-dots="true" :autoplay="autoplay"
				:interval="interval" :duration="duration" autoplay="true" interval="5000" duration="1000">
				<swiper-item v-for="(item,index) in lunbo" :key="index">
					<image :src="item.img" mode="aspectFill"></image>
				</swiper-item>
			</swiper>
		</view>

		<view class='nav'>
			<navigator class='nav_item' url="../registration/registration">
				<view>
					<uni-icons type="contact" size="30"></uni-icons>
				</view>
				<text>预约挂号</text>
			</navigator>
			<navigator class='nav_item' url="../categorize/categorize">
				<view class="iconfont icon-medicinebox"></view>
				<text>药品选购</text>
			</navigator>
			<navigator class='nav_item' url="../appointment/appointment">
				<view class="iconfont icon-date"></view>
				<text>挂号详情</text>
			</navigator>
			<navigator class='nav_item' url="../order/order">
				<view class="iconfont icon-order"></view>
				<text>我的订单</text>
			</navigator>
		</view>
		<view class="hot_goods">
			<view class="title">推荐商品</view>
			<view class="goods_list">
				<view class="goods_item" v-for="(it,id) in medicine" :key="id" @click="nav(it,id)">
					<image :src="it.imageUrl"></image>
					<view class="price">
						<text>￥{{it.costPrice}}</text>
						<text>￥{{it.sellingPrice}}</text>
					</view>
					<view class="name">
						<text>{{it.medicineName}}</text>
					</view>
				</view>

			</view>
		</view>
	</view>
</template>

<script>
	const baseUrl = 'http://192.168.43.151:9090'
	import {
		request
	} from '../../utils/request'
	export default {
		data() {
			return {
				lunbo: [{
						img: "/static/cdf38e966d914119afb926bed77a1342.jpeg"
					},
					{
						img: "/static/9439.jpg_wh300.jpg"
					},
					{
						img: "/static/74fa-iuzasxs3970081.png"
					}
				],
				token: '',
				medicine: [],
				good: {}
			}
		},
		onShow() {
			this.token = uni.getStorageSync('token');
			uni.request({
				url: baseUrl + '/medicine/web',
				method: 'GET',
				header: {
					'token': this.token
				},
				success: (res) => {
					if(res.statusCode == 401){
						uni.redirectTo({
							url: '/pages/login/login'
						})
					}
					this.medicine = res.data.body
				}
			})
		},
		methods: {
			nav(it, id) {
				this.good = it
				uni.navigateTo({
					url: '/pages/goodsDetail/goodsDetail?good=' + JSON.stringify(this.good)
				})
			}
		}
	}
</script>

<style>
	@import url("../../common/iconfont/iconfont.css");

	.page-section-spacing {
		width: calc(100% - 60upx);
		margin: 30upx 30upx;
	}

	.swiper {
		height: 300upx;
		width: 100%;
	}

	// swiper-item 里面的图片高度
	swiper-item image {
		width: 100%;
		height: 300upx;
		border-radius: 20upx 20upx 0 0;
	}

	.nav {
		display: flex;
	}

	.nav_item {
		width: 25%;
		text-align: center;
	}

	.nav view {
		width: 120rpx;
		height: 120rpx;
		border-radius: 60rpx;
		background: #1E90FF;
		margin: 10rpx auto;
		line-height: 120rpx;
		color: #000;
		font-size: 50rpx;
	}

	text {
		font-size: 30rpx;
	}

	.hot_goods {
		background: #eee;
		overflow: hidden;
		margin-top: 10rpx;
	}

	.title {
		height: 50px;
		line-height: 50px;
		color: $shop-color;
		background: #fff;
		text-align: center;
		letter-spacing: 20px;
		margin: 7rpx 0;
	}

	.goods_list {
		padding: 0 15rpx;
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}

	.goods_item {
		background: #fff;
		width: 355rpx;
		margin: 10px 0;
		padding: 15rpx;
		box-sizing: border-box;
	}

	image {
		width: 80%;
		height: 150px;
		display: block;
		margin: 5px auto;
	}

	.price {
		color: $shop-color;
		font-size: 36rpx;
	}

	text:nth-child(2) {
		color: #ccc;
		font-size: 28rpx;
		margin-left: 17rpx;
		text-decoration: line-through;
	}

	.name {
		font-size: 28rpx;
		line-height: 50rpx;
		padding-bottom: 15rpx;
		padding-top: 10rpx;
	}
</style>
