<template>
	<view class="w-100 fx-t-v" style="padding-top: 120upx;">
		<view class="fx-t-v" style="position:fixed;top:0;background-color: #FFFFFF; z-index: 100;">
			<view class="fx-l-y-v">
				<view class="cell-mr-r-20 fx-t-v-h  title-min-font cell-width">
					名称
				</view>
				<view class="cell-mr-r-20 fx-t-v-h title-min-font cell-width">
					开始时间<br>
					结束时间
				</view>
				<view class="cell-mr-r-20 fx-t-v-h  title-min-font cell-width">
					操作
				</view>
				<view class=" fx-t-v-h title-min-font cell-width">
					状态
				</view>
			</view>
			<view class="w-100 fx-l-y-v">
				<hr class="hr-solid-gray mr-t-xs">
			</view>	
		</view>
		<view class="h-90 fx-t-v">
			<view class="fx-l-y-v" style="height:80upx" v-for="(item,index) in seasonList" :key="index">
				<view class="cell-mr-r-20 fx-t-v-h content-font  cell-width">
					{{getCurrSeason(item.recruitseason)}}
				</view>
				<view class="cell-mr-r-20 fx-t-v-h content-font cell-width ">
					{{formatDateInfo(item.recruitstartdate)}}<br>
					{{formatDateInfo(item.recruitenddate)}}
				</view>
				<view class="cell-mr-r-20 fx-l-y-v content-font  cell-width" v-if="item.status">
					<view class=" fx-t-v-h link cell-mr-r-15" @click="toPage('revisePeople',item.id)">
						校正人数 
					</view>
					<view class="fx-t-v-h link" @click="toPage('countDetail',item.id)">
						统计
					</view>
				</view>
				<view class="cell-mr-r-20 fx-l-y-v content-font  cell-width" v-if="!item.status">
					<view class=" fx-t-v-h link cell-mr-r-15" @click="toUpdateSeason(item.id,item.recruityear,item.recruitseason,item.recruitstartdate,item.recruitenddate)">
						修改 
					</view>
					<view class="fx-t-v-h link" @click="toPage('plan',item.id)">
						招生计划
					</view>
				</view>
				<view class=" fx-t-v-h content-font cell-width">
					{{getRecruitStatus(item.status)}}
				</view>
			</view>
			<uni-popup ref="popup"  type="center">
				<view class="up-box">
					<view class="fx-r w-100">
						<uni-icons type="closeempty" @click="close" size="30"></uni-icons>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-12 fx-t-v-h content-font ">
							招生季:
						</view>
						<view class=" fx-l-y-v pd-l-10">
							<picker mode ="date" @change="yearChange" fields="year" :value="yearindex" >
								<view class="uni-input mr-r-xs input-border" style="width:200upx;height:60upx;line-height: 60upx;">{{yearindex}}</view>
							</picker>
							<picker  @change="seasonChange" :value="seasonindex" :range="season">
								<view class="uni-input input-border" style="width:200upx;height:60upx;line-height: 60upx;">{{season[seasonindex]}}</view>
							</picker>
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s">
						<view class=" mr-r-xs fx-t-v-h content-font">
							开始日期:
						</view>
						<view class=" fx-t-v-h">
							<picker mode ="date" @change="startConfirm" :value="startTimeValue" >
								<view class="uni-input mr-r-xs input-width">{{startTimeValue}}</view>
							</picker>
						</view>
					</view>
					<view class="fx-l-y-v mr-t-s"> 
						<view class=" mr-r-xs fx-t-v-h content-font">
							结束日期:
						</view>
						<view class=" fx-t-v-h">
							<picker mode ="date" @change="endConfirm" :value="endTimeValue" >
								<view class="uni-input mr-r-xs input-width" >{{endTimeValue}}</view>
							</picker>
						</view>
					</view>
					<view class="w-100 fx-l-y-v-bt mr-t-s" >
						<button type="primary" v-if="add" class="w-80" @click="submitAdd" style="height:60upx;line-height: 52upx;">提交</button>
						<button type="primary" v-if="update" class="w-80" @click="submitUpdate" style="height:60upx;line-height: 52upx;">提交</button>
					</view>
				</view>
			</uni-popup>
			<view style="position:fixed;bottom:0; z-index: 100;">
				<button class="mini-btn mr-b-xs" type="primary" @click="open"  size="mini">添加</button>
			</view>
		</view>
	</view>
</template>

<script>
	import uniPopup from '@/components/uni-popup/uni-popup.vue';
	import uniIcons from "@/components/uni-icons/uni-icons.vue";
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue';
	import { mapState,mapActions,mapGetters } from 'vuex';
	export default{
		components: {uniPopup,uniIcons,uniCalendar},
		computed:{
			...mapGetters({
				formatDateInfo : 'constant/formatDateShort',
				getCurrSeason : 'constant/getCurrSeason',
				getRecruitStatus : 'constant/getRecruitStatus',
			}),
		},
		data(){
			return{
				yearindex:'',
				seasonindex:0,
				season:['春季','秋季'],
				startTimeValue:'',
				endTimeValue:'',
				seasonList : [],
				seasonId : 0,
				add : false,
				update : false,
			}
		},
		onLoad(){
			this.findWhetherOvertime().then((res) =>{
				this.getSeasonAll();
			});
		},
		methods:{
			...mapActions({
				saveSeason : 'page/saveSeason',
				querySeason : 'page/querySeason',
				updateSeason : 'page/updateSeason',
				loadCurrSeason: 'page/loadCurrSeason',
				findWhetherOvertime: 'page/findWhetherOvertime',
			}),
			whetherOvertime(){
				
			},
			getSeasonAll(){
				this.seasonList = [];
				this.querySeason().then((res) =>{
					this.seasonList = res;
				});
				this.loadCurrSeason();
			},
			toUpdateSeason(id,year,season,startDate,endDate){
				this.yearindex = year;
				this.seasonindex = season ? 1 : 0;
				this.endTimeValue = this.formatDateInfo(endDate);
				this.startTimeValue = this.formatDateInfo(startDate);
				this.seasonId = id;
				this.update = true;
				this.add = false;
				this.$refs.popup.open();
			},
			submitAdd(){
				if(this.checkData()){
					let data = {recruitStartDate : this.startTimeValue,
								recruitEndDate : this.endTimeValue,
								recruitYear : this.yearindex,
								recruitSeason : this.seasonindex};
					this.saveSeason(data).then((res) =>{
						this.getSeasonAll();
						this.close();
					});
				}
			},
			submitUpdate(){
				if(this.checkData()){
					let data = {recruitStartDate : this.startTimeValue,
								recruitEndDate : this.endTimeValue,
								recruitYear : this.yearindex,
								recruitSeason : this.seasonindex,
								id : this.seasonId};
					this.updateSeason(data).then((res) =>{
						this.getSeasonAll();
						this.close();
					});
				}
			},
			checkData(){
				if(this.yearindex === ''){
					uni.showModal({
						content: '年份不能为空',
						showCancel: false,
					});
					return false;
				}
				if(this.startTimeValue === '' || this.endTimeValue === ''){
					uni.showModal({
						content: '日期不能为空',
						showCancel: false,
					});
					return false;
				}
				if(this.endTimeValue <= this.startTimeValue){
					uni.showModal({
						content: '结束日期必须大于开始日期',
						showCancel: false,
					});
					return false;
				}
				return true;
			},
			open(){
				this.startTimeValue = '';
				this.endTimeValue = '';
				this.yearindex = '';
				this.update = false;
				this.add = true;
				this.$refs.popup.open();
			},
			close(){
				this.$refs.popup.close();
			},
			startTime(){
				this.$refs.startTime.open();
			},
			endTime(){
				this.$refs.endTime.open();
			},
			startConfirm(e) {
				this.startTimeValue = e.target.value;
			},
			endConfirm(e) {
				this.endTimeValue = e.target.value;
			},
			yearChange: function(e) {
				this.yearindex = e.target.value;
			},
			seasonChange: function(e) {
				this.seasonindex = e.target.value;
			},
			toPage(page,id) {
				uni.navigateTo({url: './'+page+'?seasonId='+encodeURIComponent(id)});
			},
		}
	}
</script>

<style>
	.cell-width{
		width:180upx;
	}
	.up-box{
		background-color:#FFFFFF;
		width:650upx;
		height:600upx;
	}
</style>
