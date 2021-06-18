// 组件全局注册
import Vue from 'vue'
import AppSvg from './AppSvg'
import AppCard from './AppCard'
import AppCourseType from './AppCourseType'
import AppInfinite from './AppInfinite'


// 组件库
const Components = [
    AppSvg,
    AppCard,
    AppCourseType,
    AppInfinite
]

// 注册全局组件
Components.map((com) =>{
    Vue.component(com.name, com)
})

export default Vue
