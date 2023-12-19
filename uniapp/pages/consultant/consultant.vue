<template>
	<view>
		<view style="display: flex" id="view1">
			<view style="width: 100%">
				<scroll-view :scroll-top="scrollTop" scroll-y="true" :style="'height:' + scrollH + 'px'">
					<view class="message" v-for="(item,result) in messages">{{item}}</view>
				</scroll-view>
			</view>
		</view>
		<view class="footer" ref="footer" id="footer">
			<view class="content-wrap">
				<textarea class="content" v-model="text" maxlength="-1" auto-height />
			</view>
			<view class="btn-wrap">
				<button class="btn" :disabled="disable" :class="{'disabled': disable}" @click="handleSend">发送</button>
			</view>
		</view>
	</view>
</template>
<script>
	const baseUrl = 'http://192.168.43.151:9090'
	export default {
		data() {
			return {
				text: '',
				height: 0,
				pageHeight: 0,
				disable: true,
				footerHeight: 0,
				keyBoardHeight: 0,
				messages: [],
				userName: ''
			}
		},
		watch: {
			//监听text，当他有值时发送按钮才可以点击
			text(newVal) {
				if (newVal.trim() != '') {
					this.disable = false
				} else {
					this.disable = true
				}
			}
		},
		onReady() {
			//获取整个页面的高度，从而计算出页面可用的高度，因为使用了自定义的navbar所以this.pageHeight不是单纯的res.windowHeight。（ps: uview组件的navbar高度是固定的44px,不包括statusBarHeight）
			uni.getSystemInfo({
				success: (res) => {
					this.pageHeight = res.windowHeight - res.statusBarHeight - 44
				}
			})
		},
		onLoad(){
			this.userName = uni.getStorageSync('userName'); //将名字存入缓存	
			this.messages[0] = "推荐问题：慢性疾病、高血压、糖尿病"
		},
		// onLoad() {
		// 	this.initListener()
		// 	this.userName = uni.getStorageSync('userName'); //将名字存入缓存	
		// 		var that = this

		// 		// 创建一个 WebSocket 连接
		// 		wx.connectSocket({
		// 			url: 'ws://127.0.0.1:9090/websocket/' + this.userName,
		// 		})

		// 		// 监听 WebSocket 连接打开事件
		// 		wx.onSocketOpen((res)=> {

		// 			// 通过 WebSocket 连接发送数据
		// 			wx.sendSocketMessage({
		// 				data: "已上线",
		// 				success:((res)=>{
		// 					console.log("数据发送成功")

		// 					// 监听 WebSocket 接受到服务器的消息事件
		// 					wx.onSocketMessage((re)=> {
		// 						console.log(re)
		// 						this.messages.push(re.data)
		// 					})
		// 				}),
		// 				fail() {
		// 					console.log("数据发送失败")
		// 				}
		// 			})
		// 		})
		// },
		onShow() {

		},
		// onUnload() {
		// 	this.destoryListener()
		// },
		mounted() {
			//这里获取footer元素的高度，根据不同平台用的方式不同，对于uniapp的dom定位方法应该是通用的。特别注意，一定要在this.$nextTick方法里写，不然可能页面还没渲染出footer元素
			this.$nextTick(() => {
				// #ifdef H5
				this.footerHeight = this.$refs.footer.$el.offsetHeight
				this.height = this.pageHeight - this.footerHeight
				// #endif
				// #ifdef APP-PLUS
				uni.createSelectorQuery().in(this).select("#footer").boundingClientRect((data) => {
					this.footerHeight = data.height
					this.height = this.pageHeight - this.footerHeight
				}).exec()
				// #endif
			})
		},
		methods: {
			initListener() {
				//监听键盘的高度变化，让sroll-view的高度随之变化
				uni.onKeyboardHeightChange(res => {
					let keyBoardHeight = res.height
					if (this.keyBoardHeight == 0 && keyBoardHeight > 0) {
						this.keyBoardHeight = keyBoardHeight
					}
					if (keyBoardHeight > 0) {
						this.height = this.height - this.keyBoardHeight
					} else {
						this.height = this.height + this.keyBoardHeight
					}
				})
			},
			destoryListener() {
				uni.offKeyboardHeightChange((res) => {
					console.log("offKeyboardHeightChange...")
				})
			},
			handleSend() {
				// wx.sendSocketMessage({
				// 	data: this.text,
				// 	success: (res) => {
				// 		// 监听 WebSocket 接受到服务器的消息事件
				// 		wx.onSocketMessage((re)=> {
				// 			console.log("SocketMessage: " + re.data)
				// 			this.messages.push(re.data)
				// 			this.text = ''

				// 		})

				// 	},
				// 	fail() {
				// 		console.log("数据发送失败")
				// 	}
				// })
				uni.request({
					url: baseUrl + '/chatbot/askQuestion?question=' + this.text,
					methods: 'GET',
					header: {
						'token': this.token
					},
					success: (res) => {
						this.messages.push(this.userName+": "+this.text)
						this.messages.push("ChatBot: "+res.data)
						this.text = ''
						console.log(res.data)
					}

				})
			}
		}
	}
</script>

<style>
	body {
		padding-bottom: 18%;
	}
	.message{
		background-color: #FFFFFF;
		width: 70%;
		border-radius: 30rpx;
		margin-right: 10%;
		margin-left: 5%;
		padding: 20rpx;
		margin-top: 30rpx;
	}
	.message:nth-child(2n){
		background-color: lightgreen;
		width: 70%;
		border-radius: 30rpx;
		margin-left: 20%;
		padding: 20rpx;
		margin-top: 30rpx;
	}

	.footer {
		width: 100%;
		background-color: #E9EDF4;
		display: flex;
		position: fixed;
		bottom: 0;
	}

	.footer .content-wrap {
		width: 78%;
		margin-left: 2%;
	}

	.footer .content {
		width: 100%;
		box-sizing: border-box;
		margin: 14rpx 0;
		background-color: #FFFFFF;
		border-radius: 10rpx;
		caret-color: #01B4FE;
	}

	.footer .btn-wrap {
		width: 18%;
		margin-right: 2%;
	}

	.footer .btn {
		width: 15%;
		height: 65rpx;
		font-size: 26rpx;
		margin-left: 2%;
		background-color: #01B4FE;
		color: #FFFFFF;
		position: fixed;
		bottom: 14rpx;
		border: 0;
		outline: none;
	}

	.footer .btn-wrap .disabled {
		background-color: #aae8f5;
	}

	/deep/ .uni-textarea-wrapper {
		max-height: 180rpx;
	}
</style>
