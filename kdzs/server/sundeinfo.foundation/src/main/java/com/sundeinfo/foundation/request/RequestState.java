package com.sundeinfo.foundation.request;

import org.springframework.http.HttpStatus;

public enum RequestState {

    SUCCESS(0, "执行成功"),

    ERROR(1,"服务器发生未知异常"),

    ERROR400(400,"错误的请求"),
    ERROR401(401,"未经授权的访问"),
    ERROR403(403,"禁止访问"),
    ERROR404(404,"没有找到资源"),
    ERROR405(405,"方法不允许"),
    ERROR406(406,"不可接受"),
    ERROR407(407,"需要代理身份验证"),
    ERROR408(408,"请求超时"),
    ERROR409(409,"指令冲突"),
    ERROR500(500,"内部服务器错误"),
    ERROR501(501,"未实现"),
    ERROR503(503,"服务不可用"),

    PARAM_CHECK_CONFIG_ERROR(1000, "参数校验配置异常"),
    PARAM_USELESS(1001, "参数无效"),
    PARAM_NULL(1002, "必须参数为空"),
    PARAM_USELESS_RANDOM(1012, "有相同的外链RANDOM"),
    PARAM_SCHEMA_ERROR(1101,"数据表检查异常"),

    PARAM_PERMISSION_NULL(1301, "权限列表为空"),
    PARAM_PERMISSION_NULL_MEMBER(1302, "权限没有指定用户"),
    PARAM_PERMISSION_ERROR_TYPE(1303, "权限类型设定错误"),
    PARAM_PERMISSION_MULTIPLE(1304, "指定资源已有特定权限设定，不能新增"),
    PARAM_PERMISSION_NULL_ROLE(1305, "没有找到相关角色"),
    PARAM_PERMISSION_EXIST_SEASON(1306, "该时间段已有其他季度"),

    REQUEST_USELESS(2001, "请求无效"),
    REQUEST_ILLEGAL(2002, "请求非法"),
    REQUEST_PASSWORD_NULL(2003, "需要访问密码"),
    REQUEST_PASSWORD_USELESS(2004, "无效的访问密码"),

    TOKEN_ERROR(3001,"令牌检查异常"),
    TOKEN_IMPL_ERROR(3002,"令牌校验方式异常"),

    DB_INSERT_ERROR(4001,"数据新建失败"),
    DB_UPDATE_ERROR(4002,"数据更新失败"),
    DB_DELETE_ERROR(4003,"数据删除失败"),

    PARAM_PASSWORD_ERROR(10001, "原始密码不正确"),
    PARAM_PASSWORD_NEW_ERROR(10002, "新密码不能与旧密码一致"),
    PARAM_CARD_NAME_ERROR(10003, "用户名与身份证不匹配"),
    PARAM_USERNAME_PASSWORD_NULL(10004, "用户名或者密码不能为空"),
    PARAM_USELESS_USERNAME_PASSWORD(10005, "用户名或者密码不正确"),
    PARAM_PASSWORD_NULL(10006, "密码不能为空"),
    PARAM_USELESS_USERNAME(10007, "用户名已经存在"),
    PARAM_USELESS_PHONE(10008, "手机号已经存在"),
    PARAM_PASSWORD_RE_ERROR(10009, "两次密码输入不一致"),

    WX_AUTH_ERROR(5001,"微信认证失败,请稍后再试！"),

    WX_AUTH_NO_USER_ERROR(5002,"用户不存在"),

    NO_IMPLEMENTED(9999,"接口没有完成"),;


    private final int id;

    private final String value;

    RequestState(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int id() {
        return this.id;
    }

    public String value() {
        return this.value;
    }


    public static HttpStatus valueOfHttpStatus(RequestState state){
        switch (state){
            case ERROR:
            case ERROR500:
            case TOKEN_IMPL_ERROR:
                return HttpStatus.INTERNAL_SERVER_ERROR;
            case ERROR400:
                return HttpStatus.BAD_REQUEST;
            case ERROR401:
            case TOKEN_ERROR:
                return HttpStatus.UNAUTHORIZED;
            case ERROR403:
            case REQUEST_ILLEGAL:
                return HttpStatus.FORBIDDEN;
            case ERROR404:
                return HttpStatus.NOT_FOUND;
            case ERROR405:
                return HttpStatus.METHOD_NOT_ALLOWED;
            case ERROR406:
                return HttpStatus.NOT_ACCEPTABLE;
            case ERROR407:
                return HttpStatus.PROXY_AUTHENTICATION_REQUIRED;
            case ERROR408:
                return HttpStatus.REQUEST_TIMEOUT;
            case ERROR409:
                return HttpStatus.CONFLICT;
            case ERROR501:
            case ERROR503:
                return HttpStatus.SERVICE_UNAVAILABLE;
            default:
                return HttpStatus.OK;
        }
    }
}
