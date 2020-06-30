package com.sundeinfo.core.log;

import com.sundeinfo.foundation.mvc.service.Service;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

public interface  LogService<T,D extends LogInfo> extends Service<T> {

    D createLog(ProceedingJoinPoint joinPoint, ActionLog log, HttpServletRequest request);

    void export(D d);
}
