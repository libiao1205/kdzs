package com.sundeinfo.foundation.request;

public interface ResponseCallback<T> extends PostCallback {
    /** 获取Response对象 */
    T response() throws Exception;
}
