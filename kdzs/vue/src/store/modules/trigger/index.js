const state = {
    signUp: 0
};

const mutations = {
    setSignUp: (state) => {
        state.signUp += 1;
    },
};

export default {
    namespaced: true,
    state,
    mutations
}
