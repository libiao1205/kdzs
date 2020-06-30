const state = {
	userName: '',
	token: '',
	hasLogin: false,
	loginDate: '',
	unionId: '',
	roles:[
	],
	organizations: [
	],
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
	setUnionId(state, unionId){
		state.unionId = unionId;
		uni.setStorageSync('unionId', state.unionId);
	},
	setUserInfo(state, userInfo) {
		let now = (new Date()).toDateString();
		state.hasLogin = true;
		state.userName = userInfo.name;
		state.token = userInfo.token;
		state.loginDate = now;
		state.roles = userInfo.roleDomains;
		state.organizations = userInfo.organizations;
		uni.setStorageSync('userAccount',state.userAccount);
		uni.setStorageSync('token', state.token);
		uni.setStorageSync('userName', state.userName);
		uni.setStorageSync('hasLogin', state.hasLogin);
		uni.setStorageSync('loginDate', state.loginDate);
		uni.setStorageSync('roles', state.roles);
		uni.setStorageSync('organizations', state.organizations);
	},
	removeUserInfo(state) {
		state.hasLogin = false;
		state.userName = '';
		state.token = '';
		state.loginDate = '';
		state.roles = [];
		state.organizations = [];
		
		uni.removeStorageSync('token');
		uni.removeStorageSync('userName');
		uni.removeStorageSync('hasLogin');
		uni.removeStorageSync('loginDate');
		uni.removeStorageSync('roles');
		uni.removeStorageSync('organizations');
		
		uni.setStorageSync('currSeason', "");
		uni.setStorageSync('schoolAll', "");
		uni.setStorageSync('schoolOne', "");
		uni.setStorageSync('permissionCodeList', "");
	},
}

const actions = {
	loginUserName({state,commit}, data) {
		if (state.token) return true;
        return new Promise((resolve) => {
            this.$axios({
                url: '/auth/login',
                method: 'POST',
				data: data,
            }).then(res => {
               if (res.c === 0) {
					commit('setUserInfo', res.ctt);
					resolve(true);
               } else {
					commit('removeUserInfo', res.ctt);
					uni.showModal({
						content: res.msg,
						showCancel: false,
					});
					resolve(false);
               }
            });
        })
	},
	
	async login({state,dispatch}) {
		if (state.token) return true;
		let code = await this.$axios.wxLogin();
		wx.getSetting({
		  success (res) {
	        state.canUserInfo = res.authSetting['scope.userInfo'];
			state.canUserLocation = res.authSetting['scope.userLocation'];
		  }
		});
		return await dispatch('wxLogin', code);
	},
	async relogin({state,dispatch}) {
		if (!state.token) {
			let code = await this.$axios.wxLogin();
			return await dispatch('wxLogin', code);
		}
	},	
	async wxLogin({commit, state}, code) {
		return await this.$axios({
			url: '/auth/wx/login',
			method: 'POST',
			data: {
				wxCode: code,
				unionId: uni.getStorageSync('unionId'),
			},
		}).then((res) => {
			if (res.c === 0) {
				commit('setUserInfo', res.ctt);
				return true;
			} else {
				commit('removeUserInfo', res.ctt);
				return false;
			}
		})
	},
	async logout({commit}) {
		commit('removeUserInfo');
	},
	async registerPhone({
		dispatch
	}, data) {
		if (state.token) return true;
		let code = await this.$axios.wxLogin();
		data.wxCode = code;
		return await dispatch('wxRegisterPhone', data);
	},
	async wxRegisterPhone({
		commit
	}, data) {
		return await this.$axios({
			url: '/auth/wx/registerPhone',
			method: 'POST',
			data: data,
		}).then((res) => {
			if (res.c === 0) {
				commit('setUserInfo', res.ctt);
				return true;
			} else if (res.c === 10017) {
				uni.showToast({
					icon: 'none',
					title: '处理失败，请先关注公众号！'
				});
			} else {
				uni.showModal({
					content: res.msg,
					showCancel: false,
				});
				return false;
			}
		})
	},
}
export default {
	namespaced: true,
	state,
	mutations,
	actions,
	getters,
}
