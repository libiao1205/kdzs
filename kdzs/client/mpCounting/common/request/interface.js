import _config from './config'

const $http = function(options) {
  options.url = _config.url + options.url
  return new Promise((resolve, reject) => {
    uni.showLoading({ title: '加载中' })
    let token = uni.getStorageSync('token')
    // 拦截请求
    if (token) {
      _config.header = {
        Authorization: 'Bearer' + token,
		
      }
    } else {
      _config.header = {}
    }
    _config.complete = response => {
    if (response.statusCode === 200) {
        if (response.data && response.data.c && response.data.c > 90000) {
            uni.showToast({
            icon: 'none',
            title: response.data.msg
          })
        }
        resolve(response.data)
    } else if(response.statusCode === 403){
		uni.redirectTo({
			url: '../../user/login'
		});
	}else {
        uni.stopPullDownRefresh()
        if (options.handle) {
          reject(response)
        } else {
          try {
            Promise.reject(response).catch(err => {
              _page_error(response.statusCode || response.errMsg)
            })
          } catch (e) {
            console.log(e)
          }
        }
      }
      uni.hideLoading()
    }
    let data = Object.assign({}, _config, options)
    uni.request(data)
  })
}

$http.wxLogin = () => {
  return new Promise(resolve => {
    uni.login({
      provider: 'weixin',
      success: res => {
        if (res.code) {
          resolve(res.code)
        } else {
          resolve(false)
        }
      },
      fail: () => {
        resolve(false)
      }
    })
  })
}

$http.wxSession = () => {
  return new Promise(resolve => {
    uni.checkSession({
      success: () => {
        resolve(true)
      },
      fail: () => {
        resolve(false)
      }
    })
  })
}

$http.wxUserInfo = () => {
  return new Promise(resolve => {
    uni.getUserInfo({
      provider: 'weixin',
      lang: 'zh_CN',
      success: res => {
        if (res && res.userInfo) {
          resolve(res.userInfo)
        } else {
          resolve(false)
        }
      },
      fail: () => {
        resolve(false)
      }
    })
  })
}

function _error(err, msg = '') {
  switch (err) {
    case 400:
      console.error(msg)
      break
  }
  if (msg) {
    uni.showToast({
      icon: 'none',
      title: msg
    })
  }
}

function _page_error(err) {
  let title = ''
  switch (err) {
    case 401:
      title = '请求被拒绝：401'
      break
    case 404:
      title = '没有找到页面：404'
      break
    case 405:
      title = '错误的请求：405'
      break
    case 'request:fail timeout':
      title = '访问服务器超时'
      break
    default:
      title = '访问服务器失败'
      break
  }
  if (title) {
    uni.showToast({
      icon: 'none',
      title: title
    })
  }
}

export default $http
