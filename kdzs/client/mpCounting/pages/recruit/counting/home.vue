<template>
	<view class="content">
		<view class="fx-t-v w-100" style="position:fixed;top:0;background-color: #FFFFFF; z-index: 100;padding-top:15%">
			<image src="../../../static/img/logo.png" class="home-logo-title-img">
			<hr class="hr-solid mr-t-xs">
			<view v-if="currSeason" class="w-100 fx-t-v">
				<view class="fx-t-v mr-t-xs w-100 title-max-font">
					{{recruitYear}}年{{recruitSeason}}招生
				</view>
				<view class="fx-l-v-bt mr-t-four w-90 ">
					<view class="uni-list-cell-db">
						<picker v-if="isPermissionCode('view')" @change="bindPickerChange" :value="index" :range="arrayInfo">
							<view class="uni-input home-paicker fx-t-v color-title2" >{{arrayInfo[index]}}</view>
						</picker>
						<view v-if="!isPermissionCode('view')" class="uni-list-cell-db mr-b-xs">
							{{arrayOne.name}}
						</view>
					</view>
					<view class="uni-list-cell-left home-input fx-t-v line10">
						{{getCurrDate()}}
					</view>
				</view>
			</view>
		</view>
		<view class="fx-t" style="padding-top: 45%;">
			<view class="fx-t-v font h-90">
				<view class="uni-column fx-l-y-v mr-t-xs home-content pd-xs" >
					<view class=" uni-row w-100">
						<view v-if="currSeason">
							<view class="flex-item uni-column mr-t-four">
								<view class="fx-l-y-v-bt uni-row font-weight">
									总招生人数	
								</view>
								<view class="fx-l-y-v-bt uni-row retract color-title2 mr-t-min">
									{{isUndefined(currYear.sumPeople)}}人
								</view>
								<view class="fx-l-y-v-bt w-100 mr-t-min">
									<view class="fx-r uni-row retract">
										<span class="home-content-font ">
											{{onRecruitYear}}年{{recruitSeason}}:{{isUndefined(oldYear.sumPeople )}}人	
										</span>
									</view>
									<view class="fx-r uni-row home-content-font color-rate2" v-if="currYear.rateType === 0">
										<uni-icons type="undo-filled" class="icon-select" color="#306191" size="20"></uni-icons>
										{{isUndefined(currYear.recruitRate)}}%		
									</view>
									<view class="fx-r uni-row home-content-font color-rate1" v-if="currYear.rateType === 1">
										<uni-icons type="redo-filled" class="icon-select" color="#e86c00" size="20"></uni-icons>
										{{isUndefined(currYear.recruitRate)}}%		
									</view>
								</view>
							</view>
							<view class="flex-item uni-column mr-t-xs">
								<hr class="hr-dashed">
							</view>
							<view class="flex-item uni-column mr-t-xs">
								<view class="fx-l-y-v-bt uni-row color-title2 font-weight">
									上开本科招生
								</view>
								<view class="fx-l-y-v-bt uni-row retract color-title2 mr-t-min">
										{{isUndefined(currYear.undergraduatePeople)}}人
								</view>
								<view class="fx-l-y-v-bt uni-row w-100 mr-t-min">
									<view class="fx-r uni-row retract">
										<span class="home-content-font ">
											{{onRecruitYear}}年{{recruitSeason}}:{{isUndefined(oldYear.undergraduatePeople)}}人
										</span>
									</view>
									<view class="fx-r uni-row home-content-font color-rate2" v-if="currYear.undergraduateRateType === 0">
										<uni-icons  type="undo-filled" class="icon-select" color="#306191" size="20"></uni-icons>
										{{isUndefined(currYear.undergraduateRate)}}%		
									</view>
									<view class="fx-r uni-row home-content-font color-rate1" v-if="currYear.undergraduateRateType === 1">
										<uni-icons  type="redo-filled" class="icon-select" color="#e86c00" size="20"></uni-icons>
										{{isUndefined(currYear.undergraduateRate)}}%		
									</view>
								</view>
								<view class="flex-item uni-column mr-t-xs">
									<hr class="hr-dashed">
								</view>
							</view>
							<view class="flex-item uni-column mr-t-xs">
								<view class="fx-l-y-v-bt uni-row color-title2 font-weight">
									上开专科招生
								</view>
								<view class="fx-l-y-v-bt uni-row retract color-title2 mr-t-min">
										{{isUndefined(currYear.technicalPeople)}}人
								</view>
								<view class="fx-l-y-v-bt uni-row w-100 mr-t-min">
									<view class="fx-r uni-row retract">
										<span class="home-content-font ">
											{{onRecruitYear}}年{{recruitSeason}}:{{isUndefined(oldYear.technicalPeople)}}人
										</span>
									</view>
									<view class="fx-r uni-row home-content-font color-rate2" v-if="currYear.technicalRateType === 0">
										<uni-icons  type="undo-filled" class="icon-select" color="#306191" size="20"></uni-icons>
										{{isUndefined(currYear.technicalRate)}}%
									</view>
									<view class="fx-r uni-row home-content-font color-rate1" v-if="currYear.technicalRateType === 1">
										<uni-icons  type="redo-filled" class="icon-select" color="#e86c00" size="20"></uni-icons>
										{{isUndefined(currYear.technicalRate)}}%
									</view>
								</view>
								<view class="flex-item uni-column mr-t-xs">
									<hr class="hr-dashed">
								</view>
							</view>
							<view class="flex-item uni-column mr-t-xs">
								<view class="fx-l-y-v-bt uni-row color-title2 font-weight">
									国开本科招生
								</view>
								<view class="fx-l-y-v-bt uni-row retract color-title2 mr-t-min">
										{{isUndefined(currYear.gkPeople)}}人
								</view>
								<view class="fx-l-y-v-bt uni-row w-100 mr-t-min">
									<view class="fx-r uni-row retract">
										<span class="home-content-font ">
											{{onRecruitYear}}年{{recruitSeason}}:{{isUndefined(oldYear.gkPeople)}}人
										</span>
									</view>
									<view class="fx-r uni-row home-content-font color-rate2" v-if="currYear.gkRateType === 0">
										<uni-icons type="undo-filled" class="icon-select" color="#306191" size="20"></uni-icons>
										{{isUndefined(currYear.gkRate)}}%
									</view>
									<view class="fx-r uni-row home-content-font color-rate1" v-if="currYear.gkRateType === 1">
										<uni-icons  type="redo-filled" class="icon-select" color="#e86c00" size="20"></uni-icons>
										{{isUndefined(currYear.gkRate)}}%
									</view>
								</view>
								<view class="flex-item uni-column mr-t-xs">
									<hr class="hr-dashed">
								</view>
							</view>
						</view>
						<view class="fx-l-y-v w-100 h-90" v-if="!currSeason" >
							没有招生计划
						</view>
					</view>
				</view>
			</view>
		</view>
			<view class="fx-l-y-v mr-b-10 button-b">
				<view class="fx-t-v" v-if="isPermissionCode('viewAll')">
					<button class="mini-btn mr-r-xs"  @click="toAuthorize('season')" type="primary" size="mini">招生季列表</button>
				</view>
				<view class="fx-t-v" v-if="isPermissionCode('view')">
					<button class="mini-btn mr-r-xs" :disabled="flag" type="primary"  @click="toAuthorize('countDetail',4)" size="mini">分校详情</button>
				</view>
				<view v-if="!isPermissionCode('view')">
					<button class="mini-btn mr-r-xs" :disabled="flag" type="primary"  @click="toAuthorize('countDetail')" size="mini">本校详情</button>
					<button  class="mini-btn" type="primary" :disabled="flag" @click="toAuthorize('counting')" size="mini">填报人数</button>
				</view>
			</view>
	</view>
	
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import { mapState,mapActions,mapGetters } from 'vuex'
	export default {
		components: {uniIcons},
		computed: {
			...mapState({
				hasLogin : state => state.auth.token,
			}),
			...mapGetters({
				isUndefined : 'constant/isUndefined',
				isPermissionCode : 'constant/isPermissionCode',
				getRecruitYear : 'constant/getRecruitYear',
				getOnRecruitYear : 'constant/getOnRecruitYear',
				getRecruitSeason : 'constant/getRecruitSeason',
				getCurrDate : 'constant/getCurrDate',
			}),
		},
		onShow(){
			this.currSeason = uni.getStorageSync('currSeason') ;
			if(this.currSeason === null || this.currSeason === ''){
				this.flag = true;
			}else{
				this.flag = false;
				this.recruitYear = this.getRecruitYear(this.currSeason);
				this.recruitSeason = this.getRecruitSeason(this.currSeason);
				this.onRecruitYear = this.getOnRecruitYear(this.currSeason);
				this.doStatistics();
			}
		},
		created(){
			this.currSeason = uni.getStorageSync('currSeason') ;
			this.arrayInfo = uni.getStorageSync('schoolAll');
			this.arrayOne = uni.getStorageSync('schoolOne');
		},
		async onLoad(option) {
			if (!uni.getStorageSync('token')) {
				uni.redirectTo({
					url: '../../user/login'
				});
			}
			if(this.currSeason === null || this.currSeason === ''){
				this.flag = true;
			}
			if(!this.isPermissionCode('view')){
				if(this.arrayOne != null && this.arrayOne != '' ){
					this.schoolId = this.arrayOne.schoolId;
				}
			}
			if(this.currSeason != null && this.currSeason != ''){
				this.doStatistics();
			}
		},
		data() {
			return {
				currYear:{},
				oldYear:{},
				arrayInfo: [],
				arrayOne:{},
				index: 0,
				currSeason: [],
				schoolId: 0,
				recruitYear : '',
				recruitSeason : '',
				onRecruitYear : '',
				flag : false,
			}
		},
		methods:{
			...mapActions({
				loadCurrSeason: 'page/loadCurrSeason',
				loadStatistics: 'page/loadStatistics',
			}),
			doStatistics(){
				let data = {schoolId:this.schoolId,recruitSeason:this.currSeason.recruitSeason,
							onRecruitYear:this.currSeason.onRecruitYear,
							recruitYear:this.currSeason.recruitYear,
							};
				this.loadStatistics(data).then((res) =>{
					this.currYear = res[0];
					this.oldYear = res[1];
				});
			},
			toAuthorize(page,groupId) {
				if(page === "counting"){
					if(this.arrayOne.recruitPlan === 0 || this.arrayOne.recruitCount === 0){
						uni.showModal({
							content: "该分校还没有招生计划",
							showCancel: false,
						});
						return ;
					}
				}
				uni.navigateTo({url: './'+page+'?groupId = '+encodeURIComponent(groupId)});
			},
			bindPickerChange: function(e) {
				this.index = e.target.value;
				this.schoolId = this.index;
				this.doStatistics();
			},
		}
	}
</script>

<style>
	.icon-select{
		transform: rotate(90deg);
	}
	.button-b{
		position:fixed;
		bottom:0;
		background-color: #FFFFFF; 
		z-index: 100;
		padding-bottom: 5%;
		width: 98%;
	}
</style>
