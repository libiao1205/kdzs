<template>
	<view style="z-index: 100;">
		<view style="position: absolute;
					width:100%;
					height:auto;
					bottom:0;
					">		
			<view style="width: 100%;" class="fx-r">
				<view style="width: 60%;" class="fx-t">
					<view style="position:relative;
							width:100%;
							height:auto;margin-bottom: -5px;">
						<image :src="imageUrl+'/images/file?path=file/bg3-2.png'" id="bgImg" style="width:100%;" mode ="widthFix"/>
					</view>
					<view style="position:relative;
							width:100%;
							height:auto;margin-bottom: -5px;">
							<view v-if="flag_show" style="width:100%;height: auto;position: absolute;top:0px;">
								<image :src="imageUrl+'/images/file?path=file/streamer.png'" id="streamer" style="width:100%;position: absolute;top:0" mode ="widthFix"/>
							</view>
						<image :src="imageUrl+'/images/file?path=file/bg3-1.png'" id="bgImg" style="width:100%;" mode ="widthFix"/>
					</view>
				</view>
			</view>
			<image :src="imageUrl+'/images/file?path=file/bg4.png'" id="bgImg" style="width:100%;margin-bottom: -3px;" mode ="widthFix"/>			
		</view>
		
		<view class="running_box" :style="'left:'+leftNum+'%;bottom:'+bottomNum+'%;'">
			<image :src="imageUrl+'/images/file?path=file/vehicle.gif'" class="running-img" ></image>		
		</view>
		
		<view class="fx-l-y-v fx-b-v progress">
			<view class="title-msg">
				{{recruitPeople}}/{{planPeople}}
			</view>
			<view class="progress_bar" :style="'height:'+progressNum+'%;'">
			</view>
		</view>
	</view>
	<!-- <view class="w-100 posA" :style="'top:'+mrTop+'%'">
		<view class="posA" :class="{'slide':aniStart}" :style="'left: calc('+rate+'% - '+(rate*1.2)+'px);'">
			<view class="bike bikeAni running-height" v-if="flag" :style="'background-image: url('+imageUrl+'/images/file?path=file/bike.png)'"></view>
		</view>
		<view class="bike running-height" v-if="!flag" :style="'background-image: url('+imageUrl+'/images/file?path=file/bike1.png)'"></view>
		<view class="progress running-height ">
			<view class="progress-bar-l fx-t-v-h" :class="{'slide':aniStart}" :style="'width: calc('+rate+'% - '+(rate*1.2)+'px);background-color: '+progressBarColor">
				<view v-if="rate >= 50">
					{{recruitPeople}}{{flag ? '/' : ''}}{{planPeople}}
				</view>
			</view>
			<view class="progress-bar-r fx-t-v-h" :class="{'slide':aniStart}" :style="'width: calc('+(100 - rate)+'% - '+(120-rate*1.2)+'px);background-color: '+progressBarColor">
				<view v-if="rate < 50">
					{{recruitPeople}}{{flag ? '/' : ''}}{{planPeople}}
				</view>
			</view>
		</view>
	</view> -->
</template>

<script>
	import _config from '../common/request/config.js'
	import AnimationItems from '../utils/AnimationItems.js'
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
				leftNum: 16,
				bottomNum: 0,
				progressNum: 0,
				flag_show: false,
				
			}
		},
		methods:{
			start(){	
				//计算图片高度
				// if(this.streamerTop == 0){
				// 	const query = uni.createSelectorQuery().in(this);
				// 	query.select('#streamer').boundingClientRect(data => {
				// 	this.streamerTop = data.height;
					
				// 	}).exec();
				// }
				this.Interval = setInterval(()=>{
					if((this.rate * 0.21) <= this.bottomNum){
						clearInterval(this.Interval);
						if(this.progressNum < this.rate){
							this.progressNum = this.rate;
						}
						if(this.rate == 100){
							this.flag_show = true;
						}
					}
					this.leftNum = this.leftNum + 0.24;
					this.bottomNum = this.bottomNum + 0.2;
					if(this.progressNum <= this.rate){
						this.progressNum+= 1;
					}
				},100);
			},
			stop(){
				clearInterval(this.Interval);
			}
		},
		watch: {
			//正确给 cData 赋值的 方法
			rate: function(newVal,oldVal){
				this.rate = newVal;  
				this.rate ? this.start() : ''; 
			}
		},
	}
</script>

<style>
	.running_box{
		height:300upx;
		position: absolute;
		left:0;
	}
	.title-msg{
		color:#FFFFFF;
		font-weight: 600;
		width: 18upx;
		word-wrap: break-word; 
		word-break: normal;
		z-index:100;
	}
	.running-img{
		width:300upx;
		height:300upx;
	}
	.progress{
		position: absolute;
		left: 10upx;
		top: 8%;
		width:26upx;
		height: 650upx;
		border-radius:26upx;
		background-color: #ccced4;
	}
	.progress .progress_bar{
		position: absolute;
		border-radius:26upx;
		width:100%;
		background-color: rgb(255, 73, 73);
	}
</style>
