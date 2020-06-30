<template>
	<view class="w-100" style="padding-top: 220upx;">
		<view class="fx-t-v w-100 mr-b-xs " style="position:fixed;top:0;background-color: #FFFFFF; z-index: 100;">
			<view class="uni-row w-80">
				<uni-search-bar placeholder="输入分校名称" @input="search" clearButton="none" bgColor="#FFFFFF" cancelButton="none"></uni-search-bar>
			</view>
			<view class="fx-l-y-v">
				<view class="cell-mr-r-20 line50 fx-l title-min-font " style="width:180upx">
					分校名称
				</view>
				<view class="cell-mr-r-20 fx-t-v-h title-min-font cell-width">
					上开<br>本科
				</view>
				<view class="cell-mr-r-20 fx-t-v-h title-min-font cell-width">
					上开<br>专科
				</view>
				<view class="cell-mr-r-20 fx-t-v-h title-min-font cell-width">
					国开<br>本科
				</view>
				<view class="cell-mr-r-20 fx-t-v-h  title-min-font" style="width:70upx">
					操作
				</view>
			</view>
			<view class="w-100 fx-l-y-v">
				<hr class="hr-solid-gray mr-t-xs">
			</view>
		</view>
		<view>
			<view class="fx-l-y-v" style="height:80upx" v-for="(item,index) in schoolList" :key="index">
				<view class="cell-mr-r-20 fx-l content-font pd-t-xs" style="width:180upx">
					{{item.name}}
					<view class="name-seat">{{isRevise(item.revise)}}</view>
				</view>
				<view class="cell-mr-r-20 fx-t-v-h content-font cell-width ">
					{{isUndefined(item.undergraduatePeople)}}人
				</view>
				<view class="cell-mr-r-20 fx-t-v-h content-font cell-width ">
					{{isUndefined(item.technicalPeople)}}人
				</view>
				<view class="cell-mr-r-20 fx-t-v-h content-font cell-width ">
					{{isUndefined(item.gkPeople)}}人
				</view>
				<view class="cell-mr-r-20 fx-l-y-v content-font " style="width:70upx">
					<view class="fx-t-v-h link" @click="open(item.schoolId,item.name,item.undergraduatePeople,item.technicalPeople,item.gkPeople)">
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
						<view class=" mr-r-xs fx-t-v-h content-font">
							上开本科:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-width mr-r-xs " v-model="undergraduatepeople" type="number"/>
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							上开专科:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-width mr-r-xs" v-model="technicalpeople" type="number"/>
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							国开本科:
						</view>
						<view class=" fx-t-v-h">
							<input class="uni-input input-width mr-r-xs" v-model="gkpeople" type="number"/>
						</view>
					</view>
					
					<view class="w-100 fx-l-y-v-bt mr-t-s" >
						<button type="primary" class="w-80" @click="submitUpdate" style="height:60upx;line-height: 52upx;">提交</button>
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
				isRevise : 'constant/isRevise',
			}),
		},
		data(){
			return{
				currSeason:[],
				schoolList:[],
				recruitSeason : '',
				seasonId : 0,
				schoolId:0,
				schoolName : '',
				undergraduatepeople : 0,
				technicalpeople : 0,
				gkpeople : 0,
			}
		},
		onLoad(option){
			this.seasonId = decodeURIComponent(option.seasonId);
			this.getSchoolRevisPeople("");
			this.getSeason(option);
		},
		methods:{
			...mapActions({
				querySeasonoOne : 'page/querySeasonoOne',
				findSchoolByNameRevisePeople : 'page/findSchoolByNameRevisePeople',
				updatePeople : 'page/updatePeople',
			}),
			getSeason(option){
				let data = {id:this.seasonId};
				this.querySeasonoOne(data).then((res) =>{
					this.currSeason = res;
					this.recruitSeason = this.getRecruitSeason(res);
				});
			},
			isUndefined(recruitCount){
				if(recruitCount == null){
					return 0
				}else{
					return recruitCount;
				}
			},
			open(schoolId,name,undergraduatepeople,technicalpeople,gkpeople){
				this.schoolId = schoolId;
				this.schoolName = name;
				this.undergraduatepeople = undergraduatepeople;
				this.technicalpeople = technicalpeople;
				this.gkpeople = gkpeople;
				this.$refs.popup.open();
			},
			close(){
				this.$refs.popup.close();
			},
			search(e){
				this.getSchoolRevisPeople(e.value);
			},
			getSchoolRevisPeople(schoolName){
				let data = {seasonId:this.seasonId,name:schoolName};
				this.findSchoolByNameRevisePeople(data).then((res) =>{
					this.schoolList = res;
				});
			},
			radioChange(e){
				this.recruitPlan = e.target.value;
			},
			submitUpdate(){
				let data = {schoolid : this.schoolId,
							seasonid : this.seasonId,
							undergraduatepeople : this.undergraduatepeople,
							technicalpeople : this.technicalpeople,
							gkpeople : this.gkpeople,
							};
				this.updatePeople(data).then((res) =>{
					this.schoolList = [];
					this.getSchoolRevisPeople("");
					this.close();
				});
			}
		}
	}
</script>

<style>
	.cell-width{
		width:100upx;
	}
	.up-box{
		background-color:#FFFFFF;
		width:650upx;
		height:600upx;
	}
	.name-seat{
		font-size: 20upx;
		color:blue;
	}
</style>
