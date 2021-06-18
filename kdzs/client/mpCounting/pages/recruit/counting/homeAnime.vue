<template>
	<view class="w-100 h-100">
		<!-- <view class="d" :style="'-webkit-transform:rotate('+xuanZhuanNum+'deg);top:'+saHuaTopNum+'%;'"></view> -->
		<!-- <image src="../../../static/img/climber.jpg" class="climber-img"></image>
		<climber ref="myClimber"
				 :rate="rate1"
				 :recruitPeople="isNull(recruitStudent.sumPeople)"
				 :planPeople="isNull(recruitStudent.planSumPeople)"
				 :flag="flag"
				></climber>
				<view class="fx-t-v-h">
					<button  size="mini" @click="toAuthorize" class="mini-btn posA" style="z-index:10;bottom: 5%; background-color: rgb(247,252,246);">进入首页</button>
				</view> -->
				<!-- <view class="guideboard-text">{{msg}}<br>{{day}}</view>
				<image src="../../../static/img/timg1.jpg" class="bg-img"></image> -->
				
				
				<image :src="imageUrl+'/images/file?path=file/vehicleBgImg.gif'" id="bgGif" class="climber-img"></image>
				<running :mrTop="55"
						 progressBarColor="rgb(139,193,37)"
						 :flag="flag"
						 :recruitPeople="isNull(recruitStudent.sumPeople)"
						 :rate="rate1"
						 :planPeople="isNull(recruitStudent.planSumPeople)"
						 :aniStart="aniStart"
				></running>
				
				<view class="guideboard-img">
					<image style="width: 100%;height: 100%;" src="../../../static/img/guideboard.png"></image>
					<view @click="toAuthorize" class="posA fx-t-v-h" style="top: 19%;right:18%;width:60%;height:26%;color:#FFFFFF;">首页</view>
				</view>
				
		<!-- <swiper class="swiper w-100 h-100" indicator-active-color="#07c803" @change="loadSlide" :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval" :duration="duration">
			
			<swiper-item>
				<image src="../../../static/img/sky.jpg" class="sky-img"></image>
				<image src="../../../static/img/guideboard.png" class="guideboard-img"></image>
				<view class="guideboard-text">{{msg}}<br>{{day}}</view>
				<image src="../../../static/img/timg1.jpg" class="bg-img"></image>
				<running :mrTop="55"
						 progressBarColor="rgb(139,193,37)"
						 :flag="flag"
						 :recruitPeople="isNull(recruitStudent.sumPeople)"
						 :rate="rate2"
						 :planPeople="isNull(recruitStudent.planSumPeople)"
						 :aniStart="aniStart"
				></running>
				<view class="fx-t-v-h">
					<button  size="mini" @click="toAuthorize" class="mini-btn posA" style="z-index:10;bottom: 5%; background-color: rgb(247,252,246);">进入首页</button>
				</view>
			</swiper-item>
			<swiper-item>
					<image src="../../../static/img/climber.jpg" class="climber-img"></image>
					<climber ref="myClimber"
							 :rate="rate1"
							 :recruitPeople="isNull(recruitStudent.sumPeople)"
							 :planPeople="isNull(recruitStudent.planSumPeople)"
							 :flag="flag"
							></climber>
							<view class="fx-t-v-h">
								<button  size="mini" @click="toAuthorize" class="mini-btn posA" style="z-index:10;bottom: 5%; background-color: rgb(247,252,246);">进入首页</button>
							</view>
			</swiper-item>
		</swiper> -->
	</view>
</template>

<script>
	import { mapState,mapActions,mapGetters } from 'vuex';
	import running from '../../../subassembly/running.vue';
	import climber from '../../../subassembly/climber.vue'
	import _config from '../../../common/request/config.js'
	export default {
		components: {running,climber},
		computed: {
			...mapState({
				hasLogin : state => state.auth.token,
			}),
			...mapGetters({
				isUndefined: 'constant/isUndefined',
				isNull: 'constant/isNull',
				isPermissionCode: 'constant/isPermissionCode',
				timeDifference: 'constant/timeDifference',
				getSumSchoolIds: 'constant/getSumSchoolIds'
			})			
		},
		async onLoad(option) {
			if (!uni.getStorageSync('token')) {
				/* uni.redirectTo({
					url: '../../user/login'
				}); */
			}else{
				this.schoolIds = this.getSumSchoolIds();
				this.loadSchoolOne().then(() =>{
					this.arrayOne = uni.getStorageSync('schoolOne');
					if(!this.isPermissionCode('view')){
						if(this.arrayOne != null && this.arrayOne != '' ){
							this.schoolId = this.arrayOne.schoolId;
						}
					}
					this.loadCurrSeason().then((res) =>{
						this.currSeason = res;
						if(this.currSeason != null && this.currSeason != ''){
							this.day = this.timeDifference(this.currSeason.recruitEndDate);
							this.doStatistics();
							this.msg = '倒计时';
							this.flag = true;
						}
					});						
				});
			}
		},
		data() {
			return {
				recruitStudent:{},
				arrayOne:{},
				index: 0,
				currSeason: [],
				schoolId: 0,
				msg: '暂无计划',
				day:'',
				flag: false,
				indicatorDots: true,
				autoplay: false,
				interval: 2000,
				duration: 500,
				schoolIds: '',
				rate1: 0,
				rate2: 0,
				aniStart: false,
				imageUrl : _config.imageUrl,
			}
		},
		methods:{
			...mapActions({
				loadCurrSeason: 'page/loadCurrSeason',
				loadStatisticsAnime: 'page/loadStatisticsAnime',
				loadSchoolOne: 'page/loadSchoolOne',
			}),
			
			doStatistics(){
				let data = {schoolId:this.schoolId,schoolIds:this.schoolIds,seasonId:this.currSeason.seasonId,
				recruitYear:this.currSeason.recruitYear,recruitSeason:this.currSeason.recruitSeason};
				this.loadStatisticsAnime(data).then((res) =>{
					this.recruitStudent = res;
					/********临时代码,日后需删除**********/
					// this.$nextTick(()=>{
					// 	let e ={detail:{current:0}}
					// 	this.loadSlide(e);
					// })
					/**临时注释，日后需解封**/
					this.$nextTick(()=>{
						this.rate1 = this.isNull(this.recruitStudent.recruitRate);
					})
				});
			},
			toAuthorize() {
				//this.$refs.myClimber.stop();
				uni.reLaunch({
					url: './home',
				});
			},
			loadSlide(e){
				this.aniStart = false;
				this.rate1 = 0;
				this.rate2 = 0;
				let num = this.isNull(this.recruitStudent.recruitRate)
				if(e.detail.current == 1){
					this.rate1 = num;
				}else if(e.detail.current == 0){
					this.$refs.myClimber.stop();
					this.$nextTick(()=>{
						this.rate2 = num;
						this.aniStart = true;
					})
				}
			}
		}
	}
</script>

<style>
	.d{
		transition:all 10s;
		position: absolute;
		top:10%;
		left:40%;
		border:1px solid red;
		width:30px;
		height:30px;
	}
	.climber-img{
		width: 100%;
		height:100%;
	}
	.bg-img{
		position: absolute;
		width:100%;
		height:40%;
		top:60%;
	}
	.sky-img{
		position: absolute;
		width:100%;
		height:60%;
		top:0%
	}
	.guideboard-img{
		position: absolute;
		width:20%;
		height:15%;
		bottom:0%;
		right:10%;
	}
	.guideboard-text{
		position: absolute;
		width:30%;
		height:25%;
		color:#FFFFFF;
		font-size: 30upx;
		top:47%;
		left:8%
	}
	.track-img{
		position: absolute;
		width: 10%;
		height: 37%;
		top:63%;
		right:0;
		z-index: 100;
	}
</style>
