const state = {
	currSeason:[],
	schoolAll:[],
}
const getters = {
    hasRole: (state, getters) => (key) => {
        let r = state.roles.find(c => c.key === key);
        if (!r) return false;
        return true;
    },
}

const mutations = {

}

const actions = {
	//***************************************************  首页统计*********************
	loadCurrSeason({state,commit}) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/currSeason',
				method: 'GET',
			}).then(res => {
			    if (res.c === 0) {
				   uni.setStorageSync('currSeason', res.ctt);
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadSchoolAll({state,commit}) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/schoolFindAll',
				method: 'GET',
			}).then((res) => {
			    if (res.c === 0) {
				    uni.setStorageSync('schoolAll', res.ctt);
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			    }
			});
		})
	},
	loadSchoolOne({state,commit}) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/schoolFindOne',
				method: 'GET',
			}).then((res) => {
			    if (res.c === 0) {
				   uni.setStorageSync('schoolOne', res.ctt);
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	findSchoolByNamePlan({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/SchoolByNamePlan',
				method: 'GET',
				data:data,
			}).then((res) => {
			   if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	findSchoolByNameRevisePeople({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/SchoolByNameRevisePeople',
				method: 'GET',
				data:data,
			}).then((res) => {
			   if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadStatistics({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/statisticsHome',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadStatisticsAnime({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/statisticsHomeAnime',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadStatisticsDetail({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/statisticsDetail',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	exportExcel({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/getExcel',
				method: 'GET',
				//data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadPermissionCode({state,commit}) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/getAuthority',
				method: 'GET',
			}).then((res) => {
			    if (res.c === 0) {
				   uni.setStorageSync('permissionCodeList', res.ctt);
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	//*******************************添加招生人数************************
	
	saveStudent({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/recruitStudent',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(true);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	updatePeople({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/updateRecruitSum',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(true);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadRecruitStudentList({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/getRecruitStudentList',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})
	},
	loadRecruitStudentLogList({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/getRecruitStudentLogList',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	},
	//***************************************************招生季*********************************
	saveSeason({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/saveSeason',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(true);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	},
	updateSeason({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/updateSeason',
				method: 'POST',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(true);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			    }
			});
		})	
	},
	querySeason({state,commit}){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/querySeason',
				method: 'GET',
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	},
	querySeasonoOne({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/querySeasonOne',
				method: 'GET',
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	},
	findWhetherOvertime({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/whetherOvertime',
				method: 'GET',
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	},
	//***************************************************招生计划*********************************
	updateRecruitPlan({state,commit},data){
		return new Promise((resolve) => {
			this.$axios({
				url: '/school/recruit/updateRecruitPlan',
				method: 'POST',
				data:data,
				data:data,
			}).then((res) => {
			    if (res.c === 0) {
					resolve(res.ctt);
			    } else {
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
			   }
			});
		})	
	}
}

export default {
	namespaced: true,
	state,
	mutations,
	actions,
	getters,
}
