package com.sundeinfo.foundation.request;


public interface GetCallback<T> {
    /** 参数检查 */
    RequestState doCheck();

    /** 获取Get对象 */
    T invoke() throws Exception;
}
