package com.sundeinfo.foundation.request;


public interface PostCallback{
    /** 参数检查 */
    RequestState doCheck() throws Exception;

    /** 执行Post请求 */
    RequestState invoke() throws Exception;
}
