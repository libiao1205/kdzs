<template>
	<view class="fx-t-v w-100">
		<uni-collapse class="coll-width" @change="exchange">
			<uni-collapse-item title="填写招生人数" showAnimation="true" open="true" class="coll-width">
				<view class="bg-w">
					<view class="fx-r uni-row mr-t-xs">
						<view class="uni-row title-right" >
							{{recruitdateAdd}}
						</view>
					</view>
					<view class="fx-l-y-v"> 
						<view class="line50 mr-r-xs fx-t-v-h content-font">
							上开本科招生人数:
						</view>
						<view class="line50 fx-t-v-h">
							<input class="uni-input input-width" v-model="undergraduatepeople" type="text"  placeholder-style="color: #f4f4f4;" />
						</view>
					</view>
					<view class="fx-l-y-v">
						<view class="line50 mr-r-xs fx-t-v-h content-font">
							上开专科招生人数:
						</view>
						<view class="line50 fx-t-v-h">
							<input class="uni-input input-width" v-model="technicalpeople" type="text" placeholder-style="color: #f4f4f4;" />
						</view>
					</view>
					<view class="fx-l-y-v"> 
						<view class="line50 mr-r-xs fx-t-v-h content-font">
							国开本科招生人数:
						</view>
						<view class="line50 fx-t-v-h">
							<input class="uni-input input-width" v-model="gkpeople" type="text" placeholder-style="color: #f4f4f4;" />
						</view>
					</view>
					<view class="w-100 fx-l-y-v-bt mr-b-xs" >
						<button type="primary" class="w-80" @click="savePeople" style="height:60upx;line-height: 52upx;">提交</button>
					</view>
				</view>
			</uni-collapse-item>
			<uni-collapse-item title="查看招生记录" showAnimation="true" class="w-100">
				<view class="bg-w">
					<view class="fx-r uni-row mr-t-xs">
						<view class="uni-row title-right link" @click="TimeSearch">
							{{recruitDateSearch}}
						</view>
					</view>
					<view class="fx-t-v mr-t-xs">
						<view class="fx-l-y-v">
							<view class="fx-l box2-cell-5 mr-r-xs title-min-font">
								更新日期
							</view>
							<view class="fx-t-v box2-cell mr-r-xs title-min-font">
								上开<br>
								本科
							</view>
							<view class="fx-t-v box2-cell mr-r-xs title-min-font">
								上开<br>
								专科
							</view>
							<view class="fx-t-v box2-cell mr-r-xs title-min-font">
								国开<br>
								本科
							</view>
							<view class="fx-l-y-v box2-cell-4  title-min-font">
								填写人
							</view>
							
						</view>
						<hr class="hr-solid-gray mr-t-xs">
						<scroll-view style="height:450upx" scroll-y="true" class="scroll-Y">
							<view class="fx-l-y-v mr-t-xs pd-b-xs" v-for="(item,index) in recruitStudentLogList" :key="index">
								<view class="fx-l box2-cell-5 mr-r-xs content-font">
									{{formatDateAll(item.updateDate)}}
								</view>
								<view class="fx-t-v-h box2-cell mr-r-xs content-font">
									{{item.undergraduatePeople}}人
								</view>
								<view class="fx-t-v-h box2-cell mr-r-xs content-font">
									{{item.technicalPeople}}人
								</view>
								<view class="fx-t-v-h box2-cell mr-r-xs content-font">
									{{item.gkPeople}}人
								</view>
								<view class="fx-l-y-v box2-cell-4 content-font">
									{{item.name}}
								</view>
							</view>
						</scroll-view>
					</view>
				</view>
			</uni-collapse-item>
			<uni-collapse-item title="最新招生情况" showAnimation="true" class="w-100">
				<view class="bg-w">
					<view class="fx-l-y-v mr-t-xs uni-row " >
						<!-- 前后端约定 1：前三天，2：近一周，3：近一个月，4：本招生季-->
						<view class=" fx-t-v-h mr-r-40 link" @click="findRecruitStudent(1)">
							近三天
						</view>
						<view class=" fx-t-v-h mr-r-40 link" @click="findRecruitStudent(2)">
							近一周
						</view>
						<view class=" fx-t-v-h mr-r-40 link" @click="findRecruitStudent(3)">
							近一个月
						</view>
						<view class=" fx-t-v-h mr-r-40 link" @click="findRecruitStudent(4)">
							本招生季
						</view>
					</view>
					<view class="fx-t-v mr-t-xs">
						<view class="fx-l-y-v">
							<view class="fx-l  title-min-font pd-t-xs mr-r-s" style="width: 220upx;">
								日期
							</view>
							<view class=" fx-t-v-h box2-cell mr-r-75 title-min-font" >
								上开<br>
								本科
							</view>
							<view class=" fx-t-v-h box2-cell mr-r-75 title-min-font">
								上开<br>
								专科
							</view>
							<view class=" fx-t-v-h box2-cell title-min-font">
								国开<br>
								本科
							</view>
						</view>
						<hr class="hr-solid-gray mr-t-xs">
						<scroll-view style="height:450upx" scroll-y="true" class="scroll-Y">
							<view class="fx-l-y-v mr-t-xs" v-for="(item,index) in recruitStudentList" :key="index">
								<view class=" fx-l  content-font mr-r-s" style="width: 220upx;">
									{{formatDateShort(item.recruitdate)}}
								</view>
								<view class=" fx-t-v-h box2-cell mr-r-75 content-font">
									{{item.undergraduatepeople}}人
								</view>
								<view class=" fx-t-v-h box2-cell mr-r-75 content-font">
									{{item.technicalpeople}}人
								</view>
								<view class=" fx-t-v-h box2-cell content-font">
									{{item.gkpeople}}人
								</view>
							</view>
						</scroll-view>
					</view>
				</view>
			</uni-collapse-item>
		</uni-collapse>
		<uni-calendar
			ref="TimeAdd"
			:insert="false"
			:start-date="formatDateShort(currSeason.recruitStartDate)"
			:end-date="formatDateShort(currSeason.recruitEndDate)"
			@confirm="ConfirmAdd"
			/>
		<uni-calendar
			ref="TimeSearch"
			:insert="false"
			:start-date="formatDateShort(currSeason.recruitStartDate)"
			:end-date="formatDateShort(currSeason.recruitEndDate)"
			@confirm="ConfirmSearch"
			/>
	</view>
</template>

<script>
	import uniCollapse from '@/components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '@/components/uni-collapse-item/uni-collapse-item.vue'
	import uniBadge from '@/components/uni-badge/uni-badge.vue'
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue'
	 import { mapState,mapActions,mapGetters} from 'vuex'
	export default{
		components: {uniBadge,uniCollapse,uniCollapseItem,uniCalendar},
		computed:{
			...mapGetters({
				formatDateAll: 'constant/formatDateAll',
				formatDateShort: 'constant/formatDateShort',
				getCurrDate: 'constant/getCurrDate',
				checkNumber: 'constant/checkNumber',
			}),
		},
		data(){
			return {
				undergraduatepeople : '',
				technicalpeople : '',
				gkpeople : '',
				recruitdateAdd : '',
				recruitDateSearch : '',
				recruitStudentList : [],
				recruitStudentLogList : [],
				currSeason : [],
			}
		},
		onLoad(){
			this.recruitdateAdd = this.getCurrDate();
			this.recruitDateSearch = this.getCurrDate();
			this.currSeason = uni.getStorageSync('currSeason');
		},
		methods:{
			...mapActions({
				saveStudent: 'page/saveStudent',
				loadRecruitStudentList : 'page/loadRecruitStudentList',
				loadRecruitStudentLogList : 'page/loadRecruitStudentLogList',
			}),
			savePeople(){
				if(!this.checkrecruitCount()){
					return;
				}
				let data = {seasonid : this.currSeason.seasonId,
							schoolid : uni.getStorageSync('schoolOne').schoolId,
							undergraduatepeople :this.undergraduatepeople,
							technicalpeople : this.technicalpeople,
							gkpeople : this.gkpeople,
							recruitdate : this.recruitdateAdd}
				this.saveStudent(data).then((res) =>{
					this.findRecruitStudentLog();
					this.findRecruitStudent(1);
				});
				this.undergraduatepeople = '';
				this.technicalpeople = '';
				this.gkpeople = '';
			},
			checkrecruitCount(){
				if(!this.checkNumber(this.undergraduatepeople)||
				!this.checkNumber(this.technicalpeople)||
				!this.checkNumber(this.gkpeople)){
					uni.showModal({
						content: '请输入正数或负数',
						showCancel: false,
					});
					return false;
				}
				return true;
			},
			TimeAdd(){
				this.$refs.TimeAdd.open();
			},
			ConfirmAdd(e) {
				this.recruitdateAdd = e.fulldate;
			},
			TimeSearch(){
				this.$refs.TimeSearch.open();
			},
			ConfirmSearch(e) {
				this.recruitDateSearch = e.fulldate;
				this.findRecruitStudentLog();
			},
			exchange(collapseArray){
				for(let index in collapseArray){
					if(collapseArray[index] == 1){
						this.findRecruitStudentLog();
					}else if(collapseArray[index] == 2){
						this.findRecruitStudent(1);
					}
				}
			},
			findRecruitStudent(value){
				let data = {schoolId : uni.getStorageSync('schoolOne').schoolId,
							seasonId : this.currSeason.seasonId,
							type : value};
				this.loadRecruitStudentList(data).then((res) =>{
					this.recruitStudentList = res;
				});
			},
			findRecruitStudentLog(){
				let data = {seasonid : this.currSeason.seasonId,
							schoolid : uni.getStorageSync('schoolOne').schoolId,
							recruitdate : this.recruitDateSearch};
				 this.loadRecruitStudentLogList(data).then((res) => {
					this.recruitStudentLogList = res;
				});
			},
		}
	}
</script>

<style>
	.coll-width{
		width:750upx;
		background-color:#FFFFFF;
	}
	.title-right{
		position: relative;
		right: 50upx;
	}
	.cell-mr-r-20{
		margin-right: 20upx;
	}
	.cell-mr-r-100{
		margin-right: 150upx;
	}
	.cell-mr-r-40{
		margin-right: 40upx;
	}
	
	.cell-mr-r-50{
		margin-right: 60upx;
	}
	.box2-cell{
		width:80upx;
	}
	.box2-cell-4{
		width:110upx;
	}
	.box2-cell-5{
		width:240upx;
	}
	
	.box-3-time{
		position: relative;
		top:0upx;
		left:0upx;
	}
</style>
