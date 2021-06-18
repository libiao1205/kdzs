<template>
	<view class="w-100">
		<image v-if="flag_show" :src="imageUrl+'/images/file?path=file/flag.png'" class="climber-flag"></image>
		<view  class="climber_box" :style="'left:'+leftNum+'%;bottom:'+bottomNum+'%;'">
			<view class="title-msg" v-if="flag">
				{{recruitPeople}}/{{planPeople}}
			</view>
			<image :src="img" class="climber-img"></image>
		</view>
	</view>
</template>
<script>
	import climber_left from '../static/img/climber-son-left.gif'
	import climber_right from '../static/img/climber-son-right.gif'
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
			flag:{
				type:Boolean,
				default:true
			}
		},
		data(){
			return{
				leftNum: 0,
				bottomNum: 5,
				Interval:'',
				bend: 0,
				img:climber_left,
				flag_show:false,
				imageUrl : _config.imageUrl,
			}
		},
		methods:{
			start(){
				this.leftNum = 4.5;
				this.bottomNum = 5;
				this.bend = 0;
				this.Interval = setInterval(()=>{
					if((100 * 0.71 + 5) <= this.bottomNum){
						clearInterval(this.Interval);
					}
					if(this.bend === 0){
						this.flag_show = false;
						this.img = climber_left;
						this.leftNum = this.leftNum + 0.6;
						this.bottomNum = this.bottomNum + 0.3;
						if(this.bottomNum >= (28.4)){
							this.bend = 1;
						}
					}
					if(this.bend === 1){ 
						this.img = climber_right;
						this.leftNum = this.leftNum - 0.3;
						this.bottomNum = this.bottomNum + 0.4;
						if(this.bottomNum >= 40){
							this.bend = 2;
						}
					}
					if(this.bend === 2){
						this.img = climber_left;
						this.leftNum = this.leftNum + 0.31;
						this.bottomNum = this.bottomNum + 0.4;
						if(this.bottomNum >= 63){
							this.bend = 3;
						}
					}
					if(this.bend === 3){
						this.img = climber_right;
						this.leftNum = this.leftNum - 0.42;
						this.bottomNum = this.bottomNum + 0.3;
						if(this.bottomNum >= 76){
							clearInterval(this.Interval);
							this.flag_show = true;
						}
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
	.title-msg{
		color:rgb(249, 18, 34);
		font-weight: 600;
		text-align: center;
		background-color: #EFEFF4;
		padding: 5upx;
	}
	.climber_box{
		height:150upx;
		position: absolute;
		left:0;
		bottom: 20%;
	}
	.climber_box_ani {
		transition:all 15s ease-out; 
	}
	.climber-img{
		width:150upx;
		height:150upx;
	}
	.climber-flag{
		width: 190upx;
		height: 260upx;
		position: absolute;
		top:6%;
		left:51%;
	}
</style>
