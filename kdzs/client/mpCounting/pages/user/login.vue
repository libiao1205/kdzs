<template>
	<view class="content">
		<view class="fx-t">
			<view class="fx-t-v-h mr-s">
				<image src="../../static/img/logLogo.png" class="logo-title-img">
			</view>
			<view class="pd-s fx-t-v-h ">
				<view class="line50 w-100 bd-b-1 fx-t-v-h">
					<input class="uni-input w-100" type="number" placeholder="请输入手机号"
						v-model="phoneNumber"  placeholder-style="color: #969696;" />
				</view>
				<view class="line50 w-100 bd-b-1 fx-t-v-h">
					<input class="uni-input w-100" placeholder="请输入密码"
						v-model="password"   password="true" placeholder-style="color: #969696;" />
				</view>
				<button class="primary w-100 line45 mr-t-s" @click="loginByUsername" >登 录</button>
			</view>
			<view class="home-content-font font-size-14 text-align w-100">
				功能介绍:设定招生计划、填报招生信息、查阅招生统计<br>
				注:仅限上海开放大学招生填报使用。
			</view>
			<!-- <view class="fx-t-v-h">
				<button class="fx-t-v-h noBorder" open-type="getPhoneNumber" @getphonenumber="getPhoneNumber" ><image src="../../static/img/wechat.png" class="small-icon" @click="toAuthorize" /></button>
			</view> -->
		</view>
	</view>
</template>

<script>
	import { mapState,mapActions,mapGetters } from 'vuex'
	export default {
		components: {
		},
		onLoad() {
		},
		onShow() {
		},
		computed: {
			...mapGetters({
				isPermissionCode : 'constant/isPermissionCode',
			}),
		},
		data() {
			return {
				phoneNumber:'',
				password:'',
			}
		},
        methods: {
			...mapActions({
				registerPhone: 'auth/registerPhone',
				loginUserName: 'auth/loginUserName',
				loadPermissionCode: 'page/loadPermissionCode',
			}),
			getPhoneNumber(e) {
				if (!e.detail.encryptedData) return;
				let data = {
					iv: e.detail.iv,
					encryptedData: e.detail.encryptedData,
					mpType: "COUNTING"
				};
				this.registerPhone(data).then((res) => {
					if (res) {
						uni.reLaunch({
							url: '../recruit/counting/homeAnime',
						});
					}
				});
			},
			loginByUsername() {
				let data = {
					username: this.phoneNumber,
					password: this.password,
				};
				this.loginUserName(data).then((res) => {
					if (res) {
						this.loadPermissionCode().then(() =>{
							uni.reLaunch({
								url: '../recruit/counting/homeAnime',
							});
						});
					}
				});
			}
        }
    }
</script>

<style>
</style>
