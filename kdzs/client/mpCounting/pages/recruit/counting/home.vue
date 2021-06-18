<template>
	<view class="content">
		<uni-icons type="back" size="30" @click="back" style="position:fixed;left:0%;top:4.5%;z-index: 110;"></uni-icons>
		<view class="fx-t-v w-100" style="position:fixed;top:0;background-color: #FFFFFF; z-index: 100;padding-top:22%">
			<image src="../../../static/img/logo.png" class="home-logo-title-img">
			<hr class="hr-solid mr-t-xs">
			<view v-if="currSeason && token" class="w-100 fx-t-v">
				<view class="fx-t-v mr-t-xs w-100 title-max-font">
					{{recruitYear}}年{{recruitSeason}}招生
				</view>
				<view class="fx-l-v-bt mr-t-four w-90 ">
					<view class="uni-list-cell-db">
						<picker v-if="isPermissionCode('view')" @change="bindPickerChange" :value="index" :range="arrayInfo" range-key="name">
							<view class="fx-l-v-bt border" style="width:380upx;height:50upx">
								<view class="uni-input color-title2 mr-l-min">{{arrayInfo[index].name}}</view>
								<uni-icons  type="arrowdown"/>
							</view>
						</picker>
						<picker v-if="getRoleCode(roles) === sumSchoolRoleCode" @change="sumSchoolPickerChange" :value="index" :range="sumSchool">
							<view class="fx-l-v-bt border" style="width:380upx;height:50upx">
								<view class="uni-input color-title2 mr-l-min">{{sumSchool[index]}}</view>
								<uni-icons type="arrowdown"/>
							</view>
						</picker>
						<view v-if="!isPermissionCode('view')" class="uni-list-cell-db mr-b-xs">
							{{isNull(arrayOne.name)}}
						</view>
					</view>
					<view class="uni-list-cell-left home-input fx-t-v line10">
						{{getCurrDate()}}
					</view>
				</view>
			</view>
		</view>
		<view class="fx-t" style="padding-top: 52%;">
			<view class="fx-t-v font h-90">
				<view class="uni-column fx-l-y-v mr-t-xs home-content pd-xs" >
					<view class=" uni-row w-100">
						<view v-if="currSeason && token">
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
									本科招生
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
									专科招生
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
							<!-- <view class="flex-item uni-column mr-t-xs">
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
							</view>-->
						</view> 
						<view v-if="!token" class="home-content-font font-size-14 text-align w-100">
							本系统仅供上海开放大学招生办使用<br>
							请相关老师点击右下角“我的”进行登录
						</view>
						<view class="fx-l-y-v w-100 h-90" v-if="!currSeason && token" >
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
				<view class="fx-t-v" v-if="!isPermissionCode('view') && isPermissionCode('add')">
					<button class="mini-btn mr-r-xs" :disabled="flag" type="primary"  @click="toAuthorize('countDetail')" size="mini">本校详情</button>
				</view>
				<view v-if="isPermissionCode('add') && !isPermissionCode('viewAll')">
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
				hasLogin: state => state.auth.token,
				sumSchool: state => state.constant.sumSchool,
				sumSchoolRoleCode: state => state.constant.sumSchoolRoleCode
			}),
			...mapGetters({
				isUndefined: 'constant/isUndefined',
				isNull: 'constant/isNull',
				isPermissionCode: 'constant/isPermissionCode',
				getRecruitYear: 'constant/getRecruitYear',
				getOnRecruitYear: 'constant/getOnRecruitYear',
				getRecruitSeason: 'constant/getRecruitSeason',
				getCurrDate: 'constant/getCurrDate',
				getSumSchoolIds: 'constant/getSumSchoolIds',
				getSchoolOne: 'constant/getSchoolOne',
				selectSchoolId: 'constant/selectSchoolId',
			}),
		},
		onShow(){
			this.token = uni.getStorageSync('token');
			if (this.token) {
				this.loadCurrSeason().then((res) =>{
					this.currSeason = res;
					if(this.currSeason === null || this.currSeason === ''){
						this.flag = true;
					}else{
						this.flag = false;
						this.recruitYear = this.getRecruitYear(this.currSeason);
						this.recruitSeason = this.getRecruitSeason(this.currSeason);
						this.onRecruitYear = this.getOnRecruitYear(this.currSeason);
						//更新学校列表
						this.loadSchoolAll().then((res) =>{
							this.arrayInfo = res;
						});
						this.loadSchoolOne().then(() =>{
							this.arrayOne = this.getSchoolOne();
						});
						this.doStatistics();
					}
				})
			}
		},
		async onLoad(option) {
			this.roles = uni.getStorageSync('roles');
			this.schoolIds = this.getSumSchoolIds();
			/* if (!uni.getStorageSync('token')) {
				uni.redirectTo({
					url: '../../user/login'
				});
			} */
			if(this.currSeason === null || this.currSeason === ''){
				this.flag = true;
			}
			
			if(this.currSeason != null && this.currSeason != ''){
				this.doStatistics();
			}
			this.loadSchoolOne().then(() =>{
				this.arrayOne = this.getSchoolOne();
				if(!this.isPermissionCode('view')){
					if(this.arrayOne != null && this.arrayOne != '' ){
						this.schoolId = this.arrayOne.schoolId;
					}
				}
			});
		},
		data() {
			return {
				currYear:{},
				oldYear:{},
				arrayInfo: [],
				arrayOne:{name:''},
				index: 0,
				currSeason: [],
				schoolId: 0,
				schoolIds: '',//查总校下的四个分校
				recruitYear : '',
				recruitSeason : '',
				onRecruitYear : '',
				flag : false,
				roles:{},
				token:'',
			}
		},
		methods:{
			...mapActions({
				loadCurrSeason: 'page/loadCurrSeason',
				loadStatistics: 'page/loadStatistics',
				loadSchoolAll: 'page/loadSchoolAll',
				loadSchoolOne: 'page/loadSchoolOne',
			}),
			doStatistics(){
				let data = {schoolId:this.schoolId,schoolIds:this.schoolIds,recruitSeason:this.currSeason.recruitSeason,
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
				this.schoolId = this.arrayInfo[parseInt(e.target.value)].schoolId;
				this.doStatistics();
			},
			sumSchoolPickerChange: function(e) {
				this.index = e.target.value;
				this.schoolId = this.selectSchoolId(this.index);
				this.doStatistics();
			},
			back(){
				uni.redirectTo({
					url: './homeAnime'
				});
			},
			getRoleCode(roles){
				if(roles[0] != undefined){
					return roles[0].roleCode;
				}else{
					return "";
				}
			}
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
