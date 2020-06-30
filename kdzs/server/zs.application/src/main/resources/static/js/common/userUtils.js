userUtils = function () {

};

userUtils.getToken = function () {
    return localStorage.getItem('x-token');
};

userUtils.saveToken = function (token) {
    localStorage.setItem('x-token', token);
};

userUtils.clearToken = function () {
    localStorage.removeItem('x-token');
};

userUtils.clearUserInfo = function () {
    localStorage.removeItem('x-user-info');
};


userUtils.getUserInfo = function () {
    return JSON.parse(localStorage.getItem('x-user-info'));
};

userUtils.saveUserInfo = function (userInfo) {
    let data ={
        organizations : userInfo.organizations,
        rolePermissionDomains : userInfo.rolePermissionDomains,
    };
    localStorage.setItem('x-user-info', JSON.stringify(data));
};

userUtils.hasSchoolRecruitAddPermission = function() {
    let userInfo = userUtils.getUserInfo();

    if (!userInfo) {
        return false;
    }
    return userInfo.rolePermissionDomains.filter(item => item.permissionCode === 'school:recruit:add').length > 0;
};