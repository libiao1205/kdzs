
requestServer = function(){}

requestServer.ajax = function (data) {
    let token = userUtils.getToken();
    if (token) {
        let headers = {
            'Authorization': 'Bearer' + token
        };
        data.headers = headers;
    }
    $.ajax(data);
}

requestServer.gotoUrl = function (url) {
    window.location = url;
}