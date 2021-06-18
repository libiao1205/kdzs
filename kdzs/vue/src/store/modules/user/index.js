import de from "element-ui/src/locale/lang/de";

const USERTYPE_STUDENT = 1;
const USERTYPE_UNIT = 2;
const USERTYPE_ADMIN = 3;

const state = {
    // 用户名
    name: '',
    operation: '',
    roleName:[{key:'ROLE:ADMIN',name:'管理员'},
        {key:'ROLE:RECRUIT:COUNTING',name:'填写人员'},
        {key:'ROLE:RECRUIT:COUNTING:VIEW',name:'查阅人员'},
        {key:'ROLE:RECRUIT:COUNTINGSUMSCHOOL',name:'总校填写人员'}],
    url:[
        {code : 1, url: '/home'},
        {code : 2, url: '/school'},
        ],
    roles:[
        {key: '/userInfo', operations: [USERTYPE_STUDENT]},
        {key: '/school', operations: [USERTYPE_UNIT, USERTYPE_ADMIN]},
        {key: '/school/course', operations: [USERTYPE_UNIT, USERTYPE_ADMIN]},
        {key: '/school/teacher', operations: [USERTYPE_UNIT, USERTYPE_ADMIN]},
        {key: '/school/student', operations: [USERTYPE_ADMIN]},
        {key: '/school/unit', operations: [USERTYPE_ADMIN]},
        {key: '/school/editUnit', operations: [USERTYPE_UNIT]},
        {key: 'signup', operations: [USERTYPE_STUDENT]},
        {key: 'editCourse', operations: [USERTYPE_UNIT]},
        {key: 'editTeacher', operations: [USERTYPE_UNIT]},
        {key: 'sendMessage', operations: [USERTYPE_ADMIN]},
        {key: 'afterSignUp', operations: [USERTYPE_ADMIN]},
    ],
    isShowNotice: true,
};

const mutations = {
    setName: (state, data) => {
        if(data){
            sessionStorage.setItem('userName', encodeURIComponent(data))
        } else {
            sessionStorage.removeItem('userName')
        }
        state.name = data
    },
    setOperation: (state, data) => {
        if(data){
            sessionStorage.setItem('operation', encodeURIComponent(data))
        } else {
            sessionStorage.removeItem('operation')
        }
        state.operation = data
    },
    setIsShowNotice: (state, data) => {
        sessionStorage.setItem('isShowNotice', encodeURIComponent(data))
        state.isShowNotice = data
    },
};
const getters = {
    getDefaultUrl: (state) => () => {
        let r = state.url.find(c => c.code === state.operation);
        return r ? r.url: '/home';
    },
    getHasRole: (state) => (key) => {
        let r = state.roles.find(c => c.key === key);
        if (!r) return true;
        return r.operations.find(c => c === state.operation);
    },
    getUserName:(state) => () =>{
      let username = sessionStorage.getItem("userName")
      if(username){
          return decodeURIComponent(username)
      } else{
          return ''
      }
    },
    getRoleName: (state) => () => {
        let r = state.roleName.find(c => encodeURIComponent(c.key) === sessionStorage.getItem("operation"));
        if (r){
            return r.name
        }else{
            return ''
        }
    },
    isAdmin:(state) => () =>{
        if(encodeURIComponent("ROLE:ADMIN") === sessionStorage.getItem("operation")){
            return true
        }else {
            return false;
        }
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    getters
}
