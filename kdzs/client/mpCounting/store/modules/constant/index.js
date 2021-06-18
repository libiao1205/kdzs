function  getDisplayName(dataList, value, defaultValue) {
    let data = dataList.find(c => c.code === value);
    if (data)
        return data.value;
    else
        return defaultValue.value;
}
function  isObjectUndefined(value) {
	if(value == null || value == undefined){
		return false;
	}else{
		return true;
	}
}
const state = {
    gender: [
        { code: 0, value: '保密' },
        { code: 1, value: '男' },
        { code: 2, value: '女' }
    ],
	season:[
		{code: 0, value: '春季'},
		{code: 1, value: '秋季'},
		{code: false, value: '春季'},
		{code: true, value: '秋季'}
	],
	recruitStatus:[
		{code:false,value:'进行中'},
		{code:true,value:'结束'}
	],
	permissionCode:[
		{code: "add", value: "school:recruit:counting:add"},
		{code: "view", value: "school:recruit:counting:view"},
		{code: "viewAll", value: "school:recruit:counting:viewall"},
		{code: "addSum", value: "school:recruit:counting:addsum"},
	],
	sumSchool:['总校','公共管理学院','经济管理学院','人文学院','理工学院'],
	sumSchoolRoleCode: 'ROLE:RECRUIT:COUNTINGSUMSCHOOL'
};

const getters = {
    getGenderNameForCell: (state) => (row, column, cellValue) => {
        return getDisplayName(state.gender, cellValue, state.gender[0]);
    },
    getPlanTypeNameForCell: (state) => (row, colum, cellValue) => {
        return getDisplayName(state.planType, cellValue, state.planType[0]);
    },
    getPercentNameForCell: () => (row, colum, cellValue) => {
        return `${cellValue}%`;
    },
    getIsEndNameForCell: () => (row, colum, cellValue) => {
        return getDisplayName(state.isEndStatus, cellValue, state.isEndStatus[0]);
    },
    getStudentCategoryNameForCell: () => (row, colum, cellValue) => {
        return getDisplayName(state.studentCategory, cellValue, state.studentCategory[0]);
    },
    getStudentTypeNameForCell: () => (row, colum, cellValue) => {
        return getDisplayName(state.studentType, cellValue, state.studentType[0]);
    },
    getHasWeChatNameForCell: () => (row, colum, cellValue) => {
        if (cellValue && cellValue > 0) return '已绑定';
        return '未绑定'
    },
    getAuditNameForCell: () => (row, colum, cellValue) => {
        return getDisplayName(state.auditStatus, cellValue, state.auditStatus[1]);
    },
    getTrueNameForCell: () => (row, colum, cellValue) => {
        if (cellValue === 1) return '是';
        return '否'
    },
	getNotFilledName: () => (value) => {
		if (value) return value;
		return '';
	},
	getCurrSeason: () => (cellValue) => {
	    return getDisplayName(state.season, cellValue, '空');
	},
	isUndefined: () => (value) => {
		if(value === null || value === undefined){
			return 0;
		}else{
			return value;
		}
	},
	isNull:() => (value) =>{
		if(value === null || value === undefined){
			return ''
		}else{
			return value;
		}
	},
	getRecruitYear: () => (season) =>{
		if(isObjectUndefined(season)){
			return season.recruitYear;
		}else{
			return '--';
		}
	},
	getOnRecruitYear: () => (season) =>{
		if(isObjectUndefined(season)){
			return season.onRecruitYear;
		}else{
			return '--';
		}
	},
	getRecruitSeason: () => (season) =>{
		if(isObjectUndefined(season)){
			let recruitSeason = season.recruitseason == undefined ? season.recruitSeason : season.recruitseason;
			return getDisplayName(state.season, recruitSeason, '空');
		}else{
			return '--';
		}
	},
	
	isPermissionCode: (state) => (value) => {
		let flag = false;
		let permissionCodeList = uni.getStorageSync('permissionCodeList');
		for(let index in permissionCodeList){
			if(getDisplayName(state.permissionCode,value,"") === permissionCodeList[index]){
				flag = true;
				break;
			}
		}
		return flag;
	},
	getRecruitStatus: (state) => (value) => {
		return getDisplayName(state.recruitStatus, value, '空');
	},
	formatDateAll: () => (number) => {
		let date = new Date(number);
		let month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
		let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		let hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
		let minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
		return date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minute;
	},
	formatDateShort: () => (number) => {
		let date = new Date(number);
		let month = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
		let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		return date.getFullYear() + "-" + month + "-" + day ;
	},
	getCurrDate:() => () => {
		let date = new Date();
		let month = (date.getMonth()+1)<10 ? "0"+(date.getMonth()+1) : (date.getMonth()+1);
		let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		return date.getFullYear() + "-" + month + "-" + day;
	},
	
	timeDifference:() => (endDate) =>{
		let day = (endDate - (new Date().getTime()))/1000/60/60/24;
		return  Math.ceil(day)+'天';
	},
	isRevise:() => (revise) =>{
		if(revise === 1){
			return "校";
		}
		return "";
	},
	copyText:() => (excelUrl) =>{
		uni.showModal({
			title : "复制网址下载文件",
			content: excelUrl,
			showCancel: false,
			confirmText:'复制',
			success: function (res) {  
				if (res.confirm) {  
					uni.setClipboardData({
						data:excelUrl,
						success() {
							uni.showToast({
								title: '复制成功',
								duration:2000,
								icon:"none"
							})
						}	
					});
				}  
			}  
		});
	},
	checkNumber:() => (number) =>{
		let reg = /^[-]{0,1}\d+$/;
		if(reg.test(number)){
			return true;
		}
		return false;
	},
	getSumSchoolIds:(state) => () =>{
		let roles = uni.getStorageSync('roles');
		if(roles !=null && roles != '' && roles != []){
			if(roles[0].roleCode === state.sumSchoolRoleCode){
				return "(2,3,4,5)";
			}
		}
		return "";
	},
	selectSchoolId:() => (index) =>{
		if(index === "0"){
			return 2;
		}else if(index === "1"){
			return 3;
		}else if(index === "2"){
			return 4;
		}else if(index === "3"){
			return 5;
		}
	},
	getSchoolOne: () => () =>{
		if(uni.getStorageSync('schoolOne')){
			return uni.getStorageSync('schoolOne');
		}
		return {name:''};
	}
};

export default {
    namespaced: true,
    state,
    getters
}
