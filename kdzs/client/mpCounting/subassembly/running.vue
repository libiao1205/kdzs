<template>
	<view class="w-100 posA" :style="'top:'+mrTop+'%'">
		<view class="bike bikeAni running-height" v-if="flag" :style="'left: calc('+rate+'% - '+(rate*0.6)+'px);background-image: url('+imageUrl+'/images/file?path=file/bike.png)'"></view>
		<view class="bike running-height" v-if="!flag" :style="'left: calc('+rate+'% - '+(rate*0.6)+'px);background-image: url('+imageUrl+'/images/file?path=file/bike1.png)'"></view>
		<view class="progress running-height ">
			<view class="progress-bar-l fx-t-v-h" :style="'width: calc('+rate+'% - '+(rate*0.6)+'px);background-color: '+progressBarColor">
				<view v-if="rate >= 50">
					{{recruitPeople}}{{flag ? '/' : ''}}{{planPeople}}
				</view>
			</view>
			<view v-if="planPeople > 0 " class="progress-bar-r fx-t-v-h" :style="'width: calc('+(100 - rate)+'% - '+(60-rate*0.6)+'px);background-color: '+progressBarColor">
				<view v-if="rate < 50">
					{{recruitPeople}}{{flag ? '/' : ''}}{{planPeople}}
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import _config from '../common/request/config.js'
	export default {
		props:{
			rate:{
				type:Number,
				default:0
			},
			recruitPeople:{
				type:String,
				default:''
			},
			planPeople:{
				type:String,
				default:''
			},
			mrTop:{
				type:Number,
				default:0
			},
			progressBarColor:{
				type:String,
				default:'#0FAEFF'
			},
			flag:{
				type:Boolean,
				default:true
			}
		},
		data(){
			return {
				imageUrl : _config.imageUrl,
			}
		}
	}
</script>

<style>
	.bike {
        width: 60px;
		background-size: cover;
		-webkit-background-size: cover;
		-o-background-size: cover;
        position: absolute;
    }
    /*steps属性是把动画拆分N次显示-*/
    .bikeAni {
        -webkit-animation:bikeAni 5s steps(99) infinite;
        animation:bikeAni 5s steps(99) infinite;
		
    }
    @keyframes bikeAni{
        0%{background-position:0 5940px;}
        99%{background-position:0 0;}
    }	
	.redFlag{
		width:15%;
		height:100%;
		position: absolute;
		right:0;
		bottom: 0;
		z-index: 100;
	}
	.progress{
		width:100%;
		position: absolute;
		left: 0%;
	}
	.progress-bar-l{
		height:40%;
		color:#FFFFFF;
		position: absolute;
		bottom: 0%;
		left: 0%;
	}
	.progress-bar-r{
		height:40%;
		color:#FFFFFF;
		position: absolute;
		bottom: 0%;
		right: 0%;
	}
	.running-height{
		height:60px;
	}
</style>
