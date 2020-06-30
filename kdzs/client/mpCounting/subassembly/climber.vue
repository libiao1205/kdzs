<template>
	<view class="w-100">
		<image v-if="rate < 100" :src="img" class="posA" :style="'left:'+(mrLeft+(num*rate))+'%;bottom:'+(47/100*rate)+'%;height:'+(15-(6/100*rate))+'%;width:'+(20-(7/100*rate))+'%'"></image>
		<image v-if="rate === 100" src="../static/img/climber-per.png" class="climber-per-img" :style="'left:'+perMrLeft+'%'"></image>
		<view class="bar-box">
			<view v-if="flag" class="progress-bar fx-l" :style="'top:'+barTop+'%'">
				<view class="school-color" :style="'background-color: '+progressBarColor"></view>
				<view>
					{{schoolName}}：{{recruitPeople}}/{{planPeople}}
				</view>
			</view>
			<view v-if="!flag" class="w-100 h-100 fx-t-v-h color-w">
				暂无计划
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		props:{
			img:{
				type:String,
				default:''
			},
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
			schoolName:{
				type:String,
				default:''
			},
			mrLeft:{
				type:Number,
				default:0
			},
			perMrLeft:{
				type:Number,
				default:0
			},
			barTop:{
				type:Number,
				default:0
			},
			progressBarColor:{
				type:String,
				default:'#0FAEFF'
			},
			num:{
				type:Number,
				default:0.25
			},
			flag:{
				type:Boolean,
				default:true
			}
		},
	}
</script>

<style>
	.climber-per-img{
		position: absolute;
		width: 16%;
		height:10%;
		top:37%;
	}
	.progress-bar{
		width: 90%;
		height:25%;
		color:#FFFFFF;
		position: absolute;
		left: 3%;
	}
	.bar-box{
		width:60%;
		height:15%;
		border:1px solid #D1D4D5;
		position: absolute;
		top:5%;
		left:3%;
	}
	.school-color{
		width: 30upx;height: 30upx;
		margin-top: 1.5%;
		margin-right: 3%;
	}
</style>
