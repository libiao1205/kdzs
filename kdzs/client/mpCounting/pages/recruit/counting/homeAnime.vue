<template>
	<view class="w-100 h-100" @click="toAuthorize">
		 <swiper class="swiper w-100 h-100" indicator-active-color="#07c803"  :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval" :duration="duration">
			<swiper-item>
					<image src="../../../static/img/climber.jpg" class="climber-img"></image>
					<climber img="../../../static/img/climber-son3.png"
							 :mrLeft="34"
							 :perMrLeft="52" 
							 :rate="isUndefined(recruitStudent.gkRate)"
							 :recruitPeople="isNull(recruitStudent.gkPeople)"
							 :planPeople="isNull(recruitStudent.planGkPeople)"
							 schoolName="国开本科"
							 :barTop="3"
							 :flag="flag"
							 :num="0.2"
							 progressBarColor="#ff55ff"></climber>
					<climber img="../../../static/img/climber-son1.png"
					         :mrLeft="23" 
							 :perMrLeft="45" 
							 :rate="isUndefined(recruitStudent.technicalRate)"
							 :recruitPeople="isNull(recruitStudent.technicalPeople)"
							 :planPeople="isNull(recruitStudent.planTechnicalPeople)"
							 schoolName="上开本科"
							 :barTop="37"
							 :flag="flag"
							 :num="0.22"
							 progressBarColor="rgb(48,43,105)"></climber>
					<climber img="../../../static/img/climber-son2.png"
					         :mrLeft="12" 
					         :perMrLeft="39" 
							 :rate="isUndefined(recruitStudent.undergraduateRate)"
							 :recruitPeople="isNull(recruitStudent.undergraduatePeople)"
							 :planPeople="isNull(recruitStudent.planUndergraduatePeople)"
							 schoolName="上开专科"
							 :barTop="70"
							 :flag="flag"
							 :num="0.25"
							 progressBarColor="rgb(139,193,37)"></climber>
			</swiper-item>
			<swiper-item>
				<image src="../../../static/img/sky.jpg" class="sky-img"></image>
				<image src="../../../static/img/guideboard.png" class="guideboard-img"></image>
				<view class="guideboard-text">{{msg}}<br>{{day}}</view>
				<image src="../../../static/img/timg1.jpg" class="bg-img"></image>
				<running :mrTop="65"
						 progressBarColor="#ff55ff"
						 :flag="flag"
						 :recruitPeople="isNull(recruitStudent.gkPeople)"
						 :rate="isUndefined(recruitStudent.gkRate)"
						 :planPeople="isNull(recruitStudent.planGkPeople)"
				></running>
				<running :mrTop="75"
						 progressBarColor="rgb(48,43,105)"
						 :flag="flag"
						 :recruitPeople="isNull(recruitStudent.technicalPeople)"
						 :rate="isUndefined(recruitStudent.technicalRate)"
						 :planPeople="isNull(recruitStudent.planTechnicalPeople)"
						 
				></running>
				<running :mrTop="85"
						 progressBarColor="rgb(139,193,37)"
						 :flag="flag"
						 :recruitPeople="isNull(recruitStudent.undergraduatePeople)"
						 :rate="isUndefined(recruitStudent.undergraduateRate)"
						 :planPeople="isNull(recruitStudent.planUndergraduatePeople)"
				></running>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	import { mapState,mapActions,mapGetters } from 'vuex';
	import running from '../../../subassembly/running.vue';
	import climber from '../../../subassembly/climber.vue'
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
			}),
		},
		async onLoad(option) {
			if (!uni.getStorageSync('token')) {
				uni.redirectTo({
					url: '../../user/login'
				});
			}else{
				this.loadCurrSeason().then((res) =>{
					this.currSeason = res;
					if(this.currSeason != null && this.currSeason != ''){
						this.day = this.timeDifference(this.currSeason.recruitEndDate);
						this.doStatistics();
						this.msg = '倒计时';
						this.flag = true;
					}
				});
				this.arrayOne = uni.getStorageSync('schoolOne');
				if(!this.isPermissionCode('view')){
					if(this.arrayOne != null && this.arrayOne != '' ){
						this.schoolId = this.arrayOne.schoolId;
					}
				}
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
				duration: 500
			}
		},
		methods:{
			...mapActions({
				loadCurrSeason: 'page/loadCurrSeason',
				loadStatisticsAnime: 'page/loadStatisticsAnime',
			}),
			doStatistics(){
				let data = {schoolId:this.schoolId,seasonId:this.currSeason.seasonId};
				this.loadStatisticsAnime(data).then((res) =>{
					this.recruitStudent = res;
				});
			},
			toAuthorize() {
				uni.reLaunch({
					url: './home',
				});
			},
		}
	}
</script>

<style>
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
		width:30%;
		height:25%;
		top:42%
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
