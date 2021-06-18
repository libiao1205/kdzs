<template>
	<view class="fx-t-v h-100">
		<view class="uni-column h-100 " style="width:750upx">
			<view class="uni-row fx-l-v-bt w-100 mr-b-min pd-l-xs ">
				<view class="uni-list-cell-db mr-l-min">
					<picker v-if="isPermissionCode('view')" @change="bindPickerChange" :value="index" :range="arrayInfo">
						<view class="fx-l-v-bt border" style="width:280upx;height:50upx">
							<view class="uni-input fx-t-v color-title2 mr-l-min">{{arrayInfo[index]}}</view>
							<uni-icons type="arrowdown"/>
						</view>
					</picker>
					<view v-if="!isPermissionCode('view')" class="uni-list-cell-db mr-b-xs">
						{{isNull(arrayOne.name)}}
					</view> 
				</view>
				<view class="uni-row pd-r-s" v-if="isPermissionCode('view')">
					<button class="mini-btn" type="primary" @click="getExcel" size="mini">查看Excel</button>
				</view>
			</view>
			<view class="uni-row fx-l-y-v title-min-font bg-gray fx-t-v-h" style="border:1upx solid #c1c4c5;height:80upx">
				{{isUndefined(recruitYear)}}年{{isUndefined(getCurrSeason(recruitSeason))}}开放教育招生数据统计表
			</view>
			<view class="fx-l" >
				<view class="uni-row fx-t-v-l th-width bg-gray" style="border-left:1upx solid #c1c4c5;">
					<view class="th th1 fx-t-v-h">分校</view>
					<view class="uni-row th th2 fx-t-v-h">招生计划</view>
					<view class="uni-row th  th7 fx-t-v-h">
							国开本科<br>
							目前招生
					</view>
					<view class="uni-row th  th7 fx-t-v-h">
							上开本科<br>
							目前招生
					</view>
					<view class="uni-row th  th7 fx-t-v-h">
							上开专科<br>
							目前招生
					</view>
					<view class="uni-row th  th6 fx-t-v-h">本季合计</view>
					<view class="uni-row th  th6 fx-t-v-h" v-if="recruitSeason == 1">春季合计</view>
					<view class="uni-row th  th6 fx-t-v-h" v-if="recruitSeason == 1">全年合计</view>
					<view class="uni-row th  th7 fx-t-v-h">计划完成率</view>
					<view class="uni-row th  th8 fx-t-v-h">同比增长率</view>
				</view>
				<scroll-view class="scroll-view_H" scroll-x="true"  scroll-left="120" style="width:600upx;">
					<view class="fx-l " style="height: 100%;">
						<view v-for="(item,index) in countList" class="fx-t-v-l " :key="index"  >
							<view class="fx-l-v th th1 td-width" :style="item.cellBgColor">
								<view style="width: 100%;text-align: center;color:black" v-if="getNameColorBlack(item.name,item.lastRecruitDate)">{{item.name}}</view>
								<view style="width: 100%;text-align: center;color:red" v-if="getNameColorRed(item.name,item.lastRecruitDate)">{{item.name}}</view>
							</view>
							<view class="fx-t-v-h th th2  td-width" :style="item.cellBgColor">{{isUndefined(item.recruitCount)}}</view>
							<view class="fx-t-v-h th  th7 td-width" :style="item.cellBgColor">
									{{isUndefined(item.gkPeople)}}
							</view>
							<view class="fx-t-v-h th  th7 td-width" :style="item.cellBgColor">
									{{isUndefined(item.undergraduatePeople)}}
							</view>
							<view class="fx-t-v-h th  th7 td-width" :style="item.cellBgColor">
									{{isUndefined(item.technicalPeople)}}
							</view>
							<view class="fx-t-v-h th  th6 td-width" :style="item.cellBgColor">{{isUndefined(item.sumPeople)}}</view>
							<view class="fx-t-v-h th  th6 td-width" v-if="recruitSeason == 1" :style="item.cellBgColor">{{isUndefined(item.chunJiSumPeople)}}</view>
							<view class="fx-t-v-h th  th6 td-width" v-if="recruitSeason == 1" :style="item.cellBgColor">{{isUndefined(item.chunQiuSumPeople)}}</view>
							<view class="fx-t-v-h th  th7 td-width" :style="item.cellBgColor">{{isUndefined(item.planAccomplishRate)}}%</view>
							<view class="fx-t-v-h th  th8 td-width" :style="item.cellBgColor">{{item.recruitRate == null ? '' : (item.recruitRate+'%')}}</view>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>
	</view>
</template>

<script>
	import _config from '../../../common/request/config.js'
	import { mapState,mapActions,mapGetters } from 'vuex';
	export default{
		computed:{
			...mapGetters({
				getCurrSeason : 'constant/getCurrSeason',
				isUndefined: 'constant/isUndefined',
				isNull: 'constant/isNull',
				copyText: 'constant/copyText',
				isPermissionCode: 'constant/isPermissionCode',
				getSumSchoolIds: 'constant/getSumSchoolIds',
				getSchoolOne: 'constant/getSchoolOne',
				getCurrDate: 'constant/getCurrDate',
			}),
		},
		created(){
			this.arrayOne = this.getSchoolOne();
		},
		data(){
			return {
				arrayInfo: ['总校详情','郊区详情','市区详情','行业详情'],
				index: 0,
				countList: [],
				schoolId:0,
				currSeason:[],
				arrayOne: [],
				seasonId: 0,
				onRecruitYear: '',
				recruitYear: '',
				recruitSeason: 1,
				schoolIds: ''
			}
		},
		onLoad(option){
			if(this.currSeason != null){
				this.schoolIds = this.getSumSchoolIds();
				this.seasonId = decodeURIComponent(option.seasonId);
				if(!this.isPermissionCode('view')){
					if(this.arrayOne != null && this.arrayOne != '' ){
						this.schoolId = this.arrayOne.schoolId;
					}
				}
				if(this.seasonId > 0){
					let data = {id:this.seasonId};
					 this.querySeasonoOne(data).then((res) =>{
						 if(res){
							 this.currSeason = res;
							 this.onRecruitYear = parseInt(this.currSeason.recruityear) - 1;
							 this.recruitYear = this.currSeason.recruityear;
							 this.recruitSeason = this.currSeason.recruitseason == true ? 1 : 0;
							 this.doStatisticsDetail();
						 }
					 });
				}else{
					this.currSeason = uni.getStorageSync('currSeason') == '' ? null : uni.getStorageSync('currSeason');
					if(this.currSeason){
						this.onRecruitYear = this.currSeason.onRecruitYear;
						this.recruitYear = this.currSeason.recruitYear;
						this.recruitSeason = this.currSeason.recruitSeason;
						this.seasonId = this.currSeason.seasonId;
						this.doStatisticsDetail();
					}
				}
			}
		},
		methods:{ 
			...mapActions({
				loadStatisticsDetail: 'page/loadStatisticsDetail',
				loadStatisticsQuiJiDetail: 'page/loadStatisticsQuiJiDetail',
				querySeasonoOne: 'page/querySeasonoOne',
			}),
			doStatisticsDetail(){
				let data = {schoolId:this.schoolId,schoolIds:this.schoolIds,seasonId:this.seasonId,
							onRecruitYear:this.onRecruitYear,
							recruitYear:this.recruitYear,
							recruitSeason : this.recruitSeason,
							sort : parseInt(this.index) + 1,
							};
				this.loadStatisticsDetail(data).then((res) => {
					this.countList = res;
				});
			},
			
			getExcel(){
				let getExcelUrl = _config.url+"/school/recruit/getExcel?seasonId="+this.seasonId+"&onRecruitYear="+this.onRecruitYear+"&recruitYear="+this.recruitYear+"&recruitSeason="+this.recruitSeason;
				this.copyText(getExcelUrl);
			},
			
			bindPickerChange: function(e) {
				this.index = e.target.value;
				this.doStatisticsDetail();
			},
			getNameColorRed(name,lastRecruitDate){
				if(lastRecruitDate != this.getCurrDate() && name != '小计'){
					return true;
				}
				return false;
			},
			getNameColorBlack(name,lastRecruitDate){
				if(lastRecruitDate === this.getCurrDate() || name === '小计'){
					return true;
				}
				return false;
			}
		}
	}
</script>

<style>
	.th{
		font:'MicrosoftYaHei';
		font-size: 26upx;
		border-right:0.5upx solid #d1d4d5;
		border-bottom:0.5upx solid #d1d4d5;
		text-align: center;
		font-weight: 500;
	}
	
	.left-title{
		height: 200upx;
	}
	.table-font{
		background-color: #DEE1E4;
	}
	.th-width{
		width:150upx;
	}
	.td-width{
		width:110upx;
	}
	.gkHeight{
		height:30%;
	}
	.th1{
		height:140upx;
	}
	.th2{
		height:95upx;
	}
	/* .th3{
		height:65upx;
	} */
	/* .th4{
		height:65upx;
	}
	.th5{
		height:65upx;
	} */
	.th-1{
		height:100upx;
		width:100upx;
	}
	.th-2{
		height:100upx;
		width:100upx;
	}
	.td-1{
		height:100upx;
	}
	.td-2{
		height:100upx;
	}
	
	.th6{
		height:105upx;
	}
	.th7{
		height:105upx;
	}
	.th8{
		height:105upx;
	}
</style>
