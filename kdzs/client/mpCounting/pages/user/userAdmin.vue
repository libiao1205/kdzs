<template>
	<view class="w-100" style="padding-top: 170upx;">
		<view class="fx-l-y-v mr-t-s">
			<view class=" mr-r-xs fx-t-v-h content-font">
				旧密码:
			</view>
			<view class=" fx-t-v-h">
				<input class="uni-input input-border mr-r-xs input-width" type="password" v-model="oldPassword" />
			</view>
		</view>
		<view class="fx-l-y-v mr-t-s">
			<view class=" mr-r-xs fx-t-v-h content-font">
				新密码:
			</view>
			<view class=" fx-t-v-h">
				<input class="uni-input input-border mr-r-xs input-width" type="password" v-model="newPassword" />
			</view>
		</view>
		<view class="fx-l-y-v mr-t-s" style="margin-right: 25upx;">
			<view class=" mr-r-xs fx-t-v-h content-font">
				确认密码:
			</view>
			<view class=" fx-t-v-h">
				<input class="uni-input input-border mr-r-xs input-width" type="password"v-model="rePassword" />
			</view>
		</view>
		<view class="w-100 fx-l-y-v-bt mr-t-s" >
			<button type="primary" class="w-90" @click="update" style="height:60upx;line-height: 52upx;">提交</button>
		</view>
	</view>
</template>

<script>
	import {mapActions} from 'vuex';
	export default{
		data(){
			return{
				oldPassword:'',
				newPassword:'',
				rePassword:'',
			}
		},
		
		methods:{
			...mapActions({
				updatePassword: 'user/updatePassword',
				logout:'auth/logout',
			}),
			update(){
				let data = {oldPassword:this.oldPassword,
				newPassword:this.newPassword,
				rePassword:this.rePassword};
				let thes = this;
				this.updatePassword(data).then((res)=>{
					if(res){
						uni.showModal({
							content: "执行成功，请重新登陆",
							showCancel: false,
							success() {
								thes.logout();
								uni.navigateTo({url: './login'});
							}
						});
					}
				});
			}
		}
	}
</script>

<style>
</style>
