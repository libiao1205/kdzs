<template>
    <view class="fx-t bg-gray s-100">
		<view class="fx-l-v bg-w pd-s ">
			<view class="logo-img">
				<open-data  type="userAvatarUrl"></open-data>
			</view>
			<view class="fx-t-v mr-l-s">
				<view v-if="hasLogin" class="fx-start font-sm-size">
					<text>{{userName}}</text>
				</view>	
				<view v-if="!hasLogin" class="fx-start font-sm-size bd-b-1" @click="toAuthorize">
					<text>您还未登录，点击登录</text>
				</view>	
				<view class="font-xxs-size color-gray2 mr-t-xs fx-start">
					<text>微信名：</text><open-data type="userNickName" lang="zh_CN"></open-data>
				</view>
			</view>
		</view>
		<view class="fx-t bg-w mr-t-xs">
			<view class="pd-xs fx-l-v-bt bd-b-1">
				<text >姓名</text>
				<view>
					<text class="color-gray2">{{getNotFilledName(userName)}}</text>
				</view>
			</view>
			<view class="pd-xs fx-l-v-bt bd-b-1" >
				<text >所属分校</text>
				<view>
					<text class="color-gray2">{{organization}}</text>
				</view>
			</view>
		</view>
		<!-- <view class="fx-t bg-w mr-t-xs" @click="userAdmin">
			<view class="pd-xs fx-t-v bd-b-1">
				<text>修改密码</text>
			</view>
		</view> -->
		<view class="fx-t bg-w mr-t-xs" @click="exitInfo">
			<view class="pd-xs fx-t-v bd-b-1">
				<text>退出程序</text>
			</view>
		</view>
    </view>
</template>

<script>
	import {mapActions, mapGetters, mapState} from 'vuex'
	import comPopupBottom from "@/components/uni-popup/uni-popup.vue"
	import uniIcons from '@/components/uni-icons/uni-icons.vue'	
    export default {
		components: {
			comPopupBottom,
			uniIcons,
		},
		onShow() {
			this.hasLogin = uni.getStorageSync('token');
			this.userName = uni.getStorageSync('userName');
			this.organizations = uni.getStorageSync('organizations');
			if(this.organizations == null){
				this.organization = "";
				return ;
			}
			if(this.organizations ) {
				this.organization = this.organizations[0].name;
			}
			
		},
		computed: {
			...mapGetters({
				getNotFilledName: 'constant/getNotFilledName',
			}),
			
		},
		data() {
			return {
				organizations:[{name:""}],
				organization: "",
				hasLogin: "",
				userName: "",
			}
		},
        methods: {
			...mapActions({
				logout : 'auth/logout',
			}),
			toAuthorize() {
				uni.navigateTo({url: './login'});
			},
			exitInfo(){
				this.logout();
				uni.navigateTo({url: './login'});
			},
			userAdmin(){
				uni.navigateTo({url: './userAdmin'});
			}
        },
    }
</script>

<style>
	.logo-img {
		width: 150upx;
		height: 150upx;
		overflow:hidden;  
		display: block;  
		border-radius: 50%;  
		border: 2px solid #fff;  
		box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);  
	}
</style>