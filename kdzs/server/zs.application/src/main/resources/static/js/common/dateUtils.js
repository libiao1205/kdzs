dateUtils = function () {

};

// 后台返回的星期下标转换为数组下标
dateUtils.changeWeekIndexFromBackend = function(index) {
    // 周日
    if (index === 1) {
        return 6;
    } else if (index === 2) {
        // 周一
        return 0;
    } else if (index === 3) {
        // 周二
        return 1;
    } else if (index === 4) {
        // 周三
        return 2;
    } else if (index === 5) {
        // 周四
        return 3;
    } else if (index === 6) {
        // 周五
        return 4;
    } else if (index === 7) {
        // 周六
        return 5;
    }
};

// 后台返回的月份下标转换为数组下标
dateUtils.changeMonthIndexFromBackend = function(index) {
    return index - 1;
};

// 后台返回的季度下标转换为数组下标
dateUtils.changeSeasonIndexFromBackend = function(index) {
    return index - 1;
};

dateUtils.format = function (date, fmt) {

    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};