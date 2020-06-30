package com.sundeinfo.foundation.request;

import com.sundeinfo.foundation.mvc.component.AbstractComponent;
import com.sundeinfo.foundation.request.result.Result;
import com.sundeinfo.foundation.request.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@SuppressWarnings("unchecked")
public class RequestHandler extends AbstractComponent<RequestHandler> {

    @Autowired
    HttpServletRequest request;

    /**
     * 执行Get请求
     */
    public <T> ResponseEntity<Result<T>> doGet(final GetCallback<T> callback){
        if (request != null){
            logger.info("请求:[" + request.getMethod() + "] " + request.getRequestURL());
        }
        try{
            RequestState state = callback.doCheck();
            //结果检查
            if (state != RequestState.SUCCESS)
            {
                //异常发生时直接返回
                return ResultFactory.getDataMessage(state,null);
            }
            //返回值取得
            T t = callback.invoke();
            //返回值检查
            if (t == null){
                logger.warn("被执行的请求没有有效响应结果：db=null");
                return ResultFactory.getDataMessage(RequestState.SUCCESS,null);
            }
            //正常返回
            if (logger.isDebugEnabled()){logger.debug("执行请求成功：db=" + t.toString());}
            return ResultFactory.getDataMessage(RequestState.SUCCESS,t);
        }catch (Exception e)
        {
            logger.error("执行请求失败:", e);
            return ResultFactory.getDataMessage(RequestState.ERROR,null);
        }
    }

    /**
     * 执行Post请求:无返回
     */
    public ResponseEntity<Result> doPost(final PostCallback callback) {
        if (request != null){
            logger.info("请求:[" + request.getMethod() + "] " + request.getRequestURL());
        }
        try {
            //参数校验
            RequestState checkState = callback.doCheck();
            if (checkState != RequestState.SUCCESS)
            {
                //异常发生时直接返回
                return ResultFactory.getDataMessage(checkState);
            }
            //数据更新
            RequestState invokeState = callback.invoke();
            //结果返回
            return ResultFactory.getDataMessage(invokeState);
        }catch (Exception e)
        {
            logger.error("执行请求失败:", e);
            return ResultFactory.getDataMessage(RequestState.ERROR);
        }
    }

    /**
     * 执行Post请求:有返回
     */
    public <T> ResponseEntity<Result<T>> doPost(final ResponseCallback<T> responseCallback) {
        if (request != null){
            logger.info("请求:[" + request.getMethod() + "] " + request.getRequestURL());
        }
        try {
            //参数校验
            RequestState checkState = responseCallback.doCheck();
            if (checkState != RequestState.SUCCESS)
            {
                //异常发生时直接返回
                return ResultFactory.getDataMessage(checkState,null);
            }
            //数据更新
            RequestState invokeState = responseCallback.invoke();
            //结果检查
            if (invokeState != RequestState.SUCCESS)
            {
                //异常发生时直接返回
                return ResultFactory.getDataMessage(invokeState,null);
            }
            //返回值取得
            T t = responseCallback.response();
            //返回值检查
            if (t == null){
                logger.warn("被执行的请求没有有效响应结果");
                return ResultFactory.getDataMessage(RequestState.SUCCESS,null);
            }
            //正常返回
            if (logger.isDebugEnabled()){logger.debug("执行请求成功");}
            return ResultFactory.getDataMessage(RequestState.SUCCESS,t);
        }catch (BadCredentialsException be){
            logger.error("登录失败:", be);
            return ResultFactory.getDataMessage(RequestState.PARAM_USELESS_USERNAME_PASSWORD,null);
        }
        catch (Exception e)
        {
            logger.error("执行请求失败:", e);
            return ResultFactory.getDataMessage(RequestState.ERROR,null);
        }
    }
}

