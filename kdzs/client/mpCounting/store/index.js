import Vue from 'vue'
import Vuex from 'vuex'
import vueModules from './modules'

Vue.use(Vuex)

export default new Vuex.Store({
	modules:vueModules
})

