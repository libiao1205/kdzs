import axios from '@/util/ajax'

const state = {
    areas: [],
    teachers: [],
    natures: [],
    units: []
};

const actions = {
    loadAreas({state}) {
        return new Promise((resolve) => {
            if (state.areas.length > 0) resolve(true);
            axios({
                url: '/dictionary/areas',
                method: 'get',
            }).then(res => {
                state.areas = res.ctt.list;
                resolve(true)
            });
        })
    },
    loadNatures({state}) {
        return new Promise((resolve) => {
            if (state.natures.length > 0) resolve(true);
            axios({
                url: '/dictionary/nature',
                method: 'get',
            }).then(res => {
                state.natures = res.ctt.list;
                resolve(true)
            });
        })
    },
    loadUnits({state}) {
        return new Promise((resolve) => {
            if (state.units.length > 0) resolve(true);
            axios({
                url: '/dictionary/units',
                method: 'get',
            }).then(res => {
                state.units = res.ctt.list;
                resolve(true)
            });
        })
    },
    loadTeachers({state}){
        return new Promise((resolve) => {
            if(state.teachers.length > 0) resolve(true);
            axios({
                url: '/teacher/all',
                method: 'get',
            }).then(res => {
                state.teachers = res.ctt.list;
                resolve(true)
            });
        })
    },
};
const getters = {
    getAreaNameForCell: (state) => (row, column, cellValue) => {
        let data = state.areas.find(c => c.code === cellValue);
        return data ? data.value : '';
    },
}

export default {
    namespaced: true,
    state,
    actions,
    getters
}
