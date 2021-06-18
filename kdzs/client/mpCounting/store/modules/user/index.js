const actions = {
	
	updatePassword({state,commit},data) {
		return new Promise((resolve) => {
			this.$axios({
				url: '/auth/updatePassword',
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
};

export default {
    namespaced: true,
    actions
}
