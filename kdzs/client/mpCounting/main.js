import Vue from 'vue'
import App from './App'

import store from './store'
import axios from './common/request/interface'
import moment from 'moment'

import  './static/css/base.scss'
import  './static/css/home.scss'
import  './static/css/component.scss'

Vue.config.productionTip = false

Vue.prototype.$store = store;

Vue.prototype.$axios = axios;
store.$axios = axios;

Vue.prototype.$moment = moment;
store.$moment = moment;

App.mpType = 'app';

const app = new Vue({
	store,
	...App
})
app.$mount()