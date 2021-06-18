<template>
	<view class="w-100" style="padding-top: 170upx;">
		<view class="fx-t-v w-100" style="position:fixed;top:0;background-color: #FFFFFF; z-index: 100;">
			<view class="uni-row w-80">
				<uni-search-bar placeholder="输入分校名称" @input="search" clearButton="none" bgColor="#FFFFFF" cancelButton="none"></uni-search-bar>
			</view>
			<view class="fx-l-y-v ">
				<view class="mr-r-s fx-l  title-min-font" style="width:280upx">
					分校名称
				</view>
				<view class="mr-r-60 fx-t-v-h title-min-font cell-width">
					招生人数
				</view>
				<view class=" fx-t-v-h  title-min-font" style="width:80upx">
					操作
				</view>
			</view>
			<view class="w-100 fx-l-y-v">
				<hr class="hr-solid-gray mr-t-xs">
			</view>
		</view>
		<view >
			<view class="fx-l-y-v" style="height:80upx" v-for="(item,index) in schoolList" :key="index">
				<view class="mr-r-s fx-l content-font pd-t-xs" style="width:280upx">
					{{item.name}}
				</view>
				<view v-if="item.recruitPlan === 1" class="mr-r-60 fx-t-v-h content-font cell-width">
					{{isUndefined(item.recruitCount)}}人
				</view>
				<view v-if="item.recruitPlan === 0" class="mr-r-60 fx-t-v-h content-font cell-width">
					不招生
				</view>
				<view class="fx-l-y-v content-font" style="width:80upx">
					<view class="fx-t-v-h link" @click="open(item.planId,item.name,item.recruitCount,item.recruitPlan,item.schoolId)">
						修改
					</view>
				</view>	
			</view>
			<uni-popup ref="popup"  type="center">
				<view class="up-box">
					<view class="fx-r w-100">
						<uni-icons type="closeempty" @click="close" size="30"></uni-icons>
					</view>
					<view class="fx-l-y-v w-100 title-min-font">
						{{schoolName}}
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class="mr-r-5 fx-t-v-h content-font">
							计划招生:
						</view>
						<view class="fx-l-y-v" style="width:460upx;">
							<radio-group @change="radioChange" >
								<label class="radio mr-r-s"><radio value="true" :checked="oldRecruitPlan" />招生</label>
								<label class="radio"><radio value="false" :checked="!oldRecruitPlan" />不招生</label>
							</radio-group>
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							招生人数:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-border mr-r-xs input-width" v-model="sumPeople" type="number"  />
						</view>
					</view>
					<!-- <view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							上开本科:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-border mr-r-xs input-width" :disabled="flag" v-model="technicalPeople" type="number"  />
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							上开专科:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-border mr-r-xs input-width" :disabled="flag" v-model="undergraduatePeople" type="number"  />
						</view>
					</view> -->
					
					<view class="w-100 fx-l-y-v-bt mr-t-s" >
						<button type="primary" class="w-90" @click="submitUpdate" style="height:60upx;line-height: 52upx;">提交</button>
					</view>
				</view>
			</uni-popup>
		</view>
	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue';
	import uniIcons from "@/components/uni-icons/uni-icons.vue";
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import { mapState,mapActions,mapGetters } from 'vuex';
	export default{
		components: {uniPopup,uniIcons,uniSearchBar},
		computed:{
			...mapGetters({
				formatDateInfo : 'constant/formatDateShort',
				getRecruitStatus : 'constant/getRecruitStatus',
				getRecruitSeason : 'constant/getRecruitSeason',
				isUndefined : 'constant/isUndefined',
			}),
		},
		data(){
			return{
				currSeason : [],
				schoolList : [],
				recruitYear : '',
				recruitSeason : '',
				seasonId : 0,
				schoolName : '',
				planId : 0,
				undergraduatePeople : 0,
				technicalPeople: 0,
				gkPeople: 0,
				oldRecruitPlan : true,
				recruitPlan : true,
				schoolId:0,
				flag : false,
				sumPeople:0,
			}
		},
		onLoad(option){
			this.seasonId = decodeURIComponent(option.seasonId);
			this.getSchoolPlan("");
			this.getSeason(option);
		},
		methods:{
			...mapActions({
				querySeasonoOne : 'page/querySeasonoOne',
				findSchoolByNamePlan : 'page/findSchoolByNamePlan',
				updateRecruitPlan : 'page/updateRecruitPlan',
				loadSchoolAll: 'page/loadSchoolAll',
			}),
			getSeason(option){
				let data = {id:this.seasonId};
				this.querySeasonoOne(data).then((res) =>{
					this.currSeason = res;
					this.recruitSeason = this.getRecruitSeason(res);
					this.recruitYear = this.currSeason.recruityear;
				});
			},
			open(id,name,sumPeople,recruitPlan,schoolId){
				if(recruitPlan === 0){
					this.flag = true;
					this.oldRecruitPlan = false ;
				}else{
					this.oldRecruitPlan = true;
				}
				this.sumPeople = sumPeople;
				this.planId = id;
				this.schoolName = name;
				
				this.recruitPlan = recruitPlan;
				this.schoolId = schoolId;
				this.$refs.popup.open();
			},
			close(){
				this.$refs.popup.close();
			},
			search(e){
				this.getSchoolPlan(e.value);
			},
			getSchoolPlan(schoolName){
				let data = {seasonId:this.seasonId,name:schoolName};
				this.findSchoolByNamePlan(data).then((res) =>{
					this.schoolList = res;
				});
			},
			radioChange(e){
				if(e.target.value === "false"){
					this.flag = true;
					this.oldRecruitPlan = false;
				}else{
					this.oldRecruitPlan = true;
					this.flag = false;
				}
				this.recruitPlan = e.target.value;
			},
			submitUpdate(){
				if((!this.sumPeople) && this.recruitPlan === 1){
					uni.showModal({
						content: '请填写招生人数',
						showCancel: false,
					});
					return ;
				}
				let data = {id:this.planId,recruitcount:parseInt(this.sumPeople),
							recruitplan:this.recruitPlan,schoolid:this.schoolId,
							seasonid:this.seasonId
							};
				this.updateRecruitPlan(data).then((res) =>{
					this.schoolList = [];
					this.getSchoolPlan("");
					this.close();
					
				});
			}
		}
	}
</script>

<style>
	.cell-width{
		width:150upx;
	}
	.up-box{
		background-color:#FFFFFF;
		width:650upx;
		height:700upx;
	}
</style>
