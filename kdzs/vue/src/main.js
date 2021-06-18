// 生产环境中注释掉以下语句
import 'sysStatic/css/theme-default.scss'
import './assets/iconfont/iconfont.css'

import 'babel-polyfill'
import Vue from "vue"
import ElementUI from 'element-ui'
import router from './router'
import store from './store'
import axios from './util/ajax'
import App from './index'
import moment from 'moment'
import InfiniteLoading from 'vue-infinite-loading';
import './components/install'
import './plugins/install'
import './assets/iconfont/iconfont'
import animated from 'animate.css'
import 'swiper/css/swiper.css'
import VueAwesomeSwiper from 'vue-awesome-swiper'

// 注册组件到Vue
Vue.prototype.$axios = axios;
Vue.prototype.$moment = moment;
Vue.use(ElementUI);
Vue.use(InfiniteLoading);
Vue.use(animated);
Vue.use(VueAwesomeSwiper)

new Vue({
    axios,
    router,
    store,
    render: h => h(App)
}).$mount('#app');
