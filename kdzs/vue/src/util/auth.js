const authToken = {
    // 当Token超时后采取何种策略
    // jumpAuthPage  每次请求时判断Token是否超时，若超时则跳转到授权页面
    // getNewToken  每次请求时判断Token是否超时，若超时则获取新Token (推荐)
    tokenTimeoutMethod: 'getNewToken',

    // 在Cookie中记录登录状态的key
    loginKey: 'isLogin',

    // Token是否超时
    hasToken: function(){
        return sessionStorage.getItem('token')
    },

    // 当前是否是登录状态
    isLogin: function(){
        return sessionStorage.getItem(this.loginKey)
    },

    // 设置Token
    setToken: function(token){
        sessionStorage.setItem('token', token)
    },

    // 设置登录状态
    setLoginStatus: function(){
        sessionStorage.setItem(this.loginKey, 'true')
    },

    // 移除Token
    removeToken: function(){
        sessionStorage.removeItem('token')
    },

    // 移除登录状态
    removeLoginStatus: function(){
        sessionStorage.removeItem(this.loginKey)
    }
}

export default authToken
