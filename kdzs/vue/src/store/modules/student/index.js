import axios from '@/util/ajax'

const actions = {
    getList({}, params) {
        return new Promise((resolve) => {
            axios({
                url: '/student/list',
                method: 'get',
                params: params
            }).then(res => {
                resolve(res)
            });
        })
    },
    getSaleUrl({}, courseId) {
        return new Promise((resolve) => {
            axios({
                url: '/studentCourse/saleurl',
                method: 'post',
                data: {courseid : courseId}
            }).then(res => {
                resolve(res)
            });
        })
    }
};

export default {
    namespaced: true,
    actions
}
